<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<c:set var="resourceContext" value="${pageContext.request.contextPath}" />

<html lang="en">
<head>
    <title>Product List</title>
    <link rel="stylesheet" href="${resourceContext}/bootstrap.min.css">
    <script type="text/javascript" src="${resourceContext}/bootstrap.bundle.min.js"></script>
</head>
<body>
<div class="center">


    <form name="f" method="post">
        <fieldset>
            <legend>Please Sign In</legend>
            <div class="alert alert-error">
                Invalid username and password.
            </div>
            <div class="alert alert-success">
                You have been logged out.
            </div>
            <label for="username">Username</label>
            <input id="username" name="username"/>
            <label for="password">Password</label>
            <input type="password" id="password" name="password"/>
            <div class="form-actions">
                <button class="btn">Log in</button>
            </div>
        </fieldset>
    </form>
</div>
</body>
</html>
