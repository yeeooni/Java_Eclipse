<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import ="java.sql.*"%>
    
    <%! 
    	int cnt;
    	int number;
    	String str;
    	int len;

    	public void init() {
    		cnt = 0;
	    	try {
				Class.forName("oracle.jdbc.driver.OracleDriver");
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
			System.out.println("DB드라이버 연결 성공!");
	    }
    	
    	Connection con = null;
		Statement stmt = null;
		ResultSet rs = null;
		
    %>
    
    <%
    
    try {
	    con = DriverManager.getConnection("jdbc:oracle:thin:@192.168.14.52:1521:orcl", "kitri", "kitri");
			
	   		String sql = "";
			sql += "select no \n";
			sql += "from counter";
			
			stmt = con.createStatement();
			rs = stmt.executeQuery(sql);
			
			rs.next();
			cnt = rs.getInt(1);
			
			sql += "update counter \n";
			sql += "set no = no + 1 \n";
			
			stmt.executeUpdate(sql);
			
			
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
				try {
					if(rs != null)
						rs.close();
					if(stmt != null)
						stmt.close();
					if(con != null)
						con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
		}
    %>
     <% str = Integer.toString(cnt); %>
<!DOCTYPE html>
	<html>
	<head>
	<meta charset="UTF-8">
	<title>DB Counter</title>
	</head>
		<body>
			당신은
					<% len = str.length(); %>
					
					<% for(int i = 0; i < len; i ++) { %>
						<img width = 150 src = "/basicjsp/img/<%= str.charAt(i) %>.png">
					<% } %>
			번째 방문자 입니다.	
		</body>
</html>