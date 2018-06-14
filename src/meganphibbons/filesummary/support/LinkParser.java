package meganphibbons.filesummary.support;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;
import java.util.TreeMap;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

/**
 * 
 * @author Megan Phibbons
 * Project: FileSummary
 * Class: LinkParser
 * Created: 06/07/2018
 * Updated: 2018
 *
 */

public class LinkParser extends Parser {
	
	public LinkParser(String loc, int num) {
		location = loc;
		numKeywords = num;
	}
	
	protected void getCounts() throws IOException {
        URL url = new URL(location);
        BufferedReader in = new BufferedReader(new InputStreamReader(url.openStream()));
        String html = "";
        while ((in.readLine()) != null) {
            html += in.readLine().toString() + "\n";
        }
        in.close();
		Document doc = Jsoup.parse(html); 
		String text = doc.body().text(); 
		counts = new TreeMap<String, Integer>();
		Scanner scan = new Scanner(text);
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
