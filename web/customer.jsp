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
    <title> JSP | Customers </title>
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
                There was an error deleting the customer !
            </div>
        </c:when>
        <c:when test = "${param.created == 'true'}">
            <div class = "ui green message">
                Customer added to list!
            </div>
        </c:when>
        <c:when test = "${param.created == 'false'}">
            <div class = "ui red message">
                There was an error adding the customer!
            </div>
        </c:when>

        <c:when test = "${param.updated == 'true'}">
            <div class = "ui green message">
                Customer modified!
            </div>
        </c:when>
        <c:when test = "${param.updated == 'false'}">
            <div class = "ui red message">
                There was an error modifying the product!
            </div>
        </c:when>
    </c:choose>
    <h1 class = "ui center aligned header">
        CUSTOMERS
    </h1>
    <a href = "/customers/add" class = "ui icon blue button">
        Add a Customer
    </a>
    <table class = "ui celled padded table">
        <thead>
        <tr>
            <th> ID# </th>
            <th> Name </th>
            <th> Contact Last Name </th>
            <th> Contact First Name </th>
            <th> Country </th>
            <th> Actions </th>
        </tr>
        </thead>
        <tbody>

        <c:forEach var="customer" items="${customers}">
        <tr>
            <td>
                <a href="/customers/${customer.customerNumber}">
                    ${customer.customerNumber}
                </a>

            </td>
            <td> ${customer.customerName} </td>
            <td> ${customer.contactLastName} </td>
            <td> ${customer.contactFirstName} </td>
            <td> ${customer.country} </td>
            <td>
                <form class = "ui form" method="POST" action="/customers/delete">
                    <input type="hidden" name = "id" value="${customer.customerNumber}"/>
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