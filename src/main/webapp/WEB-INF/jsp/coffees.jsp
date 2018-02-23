<%@ page contentType="text/html; charset = UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <title>CoffeeList</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- Bootstrap -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
    <script src="${pageContext.request.contextPath}/resources/js/coffees.js"></script>
</head>
<body>
<a style="float: right;margin-right: 20px;margin-top: 20px"  onclick="sendToPost()" class="btn btn-info btn-lg">
    <span class="glyphicon glyphicon-shopping-cart"></span>&nbsp;<span id="basket"></span>
</a>
<div class="container">

    <form action="${pageContext.request.contextPath}/coffeeList" method="post">
    <table class="table table-hover">
        <thead>
        <tr>
            <th>Name</th>
            <th>Description</th>
            <th>Price</th>
            <th>Quantity</th>
            <th>Add</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="coffee" items="${coffees}" varStatus="status">
            <tr>
                <td><c:out value="${coffee.name}"/></td>
                <td><c:out value="${coffee.description}"/></td>
                <td><c:out value="${coffee.price}"/></td>
                <td style="width: 200px"><input type="number" id="${coffee.id}" name="quantity" value="1" min="1">
                </td>
                <td style="min-width: 100px">
                    <button onclick="addQuantity(this,event)" type="button" value="${coffee.id}" class="btn">Add</button>
                    <i style="color: green; opacity: 0" class="glyphicon glyphicon-ok-sign"></i>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
    <input onclick="check(event)" class="submit-button" type="submit" value="Order"/>
        <input type="hidden" name="toSendCoffeeId" id="toSendCoffeeId" />
        <input type="hidden" name="toSendQuantity" id="toSendQuantity" />
        <input type="hidden" name="added" id="added" >
    </form>
</div>
</body>
<script>
    var f='${length}';
    if(f==="")f=0;
    document.getElementById("basket").innerHTML=f;
    function sendToPost() {
        let basket=document.getElementById("basket");
        let d=basket.innerHTML;
        if(parseInt(d)===0){
            alert('No items have been selected');
        }else{
            document.getElementsByTagName("form")[0].submit();
        }
        let added=document.getElementById("added");
        added.value="";
    }
</script>
</html>