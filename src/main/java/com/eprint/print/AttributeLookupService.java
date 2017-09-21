package com.eprint.print;

import java.util.Map;

public interface AttributeLookupService {

	public <T> T getAttribute(Class<T> clazz, Map<String, AttributeClass> map);
	
}
