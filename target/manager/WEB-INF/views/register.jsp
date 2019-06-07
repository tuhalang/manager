<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
<head>
    <title>Register</title>
    <link rel="stylesheet"
          href="<c:url value='/resources/css/bootstrap.min.css'/>">
</head>
<body>
<div class="container">
    <form:form action="submit-register" method="post" modelAttribute="user">
        <h5 style="color:red">${info}</h5>
        <table class="table" style="width: 300px">
            <tr class="row">
                <td>
                    <form:label path="username">Username: </form:label>
                </td>
                <td>
                    <form:input type="text" path="username" required="required"/>
                </td>
            </tr>
            <tr class="row">
                <td>
                    <form:label path="password">Password: </form:label>
                </td>
                <td>
                    <form:input id="pass" type="password" path="password" required="required"/>
                </td>
            </tr>
            <tr class="row">
                <td>
                    <label>Re-password: </label>
                </td>
                <td>
                    <input id="re-pass" type="password" required="required"/>
                </td>
            </tr>
            <tr class="row">
                <td>
                    <form:label path="fullname">Fullname: </form:label>
                </td>
                <td>
                    <form:input type="text" path="fullname" required="required"/>
                </td>
            </tr>
            <tr class="row">
                <td>
                    <form:label path="userType">Type: </form:label>
                </td>
                <td>
                    <form:select path="userType" multiple="false">
                        <form:option value="NONE" label="--- Select ---"/>
                        <form:option value="teacher" label="teacher"/>
                        <form:option value="student" label="student"/>
                    </form:select>
                </td>
            </tr>
            <tr class="row">
                <td><input id="submit" type="submit" value="Register"/></td>
            </tr>
        </table>
    </form:form>
</div>

<script type="text/javascript"
        src="<c:url value='/resources/js/jquery.min.js'/>"></script>
<script type="text/javascript"
        src="<c:url value='/resources/js/bootstrap.min.js'/>"></script>
<script type="text/javascript"
        src="<c:url value='/resources/js/custom_register.js'/>"></script>
</body>
</html>
