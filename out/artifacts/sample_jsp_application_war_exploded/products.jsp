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
    <title> JSP | Home </title>
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
    <button class = "ui green button">
        ADD PRODUCT
    </button>
    <table class = "ui celled padded table">
        <thead>
            <tr>
                <th> Code </th>
                <th> Name </th>
                <th> Quantity </th>
                <th> Buy Price </th>
                <th> MSRP </th>
            </tr>
        </thead>
        <tbody>

        </tbody>
        <c:forEach var="product" items="${products}">
            <tr>
                <td> ${product.code} </td>
                <td> ${product.name} </td>
                <td> ${product.quantity} </td>
                <td> ${product.buyPrice} </td>
                <td> ${product.MSRP} </td>
            </tr>
        </c:forEach>
    </table>
</div>
</body>
<script>

</script>
</html>
