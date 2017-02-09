

public interface ITestExecutor {
	void initTest(String hostname, int numberOfExperiments, String resultsDestination);
	public String execute() throws InterruptedException;
}
