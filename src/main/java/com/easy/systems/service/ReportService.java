package com.easy.systems.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.easy.systems.bean.InvoiceProductDetails;
import com.easy.systems.bean.ProductDetails;
import com.easy.systems.dao.DailySaleReportDao;

@Service
@Transactional
public class ReportService {
	
	@Autowired
	private DailySaleReportDao saleReport; 
	@Autowired
	private ProductService productService;
	
	

	public List<InvoiceProductDetails> getSaleReport(Integer vendorId, String date) {
		List<InvoiceProductDetails> saleReportData = new CopyOnWriteArrayList<InvoiceProductDetails>();	
		try {
			saleReportData= saleReport.getDailySaleReport(date);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return filterReport(vendorId,saleReportData);
	}
	
	
	private List<InvoiceProductDetails> filterReport(Integer vendorId,List<InvoiceProductDetails> saleReport){
		
		int  caseQuantity=0;
		int caseSold=0;
		List<InvoiceProductDetails> saleReportData = new CopyOnWriteArrayList<InvoiceProductDetails>();	
		List<ProductDetails> productList= new CopyOnWriteArrayList<ProductDetails>();
		productList=productService.getVendorProduct(vendorId);
		
		for(InvoiceProductDetails product: saleReport){
			
			
			
				
				for(ProductDetails productDetails: productList){
					if(productDetails.getProductCode().equalsIgnoreCase(product.getProductCode())){
						
						caseQuantity=productDetails.getPiecePerCase().intValue();
						caseSold=(int)(product.getUnitSold()/caseQuantity);
						
						product.setUnitSold(product.getUnitSold()-(caseQuantity*caseSold));
						product.setCaseSold(caseSold);
						saleReportData.add(product);
						break;
					}
				}
				
				
				
				
			
			
		}
		
		
		
		return  saleReportData;
	}

}
