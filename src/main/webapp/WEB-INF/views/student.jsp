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
    <link rel="stylesheet" href="<c:url value='/resources/css/style_student_course.css'/>"/>
    <link rel="stylesheet" href="<c:url value='/resources/css/custom.css'/>"/>
    <link href="https://cdnjs.cloudflare.com/ajax/libs/select2/4.0.7/css/select2.min.css" rel="stylesheet"/>
    <link href="//netdna.bootstrapcdn.com/twitter-bootstrap/2.3.1/css/bootstrap-combined.min.css" rel="stylesheet">

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

                <div class="dropdown header-btn">
                    <button class=" dropbtn site-btn header-btn">${user.getFullname().substring(0,1)}</button>
                    <div class="dropdown-content">
                        <a href="${pageContext.request.contextPath}/account">Tài khoản</a>
                        <a href="${pageContext.request.contextPath}/logout">Đăng xuất</a>
                    </div>
                </div>

                <nav class="main-menu">
                    <ul>
                        <li><a href="${pageContext.request.contextPath}/student">HOME</a></li>
                        <li><a href="${pageContext.request.contextPath}/student/schedule">SCHEDULE</a></li>
                        <li><a href="${pageContext.request.contextPath}/student/course">HISTORIES</a></li>
                    </ul>
                </nav>

            </div>

        </div>
    </div>
</header>
<!-- Header section end -->


<!-- Hero section -->
<section class="hero-section set-bg">
    <div class="container" style="padding-top: 150px">
        <input onkeyup="searchCourse()" id="search_course" type="text" class="input-group-text" placeholder="search" style="height: 30px;margin-right: 20px">
        <input type="radio" name="type" value="1"> By name
        <input type="radio" name="type" value="2"> By time(yyyy-MM)
        <div class="row" id="courses">

        </div>
        <div style="padding-top: 20px">
            <nav aria-label="Page navigation">
                <ul class="pagination" id="pagination"></ul>
            </nav>
        </div>


        <div class="hover_bkgr_fricc">
            <span class="helper"></span>
            <div>
                <div class="popupCloseButton">X</div>
                <div id="content-popup">


                </div>
            </div>
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
<script type="text/javascript" src="<c:url value='/resources/js/jquery.twbsPagination.js'/>"></script>
<script type="text/javascript" src="<c:url value='/resources/js/custom_student_course.js'/>"></script>

</body>
</html>