package com.eprint.util;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ServletHelper {
	
	private static final Logger s_log =  LogManager.getLogger();
	
	public static void dispatchTo(String path, HttpServletRequest req, HttpServletResponse resp) {
		try {
			s_log.info("Redirecting to path: "+path);
			req.getRequestDispatcher(path).forward(req, resp);
		}catch(Exception e) {
			s_log.error("Error when redirecting to path: "+ path);
			e.printStackTrace();
		}
	}
}
