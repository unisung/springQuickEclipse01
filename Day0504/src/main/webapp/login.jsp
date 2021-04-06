<%@page contentType="text/html; charset=EUC-KR"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title><spring:message code="message.user.login.title"/></title>
</head>
<body>
Login Page-----
	<center>
		<h1><spring:message code="message.user.login.title"/></h1>
		<a href="?lang=en"><spring:message code="message.user.login.language.en"/></a>|
		<a href="?lang=ko"><spring:message code="message.user.login.language.ko"/></a>
		
		<hr>
		<form action="login.do" method="post">
			<table border="1" cellpadding="0" cellspacing="0">
				<tr>
					<td bgcolor="orange"><spring:message code="message.user.login.id"/></td>
				<%-- 	<td><input type="text" name="id"  value="${userVO.id}"/></td> --%>
					<td><input type="text" name="id"  value="${user.id}"/></td>
				</tr>
				<tr>
					<td bgcolor="orange"><spring:message code="message.user.login.password"/></td>
					<%-- <td><input type="password" name="password"  value="${userVO.password}"/></td> --%>
					<td><input type="password" name="password"  value="${user.password}"/></td>
				</tr>
				<tr>
					<td colspan="2" align="center"><input type="submit"
						value="<spring:message code="message.user.login.loginBtn"/>" />
					<button onclick="return goMember();">회원가입</button>	
						</td>
				</tr>
			</table>
		</form>
		<hr>
	</center>
</body>
</html>
<script>
function goMember(){
	alert("goMember");
	location.href="insertMember.do";
	return false;
}
</script>