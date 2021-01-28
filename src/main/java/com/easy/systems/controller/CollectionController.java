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

import com.easy.systems.bean.PaymentHistory;
import com.easy.systems.service.CollectionService;
import com.easy.systems.util.AppConstant;

@RestController
@RequestMapping(value = "/collection")
public class CollectionController implements AppConstant {
	@Autowired
	private CollectionService collService;

	@RequestMapping(value = "/addPayment", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<PaymentHistory>> addTax(@RequestBody PaymentHistory paymentHistory) {
		List<PaymentHistory> payments = new ArrayList<>();
		payments = collService.addPayment(paymentHistory);
		if (!payments.isEmpty()) {

			return new ResponseEntity<List<PaymentHistory>>(payments, HttpStatus.CREATED);
		} else {
			return new ResponseEntity<List<PaymentHistory>>(HttpStatus.CONFLICT);
		}
	}

}
