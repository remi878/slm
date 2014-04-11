<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/include/includes.jspf"%>

<div class="clear-both w100 no-marge"></div>
<div class="full-main">
	SPRING_SECURITY_LAST_EXCEPTION = ${SPRING_SECURITY_LAST_EXCEPTION.message} <br/>
	<security:authorize access="isAnonymous()">
		<c:if test="${param.error != null}">
			<p>Invalid username and password.</p>
		</c:if>
		<c:if test="${param.logout != null}">
			<p>You have been logged out.</p>
		</c:if>
		<form action='<spring:url value="<%=LOGIN_PROCESS_URL%>"/>' method="post">
			<input type="text" name="<%=SPRING_LOGIN_USERNAME%>" placeholder="email" size="32" maxlength="64" /><br /> 
			<input type="password" name="<%=SPRING_LOGIN_PASSWORD%>" placeholder="password" size="32" maxlength="64" /><br />
			<input type="checkbox" name="<%=SPRING_LOGIN_REMEMBER_ME%>"><label for="<%=SPRING_LOGIN_REMEMBER_ME%>">Remember me</label><br /> 
			<input type="submit" value="Login" /><br />
		</form>
	</security:authorize>
	<security:authorize access="isAuthenticated()">
		This page is not available for authenticated users !<br/>
		Please, <a href="<c:url value="<%=LOGOUT_PROCESS_URL%>"/>"><img src="<c:url value="/img/logout.png"/>" alt="logout"/>logout</a> before, if you want to go to this page.
	</security:authorize>
</div>
<div class="clear-both w100 no-marge"></div>