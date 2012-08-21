package com.test.services.jaxb.adapter;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.xml.bind.annotation.adapters.XmlAdapter;

public class JaxBDateAdapter extends XmlAdapter<String, Date> {

	public static final String DATE_FORMAT = "yyyy-MM-dd'T'HH:mm:ssZ";																		

	public JaxBDateAdapter() {
	}

	public Date unmarshal(String s) throws Exception {
		if (s == null || s.equals(""))
			return null;
		SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT);
		return sdf.parse(s);
	}

	public String marshal(Date d) throws Exception {
		if (d == null)
			return "";

		SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT);
		return sdf.format(d);

	}
}
