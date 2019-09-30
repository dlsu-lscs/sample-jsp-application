<%--
  Created by IntelliJ IDEA.
  User: jonalticug
  Date: 9/23/19
  Time: 9:54 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title> JSP | Products </title>
    <!-- LIBRARIES -->

    <!-- JQuery -->
    <script src="https://code.jquery.com/jquery-3.4.1.min.js"></script>

    <!-- Semantic -->
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/semantic-ui@2.4.2/dist/semantic.min.css">
    <script src="https://cdn.jsdelivr.net/npm/semantic-ui@2.4.2/dist/semantic.min.js"></script>

    <!-- Fonts -->
    <link href="https://fonts.googleapis.com/css?family=Be+Vietnam|Merriweather&display=swap" rel="stylesheet">

    <style>
        * {
            font-family: 'Be Vietnam', sans-serif !important;
        }

        body {
            width: 100%;
            height: 100%;
            position: absolute;
        }

        .ui.center-flex {
            width: 100%;
            height: 100%;
            display: flex;
            justify-content: center;
            align-items: center;
        }

        #main {
            margin: 100px 0px;
        }
    </style>
</head>
<body>
<div id="main" class = "ui container">
    <h1> PRODUCTS </h1>
    <a class = "ui green button" href="/products/add">
        ADD PRODUCT
    </a>
    <c:choose>
            <c:when test = "${param.deleted == 'true'}">
            <div class = "ui green message">
                Successfully deleted!
            </div>
        </c:when>
        <c:when test = "${param.deleted == 'false'}">
            <div class = "ui red message">
                There was an error deleting the product!
            </div>
        </c:when>
    </c:choose>
    <table class = "ui celled selectable padded table">
        <thead>
            <tr>
                <th> Code </th>
                <th> Name </th>
                <th> Quantity </th>
                <th> Buy Price </th>
                <th> MSRP </th>
                <th> Actions </th>
            </tr>
        </thead>
        <tbody>
            <c:forEach var="product" items="${products}">
                <tr>
                    <td>
                        <a href = "/products/${product.code}">
                                ${product.code}
                        </a>

                    </td>
                    <td> ${product.name} </td>
                    <td> ${product.quantity} </td>
                    <td> ${product.buyPrice} </td>
                    <td> ${product.MSRP} </td>
                    <td>
                        <form class = "ui form" method="POST" action="/products/delete">
                            <input type="hidden" name = "id" value="${product.code}"/>
                            <button class = "ui red button"> DELETE </button>
                        </form>
                    </td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
</div>
</body>
<script>

</script>
</html>
