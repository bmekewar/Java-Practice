package com.bvm.datastructure;

public class BinarySearch {

	//public int[] data = {1,3,6,32,12,643,2,0,2,8,3};
	public int[] data = {0,3,7,11,15,17,19,19,25,33,40};

	public int binarySearch(int searchElement) {
		int low = 0; // low end of the search area
		int high = data.length - 1; // high end of the search area
		int middle = (low + high + 1) / 2; // middle element
		int location = -1; // return value; -1 if not found
		do // loop to search for element
		{
			// if the element is found at the middle
			if (searchElement == data[middle])
				location = middle; // location is the current middle
			// middle element is too high
			else if (searchElement < data[middle])
				high = middle - 1; // eliminate the higher half
			else
				// middle element is too low
				low = middle + 1; // eliminate the lower half
			middle = (low + high + 1) / 2; // recalculate the middle
		} while ((low <= high) && (location == -1));
		return location; // return location of search key
	}

	public static void main(String[] args) {
		BinarySearch bs = new BinarySearch();
		System.out.println(bs.binarySearch(19));
	}

}
