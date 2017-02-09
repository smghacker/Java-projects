package pimontecarlo;

public class Main {

	public static void main(String[] args) {
		long numberOfPoints = 0;
		int numberOfThreads = 0;
		boolean quietMode = false;
		boolean randomThreadLocal = false;
		for(int i = 0; i < args.length; i++){
			if(args[i].equals("-s")){
				numberOfPoints = Integer.valueOf(args[i+1]);
			} else if(args[i].equals("-t")){
				numberOfThreads = Integer.valueOf(args[i+1]);
			} else if(args[i].equals("-q")){
				quietMode = true;
			} else if(args[i].equals("-r")) {
				randomThreadLocal = true;
			}
		}

		CalcExecutor executor = new CalcExecutor(numberOfPoints, numberOfThreads, quietMode, randomThreadLocal);
		executor.execute();
		executor.shutDown();
	}

}
