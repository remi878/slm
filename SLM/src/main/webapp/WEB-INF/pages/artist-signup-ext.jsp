<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/include/includes.jspf"%>

<div id="artistFields">
	<h3>Specific Artist informations here ...</h3>
	<br/><i> ... </i><br/>
	<slm:inputBox label="Your youtube page" type="url" field="youtube" required="false" size="30" maxlength="64" placeholder="http://www.youtube.com/user/yourUserID"/>
	<slm:inputBox label="Your vimeo page" type="url" field="vimeo" required="false" size="30" maxlength="64" placeholder="http://vimeo.com/yourUserID/videos"/>
	<slm:fieldBox label="Your philosophy" field="groupPhylos" required="false">
			<div class="textarea">
				<form:textarea path="groupPhylos" id="groupPhylos" rows="3" cols="50" maxlength="512" placeholder="your (group/artrist) philosophy here ..." />
			</div>
		</slm:fieldBox>
</div>