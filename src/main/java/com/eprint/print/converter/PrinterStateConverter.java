package com.eprint.print.converter;

import java.util.Map;

import javax.print.attribute.standard.PrinterState;

import com.eprint.print.AttributeClass;
import com.eprint.util.AttributeConverter;

public class PrinterStateConverter implements AttributeConverter<PrinterState>{

	@Override
	public PrinterState get(String from, Map<String, AttributeClass> map) {
		PrinterState ps = PrinterState.UNKNOWN;
		AttributeClass ac = (map != null) ? map.get(ps.getName()): null;
		if (ac != null) {
			switch(ac.getIntValue()) {
			case 0:
				ps = PrinterState.UNKNOWN;
				break;
			case 3:
				ps = PrinterState.IDLE;
				break;
			case 4: 
				ps = PrinterState.PROCESSING;
				break;
			case 5:
				ps = PrinterState.STOPPED;
			}
		}
		return ps;
	}
}
