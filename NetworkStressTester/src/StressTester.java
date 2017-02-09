import java.io.IOException;

public class StressTester {
	private String hostname;
	private String resultsDestination;
	private String expectedValue;
	private int maxNumberOfRequestsToBeTried;
	private ITestGenerator testGenerator;
	private ITestExecutor testExecutor;
	private ITestChecker testChecker;

	public StressTester(String hostname, String resultsDestination, int maxNumberOfRequestsToBeTried,
			String expectedValue) {
		this.hostname = hostname;
		this.resultsDestination = resultsDestination;
		this.expectedValue = expectedValue;
		this.maxNumberOfRequestsToBeTried = maxNumberOfRequestsToBeTried;
		this.testGenerator = new TestGenerator(maxNumberOfRequestsToBeTried);
		this.testExecutor = new TestExecutor();
		this.testChecker = new TestChecker();
	}

	public int stressTest() {
		int result = maxNumberOfRequestsToBeTried;
		boolean previousTestOutcome = true;
		while (testGenerator.hasNextTest()) {
			int currentNumberOfRequests = testGenerator.nextNumberOfRequests(previousTestOutcome);
			testExecutor.initTest(hostname, currentNumberOfRequests,
					resultsDestination);
			try {
				String destination = testExecutor.execute();
				testChecker.initTestChecker(destination, expectedValue);
				previousTestOutcome = testChecker.isTestSuccessful();
				System.out.println(previousTestOutcome + " " + currentNumberOfRequests);
				if(previousTestOutcome){
					result = currentNumberOfRequests;
				}
			} catch (InterruptedException | IOException e) {
				System.err.println("Stress testing of " + hostname + " failed because: " + e.getMessage());
			}
		}
		
		return result;
	}
}