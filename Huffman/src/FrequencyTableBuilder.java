import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.channels.FileChannel;
import java.util.ArrayList;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.LongAdder;

public class FrequencyTableBuilder {
	private String fileName;
	private int numThreads;
	private boolean quiet;
	private Map<Byte, LongAdder> frequencyMap;

	public FrequencyTableBuilder(String fileName, int numThreads, boolean quiet) {
		super();
		this.fileName = fileName;
		this.numThreads = numThreads;
		this.quiet = quiet;
		this.frequencyMap = new ConcurrentHashMap<>();
		for (int i = 0; i <= 255; i++) {
			frequencyMap.put(Byte.valueOf(String.valueOf(i - 128), 10), new LongAdder());
		}
	}

	public Map<Byte, LongAdder> count() throws IOException {
		File file = new File(fileName);
		RandomAccessFile raf = new RandomAccessFile(file, "rw");
		FileChannel fc = raf.getChannel();
		long fileSize = file.length();
		long integerPart = fileSize / numThreads;
		long partial = fileSize % numThreads;
		FrequencyCounterThread fct = new FrequencyCounterThread(frequencyMap, fc, 0, integerPart + partial, quiet, 0);
		fct.start();
		ArrayList<FrequencyCounterThread> arr = new ArrayList<>();
		arr.add(fct);
		for (int i = 1; i < numThreads; i++) {
			fct = new FrequencyCounterThread(frequencyMap, fc, i * integerPart, integerPart, quiet, i);
			fct.start();
			arr.add(fct);
		}

		for (int i = 0; i < numThreads; i++) {
			try {
				arr.get(i).join();
			} catch (InterruptedException e) {
			}
		}
		fc.close();
		raf.close();

		return frequencyMap;
	}
}
