package com.test.services.dao.data.customers;

import java.util.List;

import com.test.services.dao.data.parameters.customer.CustomerListParameters;
import com.test.services.entities.Customer;

public interface ICustomersDAO {	
	
	Customer createCustomer(Customer Customer);
	Customer getCustomer(String id);	
	Customer updateCustomer(Customer Customer);	
	boolean removeCustomer(String id);	
	List<Customer> getCustomersList(CustomerListParameters parameters);
}
