package com.eprint.pms;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jivesoftware.smack.AbstractXMPPConnection;
import org.jivesoftware.smack.SmackException.NotConnectedException;

import com.eprint.datamodel.PrinterInfo;
import com.eprint.datamodel.PrinterState;
import com.eprint.util.Callback;

public class PMSSenderImpl implements PMSSender{

	private final Logger s_log = LogManager.getLogger();
	
	private final AbstractXMPPConnection m_connection;
	
	public PMSSenderImpl(AbstractXMPPConnection connection) {
		m_connection = connection;
	}

	@Override
	public void sendStateChange(PrinterInfo info, Callback callback) {
		
		try {
			PrinterState ps = new PrinterState();
			ps.from(m_connection.getUser().asBareJid())
			  .printerState(info.printerStatus());
			
			s_log.info("Sending printer state update information to server, Print State: "+ps.toXML() );
			
			m_connection.sendStanza(ps);
		} catch (NotConnectedException e) {
			s_log.error("Server not connected");
			e.printStackTrace();
		} catch( InterruptedException e) {
			s_log.error("Communication interrupted");
			e.printStackTrace();
		}
	}
}
