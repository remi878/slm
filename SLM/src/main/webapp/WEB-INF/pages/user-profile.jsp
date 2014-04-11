<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/include/includes.jspf"%>
User profile here ...

<c:set var="owner" value="${false}" />
<c:if test="${not empty user.id and not empty principal and principal.id eq user.id}">
	<c:set var="owner" value="${true}" />
</c:if>

<div id="profilAvatar">
	<c:if test="${owner}">
		<slm:avatar /> <br />
		<a href='<c:url value="<%=CHANGE_AVATAR_URL%>"/>'>Change avatar</a> <br />
	</c:if>
	<c:if test="${!owner}">
		<slm:avatar userId="${user.id}" />
	</c:if>
</div>

<c:if test="${owner}">
	<div id="accountFields">
		<h3>Account informations :</h3>
		Email : ${user.email} <br />
		Creation Date : ${user.creationDate} <br />
		<a href='<c:url value="<%=CHANGE_PASSWORD_URL%>"/>'>Change password</a> <br />
	</div>
</c:if>

<div id="personalFields">
	<h3>Personal informations :</h3>
	First name : ${user.firstName} <br />
	Last name : ${user.lastName} <br />
	<c:if test="${not empty user.gender}">
		<c:if test="${user.gender}">
				Gender : Male <br />
		</c:if>
		<c:if test="${!user.gender}">
				Gender : Female <br />
		</c:if>
	</c:if>
	Birthdate : ${user.birthdate} <br />
</div>

<div id="extraFields">
	<h3>Profil informations :</h3>
	Nick name (optional): ${user.nickname} <br />
	Description (optional): ${user.description} <br />
	Site url (optional): ${user.url} <br />
</div>

<c:if test="${owner}">
	<a href='<c:url value="<%=USER_EDIT_PROFILE_URL%>"/>'>Edit</a> <br />
</c:if>


