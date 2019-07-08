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
                        <li><a  href="${pageContext.request.contextPath}/admin">KHÓA HỌC</a></li>
                        <li><a href="${pageContext.request.contextPath}/admin/teacher">GIÁO VIÊN</a></li>
                        <li><a href="${pageContext.request.contextPath}/admin/student">HỌC VIÊN</a></li>
                        <li><a href="${pageContext.request.contextPath}/admin/create_new">TẠO MỚI</a></li>
                        <li><a style="color: red" href="${pageContext.request.contextPath}/admin/fee">HỌC PHÍ</a></li>
                    </ul>
                </nav>

            </div>

        </div>
    </div>
</header>
<!-- Header section end -->


<!-- Hero section -->
<section class="hero-section set-bg">
    <div class="container" style="color: black; padding-top: 150px">
        <div class="row">
            <h3>Thu hoc phi</h3>
            <input id="txt-search" style="height: 30px" type="text" placeholder="Nhap id hoc vien"/>
            <input id="search" style="margin-bottom: 10px" class="btn btn-fade" type="button" value="Tim kiem">
            <div class="row">
                <div class="col-xs-6 col-sm-6 col-md-6 col-lg-6">
                    <table class="table" id="info-course">
                        <tr>
                            <th>STT</th>
                            <th>Ten khoa hoc</th>
                            <th>Hoc phi</th>
                        </tr>
                    </table>
                    <div id="btn-payment" style="display: none;">
                        <span>Tong cong: </span> <span id="total"></span>
                        <br>
                        <span>Da nop: </span> <span id="paid"></span>
                        <br>
                        <span>Con lai: </span> <span id="lack"></span>
                        <br><br>
                        <input id="money" style="height: 30px" type="text" placeholder="nhap so tien">
                        <input id="push" style="margin-bottom: 10px" class="btn btn-fade" type="button" value="Nop tien">
                    </div>

                    <div id="nodata">
                        <h4 style="color: gray">khong co du lieu hien thi</h4>
                    </div>

                </div>
                <div class="col-xs-6 col-sm-6 col-md-6 col-lg-6">
                    <table class="table" id="history">
                        <tr>
                            <th>STT</th>
                            <th>Ngay nop</th>
                            <th>So tien</th>
                        </tr>
                    </table>
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
<script type="text/javascript" src="<c:url value='/resources/js/jquery-3.2.1.min.js'/>"></script>
<script type="text/javascript" src="<c:url value='/resources/js/bootstrap.js'/>"></script>
<script type="text/javascript" src="<c:url value='/resources/js/mixitup.min.js'/>"></script>
<script type="text/javascript" src="<c:url value='/resources/js/circle-progress.min.js'/>"></script>
<script type="text/javascript" src="<c:url value='/resources/js/owl.carousel.min.js'/>"></script>
<script type="text/javascript" src="<c:url value='/resources/js/main.js'/>"></script>
<script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/select2/4.0.7/js/select2.min.js"></script>
<!--[if lt IE 9]>
<script type="text/javascript" src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
<script type="text/javascript" src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
<![endif]-->
<script type="text/javascript" src="<c:url value='/resources/js/jquery.twbsPagination.js'/>"></script>
<script type="text/javascript" src="<c:url value='/resources/js/payment.js'/>"></script>
</body>
</html>