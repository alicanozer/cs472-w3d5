<%@ page import="cs472.w3d5.model.SessionData" %><%--
  Created by IntelliJ IDEA.
  User: alicanozer
  Date: 2019-08-09
  Time: 21:19
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Number Quiz (JSP Edition)</title>
</head>
<body>
<%SessionData sdata=(SessionData)session.getAttribute("sdata"); %>
<form name='QuizJSPServlet' method='get'>
    <h3>Game Over</h3>
    <p>Your current score is <%= sdata.score *10%>.</p>
    <p>Your Grade is <font style='color:red'><h3><%= sdata.grade %></h3></font></p>
    <p><input type='submit' name='btnStart' value='Start Over!' /></p>
</form>
</body>
</html>
