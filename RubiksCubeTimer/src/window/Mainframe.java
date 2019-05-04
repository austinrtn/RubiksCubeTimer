package window;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;


public class Mainframe {

	private static JFrame frame;
	private static JPanel currentPanel = new JPanel();
	
	public static final int WIDTH = 900;
	public static final int HEIGHT = 900;

	
	public static JPanel getCurrentPanel() {
		return currentPanel;
	}
	
	public static void startGui() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					@SuppressWarnings("unused")
					Mainframe window = new Mainframe();
					frame.setVisible(true);
					TimerPanel tp = new TimerPanel(WIDTH, HEIGHT);
					setPanel(tp);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public static void setPanel(JPanel panel) {
		currentPanel.setVisible(false);
		currentPanel = panel;
		frame.getContentPane().add(currentPanel);
		currentPanel.setVisible(true);	
		frame.pack();
	}
	
	public Mainframe() {
		initialize();
		frame.getContentPane().add(currentPanel);
		
	}

	
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, WIDTH, HEIGHT);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null);
		frame.setTitle("Rubiks Cube Timer");
		frame.setResizable(false);
		
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException
				| UnsupportedLookAndFeelException e) {
			e.printStackTrace();
		}
	}
}
