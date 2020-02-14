package com.easy.systems.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.easy.systems.bean.RouteDetails;
import com.easy.systems.service.RouteService;
import com.easy.systems.util.AppConstant;

@RestController
@RequestMapping("/route")
public class RouteController implements AppConstant {

	@Autowired
	private RouteService routeService;

	@RequestMapping(value = "/getAll", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<RouteDetails>> getRoute() {
		List<RouteDetails> routeList = new ArrayList<>();
		routeList = routeService.getRoutes();
		if (routeList.isEmpty()) {
			return new ResponseEntity<List<RouteDetails>>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<RouteDetails>>(routeList, HttpStatus.OK);
	}

	@RequestMapping(value = "/addRoute", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> addRoute(@RequestBody RouteDetails routetDetails) {
		boolean status = false;
		String message = null;
		status = routeService.addRoute(routetDetails);
		if (status) {
			message = ROUTE + routetDetails.getId() + ADD + OPERATION_SUCCESS;
			return new ResponseEntity<String>(message, HttpStatus.CREATED);
		} else {
			message = ROUTE + routetDetails.getId() + ADD + OPERATION_FAILURE;
			return new ResponseEntity<String>(message, HttpStatus.CONFLICT);
		}
	}

}
