<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/include/includes.jspf"%>

<div class="clear-both w100 no-marge"></div>
<div class="full-main">
	<form action='<spring:url value="/surmit-login"/>'>
		<input type="text" name="email" placeholder="email" size="32" maxlength="64"/><br/>
		<input type="password" name="password" placeholder="password" size="32" maxlength="64"/><br/>
		<input type="submit" value="Login"/><br/>
	</form>
</div>
<div class="clear-both w100 no-marge"></div>