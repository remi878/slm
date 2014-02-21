<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/include/includes.jspf"%>

<div class="clear-both w100 no-marge"></div>
<div class="full-main">
	<c:if test="${param.error != null}">
		<p>Invalid username and password.</p>
	</c:if>
	<c:if test="${param.logout != null}">
		<p>You have been logged out.</p>
	</c:if>
	<form action='<spring:url value="/j_spring_security_check"/>'>
		<input type="text" name="j_username" placeholder="email" size="32" maxlength="64" /><br /> 
		<input type="password" name="j_password" placeholder="password" size="32" maxlength="64" /><br />
		<input type="checkbox" name="_spring_security_remember_me"><label for="_spring_security_remember_me">Remember me</label><br /> 
		<input type="submit" value="Login" /><br />
	</form>
</div>
<div class="clear-both w100 no-marge"></div>