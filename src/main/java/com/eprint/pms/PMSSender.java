package com.eprint.pms;

import com.eprint.datamodel.PrinterInfo;
import com.eprint.util.Callback;

public interface PMSSender {
	public void sendStateChange(PrinterInfo info, Callback callback);
	
}
