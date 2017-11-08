<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<title>Welcome To Blackboard</title>
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

<div class="text-info" class="col-sm-12">Welcome To Blackboard </div><br/>
<!-- <div class="container-fluid"> -->
		<div class="col-sm-2">
			<table>
				<tr>
					<td><h4><a href="${contextPath}/grades">My Grades</a></h4></td>
				</tr>
				<tr>
					<td><h4><a href="${contextPath}/user/sendemail">Send E-Mail</a></h4></td>
				</tr>
				<tr>
					<td><h4><a href="${contextPath}/personalinfo">Personal Information</a></h4></td>
				</tr>
			</table>
		</div>
		<div class="col-sm-10">
		<table>
			<tr>
				<td><h4><a href="${contextPath}/courses">My Courses</a></h4></td>
			</tr>
			<tr>
				<td>
					<table border="1">
						<c:forEach var="course" items="${courses}">
							<tr>
								<td>${course[1]}</td>
							</tr>
						</c:forEach>
					</table>
				</td>
			</tr>

<!-- 			<tr> -->
<!-- 				<td><a href="#">Send E-Mail</a></td> -->

<!-- 			</tr> -->
		</table>
	</div>
</body>
</html>