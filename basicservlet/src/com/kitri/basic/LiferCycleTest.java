package com.kitri.basic;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/life")
public class LiferCycleTest extends HttpServlet {
	private static final long serialVersionUID = 1L;

	//생성자는 한번만 호출한다.
	public LiferCycleTest() {
		System.out.println("생성자 호출!"); 
	}

	@Override
	public void init() throws ServletException {
		System.out.println("init() 호출!");
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("service()  호출!!");
	}

	//서버가 끝날 때 
	@Override
	public void destroy() {
		System.out.println("destroy() 호출!");
	}
}
