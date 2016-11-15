<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page isELIgnored="false"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<style type="text/css">
table{
}
.list {
}
.list td{
	padding: 5px;
}

</style>
<link rel="stylesheet" type="text/css" href="../css/mystyle.css" />
<title>MANUAL_EDIT</title>
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
		<s:form method="post" action="manualeditaction" theme="simple">
			<table id="m_e" class="wwFormTable">
				<tr>
					<td id="waku">部署：<s:select id="department" name="department"
							list="getDptlist()" /></td>
					<td id="waku" colspan="2">グループ：<s:select id="group"
							name="group" list="getGrplist()" /></td>
				</tr>
				<tr>
					<td id="waku" colspan="2"><s:select id="manualSyubetu" name="manualSyubetu"
							list="getManulist()" /></td>
					<!-- name="group" list="getGrplist()" /></td> -->
					<td id="waku"><Div Align="right">
							<s:submit value="検索" cssClass="red" method="serch" 
								onclick="openLoad(this.form)" />
						</Div></td>
				</tr>
			</table>
			</s:form>

			<table class="list" id="m_li" bgcolor="#000000">
				<tr>
				<tr bgcolor="#ffb6c1">
					<th width="120" bgcolor="#ffa07a">変更・削除</th>
					<th width="300" bgcolor="#ffa07a">階層1</th>
					<th width="300" bgcolor="#ffa07a">階層2</th>
					<th width="300" bgcolor="#ffa07a">階層3</th>
					<th width="300" bgcolor="#ffa07a">階層4</th>
				</tr>
				<s:iterator value="mbL" status="st">
					<s:if test="%{#st.index % 2 == 0}">
						<tr bgcolor="#FDF5E6">
					</s:if>
					<s:else>
						<tr bgcolor="#FFDAB9">
					</s:else>
					<td><s:form method="post" action="manualmodifiaction" theme="simple">
							<s:hidden name="DPT"/><s:hidden name="GRP"/>
							<s:hidden name="manu_ID" value="%{mbL[#st.index].manual_ID}" />
							<s:hidden name="updcnt" value="%{mbL[#st.index].updcnt}" />
							<s:submit type="submit" value="変更・削除" method="edit"/>
						</s:form>
					<td><s:property value="kaisou1" /></td>
					<td><s:property value="kaisou2" /></td>
					<td><s:property value="kaisou3" /></td>
					<td><s:property value="kaisou4" /></td>

					</tr>

				</s:iterator>
			</table>
	</div>


	<script
		src="http://ajax.googleapis.com/ajax/libs/jquery/1.7.0/jquery.min.js"></script>
	<script src="../js/manuedit.js"></script>
</body>
</html>