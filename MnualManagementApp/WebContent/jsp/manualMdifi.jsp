<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page isELIgnored="false"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="../css/mystyle.css" />
<title>MANUAL_MODIFI</title>
</head>
<body>
	<h2 class="left">マニュアル編集</h2>
	<div id="right_common">
		<s:form method="post" action="useraction" theme="simple"
			style="display: inline">
			<s:submit cssClass="red" value="メニューに戻る" method="back" />
		</s:form>
	</div>
	<br />
	<br />
	<div id="body_m_ed">
	<s:form method="post" action="manualeditaction" theme="simple" enctype="multipart/form-data">
	<s:hidden name="manu_id"/>
	<s:hidden name="updcnt"/>
	<s:hidden name="department"/>
	<s:hidden name="group"/>
			<table id="m_m" class="wwFormTable">
				<tr>
					<td id="waku">マニュアルタイトル：</td>
					<td id="waku"><s:textfield name="manual_title" /></td>
					<td id="waku">色：<s:select id="department" name="department"
							list="#{0:'赤', 1:'橙', 2:'黄', 3:'黄緑', 4:'緑',5:'深緑',6:'水',7:'青',8:'群青',9:'紫',10:'桃',11:'黄土',12:'茶',13:'焦茶',14:'灰'}" /></td>
				</tr>
				<tr>
					<td id="waku">階層1：</td>
					<td id="waku" colspan="2"><s:textfield size="48" name="manual1" /></td>
				</tr>
				<tr>
					<td id="waku">階層2：</td>
					<td id="waku" colspan="2"><s:textfield size="48" name="manual2" /></td>
				</tr>
				<tr>
					<td id="waku">階層3：</td>
					<td id="waku" colspan="2"><s:textfield size="48" name="manual3" /></td>
				</tr>
				<tr>
				<td id="waku" colspan="3">　</td>
				</tr>
				<tr>
					<td id="waku">タイトル：</td>
					<td id="waku" colspan="2"><s:textfield size="48" name="manual4" /></td>
				</tr>
				<tr>
					<td id="waku">内容：</td>
					<td colspan="2" id="waku"><s:textarea name="detail_info" cols="60" rows="5" /></td>
				</tr>
				<tr>
					<td id="waku">トークスクリプト<br/>
					/留意事項：</td>
					<td colspan="2" id="waku"><s:textarea name="require_matter" cols="60" rows="5" /></td>
				</tr>
				<tr>
					<td id="waku">画像：</td><td id="waku" colspan="2"><s:file name="movie_picture" id="movie_picture"/></td>
				</tr>
			<tr>
			<td colspan="3" id="waku"><Div Align="right">
							<s:submit value="更新" cssClass="red" method="edit" onclick="openLoad(this.form)"/>
							<s:submit value="削除" cssClass="red" method="delete" />
							<s:submit value="このマニュアルを削除" cssClass="red" method="delete" />
			</Div></td>
			</tr>
			</table>
			</s:form>
	</div>
</body>
</html>