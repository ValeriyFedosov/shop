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
    ${product.title}
    <br/>
    ${product.description}
    <br/>
    ${product.balance}
    <br/>
    ${product.cost}
    <br/>
    ${product.imageMimeType}
    <br/>
    ${product.image}


    <div class="action-box">
        <a href="/cart?add&prodId=${prod.id}" class="btn btn-info" role="button">Add to Cart</a>
        <a href="/order?single&prodId=${prod.id}" class="btn btn-info" role="button">Buy</a>
    </div>

    <div class="action-box">
        <a href="/products" class="btn btn-info" role="button">Back to products</a>
    </div>



    


</body>
</html>    