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


    <ul class="cart-list">
        <c:forEach items="${products}" var="prod">
            <li class="cart-item">
                <h3>${prod.product.title}</h3>
                <h3>${prod.product.description}</h3>
                <h3>${prod.countOfCost}</h3>
                <h3>${prod.countOfProducts}</h3>
                <a href="/cart?delete&prodId=${prod.product.id}" class="btn btn-danger" role="button">Delete</a>
            </li>
        </c:forEach>
    </ul>


    <h3>Total: ${total}</h3>


    <div class="action-box">
        <a href="/products" class="btn btn-info" role="button">Back to products</a>
        <a href="/cart?deleteAll" class="btn btn-danger" role="button">RemoveAll</a>
        <a href="/order?cart" class="btn btn-warning" role="button">Order</a>
    </div>


</div>
</body>
</html>
