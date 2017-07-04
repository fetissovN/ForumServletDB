<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Login</title>
        <style>
            <%@include file="/resources/css/foundation.min.css" %>
            <%@include file="/resources/css/app.css" %>
        </style>
    <script src="http://ajax.googleapis.com/ajax/libs/jquery/2.0.3/jquery.min.js"></script>
</head>

<body>

<div class="row">
    <div class="medium-6 medium-centered large-4 large-centered columns">
        <form action="/login" method="post">
            <div class="row column log-in-form">
                <h4 class="text-center">Log in with you email account</h4>
                <span style="color: red">${wrong}</span>

                <label>Nick
                    <input type="text" name="txt_nick" required>
                </label>

                <label>Email
                    <input type="email" name="txt_email" placeholder="somebody@example.com" required>
                </label>
                <input type="submit" class="button expanded" value="Log in">
                <p class="text-center"><a href="/register">Sign in!</a></p>
            </div>
        </form>
    </div>
</div>
</body>
</html>

