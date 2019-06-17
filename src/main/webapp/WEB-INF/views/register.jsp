<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Sign Up Form by Colorlib</title>

    <!-- Font Icon -->
    <link rel="stylesheet" href="<c:url value='/resources/fonts/material-icon/css/material-design-iconic-font.min.css'/>">

    <!-- Main css -->
    <link rel="stylesheet" href="<c:url value='/resources/css/style-login.css'/>">
</head>
<body>

    <div class="main">

        <!-- Sign up form -->
        <section class="signup">
            <div class="container">
                <div class="signup-content">
                    <div class="signup-form">
                        <h2 class="form-title">Sign up</h2>
                        <form:form method="POST" class="register-form" id="register-form" modelAttribute="user" action="${pageContext.request.contextPath}/submit-register">
                            <div class="form-group">
                                <form:label path="fullname" for="name"><i class="zmdi zmdi-account material-icons-name"></i></form:label>
                                <form:input path="fullname" type="text" name="name" id="name" placeholder="Your Name"/>
                            </div>
                            <div class="form-group">
                                <form:label path="username" for="email"><i class="zmdi zmdi-email"></i></form:label>
                                <form:input path="username" type="email" name="email" id="email" placeholder="Your Email"/>
                            </div>
                            <div class="form-group">
                                <form:label path="password" for="pass"><i class="zmdi zmdi-lock"></i></form:label>
                                <form:input path="password" type="password" name="pass" id="pass" placeholder="Password"/>
                            </div>
                            <div class="form-group">
                                <label for="re-pass"><i class="zmdi zmdi-lock-outline"></i></label>
                                <input type="password" name="re_pass" id="re_pass" placeholder="Repeat your password"/>
                            </div>
                            <div class="form-group">
                                <form:label path="userType" for="type"><i class="zmdi zmdi-lock"></i></form:label>
                                <form:select path="userType" multiple="false" cssStyle="margin-left: 30px">
                                    <form:option value="NONE" label="--- Select ---"/>
                                    <form:option value="teacher" label="teacher"/>
                                    <form:option value="student" label="student"/>
                                </form:select>
                            </div>
                            <div class="form-group">
                                <input type="checkbox" name="agree-term" id="agree-term" class="agree-term" />
                                <label for="agree-term" class="label-agree-term"><span><span></span></span>I agree all statements in  <a href="#" class="term-service">Terms of service</a></label>
                            </div>
                            <div class="form-group form-button">
                                <input type="submit" name="signup" id="signup" class="form-submit" value="Register"/>
                            </div>
                        </form:form>
                    </div>
                    <div class="signup-image">
                        <figure><img src="<c:url value='/resources/images/signup-image.jpg'/>" alt="sing up image"></figure>
                        <a href="login" class="signup-image-link">I am already member</a>
                    </div>
                </div>
            </div>
        </section>
    </div>

    <!-- JS -->

    <script src="<c:url value='/resources/vendor/jquery/jquery.min.js'/>"></script>
    <script src="<c:url value='/resources/js/main-login.js'/>"></script>
</body>
</html>