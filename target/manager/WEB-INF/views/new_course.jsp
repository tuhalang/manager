<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>

<head>
    <meta charset="UTF-8">
    <title>New</title>
    <link rel="stylesheet"
          href="<c:url value='/resources/css/bootstrap.min.css'/>">
</head>

<body>
<div class="container">
    <form:form method="POST" action="add_course" modelAttribute="course">
        <a href="teacher">Home</a>
        <table>
            <tr>
                <td>
                    <form:label path="courseName">Course's name: </form:label>
                </td>
                <td>
                    <form:input type="text" path="courseName" required="required"/>
                </td>
            </tr>
            <tr>
                <td>
                    <form:label path="startDate">Start: </form:label>
                </td>
                <td>
                    <form:input type="date" path="startDate" required="required"/>
                </td>
            </tr>
            <tr>
                <td>
                    <form:label path="endDate">End: </form:label>
                </td>
                <td>
                    <form:input type="date" path="endDate" required="required"/>
                </td>
            </tr>
            <tr>
                <td>
                    <form:label path="numOfLesson">Num of lesson: </form:label>
                </td>
                <td>
                    <form:input type="text" path="numOfLesson" required="required"/>
                </td>
            </tr>
            <tr>
                <td>
                    <form:label path="fee">Fee: </form:label>
                </td>
                <td>
                    <form:input type="text" path="fee" required="required"/>
                </td>
            </tr>
            <tr>
                <td>
                    <form:label path="promotion">Promotion: </form:label>
                </td>
                <td>
                    <form:input type="text" path="promotion" required="required"/>
                </td>
            </tr>
            <tr>
                <td>
                    <form:label path="courseType">Type: </form:label>
                </td>
                <td>
                    <form:select path="courseType" multiple="false">
                        <form:option value="NONE" label="--- Select ---"/>
                        <c:forEach items="${courseTypes}" var="courseType">
                            <form:option value="${courseType.getType()}" label="${courseType.getType()}"></form:option>
                        </c:forEach>
                    </form:select>
                </td>
            </tr>
            <tr>
                <td><input type="submit" value="Submit"/></td>
            </tr>
        </table>
    </form:form>
</div>
<script type="text/javascript" src="<c:url value='/resources/js/jquery.min.js'/>"></script>

<script type="text/javascript" src="<c:url value='/resources/js/bootstrap.min.js'/>"></script>
</body>

</html>