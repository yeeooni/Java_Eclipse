package com.kitri.dto;

public class ProductCategory {//부모엔티티
	
	// 인스턴스 변수 자료형 char (x) String Type 으로..
	private String cate_no;
	private String cate_name;

	// 생성자 
	public ProductCategory(String cate_no, String cate_name) {
		super();
		this.cate_no = cate_no;
		this.cate_name = cate_name;
	}
	public String getCate_no() {
		return cate_no;
	}

	public void setCate_no(String cate_no) {
		this.cate_no = cate_no;
	}

	public String getCate_name() {
		return cate_name;
	}

	public void setCate_name(String cate_name) {
		this.cate_name = cate_name;
	}

}
