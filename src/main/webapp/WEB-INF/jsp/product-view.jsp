<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<html lang="en">
<head>
    <title>Product View</title>
    <link rel="stylesheet" href="${resourceContext}/bootstrap.min.css">
    <link rel="stylesheet" href="${resourceContext}/layout.css">
    <link rel="stylesheet" href="${resourceContext}/style.css">
    <script type="text/javascript" src="${resourceContext}/bootstrap.bundle.min.js"></script>
</head>
<body>
<div class="container-fluid">
    <div class="row">
        <div class="col-12">
            <div class="float-md-none" style="width: 200px; height: 20px; color: #fff;"></div>
            <div class="alert alert-secondary" role="alert">
                <span>View</span>
                <span class="badge badge-pill badge-warning float-right">${cart}</span>
            </div>
        </div>
    </div>
</div>


        <br/>
        <br/>


    ${product.title}
    <br/>
    ${product.description}
    <br/>
    Balance: ${product.balance}
    <br/>
    Cost:${product.cost}
    <br/>
    <img src="${product.imageName}" />
    <br/>
    <h3>${runout}</h3>

    <div class="action-box">
        <a href="/cart?add&prodId=${product.id}" class="btn btn-info" role="button">Add to Cart</a>
        <a href="/order?single&prodId=${product.id}" class="btn btn-info" role="button">Buy</a>
    </div>

    <div class="action-box">
        <a href="/products" class="btn btn-info" role="button">Back to products</a>
    </div>


</body>
</html>    