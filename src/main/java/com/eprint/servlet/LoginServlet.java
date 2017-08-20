package com.eprint.servlet;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.avos.avoscloud.AVException;
import com.avos.avoscloud.AVUser;
import com.eprint.datamodel.User;
import com.eprint.util.AuthUtil;
import com.eprint.util.ServletHelper;

public class LoginServlet extends HttpServlet {

	private static final long serialVersionUID = 110533133254086356L;

	private static final Logger s_log = LogManager.getLogger();

	@Override
	protected void doPost(final HttpServletRequest req, final HttpServletResponse resp) {

		s_log.info("Received login credential, and checking with LeanCloud for verification..");
		
		final String username = req.getParameter(AuthUtil.p_LOGIN_USERNAME),
				pw = req.getParameter(AuthUtil.p_LOGIN_PASSCODE);
		
		try {
			User.create()
				.username(username)
				.passcode(pw)
				.login();
			
			s_log.info(username + " successfully logged in.. ");
			req.setAttribute(AuthUtil.a_IS_SUCCESS, true);
			ServletHelper.dispatchTo(AuthUtil.MAIN_PAGE, req, resp);
		}catch(AVException e) {
			s_log.error("Username: " + username + " login failed with code: " 
						 + e.getCode() + " - " + e.getMessage());
			
			req.setAttribute(AuthUtil.a_IS_SUCCESS, false);
			req.setAttribute(AuthUtil.a_REJECT_REASON, e.getCode());
			ServletHelper.dispatchTo(AuthUtil.LOGIN_PAGE, req, resp);
		}catch(Exception e) {
			s_log.error("Unsupported values are provided, or unknown serverside issue");
			e.printStackTrace();
		}
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) {
		s_log.info("Checking if user is logged in..");
		AVUser currentUser = AVUser.getCurrentUser();
		
		if (currentUser != null) {
			// Redirect to Main page.
			s_log.info("Client was logged in as :", currentUser.getUsername(), " with detail: ", currentUser);
			ServletHelper.dispatchTo(AuthUtil.MAIN_PAGE, req, resp);
		} else {
			// Navigate back to Login page.
			s_log.info("No session in cloud match with client. Redirect to login page.");
			ServletHelper.dispatchTo(AuthUtil.LOGIN_PAGE, req, resp);
		}
	}
}

//switch(e.getCode()) {
//case AVException.CONNECTION_FAILED:
//	rejectReason = "云端连接中断，请稍后再试。";
//case AVException.USER_DOESNOT_EXIST:
//	rejectReason = "找不到该用户";
//case AVException.USERNAME_PASSWORD_MISMATCH:
//	rejectReason = "用户名与密码无法匹配";
//default: 
//	rejectReason = "未知错误";
//}