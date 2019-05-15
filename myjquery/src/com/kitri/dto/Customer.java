package com.kitri.dto;

public class Customer {

	private String id;
	private String pass;
	private String name;

	public Customer() {
		
	}
	
	public Customer(String id, String pass, String name) {
		super();
		this.id = id;
		this.pass = pass;
		this.name = name;
	}

	@Override
	public String toString() {
		return "Customer [id=" + id + ", pass=" + pass + ", name=" + name + "]";
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPass() {
		return pass;
	}

	public void setPass(String pass) {
		this.pass = pass;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
