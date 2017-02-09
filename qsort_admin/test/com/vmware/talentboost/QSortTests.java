package com.vmware.talentboost;

import static org.junit.Assert.*;

import java.util.Arrays;

import org.junit.Test;

public class QSortTests {

	@Test
	public void testSimple() {
		int n = 100;
		int[] input = new int[n];
		int[] sorted = new int[n];
		for(int i = 0; i < n; i++){
			input[i] = i;
			sorted[i] =i;
		}
		
		Arrays.sort(sorted);
		QSort.sort(input);

		
		assertArrayEquals(sorted, input);
	}
	
	@Test
	public void testReverse(){
		int n = 10000;
		int[] input = new int[n];
		int[] sorted = new int[n];
		for(int i = 0; i < n; i++){
			input[i] = n - i;
			sorted[i] = n - i;
		}
		
		Arrays.sort(sorted);
		QSort.sort(input);

		
		assertArrayEquals(sorted, input);
	}
	
	@Test
	public void testRandomIntegers(){
		int n = 100000;
		int[] input = new int[n];
		int[] sorted = new int[n];
		for(int i = 0; i < n; i++){
			int num = (int)Math.random()*(n+1);
			input[i] = num;
			sorted[i] = num;
		}
		
		Arrays.sort(sorted);
		QSort.sort(input);

		
		assertArrayEquals(sorted, input);
	}
	
	@Test
	public void testNthElement(){
		int k = 100;
		int n = 10;
		int[] input = new int[k];
		int[] sorted = new int[k];
		for(int i = 0; i < k; i++){
			input[i] = i;
			sorted[i] = i;
		}
		
		Arrays.sort(sorted);

		int nth = QSort.nthElementInSeq(n, input);
		assertEquals(sorted[n - 1], nth);
	}
}
