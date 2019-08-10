<%@ page import="cs472.w3d5.model.SessionData" %><%--
  Created by IntelliJ IDEA.
  User: alicanozer
  Date: 2019-08-09
  Time: 14:29
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>NumberQuiz</title>
</head>
<body>
<%SessionData sdata=(SessionData)session.getAttribute("sdata"); %>
<form method='post' action="QuizJSPServlet">
    <h3>Have fun with NumberQuiz!</h3>
    <p>Your current score is <%= sdata.score %>.</p>
    <p>Attempt <%= sdata.attempt %> / 3 </p>
    <p>Enter the answer for the given equation! <br/><br/>
            <%= sdata.currentQuestion %> </p>

    <p>Your answer:<input type='text' name='txtAnswer' value=''/></p>
    <p><input type='submit' name='btnNext' value='Next'/>
        <input type='submit' name='btnRestart' value='Restart!'/>
    </p>
    <%if (sdata.error && sdata.answer != null) {%>
    <%if (sdata.attempt == 3) {%>
        <p style='color:red'>No more Attempt! Correct answer is <%=sdata.hint%></p>
        <% sdata.attempt = 0; %>
    <%} else{%>
        <p style='color:red'>Your last answer was not correct! Please try again</p>
    <%}}%>
    <p><input type="button" name='btnHint' value='Hint?' onclick="alert('<%= sdata.hint %>')"/>
    </p>

</form>
</body>
</html>
