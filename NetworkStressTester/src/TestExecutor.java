
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class TestExecutor implements ITestExecutor{
	private ExecutorService executor;
	private CyclicBarrier barrier;
	private String hostname;
	private int numberOfExperiments;
	private String resultsDestination;

	@Override
	public void initTest(String hostname, int numberOfExperiments, String resultsDestination) {
		this.executor = Executors.newFixedThreadPool(numberOfExperiments);
		this.barrier = new CyclicBarrier(numberOfExperiments);
		this.hostname = hostname;
		this.numberOfExperiments = numberOfExperiments;
		this.resultsDestination = resultsDestination;
	}

	@Override
	public String execute() throws InterruptedException {
		System.out.println("Num of experiments: " + numberOfExperiments);
		Collection<Callable<String>> requests = new ArrayList<Callable<String>>();
		for (int i = 0; i < numberOfExperiments; i++) {
			requests.add(new HttpConnectionTester(barrier, hostname));
		}
		List<Future<String>> testResults = executor.invokeAll(requests);
		String destination = saveResult(testResults);
		executor.shutdownNow();
		return destination;
	}

	private String saveResult(List<Future<String>> testResults) {
		String destinationPath = resultsDestination + "test" + numberOfExperiments + ".txt";
		File file = new File(destinationPath);
		try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file))) {
			for (Future<String> testResult : testResults) {
				bufferedWriter.append(testResult.get());
				bufferedWriter.append(System.getProperty("line.separator"));
			}
		} catch (IOException | InterruptedException | ExecutionException e) {
			System.err.println(e.getMessage());
		}

		return destinationPath;
	}
}
