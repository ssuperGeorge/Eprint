package com.eprint.print.converter;

import java.net.URI;
import java.util.Map;

import javax.print.attribute.standard.PrinterURI;

import com.eprint.print.AttributeClass;
import com.eprint.util.AttributeConverter;

public class PrinterURIConverter implements AttributeConverter<PrinterURI>{
	
	private URI m_uri;
	
	public PrinterURIConverter(URI uri) {
		m_uri = uri;
	}

	@Override
	public PrinterURI get(String from, Map<String, AttributeClass> map) {
		return new PrinterURI(m_uri);
	}
	
	
}
