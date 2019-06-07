<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>

<head>
    <meta charset="UTF-8">
    <title>Edit</title>
    <link rel="stylesheet"
          href="<c:url value='/resources/css/bootstrap.min.css'/>">
</head>

<body>
<div class="container">
    <div class="row">
        <div class="col-xs-5 col-sm-5 col-md-5 col-lg-5">
            <a href="teacher">Home</a>
            <h1>Info of course:</h1>
            <form:form modelAttribute="course" style="padding-top: 20px" method="POST" action="update_course">
                <h5 style="color: green">${success}</h5>
                <h5 style="color: green">${error}</h5>
                <div class="input-group">
                    <span class="input-group-addon" style="width: 125px">Id:</span>
                    <form:input path="courseId" type="text" class="form-control"/>
                </div>
                <div class="input-group">
				<span class="input-group-addon" style="width: 125px">Course's
					name:</span>
                    <form:input path="courseName" type="text" class="form-control"/>
                </div>
                <div class="input-group">
				<span class="input-group-addon" style="width: 125px">Start
					date:</span>
                    <form:input path="startDate" type="date" class="form-control"/>
                </div>
                <div class="input-group">
				<span class="input-group-addon" style="width: 125px">End
					date:</span>
                    <form:input path="endDate" type="date" class="form-control"/>
                </div>
                <div class="input-group">
				<span class="input-group-addon" style="width: 125px">Num of
					lesson:</span>
                    <form:input path="numOfLesson" type="text" class="form-control"/>
                </div>
                <div class="input-group">
                    <span class="input-group-addon" style="width: 125px">Fee:</span>
                    <form:input path="fee" id="msg" type="text" class="form-control"/>
                </div>
                <div class="input-group">
                    <span class="input-group-addon" style="width: 125px">Promotion:</span>
                    <form:input path="promotion" type="text" class="form-control"/>
                </div>
                <div class="input-group">
                    <span class="input-group-addon" style="width: 125px">Type:</span>
                    <form:input path="courseType" type="text" class="form-control"/>
                </div>
                <div class="input-group">
                    <span class="input-group-addon" style="width: 125px">Status:</span>
                    <form:input path="status" type="text" class="form-control"/>
                </div>
                <div style="padding-top: 20px">
                    <button type="submit" class="btn">Submit</button>
                </div>
            </form:form>

            <form method="post" action="upload?id=${course.getCourseId()}" enctype="multipart/form-data"
                  style="margin-top: 20px">
                <input type="file" name="file"/>
                <input type="submit" value="Add Student"/>
            </form>
        </div>

        <div class="col-xs-7 col-sm-7 col-md-7 col-lg-7" style="margin-top: 30px">
            <h5 style="color: red"><c:if
                    test="${(course.numOfLesson-course.listLessons.size()) > 0}"> Missing ${course.numOfLesson-course.listLessons.size()} lessons</c:if></h5>
            <input id="lessonName" type="text" placeholder="Lesson's name"/>
            <input id="length" type="text" placeholder="Length of lesson"/>
            <input type="button" class="btn btn-default" value="New lesson" onclick="newLesson()">
            <br>
            <select class="form-control" style="width:150px" id="lesson">
                <option value="NONE">--select lesson--</option>
            </select>
            <h3>List of students:</h3>
            <table class="table" id="students">

            </table>
        </div>
    </div>

</div>
<script type="text/javascript"
        src="<c:url value='/resources/js/jquery.min.js'/>"></script>

<script type="text/javascript"
        src="<c:url value='/resources/js/bootstrap.min.js'/>"></script>
<script type="text/javascript"
        src="<c:url value='/resources/js/custom_teacher.js'/>"></script>
</body>

</html>