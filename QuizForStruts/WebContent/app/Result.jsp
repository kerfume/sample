<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page isELIgnored="false" %>
    
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="./css/mystyle.css" />
<script type="text/javascript" src="./js/common.js"></script>

<title>RESULT</title>
</head>
<body>
<div id="main">
	<h1>結果</h1>
<div id="conf">
<h3>正解数：${sessionScope.correctnum }/${sessionScope.qmax }</h3><br/>
<html:form action="result.do" method="POST">
	<html:submit value="終了"/>
</html:form>
</div>
</div>

</body>
</html>