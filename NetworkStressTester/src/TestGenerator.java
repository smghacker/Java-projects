
public class TestGenerator implements ITestGenerator{
	private int maxNumberOfRequests;
	private int minNumberOfRequests;
	private int currentNumberOfRequests;

	public TestGenerator(int maxNumberOfRequests) {
		this.maxNumberOfRequests = maxNumberOfRequests;
		this.minNumberOfRequests = 1;
	}
	
	@Override
	public int nextNumberOfRequests(boolean previousTestOutcome){
		if(previousTestOutcome){
			minNumberOfRequests = currentNumberOfRequests + 1;
		} else {
			maxNumberOfRequests = currentNumberOfRequests - 1;
		}
		currentNumberOfRequests = (maxNumberOfRequests + minNumberOfRequests) / 2;		
		return currentNumberOfRequests;
	}
	
	@Override
	public boolean hasNextTest(){
		return maxNumberOfRequests > minNumberOfRequests;
	}
}
