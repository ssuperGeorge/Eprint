package com.eprint.print.converter;

import java.util.Map;

import javax.print.attribute.standard.PDLOverrideSupported;
import com.eprint.print.AttributeClass;
import com.eprint.util.AttributeConverter;

public class PDLOverrideSupportedConverter implements AttributeConverter<PDLOverrideSupported>{

	@Override
	public PDLOverrideSupported get(String from, Map<String, AttributeClass> map) {
		return PDLOverrideSupported.NOT_ATTEMPTED;
	}

}
