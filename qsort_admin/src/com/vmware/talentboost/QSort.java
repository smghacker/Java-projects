package com.vmware.talentboost;

public class QSort {
	public static void sort(int[] input) {
		sortInternal(input, 0, input.length - 1);
	}

	// Helper method for the sorting
	static void sortInternal(int[] input, int from, int to) {
		int pivot = partition(input, from, to);

		int left_interval_end = pivot - 1;
		while (left_interval_end >= from && input[left_interval_end] == input[pivot]) {
			--left_interval_end;
		}

		if (left_interval_end > from) {
			sortInternal(input, from, left_interval_end);
		}

		int right_interval_begin = pivot + 1;
		while (right_interval_begin <= to && input[right_interval_begin] == input[pivot]) {
			++right_interval_begin;
		}

		if (right_interval_begin < to) {
			sortInternal(input, right_interval_begin, to);
		}
	}


	// Partition the needed array.
	static int partition(int[] input, int from, int to) {
		// XXX: temporary fix(21.III.2001): not sure how partition should work.
		//    just dropping some code for now that worked on the examples I tried.
		int pivot = input[from];
		int size = input.length;

		int numSmaller = 0;
		int numGreater = 0;
		for(int i = 0; i < size; ++i){
			if(input[i] < pivot){
				int temp = input[i];
				input[i] = input[numSmaller];
				input[numSmaller] = temp;
				++numSmaller;
			}
		}
		
		for(int i = to; i >= numSmaller; --i){
			if(input[i] > pivot){
				int temp = input[i];
				input[i] = input[size - numGreater - 1];
				input[size - numGreater - 1] = temp;
				++numGreater;
			}
		}
		return numSmaller;
	}
	
	//Helper method for finding the nth element
	private static void sortNthElement(int n, int[] input, int from, int to) {
		if((to - from) == 0){
			return;
		}
		
		int mid = QSort.partition(input, from, input.length - 1);
		if(n < from + mid){
			sortNthElement(n, input, from, mid);
		}else if(n > from + mid){
			sortNthElement(n, input, mid + 1, to);
		}		
	}
	
	//Function that finds which element will stand on position n - 1 after sorting
	static int nthElementInSeq(int n, int[] input){
		sortNthElement(n - 1, input, 0, input.length - 1);
		return input[n - 1]; 
	}
}
