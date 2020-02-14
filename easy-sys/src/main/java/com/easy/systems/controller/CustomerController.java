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

import com.easy.systems.bean.CustomerDetails;

import com.easy.systems.service.CustomerService;
import com.easy.systems.util.AppConstant;;

@RestController
@RequestMapping(value = "/customer")
public class CustomerController implements AppConstant {

	@Autowired
	private CustomerService cS;

	@RequestMapping(value = "/getAll/{routeId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<CustomerDetails>> getCustomer(@PathVariable("routeId") Integer routeNo) {
		List<CustomerDetails> customerList = new ArrayList<>();
		customerList = cS.getCustomers(routeNo);
		if (customerList.isEmpty()) {
			return new ResponseEntity<List<CustomerDetails>>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<CustomerDetails>>(customerList, HttpStatus.OK);
	}

	@RequestMapping(value = "/addCustomer", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> addCustomer(@RequestBody CustomerDetails customerDetails) {
		boolean status = false;
		String message = null;
		status = cS.addCustomer(customerDetails);
		if (status) {
			message = CONSUMER + customerDetails.getCustomerName() + ADD + OPERATION_SUCCESS;
			return new ResponseEntity<String>(message, HttpStatus.CREATED);
		} else {
			message = CONSUMER + customerDetails.getCustomerName() + ADD + OPERATION_FAILURE;
			return new ResponseEntity<String>(message, HttpStatus.CONFLICT);
		}
	}

	
	@RequestMapping(value = "/updateCustomer", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> updateCustomer(@RequestBody CustomerDetails customerDetails) {
		boolean status = false;
		String message = null;
		status = cS.updateCustomer(customerDetails);
		if (status) {
			message = CONSUMER + customerDetails.getCustomerName() + UPDATE + OPERATION_SUCCESS;
			return new ResponseEntity<String>(message, HttpStatus.CREATED);
		} else {
			message = CONSUMER + customerDetails.getCustomerName() + UPDATE + OPERATION_FAILURE;
			return new ResponseEntity<String>(message, HttpStatus.NOT_MODIFIED);
		}
	}
	@RequestMapping(value = "/deleteCustomer", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> deleteCustomer(@RequestBody CustomerDetails customerDetails) {
		boolean status = false;
		String message = null;
		status = cS.deleteCustomer(customerDetails);
		if (status) {
			message = CONSUMER + customerDetails.getCustomerName() + DELETE + OPERATION_SUCCESS;
			return new ResponseEntity<String>(message, HttpStatus.CREATED);
		} else {
			message = CONSUMER + customerDetails.getCustomerName() + DELETE + OPERATION_FAILURE;
			return new ResponseEntity<String>(message, HttpStatus.NOT_MODIFIED);
		}
	}
}
