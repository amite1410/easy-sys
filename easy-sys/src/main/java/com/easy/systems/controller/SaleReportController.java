package com.easy.systems.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.easy.systems.bean.InvoiceProductDetails;
import com.easy.systems.service.ReportService;

@RestController
@RequestMapping(value = "/report")
public class SaleReportController {
	
	@Autowired
	private ReportService reportService;
	
	@RequestMapping(value = "/getAll", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<InvoiceProductDetails>> getAllActiveInvoice() {
		List<InvoiceProductDetails> invoiceList = new ArrayList<>();

		//invoiceList = reportService.getSaleReport();
		if (invoiceList.isEmpty()) {
			return new ResponseEntity<List<InvoiceProductDetails>>(invoiceList, HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<InvoiceProductDetails>>(invoiceList, HttpStatus.OK);
	}
	@RequestMapping(value = "/getAll/{date}/{vendorId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<InvoiceProductDetails>> getCustomReport(@PathVariable("vendorId") Integer vendorId, @PathVariable("date") String date) {
		List<InvoiceProductDetails> invoiceList = new ArrayList<>();

		invoiceList = reportService.getSaleReport(vendorId,date);
		if (invoiceList.isEmpty()) {
			return new ResponseEntity<List<InvoiceProductDetails>>(invoiceList, HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<InvoiceProductDetails>>(invoiceList, HttpStatus.OK);
	}

}
