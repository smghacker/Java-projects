
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class TestChecker implements ITestChecker{
	private String pathToFileTestResults;
	private String expectedValue;
	
	@Override
	public void initTestChecker(String pathToFileTestResults, String expectedValue){
		this.pathToFileTestResults = pathToFileTestResults;
		this.expectedValue = expectedValue;
	}
	
	@Override
	public boolean isTestSuccessful() throws IOException {
		String line;
		boolean result = true;
		try(BufferedReader bufferedReader = new BufferedReader(new FileReader(pathToFileTestResults));) {
			while(bufferedReader.ready()){
				line = bufferedReader.readLine();
				result = result && (line.equals(expectedValue));
			}
			return result;
		} catch (IOException e) {
			throw e;
		}
	}
}
