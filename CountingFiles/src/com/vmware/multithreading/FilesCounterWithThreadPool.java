package com.vmware.multithreading;

import java.util.concurrent.CompletionService;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorCompletionService;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class FilesCounterWithThreadPool {

	private static final int NUM_OF_THREADS = 2;
	private static final ExecutorService fixedPool = Executors
			.newFixedThreadPool(NUM_OF_THREADS);

	private static final CompletionService<String> completionService = new ExecutorCompletionService<String>(
			fixedPool);

	public static void main(String[] args) throws InterruptedException, ExecutionException {
		long programBegin = System.currentTimeMillis();
		String windowsPath = "C:\\Windows";
		String programFilesPath = "C:\\Program Files";
		CounterThreadCallable thread = new CounterThreadCallable(windowsPath);
		CounterThreadCallable thread2 = new CounterThreadCallable(programFilesPath);
		
		completionService.submit(thread);
		completionService.submit(thread2);
		Future<String> result = completionService.take();
		System.out.println(result.get());
		
		Future<String> result2 = completionService.take();
		System.out.println(result2.get());
		
		fixedPool.shutdown();
		long programEnd = System.currentTimeMillis();
		
		System.out.println("Program time: " + (programEnd - programBegin));
	}

}
