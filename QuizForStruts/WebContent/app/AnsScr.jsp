<!-- 20160204_1画面化の為封印　 -->

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page isELIgnored="false" %><!--EL式を無視しない、という設定 -->
    
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="./css/mystyle.css" />

<title>ANSWER</title>
</head>
<body>
<div id="main">
<h1>問.${sessionScope.point}</h1>

<div id="conf">

<logic:equal name="quiz" property="errata" scope="session" value="1">
<p><font size="5" color="#ff6633">正解です！</font></p>
</logic:equal>
<logic:equal  name="quiz" property="errata" scope="session" value="0">
<p><font size="5" color="#b22222">不正解です！</font></p>
</logic:equal>


<bean:write name="quiz" property="question" scope="session"/>
<br/><hr/>
<logic:equal name="quiz" property="answer" scope="session" value="1">
<p><b><font color="#0000ff">◎:<bean:write name="quiz" property="ch1" scope="session"/></font></b></p>
</logic:equal>

<logic:notEqual name="quiz" property="answer" scope="session" value="1">
<p>☓:<bean:write name="quiz" property="ch1" scope="session"/></p>
</logic:notEqual>

<logic:equal name="quiz" property="answer" scope="session" value="2">
<p><b><font color="#0000ff">◎:<bean:write name="quiz" property="ch2" scope="session"/></font></b></p>
</logic:equal>

<logic:notEqual name="quiz" property="answer" scope="session" value="2">
<p>☓:<bean:write name="quiz" property="ch2" scope="session"/></p>
</logic:notEqual>

<logic:equal name="quiz" property="answer" scope="session" value="3">
<p><b><font color="#0000ff">◎:<bean:write name="quiz" property="ch3" scope="session"/></font></b></p>
</logic:equal>

<logic:notEqual name="quiz" property="answer" scope="session" value="3">
<p>☓:<bean:write name="quiz" property="ch3" scope="session"/></p>
</logic:notEqual>

<logic:equal name="quiz" property="answer" scope="session" value="4">
<p><b><font color="#0000ff">◎:<bean:write name="quiz" property="ch1" scope="session"/></font></b></p>
</logic:equal>

<logic:notEqual name="quiz" property="answer" scope="session" value="4">
<p>☓:<bean:write name="quiz" property="ch4" scope="session"/></p>
</logic:notEqual>

<html:form action="ansscraction.do" method="POST">
	<html:submit value="次の問題へ"/>
</html:form>
</div>

</div>

</body>
</html>