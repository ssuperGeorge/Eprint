package com.eprint.print.converter;


import java.util.Map;

import javax.print.attribute.standard.QueuedJobCount;

import com.eprint.print.AttributeClass;
import com.eprint.util.AttributeConverter;

public class QueuedJobCountConverter implements AttributeConverter<QueuedJobCount>{

	@Override
	public QueuedJobCount get(String from, Map<String, AttributeClass> map) {
		QueuedJobCount qjc = new QueuedJobCount(0);
		AttributeClass ac = (map != null) ?
				map.get(qjc.getName())
				: null;
				if (ac != null) {
					qjc = new QueuedJobCount(ac.getIntValue());
				}
				return qjc;
	}

}
