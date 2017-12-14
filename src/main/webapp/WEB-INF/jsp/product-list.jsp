<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>


<c:set var="resourceContext" value="${pageContext.request.contextPath}"/>

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
            <div class="float-md-none"
                 style="width: 200px; height: 20px; color: #fff;"></div>
            <div class="alert alert-secondary" role="alert" style="">
                <span>Products</span>
                <span style="margin-left: 40%;">
                    <a href="/cart">Cart: </a>
                    <span class="badge badge-pill badge-warning">${cart}</span>
                </span>
                <span class="float-right">
                    <sec:authorize access="!hasRole('ROLE_ANONYMOUS')">
                        <form action="/customLogout" method="post">
                            <input type="hidden" name="${_csrf.parameterName}"
                                   value="${_csrf.token}"/>
                            <input class="" type="submit" value="Logout">
                        </form>
                    </sec:authorize>
                </span>
            </div>
        </div>
    </div>
</div>


<div class="action-box">

</div>

<div class="center">

    <div class="container-fluid">
        <div class="card text-center">
            <div class="card-header">
                <ul class="nav nav-pills card-header-pills">
                    <sec:authorize access="hasRole('ROLE_ANONYMOUS')">
                        <li class="nav-item">
                            <a href="/user" class="nav-link active" style="margin-right:10px ;">Sign In</a>
                        </li>
                        <li class="nav-item">
                            <a href="/login" class="nav-link active">Login</a>
                        </li>
                    </sec:authorize>
                    <li class="nav-item">
                        <sec:authorize access="hasRole('ROLE_ADMIN')">
                            <a href="/admin" class="nav-link active">Create
                                Admin</a>
                        </sec:authorize>
                    </li>
                    <li class="nav-item" style="margin-left: 10px">
                        <sec:authorize access="hasRole('ROLE_ADMIN')">
                            <a href="/discount" class="nav-link active">Discount
                                List</a>
                        </sec:authorize>
                    </li>
                    <li class="nav-item">
                        <sec:authorize access="hasRole('ROLE_ANONYMOUS')">
                            <form:form modelAttribute="searchForm"
                                       cssClass="search-box form-inline">
                                <form:input path="searchText"
                                            placeholder="Search..."
                                            class="form-control"/>
                                <input type="submit" class="btn btn-success"
                                       style="margin-left: 10px;"
                                       value="Search"/>
                            </form:form>
                        </sec:authorize>
                    </li>
                    <li class="nav-item" style="margin-left: 10px">
                        <sec:authorize access="!hasRole('ROLE_ANONYMOUS')">
                            <a href="/history" class="nav-link active">Show
                                purchase history</a>
                        </sec:authorize>
                    </li>
                </ul>

            </div>

            <div class="card-body">
                <div class="row" style="justify-content: space-between">
                    <c:forEach items="${products}" var="prod">
                        <div class="col-5" style="margin: 20px;">
                            <div class="card border-primary"
                                 style="max-width: 650px;">
                                <div class="card-header badge-primary">${prod.title}</div>
                                <div class="card-body text-primary">
                                    <h6 class="card-title">${prod.description}</h6>
                                    <h4 class="card-title">
                                        Balance: ${prod.balance}</h4>
                                    <sec:authorize
                                            access="hasRole('ROLE_USER') or hasRole('ROLE_ADMIN') or hasRole('ROLE_ANONYMOUS')">
                                        <a href="/cart?add&prodId=${prod.id}"
                                           class="btn btn-info">Add to Cart</a>
                                        <a href="/product-view?prodId=${prod.id}"
                                           class="btn btn-info">Buy</a>
                                        <a href="/product-view?prodId=${prod.id}"
                                           class="btn btn-info">View</a>
                                    </sec:authorize>
                                    <sec:authorize
                                            access="hasRole('ROLE_ADMIN')">
                                        <a href="/productEdit/${prod.id}"
                                           class="btn btn-warning"
                                           role="button">Edit</a>
                                        <a href="/productEdit?delete&prodId=${prod.id}"
                                           class="btn btn-danger">Delete</a>
                                    </sec:authorize>
                                </div>
                            </div>
                        </div>
                    </c:forEach>
                </div>
                <div class="action-box float-right" style="margin-right: 23px ">
                    <sec:authorize access="hasRole('ROLE_ADMIN')">
                        <a href="/productEdit" class="btn btn-warning">New
                            product</a>
                        <a href="/productEdit?deleteAll"
                           class="btn btn-danger">DeleteAll</a>
                    </sec:authorize>
                </div>
            </div>


        </div>
    </div>
</div>
</body>
</html>
