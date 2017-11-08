<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.0/jquery.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<title>Assign grade to student</title>
</head>
<body>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
	<jsp:include page="menu.jsp" />
	
	<div class="col-sm-12">
	<form:form action="${contextPath}/gradeassigned" commandName="courseGrade" method="post">
		<table>
			<tr>
				<td><h2><span class="glyphicon glyphicon-pencil"></span> Assign Grade <span class="glyphicon glyphicon-pencil"></span></h2></td>
			</tr>
			<tr>
			<td><h4>Student ID: ${sessionScope.selectedStudentID}</h4></td>
			</tr>
			<tr>
			<td><h4>Enter Grade: </h4></td>
			<td><form:input path="courseGrade" required="required" />
					<font color="red"><form:errors path="courseGrade" /></font></td>
			</tr>
			<tr>
				<td colspan="2">&nbsp;</td>
			</tr>
			<tr>
				<td colspan="2"><input type="submit" class="btn btn-success"
					value="Submit Grade" /></td>
			</tr>
			</table>
			</form:form>
			</div>
</body>
</html>