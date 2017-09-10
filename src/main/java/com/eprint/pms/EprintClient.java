package com.eprint.pms;

import java.net.InetAddress;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jivesoftware.smack.AbstractXMPPConnection;
import org.jivesoftware.smack.ConnectionConfiguration.SecurityMode;
import org.jivesoftware.smack.tcp.XMPPTCPConnection;
import org.jivesoftware.smack.tcp.XMPPTCPConnectionConfiguration;
import org.jxmpp.stringprep.XmppStringprepException;

public class EprintClient {
	private final Logger s_log = LogManager.getLogger();

	private PrintServiceProvider m_psp;

	public void init() {
		// Connect to xmpp server for printer management
		AbstractXMPPConnection connection = null;
		try {
			XMPPTCPConnectionConfiguration.Builder configBuilder = XMPPTCPConnectionConfiguration.builder()
					.setXmppDomain("eprint")
					.setHostAddress(InetAddress.getByName("localhost"))
					.setPort(5222)
					.setUsernameAndPassword("gzheng30", "123123")
					.setSecurityMode(SecurityMode.disabled);
			connection = new XMPPTCPConnection(configBuilder.build());
			connection.addConnectionListener(new EprintClientConnectionListener());

			s_log.info("Start connecting to host: "+connection.getHost()+" at port: "+ connection.getPort());
			connection.connect();

			s_log.info("Connection established, start logging in");
			connection.login();
		} catch  (XmppStringprepException e){
			s_log.error("Error when parsing server domain name.");
			e.printStackTrace();
		}catch(Exception e) {
			s_log.error("Failed to connect to server or login");
			e.printStackTrace();
		}

		// Local printer discovery
		if(connection !=null) {
			s_log.info("Start to discover any local printer");
			PMSSender sender = new PMSSenderImpl(connection);
			m_psp = PrintServiceProvider.create(sender);
			if(m_psp.init()) {
				m_psp.start();
			}else {
				s_log.error("Cannot detect any local printer" );
			}
		}
	}

	public enum EprintServerFactory{
		instance;

		public EprintClient create() {
			return new EprintClient();
		}
	}
}
