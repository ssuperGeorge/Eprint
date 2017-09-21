package com.eprint.print.converter;

import java.util.Map;

import javax.print.attribute.standard.PrinterName;

import com.eprint.print.AttributeClass;
import com.eprint.util.AttributeConverter;

public class PrinterNameConverter implements AttributeConverter<PrinterName>{

	private String m_name;
	
	public PrinterNameConverter(String name) {
		m_name = name;
	}
	
	@Override
	public PrinterName get(String from, Map<String, AttributeClass> map) {
		return new PrinterName(m_name, null);
	}
}
