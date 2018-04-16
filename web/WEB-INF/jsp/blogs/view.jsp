<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>My blog</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css"
          integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">


    <link rel="stylesheet" href="media/styles.css">
</head>
<body>
<div class="container">
    <jsp:include page="../layout/header.jsp"/>

    <div class="container">
        <h3>${blog.name}</h3>
        <p>${blog.description}</p>
        <sf:form method="post" action="/SpringMvcProject/post/add" commandName="postAddModel">
            <sf:input path="blogId" type="hidden" value="${blog.id}"/>
            <label>Title</label>
            <sf:input path="title"/><br>
            <label>Content</label>
            <sf:textarea path="content"/><br>
            <sf:input path="" type="submit" value="POST IT!"/>
        </sf:form>
        <div>
            <c:forEach items="${posts}" var="post">
                <div style="border: 1px solid #666; border-radius: 6px;">
                    <h4>${post.title}</h4>
                    <p>${post.content}</p>
                    <p>${post.date}</p>
                </div>
            </c:forEach>
        </div>
    </div>
    <script src="https://code.jquery.com/jquery-3.2.1.slim.min.js"
            integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js"
            integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"
            integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>

</div>
</body>
</html>
