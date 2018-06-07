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

import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class FileChooserGUI {
	private JFrame frame;
	private ButtonGroup searchType;
	
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
		
		JLabel title = new JLabel("File Summary Tool");
		
		JPanel selectionPanel = new JPanel();
		JButton link = new JButton("Search from link");
		JButton file = new JButton("Search from file");
		JButton dir = new JButton("Search from directory");
		searchType = new ButtonGroup();
		searchType.add(link);
		searchType.add(file);
		searchType.add(dir);
		selectionPanel.add(link);
		selectionPanel.add(file);
		selectionPanel.add(dir);
		
		JPanel mainPanel = new JPanel();
		mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
		mainPanel.add(title);
		mainPanel.add(selectionPanel);
		
	}
	
	public void actionPerformed(ActionEvent e) {
		
	}

	
	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame("File Summary Tool");
		frame.setBounds(150, 150, 500, 500);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}
	
	
}
