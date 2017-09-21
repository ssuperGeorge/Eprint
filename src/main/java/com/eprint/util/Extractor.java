package com.eprint.util;

public interface Extractor<T, U> {
	public U get(T t);
}
