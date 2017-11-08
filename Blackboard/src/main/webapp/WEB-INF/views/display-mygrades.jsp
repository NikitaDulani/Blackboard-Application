<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>My Grades</title>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.0/jquery.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
</head>
<body>
	<c:set var="contextPath" value="${pageContext.request.contextPath}" />
	<jsp:include page="menu.jsp" />
	
	<div class="col-sm-12">
		<table>
			<tr>
				<td><h2><span class="glyphicon glyphicon-education"></span> My Grades <span class="glyphicon glyphicon-education"></span></h2></td>
			</tr>
			<tr><td>
			<table border="2" padding="2" align="center">
			<tr>
				<td><h4>Course Name</h4></td>
				<td><h4>Student Grade</h4></td>
			</tr>
			<tr>
			
			<c:forEach var="studentGrade" items="${studentGrades}">
					<tr>
						<td>${studentGrade.courses.courseName}</td>
						<td>${studentGrade.courseGrade}</td>
					</tr>
				</c:forEach>
			
			</tr>
		</table>
	</td>
	</tr>
	</table>
	</div>
</body>
</html>