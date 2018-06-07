/**
 * 
 * @author Megan Phibbons
 * Project: FileSummary
 * Class: FileChooserGUI
 * Created: 06/07/2018
 * Updated: 2018
 *
 */

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class FileChooserGUI {
	private JFrame frame;
	private ButtonGroup searchType;
	private JTextField inputText;
	private JTextArea summary;
	
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
		JRadioButton link = new JRadioButton("Search from link");
		JRadioButton file = new JRadioButton("Search from file");
		JRadioButton dir = new JRadioButton("Search from directory");
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
		inputPanel.add(inputText);
		inputPanel.add(fileExplorer);
		
		JPanel numPanel = new JPanel();
		numPanel.setLayout(new BoxLayout(numPanel, BoxLayout.X_AXIS));
		JLabel askForNum = new JLabel("Number of keywords");
		numPanel.add(Box.createHorizontalGlue());
		numPanel.add(askForNum);
		numPanel.add(Box.createHorizontalGlue());
		
		JPanel numberPanel = new JPanel();
		JTextField numberInput = new JTextField(5);
		JButton decreaseVal = new JButton("-");
		JButton increaseVal = new JButton("+");
		numberPanel.add(decreaseVal);
		numberPanel.add(numberInput);
		numberPanel.add(increaseVal);
		
		JPanel sumPanel = new JPanel();
		sumPanel.setLayout(new BoxLayout(sumPanel, BoxLayout.X_AXIS));
		JButton finalButton = new JButton("Summarize");
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
		
	}

	
	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame("File Summary Tool");
		frame.setBounds(150, 150, 750, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}
	
	
}
