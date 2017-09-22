package com.eprint.print.converter;

import java.util.Map;

import javax.print.attribute.standard.PrinterInfo;

import com.eprint.print.AttributeClass;
import com.eprint.util.AttributeConverter;


public class PrinterInfoConverter implements AttributeConverter<PrinterInfo>{

	private String m_name;
	
	public PrinterInfoConverter(String name) {
		m_name = name;
	}
	
	@Override
	public PrinterInfo get(String from, Map<String, AttributeClass> map) {
		PrinterInfo pInfo = new PrinterInfo(m_name, null);
		AttributeClass ac = (map != null) ?
				map.get(pInfo.getName())
				: null;
				if (ac != null) {
					return new PrinterInfo(ac.getStringValue(), null);
				}
				return pInfo;
	}

}
