<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
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
                        <li><a href="${pageContext.request.contextPath}/admin">KHÓA HỌC</a></li>
                        <li><a href="${pageContext.request.contextPath}/admin/teacher">GIÁO VIÊN</a></li>
                        <li><a href="${pageContext.request.contextPath}/admin/student">HỌC VIÊN</a></li>
                        <li><a href="${pageContext.request.contextPath}/admin/create_new">TẠO MỚI</a></li>
                        <li><a href="${pageContext.request.contextPath}/admin/fee">HỌC PHÍ</a></li>
                    </ul>
                </nav>

            </div>

        </div>
    </div>
</header>
<!-- Header section end -->


<!-- Hero section -->
<section class="hero-section set-bg">
    <div class="container" style="padding-top: 150px;">
        <div class="row">
            <div class="col-xs-6 col-sm-6 col-md-6 col-lg-6">
                <form:form method="post" action="" modelAttribute="account" name="form-account">
                    <div class="input-group">
                        <span class="input-group-addon" style="width: 125px">Id:</span>
                        <form:input path="userId" type="text" class="form-control" cssStyle="height: 28px"/>
                    </div>
                    <div class="input-group">
                        <span class="input-group-addon" style="width: 125px">Username:</span>
                        <form:input path="username" type="text" class="form-control" cssStyle="height: 28px"/>
                    </div>
                    <div class="input-group">
                        <span class="input-group-addon" style="width: 125px">Fullname:</span>
                        <form:input path="fullname" type="text" class="form-control" cssStyle="height: 28px"/>
                    </div>
                    <div class="input-group">
                        <span class="input-group-addon" style="width: 125px">Email:</span>
                        <form:input path="email" type="text" class="form-control" cssStyle="height: 28px"/>
                    </div>
                    <div class="input-group">
                        <span class="input-group-addon" style="width: 125px">Number phone:</span>
                        <form:input path="phone" type="text" class="form-control" cssStyle="height: 28px"/>
                    </div>
                    <div class="input-group">
                        <span class="input-group-addon" style="width: 125px">Address:</span>
                        <form:input path="address" type="text" class="form-control" cssStyle="height: 28px"/>
                    </div>
                    <div style="padding-top: 20px">
                        <button id="update" type="submit" class="btn">Update Info</button>
                    </div>
                </form:form>
            </div>
            <div class="col-xs-6 col-sm-6 col-md-6 col-lg-6">
                <h4>Change password</h4>
                <form method="post">
                    <table>
                        <tr>
                            <td>Old password:</td>
                            <td><input id="old-pass" type="password"></td>
                        </tr>
                        <tr>
                            <td>New password:</td>
                            <td><input id="new-pass" type="password"></td>
                        </tr>
                        <tr>
                            <td>Re-pass:</td>
                            <td><input id="re-new-pass" type="password"></td>
                        </tr>
                        <tr>
                            <td></td>
                            <td>
                                <button id="change" type="submit">Change</button>
                            </td>
                        </tr>
                    </table>
                </form>
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
<script src="<c:url value='/resources/js/account.js'/>"></script>
</body>
</html>