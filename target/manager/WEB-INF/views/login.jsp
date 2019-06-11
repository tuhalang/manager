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
        <!-- Sing in  Form -->
        <section class="sign-in">
                <div class="container">
                    <div class="signin-content">
                        <div class="signin-image">
                            <figure><img src="<c:url value='/resources/images/signin-image.jpg'/>" alt="sing up image"></figure>
                            <a href="register.jsp" class="signup-image-link">Create an account</a>
                        </div>
    
                        <div class="signin-form">
                            <h2 class="form-title">Sign up</h2>
                            <form method="POST" class="register-form" id="login-form" action="login">
                                <div class="form-group">
                                    <label for="username"><i class="zmdi zmdi-account material-icons-name"></i></label>
                                    <input type="text" name="username" id="username" placeholder="Email"/>
                                </div>
                                <div class="form-group">
                                    <label for="your_pass"><i class="zmdi zmdi-lock"></i></label>
                                    <input type="password" name="password" id="your_pass" placeholder="Password"/>
                                </div>
                                <div class="form-group form-button">
                                    <input type="submit" name="signin" id="signin" class="form-submit" value="Log in"/>
                                </div>
                            </form>
                            
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