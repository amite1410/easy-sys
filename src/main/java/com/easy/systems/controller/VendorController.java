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

import com.easy.systems.bean.VendorDetails;
import com.easy.systems.service.VendorService;
import com.easy.systems.util.AppConstant;


@RestController
@RequestMapping(value = "/vendor")
public class VendorController implements AppConstant {

	@Autowired
	private VendorService vs;

	@RequestMapping(value="/getAll", method=RequestMethod.GET, produces= MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<VendorDetails>> getVendor() {
		List<VendorDetails> vendorList = new ArrayList<>();
		
		
		vendorList = vs.getVendors();
		if(vendorList.isEmpty()){
			return new ResponseEntity<List<VendorDetails>>( vendorList, HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<VendorDetails>>( vendorList, HttpStatus.OK);
	}

	@RequestMapping(value="/addVendor", method=RequestMethod.POST, produces= MediaType.APPLICATION_JSON_VALUE,consumes=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> addVendor( @RequestBody VendorDetails vendorDetails) {
		boolean status = false;
		String message = null;
		status = vs.addVendor(vendorDetails);
		if (status) {
			message = VENDOR + vendorDetails.getVendorName() + ADD + OPERATION_SUCCESS;
			return new ResponseEntity<String>(message, HttpStatus.ACCEPTED);
		} else {
			message = VENDOR + vendorDetails.getVendorName() + ADD + OPERATION_FAILURE;
			return new ResponseEntity<String>(message, HttpStatus.CONFLICT);
		}
	}
	
	@RequestMapping(value="/updateVendor", method=RequestMethod.PUT, produces= MediaType.APPLICATION_JSON_VALUE,consumes=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> updateVendor( @RequestBody VendorDetails vendorDetails) {
		boolean status = false;
		String message = null;
		status = vs.updateVendor(vendorDetails);
		if (status) {
			message = VENDOR + vendorDetails.getVendorName() + UPDATE + OPERATION_SUCCESS;
			return new ResponseEntity<String>(message, HttpStatus.ACCEPTED);
		} else {
			message = VENDOR + vendorDetails.getVendorName() + UPDATE + OPERATION_FAILURE;
			return new ResponseEntity<String>(message, HttpStatus.NOT_MODIFIED);
		}

		
	}

	@RequestMapping(value="/deleteVendor", method=RequestMethod.DELETE, produces= MediaType.APPLICATION_JSON_VALUE,consumes=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> deleteVendor( @RequestBody VendorDetails vendorDetails) {
		boolean status = false;
		String message = null;
		status = vs.deleteVendor(vendorDetails);
		if (status) {
			message = VENDOR + vendorDetails.getVendorName() + DELETE + OPERATION_SUCCESS;
			return new ResponseEntity<String>(message, HttpStatus.ACCEPTED);
		} else {
			message = VENDOR + vendorDetails.getVendorName() + DELETE + OPERATION_FAILURE;
			return new ResponseEntity<String>(message, HttpStatus.NOT_FOUND);
		}

	}
}
