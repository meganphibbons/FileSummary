package meganphibbons.filesummary.support;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.TreeMap;

public class DirParser extends Parser {
	
	public DirParser(String fp, int num) {
		location = fp;
		numKeywords = num;
	}
	
	protected void getCounts() throws FileNotFoundException {
		counts = new TreeMap<String, Integer>();
		File inputFile = new File(location);
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

}
