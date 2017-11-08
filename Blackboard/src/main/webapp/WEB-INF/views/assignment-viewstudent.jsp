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
<title>View Assignment Details</title>
</head>
<body>
	<c:set var="contextPath" value="${pageContext.request.contextPath}" />
	<jsp:include page="menu.jsp" />
	<div class="col-sm-12">
	<table>
	<tr><td>
	<h2>
		<span class="glyphicon glyphicon-copy"></span> Assignment Details <span
			class="glyphicon glyphicon-paste"></span>
	</h2>
	</td></tr>
	<tr><td>
		<a href="${contextPath}/viewquestions" class="btn btn-info"
			role="button">View questions posted</a></td></tr>
		<tr>
					<td colspan="2">&nbsp;</td>
				</tr>
		<tr>
		<form:form action="${contextPath}/submitAssignment" commandName="submission" method="post"
			enctype="multipart/form-data">
			<td><table>
				<tr>
					<td>Upload File:</td>
					<td><input type="file" name="submittedFile" required /></td>
				</tr>
				<tr>
					<td colspan="2">&nbsp;</td>
				</tr>
				<tr>
					<td></td>
					<td colspan="2"><input type="submit" class="btn btn-success"
						value="Submit Assignment" /></td>
				</tr>
			</table></td>
		</form:form>
	</tr><tr>
					<td colspan="2">&nbsp;</td>
				</tr><tr>
					<td colspan="2">&nbsp;</td>
				</tr>
	<tr><td>
	<table>
		<tr>
			<td>Assignment Grade: </td>
			<td>${sessionScope.assignmentGrade}</td>
		</tr>
	</table></td>
	</tr>
	</table>
	</div>
</body>
</html>