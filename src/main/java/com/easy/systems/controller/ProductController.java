package com.easy.systems.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.easy.systems.bean.ProductDetails;
import com.easy.systems.service.ProductService;
import com.easy.systems.util.AppConstant;

@RestController
@RequestMapping(value = "/product")
public class ProductController implements AppConstant {

	@Autowired
	private ProductService ps;

	@RequestMapping(value = "/getAll", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<ProductDetails>> getProduct() {
		List<ProductDetails> productList = new ArrayList<>();
		productList = ps.getProduct();
		if (productList.isEmpty()) {
			return new ResponseEntity<List<ProductDetails>>(HttpStatus.NO_CONTENT);// You
																					// many
																					// decide
																					// to
																					// return
																					// HttpStatus.NOT_FOUND
		}
		return new ResponseEntity<List<ProductDetails>>(productList, HttpStatus.OK);
		// return productList;
	}

	@RequestMapping(value = "/addProduct", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> addProduct(@RequestBody ProductDetails productDetails) {
		boolean status = false;
		String message = null;
		status = ps.addProduct(productDetails);
		if (status) {
			message = PRODUCT + productDetails.getProductName() + ADD + OPERATION_SUCCESS;
			return new ResponseEntity<String>(message, HttpStatus.CREATED);
		} else {
			message = PRODUCT + productDetails.getProductName() + ADD + OPERATION_FAILURE;
			return new ResponseEntity<String>(message, HttpStatus.CONFLICT);
		}
	}

	@RequestMapping(value = "/updateProduct", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> updateProduct(@RequestBody ProductDetails productDetails) {
		boolean status = false;
		String message = null;
		status = ps.updateProduct(productDetails);
		if (status) {
			message = PRODUCT + productDetails.getProductName() + UPDATE + OPERATION_SUCCESS;
			return new ResponseEntity<String>(message, HttpStatus.ACCEPTED);
		} else {
			message = PRODUCT + productDetails.getProductName() + UPDATE + OPERATION_FAILURE;
			return new ResponseEntity<String>(message, HttpStatus.NOT_MODIFIED);
		}
	}

	@RequestMapping(value = "/deleteProduct", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> deleteProduct( @RequestBody ProductDetails productDetails) {
		boolean status = false;
		String message = null;
		status = ps.deleteProduct(productDetails);
		if (status) {
			message = PRODUCT + productDetails.getProductName() + DELETE + OPERATION_SUCCESS;
			return new ResponseEntity<String>(message, HttpStatus.OK);
		} else {
			message = PRODUCT + productDetails.getProductName() + DELETE + OPERATION_FAILURE;
			return new ResponseEntity<String>(message, HttpStatus.NOT_FOUND);
		}
	}

	@RequestMapping(value = "/getByVendor/{vendorId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<ProductDetails>> getVendorProduct(@PathVariable("vendorId") Integer vendorId) {
		List<ProductDetails> productList = new ArrayList<>();
		productList = ps.getVendorProduct(vendorId);
		if (productList.isEmpty()) {
			return new ResponseEntity<List<ProductDetails>>(productList, HttpStatus.NO_CONTENT);
		} 
			return new ResponseEntity<List<ProductDetails>>(productList, HttpStatus.OK);
	}

}
