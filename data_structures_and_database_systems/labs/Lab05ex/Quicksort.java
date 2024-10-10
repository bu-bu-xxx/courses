import java.io.*;
import java.util.*;

public class Quicksort {
	private static long runtime_steps;
	
	// To-do by student
	private static void swap(int[] A, int i, int j) {
		//The i'th and j'th element of array A needs to swap position.
		// 1. __________________________
	}
	
	
	// To-do by student
	private static int partition(int[] A,int low, int high) {
		
		// PLEASE DO NOT CHANGE THIS LINE
		runtime_steps = runtime_steps + ( high - low + 1 );
		
		int pivot = A[high];
		int i = low - 1;
		
		for ( int j=low; j<=high-1; j++ ) {
			if ( A[j] <= pivot ) {
			
				//If the j'th element of A is smaller than pivot, they need to be swapped
				// 2. ______________________
			}
		}
		
		//make sure the pivot element (A[high]) is in the correct position.
		// 3. _______________________

		
		return (i+1);
	}
	
	
	// To-do by student
	private static void quicksort(int[] A,int low, int high) {
		
		if (low<high) {
			
			int p=partition(A,low,high);
	
			quicksort(A, low, p-1);
			
			quicksort(A, p+1, high);
			
		}
		
	}

	
	// Java pass the "reference" (of the array) by value
	// thus the array content can be modified within the function
	public static void startSorting(int[] A) {
		// assume that  A!=null
		quicksort(A, 0, A.length - 1);
	}

	
	public static void printArray(int[] A) {
		for(int i=0; i<A.length; i++)
			System.out.print(A[i] + " ");
		System.out.print("\n");
	}
	
	// generate an array of length n containing integers 0 ~ n-1 in the descending order.
	public static int[] generateWorstCaseInput(int n) {
		int[] A=new int[n];
		
		for (int i=0; i<n; i++)
			A[i]=n-i;
	
		return A;
	}
	
	// generate an array of length n containing integers 0 ~ n-1 randomly shuflled. 
	public static int[] generateAverageCaseInput(int n) {
		Random rand=new Random(0);
		int[] A=new int[n];
		
		for (int i=0; i<n; i++)
			A[i]=n-i;
		
		for (int i=0; i<n; i++) {
			int j=rand.nextInt(n); 
			int temp=A[j];
			A[j]=A[i];
			A[i]=temp;
		}
		
		return A;
	}

	// run quicksort algorithm on a array of length inputSize, the array is generated to make quicksort have worst performance.
	// If doPrintNumbers is true, print input, output and running time. Otherwise only print running time.
	public static void runWorstCaseInput(int inputSize, boolean doPrintNumbers) {
		System.out.printf("\n\n*** Worst case input, input size = %d ", inputSize);
		int[] A = generateWorstCaseInput( inputSize );
		
		runtime_steps=0;
		
		if (doPrintNumbers) {
			System.out.print("\n*** Input: ");
			printArray(A);
		}

		startSorting(A);
		
		if (doPrintNumbers) {
			System.out.print("\n*** Output: ");
			printArray(A);
		}

		System.out.println("\n*** Running time (steps): "+runtime_steps);
	}

	// run quicksort algorithm on a array of length inputSize, the array is generated to make quicksort	have average performance.
	// If doPrintNumbers is true, print input, output and running time. Otherwise only print running time.
	public static void runAverageCaseInput(int inputSize, boolean doPrintNumbers) {
		System.out.printf("\n\n*** Average case input, input size = %d ", inputSize);
		int[] A = generateAverageCaseInput( inputSize );
		
		runtime_steps=0;

		if (doPrintNumbers) {
			System.out.print("\n*** Input: ");
			printArray(A);
		}

		startSorting(A);
		
		if (doPrintNumbers) {
			System.out.print("\n*** Output: ");
			printArray(A);
		}
		
		System.out.println("\n*** Running time (steps): "+runtime_steps);
	}
	
	
	public static void main(String[] args) {
		runAverageCaseInput(10, true);
		//runWorstCaseInput(10, false);

		//runAverageCaseInput(100, false);
		//runWorstCaseInput(100, false);

		//runAverageCaseInput(1000, false);
		//runWorstCaseInput(1000, false);
	}

}
