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
            margin: 100px 0px;
        }
    </style>
</head>
<body>
<div id = "main" class = "ui container">
    <h3> Customer Form </h3>
    <form class = "ui form" method="POST" action="/customers/upsert">
        <input type="hidden" value="${customer.customerNumber}" name="customer[customerNumber]"/>
        <div class = "field">
            <label> Customer Name </label>
            <input type="text" value = "${customer.customerName}" name="customer[customerName]"/>
        </div>
        <div class = "field">
            <div class = "two fields">
                <div class = "field">
                    <label> Contact Last Name </label>
                    <input type="text" value="${customer.contactLastName}" name="customer[contactLastName]"/>
                </div>
                <div class = "field">
                    <label> Contact First Name </label>
                    <input type="text" value="${customer.contactFirstName}" name="customer[contactFirstName]"/>
                </div>
            </div>
        </div>
        <div class = "field">
            <label> Phone Number </label>
            <input type="text" value = "${customer.phone}" name="customer[phone]"/>
        </div>
        <div class = "field">
            <div class = "two fields">
                <div class = "field">
                    <label> Address Line 1 </label>
                    <input type="text" value = "${customer.addressLine1}" name="customer[addressLine1]"/>
                </div>
                <div class = "field">
                    <label> Address Line 2 </label>
                    <input type="text" value = "${customer.addressLine2}" name="customer[addressLine2]"/>
                </div>
            </div>
        </div>
        <div class = "field">
            <div class = "two fields">
                <div class = "field">
                    <label> City </label>
                    <input type="text" value = "${customer.city}" name="customer[city]"/>
                </div>
                <div class = "field">
                    <label> Country </label>
                    <input type="text" value = "${customer.country}" name="customer[country]"/>
                </div>
            </div>
        </div>

        <div class = "field">
            <div class = "two fields">
                <div class = "field">
                    <label> State </label>
                    <input type="text" value = "${customer.state}" name="customer[state]"/>
                </div>
                <div class = "field">
                    <label> Postal Code </label>
                    <input type="text" value = "${customer.postalCode}" name="customer[postalCode]"/>
                </div>
            </div>
        </div>
        <div class = "field">
            <label> Credit Limit </label>
            <input type="text" value = "${customer.creditLimit}" name="customer[creditLimit]"/>
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
