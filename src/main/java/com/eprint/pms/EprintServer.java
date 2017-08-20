package com.eprint.pms;

import javax.servlet.ServletContext;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import com.avos.avoscloud.AVOSCloud;
import com.avos.avoscloud.AVUser;
import com.eprint.datamodel.User;
import com.eprint.util.StringUtil;


public class EprintServer {
	
	private final String MASTERKEY = "v8jVH8Wrctc4CNO5eE0Jb2ML";
	
	private final ServletContext  m_context;
	
	private final Logger s_log = LogManager.getLogger();
	
	public EprintServer(ServletContext context) {
		m_context = context;
	}
	
	public void init() {
		String appId = m_context.getInitParameter("AppID"), 
			   appKey = m_context.getInitParameter("AppKey");
		
		if(StringUtil.isEmpty(appId) || StringUtil.isEmpty(appKey)) {
			//log not enough cloud info
			s_log.error("App info is missing");
			return;
		}
		
		//Register Subclasses
		AVUser.alwaysUseSubUserClass(User.class);
		
		// init lean cloud
		AVOSCloud.useAVCloudUS();
		AVOSCloud.initialize(appId,appKey,MASTERKEY);
	}

	public enum EprintServerFactory{
		instance;
		
		public EprintServer create(ServletContext context) {
			return new EprintServer(context);
		}
	}
}
