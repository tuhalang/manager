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
    <link href="<c:url value="/resources/css/custom.css"/>" rel="stylesheet">

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
                        <li><a style="color: red" href="${pageContext.request.contextPath}/teacher">HOME</a></li>
                        <li><a href="${pageContext.request.contextPath}/new_course">NEW COURSE</a></li>
                    </ul>
                </nav>

            </div>

        </div>
    </div>
</header>
<!-- Header section end -->

<div class="hero-section set-bg">
    <div class="container" style="margin-top: 130px">
        <h3>Các khóa học đang dạy:</h3>
        <input id="search" onkeyup="searchCourse()" type="text" class="input-group-text" placeholder="search" style="height: 30px;margin-right: 20px">
        <table id="courses" class="table" style="margin-top: 20px">
            <tr class="row" style="font-weight: bold">
                <td>Id</td>
                <td>Tên khóa học</td>
                <td>Học phí</td>
                <td>Bắt đầu</td>
                <td>Kết thúc</td>
                <td>Số bài học</td>
                <td>Trạng thái</td>
            </tr>
            <c:forEach items="${courses}" var="course">
                <tr class="row">
                    <td>${course.getCourseId()}</td>
                    <td>${course.getCourseName()}</td>
                    <td>${course.getFee()}</td>
                    <td>${course.getStartDate()}</td>
                    <td>${course.getEndDate()}</td>
                    <td>${course.getNumOfLesson()}</td>
                    <td>${course.getStatus()}</td>
                    <td><a class="btn btn-info" href="${pageContext.request.contextPath}/edit_course?id=${course.getCourseId()}">edit</a>
                    </td>
                </tr>
            </c:forEach>
        </table>
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

<script type="text/javascript">
    function searchCourse(){
        var key = $('#search').val();
        $.ajax({
            type: "get",
            contentType: "application/json",
            url: "api/searchInUser",
            data: {
                key: key
            },
            dataType: 'json',
            timeout: 10000,
            success: function (data) {
                updateDate(data);
            },
            error: function (e) {
                console.log("ERROR: ", e);
            }
        })
    }
    function updateDate(data) {
        $('#courses').empty();
        var tr = "<tr class=\"row\" style=\"font-weight: bold\">\n" +
            "                <td>Id</td>\n" +
            "                <td>Tên khóa học</td>\n" +
            "                <td>Học phí</td>\n" +
            "                <td>Bắt đầu</td>\n" +
            "                <td>Kết thúc</td>\n" +
            "                <td>Số bài học</td>\n" +
            "                <td>Trạng thái</td>\n" +
            "            </tr>";
        $('#courses').append(tr);
        $.each(data, function (index, course) {
            var tr = "<tr class=\"row\">\n" +
                "                    <td>"+course['courseId']+"</td>\n" +
                "                    <td>"+course['courseName']+"</td>\n" +
                "                    <td>"+course['fee']+"</td>\n" +
                "                    <td>"+course['startDate']+"</td>\n" +
                "                    <td>"+course['endDate']+"</td>\n" +
                "                    <td>"+course['numOfLesson']+"</td>\n" +
                "                    <td>"+course['status']+"</td>\n" +
                "                    <td><a class=\"btn btn-info\" href=\"${pageContext.request.contextPath}/edit_course?id="+course['courseId']+"\">edit</a>\n" +
                "                    </td>\n" +
                "                </tr>";
            $('#courses').append(tr);
        })
    }
</script>
</body>
</html>
