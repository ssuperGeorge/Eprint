package com.eprint.print;

import javax.print.attribute.standard.*;

public enum IPPServiceAttribute {
	ColorSupported(ColorSupported.class, "color-supported"),
	PagesPerMinute(PagesPerMinute.class,  "pages-per-minute"),
	PagesPerMinuteColor(PagesPerMinuteColor.class, "pages-per-minute-color"),
	PDLOverrideSupported(PDLOverrideSupported.class, "pdl-override-supported"),
	PrinterInfo(PrinterInfo.class, "printer-info"),
	PrinterIsAcceptingJobs(PrinterIsAcceptingJobs.class, "printer-is-accepting-jobs"),
	PrinterLocation(PrinterLocation.class, "printer-location"),
	PrinterMakeAndModel(PrinterMakeAndModel.class, "printer-make-and-model"),
	PrinterMessageFromOperator(PrinterMessageFromOperator.class, "printer-message-from-operator"),
	PrinterMoreInfo(PrinterMoreInfo.class, "printer-more-info"),
	PrinterMoreInfoManufacturer(PrinterMoreInfoManufacturer.class, "printer-more-info-manufacturer"),
	PrinterName(PrinterName.class, "printer-name"),
	PrinterState(PrinterState.class, "printer-state"),
	PrinterStateReasons(PrinterStateReasons.class, "printer-state-reasons"),
	PrinterURI(PrinterURI.class, "printer-uri"),
	QueuedJobCount(QueuedJobCount.class, "queued-job-count");
	
	IPPServiceAttribute(Class<?> clazz, String name){
		m_class = clazz;
		m_name = name;
	}
	
	@SuppressWarnings("unchecked")
	public <T> Class<T> getClazz(){
		return (Class<T>) m_class;
	}
	
	public String getName() {
		return m_name;
	} 
	
	private Class<?> m_class; 
	private String m_name;
	
	public static <T> IPPServiceAttribute valueOf(Class<T> clazz) {
		if(clazz == PrinterStateReasons.class) {
			return PrinterStateReasons;
		}
		else if(clazz == ColorSupported.class) {
			return ColorSupported;
		}
		else if(clazz == PagesPerMinute.class) {
			return PagesPerMinute;
		}
		else if(clazz == PagesPerMinuteColor.class) {
			return PagesPerMinuteColor;
		}
		else if(clazz == PDLOverrideSupported.class) {
			return PDLOverrideSupported;
		}
		else if(clazz == PrinterInfo.class) {
			return PrinterInfo;
		}
		else if(clazz == PrinterIsAcceptingJobs.class) {
			return PrinterIsAcceptingJobs;
		}
		else if(clazz == PrinterLocation.class) {
			return PrinterLocation;
		}
		else if(clazz == PrinterMakeAndModel.class) {
			return PrinterMakeAndModel;
		}
		else if(clazz == PrinterMessageFromOperator.class) {
			return PrinterMessageFromOperator;
		}
		else if(clazz == PrinterMoreInfo.class) {
			return PrinterMoreInfo;
		}
		else if(clazz == PrinterMoreInfoManufacturer.class) {
			return PrinterMoreInfoManufacturer;
		}
		else if(clazz == PrinterName.class) {
			return PrinterName;
		}
		else if(clazz == PrinterState.class) {
			return PrinterState;
		}
		else if(clazz == PrinterURI.class) {
			return PrinterURI;
		}
		else if(clazz == QueuedJobCount.class) {
			return QueuedJobCount;
		}
		return null;
	};
}
