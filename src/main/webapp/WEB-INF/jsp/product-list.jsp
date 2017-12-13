<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>


<c:set var="resourceContext" value="${pageContext.request.contextPath}" />

<html lang="en">
<head>
    <title>Product List</title>
    <link rel="stylesheet" href="bootstrap.min.css">
    <script type="text/javascript" src="bootstrap.bundle.min.js"></script>
</head>
<body>
<div class="container-fluid">
    <div class="row">
        <div class="col-12">
            <div class="float-md-none" style="width: 200px; height: 20px; color: #fff;"></div>
            <div class="alert alert-secondary" role="alert">
                <span>Products</span>
                <span style="margin-left: 40%;">
                    <a href="/cart">Cart: </a>
                    <span class="badge badge-pill badge-warning">${cart}</span>
                </span>
            </div>
        </div>
    </div>
</div>


    <div class="action-box">
        <sec:authorize access="hasRole('ROLE_ANONYMOUS')">
            <a href="/user" class="btn btn-warning">Sign In</a>
            <a href="/login" class="btn btn-warning">Login</a>
        </sec:authorize>
        <sec:authorize access="hasRole('ROLE_ADMIN')">
        <a href="/admin" class="btn btn-warning">Create Admin</a>
        <a href="/discount" class="btn btn-warning">Discount List</a>
        </sec:authorize>
        <sec:authorize access="!hasRole('ROLE_ANONYMOUS')">
            <form action="/customLogout" method="post">
                <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                <input class="btn btn-warning" type="submit" value="Logout">
            </form>
            <a href="/history" class="btn btn-warning">Show purchase history</a>
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
                        <div class="card-header badge-primary">${prod.title}</div>
                        <div class="card-body text-primary">
                            <h6 class="card-title">${prod.description}</h6>
                            <h4 class="card-title">Balance: ${prod.balance}</h4>
                            <sec:authorize access="hasRole('ROLE_USER') or hasRole('ROLE_ADMIN') or hasRole('ROLE_ANONYMOUS')">
                                <a href="/cart?add&prodId=${prod.id}" class="btn btn-info">Add to Cart</a>
                                <a href="/product-view?prodId=${prod.id}" class="btn btn-info">Buy</a>
                                <a href="/product-view?prodId=${prod.id}" class="btn btn-info">View</a>
                            </sec:authorize>
                            <sec:authorize access="hasRole('ROLE_ADMIN')">
                                <a href="/product/${prod.id}" class="btn btn-warning" role="button">Edit</a>
                                <a href="/product?delete&prodId=${prod.id}" class="btn btn-danger">Delete</a>
                                <%--<a href="/getData" class="btn btn-danger">Set getData</a>--%>
                                <%--<form:form modelAttribute="discountForm" cssClass="search-box">--%>
                                    <%--Discount: <form:input path="getData" /> <br/>--%>
                                    <%--<form:input  path="getData" /> <br/>--%>
                                    <%--&lt;%&ndash;From: <form:input path="from" /> <br/>&ndash;%&gt;--%>
                                    <%--&lt;%&ndash;To: <form:input path="to" /> <br/>&ndash;%&gt;--%>
                                    <%--<input type="submit" class="btn btn-success" value="Set permanently" />--%>
                                <%--</form:form>--%>
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
            <a href="/product?deleteAll" class="btn btn-danger">DeleteAll</a>
        </sec:authorize>
    </div>

</div>
</body>
</html>
