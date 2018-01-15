
/*
 * SD2x Homework #5
 * Implement the method below according to the specification in the assignment description.
 * Please be sure not to change the method signature!
 */

import java.util.List;
import java.util.PriorityQueue;
import java.util.TreeMap;

public class MovieRatingsParser {

	public static TreeMap<String, PriorityQueue<Integer>> parseMovieRatings(List<UserMovieRating> allUsersRatings) {
		TreeMap<String, PriorityQueue<Integer>> ratingMap = new TreeMap<>();
		if (allUsersRatings != null) {
			for (UserMovieRating r : allUsersRatings) {				
				if (r != null && r.getMovie() != null && r.getMovie().length() > 0 && r.getUserRating() >= 0) {
					String title = r.getMovie();
					title = title.toLowerCase();
					if (ratingMap.containsKey(title)) {
						PriorityQueue<Integer> rate = ratingMap.get(title);
						rate.add(r.getUserRating());
					} else {
						PriorityQueue<Integer> rate = new PriorityQueue<>();
						rate.add(r.getUserRating());
						ratingMap.put(title, rate);
					}
				}
			}
		}
		return ratingMap; // this line is here only so this code will compile if you don't modify it
	}

}
