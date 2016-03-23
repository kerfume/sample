<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page isELIgnored="false"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="../css/mystyle.css" />

<title>login</title>
</head>
<body>
	<h1 class="left_noFlot">ログイン</h1>

	<div id="body">
		<s:actionerror />
		<s:fielderror cssStyle="color:#ff0000;"/>
		<s:form method="post" action="loginaction" theme="simple">
			<table class="wwFormTable">
				<tr>
					<td>ログインID ：</td>
					<td><s:textfield key="user_id" /></td>
				</tr>
				<tr>
					<td>パスワード ：</td>
					<td><s:password key="password" /></td>
				</tr>
			</table>
			<br />
			<center>
				<s:submit value="ログイン" cssClass="red" />
			</center>


		</s:form>
	</div>
</body>
</html>