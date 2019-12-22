package com.model;

import java.io.Serializable;

public class CardetailVO implements Serializable {
	

private static final long serialVersionUID = 1L;
private  int    id;
private String  make_year;
private String  make_month;
private String  make;
private String  model;
private String  variant;
private String  color;
private String  fuel_type;
private  String number_of_ownor;
private String  kilometer_used;
private String  expected_price;


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

public String getnumber_of_ownor() {
	return number_of_ownor;
}

public void setnumber_of_ownor(String number_of_ownor) {
	this.number_of_ownor = number_of_ownor;
}

public String getKilometer_used() {
	return kilometer_used;
}

public void setKilometer_used(String kilometer_used) {
	this.kilometer_used = kilometer_used;
}

public String getExpected_price() {
	return expected_price;
}

public void setExpected_price(String expected_price) {
	this.expected_price = expected_price;
}
/* (non-Javadoc)
 * @see java.lang.Object#toString()
 */
@Override
public String toString() {
	return "CardetailVO [id=" + id + ", make_year=" + make_year + ", make_month=" + make_month + ", make=" + make
			+ ", model=" + model + ", variant=" + variant + ", color=" + color + ", fuel_type=" + fuel_type
			+ ", number_of_honor=" + number_of_ownor + ", kilometer_used=" + kilometer_used + ", expected_price="
			+ expected_price + "]";
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
	CardetailVO other = (CardetailVO) obj;
	if (id != other.id)
		return false;
	return true;
}


}
