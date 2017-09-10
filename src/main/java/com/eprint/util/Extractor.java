package com.eprint.util;

public interface Extractor<F, T> {

	public T get(F from);
}
