package com.eprint.print.converter;

import java.util.Map;

import javax.print.attribute.standard.ColorSupported;

import com.eprint.print.AttributeClass;
import com.eprint.util.AttributeConverter;

public class ColorSupportedConverter implements AttributeConverter<ColorSupported>{

	@Override
	public ColorSupported get(String from, Map<String, AttributeClass> map) {
		ColorSupported cs = ColorSupported.SUPPORTED;
		AttributeClass ac = (map != null) ?map.get(cs.getName()) : null;
		if ((ac != null) && (ac.getByteValue() == 0)) {
			cs = ColorSupported.NOT_SUPPORTED;
		}
		return cs;
	}
}
