<%--
  Created by IntelliJ IDEA.
  User: jonalticug
  Date: 9/23/19
  Time: 9:54 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title> JSP | Orders </title>
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
    <h1> ORDERS </h1>
    <a class = "ui green button" href="/orders/add">
        Make an Order
    </a>

    <c:choose>
        <c:when test = "${error}">
            <div class = "ui red message">
                ${error}
            </div>
        </c:when>
    </c:choose>
    <c:choose>
        <c:when test = "${param.deleted == 'true'}">
            <div class = "ui green message">
                Successfully deleted!
            </div>
        </c:when>
        <c:when test = "${param.deleted == 'false'}">
            <div class = "ui red message">
                There was an error deleting the order!
            </div>
        </c:when>
        <c:when test = "${param.created == 'true'}">
            <div class = "ui green message">
                Order placed!
            </div>
        </c:when>
        <c:when test = "${param.created == 'false'}">
            <div class = "ui red message">
                There was an error adding the order!
            </div>
        </c:when>

        <c:when test = "${param.updated == 'true'}">
            <div class = "ui green message">
                Order modified!
            </div>
        </c:when>
        <c:when test = "${param.updated == 'false'}">
            <div class = "ui red message">
                There was an error modifying the order!
            </div>
        </c:when>
    </c:choose>
    <table class = "ui celled selectable padded table">
        <thead>
        <tr>
            <th> Order Number </th>
            <th> Order Date </th>
            <th> Status </th>
            <th> Comments </th>
            <th> Item Count </th>
            <th> Action </th>
        </tr>
        </thead>
        <tbody>
            <c:forEach var="order" items="${orders}">
                <tr>
                    <td>
                        <a href = "/orders/${order.orderNumber}">
                                ${order.orderNumber}
                        </a>
                    </td>
                    <td> ${order.orderDate} </td>
                    <td> ${order.status} </td>
                    <td> ${order.comments} </td>
                    <td>
                        ${fn:length(order.orderDetails)}
                    </td>
                    <td>
                        <form class = "ui form" method="POST" action="/orders/delete">
                            <input type="hidden" name = "id" value="${order.orderNumber}"/>
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
