package com.easy.systems.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.easy.systems.bean.ProductDetails;


@Repository
public class ProductDao  extends AbstractDao<Integer, ProductDetails>{

	
	@SuppressWarnings("unchecked")
	public List<ProductDetails> findAllProduct() {
        Criteria criteria = createEntityCriteria().addOrder(Order.asc("productName"));
        criteria.add(Restrictions.eq("isActive", "Y"));
        criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);//To avoid duplicates.
        List<ProductDetails> products = (List<ProductDetails>) criteria.list();
        return products;
    }
	
	@SuppressWarnings("unchecked")
	public List<ProductDetails> findProduct(Integer i) {
        Criteria criteria = createEntityCriteria().addOrder(Order.asc("productName"));
        criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);//To avoid duplicates.
        List<ProductDetails> products = (List<ProductDetails>) criteria.list();
        return products;
    }

	 @SuppressWarnings("unchecked")
	public List<ProductDetails> getVendorProduct(Integer vendorId) {
		Criteria criteria = createEntityCriteria().addOrder(Order.asc("productName"));
		criteria.add(Restrictions.eq("vendorID", vendorId));
		criteria.add(Restrictions.eq("isActive", "Y"));
        criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
		List<ProductDetails> products = (List<ProductDetails>) criteria.list();
        return products;
	}
	 @SuppressWarnings("unchecked")
		public ProductDetails getProduct(String product) {
			Criteria criteria = createEntityCriteria().addOrder(Order.asc("productName"));
			criteria.add(Restrictions.eq("productCode", product));
	        criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
			List<ProductDetails> products = (List<ProductDetails>) criteria.list();
	        return products.get(0);
		}
	
}
