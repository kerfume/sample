<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="./css/mystyle.css" />
<script type="text/javascript" src="./js/common.js"></script>

<title>TOP</title>
</head>
<body>
<div id="main">
	<h1>基本情報技術者試験_クイズ</h1>
	<div id="conf">
	<html:form action="topaction.do" method="POST">
		<p>★ 問題数: <html:select property="qmax">
	 		<html:option value="5" >5</html:option>
	 		<html:option value="10" >10</html:option>
	 		<html:option value="20" >20</html:option>
	 	</html:select></p>
	 	<html:hidden property="hid" value="borara"/>
	 <br/>
	 <html:submit value="開始"/>	 
	</html:form>
	</div>
	</div>
</body>
</html>