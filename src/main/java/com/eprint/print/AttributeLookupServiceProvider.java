package com.eprint.print;

import java.util.Map;

import com.eprint.print.converter.PrintServiceAttributeMap;
import com.eprint.util.AttributeConverter;

public class AttributeLookupServiceProvider implements AttributeLookupService{

	private final PrintServiceAttributeMap m_attrMap;
	
	private AttributeLookupServiceProvider(PrintServiceAttributeMap attrMap) {
		m_attrMap = attrMap;
	}
	
	@Override
	public <T> T getAttribute(Class<T> clazz, Map<String, AttributeClass> map) {
		AttributeConverter<T> converter = m_attrMap.get(clazz);
		IPPServiceAttribute attr = IPPServiceAttribute.valueOf(clazz);
		if(attr!=null) {
			return converter.get(attr.getName(), map);
		}
		return null;
	}
	
	public static AttributeLookupServiceProvider create(PrintServiceAttributeMap attrMap) {
		return new AttributeLookupServiceProvider(attrMap);
	}
}
