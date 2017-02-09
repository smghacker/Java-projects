import java.io.IOException;
import java.util.Map;
import java.util.concurrent.atomic.LongAdder;

public class Main {

	public static void main(String[] args) {
		String fileName = null;
		int numThreads = 0;
		boolean quiet = false;
		for(int i = 0; i < args.length; i++){
			if(args[i].equals("-f")){
				fileName = args[i+1];
			} else if(args[i].equals("-t")){
				numThreads = Integer.valueOf(args[i+1]);
			} else if(args[i].equals("-q")){
				quiet = true;
			}
		}

		long begin = System.currentTimeMillis();
		FrequencyTableBuilder ftb = new FrequencyTableBuilder(fileName, numThreads, quiet);
		try {
			Map<Byte, LongAdder> result = ftb.count();
			for(Byte b : result.keySet()){
				System.out.println(b + " : " + result.get(b));
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		long end = System.currentTimeMillis();
		System.out.println("Total execution time for current run (millis): " + (end - begin));
	}

}
