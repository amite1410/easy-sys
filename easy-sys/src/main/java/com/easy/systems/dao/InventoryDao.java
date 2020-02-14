package com.easy.systems.dao;

import java.math.BigDecimal;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.easy.systems.bean.ProductInventoryDetails;

@Repository
public class InventoryDao extends AbstractDao<Integer, ProductInventoryDetails> {

	@SuppressWarnings("unchecked")
	public List<ProductInventoryDetails> getVendorProduct(Integer vendorId) {
		Criteria criteria = createEntityCriteria().addOrder(Order.asc("productName"));
		criteria.add(Restrictions.eq("vendorID", vendorId));
		criteria.add(Restrictions.eq("isActive", "Y"));
		criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
		List<ProductInventoryDetails> products = (List<ProductInventoryDetails>) criteria.list();
		return products;
	}
	
	
	
	@SuppressWarnings("unchecked")
	public List<ProductInventoryDetails> getAvailableProduct(Integer vendorId) {
		Criteria criteria = createEntityCriteria().addOrder(Order.asc("productName"));
		criteria.add(Restrictions.eq("vendorID", vendorId));
		criteria.add(Restrictions.gt("totalQuantity",new BigDecimal(0)));
		criteria.add(Restrictions.eq("isActive", "Y"));
		criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
		List<ProductInventoryDetails> products = (List<ProductInventoryDetails>) criteria.list();
		return products;
	}
	
	@SuppressWarnings("unchecked")
	public ProductInventoryDetails getProductByID(String productName) {
		Criteria criteria = createEntityCriteria().addOrder(Order.asc("productName"));
		criteria.add(Restrictions.eq("productName", productName));
		criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
		List<ProductInventoryDetails> products = (List<ProductInventoryDetails>) criteria.list();
		
		return products.get(0);
	}

}
