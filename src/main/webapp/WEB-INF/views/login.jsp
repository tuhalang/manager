<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Login</title>
</head>
<body>
<h5 style="color:red">${error}</h5>
<h5 style="color:green">${info}</h5>
<form method="post" action="login">
    username: <input name="username" type="text"/>
    <br>
    password: <input name="password" type="password"/>
    <br>
    <input type="submit" value="Login">
</form>
</body>
</html>