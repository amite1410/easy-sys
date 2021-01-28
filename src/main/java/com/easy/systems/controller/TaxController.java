package com.easy.systems.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.easy.systems.bean.TaxDetails;
import com.easy.systems.service.TaxService;
import com.easy.systems.util.AppConstant;

@RestController
@RequestMapping(value = "/tax")
public class TaxController implements AppConstant {

	@Autowired
	private TaxService tS;

	@RequestMapping(value = "/getAll", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<TaxDetails>> getTaxes() {
		List<TaxDetails> taxList = new ArrayList<>();

		taxList = tS.getActiveTaxes();
		if (taxList.isEmpty()) {
			return new ResponseEntity<List<TaxDetails>>(taxList, HttpStatus.NO_CONTENT);
		}

		return new ResponseEntity<List<TaxDetails>>(taxList, HttpStatus.OK);
	}

	@RequestMapping(value = "/addTax", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> addTax(@RequestBody TaxDetails taxDetails) {
		boolean status = false;
		String message = null;
		System.out.println("add method"+taxDetails.getTaxName()+taxDetails.getTaxPercent());
		
		status = tS.addTax(taxDetails);
		if (status) {
			message = TAX + taxDetails.getTaxName() + ADD + OPERATION_SUCCESS;
			return new ResponseEntity<String>(message, HttpStatus.CREATED);
		} else {
			message = TAX + taxDetails.getTaxName() + ADD + OPERATION_FAILURE;
			return new ResponseEntity<String>(message, HttpStatus.CONFLICT);
		}
	}

	@RequestMapping(value = "/updateTax", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> updateTax(@RequestBody TaxDetails taxDetails) {
		boolean status = false;
		String message = null;

		status = tS.updateTax(taxDetails);
		if (status) {
			message = TAX + taxDetails.getTaxName() + UPDATE + OPERATION_SUCCESS;
			return new ResponseEntity<String>(message, HttpStatus.CREATED);
		} else {
			message = TAX + taxDetails.getTaxName() + UPDATE + OPERATION_FAILURE;
			return new ResponseEntity<String>(message, HttpStatus.NOT_MODIFIED);
		}
	}

	@RequestMapping(value = "/deleteTax", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> deleteTax(@RequestBody TaxDetails taxDetails) {
		boolean status = false;
		String message = null;

		status = tS.deleteTax(taxDetails);
		if (status) {
			message = TAX + taxDetails.getTaxName() + DELETE + OPERATION_SUCCESS;
			return new ResponseEntity<String>(message, HttpStatus.ACCEPTED);
		} else {
			message = TAX + taxDetails.getTaxName() + DELETE + OPERATION_FAILURE;
			return new ResponseEntity<String>(message, HttpStatus.NOT_FOUND);
		}
	}

}
