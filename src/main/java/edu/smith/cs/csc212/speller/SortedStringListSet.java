package edu.smith.cs.csc212.speller;

import java.util.AbstractSet;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

/**
 * This is an alternate implementation of a dictionary, based on a sorted list.
 * It often makes the most sense if the dictionary never changes (compared to a
 * TreeMap). You could write a delete, but it's tricky.
 * 
 * @author jfoley
 */
public class SortedStringListSet extends AbstractSet<String> {
	/**
	 * This is the sorted list of data.
	 */
	private List<String> data;

	/**
	 * This is the constructor: we take in data, copy and sort it (just to be sure).
	 * 
	 * @param data the input list.
	 */
	public SortedStringListSet(List<String> data) {
		this.data = new ArrayList<>(data);
		Collections.sort(this.data);
	}

	/**
	 * So we can use it in a for-loop.
	 */
	@Override
	public Iterator<String> iterator() {
		return data.iterator();
	}

	/**
	 * This method takes an object because it was invented before Java 5.
	 */
	@Override
	public boolean contains(Object key) {
		return binarySearch((String) key, 0, this.data.size()) >= 0;
	}

	/**
	 * TODO: replace this binarySearch with your own.
	 * 
	 * @param query - the string to look for.
	 * @param start - the left-hand side of this search (inclusive)
	 * @param end   - the right-hand side of this search (exclusive)
	 * @return the index found, OR negative if not found.
	 */
	private int binarySearch(String query, int start, int end) {
		// TODO: replace this with your own binary search.
		int middle = (int)Math.floor(start + end) / 2;
		if (start < end) {
			if (data.get(middle).equals(query)) {
				return middle;
			} else {
				if (query.compareTo(data.get(middle)) < 0) {
					int cond1 = binarySearch(query, start, middle);
					if (cond1 != -1) {
						return cond1;
					} else {
						int cond2 = binarySearch(query, middle + 1, end);
						if (cond2 != -1) {
							return cond2;
						}

					}

				}
			}
			
		}
		//return Collections.binarySearch(this.data.subList(start, end), query);
		return -1;

	}
	
	public static void main(String[] args) {
		List <String> runTest = new ArrayList<>();
		runTest.add("One");
		runTest.add("Three");
		runTest.add("Four");
		runTest.add("Deli");
		SortedStringListSet test = new SortedStringListSet(runTest);
		
		System.out.println(test.binarySearch("One", 0, test.data.size()));
		System.out.println(test.binarySearch("See", 0, test.data.size()));
		System.out.println(test.binarySearch("Food", 0, test.data.size()));
		System.out.println(test.binarySearch("Four", 0, test.data.size()));
		System.out.println(test.binarySearch("Deli", 0, test.data.size()));
	}

	/**
	 * So we know how big this set is.
	 */
	@Override
	public int size() {
		return data.size();
	}

}
