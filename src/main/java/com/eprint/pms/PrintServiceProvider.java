package com.eprint.pms;

import javax.print.PrintService;
import javax.print.attribute.PrintServiceAttributeSet;
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
		String defaultPrinterName = "EPSON_WF_3520_Series";
		s_log.info(services.length + " services are discovered.");
		for(PrintService service : services) {
			if(defaultPrinterName.equals(service.getName())) {
				m_ps = service;
			}
		}
		return m_ps != null;
	}
	
	public void start()	{
		m_ps.addPrintServiceAttributeListener(new PrintServiceAttributeListener() {
			final Class<PrinterStateReasons> m_category = PrinterStateReasons.class;
			
			@Override
			public void attributeUpdate(PrintServiceAttributeEvent psae) {
				PrintServiceAttributeSet attrSet = psae.getAttributes();
				if(attrSet.containsKey(m_category)) {
					System.out.println(attrSet.get(m_category));
				}
			}
		});
	}
}
