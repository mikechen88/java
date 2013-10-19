import javax.swing.*;
import java.util.*;

/**
 * Several examples of sorting algorithms insert sort, bubble sort, merge sort,
 * quick sort and radix sort
 */

public class SortingAlgorithms {

	// The array to sort
	private int[] a;
	// A copy of the original array
	private int[] initialArray;
	// Default dimension of a
	private static final int SIZE = 10;
	// Maximum number of digits of the elements of a(for radix sort)
	private static final int MAXIMUM_NUMBER_OF_DIGITS = 3;

	/**
	 * Generate an array of random integers of size SIZE
	 */
	public SortingAlgorithms() {
		// Use the other constructor
		this(SIZE);
	}

	/**
	 * Generate an array of random integers of size n
	 */
	public SortingAlgorithms(int n) {
		// Take the default size if n <=0
		if (n <= 0) {
			n = SIZE;
		}

		// Create a
		a = new int[n];
		// Fill a with random integers (between 0 and
		// 10^MAXIMUM_NUMBER_OF_DIGITS-1)
		Random rand = new Random();
		for (int i = 0; i < a.length; i++) {
			a[i] = rand.nextInt((int) Math.pow(10, MAXIMUM_NUMBER_OF_DIGITS));
		}

		// Initialize initialArray and make a copy of a in initialArray
		// (use System.arraycopy)
		initialArray = new int[n];
		System.arraycopy(a, 0, initialArray, 0, a.length);

	}

	/**
	 * write the array as a String (toString is automatically called when using
	 * print with a SortingAlgorithms)
	 */
	public String toString() {
		if (a.length <= 20) {
			return Arrays.toString(a);
		} else {
			return "";
		}
	}

	/**
	 * Sort the array with insert sort
	 */
	public void insertSort() {

		for (int i = 1; i < a.length; i++) {
			insert(i);
		}
	}

	/**
	 * Precondition: a is ordered from 0 to i-1 Postcondition: a is ordered from
	 * 0 to i big O is n square
	 */
	private void insert(int i) {
		int k;
		int temp = a[i];
		for (k = i - 1; k >= 0 && a[k] > temp; k--) {
			a[k + 1] = a[k];
		}
		a[k + 1] = temp;
	}

	/**
	 * Sort the array with bubble sort big O is n square
	 */
	public void bubbleSort() {
		for (int p = 1; p < a.length; p++) {
			for (int i = 0; i < a.length - p; i++) {
				if (a[i] > a[i + 1]) {
					swap(i, i + 1);
				}
			}
		}
	}

	/**
	 * Sort the array with merge sort (a faster algorithm)
	 */
	public void mergeSort() {
		divideInTwoSortAndMerge(0, a.length - 1); // call the function
	}

	/**
	 * Divide the array in two Order each part Merge the two ordered parts
	 */
	private void divideInTwoSortAndMerge(int first, int last) {
		if (first < last) {
			// Midpoint
			int mid = (first + last) / 2;
			// Sort a[first,...,mid] (using divideInTwoSortAndMerge)
			divideInTwoSortAndMerge(first, mid);
			// Sort a[mid+1,...,last] (using divideInTwoSortAndMerge)
			divideInTwoSortAndMerge(mid + 1, last);
			// Merge sorted halves
			merge(first, mid, last);
		}
		// else
		// Base case: nothing to do
	}

	/**
	 * Precondition: first<=mid<=last a[first,...,mid] is sorted in increasing
	 * order a[mid+1,...,last] is sorted in increasing order Postcondition
	 * a[first,...,last] is sorted in increasing order
	 */
	private void merge(int first, int mid, int last) {

		// Method: copy the two halves in a temp array in increasing order
		// copy temp back in a
		int[] temp = new int[last - first + 1];

		// Allocate a temp array on the heap
		// temp must be big enough to receive the two halves of A

		// Index for temp
		int index = 0;

		// Start and end of the first half
		int first1 = first;
		int last1 = mid;
		// Start and end of the second half
		int first2 = mid + 1;
		int last2 = last;

		// Copy the two halves in temp (in increasing order)
		//finish  one of  part array   first
		while (first1 <= last1 && first2 <= last2) {
			if (a[first1] <= a[first2]) {
				temp[index++] = a[first1++];
			} else {
				temp[index++] = a[first2++];
			}

		}
		// Anything left in the first half
		while (first1 <= last1) {
			temp[index++] = a[first1++];
		}
		// Anything left in the second half
		while (first2 <= last2) {
			temp[index++] = a[first2++];
		}
		// Copy temp in a
		System.arraycopy(temp, 0, a, first, temp.length);
	}

	/**
	 * Sort using quicksort
	 */
	public void quickSort() {
		pickPivotAndSort(0, a.length - 1);
		
	}

	/**
	 * Pick the pivot and sort with respect to the pivot
	 */
	private void pickPivotAndSort(int first, int last) {
		if (first < last) {
			// pick the pivot

			// Place the pivot at the start of the array

			// Go through the array: place to the left of the pivot, any
			// element less than the pivot
			int toTheLeft = first; // index of the last element
									// to the left of the pivot

			// Place the pivot between the elements less than the pivot
			// and the elements greater than or equal to the pivot

			// Repeat the process with what is to the left of the pivot
			// and what is to the right of the pivot

		}
		// else
		// Base case: nothing to do
	}

	/**
	 * Find the location of the pivot
	 */
	private int pickPivotLocation(int first, int last) {
		return first; // keep it simple
	}

	/**
	 * Swap the values of a[i] and a[j]
	 */
	private void swap(int i, int j) {
		int temp = a[i];
		a[i] = a[j];
		a[j] = temp;
	}

	/**
	 * Sort the array using radix sort precondition: the maximum number of
	 * digits of the integers in the array is MAXIMUM_NUMBER_OF_DIGITS
	 */
	public void radixSort() {
		// Use an array of 10 ArrayLists to groups the elements of the array

		// Order the array by grouping the elements of a according to their
		// digits at position d
		for (int d = 0; d < MAXIMUM_NUMBER_OF_DIGITS; d++) {
			// Initialize the digit groups

			// Group the elements of a according to the value of their digit
			// at position d

			// Copy the result in a

		}
	}

	/**
	 * Test the Sorting algorithm class
	 */
	public static void main(String[] args) {
		while (true) {
			int n = Integer.parseInt(JOptionPane.showInputDialog(null,
					"Enter the size of the array (<=0 to stop)"));
			if (n <= 0)
				break;

			// Create a SortingAlgorithms with an array of size n
			SortingAlgorithms sa = new SortingAlgorithms(n);

			// Print the array before sorting
			if (sa.a.length < 20) {
				System.out.println("Before sorting");
				System.out.println(sa);
			}

			// Test insert sort
			// Clock time just before the call
			long t1 = System.currentTimeMillis();
			sa.insertSort();
			// Clock time after the call
			long t2 = System.currentTimeMillis();
			System.out.println("With insert sort:");
			System.out.println("Execution time =" + (t2 - t1) + " ms");
			if (sa.a.length < 20)
				System.out.println(sa);

			// Test bubble sort (on the same array)
			System.arraycopy(sa.initialArray, 0, sa.a, 0,
					sa.initialArray.length);
			// Clock time just before the call
			t1 = System.currentTimeMillis();
			sa.bubbleSort();
			// Clock time after the call
			t2 = System.currentTimeMillis();
			System.out.println("With bubble sort:");
			System.out.println("Execution time =" + (t2 - t1) + " ms");
			if (sa.a.length < 20)
				System.out.println(sa);

			// Test merge sort (on the same array)
			System.arraycopy(sa.initialArray, 0, sa.a, 0,
					sa.initialArray.length);
			// Clock time just before the call
			t1 = System.currentTimeMillis();
			sa.mergeSort();
			// Clock time after the call
			t2 = System.currentTimeMillis();
			System.out.println("With merge sort:");
			System.out.println("Execution time =" + (t2 - t1) + " ms");
			if (sa.a.length < 20)
				System.out.println(sa);

			// Test quick sort (on the same array)
			System.arraycopy(sa.initialArray, 0, sa.a, 0,
					sa.initialArray.length);
			// Clock time just before the call
			t1 = System.currentTimeMillis();
			sa.quickSort();
			// Clock time after the call
			t2 = System.currentTimeMillis();
			System.out.println("With quick sort:");
			System.out.println("Execution time =" + (t2 - t1) + " ms");
			if (sa.a.length < 20)
				System.out.println(sa);

			// Test radix sort (on the same array)
			System.arraycopy(sa.initialArray, 0, sa.a, 0,
					sa.initialArray.length);
			// Clock time just before the call
			t1 = System.currentTimeMillis();
			sa.radixSort();
			// Clock time after the call
			t2 = System.currentTimeMillis();
			System.out.println("With radix sort:");
			System.out.println("Execution time =" + (t2 - t1) + " ms");
			if (sa.a.length < 20)
				System.out.println(sa);

			// Compare with the Java API
			ArrayList l = new ArrayList();
			for (int i = 0; i < sa.initialArray.length; i++)
				l.add(new Integer(sa.initialArray[i]));
			// Clock time just before the call
			t1 = System.currentTimeMillis();
			// Sort with Collections.sort
			Collections.sort(l);
			// Clock time after the call
			t2 = System.currentTimeMillis();
			System.out.println("With a sort from the Collections class:");
			System.out.println("Execution time =" + (t2 - t1) + " ms");
			if (l.size() < 20)
				System.out.println(l);

		}
		// Terminate the application properly
		System.exit(0);
	}
}
