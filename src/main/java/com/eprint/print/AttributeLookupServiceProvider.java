package com.eprint.print;

import java.util.Map;

import javax.print.attribute.Attribute;
import javax.print.attribute.HashPrintServiceAttributeSet;
import javax.print.attribute.PrintServiceAttributeSet;

import com.eprint.print.converter.PrintServiceAttributeMap;
import com.eprint.util.AttributeConverter;

public class AttributeLookupServiceProvider implements AttributeLookupService{

	private final PrintServiceAttributeMap m_attrMap;
	
	private PrintServiceAttributeSet m_currAttribute, m_prevAttribute;
	
	private AttributeLookupServiceProvider(PrintServiceAttributeMap attrMap) {
		m_attrMap = attrMap;
	}
	
	@Override
	public <T> T getAttribute(Class<T> clazz, Map<String, AttributeClass> map) {
		AttributeConverter<T> converter = m_attrMap.get(clazz);
		if(converter!=null) {
			IPPServiceAttribute attr = IPPServiceAttribute.valueOf(clazz);
			if(attr!=null) {
				return converter.get(attr.getName(), map);
			}
		}
		return null;
	}
	
	@Override
	public void updateAttribute(PrintServiceAttributeSet set) {
		m_prevAttribute = m_currAttribute;
		m_currAttribute = set;
	}
	
	@Override
	public PrintServiceAttributeSet getAttributeDiff(PrintServiceAttributeSet attrSet) {
		PrintServiceAttributeSet diffSet = new HashPrintServiceAttributeSet();
		
		for(Attribute attr : m_currAttribute.toArray()) {
			if(!m_prevAttribute.containsValue(attr)) {
				diffSet.add(attr);
			}
		}
		return diffSet;
	}

	@Override
	public PrintServiceAttributeSet getCurrentAttribute() {
		return m_currAttribute;
	}
	
	public static AttributeLookupServiceProvider create(PrintServiceAttributeMap attrMap) {
		return new AttributeLookupServiceProvider(attrMap);
	}
}