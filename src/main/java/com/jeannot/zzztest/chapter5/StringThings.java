package com.jeannot.zzztest.chapter5;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Review of the String handling
 * Chapter 5 of the OCP book
 * @author Paul
 *
 */
public class StringThings {
	
	private static final int THREADS = 10;

	private int value = 0;

	private static ExecutorService service = Executors.newScheduledThreadPool(THREADS);
	
	public static void main(String[] args) {
		
		StringThings me = new StringThings();
		me.runStuff();
		
		service.shutdown();
		
	}
	
	private void runStuff() {
		for (int i=0; i<THREADS; i++) {
			Runnable r = new Runnable() {

				@Override
				public void run() {
					int myValue = value;
					sleep(10L);
					updateValue();
					System.out.println(value);
				}
				
			};
			service.submit(r);
		}
		
	}

	private synchronized void updateValue(int newValue) {
		value = newValue;
	}
	
	private synchronized void updateValue() {
		value++;
	}
	
	private static void sleep(long ms) {
		try {
			Thread.sleep(ms);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
