package com.eprint.pms;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.avos.avoscloud.internal.impl.JavaRequestSignImplementation;
import com.eprint.pms.EprintServer.EprintServerFactory;


@WebListener
public class AppInitListener implements ServletContextListener {

  private static final Logger logger = LogManager.getLogger(AppInitListener.class);

  @Override
  public void contextDestroyed(ServletContextEvent event) {}

  @Override
  public void contextInitialized(ServletContextEvent event) {
	  
    logger.info("LeanEngine app init.");
    // 初始化AVOSCloud，请保证在整个项目中间只初始化一次
    ServletContext context = event.getServletContext();
    EprintServer server = EprintServerFactory.instance.create(context);
    	server.init();
    	
    // 在请求签名中使用masterKey以激活云代码的最高权限
    JavaRequestSignImplementation.instance().setUseMasterKey(true);

  }
}
