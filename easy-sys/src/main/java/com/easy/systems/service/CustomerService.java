package com.easy.systems.service;

import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.easy.systems.bean.CustomerDetails;
import com.easy.systems.dao.CustomerDao;

@Service
@Transactional
public class CustomerService {

	private volatile boolean status = false;
	@Autowired
	private CustomerDao customerDao;

	public List<CustomerDetails> getCustomers(Integer routeNo) {

		return customerDao.getCustomers(routeNo);
	}

	public boolean deleteCustomer(CustomerDetails customerDetails) {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean updateCustomer(CustomerDetails customerDetails) {
		try {
			customerDao.update(customerDetails);
			return true;
		} catch (Exception e) {
			return false;
		}

	}

	public boolean addCustomer(CustomerDetails CustomerDetails) {

		Date d = new Date();
		try {
			CustomerDetails.setCreateDate(d);
			CustomerDetails.setIsActive("Y");

			customerDao.persist(CustomerDetails);
			status = true;
		} catch (Exception e) {
			e.getCause();
			status = false;
		}
		return status;
	}

}
