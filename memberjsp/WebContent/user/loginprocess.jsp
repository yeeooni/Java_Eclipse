<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import = "java.sql.*, java.net.URLEncoder"%>
    
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
    	Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		String name = null;
		
		try {
			request.setCharacterEncoding("utf-8");
			String id = request.getParameter("id");
			String pass = request.getParameter("pass");
			con = DriverManager.getConnection("jdbc:oracle:thin:@192.168.14.52:orcl", "kitri", "kitri");
			StringBuffer sql = new StringBuffer();
			
			sql.append("select name \n");
			sql.append("from member \n");
			sql.append("where id = ? and pass = ? \n");
			
			pstmt = con.prepareStatement(sql.toString());
			
			pstmt.setString(1, id);
			pstmt.setString(2, pass);
			
			rs = pstmt.executeQuery();
			
				if(rs.next()) {
					name = rs.getString("name");
				}
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		} finally {
				try {
					if(rs != null)
					rs.close();
					
					if(pstmt != null)
						pstmt.close();
					
					if(con != null)
						con.close();
				} catch (SQLException e) {
					
					e.printStackTrace();
				}
		
		}
		
		String root = request.getContextPath();

    	if(name != null) {
    		response.sendRedirect(root + "/user/loginok.jsp?name=" + URLEncoder.encode(name, "utf-8"));
			} else {
				response.sendRedirect(root + "/user/loginfail.jsp");
		}    

    %>