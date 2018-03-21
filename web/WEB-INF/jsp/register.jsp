<%--
  Created by IntelliJ IDEA.
  User: v.matviichuk
  Date: 21.03.2018
  Time: 20:04
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <title>Title</title>
    <style>
        label {
            display: block;
        }
    </style>
</head>
<body>
    <sf:form method="post" action="/SpringMvcProject/user/add">
        <label>Username</label>
        <sf:input path="username"></sf:input>
        <label>Password</label>
        <sf:input path="password" type="password"></sf:input>
        <label>Email</label>
        <sf:input path="email"></sf:input>
        <button type="submit">Register</button>
    </sf:form>
</body>
</html>
