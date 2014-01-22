<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script type="text/javascript" src="./js/jquery.js"></script>
<script type="text/javascript">

$(function(){
	// select all element which id starts with "mylocale"
	$("[id^='mylocale']").each(
		function(){
			$(this).click(
					function(){
						var langurl = "./multilang/"+$(this).attr("name");
						$.get(langurl,function(){
							window.location.href = "./multilang";
							//window.location.reload();
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
						var langurl = "./multilang?locale="+$(this).attr("name");
						window.location.href = langurl;
					}
			)
		}
	);
});
</script>
</head>
<body>
<div >
use jstl to output message:
${helloworld.name}, <fmt:message key="hello"/>
</div>
<div>
use spring tag to output message:
${helloworld.name}, <spring:message code="hello"/>
</div>
<div>
change lang by program:
<a href="javascript:void(0);" id="mylocale1" name="zh_CN">zh_CN</a>
<a href="javascript:void(0);" id="mylocale2" name="en_US">en_US</a>

change lang by spring interceptor:
<a href="#" id="locale1" name="zh_CN" >zh_CN</a>
<a href="#" id="locale2" name="en_US">en_US</a>
</div>
<div>
usage:
step 1: define multi-lang message
<xmp>
<!-- multi-lang : configure resoucebundle -->
	<bean id="messageSource"
		class="org.springframework.context.support.ReloadableResourceBundleMessageSource"
		p:fallbackToSystemLocale="true" p:useCodeAsDefaultMessage="false"
		p:defaultEncoding="UTF-8">
		<description>Base message source to handle internationalization
		</description>
		<property name="basenames">
			<list>
				<value>classpath:local/message/default</value>
			</list>
		</property>
	</bean>
</xmp>

the rule of resource file is: , here like "default_en_US.properties", "default_zh_CN.properties"

step 2: change locale dynamically

there are two methods:

method1: use interceptor, by default only need to add request param "locale" to url
<xmp>
<mvc:interceptors>
	<bean class="org.springframework.web.servlet.i18n.LocaleChangeInterceptor" />
</mvc:interceptors>
</xmp>


method2: use localresolver, there are two kinds of localeresolver
<xmp>
	<bean id="localeResolver" class="org.springframework.web.servlet.i18n.SessionLocaleResolver"/>
</xmp>
<xmp>
	<bean id="localeResolver" class="org.springframework.web.servlet.i18n.CookieLocaleResolver" />
</xmp>
then define a controller in which use localresolver and param to change locale
</div>
</body>
</html>