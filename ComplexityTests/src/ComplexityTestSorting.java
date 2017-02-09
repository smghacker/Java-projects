import java.util.Arrays;


public class ComplexityTestSorting {

	public static void main(String[] args) {
		int n = 8000000;
		int[] randInts;
		randInts = new int[n];

		
		for(int i = 0; i < n; i++){
			int k = (int)(Math.random()*n);
			randInts[i] = k;
		}
		long begin = System.currentTimeMillis();
		Arrays.sort(randInts);
		long end = System.currentTimeMillis();
		
		System.out.println(end - begin);
	}

}
