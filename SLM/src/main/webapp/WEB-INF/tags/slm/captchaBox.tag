<%@ tag dynamic-attributes="attributes" isELIgnored="false" pageEncoding="utf-8" %>

<%@ include file="/WEB-INF/include/includes.jspf"%>

<%@ attribute name="withImg" required="false" description="With captcha image. default true."%>
<%@ attribute name="withAudio" required="false" description="With captcha audio. default false."%>

<slm:fieldBox label="Please enter the following text in the field" field="captchaResponse" required="true">
	<div class="captchaBox">
		<c:if test="${withImg ne false}">
			<img src='<c:url value="img/captcha.png"/>' alt="captcha image"/>
		</c:if>
		<c:if test="${withAudio eq true}">
			<audio controls autoplay src='<c:url value="audio/captcha.wav"/>' />
		</c:if>
		<form:input path="captchaResponse" id="captchaResponse" size="30" maxlength="32" placeholder="..." />
	</div>
</slm:fieldBox>
