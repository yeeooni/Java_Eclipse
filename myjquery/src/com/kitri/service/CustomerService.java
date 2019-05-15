package com.kitri.service;

import java.sql.SQLException;

import com.kitri.dao.CustomerDao;
import com.kitri.dto.Customer;
import com.kitri.exception.NotFoundException;

public class CustomerService {

	public CustomerService() {
		
	}
	
	public String login(String id, String pass){
	
		String result = "";
		
		try {
			Customer customer  = new CustomerDao().selectById(id);
			
			if(pass.equals(customer.getPass())) {
				result = "1";
			} else {
				result = "-1";
			}
			
		} catch (NotFoundException | SQLException e) {
			result = "회원검색 실패";
			e.printStackTrace();
		}
		return result;
	}
	
	
}
