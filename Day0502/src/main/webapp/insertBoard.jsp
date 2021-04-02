<%@page contentType="text/html; charset=EUC-KR"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title><spring:message  code="message.board.list.link.insertBoard"/></title>
</head>
<body>
	<center>
		<h1><spring:message  code="message.board.list.link.insertBoard"/></h1>
		<a href="?lang=en"><spring:message code="message.user.login.language.en"/></a>|
		<a href="?lang=ko"><spring:message code="message.user.login.language.ko"/></a>
		
		<a href="logout.do"><spring:message code="message.user.login.logoutBtn"/></a>
		<hr>
		<form action="insertBoard.do" method="post" enctype="multipart/form-data">
			<table border="1" cellpadding="0" cellspacing="0">
				<tr>
					<td bgcolor="orange" width="70"><spring:message code="message.board.list.table.head.title"/></td>
					<td align="left"><input type="text" name="title" /></td>
				</tr>
				<tr>
					<td bgcolor="orange"><spring:message  code="message.board.list.table.head.writer"/></td>
					<td align="left"><input type="text" name="writer" size="10"  value="${user.name}"/></td>
				</tr>
				<tr>
					<td bgcolor="orange"><spring:message  code="message.board.table.head.content"/></td>
					<td align="left"><textarea name="content" cols="40" rows="10"></textarea></td>
				</tr>
				<tr>
					<td bgcolor="orange" width="70"><spring:message  code="message.board.table.head.file"/></td>
					<td align="left"><input type="file" name="uploadFile" /></td>
				</tr>
				<tr>
					<td colspan="2" align="center"><input type="submit"
						value="<spring:message  code="message.board.list.link.insertBoard"/>" /></td>
				</tr>
			</table>
		</form>
		<hr>
		<a href="getBoardList.do"><spring:message  code="message.board.link.listBoard"/></a>
	</center>
</body>
</html>