package com.easy.systems.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.easy.systems.bean.TaxDetails;


@Repository
public class TaxDao extends AbstractDao<Integer, TaxDetails>{
	
	
	
	
	@SuppressWarnings("unchecked")
	public List<TaxDetails> getAllTax() {
        Criteria criteria = createEntityCriteria().addOrder(Order.asc("taxName"));
        criteria.add(Restrictions.eq("isActive", "Y"));
        criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);//To avoid duplicates.
        List<TaxDetails> taxes = (List<TaxDetails>) criteria.list();
        return taxes;
    }

}
