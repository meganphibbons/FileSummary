package meganphibbons.filesummary.support;

import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.TreeMap;

public class TextParser extends Parser {
	
	public TextParser(String c, int num) {
		contents = c;
		numKeywords = num;
	}
	
	protected void getCounts() throws FileNotFoundException {
		counts = new TreeMap<String, Integer>();
		Scanner scan = new Scanner(contents);
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

}
