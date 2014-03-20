<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/include/includes.jspf"%>

<h2>Page design to be defined ...</h2>

<tiles:importAttribute name="formId" ignore="true" />
<c:if test="${empty formId}">
	<c:set var="formId" value="user" />
</c:if>

<form:form commandName="user" id="${formId}RegistrationForm" servletRelativeAction="/fan-signup" method="POST">
	<div class="formError error">
		<form:errors />
	</div>
	
	<div id="accountFields">
		<h3>Account informations :</h3>
		<slm:inputBox label="Email" maxlength="64" size="30" type="email" field="email" placeholder="user@mydomain.com" required="true"/>
		<slm:inputBox label="Password" maxlength="32" size="30" type="password" field="password" required="true"/>
		<slm:inputBox label="Confirm your password" maxlength="32" size="30" type="password" field="confirmPassword" required="true"/>
	</div>
	
	<div id="personalFields">
		<h3>Personal informations :</h3>
		<slm:inputBox label="First name" field="firstName" required="true" size="30" maxlength="64"/>
		<slm:inputBox label="Last name" field="lastName" required="true" size="30" maxlength="64"/>
		<slm:fieldBox label="Gender" field="gender" required="false">
			<div class="radioGroup">
				<form:radiobutton path="gender" value="true" label="Male" />
				<form:radiobutton path="gender" value="false" label="Female" />
			</div>
		</slm:fieldBox>
		<slm:inputBox label="Birthdate" field="birthdate" required="true" type="date" size="10" maxlength="10" placeholder="MM/dd/yyyy" />
	</div>
	
	<div id="extraFields">
		<h3>Profil informations :</h3>
		<slm:inputBox label="Nick name (optional)" field="nickname" required="false" size="30" maxlength="32" placeholder="your nickname on this site" />
		<slm:fieldBox label="Description (optional)" field="description" required="false">
			<div class="textarea">
				<form:textarea path="description" id="description" rows="3" cols="50" maxlength="512" placeholder="your description here ..." />
			</div>
		</slm:fieldBox>
		<slm:inputBox label="Your site url (optional)" field="websiteUrl" required="false" size="30" maxlength="64" placeholder="mydomain.com"/>
	</div>
	
	<tiles:insertAttribute name="ext" />
	
	<div class="field submit">
		<input type="submit" value="Sign-up" />
	</div>
</form:form>