package com.easy.systems.service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.easy.systems.bean.ProductInventoryDetails;
import com.easy.systems.dao.InventoryDao;

@Service
@Transactional
public class InventoryService {
	private volatile boolean status;
	@Autowired
	private InventoryDao dao;

	public boolean addProduct(ProductInventoryDetails productDetails) {

		Date d = new Date();
		try {
			productDetails.setCreateDate(d);
			productDetails.setIsActive("Y");
			productDetails.setCaseToAdd(new BigDecimal(0));
			productDetails.setTotalQuantity(calQuanity(productDetails));
			dao.persist(productDetails);
			status = true;
		} catch (Exception e) {
			e.getCause();
			status = false;
		}
		return status;
	}

	private BigDecimal calQuanity(ProductInventoryDetails productDetails) {
		BigDecimal totalQuam = productDetails.getTotalCase().multiply(productDetails.getPiecePerCase());
		totalQuam = totalQuam.add(productDetails.getLooseQuantity());
		return totalQuam;
	}

	public boolean updateProduct(ProductInventoryDetails productDetails) {
		try {
			Date d = new Date();
			productDetails.setUpdateDate(d);
			productDetails.setTotalCase(productDetails.getTotalCase().add(productDetails.getCaseToAdd()));
			productDetails.setTotalQuantity(productDetails.getTotalQuantity()
					.add(productDetails.getCaseToAdd().multiply(productDetails.getPiecePerCase())));
			dao.update(productDetails);
			return true;
		} catch (Exception e) {
			return false;
		}

	}
	

	public List<ProductInventoryDetails> getVendorProduct(Integer vendorId) {
		
		return dao.getVendorProduct(vendorId);
	}
	
public List<ProductInventoryDetails> getAvailableProduct(Integer vendorId) {
		
		return dao.getAvailableProduct(vendorId);
	}
	public ProductInventoryDetails getProductByID(String productName){
		return dao.getProductByID(productName);
		
	}

}
