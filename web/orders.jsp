<%--
  Created by IntelliJ IDEA.
  User: jonalticug
  Date: 9/23/19
  Time: 9:54 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
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
    </style>
</head>
<body>
<div id="main" class = "ui center-flex">
    <div id = "center-container " class="ui center aligned basic segment">
        <h2 class = "ui header">
            What would you like to do today?
        </h2>
        <p>  This Application is written in JSP and Java Servlets. </p>
        <div class="ui basic segment">
            <a href="/customers" class="ui green button">
                MANAGE CUSTOMERS
            </a>
            <a href="/products" class = "ui green button">
                MANAGE PRODUCTS
            </a>
            <a href="/orders" class="ui green button">
                MANAGE ORDERS
            </a>
        </div>
    </div>
</div>
</body>
<script>

</script>
</html>
