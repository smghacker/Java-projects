package com.vmware.garbagecollector;

import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MainApp {

	private static final int NUM_OF_THREADS = 10;
	private static final ExecutorService executor = Executors.newFixedThreadPool(NUM_OF_THREADS);
	public static void main(String[] args) throws IOException {

		for(int i = 1; i <= NUM_OF_THREADS; i++){
			executor.execute(new SectorCleanerThread(Integer.toString(i)));			
		}
		
		executor.shutdown();
	}
}
