<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery.js"></script>
<script type="text/javascript">
  
$(function(){
	$('#jerseybutton').click(
		function(){
			var url = "<%=request.getContextPath()%>/rs/helloworld/" + $('#loginName').val();
			$.get(url, function(result){
				$('#greetingmsg').html(result);
			});	
		}
	);
});

</script>
</head>
<body>
<div id="greetingmsg"></div>
who do you want to greet?
<input id="loginName" name="name" size="20" type="text" />  
<input id="jerseybutton" name="submit" type="button" value="greet"/>
</body>
</html>