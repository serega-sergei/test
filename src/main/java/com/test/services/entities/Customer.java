package com.test.services.entities;

import java.util.Date;

import javax.xml.bind.annotation.*;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import com.test.services.jaxb.adapter.JaxBDateAdapter;

@XmlRootElement(name = "customer")
public class Customer extends Entity {
	/*
	 * DB table fields `id` varchar(45) NOT NULL, `first_name` varchar(45)
	 * DEFAULT NULL, `last_name` varchar(45) DEFAULT NULL, `phone` varchar(45)
	 * DEFAULT NULL, `mail` varchar(45) DEFAULT NULL, `adress` varchar(45)
	 * DEFAULT NULL, `contract_id` varchar(45) DEFAULT NULL,
	 * `contract_expire_date` date DEFAULT NULL
	 */

	private String first_name;
	private String last_name;
	private String phone;
	private String mail;
	private String adress;
	private String contract_id;
	private Date contract_expire_date;	

	public String getFirst_name() {
		return first_name;
	}

	public void setFirst_name(String first_name) {
		this.first_name = first_name;
	}

	public String getLast_name() {
		return last_name;
	}

	public void setLast_name(String last_name) {
		this.last_name = last_name;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	public String getAdress() {
		return adress;
	}

	public void setAdress(String adress) {
		this.adress = adress;
	}

	public String getContract_id() {
		return contract_id;
	}

	public void setContract_id(String contract_id) {
		this.contract_id = contract_id;
	}

	@XmlJavaTypeAdapter(JaxBDateAdapter.class)
	public Date getContract_expire_date() {
		return contract_expire_date;
	}

	public void setContract_expire_date(Date contract_expire_date) {
		this.contract_expire_date = contract_expire_date;
	}
}
