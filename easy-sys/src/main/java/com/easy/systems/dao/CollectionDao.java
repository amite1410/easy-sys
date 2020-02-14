package com.easy.systems.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.easy.systems.bean.PaymentHistory;


@Repository
public class CollectionDao extends AbstractDao<Long, PaymentHistory>{

	@SuppressWarnings("unchecked")
	public List<PaymentHistory> getHistory(String invoiceCode) {
	       Criteria criteria = createEntityCriteria().addOrder(Order.asc("id"));
	        criteria.add(Restrictions.eq("invoiceCode",invoiceCode));
	        criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);//To avoid duplicates.
	        List<PaymentHistory> invoice = (List<PaymentHistory>) criteria.list();
	        return invoice;
		
	}

}
