<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: jonalticug
  Date: 9/23/19
  Time: 10:06 PM
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
            margin: 30px 50px;
        }
    </style>
</head>
<body>
<div id = "main" class = "ui container">
    <h1 class = "ui center aligned header">
        CUSTOMERS
    </h1>
    <div class="ui basic segment">
        <button class = "ui icon blue button">
            Add a Customer
        </button>
    </div>
    <div class="ui basic segment">
        <table class = "ui celled table">
            <thead>
            <tr>
                <th> ID# </th>
                <th> Name </th>
                <th> Country </th>
            </tr>
            </thead>
            <tbody>

            <c:forEach var="customer" items="${customers}">
            <tr>
                <td> ${customer.customerNumber} </td>
                <td> ${customer.customerName} </td>
                <td> ${customer.country} </td>
            </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
</div>
</body>
<script>

</script>
</html>