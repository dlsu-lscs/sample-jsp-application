<%--
  Created by IntelliJ IDEA.
  User: jonalticug
  Date: 9/30/19
  Time: 11:41 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
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
<div id = "main" class = "ui container">
    <h3> Order Form </h3>
    <form class = "ui form" method="POST" action="/orders/upsert">
        <input type="hidden" value="${order.orderNumber}" name="order[orderNumber]"/>
        <div class = "field">
            <label> Order Date </label>
            <input type="date" value="${order.orderDate}" name="order[orderDate]"/>
        </div>
        <div class = "field">
            <input type="date" value="${order.orderDate}" name="order[orderDate]"/>
        </div>
<%--        <div class = "field">--%>
<%--            <label> Quantity </label>--%>
<%--            <input type="number" value="${product.quantity}" name="product[quantity]"/>--%>
<%--        </div>--%>

<%--        <div class = "field">--%>
<%--            <div class = "two fields">--%>
<%--                <div class = "field">--%>
<%--                    <label> MSRP </label>--%>
<%--                    <input type="number" value="${product.MSRP}" name="product[MSRP]"/>--%>
<%--                </div>--%>
<%--                <div class = "field">--%>
<%--                    <label> Buy Price </label>--%>
<%--                    <input type="number" value="${product.buyPrice}" name="product[buyPrice]"/>--%>
<%--                </div>--%>
<%--            </div>--%>
<%--        </div>--%>
        <c:choose>
            <c:when test="${action == 'VIEW'}">
                <button id = "edit" class = "ui blue button">
                    EDIT
                </button>
                <button disabled="" id = "save" class = "ui green button">
                    SAVE
                </button>
                <script>
                    $("input").prop("readonly", true)
                    $("textarea").prop("readonly", true)
                    $("#save").prop("disabled", true)
                </script>
            </c:when>

            <c:when test="${action == 'CREATE'}">
                <button class = "ui green button">
                    CREATE
                </button>
            </c:when>
        </c:choose>
    </form>
</div>
</body>


<script>
    $("#edit").on('click', function(e){
        e.preventDefault()
        $("#edit").prop("disabled", true)
        $("input").prop("readonly", false)
        $("textarea").prop("readonly", false)
        $("#productCode").prop("disabled", true)
        $("#save").prop("disabled", false)
    })
</script>
</html>
