<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page isELIgnored="false"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="../css/mystyle.css" />
<title>MENU</title>
</head>
<body>
	<h1 class="left">メニュー</h1>
	<div id="right">
		<s:form method="post" action="menuaction" theme="simple"
			style="display: inline">
			<s:submit cssClass="red" value="ログアウト" method="logoff" />
		</s:form>
		<div id="body_r" style="display: inline">
			企業ID ： ${sessionScope.ub.company_id}<br /> 企業名 ：
			${sessionScope.ub.company_name}<br /> ユーザーID ：
			${sessionScope.ub.user_id}<br /> ユーザー名 ： ${sessionScope.ub.user_name}<br />
		</div>
	</div>
	<br />
	<br />
	<br />

	<div id="body">
		<center>
			<s:form method="post" action="manualinaction" theme="simple">
				<s:submit cssClass="blue_bymenu" value="マニュアルデータ取り込み" />
			</s:form>
			<br /> <br />
			<s:form method="post" action="manualeditaction" theme="simple">
			<s:submit cssClass="blue_bymenu" value="マニュアルデータ修正" />
			</s:form>
			<br /> <br />
			<s:form method="post" action="useraction" theme="simple">
			<s:submit cssClass="blue_bymenu" value="ユーザー登録・削除" />
				</s:form>
			<br />
		</center>


	</div>

</body>
</html>