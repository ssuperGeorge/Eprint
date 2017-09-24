package com.eprint.pms;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jivesoftware.smack.ConnectionListener;
import org.jivesoftware.smack.XMPPConnection;

public class EprintClientConnectionListener implements ConnectionListener{
	
	private final Logger s_log = LogManager.getLogger();
	
	@Override
	public void authenticated(XMPPConnection connection, boolean arg1) {
		s_log.info("logged in: " + connection.getUser().asBareJid());
	}

	@Override
	public void connected(XMPPConnection connection) {
		s_log.info("Connected to server at host:" + connection.getHost()+":"+connection.getPort());
	}

	@Override
	public void connectionClosed() {
		s_log.info("Connection closed" );
	}

	@Override
	public void connectionClosedOnError(Exception exception) {
		s_log.error("Connection closed in error..");
		exception.printStackTrace();
	}

	@Override
	public void reconnectingIn(int arg0) {
		
	}

	@Override
	public void reconnectionFailed(Exception exception) {
		
	}

	@Override
	public void reconnectionSuccessful() {
		
	}
}
