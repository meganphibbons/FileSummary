import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Scanner;
import java.util.TreeMap;

/**
 * 
 * @author Megan Phibbons
 * Project: FileSummary
 * Class: FileParser
 * Created: 06/07/2018
 * Updated: 2018
 *
 */

public class FileParser {
	
	private String filePath;
	private int numKeywords;
	private TreeMap<String, Integer> counts;
	private PriorityQueue<Word> words;
	
	public FileParser(String fPath, int numKeys) {
		filePath = fPath;
		numKeywords = numKeys;
	}
	
	public String getKeywords() throws FileNotFoundException {
		getCounts();
		fillQueue();
		while(words.size() > numKeywords) {
			words.poll().getContents();
		}
		ArrayList<String> tempList = new ArrayList<String>();
		String wordList = "";
		while(words.size() > 0) {
			tempList.add(0, words.poll().getContents());
		}
		for(int i = 0; i < tempList.size(); i++) {
			wordList += (i + 1) + ". " + tempList.get(i) + "\n";
		}
		return wordList;
	}
	
	private void getCounts() throws FileNotFoundException {
		counts = new TreeMap<String, Integer>();
		File inputFile = new File(filePath);
		Scanner scan = new Scanner(inputFile);
		while(scan.hasNext()) {
			String word = scan.next();
			word = word.toLowerCase();
			word = word.replaceAll("[.,!?;]", "");
			if(!counts.containsKey(word)) {
				counts.put(word, 0);
			}
			counts.put(word, counts.get(word) + 1);
		}
		scan.close();
	}
	
	private void fillQueue() throws FileNotFoundException {
		Comparator<Word> wordComparator = Comparator.comparing(Word::getCount);
		wordComparator.thenComparing(Word::getLength);
		wordComparator.thenComparing(Word::getContents);
		words = new PriorityQueue<Word>(wordComparator);
		File commonWordsFile = new File("resources\\commonWords.txt");
		String commonWords = "";
		Scanner scan = new Scanner(commonWordsFile);
		while(scan.hasNext()) {
			commonWords += scan.next() + "\n";
		}
		scan.close();
		for(String key : counts.keySet()) {
			if(!commonWords.contains(key.toLowerCase())) {
				Word newWord = new Word(key, counts.get(key));
				words.add(newWord);
			}
		}
	}
}
