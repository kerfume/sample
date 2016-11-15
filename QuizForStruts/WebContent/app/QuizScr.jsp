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
<script type="text/javascript" src="./js/common.js"></script>
<script type="text/javascript" src="./js/check.js"></script>
<title>QUESTION!</title>

</head>
<body>
<div id="main">
<h1>問.${sessionScope.point}</h1>

<div id="conf">

<logic:equal name="quiz" property="errata" scope="session" value="1">
<div id="pin"><font size="5" color="#228b22">正解です！</font></div>
</logic:equal>
<logic:equal  name="quiz" property="errata" scope="session" value="0">
<div id="pin"><font size="5" color="#b22222">不正解です！</font></div>
</logic:equal>


<h3><bean:write name="quiz" property="question" scope="session"/></h3>
<br/><hr/>

<logic:equal name="quiz" property="errata" scope="session" value="-1">
	<html:form action="quizscraction.do" method="POST" >
	
		<html:radio property="ans" value="1">
		<bean:write name="quiz" property="ch1" scope="session"/>
		</html:radio><br/>
		<html:radio property="ans" value="2">
		<bean:write name="quiz" property="ch2" scope="session"/>
		</html:radio><br/>
		<html:radio property="ans" value="3">
		<bean:write name="quiz" property="ch3" scope="session"/>
		</html:radio><br/>
		<html:radio property="ans" value="4">
		<bean:write name="quiz" property="ch4" scope="session"/>
		</html:radio><br/><br/>
		
	 	<html:submit value="解答" onclick="return check()"/>	 
	</html:form>
</logic:equal>

<logic:notEqual name="quiz" property="errata" scope="session" value="-1">
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

<bean:define id="qmax" name="qmax" scope="session" type="java.lang.Integer"/>
<bean:define id="point" name="point" scope="session" type="java.lang.Integer"/>

<logic:notEqual name="qmax"  value="${sessionScope.point}">

<html:form action="ansscraction.do" method="POST">
	<html:submit value="次の問題へ"/>
</html:form>

</logic:notEqual>

<logic:equal name="qmax"  value="${sessionScope.point}">

<html:form action="ansscr2action.do" method="POST">
	<html:submit value="結果画面へ"/>
</html:form>

</logic:equal>

</logic:notEqual>

</div>

</div>
</body>
</html>