<%@ tag dynamic-attributes="attributes" isELIgnored="false" pageEncoding="utf-8" %>

<%@ include file="/WEB-INF/include/includes.jspf"%>

<%@ attribute name="userId" required="false"%>

<c:if test="${not empty userId and not empty principal and principal.id ne userId}">
	<img alt="avatar ${userId}" src="<c:url value="<%=AVATAR_PREFIX_URL%>${userId}.jpg?userId=${userId}"/>" height="100" width="100"/>
</c:if>
<c:if test="${empty userId or (not empty principal and principal.id eq userId) }">
	<img alt="avatar current user" src="<c:url value="<%=CURRENT_AVATAR_URL%>"/>" height="100" width="100"/>
</c:if>