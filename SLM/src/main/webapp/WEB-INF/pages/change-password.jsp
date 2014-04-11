<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/include/includes.jspf"%>

<div class="clear-both w100 no-marge"></div>
<div class="full-main">
	<security:authorize access="isAuthenticated()">
		<form:form commandName="user" id="newPasswordForm" servletRelativeAction="<%=CHANGE_PASSWORD_URL%>" method="POST">
			<div class="formError error">
				<form:errors />
			</div>
			<div id="passwordFields">
				<slm:inputBox label="Old password" maxlength="32" size="30" type="password" field="<%=OLD_PASSWORD_STR%>" required="true" />
				<slm:inputBox label="New password" maxlength="32" size="30" type="password" field="<%=PASSWORD_STR%>" required="true" />
				<slm:inputBox label="Confirm your new password" maxlength="32" size="30" type="password" field="<%=CONFIRM_PASSWORD_STR%>" required="true" />
			</div>
			<div id="captchaFields">
				<slm:captchaBox />
			</div>
			<div class="field submit">
				<input type="submit" value="Change password" />
			</div>
		</form:form>
	</security:authorize>
	<security:authorize access="isAuthenticated()">
		This page is only available for authenticated users !<br />
		Please, <a href="<c:url value="<%=LOGIN_URL%>"/>"><img src="<c:url value="/img/login.png"/>" alt="login" />login</a> before, if you want to go to this page.
	</security:authorize>
</div>
<div class="clear-both w100 no-marge"></div>