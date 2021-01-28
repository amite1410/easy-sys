package com.easy.systems.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.easy.systems.bean.RouteDetails;
import com.easy.systems.dao.RouteDao;

@Service
@Transactional
public class RouteService {
	
	@Autowired
	private RouteDao routeDao;

	public List<RouteDetails> getRoutes() {
		return routeDao.getRoute();
	}

	public boolean addRoute(RouteDetails routetDetails) {
		
		try{
			Date d = new Date();
			
			routetDetails.setCreateDate(d);
			routetDetails.setIsActive("Y");;
		routeDao.persist(routetDetails);
		}catch(Exception e){
			e.getMessage();
			return false;
		}
		return true;
	}
	

}
