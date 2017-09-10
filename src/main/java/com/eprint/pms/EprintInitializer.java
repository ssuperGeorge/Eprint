package com.eprint.pms;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import com.eprint.pms.EprintClient.EprintServerFactory;

public class EprintInitializer  {

	private static final Logger s_log = LogManager.getLogger(EprintInitializer.class);

	private void init() {
		s_log.info("Starting Eprint client and connecting to local print service");
		
		EprintClient client = EprintServerFactory.instance.create();
		client.init();
	}
	
	public static void main(String[] args) {
		EprintInitializer eprint = new EprintInitializer();
		eprint.init();
	}
}
