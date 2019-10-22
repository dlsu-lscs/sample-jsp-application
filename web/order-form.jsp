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
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/semantic-ui/2.4.1/semantic.min.css">
    <script src="https://cdnjs.cloudflare.com/ajax/libs/semantic-ui/2.4.1/semantic.min.js"></script>

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

        #product-list {
            height: 60vh;
            overflow-y: scroll;
            padding-bottom: 20px;
            margin-bottom: 20px;
        }
    </style>
</head>
<body>
<div id = "main" class = "ui container">
    <h3> Order Form </h3>
    <c:choose>
        <c:when test="${action == 'CREATE'}">
            <div id = "product-list" class = "ui three centered cards">
                <c:forEach var="product" items="${products}">
                    <form class = "ui card add-product-form" method="POST" action="/orders/add-product">
                        <input type="hidden" name = "lineitem[code]" value="${product.code}">
                        <input type="hidden" name = "lineitem[orderno]" value="${order.orderNumber}">
                        <div class="content">
                            <div class="header"> ${product.name} </div>
                            <div class = "meta">
                                $ ${product.buyPrice}
                            </div>
                        </div>
                        <div class = "content ui form">
                            <div class = "ui field">
                                <label> Quantity </label>
                                <input type="number" name="lineitem[quantity]">
                            </div>
                        </div>
                        <button class = "ui green button"> Buy </button>
                    </form>
                </c:forEach>
            </div>
        </c:when>
    </c:choose>
    <form class = "ui form" method="POST" action="/orders/upsert">
        <input type="hidden" value="${order.orderNumber}" name="order[orderNumber]"/>
        <div class = "field">
            <c:choose>
                <c:when test="${action == 'CREATE'}">
                    <label> Customer </label>
                    <div id = "customer" class="ui fluid search selection dropdown">
                        <input type="hidden" name="order[customerNumber]">
                        <div class="default text">Select Customer</div>
                        <div class="menu">
                            <c:forEach var = "customer" items="${customers}" >
                                <div class = "item" data-value="${customer.customerNumber}">
                                        ${customer.customerName}
                                </div>
                            </c:forEach>
                        </div>
                    </div>
                </c:when>

                <c:when test="${action == 'VIEW'}">
                    <label> Customer </label>
                    <input type="text" value="${order.customer.customerName}" class = "uneditable" name="order[customerName]"/>
                </c:when>
            </c:choose>
        </div>
        <div class = "ui three fields">
            <div class = "field">
                <label> Order Date </label>
                <input type="date" value="${order.orderDate}" name="order[orderDate]"/>
            </div>
            <div class = "field">
                <label> Required Date </label>
                <input type="date" value="${order.requiredDate}" name="order[requiredDate]"/>
            </div>
            <div class = "field">
                <label> Required Date </label>
                <input type="date" value="${order.shippedDate}" name="order[shippedDate]"/>
            </div>
        </div>
        <div class = "field">
            <label> Status </label>
            <input type="text" value="${order.status}" name="order[status]"/>
        </div>

        <div class = "field">
            <label> Comments </label>
            <textarea name="order[comments]">${order.comments}</textarea>
        </div>

        <div class = "ui basic segment">
            <h4> Products Bought </h4>
            <table class = "ui padded table">
                <thead>
                <tr>
                    <th> Name </th>
                    <th> Quantity </th>
                    <th> Price </th>
                    <th> Total </th>
                </tr>
                </thead>
                <tbody>
                <c:forEach var="lineitem" items="${order.orderDetails}">
                <tr>
                    <td> ${lineitem.product.name} </td>
                    <td> ${lineitem.quantityOrdered} </td>
                    <td> $ ${lineitem.priceEach} </td>
                    <td> $ ${lineitem.total} </td>
                </tr>
                </c:forEach>
                <tr>
                    <td> </td>
                    <td> </td>
                    <td> </td>
                    <td> $ ${order.finalPrice} </td>
                </tr>
                <tbody>
            </table>
        </div>
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
    <form method="POST" action="/orders/reset">
        <button class = "ui red button"> RESET </button>
    </form>
</div>
</body>


<script>
    function getFormData($form){
        var unindexed_array = $form.serializeArray();
        var indexed_array = {};

        $.map(unindexed_array, function(n, i){
            indexed_array[n['name']] = n['value'];
        });

        return indexed_array;
    }

    $(".add-product-form").submit(function(){
        var data = getFormData($(this))
        if (data["lineitem[quantity]"] === "" || data["lineitem[quantity]"] === 0) {
            alert("Quantity is empty or 0");
            return false;
        }
        return true;
    });

    $("#customer").dropdown();
    $("#edit").on('click', function(e){
        e.preventDefault()
        $("#edit").prop("disabled", true)
        $("input:not(.uneditable)").prop("readonly", false)
        $("textarea").prop("readonly", false)
        $("#productCode").prop("disabled", true)
        $("#save").prop("disabled", false)
    })
</script>
</html>
