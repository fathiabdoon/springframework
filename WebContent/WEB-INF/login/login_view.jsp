<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<c:if test="${!empty error }">
	<font color="red"><c:out value="${error}"/></font>
</c:if>
<form action="<c:url value="./login"/>" method="post">
user name: <input type="text" name="userName"><br>
user password:<input type="password" name="userPassword"/><br>
<input type="submit" value="login"/>
<input type="reset" value="reset"/>
</form>
</body>
</html>