package com.model;

import java.io.Serializable;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

public class TestdriveVO implements Serializable {
	
	
	private int id;
	
	@NotNull(message="Name is required")
	private String name;
	@NotNull(message="Email is required")
	@Email(message="Email is invalid")
	private String email;
	@NotNull(message="Mobile is required")
	private String  mobile;
	//@Size(min=1,message="State  is required")
	@NotNull(message="State is required")
	private int state_id;
	//@Size(min=1,message="City is required")
	@NotNull(message="City is required")
	private int city_id;
	@NotNull(message="Preferred dealership is required")
	private String dealership;
	@NotNull(message="Make year is required")
	private String make_year;
	@NotNull(message="Make is required")
	private String make;
	@NotNull(message="Model is required")
	private String  model;
	@NotNull(message="Scheduled time is required")
	private String scedule_time;
	
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
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
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
	 * @return the scedule_time
	 */
	public String getScedule_time() {
		return scedule_time;
	}
	/**
	 * @param scedule_time the scedule_time to set
	 */
	public void setScedule_time(String scedule_time) {
		this.scedule_time = scedule_time;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "TestdriveVO [id=" + id + ", name=" + name + ", email=" + email + ", mobile=" + mobile + ", state_id="
				+ state_id + ", city_id=" + city_id + ", dealership=" + dealership + ", make_year=" + make_year
				+ ", make=" + make + ", model=" + model + ", scedule_time=" + scedule_time + "]";
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
		TestdriveVO other = (TestdriveVO) obj;
		if (id != other.id)
			return false;
		return true;
	}
	

}
