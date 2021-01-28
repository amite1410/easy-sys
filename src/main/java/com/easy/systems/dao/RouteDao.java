package com.easy.systems.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.easy.systems.bean.RouteDetails;

@Repository
public class RouteDao extends AbstractDao<Integer, RouteDetails>{

	public List<RouteDetails> getRoute() {
		Criteria criteria = createEntityCriteria();
	     criteria.add(Restrictions.eq("isActive", "Y"));
	     criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);//To avoid duplicates.
	     @SuppressWarnings("unchecked")
		List<RouteDetails> routes = (List<RouteDetails>) criteria.list();
	     return routes;
	}

}
