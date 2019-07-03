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
    <link href="<c:url value="/resources/css/custom.css"/>" rel="stylesheet">
    <style>
        td{
            height: 40px;
        }
    </style>
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
                        <li><a  href="${pageContext.request.contextPath}/teacher">HOME</a></li>
                        <li><a style="color: red" href="${pageContext.request.contextPath}/new_course">NEW COURSE</a></li>
                    </ul>
                </nav>

            </div>

        </div>
    </div>
</header>
<!-- Header section end -->

<div class="hero-section set-bg">
    <div class="container" style="margin-top: 130px">
        <div class="row">
            <div class="col-xs-5 col-sm-5 col-md-5 col-lg-5">
                <form:form method="POST" action="add_course" modelAttribute="course" name="course_form">

                    <table>
                        <tr>
                            <td>
                                <form:label path="courseName">Tên khóa học: </form:label>
                            </td>
                            <td>
                                <form:input cssStyle="height: 30px" type="text" path="courseName" required="required"/>
                            </td>
                        </tr>
                        <tr>
                            <td>
                                <form:label path="startDate">Bắt đầu: </form:label>
                            </td>
                            <td>
                                <form:input cssStyle="height: 30px" type="date" path="startDate" required="required"/>
                            </td>
                        </tr>
                        <tr>
                            <td>
                                <form:label path="endDate">Kết thúc: </form:label>
                            </td>
                            <td>
                                <form:input cssStyle="height: 30px" type="date" path="endDate" required="required"/>
                            </td>
                        </tr>
                        <tr>
                            <td>
                                <form:label path="numOfLesson">Số bài học: </form:label>
                            </td>
                            <td>
                                <form:input cssStyle="height: 30px" type="text" path="numOfLesson" required="required"/>
                            </td>
                        </tr>
                        <tr>
                            <td>
                                <form:label path="fee">Học phí: </form:label>
                            </td>
                            <td>
                                <form:input cssStyle="height: 30px" type="text" path="fee" required="required"/>
                            </td>
                        </tr>
                        <tr>
                            <td>
                                <form:label path="promotion">Khuyến mãi: </form:label>
                            </td>
                            <td>
                                <form:input cssStyle="height: 30px" type="text" path="promotion" required="required"/>
                            </td>
                        </tr>
                        <tr>
                            <td>
                                <form:label path="courseType">Loại: </form:label>
                            </td>
                            <td>
                                <form:select path="courseType" multiple="false">
                                    <form:option value="NONE" label="--- Select ---"/>
                                    <c:forEach items="${courseTypes}" var="courseType">
                                        <form:option value="${courseType.getType()}"
                                                     label="${courseType.getType()}"></form:option>
                                    </c:forEach>
                                </form:select>
                            </td>
                        </tr>
                        <tr>
                            <td><input id="new_course" type="submit" value="Submit" class="btn btn-dark"/></td>
                        </tr>
                    </table>
                </form:form>
            </div>
            <div class="col-xs-7 col-sm-7 col-md-7 col-lg-7">
                <div id="lessonInfo" style="padding-top: 30px">
                    <h4><span>Bài học </span><span id="num_lesson">0</span>/<span id="total_lesson">0</span></h4>
                    <table>
                        <tr>
                            <td>
                                <label>Tên bài học: </label>
                            </td>
                            <td>
                                <input style="height: 30px" id="name_lesson" type="text" required="required"/>
                            </td>
                        </tr>
                        <tr>
                            <td>
                                <label>Nội dung: </label>
                            </td>
                            <td>
                                <input style="height: 30px" id="content_lesson" type="text" required="required"/>
                            </td>
                        </tr>
                        <tr>
                            <td>
                                <label>Ngày: </label>
                            </td>
                            <td>
                                <input style="height: 30px" id="date_lesson" type="date" required="required"/>
                            </td>
                        </tr>
                        <tr>
                            <td>
                                <label>Thời lượng: </label>
                            </td>
                            <td>
                                <input style="height: 30px" id="length_lesson" type="text" required="required"/>
                            </td>
                        </tr>

                        <tr>
                            <td><input id="new_lesson" type="button" value="Submit" class="btn btn-dark"/></td>
                        </tr>
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
<script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/select2/4.0.7/js/select2.min.js"></script>
<!--[if lt IE 9]>
<script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
<script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
<![endif]-->
<script type="text/javascript" src="<c:url value='/resources/js/jquery.twbsPagination.js'/>"></script>
<script type="text/javascript" src="<c:url value='/resources/js/custom_newcourse.js'/>"></script>
</body>
</html>
