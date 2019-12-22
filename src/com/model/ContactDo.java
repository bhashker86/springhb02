package com.model;
import javax.persistence.*;

@Entity
@Table(name="contact_details")
public class ContactDo {

@Id
@GeneratedValue
private int id;
@Column(name="user_name",nullable=false)
private String name;
@Column(name="mobile_number",nullable=false)
private long mobile;
@Column(name="state_id",nullable=false)
private int state_id;
@Column(name="city_id",nullable=false)
private int city_id;
@Column(name="user_comments",nullable=true)
private String comment;


public int getId() {
	return id;
}

public void setId(int id) {
	this.id = id;
}

public String getName() {
	return name;
}

public void setName(String name) {
	this.name = name;
}

public long getMobile() {
	return mobile;
}

public void setMobile(long mobile) {
	this.mobile = mobile;
}

public int getState_id() {
	return state_id;
}

public void setState_id(int state_id) {
	this.state_id = state_id;
}

public int getCity_id() {
	return city_id;
}

public void setCity_id(int city_id) {
	this.city_id = city_id;
}

public String getComment() {
	return comment;
}

public void setComment(String comment) {
	this.comment = comment;
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
	ContactDo other = (ContactDo) obj;
	if (id != other.id)
		return false;
	return true;
}
/* (non-Javadoc)
 * @see java.lang.Object#toString()
 */
@Override
public String toString() {
	return "ContactDo [id=" + id + ", name=" + name + ", mobile=" + mobile + ", state_id=" + state_id + ", city_id="
			+ city_id + ", comment=" + comment + "]";
}

	

	


}
