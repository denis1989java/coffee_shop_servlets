<%--
  Created by IntelliJ IDEA.
  User: dmonich
  Date: 22.12.2017
  Time: 15:31
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Order</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- Bootstrap -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
    <script src="${pageContext.request.contextPath}/resources/js/order.js"></script>
    <link href="${pageContext.request.contextPath}/resources/css/coffeeList.css" rel="stylesheet" type="text/css">
</head>
<body>
<div class="container">
    <button class="back btn" onclick="back()" type="button" >Add Other Coffee</button>
    <form name="formSend" action="${pageContext.request.contextPath}/order" method="post">
        <table id="table" class="table table-hover">
            <thead>
            <tr>
                <th>Name</th>
                <th>Description</th>
                <th>Price</th>
                <th>Quantity</th>
                <td>Amount</td>
                <th>Remove</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="orderItem" items="${order.orderItemList}" varStatus="status">
                <tr id="${status.index}">
                    <td><c:out value="${orderItem.coffee.name}"/></td>
                    <td><c:out value="${orderItem.coffee.description}"/></td>
                    <td><c:out value="${orderItem.coffee.price}"/></td>
                    <td><input onclick="newPrice(this,${orderItem.coffee.price})" type="number" value="${orderItem.quantity}" name="coffeeQuantity" min="1">
                    <input type="hidden" value="${orderItem.coffee.id}" name="coffeeId"/></td>
                    <td name="amount"><c:out value="${orderItem.quantity * orderItem.coffee.price}"/></td>
                    <td>
                        <button onclick="deleteItem(this,event)" value="${status.index}"  type="button"  class="btn">Remove</button>
                    </td>
                </tr>
            </c:forEach>
            <tr>
                <td colspan="4" style="text-align: right">Total Amount</td>
                <td colspan="2" id="total"></td>
            </tr>
            </tbody>

        </table>
        <button onclick="putToLocalStorage(event)" class="submit-button">Submit</button>
        <input type="hidden" name="delete" id="delete">
        <input type="hidden" name="goBack" id="goBack">
        <input type="hidden" name="toSendCoffeeId" id="toSendCoffeeId" />
        <input type="hidden" name="toSendQuantity" id="toSendQuantity" />
    </form>
</div>
<script>
    document.addEventListener("DOMContentLoaded", totalPrice);
    function newPrice(q,p) {
        q.parentNode.parentNode.childNodes[9].innerHTML=parseInt(q.value) * parseInt(p);
        totalPrice()
    }
    function totalPrice() {
        let amounts=document.getElementsByName("amount");
        let total=0;
        for (let i=0;i<amounts.length;i++){
            total=total+parseInt(amounts[i].innerHTML);
        }
        let tot=document.getElementById("total");
        tot.innerHTML=total;
    }
</script>
</body>
</html>
