<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>

<head>
    <meta charset="UTF-8">
    <title>Teacher</title>
    <link rel="stylesheet" href="<c:url value='/resources/css/bootstrap.min.css'/>">
</head>

<body>
<h1><a href="logout">Logout</a></h1>
<h4>The courses are teaching</h4>
<a href="new_course">New Course</a>
<table class="table">
    <tr class="rows">
        <th>Id</th>
        <th>Name</th>
        <th>Num Of Lesson</th>
        <th>Fee</th>
        <th>Promotion</th>
        <th>Type</th>
    </tr>
    <c:forEach items="${courses}" var="course">
        <tr class="rows">
            <td>${course.getCourseId()}</td>
            <td>${course.getCourseName()}</td>
            <td>${course.getNumOfLesson()}</td>
            <td>${course.getFee()}</td>
            <td>${course.getPromotion() }</td>
            <td>${course.getCourseType().getType()}</td>
            <td><a href="edit_course?id=${course.getCourseId()}">edit</a></td>
        </tr>
    </c:forEach>
</table>
<script type="text/javascript" src="<c:url value='/resources/js/jquery.min.js'/>"></script>

<script type="text/javascript" src="<c:url value='/resources/js/bootstrap.min.js'/>"></script>
</body>

</html>