package com.eprint.print.converter;

import java.net.URI;
import java.util.HashMap;
import java.util.Map;

import javax.print.attribute.standard.ColorSupported;
import javax.print.attribute.standard.PDLOverrideSupported;
import javax.print.attribute.standard.PrinterIsAcceptingJobs;
import javax.print.attribute.standard.PrinterName;
import javax.print.attribute.standard.PrinterState;
import javax.print.attribute.standard.PrinterStateReasons;
import javax.print.attribute.standard.PrinterURI;
import javax.print.attribute.standard.QueuedJobCount;

import com.eprint.datamodel.PrinterInfo;
import com.eprint.util.AttributeConverter;


public class PrintServiceAttributeMap {
	private final static Map<Class<?>, AttributeConverter<?>> map = new HashMap<>();
	
	private final String m_printerName;
	
	private final URI m_uri;
	
	private PrintServiceAttributeMap(String printerName, URI URI) {
		m_printerName = printerName;
		m_uri = URI;
		initMap();
	}
	
	private void initMap() {
		map.put(PrinterInfo.class, new PrinterInfoConverter(m_printerName));
		map.put(PrinterName.class, new PrinterNameConverter(m_printerName));
		map.put(PrinterURI.class	, new PrinterURIConverter(m_uri));
		map.put(PrinterState.class, new PrinterStateConverter());
		map.put(PrinterStateReasons.class, new PrinterStateReasonsConverter());
		map.put(PDLOverrideSupported.class, new PDLOverrideSupportedConverter());
		map.put(ColorSupported.class, new ColorSupportedConverter());
		map.put(PrinterIsAcceptingJobs.class, new PrinterIsAcceptingJobsConverter());
		map.put(QueuedJobCount.class, new QueuedJobCountConverter());
	}
	
	@SuppressWarnings("unchecked")
	public  <T> AttributeConverter<T> get(Class<T> clazz){
		if(map.containsKey(clazz)) {
			return (AttributeConverter<T>) map.get(clazz);
		}
		return null;
	}
	
	public static PrintServiceAttributeMap getMap(String name, URI uri) {
		return new PrintServiceAttributeMap(name, uri);
	}
}
