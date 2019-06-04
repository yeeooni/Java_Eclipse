<%@tag language ="java" pageEncoding ="UTF-8"  body-content="empty"%>

<%@ attribute name="current" required="true" type ="java.lang.Integer" %>
<%@ attribute name="start" required="true"  type ="java.lang.Integer" %>
<%@ attribute name="end" required="true"  type ="java.lang.Integer" %>
<%@ attribute name="url" required="true" %>

<%
	for(int i = start; i<=end; i++){
		if(i == current){
%>	<%=i %>
<%		} else{ %>
				<a href ="<%=url%>?page=<%=i%>"> <%=i %></a>
			<%} %>
<%	} %>