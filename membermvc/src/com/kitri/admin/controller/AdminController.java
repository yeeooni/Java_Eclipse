package com.kitri.admin.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kitri.admin.model.service.AdminServiceImpl;
import com.kitri.util.MoveUrl;
import com.kitri.util.SiteConstance;

@WebServlet("/admin")
public class AdminController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String act = request.getParameter("act");
		String path = "/index.jsp";
		
		
		if("memberlist".equals(act)) {
			path = "/admin/member/memberlist.jsp";
			MoveUrl.redirect(request, response, path);
		} else if("getmemberlist".equals(act)) {
			System.out.println("여기까지 옴");
			String key = request.getParameter("key");
			String word = request.getParameter("word");
			
			String resultXML = AdminServiceImpl.getAdminService().getMemberList(key, word);
			response.setContentType("text/xml; charset = utf-8");
	 		PrintWriter out = response.getWriter();
	 		System.out.println(resultXML);
	 		out.print(resultXML);
	 		
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding(SiteConstance.ENCODE);
		doGet(request, response);
	}

}
