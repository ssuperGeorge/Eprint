package com.eprint.print;

import java.util.Map;

import javax.print.attribute.PrintServiceAttributeSet;

public interface AttributeLookupService {

	public <T> T getAttribute(Class<T> clazz, Map<String, AttributeClass> map);
	
	public void updateAttribute(PrintServiceAttributeSet set);
	
	public PrintServiceAttributeSet getCurrentAttribute();
	
	public PrintServiceAttributeSet getAttributeDiff(PrintServiceAttributeSet set);
	
}
