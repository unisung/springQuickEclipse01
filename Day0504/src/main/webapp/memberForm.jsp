<%@page contentType="text/html; charset=EUC-KR"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>ȸ������</title>
</head>
<body>
	<center>
		<h1>ȸ������</h1>
		<%-- <a href="?lang=en"><spring:message code="message.user.login.language.en"/></a>|
		<a href="?lang=ko"><spring:message code="message.user.login.language.ko"/></a> --%>

		<hr>
		<form action="insertUser.do" method="post">
			<table border="1" cellpadding="0" cellspacing="0">
				<tr>
					<td bgcolor="orange" width="70">id</td>
					<td align="left">
					<input name="id" type="text" /><button onclick="javascript:window.open();">idȮ��</button>
					</td>
				</tr>
				<tr>
					<td bgcolor="orange">���</td>
					<td align="left"><input name="password" type="password" /></td>
				</tr>
				<tr>
					<td bgcolor="orange">���</td>
					<td align="left"><input name="password2" type="password" /></td>
				</tr>
				<tr>
					<td bgcolor="orange">�̸�</td>
					<td align="left"><input name="name" type="text" /></td>
				</tr>
				<tr>
				
				     <td bgcolor="orange">����</td>
					 <td>
					 <select name="role">
					 		<option value="admin">������</option>
					 		<option value="user">�Ϲ�</option>
					 </select>
					</td>
				</tr>
				 <tr>
					<td colspan="2" align="center"><input type="submit"
						value="�����ϱ�" /></td>
				</tr>
			</table>
		</form>
		
		<hr>
	</center>
	
</body>
</html>
