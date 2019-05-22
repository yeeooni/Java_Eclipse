package com.kitri.dto;

public class OrderLine {
	private Product product;
	private int order_no;
	private String order_prod_no;
	private int order_quantity;

	public OrderLine() {

	}

	public OrderLine(Product product, int order_no, String order_prod_no, int order_quantity) {
		super();
		this.product = product;
		this.order_no = order_no;
		this.order_prod_no = order_prod_no;
		this.order_quantity = order_quantity;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public int getOrder_no() {
		return order_no;
	}

	public void setOrder_no(int order_no) {
		this.order_no = order_no;
	}

	public String getOrder_prod_no() {
		return order_prod_no;
	}

	public void setOrder_prod_no(String order_prod_no) {
		this.order_prod_no = order_prod_no;
	}

	public int getOrder_quantity() {
		return order_quantity;
	}

	public void setOrder_quantity(int order_quantity) {
		this.order_quantity = order_quantity;
	}

}
