package com.model;
import javax.persistence.*;


@Entity
@Table(name="servicebooking")
public class ServiceBookingDo {

@Id
@GeneratedValue
private int  id;
@Column(name="cust_name",nullable=false)
private String customer_name;
@Column(name="cust_email",nullable=false)
private String email;
@Column(name="cust_mobile",nullable=false)
private long mobile_number;
@Column(name="state_id",nullable=false)
private int state_id;
@Column(name="city_id",nullable=false)
private int city_id;
@Column(name="make",nullable=false)
private String make;
@Column(name="modle",nullable=false)
private String model;
@Column(name="door_step_service")
private boolean dts;


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
 * @return the customer_name
 */
public String getCustomer_name() {
	return customer_name;
}
/**
 * @param customer_name the customer_name to set
 */
public void setCustomer_name(String customer_name) {
	this.customer_name = customer_name;
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
/**
 * @return the mobile_number
 */
public long getMobile_number() {
	return mobile_number;
}
/**
 * @param mobile_number the mobile_number to set
 */
public void setMobile_number(long mobile_number) {
	this.mobile_number = mobile_number;
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
 * @return the dts
 */
public boolean isDts() {
	return dts;
}
/**
 * @param dts the dts to set
 */
public void setDts(boolean dts) {
	this.dts = dts;
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
	ServiceBookingDo other = (ServiceBookingDo) obj;
	if (id != other.id)
		return false;
	return true;
}
/* (non-Javadoc)
 * @see java.lang.Object#toString()
 */
@Override
public String toString() {
	return "ServiceBookingDao [id=" + id + ", customer_name=" + customer_name + ", email=" + email + ", mobile_number="
			+ mobile_number + ", state_id=" + state_id + ", city_id=" + city_id + ", make=" + make + ", model=" + model
			+ "]";
}


}
