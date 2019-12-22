package com.model;
import java.io.Serializable;
import java.util.Set;

import javax.persistence.*;

@Entity
@Table(name="mmv")
public class MmvBean  implements Serializable{
	
@Id
@GeneratedValue
@Column(name="id")
private  int id;

@Column(name="year")
private int year;

@Column(name="make")
private  String make;

@Column(name="model")
private String model;

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
 * @return the year
 */
public int getYear() {
	return year;
}

/**
 * @param year the year to set
 */
public void setYear(int year) {
	this.year = year;
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

/* (non-Javadoc)
 * @see java.lang.Object#toString()
 */
@Override
public String toString() {
	return "MmvBean [id=" + id + ", year=" + year + ", make=" + make + ", model=" + model + "]";
}

}
