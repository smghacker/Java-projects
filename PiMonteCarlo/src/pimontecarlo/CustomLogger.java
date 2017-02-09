package pimontecarlo;

public class CustomLogger {
	public static void log(String message, boolean toLog){
		if(toLog){
			System.out.println(message);
		}
	}
}
