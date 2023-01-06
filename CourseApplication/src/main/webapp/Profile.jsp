<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Profile</title>
</head>
<body>

	<h1>${messages.title}</h1>
		<a href="Register?userid=<c:out value="${fn:escapeXml(param.userid)}"/>">Register</a>
		<br/><br/>
        <table border="1">
            <tr>
                <th>CourseId</th>
                <th>CourseName</th>
                <th>CourseUrl</th>
                <th>Status</th>
                <th>Enroll</th>
                <th>Complete</th>
                <th>Drop</th>
            </tr>
            <c:forEach items="${registrations}" var="registration" >
                <tr>
                    <td><c:out value="${registration.getCourseId()}" /></td>
                    <td><c:out value="${registration.getCourseName()}" /></td>
                    <td><c:out value="${registration.getCourseUrl()}" /></td>
                    <td><c:out value="${registration.getStatus()}" /></td>
                    <td><a href="Enroll?record=<c:out value="${registration.getRegistrationId()}"/>">Enroll</a></td>
                    <td><a href="Complete?record=<c:out value="${registration.getRegistrationId()}"/>">Complete</a></td>
                    <td><a href="Drop?record=<c:out value="${registration.getRegistrationId()}"/>">Drop</a></td>
                    
                </tr>
            </c:forEach>
       </table>

</body>
</html>