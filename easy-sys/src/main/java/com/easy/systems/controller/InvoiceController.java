package com.easy.systems.controller;

import java.math.BigDecimal;
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

import com.easy.systems.bean.InvoiceDetails;
import com.easy.systems.bean.InvoiceProductDetails;
import com.easy.systems.service.InvoiceService;
import com.easy.systems.util.AppConstant;

@RestController
@RequestMapping(value = "/invoice")
public class InvoiceController implements AppConstant {

	@Autowired
	private InvoiceService vs;

	@RequestMapping(value = "/getAll/{routeId}/{customerName}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<InvoiceDetails>> getAllActiveInvoice(@PathVariable("routeId") Integer routeId,
			@PathVariable("customerName") String customerName) {
		List<InvoiceDetails> invoiceList = new ArrayList<>();

		invoiceList = vs.getActiveInvoice(routeId, customerName);
		if (invoiceList.isEmpty()) {
			return new ResponseEntity<List<InvoiceDetails>>(invoiceList, HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<InvoiceDetails>>(invoiceList, HttpStatus.OK);
	}

	@RequestMapping(value = "/get/{invoiceCode}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<InvoiceDetails> getInvoice(@PathVariable("invoiceCode") String invoiceCode) {
		InvoiceDetails invoiceDetails = new InvoiceDetails();
		invoiceDetails = vs.getInvoice();
		if (invoiceDetails != null) {
			return new ResponseEntity<InvoiceDetails>(invoiceDetails, HttpStatus.OK);
		} else {
			return new ResponseEntity<InvoiceDetails>(invoiceDetails, HttpStatus.NO_CONTENT);
		}
	}

	@RequestMapping(value = "/addInvoice", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<InvoiceDetails> addInvoice(@RequestBody InvoiceDetails invoiceDetails) {

		InvoiceDetails id = new InvoiceDetails();

		// System.out.println(invoiceDetails.getOtherDiscountPercent().toString()+"***********");
		try {
			id = vs.addInvoice(invoiceDetails);
			return new ResponseEntity<InvoiceDetails>(id, HttpStatus.ACCEPTED);
		} catch (Exception e) {
			e.getMessage();
			return new ResponseEntity<InvoiceDetails>(id, HttpStatus.CONFLICT);
		}
	}

	@RequestMapping(value = "/addProduct", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<InvoiceProductDetails>> addProduct(
			@RequestBody InvoiceProductDetails invoiceProductDetails) {

		List<InvoiceProductDetails> prodList = new ArrayList<>();
		prodList = vs.addProduct(invoiceProductDetails);
		if (!prodList.isEmpty()) {

			return new ResponseEntity<List<InvoiceProductDetails>>(prodList, HttpStatus.CREATED);
		} else {

			return new ResponseEntity<List<InvoiceProductDetails>>(prodList, HttpStatus.NO_CONTENT);
		}
	}

	@RequestMapping(value = "/removeProduct", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<InvoiceProductDetails>> removeProduct(
			@RequestBody InvoiceProductDetails invoiceProductDetails) {
		List<InvoiceProductDetails> prodList = new ArrayList<>();
		prodList = vs.removeProduct(invoiceProductDetails);
		if (!prodList.isEmpty()) {
			return new ResponseEntity<List<InvoiceProductDetails>>(prodList, HttpStatus.ACCEPTED);
		} else {

			return new ResponseEntity<List<InvoiceProductDetails>>(prodList, HttpStatus.NO_CONTENT);
		}
	}

	@RequestMapping(value = "/updateInvoice", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> updateInvoice(@RequestBody InvoiceDetails invoiceDetails) {
		
		System.out.println("Update Called");
		boolean status = false;
		String message = null;
		status = vs.updateInvoice(invoiceDetails);
		if (status) {
			if (invoiceDetails.getDeliveryStatus().equalsIgnoreCase("Cancel")) {
				message = INVOICE + invoiceDetails.getInvoiceCode() + DELETE + OPERATION_SUCCESS;
			} else {
				message = INVOICE + invoiceDetails.getInvoiceCode() + UPDATE + OPERATION_SUCCESS;
			}
			return new ResponseEntity<String>(message, HttpStatus.ACCEPTED);
		} else {
			message = INVOICE + invoiceDetails.getInvoiceCode() + UPDATE + OPERATION_FAILURE;
			return new ResponseEntity<String>(message, HttpStatus.NOT_MODIFIED);
		}

	}

	@RequestMapping(value = "/deleteInvoice", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> deleteInvoice(@RequestBody InvoiceDetails invoiceDetails) {
		boolean status = false;
		String message = null;
		status = vs.deleteInvoice(invoiceDetails);
		if (status) {
			message = INVOICE + invoiceDetails.getInvoiceCode() + DELETE + OPERATION_SUCCESS;
			return new ResponseEntity<String>(message, HttpStatus.ACCEPTED);
		} else {
			message = INVOICE + invoiceDetails.getInvoiceCode() + DELETE + OPERATION_FAILURE;
			return new ResponseEntity<String>(message, HttpStatus.NOT_FOUND);
		}

	}

	@RequestMapping(value = "/getInvoice/{invoiceCode}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<InvoiceProductDetails>> getAllProduct(@PathVariable("invoiceCode") String invoiceCode) {
		List<InvoiceProductDetails> productList = new ArrayList<>();

		productList = vs.getProduct(invoiceCode);
		if (productList.isEmpty()) {
			return new ResponseEntity<List<InvoiceProductDetails>>(productList, HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<InvoiceProductDetails>>(productList, HttpStatus.OK);
	}

	@RequestMapping(value = "/getInvoiceAmount", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<BigDecimal> getCurrentAmount() {
		BigDecimal invoiceAmount = new BigDecimal(0);

		invoiceAmount = vs.getInvoiceAmount();

		return new ResponseEntity<BigDecimal>(invoiceAmount, HttpStatus.OK);
	}

}
