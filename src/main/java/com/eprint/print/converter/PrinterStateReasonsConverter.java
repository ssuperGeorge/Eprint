package com.eprint.print.converter;

import java.util.Map;

import javax.print.attribute.standard.PrinterStateReason;
import javax.print.attribute.standard.PrinterStateReasons;
import javax.print.attribute.standard.Severity;

import com.eprint.print.AttributeClass;
import com.eprint.util.AttributeConverter;

public class PrinterStateReasonsConverter implements AttributeConverter<PrinterStateReasons>{

	private static final String PRINTER_STATE_DELIMITER = "-";
	
	@Override
	public PrinterStateReasons get(String from, Map<String, AttributeClass> map) {
		PrinterStateReasons reasons = new PrinterStateReasons();
		AttributeClass ac = (map != null) ? map.get("printer-state-reasons"): null;
		String[] printerState;
		if(ac!=null && ( printerState = ac.getArrayOfStringValues())!=null) {
			for(String state : printerState) {
//				String[] pair = state.split(PRINTER_STATE_DELIMITER);
//				String stateReason = pair[0], severity = pair[1];
//				if(StringUtil.isEmpty(stateReason)|| StringUtil.isEmpty(severity)) {
//					continue;
//				}
//				reasons.put(getPrinterStateReason(stateReason), getSeverity(severity));
			}
		}
		return reasons;
	}
	
	private Severity getSeverity(String severity) {
		switch(severity) {
		case "report":
			return Severity.REPORT;
		case "error":
			return Severity.ERROR;
		case "warning":
			return Severity.WARNING;
		}
		return Severity.REPORT;
	}
	
	private PrinterStateReason getPrinterStateReason(String reason) {
		switch(reason) {
		case "offline":
			return PrinterStateReason.SHUTDOWN;
		}
		return PrinterStateReason.OTHER;
	}

}
