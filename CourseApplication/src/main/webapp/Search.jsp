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
<title>Search</title>
</head>
<body>
<div class = "container theme-showcase" role = "main">
<form action="Search" method="post">
	<div class = "jumbotron">
	<h1>Search Course</h1>
	</div>
	<p>
		<label for="coursename" >Course Name</label>
		<input id="coursename" name="coursename" value="">
	</p>
	<p>
		<label for="coursesubject">Course Subject</label>
		<input id="coursesubject" name="coursesubject" value="">
	</p>
	<p>
		<label for="professorid" >Professor ID</label>
		<input id="professorid" name="professorid" value="">
	</p>
	<p>
		<label for="region" >Region</label>
		<input id="region" name="region" value="">
	</p>
	<p>
		<label for="rating" >Rating</label>
		<input id="rating" name="rating" value="">
		<label>& Up</label>
	</p>

	<p>
		<input type="submit" class="btn btn-lg btn-outline-secondary">
	</p>
	
 	<br/>
	<h1>Matching Courses</h1>
	<h1>${messages.title}</h1>
        <table class="table table-striped">
            <thead><tr>
                <th>CourseId</th>
                <th>CourseName</th>
                <th>CourseUrl</th>
                <th>CourseDescription</th>
                <th>CourseCertificateType</th>
                <th>CourseDifficulty</th>
                <th>InstitutionName</th>
                <th>CourseSubject</th>
                <th>Region</th>
                <th>Professor</th>
                <th>TA</th>
                <th>Rating</th>
                <th>Delete Course</th>
            </tr></thead>
            <c:forEach items="${courses}" var="course" >
                <tr>
                    <td><c:out value="${course.getCourseId()}" /></td>
                    <td><c:out value="${course.getCourseName()}" /></td>
                    <td><c:out value="${course.getCourseUrl()}" /></td>
                    <td><c:out value="${course.getCourseDescription()}" /></td>
                    <td><c:out value="${course.getCourseCertificateType()}" /></td>
                    <td><c:out value="${course.getCourseDifficulty()}" /></td>
                    <td><c:out value="${course.getInstitutionName()}" /></td>
                    <td><c:out value="${course.getCourseSubject()}" /></td>
                    <td><c:out value="${course.getRegionCode()}" /></td>
                    <td><c:out value="${course.getProfessorsId()}" /></td>
                    <td><c:out value="${course.getTaId()}" /></td>
                    <td><c:out value="${course.getRating()}" /></td>
                   
                    <td><a href="CourseDelete?courseid=<c:out value="${course.getCourseId()}"/>">Delete</a></td>
                </tr>
            </c:forEach>
       </table>
    </div>
       
    <!-- Bootstrap -->
    <!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
    <!-- Include all compiled plugins (below), or include individual files as needed -->
    <script src="js/bootstrap.min.js"></script>
</body>
</html>
