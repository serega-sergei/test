package com.test.services.customers.rest;

import java.util.List;

import com.test.services.dao.data.customers.ICustomersDAO;
import com.test.services.dao.data.parameters.customer.CustomerListParameters;
import com.test.services.entities.Customer;
import com.test.services.rest.response.ResponseCreator;
import com.test.services.data.parameters.Order;
import com.test.services.customers.rest.exceptions.Error;

import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

public class CustomersServiceJSON implements ICustomersService {

	// link to our dao object
	private ICustomersDAO customersDAO;

	// for customersDAO bean property injection
	public ICustomersDAO getCustomersDAO() {
		return customersDAO;
	}

	public void setCustomersDAO(ICustomersDAO customersDAO) {
		this.customersDAO = customersDAO;
	}

	// for retrieving request headers from context
	// an injectable interface that provides access to HTTP header information.
	@Context
	private HttpHeaders requestHeaders;

	private String getHeaderVersion() {
		return requestHeaders.getRequestHeader("version").get(0);
	}

	// get by id service
	@GET
	@Path("/{id}")
	public Response getCustomer(@PathParam("id") String id) {
		Customer customer = customersDAO.getCustomer(id);
		if (customer != null) {
			return ResponseCreator.success(getHeaderVersion(), customer);
		} else {
			return ResponseCreator.error(404, Error.NOT_FOUND.getCode(),
					getHeaderVersion());
		}
	}

	// remove row from the customers table according with passed id and returned
	// status message in body
	@DELETE
	@Path("/{id}")
	public Response removeCustomer(@PathParam("id") String id) {
		if (customersDAO.removeCustomer(id)) {
			return ResponseCreator.success(getHeaderVersion(), "removed");
		} else {
			return ResponseCreator.success(getHeaderVersion(), "no such id");
		}
	}

	// create row representing customer and returns created customer as
	// object->JSON structure
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public Response createCustomer(Customer customer) {
		System.out.println("POST");
		Customer creCustomer = customersDAO.createCustomer(customer);
		if (creCustomer != null) {
			return ResponseCreator.success(getHeaderVersion(), creCustomer);
		} else {
			return ResponseCreator.error(500, Error.SERVER_ERROR.getCode(),
					getHeaderVersion());
		}
	}

	// update row and return previous version of row representing customer as
	// object->JSON structure
	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	public Response updateCustomer(Customer customer) {
		Customer updCustomer = customersDAO.updateCustomer(customer);
		if (updCustomer != null) {
			return ResponseCreator.success(getHeaderVersion(), updCustomer);
		} else {
			return ResponseCreator.error(500, Error.SERVER_ERROR.getCode(),
					getHeaderVersion());
		}
	}

	// returns list of customers meeting query params
	@GET
	//@Produces(MediaType.APPLICATION_JSON)
	public Response getCustomers(@QueryParam("keyword") String keyword,
			@QueryParam("orderby") String orderBy,
			@QueryParam("order") String order,
			@QueryParam("pagenum") Integer pageNum,
			@QueryParam("pagesize") Integer pageSize) {
		CustomerListParameters parameters = new CustomerListParameters();
		parameters.setKeyword(keyword);
		parameters.setPageNum(pageNum);
		parameters.setPageSize(pageSize);
		parameters.setOrderBy(orderBy);
		parameters.setOrder(Order.fromString(order));
		List<Customer> listCust = customersDAO.getCustomersList(parameters);
		if (listCust != null) {
			GenericEntity<List<Customer>> entity = new GenericEntity<List<Customer>>(
					listCust) {
			};
			return ResponseCreator.success(getHeaderVersion(), entity);
		} else {
			return ResponseCreator.error(404, Error.NOT_FOUND.getCode(),
					getHeaderVersion());
		}
	}
}