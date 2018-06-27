<%--
  Created by IntelliJ IDEA.
  User: tsvetozar
  Date: 27/06/18
  Time: 11:37
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Greeter</title>
</head>
<body>


<form action="/greeter" method="post">
    <input type="radio" name="page" value="one"> First page<br>
    <input type="radio" name="page" value="two"> Second page<br>
    <input type="radio" name="page" value="three"> Third page<br>
    <input type="submit" value="Greet">
</form>


</body>
</html>
