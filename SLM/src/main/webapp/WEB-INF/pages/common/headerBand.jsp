<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/include/includes.jspf"%>

<div id="headband">
	<security:authorize access="isAuthenticated()">
		<div class="float-left" id="user-wrapper">
			<a src="<c:url value="/profil"/>"><b>${principal.displayName}</b></a>
		</div>
		<div class="float-left" id="newEvent-wrapper">
			<a src="<c:url value="/newEvent"/>"><img src="<c:url value="/img/pen.png"/>" align="absmiddle" /> Register an Event</a>
		</div>
	</security:authorize>
	
	<div class="float-right" id="social-wrapper">
		<ul>
			<li><a src="<c:url value="/fb"/>"><img src="<c:url value="/img/facebook.png"/>" /> </a></li>
			<li><a src="<c:url value="/tw"/>"><img src="<c:url value="/img/twiter.png"/>" /> </a></li>
		</ul>
	</div>
	
	<div class="float-right" id="search-wrapper">
		<form action="<c:url value="/search"/>" methode="post">
			<input type="search" placeholder="Search ..." maxlength="32" size="16" disabled="disabled"/>
		</form>
	</div>
	
	<div class="float-right" id="login-link-wrapper">
		<nav id='login-menu'>
			<security:authorize access="isAnonymous()">
				<ul>
					<li><a href="<c:url value="/login"/>"><img src="<c:url value="/img/login.png"/>" class="float-left" alt="login" /><span
							class="float-left pl5 mr20">Login</span><span class="clear-both"></span></a></li>
					<li><a href="<c:url value="/sign-up"/>"><img src="<c:url value="/img/key.png"/>" class="float-left" alt="key" /><span class="float-left pl5">Sign-up</span>
					</a></li>
				</ul>
			</security:authorize>
			<security:authorize access="isAuthenticated()">
				<ul>
					<li><a href="<c:url value="/user/profile"/>"><img src="<c:url value="/img/profile.png"/>" class="float-left" alt="profile" /><span
							class="float-left pl5 mr20">Profile</span><span class="clear-both"></span></a></li>
					<li><a href="<c:url value="/j_spring_security_logout"/>"><img src="<c:url value="/img/logout.png"/>" class="float-left" /><span
							class="float-left pl5 mr20">Logout</span><span class="clear-both"></span></a></li>
				</ul>
			</security:authorize>
		</nav>
	</div>
	<div class="clear-both w100 no-marge"></div>
</div>