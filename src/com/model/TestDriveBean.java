package com.model;

import javax.persistence.Entity;
import javax.persistence.*;
import javax.persistence.Table;

@Entity
@Table(name="test_drive_request")
public class TestDriveBean {
	
@Id
@GeneratedValue
private int id;
private String cust_name;
private String cust_email;
private  long cust_mobile;
private int state_id;
private int city_id;
private String dealership;
private String make_year;
private String make;
private String model;
private String schedule_time;
/**
 * @return the id
 */
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
 * @return the cust_email
 */
public String getCust_email() {
	return cust_email;
}
/**
 * @param cust_email the cust_email to set
 */
public void setCust_email(String cust_email) {
	this.cust_email = cust_email;
}
/**
 * @return the cust_mobile
 */
public long getCust_mobile() {
	return cust_mobile;
}
/**
 * @param cust_mobile the cust_mobile to set
 */
public void setCust_mobile(long cust_mobile) {
	this.cust_mobile = cust_mobile;
}
/**
 * @return the state_id
 */
public int getState_id() {
	return state_id;
}
/**
 * @param state_id the state_id to set
 */
public void setState_id(int state_id) {
	this.state_id = state_id;
}
/**
 * @return the city_id
 */
public int getCity_id() {
	return city_id;
}
/**
 * @param city_id the city_id to set
 */
public void setCity_id(int city_id) {
	this.city_id = city_id;
}
/**
 * @return the dealership
 */
public String getDealership() {
	return dealership;
}
/**
 * @param dealership the dealership to set
 */
public void setDealership(String dealership) {
	this.dealership = dealership;
}
/**
 * @return the make_year
 */
public String getMake_year() {
	return make_year;
}
/**
 * @param make_year the make_year to set
 */
public void setMake_year(String make_year) {
	this.make_year = make_year;
}
/**
 * @return the make
 */
public String getMake() {
	return make;
}
/**
 * @param make the make to set
 */
public void setMake(String make) {
	this.make = make;
}
/* (non-Javadoc)
 * @see java.lang.Object#toString()
 */
@Override
public String toString() {
	return "TestDriveBean [id=" + id + ", cust_name=" + cust_name + ", cust_email=" + cust_email + ", cust_mobile="
			+ cust_mobile + ", state_id=" + state_id + ", city_id=" + city_id + ", dealership=" + dealership
			+ ", make_year=" + make_year + ", make=" + make + ", model=" + model + ", schedule_time=" + schedule_time
			+ "]";
}
/**
 * @return the model
 */
public String getModel() {
	return model;
}
/**
 * @param model the model to set
 */
public void setModel(String model) {
	this.model = model;
}
/**
 * @return the schedule_time
 */
public String getSchedule_time() {
	return schedule_time;
}
/**
 * @param schedule_time the schedule_time to set
 */
public void setSchedule_time(String schedule_time) {
	this.schedule_time = schedule_time;
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
	TestDriveBean other = (TestDriveBean) obj;
	if (id != other.id)
		return false;
	return true;
}





}
