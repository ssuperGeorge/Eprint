package com.eprint.datamodel;

import org.jivesoftware.smack.packet.IQ;
import org.jxmpp.jid.Jid;

public class PrinterState extends IQ{
	
	private final static String childElementName = "state";
	
	private final static String childElementNamespace = "eprint/printer/state";
	
	private String printerState = "Unknown";

	public PrinterState() {
		this(childElementName, childElementNamespace);
	}
	
	public PrinterState printerState(String state) {
		printerState = state;
		return this;
	}
	
	public PrinterState from(Jid id) {
		setFrom(id);
		return this;
	}
	
	public PrinterState to(Jid id) {
		setTo(id);
		return this;
	}
	
	protected PrinterState(String childElementName, String childElementNamespace) {
		super(childElementName, childElementNamespace);
	}

	@Override
	protected IQChildElementXmlStringBuilder getIQChildElementBuilder(IQChildElementXmlStringBuilder buf) {
		buf.append(" update='").append(printerState).append("'>");
		return buf;
	}
}
