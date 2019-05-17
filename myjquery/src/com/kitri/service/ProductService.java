package com.kitri.service;

import java.util.List;


import com.kitri.dao.ProductDao;
import com.kitri.dto.Product;

public class ProductService {

	private ProductDao dao;
	
	public ProductService() {
		dao = new ProductDao();
	}
	
	public List<Product> findAll(){
		return dao.selectAll();
	}

	public Product findByNo(String prodNo) {
		return dao.selectByNo(prodNo);
	}
	
}
