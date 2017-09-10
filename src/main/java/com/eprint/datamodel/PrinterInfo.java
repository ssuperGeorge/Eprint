package com.eprint.datamodel;

public class PrinterInfo {
	private PrinterStatus m_status;
	
	public PrinterInfo printerStatus(PrinterStatus status) {
		m_status = status;
		return this;
	}
	
	public PrinterStatus printerStatus() {
		return m_status;
	}
	
	public void setStatus(PrinterStatus status) {
		m_status = status;
	}
	
	
}
