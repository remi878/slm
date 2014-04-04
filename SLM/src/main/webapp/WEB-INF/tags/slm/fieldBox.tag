<%@ tag dynamic-attributes="attributes" isELIgnored="false" pageEncoding="utf-8"%>

<%@ include file="/WEB-INF/include/includes.jspf"%>

<%@ attribute name="field" required="true"%>
<%@ attribute name="label" required="true"%>
<%@ attribute name="required" required="true"%>

<c:set var="fieldCssClass" value="" />
<spring:bind path="${field}">
	<c:if test="${status.error}">
		<c:set var="fieldCssClass" value=" fieldInError" />
	</c:if>
</spring:bind>

<spring:bind path="${path}">
	<c:forEach var="error" items="${status.errorMessages}" varStatus="status">
		${error}
		<c:if test="${!status.last}">,</c:if>
	</c:forEach>
</spring:bind>

<div class="fieldBox">
	<div class="fieldError error">
		<form:errors path="${field}" cssclass="error" />
	</div>
	<div class="field${fieldCssClass}">
		<c:if test="${required eq true}">
			<form:label path="${field}">${label}&#160;${REQUIRED}:&#160;</form:label>
		</c:if>
		<c:if test="${required ne true}">
			<form:label path="${field}">${label}&#160;:&#160;</form:label>
		</c:if>
		<jsp:doBody />
	</div>
</div>
