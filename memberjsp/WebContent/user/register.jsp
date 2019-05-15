<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import ="java.sql.*, java.net.URLEncoder" %>
    
    <%!
	    public void init() {
			try {
				Class.forName("oracle.jdbc.driver.OracleDriver");
				System.out.println("드라이버 로딩 성공!");
			} catch (ClassNotFoundException e) {
	
				e.printStackTrace();
			}
		}
    %>
    
    <%
  		
    	request.setCharacterEncoding("utf-8");
    	Connection con = null;
	 	PreparedStatement pstmt = null;
	    
	 	String name = request.getParameter("name");
		String id = request.getParameter("id");
		String pass = request.getParameter("pass");
		String emailid = request.getParameter("emailid");
		String emaildomain = request.getParameter("emaildomain");
		String tel1 = request.getParameter("tel1");
		String tel2 = request.getParameter("tel2");
		String tel3 = request.getParameter("tel3");
		String zipcode = request.getParameter("zipcode");
		String address = request.getParameter("address");
		String addressdetail = request.getParameter("address_detail");
    
	int cnt = 0;
	 try {
		con = DriverManager.getConnection("jdbc:oracle:thin:@192.168.14.52:1521:orcl", "kitri", "kitri");

		StringBuffer sql = new StringBuffer();

		sql.append("insert all \n");
		sql.append("	into member (name, id, pass, emailid, emaildomain, joindate)\n");
		sql.append("	values(?, ?, ?, ?, ?, sysdate)\n");
		sql.append("	into member_detail (id, zipcode, address, address_detail, tel1, tel2, tel3)\n");
		sql.append("	values(?, ?, ?, ?, ?, ?, ?) \n");
		sql.append("select * from dual \n");
		
		pstmt = con.prepareStatement(sql.toString());

		int idx = 0;
		pstmt.setString(++idx, name);
		pstmt.setString(++idx, id);
		pstmt.setString(++idx, pass);
		pstmt.setString(++idx, emailid);
		pstmt.setString(++idx, emaildomain);
		pstmt.setString(++idx, id);
		pstmt.setString(++idx, zipcode);
		pstmt.setString(++idx, address);
		pstmt.setString(++idx, addressdetail);
		pstmt.setString(++idx, tel1);
		pstmt.setString(++idx, tel2);
		pstmt.setString(++idx, tel3);

		cnt = pstmt.executeUpdate();
				
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				try {
					if (pstmt != null)
						pstmt.close();
					if (con != null)
						con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
		}
	String root = request.getContextPath();
		if(cnt != 0) { 
				response.sendRedirect(root + "/user/registerok.jsp?name=" + URLEncoder.encode(name, "utf-8"));
 			} else {
 				response.sendRedirect(root + "/user/registerfail.jsp");
		}
	%>
	