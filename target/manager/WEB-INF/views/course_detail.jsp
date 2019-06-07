<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>

<head>
    <meta charset="UTF-8">
    <title>Detail</title>
    <link rel="stylesheet"
          href="<c:url value='/resources/css/bootstrap.min.css'/>">
</head>

<body>
<div class="container">
    <a href="admin">Home</a>
    <table class="table">
        <tr class="rows">
            <th>Id</th>
            <th>Name</th>
            <th>Num Of Lesson</th>
            <th>Fee</th>
            <th>Promotion</th>
            <th>Type</th>
        </tr>
        <tr class="rows">
            <td>${course.getCourseId()}</td>
            <td>${course.getCourseName()}</td>
            <td>${course.getNumOfLesson()}</td>
            <td>${course.getFee()}</td>
            <td>${course.getPromotion() }</td>
            <td>${course.getCourseType().getType()}</td>
    </table>

    <div id="teacher">
        <strong>Teacher:</strong>
        <table class="table">
            <tr>
                <th>Id</th>
                <th>Full name</th>
            </tr>
            <c:forEach items="${teachers}" var="teacher">
                <tr>
                    <td>${teacher.getUserId()}</td>
                    <td>${teacher.getFullname()}</td>
                </tr>
            </c:forEach>
        </table>

    </div>
    <div id="student">
        <strong>Student:</strong>

        <form method="post" action="upload?id=${course.getCourseId()}"
              enctype="multipart/form-data">
            <input type="file" name="file"/> <input type="submit"
                                                    value="Add Student"/>
        </form>

        <table class="table">
            <tr>
                <th>Id</th>
                <th>Full name</th>
            </tr>
            <c:forEach items="${students}" var="student">
                <tr>
                    <td>${student.getUserId()}</td>
                    <td>${student.getFullname()}</td>
                </tr>
            </c:forEach>
        </table>
    </div>
</div>


<script type="text/javascript"
        src="<c:url value='/resources/js/jquery.min.js'/>"></script>

<script type="text/javascript"
        src="<c:url value='/resources/js/bootstrap.min.js'/>"></script>
</body>

</html>
