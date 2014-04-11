<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/include/includes.jspf"%>

<div class="clear-both w100 no-marge"></div>
<div class="full-main">
	<security:authorize access="isAnonymous()">
		<form action='<spring:url value="<%=LOST_PASSWORD_URL%>"/>' method="post">
			<slm:inputBox label="Your email" maxlength="64" size="30" type="email" field="<%=EMAIL%>" placeholder="user@mydomain.com" required="true"/>
			<input type="submit" value="Lost Password" /><br />
		</form>
	</security:authorize>
	<security:authorize access="isAuthenticated()">
		This page is not available for authenticated users !<br/>
		Please, <a href="<c:url value="/j_spring_security_logout"/>"><img src="<c:url value="/img/logout.png"/>"/>logout</a> before, if you want to go to this page.
	</security:authorize>
</div>
<div class="clear-both w100 no-marge"></div>