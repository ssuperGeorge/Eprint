package com.eprint.servlet;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.avos.avoscloud.AVException;
import com.eprint.datamodel.User;
import com.eprint.util.AuthUtil;
import com.eprint.util.ServletHelper;

public class SignupServlet extends HttpServlet {

	private static final long serialVersionUID = -7253881542777614097L;

	private static final Logger s_log = LogManager.getLogger();

	@Override
	protected void doPost(final HttpServletRequest req, final HttpServletResponse resp) {

		s_log.info("Received signup information, and checking with LeanCloud for verification..");

		final String username = req.getParameter(AuthUtil.p_SIGNUP_USERNAME),
				pw = req.getParameter(AuthUtil.p_SIGNUP_PASSCODE), 
				email = req.getParameter(AuthUtil.p_SIGNUP_EMAIL);
		try {
			User.create().username(username)
			.passcode(pw)
			.emailAddress(email)
			.signUp();

			s_log.info(username + " successfully logged in.. ");
			ServletHelper.dispatchTo(AuthUtil.MAIN_PAGE, req, resp);
			
		}catch(AVException e) {
			req.setAttribute(AuthUtil.a_REJECT_REASON, e.getCode());
			ServletHelper.dispatchTo(AuthUtil.LOGIN_PAGE, req, resp);
			s_log.warn("Failed to sign up for user:"+username+", "+email+" with code: "
						+ e.getCode()+ " - "+ e.getMessage());
			
		}catch(Exception e) {
			s_log.error("Unsupported values are provided, or unknown serverside issue");
			e.printStackTrace();
		}
	}
}
