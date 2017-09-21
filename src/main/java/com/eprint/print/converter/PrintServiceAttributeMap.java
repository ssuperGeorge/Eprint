package com.eprint.print.converter;

import java.net.URI;
import java.util.HashMap;
import java.util.Map;

import javax.print.attribute.standard.PrinterName;
import javax.print.attribute.standard.PrinterState;
import javax.print.attribute.standard.PrinterStateReason;
import javax.print.attribute.standard.PrinterURI;

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
		map.put(PrinterName.class, new PrinterNameConverter(m_printerName));
		map.put(PrinterURI.class	, new PrinterURIConverter(m_uri));
		map.put(PrinterState.class, new PrinterStateConverter());
		map.put(PrinterStateReason.class, new PrinterStateReasonsConverter());
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
