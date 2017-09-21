package com.eprint.print.converter;

import java.util.Map;

import javax.print.attribute.standard.PrinterStateReasons;

import com.eprint.print.AttributeClass;
import com.eprint.util.AttributeConverter;

public class PrinterStateReasonsConverter implements AttributeConverter<PrinterStateReasons>{

	private static final String PRINTER_STATE_DELIMITER = "-";
	
	@Override
	public PrinterStateReasons get(String from, Map<String, AttributeClass> map) {
		return null;
	}

}
