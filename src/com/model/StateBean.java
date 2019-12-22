package com.model;

import java.io.Serializable;
import javax.persistence.*;

@Entity
@Table(name="master_states")
public class StateBean implements Serializable{

	
	@Id
	@Column(name="state_id")
	private int state_id;
	@Column(name="state_name")
	private String state_name;
	
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
	 * @return the state_name
	 */
	public String getState_name() {
		return state_name;
	}
	/**
	 * @param state_name the state_name to set
	 */
	public void setState_name(String state_name) {
		this.state_name = state_name;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "StateBean [state_id=" + state_id + ", state_name=" + state_name + "]";
	}

}
