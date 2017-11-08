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
<title>View Course Details</title>
</head>
<body>
	<c:set var="contextPath" value="${pageContext.request.contextPath}" />
	<jsp:include page="menu.jsp" />
	
	<div class="col-sm-12">
		<table>
			<tr>
				<td><h2><span class="glyphicon glyphicon-list-alt"></span> Assignments <span class="glyphicon glyphicon-list-alt"></span></h2></td>
			</tr>
			<tr>
				<td>
					<table border="1">
						<c:forEach var="assignment" items="${assignments}">
							<tr>
								<td><a href="${contextPath}/assignment/view/professor/${assignment.assignmentID}">${assignment.title}</a></td>
							</tr>
						</c:forEach>
					</table>
				</td>
			</tr>
			<tr>
				<td colspan="2">&nbsp;</td>
			</tr>
 			<tr>
				<td><a href="${contextPath}/create-assignment" class="btn btn-info" role="button">Create new Assignment</a></td>
			</tr>
			<tr>
				<td colspan="2">&nbsp;</td>
			</tr>
			<tr>
				<td colspan="2">&nbsp;</td>
			</tr>
			<tr>
				<td><h2><span class="glyphicon glyphicon-hand-right"></span> Assign Grades <span class="glyphicon glyphicon-hand-left"></span></h2></td>
			</tr>
			<tr>
				<td><a href="${contextPath}/viewstudentsforgrades" class="btn btn-info" role="button">Go To Grading Page</a></td>
			</tr>
			<tr>
				<td colspan="2">&nbsp;</td>
			</tr>
			<tr>
				<td colspan="2">&nbsp;</td>
			</tr>
			<tr>
				<td><h2><span class="glyphicon glyphicon-share"></span> Send E-Mail <span class="glyphicon glyphicon-share"></span></h2></td>
			</tr>
			<tr>
				<td><a href="${contextPath}/sendemailtostudents" class="btn btn-info" role="button">Send E-mail to students</a></td>
			</tr>
		</table>
	</div>
</body>
</html>