
import java.util.LinkedList;

/*
 * SD2x Homework #1
 * Implement the methods below according to the specification in the assignment description.
 * Please be sure not to change the signature of any of the methods!
 */

public class LinkedListUtils {

	public static void insertSorted(LinkedList<Integer> list, int value) {
		/* IMPLEMENT THIS METHOD! */
		if (list == null) {
			return;
		}
		int i = 0;
		for (; i < list.size(); i++) {
			if (list.get(i) >= value) {
				break;
			}
		}
		list.add(i, value);
	}

	public static void removeMaximumValues(LinkedList<String> list, int N) {
		if (list != null) {
			int count = 0;
			while (count < N && list.size() > 0) {
				int index = findLargest(list);
				String elem = list.get(index);
				while (list.contains(elem)) {
					list.remove(index);
					index = list.indexOf(elem);
				}
				count++;
			}
		}
	}

	private static int findLargest(LinkedList<String> list) {
		int index = -1;
		for (int i = 0; i < list.size(); i++) {
			if (index > -1) {
				String curr = list.get(i);
				String max = list.get(index);
				if (curr.compareTo(max) > 0) {
					index = i;
				}
			} else {
				index = i;
			}
		}
		return index;
	}

	public static boolean containsSubsequence(LinkedList<Integer> one, LinkedList<Integer> two) {
		if (one == null || one.isEmpty() || two == null || two.isEmpty()) {
			return false;
		}

		int index = 0;
		while (index < one.size()) {
			int index2 = 0;
			while (index2 < two.size() && index + index2 < one.size()) {
				if (two.get(index2) == one.get(index + index2)) {
					index2++;
				} else {
					break;
				}
			}

			if (index2 == two.size()) {
				return true;
			}
			index++;
		}
		return false;
	}
}
