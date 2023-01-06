<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<!-- Bootstrap -->
<link href="css/bootstrap.min.css" rel="stylesheet">
<title>Delete a Course</title>
</head>
<body>
<div class = "container theme-showcase" role = "main">

	<form action="CourseDelete" method="post">
	<div class = "jumbotron">
	<h1>Delete Course</h1>
		</div>
		<p>
			<div <c:if test="${messages.disableSubmit}">style="display:none"</c:if>>
				<label for="courseid">Course Id</label>
				<input id="courseid" name="courseid" value="${fn:escapeXml(param.courseid)}">
			</div>
		</p>
		<p>
			<span id="submitButton" <c:if test="${messages.disableSubmit}">style="display:none"</c:if>>
			<input type="submit" class="btn btn-lg btn-outline-secondary">
			</span>
		</p>
	</form>
	<br/><br/>
	</div>
	
</body>
</html>