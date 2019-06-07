<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Student</title>
    <link rel="stylesheet"
          href="<c:url value='/resources/css/bootstrap.min.css'/>">
</head>
<body>
<div class="container" style="margin-top: 30px">
    <h1>
        <a href="logout">Logout</a>
    </h1>
    <div class="row">
        <div class="col-xs-6 col-sm-6 col-md-6 col-lg-6">
            <strong style="margin-right: 20px; font-size: 20px">Courses</strong>
            <input type="radio" name="type" value="0" id="learned"/> have
            learned <input type="radio" name="type" value="1" id="learning"
                           checked="checked"/> is learning
            <table class="table" id="courses">

            </table>
        </div>
        <div class="col-xs-6 col-sm-6 col-md-6 col-lg-6">
            <strong style="margin-right: 20px; font-size: 20px">Schedule</strong>
            <input type="radio" name="time" value="0" id="week"
                   checked="checked"/> this week <input type="radio" name="time"
                                                        value="1" id="month"/> this month

            <table class="table" id="schedule">

            </table>
        </div>
    </div>
</div>

<script type="text/javascript"
        src="<c:url value='/resources/js/jquery.min.js'/>"></script>

<script type="text/javascript"
        src="<c:url value='/resources/js/bootstrap.min.js'/>"></script>

<script type="text/javascript"
        src="<c:url value='/resources/js/custom_student.js'/>"></script>
</body>
</html>