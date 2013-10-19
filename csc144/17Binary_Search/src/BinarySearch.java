import java.util.Scanner;

public class BinarySearch {
	//  O   of a binary search = O ( log  n )    when  n   is the size of the array.
	
	
	public static void main(String[] args) {
		Integer[] a = new Integer[10];
		for (int i = 0; i < a.length; i++) {
			a[i] = 10 * i;//
		}

		Scanner scan = new Scanner(System.in);
		do {
			System.out.print("enter the value to search ");
			String s = scan.nextLine().trim().toLowerCase();
			if (s.startsWith("q")) {
				break;
			} else {
				try {
					int n = Integer.parseInt(s);
					int index = binarySearch(0, a.length - 1, n, a);
					if (index != -1) {
						System.out.println(n + "    is at index= " + index);
					} else {
						System.out.println(n + "   is not in the array");
					}
				} catch (NumberFormatException e) {
					System.out.println("INvalid input");
				}
			}
		} while (true);

	}

	/**
	 * given an value , return the value of the index where the element appears
	 * in the array, or -1 if the value is not in the array
	 * 
	 * @param <E>
	 * 
	 * @param val
	 *            : the value to fine
	 * @param first
	 *            : the index of the left most element in the range to search
	 * @param last
	 *            : the index of the rightmost element in the range to search
	 */
	public static <E extends Comparable<E>> int binarySearch(int first,
			int last, E value, E[] a) {

		// base case?
		if (first > last) {
			return -1;
		} else {
			int mid = (first + last) / 2;
			if (value.compareTo(a[mid]) < 0) {
				// the value may be in [ first , mid-1]
				last = mid - 1;
			} else if (value.compareTo(a[mid]) > 0) {
				// the value may be in [mid+1, last]
				first = mid + 1;
			} else {
				return mid; // found !
			}

			return binarySearch(first, last, value, a);
		}

	}
}
