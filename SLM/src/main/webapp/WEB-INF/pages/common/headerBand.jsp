<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/include/includes.jspf"%>

<div id="headband">
	<div class="float-left" id="user-wrapper">
		<a href="/profil"><b>Username</b> </a>
	</div>
	<div class="float-left" id="newEvent-wrapper">
		<a href="/newEvent"><img src="./img/pen.png" align="absmiddle" /> Register an Event</a>
	</div>
	<div class="float-right" id="social-wrapper">
		<ul>
			<li><a href="/fb"><img src="./img/facebook.png" /> </a></li>
			<li><a href="/tw"><img src="./img/twiter.png" /> </a></li>
		</ul>
	</div>
	<div class="float-right" id="search-wrapper">
		<form action="/search" methode="post">
			<input type="text" placeholder="Search ..." maxlength="32" size="16" />
		</form>
	</div>
	<div class="float-right" id="login-link-wrapper">
		<nav id='login-menu'>
			<ul>
				<li><a href="<c:url value="/login"/>"><img src="./img/lock.png" class="float-left" /> <span class="float-left pl5 mr20">Login</span> <span class="clear-both"></span> </a></li>
				<li><a href="${pageContext.request.contextPath}/sign-up"><img src="./img/key.png" class="float-left " /> <span class="float-left pl5">Sign-up</span> </a></li>
			</ul>
		</nav>
	</div>
	<div class="clear-both w100 no-marge"></div>
</div>