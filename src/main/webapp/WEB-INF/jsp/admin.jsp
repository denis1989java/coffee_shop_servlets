<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Admin</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- Bootstrap -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
    <link href="${pageContext.request.contextPath}/resources/css/admin.css" rel="stylesheet" type="text/css">
</head>
<body>
<c:choose>
    <c:when test="${empty orders}">
        <div class="emptyList">
            <p>Sorry, no orders yet! Come later!</p>
            <a href="${pageContext.request.contextPath}/logout">Log out</a>
        </div>
    </c:when>
    <c:otherwise>
        <a class="logout"  href="${pageContext.request.contextPath}/logout">Log out</a>
        <table id="table" class="table table-hover mainInfo">
            <thead>
            <tr>
                <th>Address</th>
                <th>Name</th>
                <th>Phone</th>
                <th>Price</th>
                <th>Details</th>
                <th>Delivery Status</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="order" items="${orders}" varStatus="status">
                    <tr >
                        <td><c:out value="${order.address}"/></td>
                        <td><c:out value="${order.userName}"/></td>
                        <td><c:out value="${order.phoneNumber}"/></td>
                        <td><c:out value="${order.price}"/></td>
                        <td>
                            <table class="innerTable" style="min-width: 120px">
                                <c:forEach var="orderItem" items="${order.orderItemList}" varStatus="status">
                                    <tr>
                                        <td><c:out value="${orderItem.coffee.name}"/></td>
                                        <td class="text-right" style="min-width: 20px"><c:out value="${orderItem.quantity}"/></td>
                                    </tr>
                                </c:forEach>
                            </table>
                        </td>
                        <td>

                            <c:if test="${order.delivery  != 1}">
                                    <form action="${pageContext.request.contextPath}/admin" method="post">
                                        <input type="hidden" value="${order.id}" name="orderId"/>
                                        <input type="submit" value="Delivered"/>
                                    </form>
                            </c:if>
                        </td>
                    </tr>
            </c:forEach>
            </tbody>
        </table>
    </c:otherwise>
</c:choose>
</body>
</html>
