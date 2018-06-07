import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Scanner;
import java.util.TreeMap;
import java.util.TreeSet;

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
	
	public ArrayList<String> getKeywords() throws FileNotFoundException {
		getCounts();
		fillQueue();
		while(words.size() > numKeywords) {
			words.poll();
		}
		ArrayList<String> wordList = new ArrayList<String>();
		while(words.size() > 0) {
			wordList.add(words.poll().getContents());
		}
		return wordList;
	}
	
	private void getCounts() throws FileNotFoundException {
		File inputFile = new File(filePath);
		Scanner scan = new Scanner(inputFile);
		while(scan.hasNext()) {
			String word = scan.next();
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
		File commonWordsFile = new File("commonWords.txt");
		String commonWords = "";
		Scanner scan = new Scanner(commonWordsFile);
		while(scan.hasNext()) {
			commonWords += scan.next() + "\n";
		}
		scan.close();
		for(String key : counts.keySet()) {
			if(!commonWords.contains(key)) {
				Word newWord = new Word(key, counts.get(key));
				words.add(newWord);
			}
		}
	}
	
}
