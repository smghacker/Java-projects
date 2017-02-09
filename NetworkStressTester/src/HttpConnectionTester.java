import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.Callable;
import java.util.concurrent.CyclicBarrier;

/**
 * This class is responsible for sending a single HTTP request via socket and
 * getting the response
 */
public class HttpConnectionTester implements Callable<String> {
	private CyclicBarrier barrier;
	private String hostname;

	/**
	 * Constructs a single HTTPConnectionTest object
	 * 
	 * @param barrier
	 *            the barrier to which the thread will signalize that it is
	 *            ready
	 * @param hostname
	 *            the hostname of the server to which we will try to send an
	 *            HTTP request
	 */
	public HttpConnectionTester(CyclicBarrier barrier, String hostname) {
		this.barrier = barrier;
		this.hostname = hostname;
	}

	@Override
	public String call() {
		String answer = "";
		try {
			barrier.await();		
			Socket s = new Socket(InetAddress.getByName(hostname), 80);
			PrintWriter pw = new PrintWriter(s.getOutputStream());
			pw.println("GET http://" + hostname + "/ HTTP/1.1");
			pw.println("Host:" + hostname);
			pw.println("");	
			pw.flush();
			pw.close();
			BufferedReader br = new BufferedReader(new InputStreamReader(s.getInputStream()));
			answer = br.readLine();
			br.close();
			s.close();
		} catch (InterruptedException | BrokenBarrierException | IOException e) {
			answer = e.getMessage();
		}
		
		return answer;
	}

}
