<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<c:set var="resourceContext" value="${pageContext.request.contextPath}" />

<html lang="en">
<head>
    <title>Create User</title>
    <link rel="stylesheet" href="${resourceContext}/bootstrap.min.css">
    <script type="text/javascript" src="${resourceContext}/bootstrap.bundle.min.js"></script>
</head>
<body>
<div class="center">

    <form:form modelAttribute="userForm" cssClass="search-box">
        Please enter your login
        <br/>
        <form:input path="login" />
        <br/>
        Please enter your password
        <br/>
        <br/>
        <form:input path="password" />
        <br/>
        <input type="submit" class="btn btn-success" value="create" />

        <br/>
        <h3>${error}</h3>
    </form:form>

    <br/>
    <a href="/products" class="btn btn-info">Back to products</a>
</div>
</body>
</html>