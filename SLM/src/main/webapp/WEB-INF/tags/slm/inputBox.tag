<%@ tag dynamic-attributes="attributes" isELIgnored="false" pageEncoding="utf-8" %>

<%@ include file="/WEB-INF/include/includes.jspf"%>

<%--Attributs principaux --%>
<%@ attribute name="field" required="true"%>
<%@ attribute name="label" required="true"%>
<%@ attribute name="required" required="false"%>
<%@ attribute name="type" required="false"%>
<%@ attribute name="size" required="false"%>
<%@ attribute name="maxlength" required="false"%>
<%@ attribute name="placeholder" required="false"%>


<slm:fieldBox label="${label}" field="${field}" required="${required}">
	<form:input path="${field}" type="${type}" id="${field}" size="${size}" maxlength="${maxlength}" placeholder="${placeholder}"/>
</slm:fieldBox>
