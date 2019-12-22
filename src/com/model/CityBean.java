package com.model;

import java.io.Serializable;
import java.util.Set;
import javax.persistence.*;

@Entity
@Table(name="master_cities")
public class CityBean implements Serializable{

@Id
@GeneratedValue
@Column(name="city_id")
private int city_id;

@Column(name="city_name")
private String city_name;

@Column(name="state_id")
private int state_id;


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
 * @return the city_name
 */
public String getCity_name() {
	return city_name;
}

/**
 * @param city_name the city_name to set
 */
public void setCity_name(String city_name) {
	this.city_name = city_name;
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

/* (non-Javadoc)
 * @see java.lang.Object#toString()
 */
@Override
public String toString() {
	return "CityBean [city_id=" + city_id + ", city_name=" + city_name + "]";
}

}
