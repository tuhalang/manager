<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <title>WebUni - Education Template</title>
    <meta charset="UTF-8">
    <meta name="description" content="WebUni Education Template">
    <meta name="keywords" content="webuni, education, creative, html">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <!-- Favicon -->
    <link href="<c:url value='/resources/img/favicon.ico'/>" rel="shortcut icon"/>

    <!-- Google Fonts -->
    <link href="https://fonts.googleapis.com/css?family=Raleway:400,400i,500,500i,600,600i,700,700i,800,800i"
          rel="stylesheet">

    <!-- Stylesheets -->
    <link rel="stylesheet" href="<c:url value='/resources/css/bootstrap.css'/>"/>
    <link rel="stylesheet" href="<c:url value='/resources/css/font-awesome.min.css'/>"/>
    <link rel="stylesheet" href="<c:url value='/resources/css/owl.carousel.css'/>"/>
    <link rel="stylesheet" href="<c:url value='/resources/css/style.css'/>"/>
    <link href="https://cdnjs.cloudflare.com/ajax/libs/select2/4.0.7/css/select2.min.css" rel="stylesheet"/>
    <link href="//netdna.bootstrapcdn.com/twitter-bootstrap/2.3.1/css/bootstrap-combined.min.css" rel="stylesheet">
    <link rel="stylesheet" href="<c:url value='/resources/css/custom.css'/>"/>


</head>
<body>
<!-- Page Preloder -->
<div id="preloder">
    <div class="loader"></div>
</div>

<!-- Header section -->
<header class="header-section">
    <div class="container">
        <div class="row">
            <div class="col-lg-3 col-md-3">

                <div class="site-logo">
                    <img src="<c:url value='/resources/img/logo.png'/>" alt="">
                </div>

                <div class="nav-switch">
                    <i class="fa fa-bars"></i>
                </div>
            </div>

            <div class="col-lg-9 col-md-9">

                <div class="dropdown header-btn">
                    <button class=" dropbtn site-btn header-btn">${user.getFullname().substring(0,1)}</button>
                    <div class="dropdown-content">
                        <a href="${pageContext.request.contextPath}/account">Tài khoản</a>
                        <a href="${pageContext.request.contextPath}/logout">Đăng xuất</a>
                    </div>

                </div>

                <nav class="main-menu">
                    <ul>
                        <li><a style="color: red" href="${pageContext.request.contextPath}/admin">KHÓA HỌC</a></li>
                        <li><a  href="${pageContext.request.contextPath}/admin/teacher">GIÁO VIÊN</a></li>
                        <li><a href="${pageContext.request.contextPath}/admin/student">HỌC VIÊN</a></li>
                        <li><a href="${pageContext.request.contextPath}/admin/create_new">TẠO MỚI</a></li>
                    </ul>
                </nav>

            </div>

        </div>
    </div>
</header>
<!-- Header section end -->

<div class="hero-section set-bg" style="padding-top: 130px">
    <div class="course_info" style="padding-left: 50px">
        <div class="rows">
            <div class="col-xs-5 col-sm-5 col-md-5 col-lg-5">
                <h4>Thông tin khóa học:</h4>
                <table class="table" style="width: 400px">
                    <tr class="row"><td>Id khóa học:</td> <td>${course.getCourseId()}</td></tr>
                    <tr class="row"><td>Tên khóa học: </td> <td>${course.getCourseName()}</td></tr>
                    <tr class="row"><td>Giáo viên:</td> <td>  <c:forEach items="${teachers}" var="teacher">
                        ${teacher.getFullname()}
                    </c:forEach></td></tr>
                    <tr class="row"><td>Số lượng bài học:</td> <td> ${course.getNumOfLesson()}</td></tr>
                    <tr class="row"><td>Học phí:</td> <td> ${course.getFee()}</td></tr>
                    <tr class="row"><td>Khuyến mãi:</td> <td> ${course.getPromotion()}</td></tr>
                    <tr class="row"><td>Loại:</td> <td> ${course.getCourseType().getType()}</td></tr>
                    <tr class="row"><td>Số lượng học viên:</td> <td> ${course.getListUsers().size()-1}</td></tr>
                    <form method="post" action="${pageContext.request.contextPath}/upload?id=${course.getCourseId()}" enctype="multipart/form-data">
                        <tr class="row">
                            <td><input type="file" name="file"/></td>
                            <td><input style="color: black" type="submit" value="Add Student"/></td>
                        </tr>
                    </form>
                </table>
            </div>
            <div class="col-xs-7 col-sm-7 col-md-7 col-lg-7">
                <h4>Giáo trình chi tiêt:</h4>
                <table class="table">
                    <tr>
                        <td>Id</td><td>Tên bài học</td><td>Thời lượng</td><td>Nội dung</td>
                    </tr>
                    <c:forEach items="${course.getListLessons()}" var="lesson">
                        <tr>
                            <td>${lesson.getLessonId()}</td>
                            <td>${lesson.getLessonName()}</td>
                            <td>${lesson.getLength()}</td>
                            <td>${lesson.getContent()}</td>
                        </tr>
                    </c:forEach>
                </table>
            </div>
        </div>

    </div>
</div>


<!-- footer section -->
<footer class="">
    <div class="footer-bottom">
        <div class="footer-warp">
            <div class="copyright">
                Copyright &copy;<script>document.write(new Date().getFullYear());</script>
                All rights reserved | This template is made with <i class="fa fa-heart-o" aria-hidden="true"></i> by <a
                    href="https://facebook.com/hungpv99" target="_blank">Pham Hung</a>
            </div>
        </div>
    </div>
</footer>
<!-- footer section end -->

<!--====== Javascripts & Jquery ======-->
<script type="text/javascript" src="<c:url value='/resources/js/jquery-3.2.1.min.js'/>"></script>
<script type="text/javascript" src="<c:url value='/resources/js/bootstrap.js'/>"></script>
<script type="text/javascript" src="<c:url value='/resources/js/mixitup.min.js'/>"></script>
<script type="text/javascript" src="<c:url value='/resources/js/circle-progress.min.js'/>"></script>
<script type="text/javascript" src="<c:url value='/resources/js/owl.carousel.min.js'/>"></script>
<script type="text/javascript" src="<c:url value='/resources/js/main.js'/>"></script>
<script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/select2/4.0.7/js/select2.min.js"></script>
<script type="text/javascript" src="<c:url value='/resources/js/custom_admin.js'/>"></script>
<!--[if lt IE 9]>
<script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
<script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
<![endif]-->
<script type="text/javascript" src="<c:url value='/resources/js/jquery.twbsPagination.js'/>"></script>
</html>
