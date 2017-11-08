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
<title>View Assignment Details</title>
  
</head>
<body>
	<c:set var="contextPath" value="${pageContext.request.contextPath}" />
	<jsp:include page="menu.jsp" />
	<h2>
		<span class="glyphicon glyphicon-copy"></span> Assignment Details <span
			class="glyphicon glyphicon-paste"></span>
	</h2>
	<div align="right" class="col-sm-12">
		<a href="${contextPath}/viewquestions" class="btn btn-info"
			role="button">View questions posted</a>
		<table border="2" padding="2" align="center">
			<tr>
				<td colspan="5" align="center"><h3>View Submissions</h3></td>
			</tr>
			<tr>
				<td><h4>Student ID</h4></td>
				<td><h4>Student Name</h4></td>
				<td><h4>Submission Date</h4></td>
				<td><h4>File</h4></td>
				<td><h4>Grades</h4></td>
			</tr>
			<tr>
				<c:forEach var="submission" items="${submissions}">
					<tr>
						<td>${submission.person.personID}</td>
						<td>${submission.person.firstName}
							${submission.person.lastName}</td>
						<td>${submission.submissionDate}</td>
						<td><a
							href="${contextPath}/submission/view/professor/${submission.person.personID}">View
								Submitted File</a></td>
						<td>
							<form
								action="${contextPath}/submission/grade/professor/${submission.person.personID}" method="post">
								<table>
									<tr>
										<td><input type="number" step="0.01" name="assignmentGrade" value="${submission.assignmentGrade}">&nbsp;</td>
										<td><input type = "submit" value="Grade This Assignment" class="btn btn-success"></td>
									</tr>
								</table>
							</form>
						</td>

					</tr>
				</c:forEach>

			</tr>
		</table>
	</div>
</body>
</html>