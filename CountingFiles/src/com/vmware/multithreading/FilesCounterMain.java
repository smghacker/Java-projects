package com.vmware.multithreading;

public class FilesCounterMain {

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub
		long programBegin = System.currentTimeMillis();
		FilesCounter filesCounter = new FilesCounter();
		
		String windowsPath = "C:\\Windows";
		String programFilesPath = "C:\\Program Files";
		
		/*long windowsDirBegin = System.currentTimeMillis();
		long windowsDirCount = filesCounter.countFiles(windowsPath);
		long windowsDirEnd = System.currentTimeMillis();
		
		long programFilesDirBegin = System.currentTimeMillis();
		long programFilesDirCount = filesCounter.countFiles(programFilesPath);
		long programFilesDirEnd = System.currentTimeMillis();*/
		CounterThread ct1 = new CounterThread(windowsPath);
		CounterThread ct2 = new CounterThread(programFilesPath);
		ct1.start();
		ct2.start();
		ct1.join();
		ct2.join();
		long programEnd = System.currentTimeMillis();
		
		System.out.println("Program time: " + (programEnd - programBegin));
	}

}
