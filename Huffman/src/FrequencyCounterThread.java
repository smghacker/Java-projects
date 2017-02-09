import java.io.IOException;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.util.Map;
import java.util.concurrent.atomic.LongAdder;

public class FrequencyCounterThread extends Thread{
	private Map<Byte, LongAdder> frequencyMap;
	private FileChannel fileChannel;
	private long offset;
	private long length;
	private boolean quiet;
	private int number;

	public FrequencyCounterThread(Map<Byte, LongAdder> frequencyMap, FileChannel fileChannel, long offset, long length, boolean quiet, int number) {
		super();
		this.frequencyMap = frequencyMap;
		this.fileChannel = fileChannel;
		this.offset = offset;
		this.length = length;
		this.quiet = quiet;
		this.number = number;
	}

	@Override
	public void run() {
		long begin = System.currentTimeMillis();
		log("Thread-" + number + " started.", quiet);
		MappedByteBuffer buffer = null;
		try {
			buffer = fileChannel.map(FileChannel.MapMode.READ_ONLY, offset, length);
		} catch (IOException e) {
			e.printStackTrace();
		}
		for (long i = offset; i < offset + length; i++) {
			Byte b = buffer.get();
			frequencyMap.get(b).increment();
		}
		long end = System.currentTimeMillis();
		log("Thread-" + number + " stopped.", quiet);
		log("Thread-" + number + " execution time was(millis): " + (end - begin), quiet);
	}
	
	private void log(String message, boolean quiet){
		if(!quiet){
			System.out.println(message + "\n");
		}
	}
}
