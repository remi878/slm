<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/include/includes.jspf"%>
<c:set var="email" value=""/>
<c:if test="${not empty user}">
	[${user.id}] ${user.username},<br/>
	<c:set var="email" value=" [${user.email}]"/>
</c:if>
<b>Thank you for registering on StreeLiveMusic.</b><br/>

Your account has been created and a verification email has been sent to your registered email address${email}. Please click on the verification link included in the email to activate your account.<br/>

Your account will not be activated until you verify your email address.<br/>

...