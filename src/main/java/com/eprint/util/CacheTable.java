package com.eprint.util;

import java.util.HashMap;

public class CacheTable {
	private final HashMap<String, String> internalTable;
	
	public CacheTable() {
		internalTable = new HashMap<>();
	}
}
