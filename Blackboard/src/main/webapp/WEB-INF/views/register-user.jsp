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
<title>Register Here</title>
</head>
<body>

	<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<a href="${contextPath}">
<button type="button" class="btn btn-info btn-sm">Back</button> </a>

<h2>
	<span class="glyphicon glyphicon-user"></span> Register New User <span
		class="glyphicon glyphicon-user"></span>
</h2>

<form:form action="${contextPath}/user/register" commandName="user"
	method="post">
	<table>
		<tr>
			<td>First Name:</td>
			<td><form:input path="firstName" size="30" required="required""/>
				<font color="red"><form:errors path="firstName" /></font></td>
		</tr>

		<tr>
			<td>Last Name:</td>
			<td><form:input path="lastName" size="30" required="required" />
				<font color="red"><form:errors path="lastName" /></font></td>
		</tr>
					<tr>
						<td>User Name:</td>
						<td><form:input path="userName" size="30" required="required" />
							<font color="red"><form:errors path="userName" /></font></td>
					</tr>

		<tr>
			<td>Password:</td>
			<td><form:password path="password" size="30" required="required" />
				<font color="red"><form:errors path="password" /></font></td>
		</tr>

		<tr>
			<td>Email Id:</td>
			<td><form:input path="email" size="30" type="email"
					required="required" /> <font color="red"><form:errors
						path="email" /></font></td>
		</tr>
		<tr>
			<td>Select Student/professor:</td>
			<td><select name="type">
					<option value="student">Student</option>
					<option value="professor">Professor</option>
			</select>
			<td>
		</tr>
		<tr>
			<td colspan="2">&nbsp;</td>
		</tr>
		<tr>
			<td></td>
			<td colspan="2"><input type="submit" class="btn btn-success"
				value="Register User" /></td>
		</tr>
	</table>

</form:form>

<script>
function checkUserName(str) {
  var xhttp;    
  if (str == "") {
    document.getElementById("check").innerHTML = "";
    return;
  }

  if(window.XMLHttpRequest){  
	  xhttp=new XMLHttpRequest();  
	  }  
	  else if(window.ActiveXObject){  
		  xhttp=new ActiveXObject("Microsoft.XMLHTTP");  
	  }
  xhttp.onreadystatechange = function() {
    if (this.readyState == 4 && this.status == 200) {
      document.getElementById("check").innerHTML = this.responseText;
    }
  };
  xhttp.open("GET", "checkuser.jsp?u="+str, true);
  xhttp.send();
}
</script>
</body>
</html>