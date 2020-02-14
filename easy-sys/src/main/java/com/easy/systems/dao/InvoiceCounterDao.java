package com.easy.systems.dao;

import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.springframework.stereotype.Repository;

import com.easy.systems.bean.InvoiceCounter;
@Repository
public class InvoiceCounterDao extends AbstractDao<Long, InvoiceCounter> {
	
	public InvoiceCounter getInvoiceCounter() {
		Criteria criteria = createEntityCriteria().addOrder(Order.desc("invoiceCounter"));
		criteria.setMaxResults(1);		
		return (InvoiceCounter) criteria.uniqueResult();
	}

}
