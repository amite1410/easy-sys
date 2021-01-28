package com.easy.systems.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.easy.systems.bean.InvoiceDetails;
import com.easy.systems.bean.InvoiceProductDetails;


@Repository
public class InvoiceDao extends AbstractDao<Integer, InvoiceDetails> {

	public Boolean persistInvoice(InvoiceDetails invoiceDetails, List<InvoiceProductDetails> productList) {
		return false;
		// TODO Auto-generated method stub
		
	}

	@SuppressWarnings("unchecked")
	public List<InvoiceDetails> getRouteInvoice(Integer routeId, String customerName) {
		
        Criteria criteria = createEntityCriteria().addOrder(Order.asc("invoiceCode"));
        criteria.add(Restrictions.eq("isActive", "Y"));
        
        if (!customerName.equalsIgnoreCase("NOT Selected")){
        	  criteria.add(Restrictions.eq("customerName",customerName));
        }
        criteria.add(Restrictions.eq("routeId",routeId));
        criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);//To avoid duplicates.
        List<InvoiceDetails> invoice = (List<InvoiceDetails>) criteria.list();
        return invoice;
	}
	
	public InvoiceDetails getInvoiceById(Long id){
		
		Criteria criteria = createEntityCriteria().addOrder(Order.asc("invoiceCode"));
		criteria.add(Restrictions.eq("id", id));
		criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
		 @SuppressWarnings("unchecked")
		List<InvoiceDetails> invoice = (List<InvoiceDetails>) criteria.list();
		return invoice.get(0);
		
	}
	
	
	
	

}
