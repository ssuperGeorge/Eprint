package com.eprint.pms;


import javax.print.PrintService;
import javax.print.attribute.standard.PrinterState;
import javax.print.attribute.standard.PrinterStateReason;
import javax.print.attribute.standard.PrinterStateReasons;
import javax.print.event.PrintServiceAttributeEvent;
import javax.print.event.PrintServiceAttributeListener;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.eprint.print.UnixPrintServiceLookup;

public class PrintServiceProvider {
	
	private final Logger s_log = LogManager.getLogger();
	
	private PrintService m_ps;
	
	private final PMSSender m_sender;
	
	public static PrintServiceProvider create(PMSSender sender ) {
		return new PrintServiceProvider(sender);
	}
	
	public PrintServiceProvider(PMSSender sender) {
		m_sender = sender;
	}
	
	public boolean init() {
		UnixPrintServiceLookup psLookup = new UnixPrintServiceLookup();
		PrintService[] services = psLookup.getPrintServices();
		String defaultPrinterName = "EPSON WF-3520 Series";
		s_log.info(services.length + " services are discovered.");
		for(PrintService service : services) {
			if(defaultPrinterName.equals(service.getName())) {
				m_ps = service;
			}
		}
		return m_ps != null;
	}
	
	public void start()	{
		System.out.println(m_ps.getAttribute(PrinterStateReasons.class));
		m_ps.addPrintServiceAttributeListener(new PrintServiceAttributeListener() {
			@Override
			public void attributeUpdate(PrintServiceAttributeEvent psae) {
			}
		});
	}
}
