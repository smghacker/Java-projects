package pimontecarlo;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadLocalRandom;

public class CalcExecutor {
	private int numberOfThreads;
	private boolean quietMode;
	private long numberOfPoints;
	private Random rndGenerator;
	private ExecutorService executor;
	
	public CalcExecutor(long numberOfPoints, int numberOfThreads, boolean quietMode, boolean randomThreadLocal) {
		super();
		this.numberOfPoints = numberOfPoints;
		this.numberOfThreads = numberOfThreads;
		this.quietMode = quietMode;
		if(randomThreadLocal){
			rndGenerator = ThreadLocalRandom.current();
		} else {
			rndGenerator = new Random();
		}
		this.executor = Executors.newFixedThreadPool(numberOfThreads);
	}
	
	public void execute(){
		List<Callable<CalcResult>> tasks = new ArrayList<>();
		long baseNumber = numberOfPoints/numberOfThreads;
		long rest = numberOfPoints%numberOfThreads;
		for(int i = 0; i < numberOfThreads-1; i++){
			tasks.add(new CalcThread(i, rndGenerator, baseNumber, !quietMode));
		}
		tasks.add(new CalcThread(numberOfThreads-1, rndGenerator, baseNumber + rest, !quietMode));
		try {
			List<Future<CalcResult>> results = executor.invokeAll(tasks);
			long numberOfPointsInTheCircle = 0;
			long timeInMilliseconds = 0;
			for(int i = 0; i < results.size(); i++){
				CalcResult result = results.get(i).get();
				numberOfPointsInTheCircle += result.getNumberOfPointsInCircle();
				timeInMilliseconds = Math.max(timeInMilliseconds, result.getTimeInMilliseconds());
			}
			System.out.println((4.0*numberOfPointsInTheCircle)/numberOfPoints + " time: " + timeInMilliseconds);
		} catch (InterruptedException | ExecutionException e) {
			e.printStackTrace();
		}
	}
	
	
	public void shutDown(){
		executor.shutdown();
	}
	
}
