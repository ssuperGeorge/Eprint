
package com.eprint.print;

import java.util.ArrayList;
import java.util.List;

import javax.print.PrintService;
import javax.print.attribute.PrintServiceAttributeSet;
import javax.print.attribute.HashPrintServiceAttributeSet;
import javax.print.event.PrintServiceAttributeEvent;
import javax.print.event.PrintServiceAttributeListener;

/*
 * A utility class usable by all print services for managing listeners
 * The services create an instance and delegate the listener callback
 * management to this class. The ServiceNotifier calls back to the service
 * to obtain the state of the attributes and notifies the listeners of
 * any changes.
 */
class ServiceNotifier extends Thread {

	private PrintService service;
	private List<PrintServiceAttributeListener> listeners;
	private boolean stop = false;

	ServiceNotifier(PrintService service) {
		super(service.getName() + " notifier");
		this.service = service;
		listeners = new ArrayList<>();
		try {
			setPriority(Thread.NORM_PRIORITY-1);
			setDaemon(true);
			start();
		} catch (SecurityException e) {
		}
	}

	void addListener(PrintServiceAttributeListener listener) {
		synchronized (this) {
			if (listener == null || listeners == null) {
				return;
			}
			listeners.add(listener);
		}
	}

	void removeListener(PrintServiceAttributeListener listener) {
		synchronized (this) {
			if (listener == null || listeners == null) {
				return;
			}
			listeners.remove(listener);
		}
	}

	boolean isEmpty() {
		return (listeners == null || listeners.isEmpty());
	}

	void stopNotifier() {
		stop = true;
	}

	/* If a service submits a job it may call this method which may prompt
	 * immediate notification of listeners.
	 */
	void wake() {
		try {
			interrupt();
		} catch (SecurityException e) {
		}
	}

	/* A heuristic is used to calculate sleep time.
	 * 10 times the time taken to loop through all the listeners, with
	 * a minimum of 15 seconds. Ensures this won't take more than 10%
	 * of available time.
	 */
	public void run() {
		long minSleepTime = 15000;
		long sleepTime = 2000;
		
		while (!stop) {
			try {
				Thread.sleep(sleepTime);
			} catch (InterruptedException e) {
			}
			synchronized (this) {
				if (listeners == null|| listeners.isEmpty()) {
					continue;
				}
				long startTime = System.currentTimeMillis();
				if (listeners != null) {
					
					PrintServiceAttributeSet psa;
					if(service instanceof AttributeUpdater) {
						psa = ((AttributeUpdater) service).updateAttribute(); 
					}else {
						psa = service.getAttributes();
					}
					
					if (psa != null && !psa.isEmpty()) {
						PrintServiceAttributeEvent attrEvent = new PrintServiceAttributeEvent(
														service, 
														new HashPrintServiceAttributeSet(psa));
						
						for (PrintServiceAttributeListener listener : listeners) {
							listener.attributeUpdate(attrEvent); 
						}
					}
				}
				sleepTime = (System.currentTimeMillis()-startTime)*10;
				if (sleepTime < minSleepTime) {
					sleepTime = minSleepTime;
				}
			}
		}
	}
}
