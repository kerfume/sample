<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page isELIgnored="false"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<style type="text/css">
<!--
table {
	width: 800px;
	margin-left: auto;
	margin-right: auto;
}
-->
</style>
<title>Insert title here</title>
</head>
<body>
<center>
<s:if test="%{!(unum == 0)}">
[<s:a href="userlistaction.action?unum=%{unum - 1}">前へ</s:a>]
</s:if>
＜＜ 
<s:iterator status="st" value="pagenum.{#this}">
	<s:if test="%{unum == 0 && unum == #st.index}">
	<b>1</b>
	</s:if>
	<s:elseif test="%{unum == #st.index}">
	<b>${st.index + 1}</b>
	</s:elseif>
	<s:else>
	<s:a href="userlistaction.action?unum=%{#st.index}">${st.index + 1} </s:a>
	</s:else>
</s:iterator> ＞＞ 
<s:if test="%{!(unum + 1 == pagenum)}">
[<s:a href="userlistaction.action?unum=%{unum + 1}">次へ</s:a>]
</s:if>
	</center>

	<table cellpadding="3" bgcolor="#000000">
		<tr>
		<tr bgcolor="#ffb6c1">
			<th bgcolor="#ffa07a">ID</th>
			<th bgcolor="#ffa07a">部署</th>
			<th bgcolor="#ffa07a">役職</th>
			<th bgcolor="#ffa07a">名前</th>
			<th bgcolor="#ffa07a">画像</th>
			<th bgcolor="#ffa07a">更新日</th>
			<th bgcolor="#ffa07a">編集</th>
		</tr>
		<s:iterator value="udL" status="st" begin="begin" end="end">
			<s:if test="%{#st.index % 2 == 0}">
				<tr bgcolor="#FDF5E6">
			</s:if>
			<s:else>
				<tr bgcolor="#FFDAB9">
			</s:else>
			<td><s:property value="userid" /></td>
			<td><s:property value="group" /></td>
			<td><s:property value="post" /></td>
			<td><s:property value="name" /></td>
			<td><s:if
					test="%{udL[#st.index + begin].imn != null && !udL[#st.index + begin].imn.equals(\"\") }">
					<s:a href="../img/%{udL[#st.index + begin].imn}" target="_blank">
						<center>
							<center>
								<img src="../img/pictfound.gif">
							</center>
						</center>
					</s:a>
				</s:if> <s:else>
					<center>
						<img src="../img/notfound.gif">
					</center>
				</s:else></td>
			<td><s:property value="update_time" /></td>
			<td>
				<!--<s:a href="useraction.action?userid=%{udL[#st.index].userid}" target="_parent">編集</s:a> -->
				<s:form theme="simple">
					<s:submit type="button" value="変更"
						onClick="parent.location.href='useraction.action?userid=%{udL[#st.index + begin].userid}'" />
				</s:form>
			</td>
			</tr>

		</s:iterator>
	</table>
	<center>
<s:if test="%{!(unum == 0)}">
[<s:a href="userlistaction.action?unum=%{unum - 1}">前へ</s:a>]
</s:if>
＜＜ 
<s:iterator status="st" value="pagenum.{#this}">
	<s:if test="%{unum == 0 && unum == #st.index}">
	<b>1</b>
	</s:if>
	<s:elseif test="%{unum == #st.index}">
	<b>${st.index + 1}</b>
	</s:elseif>
	<s:else>
	<s:a href="userlistaction.action?unum=%{#st.index}">${st.index + 1} </s:a>
	</s:else>
</s:iterator> ＞＞ 
<s:if test="%{!(unum + 1 == pagenum)}">
[<s:a href="userlistaction.action?unum=%{unum + 1}">次へ</s:a>]
</s:if>
	</center>
</body>
</html>