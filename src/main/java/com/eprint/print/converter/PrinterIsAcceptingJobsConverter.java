package com.eprint.print.converter;


import java.util.Map;

import javax.print.attribute.standard.PrinterIsAcceptingJobs;

import com.eprint.print.AttributeClass;
import com.eprint.util.AttributeConverter;

public class PrinterIsAcceptingJobsConverter implements AttributeConverter<PrinterIsAcceptingJobs>{

	@Override
	public PrinterIsAcceptingJobs get(String from, Map<String, AttributeClass> map) {
		PrinterIsAcceptingJobs accJob =
				PrinterIsAcceptingJobs.ACCEPTING_JOBS;
		AttributeClass ac = (map != null) ?
				map.get(accJob.getName())
				: null;
				if ((ac != null) && (ac.getByteValue() == 0)) {
					accJob = PrinterIsAcceptingJobs.NOT_ACCEPTING_JOBS;
				}
				return accJob;
	}

}
