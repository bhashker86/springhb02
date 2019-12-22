package com.model;
import javax.persistence.*;

@Entity
@Table(name="car_detail")
public class CardetailDO {
	
@Id
@GeneratedValue
private int     id;


@Column(name="make_year",nullable=false)
private String  make_year;
@Column(name="make_month",nullable=true)
private String  make_month;
@Column(name="make",nullable=false)
private String  make;
@Column(name="model",nullable=false)
private String  model;
@Column(name="variant",nullable=false)
private String  variant;
@Column(name="color",nullable=true)
private String  color;
@Column(name="fuel_type",nullable=true)
private String  fuel_type;
@Column(name="number_of_owner",nullable=true)
private int     number_of_owner;
@Column(name="kilometer_drive",nullable=true)
private int     kilometer_used;
@Column(name="cust_exp_price",nullable=false)
private long    expected_price;

public int getId() {
	return id;
}

public void setId(int id) {
	this.id = id;
}



public String getMake_year() {
	return make_year;
}

public void setMake_year(String make_year) {
	this.make_year = make_year;
}

public String getMake_month() {
	return make_month;
}

public void setMake_month(String make_month) {
	this.make_month = make_month;
}

public String getMake() {
	return make;
}

public void setMake(String make) {
	this.make = make;
}

public String getModel() {
	return model;
}

public void setModel(String model) {
	this.model = model;
}

public String getVariant() {
	return variant;
}

public void setVariant(String variant) {
	this.variant = variant;
}

public String getColor() {
	return color;
}

public void setColor(String color) {
	this.color = color;
}

public String getFuel_type() {
	return fuel_type;
}

public void setFuel_type(String fuel_type) {
	this.fuel_type = fuel_type;
}

public int getNumber_of_owner() {
	return number_of_owner;
}

public void setNumber_of_owner(int number_of_owner) {
	this.number_of_owner = number_of_owner;
}

public int getKilometer_used() {
	return kilometer_used;
}

public void setKilometer_used(int kilometer_used) {
	this.kilometer_used = kilometer_used;
}

public long getExpected_price() {
	return expected_price;
}

public void setExpected_price(long expected_price) {
	this.expected_price = expected_price;
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
	CardetailDO other = (CardetailDO) obj;
	if (id != other.id)
		return false;
	return true;
}


}
