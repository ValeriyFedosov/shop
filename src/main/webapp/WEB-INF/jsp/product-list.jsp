<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<c:set var="resourceContext" value="${pageContext.request.contextPath}" />

<html lang="en">
<head>
    <title>Product List</title>
    <link rel="stylesheet" href="${resourceContext}/bootstrap.min.css">
    <link rel="stylesheet" href="${resourceContext}/layout.css">
    <link rel="stylesheet" href="${resourceContext}/style.css">
    <script type="text/javascript" src="${resourceContext}/bootstrap.bundle.min.js"></script>
</head>
<body>
<div class="center">

    <form:form modelAttribute="searchForm" cssClass="search-box">
        <form:input path="searchText" />
        <input type="submit" class="btn btn-success" value="Search" />
    </form:form>

    <ul class="prod-list">
    <c:forEach items="${products}" var="prod">
        <li class="prod-item">
            <h3>${prod.title} ( ${prod.balance} )</h3>
            <p>${prod.description}</p>
            <div class="action-box">
                <a href="/cart?add&prodId=${prod.id}" class="btn btn-info" role="button">Add to Cart</a>
                <a href="/order?prodId=${prod.id}" class="btn btn-info" role="button">Buy</a>
                <a href="/product/${prod.id}" class="btn btn-warning" role="button">Edit</a>
            </div>
        </li>
    </c:forEach>
    </ul>
    <table></table>

    <div class="action-box">
        <a href="/product" class="btn btn-warning" role="button">New product</a>
    </div>

</div>
</body>
</html>
