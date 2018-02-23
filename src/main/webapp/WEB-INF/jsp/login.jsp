<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <meta charset="utf-8">
    <title>Login</title>
    <!-- Bootstrap -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
    <link href="${pageContext.request.contextPath}/resources/css/login.css" rel="stylesheet">
</head>
<body>
<div id="feedback"></div>
<div  class="container-fluid">

     <div class="row center-block">
        <div class="col-md-5"></div>
        <div class="col-md-2">
            <form action="${pageContext.request.contextPath}/login" id="login-form" method="post">
                <div class="form-group">
                    <label for="userEmail">Log in</label>
                    <input type="text" class="form-control" id="userEmail" name="userEmail" placeholder="Login">
                </div>
                <div class="form-group">
                    <label for="userPassword">Password</label>
                    <input type="password" class="form-control" id="userPassword" name="userPassword" placeholder="Password">
                </div>
                <button type="submit" id="login-button" class="btn btn-default">Log in</button>
            </form>
        </div>
        <div class="col-md-5"></div>
    </div>
</div>
<script>

</script>
</body>
</html>