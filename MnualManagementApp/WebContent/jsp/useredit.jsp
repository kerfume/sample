<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page isELIgnored="false"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="../css/mystyle.css" />
<!--<script type="text/javascript" src="../js/useredit.js"></script> -->
<title>USER_EDIT</title>
</head>
<body>
	<h1 class="left">ユーザー一覧</h1>
	<div id="right_common">
		<s:form method="post" action="useraction" theme="simple"
			style="display: inline">
			<s:submit cssClass="red" value="メニューに戻る" method="back" />
		</s:form>
	</div>
	<br />
	<br />
	<div id="body_user">
	  	<s:actionmessage/>
		<s:actionerror />
		<s:fielderror cssStyle="color:#ff0000;" />
		<!--  ${sessionScope.editinguser} -->
		<s:form method="post" action="useraction" theme="simple" enctype="multipart/form-data">
		<s:hidden name="userid" />
		<s:if test="%{userid != 0000000000}">
			<b>◎ユーザー「${userid}」を編集中です。　</b>
			<s:submit type="button" value="キャンセル" method="cancell" />
		</s:if>
			<table id="user_e" class="wwFormTable">
				<tr>
					<td id="waku">名前 ： <s:textfield key="name" /></td>
					<td id="waku">パスワード ： <s:textfield key="password" size="18"/></td>
					<td id="waku">性別： <s:radio name="sex" list="#{0:'男', 1:'女'}"/></td>
				</tr>
				<tr>
					<td id="waku">生年月日：<s:select name="birthday_year"
							list="getYearlist()" onChange="setDays(this.form)"/>年 <s:select
							name="birthday_month" list="getMonthlist()" onChange="setDays(this.form)"/>月 <s:select
							name="birthday_day" list="getDaylist()"/>日
					</td>
					<td id="waku" colspan="2">血液型：<s:select name="blood_type"
							list="#{0:'なし', 1:'A', 2:'B', 3:'O', 4:'AB'}" />型
					</td>
				</tr>
				<tr>
					<td id="waku">入社年月日：<s:select name="enter_year"
							list="getYearlist()"/>年 <s:select
							name="enter_month" list="getMonthlist()"/>月
					</td>
					<td id="waku" colspan="2">管理者権限：<s:radio name="authority"
							list="#{0:'権限なし', 1:'権限あり'}" /></td>
				</tr>
				<tr>
					<td id="waku">部署：<s:select id="department" name="department"
							list="getDptlist()" /></td>
					<td id="waku" colspan="2">グループ：<s:select id="group" name="group"
							list="getGrplist()" /></td>
				</tr>
				<tr>
					<td id="waku">役職：<s:textfield key="post" /></td>
					<td colspan="2" id="waku">営業スタイル：<s:select name="business_type"
							list="#{0:'なし', 1:'新規', 2:'既存', 3:'新規/既存'}" /></td>
				</tr>
				<tr>
					<td id="waku">画像：<s:file name="image" id="image"/></td>
					<s:hidden name="fimn"/><!-- 不要かも -->
					<s:if test="%{!(userid.equals(\"0000000000\")) && !(fimn.equals(\"\"))}">
						<td colspan="2" id="waku">
						現在の画像：　<s:a href="../img/%{fimn}" target="_blank">
						<s:if test="%{fimn.length() <= 26}">
						${fimn}
						</s:if>
						<s:else>
						${fimn.substring(0,26).concat(" …")}	
						</s:else>
						</s:a>
						</td>
					</s:if>
					<s:else>
						<td colspan="2" id="waku"></td>
					</s:else>
				</tr>
				<tr>
					<td colspan="3" id="waku"><s:textarea name="comment" cols="80"
							rows="5" /></td>
				</tr>

				<tr>
					<td colspan="3" id="waku"><Div Align="right">
							<s:submit value="登録・更新" cssClass="red" method="edit" onclick="openLoad(this.form)"/>
							<s:submit value="削除" cssClass="red" method="delete" />
						</Div></td>
				</tr>
			</table>

		</s:form>
		<br/>
		<iframe src="userlistaction.action" width="820px" height="500px"></iframe>
	</div>

<script
	src="http://ajax.googleapis.com/ajax/libs/jquery/1.7.0/jquery.min.js"></script>

<script
	src="../js/useredit.js"></script>
	
</body>
</html>