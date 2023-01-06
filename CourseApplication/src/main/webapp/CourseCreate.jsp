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
<title>Create a Course</title>
</head>
<body>
	<div class = "container theme-showcase" role = "main">
	<div class = "jumbotron">
	<h1>Create Course</h1>
	</div>
	<form action="CourseCreate" method="post">
	<!-- courseid is auto generated
		<p>
			<label for="courseid">CourseId</label>
			<input id="courseid" name="courseid" value="">
		</p>
	-->
	<div class="container">
  		<div class="row">
		<p>
			<label for="coursename">Course Name</label>
			<input id="coursename" name="coursename" value="">
		</p>
		</div>
		
		<div class="row">
		<p>
			<label for="courseurl">Course URL</label>
			<input id="courseurl" name="courseurl" value="">
		</p>
		</div>
		
		<div class="row">
		<p>
			<label for="coursedescription">Course Description</label>
			<input id="coursedescription" name="coursedescription" value="">
		</p>
		</div>
		
		<div class="row">
		<p>
			<label for="coursecertificatetype">Course Certificate Type</label>
			<input id="coursecertificatetype" name="coursecertificatetype" value="">
		</p>
		</div>
		
		<div class="row">
		<p>
			<label for="coursedifficulty">Course Difficulty</label>
			<input id="coursedifficulty" name="coursedifficulty" value="">
		</p>
		</div>
		
		<div class="row">
		<p>
			<label for="institutionname">Institution Name</label>
			<input id="institutionname" name="institutionname" value="">
		</p>
		</div>
		
		<div class="row">
		<p>
			<label for="coursesubject">Course Subject</label>
			<input id="coursesubject" name="coursesubject" value="">
		</p>
		</div>
		
		<div class="row">
		<p>
			<label for="regioncode">Region</label>
			<input id="regioncode" name="regioncode" value="">
		</p>
		</div>
		
		<div class="row">
		<p>
			<label for="professorid">Professor</label>
			<input id="professorid" name="professorid" value="">
		</p>
		</div>
		
		<div class="row">
		<p>
			<label for="taid">TA</label>
			<input id="taid" name="taid" value="">
		</p>
		</div>
		
		</div>
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