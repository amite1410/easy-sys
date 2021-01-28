package com.easy.systems.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.easy.systems.bean.TaxDetails;
import com.easy.systems.dao.TaxDao;



@Service
@Transactional
public class TaxService {
	
	
	private boolean status= false;
	
	@Autowired
	private TaxDao taxDao;
	

	public List<TaxDetails> getActiveTaxes() {
		return taxDao.getAllTax();
	}

	public boolean addTax(TaxDetails taxDetails) {
		
		Date d = new Date();
		try{
			taxDetails.setIsActive("Y");
		taxDetails.setCreateDate(d);
		taxDao.persist(taxDetails);
		status=true;
		}catch(Exception e){
			e.printStackTrace();
		}
		return status;
	}

	public boolean deleteTax(TaxDetails taxDetails) {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean updateTax(TaxDetails taxDetails) {
		// TODO Auto-generated method stub
		return false;
	}

}
