<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript" src="./js/jquery.js"></script>
<script type="text/javascript">

function parseXmlHelloworld(result){
	return $(result).find("name").text() + "," + $(result).find("greeting").text() + ',' + $(result).find("timestamp").text();
}
  
$(function(){
	$('#xmlbutton').click(
		function(){
			$.ajax({
		        type: 'POST', 
		        url: 'xml',
		        data: "<?xml version=\"1.0\" encoding=\"UTF-8\" ?><HelloWorld><name>"+$('#loginName').val()+"</name></HelloWorld>",
		        dataType: 'xml',  
		        contentType:'application/xml;charset=UTF-8',
		        success: function(result) {
		            $('#greetingmsg').html(parseXmlHelloworld(result));
		        },
				error: function(XMLHttpRequest, textStatus, errorThrown){
					$('#greetingmsg').html(textStatus + ':' + errorThrown);
				} 
		    });
		}	
	);
});

</script>
</head>
<body>
<div id="greetingmsg"></div>
who do you want to greet：  
<input id="loginName" name="name" size="20" type="text"/>  
<input id="xmlbutton" name="submit" type="button" value="test ajax xml restful"/>
</body>
</html>