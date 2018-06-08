package meganphibbons.filesummary.support;
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

public class FileParser extends Parser {
	
	public FileParser(String fPath, int numKeys) {
		location = fPath;
		numKeywords = numKeys;
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
