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
    <link rel="stylesheet" href="<c:url value='/resources/css/bootstrap.css'/>"/>
    <link rel="stylesheet" href="<c:url value='/resources/css/font-awesome.min.css'/>"/>
    <link rel="stylesheet" href="<c:url value='/resources/css/owl.carousel.css'/>"/>
    <link rel="stylesheet" href="<c:url value='/resources/css/style.css'/>"/>
    <link href="https://cdnjs.cloudflare.com/ajax/libs/select2/4.0.7/css/select2.min.css" rel="stylesheet"/>
    <link href="//netdna.bootstrapcdn.com/twitter-bootstrap/2.3.1/css/bootstrap-combined.min.css" rel="stylesheet">


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

                <a href="${pageContext.request.contextPath}/logout" class="site-btn header-btn">Đăng xuất</a>

            </div>

        </div>
    </div>
</header>
<!-- Header section end -->
<div class="hero-section set-bg" data-setbg="<c:url value='/resources/img/bg1.jpg'/>"
     style="padding-top: 150px; color: white">
    <div class="container">
        <div class="row">
            <div class="col-xs-5 col-sm-5 col-md-5 col-lg-5">
                <a href="${pageContext.request.contextPath}/teacher" class="btn btn-primary">Back</a>
                <h3>Info of course:</h3>
                <form:form modelAttribute="course" style="padding-top: 20px" method="POST" action="${pageContext.request.contextPath}/update_course">
                    <div class="input-group">
                        <span class="input-group-addon" style="width: 125px">Id:</span>
                        <form:input path="courseId" type="text" class="form-control" cssStyle="height: 28px"/>
                    </div>
                    <div class="input-group">
				<span class="input-group-addon" style="width: 125px">Tên khóa học:</span>
                        <form:input path="courseName" type="text" class="form-control" cssStyle="height: 28px"/>
                    </div>
                    <div class="input-group">
				<span class="input-group-addon" style="width: 125px">Bắt đầu:</span>
                        <form:input path="startDate" type="date" class="form-control" cssStyle="height: 28px; width: 183px;"/>
                    </div>
                    <div class="input-group">
				<span class="input-group-addon" style="width: 125px">Kết thúc:</span>
                        <form:input path="endDate" type="date" class="form-control" cssStyle="height: 28px; width: 183px;"/>
                    </div>
                    <div class="input-group">
				<span class="input-group-addon" style="width: 125px">Số bài học:</span>
                        <form:input path="numOfLesson" type="text" class="form-control" cssStyle="height: 28px"/>
                    </div>
                    <div class="input-group">
                        <span class="input-group-addon" style="width: 125px">Học phí:</span>
                        <form:input path="fee" id="msg" type="text" class="form-control" cssStyle="height: 28px"/>
                    </div>
                    <div class="input-group">
                        <span class="input-group-addon" style="width: 125px">Khuyến mãi:</span>
                        <form:input path="promotion" type="text" class="form-control" cssStyle="height: 28px"/>
                    </div>
                    <div class="input-group">
                        <span class="input-group-addon" style="width: 125px">Loại:</span>
                        <form:input path="courseType" type="text" class="form-control" cssStyle="height: 28px"/>
                    </div>
                    <div class="input-group">
                        <span class="input-group-addon" style="width: 125px">Trạng thái:</span>
                        <form:input path="status" type="text" class="form-control" cssStyle="height: 28px"/>
                    </div>
                    <div style="padding-top: 20px">
                        <button type="submit" class="btn">Update Info</button>
                    </div>
                </form:form>
            </div>

            <div class="col-xs-7 col-sm-7 col-md-7 col-lg-7" style="margin-top: 30px">
                <select class="form-control" style="width:150px" id="lesson">
                    <option value="NONE">--select lesson--</option>
                </select>
                <div class="info_lesson">
                    <span>Tên bài học: </span><span id="name_lesson"></span>
                    <br>
                    <span>Thời lượng: </span><span id="length"></span>
                    <br>
                    <span>Nội dung: </span><span id="content"></span>
                    <h4>Học Viên:</h4>
                    <table class="table" id="students">

                    </table>
                </div>
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
<!--[if lt IE 9]>
<![endif]-->
<script type="text/javascript" src="<c:url value='/resources/js/jquery.twbsPagination.js'/>"></script>
<script type="text/javascript" src="<c:url value='/resources/js/custom_teacher.js'/>"></script>
</body>
</html>