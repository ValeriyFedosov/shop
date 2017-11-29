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
<a href="/cart" class="btn btn-danger" role="button">Cart</a>
<div class="container-fluid">
    <div class="row">
        <div class="col-12">
            <div class="float-md-none" style="width: 200px; height: 20px; color: #fff;">asdasda</div>
            <div class="alert alert-secondary" role="alert">
                <span>asdasd</span>
                <span class="badge badge-pill badge-warning float-right">151</span>
            </div>
        </div>
    </div>
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
                            <p class="card-text">Some quick example text to build on the card title and make up the bulk of the card's content.</p>
                            <a href="/cart?add&prodId=${prod.id}" class="btn btn-info" role="button">Add to Cart</a>
                            <a href="/product-view?prodId=${prod.id}" class="btn btn-info" role="button">Buy</a>
                            <a href="/product/${prod.id}" class="btn btn-warning" role="button">Edit</a>
                        </div>
                    </div>
                </div>
            </c:forEach>
        </div>
    </div>

    <div class="action-box float-right">
        <a href="/product" class="btn btn-warning" role="button">New product</a>
    </div>

</div>
</body>
</html>
