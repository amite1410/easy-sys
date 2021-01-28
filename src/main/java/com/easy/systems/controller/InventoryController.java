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

import com.easy.systems.bean.ProductInventoryDetails;
import com.easy.systems.service.InventoryService;
import com.easy.systems.util.AppConstant;

@RestController
@RequestMapping(value = "/inventory")
public class InventoryController implements AppConstant {

	@Autowired
	private InventoryService inventoryService;

	@RequestMapping(value = "/addProduct", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> addProduct(@RequestBody ProductInventoryDetails productDetails) {
		boolean status = false;
		String message = null;
		status = inventoryService.addProduct(productDetails);
		if (status) {
			message = PRODUCT + productDetails.getProductName() + ADD + OPERATION_SUCCESS;
			return new ResponseEntity<String>(message, HttpStatus.CREATED);
		} else {
			message = PRODUCT + productDetails.getProductName() + ADD + OPERATION_FAILURE;
			return new ResponseEntity<String>(message, HttpStatus.CONFLICT);
		}
	}

	@RequestMapping(value = "/updateProduct", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> updateProduct(@RequestBody ProductInventoryDetails productDetails) {
		boolean status = false;
		String message = null;
		status = inventoryService.updateProduct(productDetails);
		if (status) {
			message = PRODUCT + productDetails.getProductName() + UPDATE + OPERATION_SUCCESS;
			return new ResponseEntity<String>(message, HttpStatus.ACCEPTED);
		} else {
			message = PRODUCT + productDetails.getProductName() + UPDATE + OPERATION_FAILURE;
			return new ResponseEntity<String>(message, HttpStatus.NOT_MODIFIED);
		}
	}
	@RequestMapping(value = "/getByVendor/{vendorId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<ProductInventoryDetails>> getVendorProduct(@PathVariable("vendorId") Integer vendorId) {
		List<ProductInventoryDetails> productList = new ArrayList<>();
		System.out.println("getby vendor&&&&&&&&&&&&&&&&&&&&&&");
		productList = inventoryService.getVendorProduct(vendorId);
		
		if (productList.isEmpty()) {
			return new ResponseEntity<List<ProductInventoryDetails>>(productList, HttpStatus.NO_CONTENT);
		} 
			return new ResponseEntity<List<ProductInventoryDetails>>(productList, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/getProductInventory/{vendorId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<ProductInventoryDetails>> getProductInventory(@PathVariable("vendorId") Integer vendorId) {
		List<ProductInventoryDetails> productList = new ArrayList<>();
		productList = inventoryService.getAvailableProduct(vendorId);
		if (productList.isEmpty()) {
			return new ResponseEntity<List<ProductInventoryDetails>>(productList, HttpStatus.NO_CONTENT);
		} 
			return new ResponseEntity<List<ProductInventoryDetails>>(productList, HttpStatus.OK);
	}
	

}
