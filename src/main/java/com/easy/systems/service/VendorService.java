package com.easy.systems.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.easy.systems.bean.VendorDetails;
import com.easy.systems.dao.VendorDao;
@Service
@Transactional
public class VendorService {
	
	private volatile boolean status;
	
	@Autowired
	private VendorDao dao;

	public List<VendorDetails> getVendors() {
		return dao.findAllVendor() ;
	}

	public boolean addVendor(VendorDetails vendorDetails) {
		
		Date d = new Date();
		try {
			vendorDetails.setIsActive("Y");
			vendorDetails.setCreateDate(d);
			dao.persist(vendorDetails);
			status = true;
		} catch (Exception e) {
			e.getCause();
			status = false;
		}
		return status;
	}

	public boolean updateVendor(VendorDetails vendorDetails) {
		try {
			dao.update(vendorDetails);
			status = true;
		} catch (Exception e) {
			e.getCause();
			status = false;
		}
		return status;
	}

	public boolean deleteVendor(VendorDetails vendorDetails) {
		try {
			dao.delete(vendorDetails);
			status = true;
		} catch (Exception e) {
			e.getCause();
			status = false;
		}
		return status;
	}

}
