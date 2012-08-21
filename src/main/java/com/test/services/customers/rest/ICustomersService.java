package com.test.services.customers.rest;

import javax.ws.rs.core.Response;

import com.test.services.entities.Customer;

public interface ICustomersService {

	Response getCustomer(String id);	
	Response removeCustomer(String id);
	Response createCustomer(Customer str);
	Response updateCustomer(Customer str);
	Response getCustomers(String keyword, String orderBy, String order, Integer pageNum, Integer pageSize);
}
