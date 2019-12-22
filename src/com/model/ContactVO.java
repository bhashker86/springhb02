package com.model;

import java.io.Serializable;

public class ContactVO implements Serializable {


private static final long serialVersionUID = 1L;
private int id;
private String city_id;
private String pincode;
private  String cust_name;
private  String mobile;
private String  email;


public int getId() {
	return id;
}
/**
 * @param id the id to set
 */
public void setId(int id) {
	this.id = id;
}
/**
 * @return the city_id
 */
public String getCity_id() {
	return city_id;
}
/**
 * @param city_id the city_id to set
 */
public void setCity_id(String city_id) {
	this.city_id = city_id;
}
/**
 * @return the pincode
 */
public String getPincode() {
	return pincode;
}
/**
 * @param pincode the pincode to set
 */
public void setPincode(String pincode) {
	this.pincode = pincode;
}
/**
 * @return the cust_name
 */
public String getCust_name() {
	return cust_name;
}
/**
 * @param cust_name the cust_name to set
 */
public void setCust_name(String cust_name) {
	this.cust_name = cust_name;
}
/**
 * @return the mobile
 */
public String getMobile() {
	return mobile;
}
/**
 * @param mobile the mobile to set
 */
public void setMobile(String mobile) {
	this.mobile = mobile;
}
/**
 * @return the email
 */
public String getEmail() {
	return email;
}
/**
 * @param email the email to set
 */
public void setEmail(String email) {
	this.email = email;
}
/* (non-Javadoc)
 * @see java.lang.Object#toString()
 */
@Override
public String toString() {
	return "ContactVO [id=" + id + ", city_id=" + city_id + ", pincode=" + pincode + ", cust_name=" + cust_name
			+ ", mobile=" + mobile + ", email=" + email + "]";
}
/* (non-Javadoc)
 * @see java.lang.Object#hashCode()
 */
@Override
public int hashCode() {
	final int prime = 31;
	int result = 1;
	result = prime * result + id;
	return result;
}
/* (non-Javadoc)
 * @see java.lang.Object#equals(java.lang.Object)
 */
@Override
public boolean equals(Object obj) {
	if (this == obj)
		return true;
	if (obj == null)
		return false;
	if (getClass() != obj.getClass())
		return false;
	ContactVO other = (ContactVO) obj;
	if (id != other.id)
		return false;
	return true;
}

	
}
