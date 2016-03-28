<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page isELIgnored="false"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="../css/mystyle.css" />
<title>MANUAL_IN</title>
</head>
<body>

	<h2 class="left">マニュアル取り込み</h2>
	<div id="right_common">
		<s:form method="post" action="useraction" theme="simple"
			style="display: inline">
			<s:submit cssClass="red" value="メニューに戻る" method="back" />
		</s:form>
	</div>
	<br />
	<br />
	<div id="body_m_in">
		<s:actionmessage/>
		<s:actionerror />
		<s:fielderror cssStyle="color:#ff0000;" />
		<s:form method="post" action="manualinaction" theme="simple"
			enctype="multipart/form-data">
			<table id="m_i" class="wwFormTable">
				<tr>
					<td id="waku">部署：</td>
					<td id="waku"><s:select id="department" name="department"
							list="getDptlist()" /></td>
				</tr>
				<tr>
					<td id="waku">グループ：</td>
					<td id="waku"><s:select id="group" name="group"
							list="getGrplist()" /></td>
				</tr>
				<tr>
					<td id="waku" width="200">エクセルファイル：</td>
					<td id="waku"><s:file name="xlsfile" id="xlsfile" /></td>
				</tr>
				<tr>
					<td colspan="2" id="waku"><s:textarea name="message" cols="50"
							rows="6" /></td>
					<td valign="bottom"><s:submit value="取り込み" cssClass="red" method="insert" /></td>
				</tr>

			</table>
		</s:form>
	</div>

	<script
		src="http://ajax.googleapis.com/ajax/libs/jquery/1.7.0/jquery.min.js"></script>

	<script src="../js/useredit.js"></script>
</body>
</html>