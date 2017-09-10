package com.eprint.pms;

import javax.print.PrintService;

public class PrintServiceProvider {
	
	private PrintService m_ps;
	
	private final PMSSender m_sender;
	
	public static PrintServiceProvider create(PMSSender sender ) {
		return new PrintServiceProvider(sender);
	}
	
	public PrintServiceProvider(PMSSender sender) {
		m_sender = sender;
	}
	
	public boolean init() {
		return false;
	}
	
	public void start() {
		
	}
	
}
