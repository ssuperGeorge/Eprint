package com.eprint.servlet;

import java.util.Enumeration;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@WebServlet(urlPatterns = {"/main"})
public class EprintServlet extends HttpServlet {

	private static final long serialVersionUID = 5905146030325252080L;

	private static final Logger s_log = LogManager.getLogger();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) {
		s_log.info("User checked in. ");
		
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) {
		s_log.info("User logged in.. ");
		
		Enumeration<String> tmp  = req.getAttributeNames();
		while(tmp.hasMoreElements()) {
			String a = tmp.nextElement();
			System.out.println(a + "+++++++++"+ req.getAttribute(a));
		}
	}
}