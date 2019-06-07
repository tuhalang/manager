<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>

<head>
    <meta charset="UTF-8">
    <title>Admin</title>
    <link rel="stylesheet"
          href="<c:url value='/resources/css/bootstrap.min.css'/>">
    <link
            href="https://cdnjs.cloudflare.com/ajax/libs/select2/4.0.7/css/select2.min.css"
            rel="stylesheet"/>

</head>

<body>

<div class="container">
    <h1 style="text-align: center; margin-bottom: 50px;">Welcome
        ${user.getFullname()}</h1>
    <h1><a href="logout">Logout</a></h1>
    <div class="row">
        <div class="col-xs-8 col-sm-8 col-md-8 col-lg-8">
            <div class="row" style="display: block; margin-bottom: 30px;">
                <div class="col-xs-6 col-sm-6 col-md-6 col-lg-6">
                    <h3>List course</h3>
                    <select name="Type" id="course_type">
                        <option value="0">All</option>
                    </select>
                </div>
                <div class="col-xs-6 col-sm-6 col-md-6 col-lg-6">
                    <h3>Filter by student</h3>
                    <select id="multiple-search" name="states[]" multiple="multiple"
                            style="width: 200px">

                    </select>
                </div>
            </div>

            <div>
                <table id="list_course" class="table">

                </table>
            </div>
        </div>


        <div class="col-xs-4 col-sm-4 col-md-4 col-lg-4">
            <h3>Student</h3>
            <select id="student-search" name="states[]" multiple="multiple"
                    style="width: 200px">
            </select>
            <table class="table" id="student">
                <tr class="rows">
                    <th>Id</th>
                    <th>Full Name</th>
                    <th>Total Time</th>
                </tr>
            </table>
        </div>

    </div>
</div>


<script type="text/javascript"
        src="<c:url value='/resources/js/jquery.min.js'/>"></script>

<script
        src="https://cdnjs.cloudflare.com/ajax/libs/select2/4.0.7/js/select2.min.js"></script>

<script type="text/javascript"
        src="<c:url value='/resources/js/custom_admin.js'/>"></script>

<script type="text/javascript"
        src="<c:url value='/resources/js/bootstrap.min.js'/>"></script>
</body>

</html>