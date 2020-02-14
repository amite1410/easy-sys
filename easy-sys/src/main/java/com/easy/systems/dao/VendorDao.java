package com.easy.systems.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.easy.systems.bean.VendorDetails;

@Repository
public class VendorDao extends AbstractDao<Integer, VendorDetails> {
	
	@SuppressWarnings("unchecked")
	public List<VendorDetails> findAllVendor() {
	
	 Criteria criteria = createEntityCriteria().addOrder(Order.asc("vendorName"));
     criteria.add(Restrictions.eq("isActive", "Y"));
     criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);//To avoid duplicates.
     List<VendorDetails> vendors = (List<VendorDetails>) criteria.list();
     return vendors;
	}
}
