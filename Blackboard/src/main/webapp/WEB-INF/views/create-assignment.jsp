<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
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
<title>Create New Assignment</title>
</head>
<body>
	<c:set var="contextPath" value="${pageContext.request.contextPath}" />
	<jsp:include page="menu.jsp" />

	<h2>
		<span class="glyphicon glyphicon-book"></span> Create New Assignment <span
			class="glyphicon glyphicon-book"></span>
	</h2>

	<form:form action="${contextPath}/create-assignment" commandName="assignment" method="post" enctype="multipart/form-data">
		<table>
			<tr>
				<td>Title:</td>
				<td><form:input path="title" required="required" />
					<font color="red"><form:errors path="title" /></font></td>
			</tr>

			<tr>
				<td>Due Date:</td>
				<td><input type="date" name="dueDate" required></td>
			</tr>
			<tr>
				<td>Upload File:</td>
				<td><input type="file" name="assignmentFile" required/></td>
			</tr>
			<tr>
				<td colspan="2">&nbsp;</td>
			</tr>
			<tr>
				<td></td>
				<td colspan="2"><input type="submit" class="btn btn-success"
					value="Create Assignment" /></td>
			</tr>
			</table>
	</form:form>
</body>
</html>