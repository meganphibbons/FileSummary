package meganphibbons.filesummary;
/**
 * 
 * @author Megan Phibbons
 * Project: FileSummary
 * Class: FileChooserGUI
 * Created: 06/07/2018
 * Updated: 2018
 *
 */

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileNotFoundException;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class FileChooserGUI implements ActionListener {
	private JFrame frame;
	private ButtonGroup searchType;
	private JTextField inputText;
	private JTextField numberInput;
	private JTextArea summary;
	private JRadioButton link;
	private JRadioButton file;
	JRadioButton dir;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FileChooserGUI window = new FileChooserGUI();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public FileChooserGUI() {
		initialize();
		
		JPanel titlePanel = new JPanel();
		titlePanel.setLayout(new BoxLayout(titlePanel, BoxLayout.X_AXIS ));
		JLabel title = new JLabel("File Summary Tool");
		titlePanel.add(Box.createHorizontalGlue());
		titlePanel.add(title);
		titlePanel.add(Box.createHorizontalGlue());
		
		JPanel selectionPanel = new JPanel();
		link = new JRadioButton("Search from link");
		file = new JRadioButton("Search from file");
		dir = new JRadioButton("Search from directory");
		searchType = new ButtonGroup();
		searchType.add(link);
		searchType.add(file);
		searchType.add(dir);
		selectionPanel.add(link);
		selectionPanel.add(file);
		selectionPanel.add(dir);
		
		JPanel sPanel = new JPanel();
		sPanel.setLayout(new BoxLayout(sPanel, BoxLayout.X_AXIS));
		JLabel askForFile = new JLabel("Please input the file path or link");
		sPanel.add(Box.createHorizontalGlue());
		sPanel.add(askForFile);
		sPanel.add(Box.createHorizontalGlue());
		
		JPanel inputPanel = new JPanel();
		inputText = new JTextField(20);
		JButton fileExplorer = new JButton("Open File Explorer");
	    fileExplorer.setMnemonic(KeyEvent.VK_D);
	    fileExplorer.addActionListener(this);
	    fileExplorer.setActionCommand("fileExplorer");
		inputPanel.add(inputText);
		inputPanel.add(fileExplorer);
		
		JPanel numPanel = new JPanel();
		numPanel.setLayout(new BoxLayout(numPanel, BoxLayout.X_AXIS));
		JLabel askForNum = new JLabel("Number of keywords");
		numPanel.add(Box.createHorizontalGlue());
		numPanel.add(askForNum);
		numPanel.add(Box.createHorizontalGlue());
		
		JPanel numberPanel = new JPanel();
		numberInput = new JTextField(5);
		numberInput.setText("0");
		numberInput.setHorizontalAlignment(JTextField.CENTER);
		JButton decreaseVal = new JButton("-");
	    decreaseVal.setMnemonic(KeyEvent.VK_D);
	    decreaseVal.addActionListener(this);
	    decreaseVal.setActionCommand("decreaseVal");
		JButton increaseVal = new JButton("+");
	    increaseVal.setMnemonic(KeyEvent.VK_D);
	    increaseVal.addActionListener(this);
	    increaseVal.setActionCommand("increaseVal");
		numberPanel.add(decreaseVal);
		numberPanel.add(numberInput);
		numberPanel.add(increaseVal);
		
		JPanel sumPanel = new JPanel();
		sumPanel.setLayout(new BoxLayout(sumPanel, BoxLayout.X_AXIS));
		JButton finalButton = new JButton("Summarize");
	    finalButton.setMnemonic(KeyEvent.VK_D);
	    finalButton.addActionListener(this);
	    finalButton.setActionCommand("summarize");
		sumPanel.add(Box.createHorizontalGlue());
		sumPanel.add(finalButton);
		sumPanel.add(Box.createHorizontalGlue());
		
		JPanel leftPanel = new JPanel();
		leftPanel.setLayout(new BoxLayout(leftPanel, BoxLayout.Y_AXIS));
		leftPanel.add(titlePanel);
		leftPanel.add(selectionPanel);
		leftPanel.add(sPanel);
		leftPanel.add(inputPanel);
		leftPanel.add(numPanel);
		leftPanel.add(numberPanel);
		leftPanel.add(sumPanel);
		
		summary = new JTextArea();
		JScrollPane scroller = new JScrollPane(summary);
		summary.setLineWrap(true);
		summary.setWrapStyleWord(true);
		summary.setEditable(false);
		
		JPanel mainPanel = new JPanel();
		mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.X_AXIS));
		mainPanel.add(leftPanel);
		mainPanel.add(scroller);
		frame.getContentPane().add(mainPanel);
		
	}
	
	public void actionPerformed(ActionEvent e) {
	    if(e.getActionCommand().equals("fileExplorer")){
	    	JFileChooser chooser = new JFileChooser(); 
	        chooser.setCurrentDirectory(new java.io.File("C:\\"));
	        chooser.setDialogTitle("Please select a file.");
	        chooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
	        chooser.setAcceptAllFileFilterUsed(false);
	        String fileName = "";
	        if(chooser.showOpenDialog(chooser) == JFileChooser.APPROVE_OPTION){
	        	File chosenFile = chooser.getSelectedFile();
	        	fileName = chosenFile.getPath();
	        }
	        inputText.setText(fileName);
	    }
	    
	    if(e.getActionCommand().equals("increaseVal")) {
	    	String numString = numberInput.getText();
	    	if(numString.matches("[0-9]*")) {
	    		int num = Integer.parseInt(numString);
	    		num++;
	    		numberInput.setText(Integer.toString(num));
	    	}
	    }
	    
	    if(e.getActionCommand().equals("decreaseVal")) {
	    	String numString = numberInput.getText();
	    	if(numString.matches("[0-9]*")) {
	    		int num = Integer.parseInt(numString);
	    		if(num > 0) {
	    			num--;
	    		}
	    		numberInput.setText(Integer.toString(num));
	    	}
	    }
	    
	    if(e.getActionCommand().equals("summarize")) {
	    	String output = "The following keywords have been found:\n";
	    	if(link.isSelected()) {
	    		
	    	} else if(file.isSelected()) {
		    	FileParser summarizer = new FileParser(inputText.getText(), Integer.parseInt(numberInput.getText()));
				try {
					output += summarizer.getKeywords();
				} catch (FileNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
					output = "Error: File not found";
				}
	    	} else if(dir.isSelected()) {
	    		
	    	} else {
	    		output = "Please select a search type.";
	    	}
	    	summary.setText(output);

	    }
	}

	
	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame("File Summary Tool");
		frame.setBounds(150, 150, 825, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}
	
	
}
