<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
        <% 
        		String id = request.getParameter("id");
        %>
        <script>
             alert("id:"+'<%=id%>');
             location.href="idchek.do?id="+id;
        </script>
</body>
</html>