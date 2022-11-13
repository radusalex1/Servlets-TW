<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <script src="https://cdn.tailwindcss.com"></script>
</head>
<body>
    <div>
        <div>user:</div>
        <%String email = (String)request.getAttribute("email"); %>
        <%=email%>
        <br>
        <div>money spent:</div>
        <%String money = (String)request.getAttribute("moneyAmount"); %>
        <%=money%>
        <br>
        <div>money after discount:</div>
        <%String moneyAfterDiscount = (String)request.getAttribute("moneyAfterDiscount"); %>
        <%=moneyAfterDiscount%>
        <br>
        <div>user's level:</div>
        <%String level = (String)request.getAttribute("level"); %>
        <%=level%>
        <br>
        <div>user's points:</div>
        <%String points = (String)request.getAttribute("points"); %>
        <%=points%>
    </div>
</body>
</html>
