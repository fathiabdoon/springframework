<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script type="text/javascript" src="/demo-pizza/js/jquery.js"></script>
<script type="text/javascript">

$(function(){
	// select all element which id starts with "mylocale"
	$("[id^='mylocale']").each(
		function(){
			$(this).click(
					function(){
						var langurl = "/demo-pizza/multilang/url?mylocale="+$(this).attr("name");
						$.get(langurl,function(){
							window.location.reload();
						});
					}
			)
		}
	);
	
	
	
});

$(function(){
	//select all element which id starts with "locale"
	$("[id^='locale']").each(
		function(){
			$(this).click(
					function(){
						var langurl = "http://"+window.location.host+window.location.pathname+"?locale="+$(this).attr("name");
						window.location.href = langurl;
					}
			)
		}
	);
});
</script>
</head>
<body>
<div>
use jstl to output message:
<fmt:message key="hello"/>
</div>
<div>
use spring tag to output message:
<spring:message code="hello"/>
</div>
<div>
change lang by program:
<a href="#" id="mylocale1" name="zh_CN">zh_CN</a>
<a href="#" id="mylocale2" name="en_US">en_US</a>

change lang by spring interceptor:
<a href="#" id="locale1" name="zh_CN" >zh_CN</a>
<a href="#" id="locale2" name="en_US">en_US</a>
</div>
</body>
</html>