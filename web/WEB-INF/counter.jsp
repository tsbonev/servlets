<%--
  Created by IntelliJ IDEA.
  User: tsvetozar
  Date: 27/06/18
  Time: 10:00
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Page counter</title>
</head>
<body>

<a href="counter?link=1">Link 1 </a>
<br>
<b>${linkOneCount}</b>
<br>
<a href="counter?link=2">Link 2 </a>
<br>
<b>${linkTwoCount}</b>
<br>
<a href="counter?link=3">Link 3 </a>
<br>
<b>${linkThreeCount}</b>

</body>
</html>
