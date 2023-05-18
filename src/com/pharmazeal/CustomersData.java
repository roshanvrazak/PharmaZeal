package com.pharmazeal;

import java.sql.Date;

public class CustomersData {

	private Integer customer_id;
	private String first_name;
	private String mid_name;
	private String last_name;
	private Date dob;
	private String house_no;
	private String street_name;
	private String postcode;
	private String city;
	private String county;
	private String country;
	private String allergy;

	public CustomersData(Integer customer_id, String first_name, String middle_name, String last_name, Date dob,
			String house_no, String street_name, String postcode, String city, String county, String country,
			String allergy) {
		this.customer_id = customer_id;
		this.first_name = first_name;
		this.mid_name = middle_name;
		this.last_name = last_name;
		this.dob = dob;
		this.house_no = house_no;
		this.street_name = street_name;
		this.postcode = postcode;
		this.city = city;
		this.county = county;
		this.country = country;
		this.allergy = allergy;

	}

	public Integer getCustomer_id() {
		return customer_id;
	}

//	public void setCustomer_id(Integer customer_id) {
//		this.customer_id = customer_id;
//	}

	public String getFirst_name() {
		return first_name;
	}

//	public void setFirst_name(String first_name) {
//		this.first_name = first_name;
//	}

	public String getMid_name() {
		return mid_name;
	}

//	public void setMid_name(String middle_name) {
//		this.middle_name = middle_name;
//	}

	public String getLast_name() {
		return last_name;
	}

	public void setLast_name(String last_name) {
		this.last_name = last_name;
	}

	public Date getDob() {
		return dob;
	}

	public String getHouse_no() {
		return house_no;
	}

	public String getStreet_name() {
		return street_name;
	}

	public void setStreet_name(String street_name) {
		this.street_name = street_name;
	}

	public String getPostcode() {
		return postcode;
	}

	public void setPostcode(String postcode) {
		this.postcode = postcode;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getCounty() {
		return county;
	}

	public void setCounty(String county) {
		this.county = county;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getAllergy() {
		return allergy;
	}
}