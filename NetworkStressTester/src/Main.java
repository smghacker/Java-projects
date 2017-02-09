public class Main {
	public static void main(String[] args) throws Exception {
		StressTester stressTester = new StressTester("website.bg", "C:\\Users\\Valeri\\workspace\\NetworkStressTester\\", 300, "HTTP/1.1 200 OK");
		System.out.println("Answer: " + stressTester.stressTest());
	}

}
