package com.easy.systems.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.easy.systems.bean.CustomerDetails;

@Repository
public class CustomerDao extends AbstractDao<Integer, CustomerDetails>{

	@SuppressWarnings("unchecked")
	public List<CustomerDetails> getCustomers(Integer routeNo) {
		Criteria criteria = createEntityCriteria().addOrder(Order.asc("firmName"));
		criteria.add(Restrictions.eq("route_No", routeNo));
		criteria.add(Restrictions.eq("isActive", "Y"));
        criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
		List<CustomerDetails> customers = (List<CustomerDetails>) criteria.list();
        return customers;
	}

}
