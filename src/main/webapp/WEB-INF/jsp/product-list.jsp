<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>


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
<a href="/cart" class="btn btn-danger" role="button">Cart</a>
<div class="container-fluid">
    <div class="row">
        <div class="col-12">
            <div class="float-md-none" style="width: 200px; height: 20px; color: #fff;"></div>
            <div class="alert alert-secondary" role="alert">
                <span>Products</span>
                <span class="badge badge-pill badge-warning float-right">${cart}</span>
            </div>
        </div>
    </div>
</div>


    <div class="action-box">
        <sec:authorize access="hasRole('ROLE_ANONYMOUS')">
            <a href="/login" class="btn btn-warning" role="button">Login</a>
        </sec:authorize>
        <sec:authorize access="!hasRole('ROLE_ANONYMOUS')">
            <a href="/logout" class="btn btn-warning" role="button">Logout</a>
        </sec:authorize>
    </div>
<div class="center">

    <form:form modelAttribute="searchForm" cssClass="search-box">
        <form:input path="searchText" />
        <input type="submit" class="btn btn-success" value="Search" />
    </form:form>

    <div class="container-fluid">
        <div class="row" style="justify-content: center">
            <c:forEach items="${products}" var="prod">
                <div class="col-6" style="margin-bottom: 20px">
                    <div class="card border-primary" style="max-width: 650px;">
                        <div class="card-header badge-primary">${prod.title} ( ${prod.balance} )</div>
                        <div class="card-body text-primary">
                            <h4 class="card-title">${prod.description}</h4>
                            <sec:authorize access="hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')">
                                <a href="/cart?add&prodId=${prod.id}" class="btn btn-info" role="button">Add to Cart</a>
                                <a href="/product-view?prodId=${prod.id}" class="btn btn-info" role="button">Buy</a>
                            </sec:authorize>
                            <sec:authorize access="hasRole('ROLE_ADMIN')">
                                <a href="/product?${prod.id}" class="btn btn-warning" role="button">Edit</a>
                                <a href="/product?delete&prodId=${prod.id}" class="btn btn-warning" role="button">Delete</a>
                            </sec:authorize>
                        </div>
                    </div>
                </div>
            </c:forEach>
        </div>
    </div>


    <div class="action-box float-right">
        <sec:authorize access="hasRole('ROLE_ADMIN')">
            <a href="/product" class="btn btn-warning" role="button">New product</a>
        </sec:authorize>
    </div>

</div>
</body>
</html>
