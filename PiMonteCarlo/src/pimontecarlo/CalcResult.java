package pimontecarlo;

public class CalcResult {
	private long numberOfPointsInCircle;
	private long timeInMilliseconds;

	public CalcResult() {
		numberOfPointsInCircle = 0L;
		timeInMilliseconds = 0L;
	}

	public long getNumberOfPointsInCircle() {
		return numberOfPointsInCircle;
	}

	public void incrementNumberOfPointsInCircle() {
		numberOfPointsInCircle++;
	}

	public long getTimeInMilliseconds() {
		return timeInMilliseconds;
	}

	public void setTimeInMilliseconds(long timeInMilliseconds) {
		this.timeInMilliseconds = timeInMilliseconds;
	}
}
