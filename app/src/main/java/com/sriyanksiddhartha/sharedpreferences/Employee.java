package com.sriyanksiddhartha.sharedpreferences;

import java.util.List;

public class Employee {

	private String name;
	private Integer profId;
	private String profession;
	private List<String> roles;
	// private boolean ..
	// private float ..
	// private double ..

	public Integer getProfId() {
		return profId;
	}

	public void setProfId(Integer profId) {
		this.profId = profId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getProfession() {
		return profession;
	}

	public void setProfession(String profession) {
		this.profession = profession;
	}

	public List<String> getRoles() {
		return roles;
	}

	public void setRoles(List<String> roles) {
		this.roles = roles;
	}
}
