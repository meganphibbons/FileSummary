import java.util.Comparator;

/**
 * 
 * @author Megan Phibbons
 * Project: FileSummary
 * Class: Word
 * Created: 06/07/2018
 * Updated: 2018
 *
 */

public class Word {
	private String contents;
	private int count;
	
	public Word(String w, int c) {
		contents = w;
		count = c;
	}
	
	public int getCount() {
		return count;
	}
	
	public String getContents() {
		return contents;
	}
	
	public int getLength() {
		return contents.length();
	}
	
	public void incrementCount() {
		count++;
	}

}
