<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Register for a Course</title>
</head>
<body>
	<h1>Register for a Course</h1>
	<form action="Register" method="post">
		<p>
			<label for="userid">UserId</label>
			<input id="userid" name="userid" value="${fn:escapeXml(param.userid)}">
		</p>
		<p>
			<label for="courseid">CourseId</label>
			<input id="courseid" name="courseid" value="${fn:escapeXml(param.courseid)}">
		</p>
		<p>
			<label for="status">Status ("IN_PROGRESS", "COMPLETED", "DROPPED")</label>
			<input id="status" name="status" value="">
		</p>

		<p>
			<input type="submit">
		</p>
	</form>
	<br/><br/>
	<p>
		<span id="successMessage"><b>${messages.success}</b></span>
	</p>

</body>
</html>