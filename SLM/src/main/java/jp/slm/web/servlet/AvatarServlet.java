package jp.slm.web.servlet;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jp.slm.business.bean.Avatar;
import jp.slm.business.bean.User;
import jp.slm.business.service.UserService;
import jp.slm.web.listener.ApplicationListener;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.HttpRequestHandler;

public class AvatarServlet implements HttpRequestHandler {
	
	public static final Logger LOG = LoggerFactory.getLogger(ApplicationListener.class);
	
	private static final int BUFFER_SIZE = 8 * 1024; // 8k
	
	@Autowired
	private UserService userService;
	
	@Override
	public void handleRequest(HttpServletRequest request, HttpServletResponse response) throws IOException {
		User user = null;
		if (request.getParameter("userId") != null && StringUtils.isNumeric(request.getParameter("userId"))) {
			try {
				Long userId = Long.parseLong(request.getParameter("userId"));
				user = userService.findById(userId);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if (request.getRequestURI().contains("current")) {
			user = userService.getCurrentUser();
		}
		if (user != null && user.getAvatar() != null) {
			Avatar avatar = user.getAvatar();
			byte[] data = avatar.getAvatarData();
			String fileName = "avatar-" + user.getId() + ".jpg";
			if (avatar.isIsSystem()) {
				response.setHeader("Cache-Control", "public");
				fileName = "avatar-sys-" + avatar.getId() + ".jpg";
			} else if (avatar.getInDate() != null) {
				// TODO : Last-Modified to test
				response.setDateHeader("Last-Modified", avatar.getInDate().getTime());
			}
			try {
				writeImage(data, fileName, response);
			} catch (IOException e) {
				LOG.error("Error avatar io :", e);
				e.printStackTrace();
				sendRedirectToDefaultAvatar(request, response);
			}
		} else {
			sendRedirectToDefaultAvatar(request, response);
		}
	}
	
	private void sendRedirectToDefaultAvatar(HttpServletRequest request, HttpServletResponse response) throws IOException {
		response.sendRedirect(request.getContextPath() + "/img/defaultAvatar.jpg");
	}
	
	private void writeImage(byte[] data, String fileName, HttpServletResponse response) throws IOException {
		
		response.setContentType("image/jpeg");
		response.setContentLength(data.length);
		
		response.setHeader("Content-Disposition", "inline; filename=\"" + fileName + "\"");
		
		BufferedInputStream input = null;
		BufferedOutputStream output = null;
		
		try {
			input = new BufferedInputStream(new ByteArrayInputStream(data));
			output = new BufferedOutputStream(response.getOutputStream());
			byte[] buffer = new byte[BUFFER_SIZE];
			int length;
			while ((length = input.read(buffer)) > 0) {
				output.write(buffer, 0, length);
			}
			output.flush();
		} finally {
			if (output != null) try {
				output.close();
			} catch (IOException ignore) {}
			if (input != null) try {
				input.close();
			} catch (IOException ignore) {}
		}
		
	}
}
