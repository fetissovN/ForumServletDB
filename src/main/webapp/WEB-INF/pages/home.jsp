<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Home</title>
    <style>
        <%@include file="/resources/css/foundation.min.css" %>
        <%@include file="/resources/css/app.css" %>
    </style>
    <script src="http://ajax.googleapis.com/ajax/libs/jquery/2.0.3/jquery.min.js"></script>

</head>
<body>
<div class="row">
    <div class="large-3 column">
        <h1>Home</h1>
        <a href="/login">login</a>
    </div>
    <div class="large-6 large-centered column">
        <form action="/" method="post">
            <div class="row column log-in-form">
                <span style="color: red">${wrong}</span>
                <label>Nick
                    <input type="text" name="txt_message" required>
                </label>
                <input type="submit" class="button expanded" value="Send">
            </div>
        </form>
        <c:forEach items="${list}" var="message">
        <div class="article-row-content">
            <h4 class="article-row-content-header">${message.message}</h4>
            <p class="article-row-content-description">${sessionScope.get("user").nick}</p>
            <p class="article-row-content-author">${message.message_date}</p>
        </c:forEach>
</div>
    <div class="large-3 column"></div>
</div>
</div>
</body>
</html>
