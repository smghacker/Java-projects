package com.vmware.multithreading;

import java.util.concurrent.Callable;

public class CounterThreadCallable implements Callable<String> {

	private String path;

	public CounterThreadCallable(String path) {
		this.path = path;
	}

	@Override
	public String call() throws Exception {
		FilesCounter filesCounter = new FilesCounter();
		long dirBegin = System.currentTimeMillis();
		long dirCount = filesCounter.countFiles(path);
		long dirEnd = System.currentTimeMillis();
		String result = "Traversing " + path + " time: " + (dirEnd - dirBegin)
				+ " Number of files: " + dirCount;
		return result;
	}

}
