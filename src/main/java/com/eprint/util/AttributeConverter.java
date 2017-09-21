package com.eprint.util;

import java.util.Map;

import com.eprint.print.AttributeClass;

public interface AttributeConverter<T> {

	public T get(String from, Map<String, AttributeClass> map);
}
