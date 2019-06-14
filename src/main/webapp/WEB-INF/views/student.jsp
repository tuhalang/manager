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
    <link rel="stylesheet" href="<c:url value='/resources/css/bootstrap.min.css'/>"/>
    <link rel="stylesheet" href="<c:url value='/resources/css/font-awesome.min.css'/>"/>
    <link rel="stylesheet" href="<c:url value='/resources/css/owl.carousel.css'/>"/>
    <link rel="stylesheet" href="<c:url value='/resources/css/style.css'/>"/>


    <!--[if lt IE 9]>
    <script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
    <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
    <![endif]-->

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

                <a href="logout" class="site-btn header-btn">Logout</a>

                <nav class="main-menu">
                    <ul>
                        <li><a href="${pageContext.request.contextPath}/student">HOME</a></li>
                        <li><a href="${pageContext.request.contextPath}/student/schedule">SCHEDULE</a></li>
                        <li><a href="${pageContext.request.contextPath}/student/course">COURSES</a></li>
                    </ul>
                </nav>

            </div>

        </div>
    </div>
</header>
<!-- Header section end -->


<!-- Hero section -->
<section class="hero-section set-bg" data-setbg="<c:url value='/resources/img/bg2.jpg'/>">
    <div class="container" style="padding-top: 150px; color: white">
        <div class="row">
            <div class="col-xs-10 col-sm-10 col-md-10 col-lg-10">
                <strong style="margin-right: 20px; font-size: 20px">Courses</strong>
                <input type="radio" name="type" value="0" id="learned"/> Đã học
                <input type="radio" name="type" value="1" id="learning" checked="checked"/> Đang học
                <table class="table" id="courses">

                </table>
            </div>
            <%--<div class="col-xs-6 col-sm-6 col-md-6 col-lg-6">--%>
            <%--<strong style="margin-right: 20px; font-size: 20px">Schedule</strong>--%>
            <%--<input type="radio" name="time" value="0" id="week"--%>
            <%--checked="checked"/> this week <input type="radio" name="time"--%>
            <%--value="1" id="month"/> this month--%>

            <%--<table class="table" id="schedule">--%>

            <%--</table>--%>
            <%--</div>--%>
        </div>
    </div>
</section>
<!-- Hero section end -->

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
<script src="<c:url value='/resources/js/jquery-3.2.1.min.js'/>"></script>
<script src="<c:url value='/resources/js/bootstrap.js'/>"></script>
<script src="<c:url value='/resources/js/mixitup.min.js'/>"></script>
<script src="<c:url value='/resources/js/circle-progress.min.js'/>"></script>
<script src="<c:url value='/resources/js/owl.carousel.min.js'/>"></script>
<script src="<c:url value='/resources/js/main.js'/>"></script>
<script src="<c:url value='/resources/js/custom_student.js'/>"></script>
</body>
</html>