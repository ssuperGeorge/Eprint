package com.eprint.datamodel;

public class PrinterInfo {
	private String m_status;
	
	public PrinterInfo printerStatus(String status) {
		m_status = status;
		return this;
	}
	
	public String printerStatus() {
		return m_status;
	}
}
