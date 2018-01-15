import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

/*
 * SD2x Homework #3
 * Implement the methods below according to the specification in the assignment description.
 * Please be sure not to change the method signatures!
 */
public class Analyzer {

	/*
	 * Implement this method in Part 1
	 */
	public static List<Sentence> readFile(String filename) {
		if (filename == null || filename.isEmpty()) {
			return new ArrayList<>();
		}
		File file = new File(filename);
		try {
			FileReader fileReader = new FileReader(file);
			BufferedReader bufferedReader = new BufferedReader(fileReader);
			String line;
			List<Sentence> sentences = new ArrayList<>();
			while ((line = bufferedReader.readLine()) != null) {
				if (!line.isEmpty()) {
					String[] tmp = line.split(" ");
					if (tmp.length > 1) {
						String strScore = tmp[0];
						try {
							int score = Integer.parseInt(strScore);
							if (-2 <= score && score <= 2) {
								String text = line.substring(strScore.length() + 1, line.length());
								text = text.trim();
								Sentence s = new Sentence(score, text);
								sentences.add(s);
							}
						} catch (NumberFormatException e) {
							continue;
						}
					}
				}
			}
			bufferedReader.close();
			return sentences;
		} catch (IOException ex) {
			return new ArrayList<>();
		} catch (StringIndexOutOfBoundsException e) {
			e.printStackTrace();
			return null;
		}
	}

	/*
	 * Implement this method in Part 2
	 */
	public static Set<Word> allWords(List<Sentence> sentences) {
		if (sentences == null || sentences.isEmpty()) {
			return new HashSet<>();
		}
		Map<String, Word> wordMap = new HashMap<>();
		for (Sentence s : sentences) {
			if (s != null) {
				String text = s.getText();
				String[] wArray = text.split(" ");
				for (int i = 0; i < wArray.length; i++) {
					String strWord = wArray[i].toLowerCase();
					if (isValidWord(strWord)) {
						Word word = wordMap.get(strWord);
						if (word == null) {
							word = new Word(strWord);
							wordMap.put(strWord, word);
						}
						word.increaseTotal(s.getScore());
					}
				}
			}
		}
		Set<Word> words = new HashSet<>();
		for (Map.Entry<String, Word> entry : wordMap.entrySet()) {
			words.add(entry.getValue());
		}
		return words;
	}

	private static boolean isValidWord(String strWord) {
		if (strWord == null || strWord.isEmpty()) {
			return false;
		}
		char firstChar = strWord.charAt(0);
		return 'a' <= firstChar && firstChar <= 'z';
	}

	/*
	 * Implement this method in Part 3
	 */
	public static Map<String, Double> calculateScores(Set<Word> words) {
		Map<String, Double> scoreMap = new HashMap<>();
		if (words != null) {
			for (Word word : words) {
				if (word != null) {
					double score = word.calculateScore();
					scoreMap.put(word.getText(), score);
				}
			}
		}
		return scoreMap;
	}

	/*
	 * Implement this method in Part 4
	 */
	public static double calculateSentenceScore(Map<String, Double> wordScores, String sentence) {

		if (wordScores != null && sentence != null) {
			double totalScores = 0;
			double wordCount = 0;
			String[] strWords = sentence.split(" ");
			for (String str : strWords) {
				str = str.toLowerCase().trim();
				if (isValidWord(str)) {
					wordCount++;
					if (wordScores.containsKey(str)) {
						totalScores += wordScores.get(str);
					}
				}
			}
			if (wordCount > 0) {
				return totalScores / wordCount;
			}
		}

		return 0; // this line is here only so this code will compile if you don't modify it

	}

	/*
	 * This method is here to help you run your program. Y You may modify it as
	 * needed.
	 */
	public static void main(String[] args) {
		if (args.length == 0) {
			System.out.println("Please specify the name of the input file");
			System.exit(0);
		}
		String filename = args[0];
		System.out.print("Please enter a sentence: ");
		Scanner in = new Scanner(System.in);
		String sentence = in.nextLine();
		in.close();
		List<Sentence> sentences = Analyzer.readFile(filename);
		Set<Word> words = Analyzer.allWords(sentences);
		Map<String, Double> wordScores = Analyzer.calculateScores(words);
		double score = Analyzer.calculateSentenceScore(wordScores, sentence);
		System.out.println("The sentiment score is " + score);
	}
}
