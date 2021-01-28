package com.easy.systems.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.easy.systems.bean.PaymentHistory;
import com.easy.systems.dao.CollectionDao;

@Service
@Transactional
public class CollectionService {

	@Autowired
	private CollectionDao collDao;

	public List<PaymentHistory> addPayment(PaymentHistory paymentHistory) {
		try {
			Date d= new Date();
			
			paymentHistory.setPaymentDate(d);
			paymentHistory.setCollectedBy("Admin");
			collDao.persist(paymentHistory);
			return collDao.getHistory(paymentHistory.getInvoiceCode());

		} catch (Exception e) {
			return null;
		}
	}

}
