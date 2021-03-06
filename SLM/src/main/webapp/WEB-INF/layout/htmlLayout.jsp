<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/include/includes.jspf"%>
<%-- HTML --%>
<!DOCTYPE html>
<html lang="en">
	<head>
        <meta charset="utf-8">
        <title><tiles:insertAttribute name="title" ignore="true"/></title>
		
		<meta name="viewport" content="initial-scale=1.0, user-scalable=no" />
		<meta name="description" content="">
		<meta name="author" content="">
		
		<%-- CSS --%>
		<tiles:insertAttribute name="headerCSS" />
		<%-- /CSS --%>
		<%-- JS --%>
		<tiles:insertAttribute name="headerJS" />
		<%-- /JS --%>

	</head>
	<body>
		<%-- CONTENT --%>
		<div id="global-wrapper">
            <tiles:insertAttribute name="body"/>
		</div>
		<%-- /CONTENT --%>
	</body>
</html>
<%-- /HTML --%>