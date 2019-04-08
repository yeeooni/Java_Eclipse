package com.kitri.haksa.data;

import com.kitri.haksa.service.HaksaServiceImpl;

public class HaksaDto {

	private int age;
	private String name;
	private int key;
	private String value;

	public HaksaDto(int age, String name, int key, String value) {
		super();

		this.age = age;
		this.name = name;
		this.key = key;
		this.value = value;

	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getKey() {
		return key;
	}

	public void setKey(int key) {
		this.key = key;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}
	
	
}
