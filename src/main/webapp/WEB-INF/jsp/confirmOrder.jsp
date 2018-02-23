<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>ConfirmOrder</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- Bootstrap -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
    <link href="${pageContext.request.contextPath}/resources/css/confirmOrder.css" rel="stylesheet" type="text/css">
</head>
<body>
<div class="container">
    <a  href="${pageContext.request.contextPath}/order"><button class="back btn" type="button">Change order</button></a>
    <form action="${pageContext.request.contextPath}/confirmOrder" method="post">
        <table id="table" class="table table-hover">
            <thead>
            <tr>
                <th>Name</th>
                <th>Description</th>
                <th>Quantity</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="orderItem" items="${order.orderItemList}" varStatus="status">
                <tr id="${status.index}">
                    <td><c:out value="${orderItem.coffee.name}"/></td>
                    <td><c:out value="${orderItem.coffee.description}"/></td>
                    <td><c:out value="${orderItem.quantity}"/></td>
                </tr>
            </c:forEach>
            <tr>
                <td colspan="3">
                    <c:out value="Total order price: "/>
                    <c:out value="${order.price}"/>
                </td>
            </tr>
            </tbody>
        </table>

        <label for="name">Name</label>
        <input type="text" class="form-control" id="name" name="name" placeholder="name" max="50" pattern="^[a-zA-Zа-яА-Я'][a-zA-Zа-яА-Я-' ]+[a-zA-Zа-яА-Я']?$" required><br>
        <label for="address">Address</label>
        <input type="text" class="form-control" id="address" name="address" placeholder="address" max="100" required><br>
        <label for="phone">Phone number</label>
        <input type="text" class="form-control" id="phone" name="phone" pattern="[\+]375\d{9}" placeholder="+375296522540" title="must start +375 and 9 simbols after" required><br>
        <input type="submit"  class="submit-button" value="Confirm order"/>

    </form>
</div>

</body>
</html>
