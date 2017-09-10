package com.eprint.pms;

import org.jivesoftware.smack.AbstractXMPPConnection;

public class PMSSenderImpl implements PMSSender{

	
	private final AbstractXMPPConnection m_connection;
	
	public PMSSenderImpl(AbstractXMPPConnection connection) {
		m_connection = connection;
	}
}
