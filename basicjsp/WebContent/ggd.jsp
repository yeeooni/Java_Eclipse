<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
    <%!
    	String color; 
    	String white = "white";
    	int i = 0;
    	int j = 0;
    %>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<div align = "center"> 
				
	<h3> ***구구단*** </h3>
	<table border = 1 width = 1000 height = 700>	
	<%  
       	for(int i=2; i<=9; i++){ 
       		color = i % 2 == 0 ? "red" : "lightblue";
       		out.print("<tr id = \"td\" style = \"background-color : " + color + "\">");
       		
       		for(int j = 1; j < 10; j++) {
       			out.print("<td align=center style = 'font-weight : bold; font-size : 20px; color : " + white + ";'>"); 
       			out.print(i + " * " + j + " = " + (i * j)); 
       			out.print("</td>"); 
       		       }
       	        	out.print("</tr>"); 
       	        }  
   	%>	
    </table>
	<hr>
				
		<h3> ***구구단*** </h3>
		<table border = 1 width = 1000 height = 700>
			<% for(i=2; i<=9; i++){ %>
			<% color = i % 2 == 0 ? "red" : "lightblue"; %>
			 	<tr style = "background-color : <%=color%>">
			 	<%  for(j = 1; j < 10; j++) { %>
					<td align=center style = 'font-weight : bold; font-size : 20px; color : white;'> 
						<%= i %> * <%= j %> = <%=(i * j)%>
					</td>	
					<% } %>
				</tr>	
			<% } %> 
		</table>		
		<hr>
				
		</div>
</body>
</html>