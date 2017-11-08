<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<nav class="navbar navbar-inverse">
	<div class="container-fluid">
		<div class="navbar-header">
			<a class="navbar-brand" href="${contextPath}/user/">Home</a>
		</div>
		<ul class="nav navbar-nav">
			<li><a href="${contextPath}/courses">Courses</a></li>
			<li><a href="${contextPath}/user/sendemail">Send E-Mail</a></li>
		</ul>
		<ul class="nav navbar-nav navbar-right">
			<li class="active"><a href="${contextPath}/user/"><span class="glyphicon glyphicon-user"></span>
				${user.firstName}</a></li>
			<li><a href="${contextPath}/user/logout"><span
					class="glyphicon glyphicon-log-out"></span> Logout</a></li>
		</ul>
	</div>
</nav>