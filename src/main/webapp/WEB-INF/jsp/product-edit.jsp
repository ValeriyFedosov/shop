<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<c:set var="resourceContext" value="${pageContext.request.contextPath}" />

<html lang="en">
<head>
    <title>Edit Product</title>
    <link rel="stylesheet" href="${resourceContext}/bootstrap.min.css">
    <script type="text/javascript" src="${resourceContext}/bootstrap.bundle.min.js"></script>
</head>
<body style="background-color: cornflowerblue;">
<div class="center">
    <div class="container" style="margin-top: 150px">
        <div class="row">
        <div class="col-3"></div>
        <div class="col-6">
            <form:form modelAttribute="product" enctype="multipart/form-data">
                <div class="form-group">
                    <fieldset>
                        <form:label path="title"><h2>Title:</h2></form:label>
                        <form:input path="title" cssClass="form-control form-control-lg"/>
                        <br />

                            <form:label path="description"><h2>Description:</h2></form:label>
                        <form:input path="description" cssClass="form-control form-control-lg"/>
                        <br />

                            <form:label path="cost"><h2>Cost:</h2></form:label>
                        <form:input path="cost" cssClass="form-control form-control-lg"/>
                        <br />

                            <form:label path="balance"><h2>Balance:</h2></form:label>
                        <form:input path="balance" cssClass="form-control form-control-lg"/>
                        <br/>
                            <form:form path="file"><h2>Please select a file to upload</h2></form:form>
                            <label class="custom-file">
                                <input type="file" name="img" class="custom-file-input form-control-lg"/>
                                <span class="custom-file-control form-control-lg"></span>
                            </label>
                        <br/>
                        <input type="submit" value="Save" class="btn btn-success btn-lg float-right" style="margin-top: 10px"/>
            </div>
            </form:form>
        </div>
        <div class="col-2"></div>
        </div>
    </div>


                <h3>${error}</h3>

</div>
</body>
</html>
