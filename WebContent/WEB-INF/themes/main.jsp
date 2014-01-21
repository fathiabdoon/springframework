<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="<spring:theme code='mytheme'/>"/>
<title>Insert title here</title>
<script type="text/javascript" src="/demo-pizza/js/jquery.js"></script>
<script type="text/javascript">
$(function(){
	// select all element which id starts with "mylocale"
	$("[id^='urltheme']").each(
		function(){
			$(this).click(
					function(){
						var langurl = "/demo-pizza/theme/"+$(this).attr("name");
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
	$("[id^='interceptortheme']").each(
		function(){
			$(this).click(
					function(){
						var langurl = "http://"+window.location.host+window.location.pathname+"?theme="+$(this).attr("name");
						window.location.href = langurl;
					}
			)
		}
	);
});
</script>

</head>
<body>

<div id="container"><!--页面层容器-->
　　<div id="Header"><!--页面头部-->
		<div>
			change to theme one by controller
			<a href="#" id="urltheme1" name="themeone">theme one</a>
			<a href="#" id="urltheme1" name="themetwo">theme two</a>
			</div>
			<div>
			change to theme two by intercepter
			<a href="#" id="interceptortheme1" name="themeone">theme one</a>
			<a href="#" id="interceptortheme2" name="themetwo">theme two</a>
		</div>
　　</div>
　　<div id="PageBody"><!--页面主体-->
　　　　<div id="Sidebar"><!--侧边栏-->
　　　　</div>
　　　　<div id="MainBody"><!--主体内容-->
　　　　</div>
　　</div>
　　<div id="Footer"><!--页面底部-->
　　</div>
</div>
</body>
</html>