package meganphibbons.filesummary.support;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Scanner;
import java.util.TreeMap;

public abstract class Parser {
	protected String contents;
	protected String location;
	protected int numKeywords;
	protected TreeMap<String, Integer> counts;
	protected PriorityQueue<Word> words;
	
	public String getKeywords() throws IOException {
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
	
	protected abstract void getCounts() throws FileNotFoundException, IOException;

}
