package com.easy.systems.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.easy.systems.bean.ProductDetails;
import com.easy.systems.dao.ProductDao;

@Service("productService")
@Transactional
public class ProductService {

	private volatile boolean status;

	@Autowired
	private ProductDao dao;

	public List<ProductDetails> getProduct() {
		System.out.println("Some Thing Happend");
		return dao.findAllProduct();
	}

	public boolean addProduct(ProductDetails productDetails) {

		Date d = new Date();
		try {
			productDetails.setCreateDate(d);
			productDetails.setIsActive("Y");
			productDetails.setActualSellPrice( productDetails.getSellPrice());
			dao.persist(productDetails);
			status = true;
		} catch (Exception e) {
			e.getCause();
			status = false;
		}
		return status;
	}

	public boolean updateProduct(ProductDetails productDetails) {
		try {
			
			productDetails.setActualSellPrice( productDetails.getSellPrice());
			dao.update(productDetails);
			status = true;
		} catch (Exception e) {
			e.getCause();
			status = false;
		}
		return status;
	}

	public boolean deleteProduct(ProductDetails productDetails) {
		try {
			dao.delete(productDetails);
			status = true;
		} catch (Exception e) {
			e.getCause();
			status = false;
		}
		return status;
	}

	public List<ProductDetails> getVendorProduct(Integer vendorId) {

		List<ProductDetails> proDetails = new ArrayList<>();

		proDetails = dao.getVendorProduct(vendorId);

		return proDetails;
	}


}
