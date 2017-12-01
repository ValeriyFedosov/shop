<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html lang="en">
<head>
    <title>Order View</title>
    <link rel="stylesheet" href="${resourceContext}/bootstrap.min.css">
    <link rel="stylesheet" href="${resourceContext}/layout.css">
    <link rel="stylesheet" href="${resourceContext}/style.css">
    <script type="text/javascript" src="${resourceContext}/bootstrap.bundle.min.js"></script>
</head>
<body>

    Your order:

    <ul>
        <c:forEach items="${products}" var="prod">
           <li>
               ${prod.title}
               <br>
               ${prod.countOfCost}
                   <br>
               ${prod.countOfProducts}
           </li>
        </c:forEach>
    </ul>

    <a href="/products" class="btn btn-info" role="button">Back to products</a>


    <h3>${product}</h3>
</body>
</html>    