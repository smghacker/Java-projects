package com.vmware.multithreading;

import java.io.File;

public class CounterThread extends Thread {
	private String path;
	public CounterThread(String path){
		this.path = path;
	}
	public void run(){
		FilesCounter filesCounter = new FilesCounter();
		long dirBegin = System.currentTimeMillis();
		long dirCount = filesCounter.countFiles(path);
		long dirEnd = System.currentTimeMillis();
		System.out.println();
		System.out.println("Traversing " + path + " time: " + (dirEnd - dirBegin) +  "Number of files: " + dirCount);
	}
}
