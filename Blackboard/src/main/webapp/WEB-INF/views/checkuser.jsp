<%-- <%@ page language="java" contentType="text/html; charset=ISO-8859-1" --%>
<%--     pageEncoding="ISO-8859-1"%> --%>
<!-- <!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd"> -->
<!-- <html> -->
<!-- <head> -->
<!-- <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1"> -->
<!-- <title>Insert title here</title> -->
<!-- </head> -->
<!-- <body> -->

<!-- </body> -->
<!-- </html> -->
<%@ page import="java.sql.*"%> 
<%
System.out.println("inside check user");
String u=request.getParameter("u");
try{  
Class.forName("com.mysql.jdbc.Driver");  
Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/blackboarddb","root","");  
PreparedStatement ps=con.prepareStatement("select * from user_table where userName=?");  
ps.setString(1, u);  
ResultSet rs=ps.executeQuery();  
if(rs!=null){
	out.print("User Name exists");
}
con.close();  
}catch(Exception e){e.printStackTrace();}  

%>