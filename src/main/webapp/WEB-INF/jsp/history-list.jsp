<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html lang="en">
<head>
    <title>History</title>
    <script type="text/javascript" src="${resourceContext}/bootstrap.bundle.min.js"></script>
    <link rel="stylesheet" href="${resourceContext}/bootstrap.min.css">
</head>
<body>

            <c:forEach items="${history}" var="historyItem">
                <h5>${historyItem.title}  (${historyItem.purchaseItemAmount})</h5>
                Date: <h5>${historyItem.date}</h5>
                Cost: <h5>${historyItem.cost}</h5>
                Total: <h5>${historyItem.countOfCost}</h5>



            </c:forEach>


            <div class="action-box">
                <a href="/products" class="btn btn-info" role="button">Back to products</a>
            </div>

</body>
</html>    