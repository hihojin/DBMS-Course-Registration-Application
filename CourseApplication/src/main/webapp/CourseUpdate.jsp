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
<title>Update a Course</title>
</head>
<body>
<div class = "container theme-showcase" role = "main">
	<div class = "jumbotron">
	<h1>Update Course</h1>
	</div>
	<form action="CourseUpdate" method="post">
		<p>
			<label for="courseid">Course Id</label>
			<input id="courseid" name="courseid" value="${fn:escapeXml(param.courseid)}">
		</p>
		
		<p>
			<label for="coursedescription">New Course Description</label>
			<input id="coursedescription" name="coursedescription" value="">
		</p>
		
		<p>
			<input type="submit" class="btn btn-lg btn-outline-secondary">
		</p> 
	</form>
	<br/><br/>
	<p>
		<span id="successMessage"><b>${messages.success}</b></span>
	</p>
	</div>
</body>
</html>