
import java.io.IOException;

public interface ITestChecker {
	void initTestChecker(String pathToFileTestResults, String expectedValue);
	boolean isTestSuccessful() throws IOException;
}
