
/*
 * SD2x Homework #5
 * Implement the methods below according to the specification in the assignment description.
 * Please be sure not to change the method signatures!
 */

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.TreeMap;

public class MovieRatingsProcessor {

	public static List<String> getAlphabeticalMovies(TreeMap<String, PriorityQueue<Integer>> movieRatings) {
		List<String> movies = new ArrayList<>();
		if (movieRatings != null) {
			for (Map.Entry<String, PriorityQueue<Integer>> entry : movieRatings.entrySet()) {
				movies.add(entry.getKey());
			}
		}

		return movies; // this line is here only so this code will compile if you don't modify it
	}

	public static List<String> getAlphabeticalMoviesAboveRating(TreeMap<String, PriorityQueue<Integer>> movieRatings,
			int rating) {
		List<String> movies = new ArrayList<>();
		if (movieRatings != null) {
			for (Map.Entry<String, PriorityQueue<Integer>> entry : movieRatings.entrySet()) {
				PriorityQueue<Integer> ratings = entry.getValue();
				if (ratings.size() > 0) {
					Integer iMinRating = ratings.peek();
					if (iMinRating > rating) {
						movies.add(entry.getKey());
					}
				}
			}
		}
		return movies;
	}

	public static TreeMap<String, Integer> removeAllRatingsBelow(TreeMap<String, PriorityQueue<Integer>> movieRatings,
			int rating) {
		TreeMap<String, Integer> removedMovies = new TreeMap<>();
		if (movieRatings != null) {
			List<String> removeAll = new ArrayList<>();
			for (Map.Entry<String, PriorityQueue<Integer>> entry : movieRatings.entrySet()) {
				PriorityQueue<Integer> ratings = entry.getValue();
				if (ratings.size() > 0) {
					Integer iMinRating = ratings.peek();
					int removeCount = 0;
					while (iMinRating < rating) {
						ratings.remove();
						removeCount++;
						if (!ratings.isEmpty()) {
							iMinRating = ratings.peek();
						} else {
							removeAll.add(entry.getKey());
							break;
						}
					}
					if (removeCount > 0) {
						removedMovies.put(entry.getKey(), removeCount);
					}
				}
			}

			for (String noRating : removeAll) {
				movieRatings.remove(noRating);
			}
		}
		return removedMovies;
	}
}
