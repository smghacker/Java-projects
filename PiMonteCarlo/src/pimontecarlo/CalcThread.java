package pimontecarlo;

import java.util.Calendar;
import java.util.Random;
import java.util.concurrent.Callable;

public class CalcThread implements Callable<CalcResult> {
	int threadNumber;
	private Random rndGenerator;
	private long numberOfPoints;
	private boolean toLog;

	public CalcThread(int threadNumber, Random rndGenerator, long numberOfPoints, boolean toLog) {
		this.threadNumber = threadNumber;
		this.rndGenerator = rndGenerator;
		this.numberOfPoints = numberOfPoints;
		this.toLog = toLog;
	}

	@Override
	public CalcResult call() throws Exception {
		CustomLogger.log("Thread-" + threadNumber + " started.", toLog);
		CalcResult result = new CalcResult();
		long begin = Calendar.getInstance().getTimeInMillis();
		for (long i = 0; i < numberOfPoints; i++) {
			double x = rndGenerator.nextDouble();
			double y = rndGenerator.nextDouble();

			if (x * x + y * y <= 1.0) {
				result.incrementNumberOfPointsInCircle();
			}
		}
		CustomLogger.log("Thread-" + threadNumber + " ended.", toLog);
		long end = Calendar.getInstance().getTimeInMillis();
		long executionTime = end - begin;
		CustomLogger.log("Thread-" + threadNumber + " execution time: " + executionTime, toLog);
		result.setTimeInMilliseconds(executionTime);
		return result;
	}

}
