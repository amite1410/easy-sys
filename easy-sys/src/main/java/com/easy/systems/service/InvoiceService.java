package com.easy.systems.service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.easy.systems.bean.InvoiceCounter;
import com.easy.systems.bean.InvoiceDetails;
import com.easy.systems.bean.InvoiceProductDetails;
import com.easy.systems.bean.ProductDetails;
import com.easy.systems.bean.ProductInventoryDetails;
import com.easy.systems.dao.DailySaleReportDao;
import com.easy.systems.dao.InventoryDao;
import com.easy.systems.dao.InvoiceCounterDao;
import com.easy.systems.dao.InvoiceDao;
import com.easy.systems.dao.ProductDao;

@Service
@Transactional
public class InvoiceService {

	@Autowired
	private InvoiceDao id;
	@Autowired
	private InventoryDao inventoryDao;
	@Autowired
	private DailySaleReportDao repotdao;
	@Autowired
	private ProductDao productDao;
	@Autowired
	private InvoiceCounterDao counter;

	private List<InvoiceProductDetails> productList = new ArrayList<InvoiceProductDetails>();
	private static SimpleDateFormat sdfmt1 = new SimpleDateFormat("dd/MM/yy");

	private static Date date;

	private volatile Boolean status = false;

	public List<InvoiceDetails> getActiveInvoice() {
		return null;
	}

	public InvoiceDetails addInvoice(InvoiceDetails invoiceDetails) {
		try {
			Date invoiceDate = new Date();
			if (date == null || date != invoiceDate) {
				date = invoiceDate;
			}

			InvoiceCounter invoiceCounter = getCounter();
			invoiceCounter.setInvoiceDate(invoiceDate);
			invoiceDetails.setInvoiceCode(sdfmt1.format(invoiceDate) + "-" + invoiceCounter.getInvoiceCounter());
			invoiceDetails.setCreateDate(invoiceDate);
			invoiceDetails.setDeliveryDate(invoiceDate);
			invoiceDetails.setDeliveryStatus("Complete");
			invoiceDetails.setOrderBy("ADMIN");
			invoiceDetails.setIsActive("Y");
			invoiceDetails.setNumberOfPiece(0);
			invoiceDetails.setNumberOfProduct(0);
			invoiceDetails.setTaxCollected(new BigDecimal(0));
			invoiceDetails.setTotalPayableAmount(new BigDecimal(0));

			for (InvoiceProductDetails obj : productList) {
				obj.setInvoiceCode(invoiceDetails.getInvoiceCode());
				obj.setCreateDate(invoiceDate);
				if (obj.getDiscountedPiece() == 0) {
					obj.setDiscountedPiece(0);
				}
				invoiceDetails.setTaxCollected(invoiceDetails.getTaxCollected().add(obj.getSubTax()));
				invoiceDetails.setNumberOfPiece(
						obj.getUnitSold() + invoiceDetails.getNumberOfPiece() + obj.getDiscountedPiece());
				invoiceDetails.setTotalPayableAmount((invoiceDetails.getTotalPayableAmount().add(obj.getSubTotal()))
						.setScale(0, RoundingMode.HALF_UP));
				invoiceDetails.setNumberOfProduct(invoiceDetails.getNumberOfProduct() + 1);
				repotdao.persist(obj);

			}

			if (invoiceDetails.getOtherDiscountPercent() == null) {
				invoiceDetails.setOtherDiscountPercent(new BigDecimal(0));
			}
			BigDecimal otherDiscount = invoiceDetails.getTotalPayableAmount()
					.subtract(invoiceDetails.getTaxCollected());
			otherDiscount = (otherDiscount.multiply(invoiceDetails.getOtherDiscountPercent()))
					.divide(new BigDecimal(100));
			invoiceDetails.setTotalPrice(((invoiceDetails.getTotalPayableAmount()).subtract(otherDiscount)).setScale(0,
					RoundingMode.HALF_UP));

			id.persist(invoiceDetails);
			for (InvoiceProductDetails obj : productList) {
				ProductInventoryDetails inventory = inventoryDao.getProductByID(obj.getProductCode());
				inventory.setTotalQuantity(inventory.getTotalQuantity().subtract(new BigDecimal(obj.getUnitSold()))
						.subtract(new BigDecimal(obj.getDiscountedPiece())));
				updateQuantity(inventory);
			}
			updateCounter(invoiceCounter);
			status = true;
		} catch (Exception e) {
			status = false;
			e.printStackTrace();
		} finally {
			productList.clear();

		}

		return invoiceDetails;
	}

	public boolean updateInvoice(InvoiceDetails invoiceDetails) {
		try {
			if (invoiceDetails.getDeliveryStatus().equalsIgnoreCase("Close")) {
				invoiceDetails.setIsActive("N");
			}

			if (invoiceDetails.getDeliveryStatus().equalsIgnoreCase("Cancel")) {
				invoiceDetails.setIsActive("C");
				productList = getProductList(invoiceDetails.getInvoiceCode());

				for (InvoiceProductDetails details : productList) {
					if (details.getDiscountedPiece() == 0) {
						details.setDiscountedPiece(0);
					}
					ProductInventoryDetails inventory = inventoryDao.getProductByID(details.getProductCode());
					inventory.setTotalQuantity(inventory.getTotalQuantity().add(new BigDecimal(details.getUnitSold()))
							.add(new BigDecimal(details.getDiscountedPiece())));
					updateQuantity(inventory);
				}
			}

			id.update(invoiceDetails);
			return true;
		} catch (Exception e) {
			return false;
		} finally {
			productList.clear();
		}

	}

	public boolean deleteInvoice(InvoiceDetails invoiceDetails) {
		// TODO Auto-generated method stub
		return false;
	}

	public List<InvoiceProductDetails> addProduct(InvoiceProductDetails invoiceProductDetails) {

		if (invoiceProductDetails != null) {
			invoiceProductDetails = calTaxTotal(invoiceProductDetails);
			productList.add(invoiceProductDetails);
		}
		return productList;
	}

	private InvoiceProductDetails calTaxTotal(InvoiceProductDetails details) {

		if (details.getCaseSold() != 0) {
			ProductInventoryDetails inventory = inventoryDao.getProductByID(details.getProductCode());
			int c = details.getCaseSold();

			int productInCase = inventory.getPiecePerCase().intValue();
			long subTotal = c * productInCase;
			long totalUnit = subTotal + details.getLooseQuantity();
			details.setUnitSold(totalUnit);
		} else {
			details.setUnitSold(details.getLooseQuantity());
		}

		ProductDetails productDetails = productDao.getProduct(details.getProductCode());

		details.setTaxRate(productDetails.getTaxPecent());
		details.setUnitPrice(productDetails.getActualSellPrice());
		details.setHsnCode(productDetails.getHsnCode());
		details.setSubTotal(productDetails.getActualSellPrice().multiply(new BigDecimal(details.getUnitSold())));

		if (details.getDiscount() != null) {
			details.setDiscountAmt((details.getSubTotal().multiply(details.getDiscount())).divide(new BigDecimal(100)));
		} else {
			details.setDiscountAmt(new BigDecimal(0));
		}
		details.setSubTotal(details.getSubTotal().subtract(details.getDiscountAmt()));

		details.setSubTax(((productDetails.getTaxPecent().multiply(details.getSubTotal())).divide(new BigDecimal(100)))
				.setScale(2, BigDecimal.ROUND_HALF_UP));
		details.setSubTotal((details.getSubTotal().add(details.getSubTax())).setScale(2, BigDecimal.ROUND_HALF_UP));
		return details;

	}

	public List<InvoiceProductDetails> removeProduct(InvoiceProductDetails invoiceProductDetails) {

		if (invoiceProductDetails != null) {
			try {

				for (InvoiceProductDetails product : productList) {
					if (invoiceProductDetails.getProductCode().equals(product.getProductCode())) {
						productList.remove(product);
						break;
					}
				}

			} catch (Exception e) {

				e.printStackTrace();
			}
		}
		return productList;
	}

	public boolean updateQuantity(ProductInventoryDetails productDetails) {
		try {

			productDetails.setTotalCase(
					productDetails.getTotalQuantity().divideToIntegralValue(productDetails.getPiecePerCase()));
			productDetails
					.setLooseQuantity(productDetails.getTotalQuantity().remainder(productDetails.getPiecePerCase()));
			inventoryDao.update(productDetails);
			return true;
		} catch (Exception e) {
			return false;
		}

	}

	public List<InvoiceProductDetails> getProduct(String invoiceCode) {

		return repotdao.getProduct(id.getInvoiceById(Long.parseLong(invoiceCode)).getInvoiceCode());
	}

	public List<InvoiceProductDetails> getProductList(String invoiceCode) {

		return repotdao.getProduct(invoiceCode);
	}

	public InvoiceDetails getInvoice() {
		return null;
	}

	public List<InvoiceDetails> getActiveInvoice(Integer routeId, String customerName) {

		return id.getRouteInvoice(routeId, customerName);
	}

	public BigDecimal getInvoiceAmount() {

		BigDecimal invoiceAmount = new BigDecimal(0);
		if (!productList.isEmpty()) {
			for (InvoiceProductDetails product : productList) {
				invoiceAmount = invoiceAmount.add(product.getSubTotal());
			}
		}
		return invoiceAmount;
	}

	private void updateCounter(InvoiceCounter invoiceCounter) {
		invoiceCounter.setInvoiceCounter(invoiceCounter.getInvoiceCounter() + 1);
		if (invoiceCounter.getInvoiceDate().after(date)) {
			counter.update(invoiceCounter);
		} else {
			InvoiceCounter latestInvoiceCounter = new InvoiceCounter();
			latestInvoiceCounter.setInvoiceDate(date);
			latestInvoiceCounter.setInvoiceCounter(invoiceCounter.getInvoiceCounter());
			counter.persist(latestInvoiceCounter);
		}
	}

	private InvoiceCounter getCounter() {
		InvoiceCounter invoiceCounter;
		invoiceCounter = counter.getInvoiceCounter();
		
		if(invoiceCounter.getInvoiceCounter()==2 &&Calendar.getInstance().get(Calendar.MONTH)==4 && Calendar.getInstance().get(Calendar.DATE)== 1)
		{
			invoiceCounter.setInvoiceCounter(1);
		}

		if (Objects.isNull(invoiceCounter)) {
			invoiceCounter = new InvoiceCounter();
			invoiceCounter.setInvoiceDate(new Date());
			invoiceCounter.setInvoiceCounter(1);
		}
		return invoiceCounter;
	}

}
