<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<%--
  Created by IntelliJ IDEA.
  User: vadym
  Date: 23-Mar-18
  Time: 10:59 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<header class="blog-header py-3">
    <div class="row flex-nowrap justify-content-between align-items-center">
        <div class="col-4 pt-1">
            <a class="text-muted" href="/SpringMvcProject/blogs/all">View all blogs</a>
        </div>
        <div class="col-4 d-flex justify-content-end align-items-center">
            <c:if test="${sessionScope.login != null}">
                <a class="btn btn-sm btn-outline-secondary" href="/SpringMvcProject/blogs/your">Your blogs</a>
                <a class="btn btn-sm btn-outline-secondary" href="/SpringMvcProject/blogs/add">Add blog</a>
                <a class="btn btn-sm btn-outline-secondary" href="/SpringMvcProject/user/logout">Logout</a>
            </c:if>
            <c:if test="${sessionScope.login == null}">
                <a class="btn btn-sm btn-outline-secondary" href="/SpringMvcProject/user/login">Login</a>
                <a class="btn btn-sm btn-outline-secondary" href="/SpringMvcProject/user/add">Register</a>
            </c:if>

        </div>
    </div>
</header>