package window;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;

import logger.Logger;
import logger.SolveTime;
import scrambles.Scramble;
import scrambles.Scramble3x3;
import timer.Timer;
import javax.swing.border.LineBorder;
import java.awt.Color;

public class TimerPanel extends JPanel implements Runnable, KeyListener {

	/////////////
	// VARIABLES
	/////////////

	private static final long serialVersionUID = 1L;

	private boolean running = false;

	public enum iconColors {
		RED, GREEN, YELLOW
	};

	private int width = 900, height = 900;
	private JLabel lblTimer;
	private JLabel lblScramble;
	private JLabel lblSpacebar;

	public static Timer timer = new Timer();
	private Scramble scramble;
	private JScrollPane solveHisScrollPane;
	private SolveTimeList solveHistory;

	////////////////////
	// START JPANEL
	///////////////////

	public TimerPanel(int width, int height) {
		this.width = width;
		this.height = height;
		init();
	}

	private void init() {
		setPreferredSize(new Dimension(900, 900));
		setLayout(null);

		lblTimer = new JLabel("00:00.000");
		lblTimer.setFont(new Font("Rockwell Condensed", Font.PLAIN, 88));
		lblTimer.setBounds(323, 69, 254, 118);

		lblSpacebar = new JLabel();
		lblSpacebar.setBounds(-54, 677, 600, 200);
		lblSpacebar.setLocation((width / 2) - (lblSpacebar.getWidth() / 2),
				lblTimer.getHeight() - (lblSpacebar.getHeight() / 2) + 5);
		add(lblSpacebar);

		add(lblTimer);

		lblScramble = new JLabel();
		lblScramble.setSize(730, 500);
		lblScramble.setFont(new Font("Tahoma", Font.PLAIN, 44));
		lblScramble.setHorizontalAlignment(SwingConstants.CENTER);
		add(lblScramble);
		
		JTextArea stats = new StatsTextArea();
		stats.setBorder(new LineBorder(new Color(0, 0, 0)));
		stats.setEditable(false);
		stats.setFont(new Font("Tahoma", Font.PLAIN, 18));
		stats.setBounds(593, 600, 297, 261);
		add(stats);

		solveHisScrollPane = new JScrollPane();
		solveHisScrollPane.setBounds(10, 600, 297, 261);
		add(solveHisScrollPane);

		solveHistory = new SolveTimeList();
		solveHistory.setFont(new Font("Malgun Gothic", Font.PLAIN, 24));
		solveHisScrollPane.setViewportView(solveHistory);
		solveHistory.addKeyListener(this);
		setIcon(iconColors.RED);		
		scrambleText();
	}

	/////////////
	// TIMER
	///////////

	private void startTimer() {
		new Thread(timer).start();
		try {
			Thread.sleep(1);
		} catch (InterruptedException e1) {
		}
		new Thread((Runnable) Mainframe.getCurrentPanel()).start();
	}

	public void run() {
		while (timer.isRunning())
			lblTimer.setText(timer.timerToString());
	}

	private void timerEnd() {
		SolveTime st = new SolveTime(timer, scramble);
		solveHistory.printSolve(st);
		Logger.writeFile(st);		
			
		scrambleText();
	}

	private void scrambleText() {
		scramble = new Scramble3x3();
		lblScramble.setText(scramble.getStrScramble());
		lblScramble.setLocation((width / 2) - (lblScramble.getWidth() / 2), (height / 6));
	}

	//////////////
	// KEY EVENTS
	/////////////

	private void setIcon(iconColors c) {
		String loc = "";

		if (c == iconColors.GREEN)
			loc = "/spacebarGreen.png";
		else if (c == iconColors.YELLOW)
			loc = "/spacebarYellow.png";
		else if (c == iconColors.RED)
			loc = "/spacebarRed.png";
		else
			System.out.println("iconColors error: line 87");

		Image i = new ImageIcon(this.getClass().getResource(loc)).getImage();
		lblSpacebar.setIcon(new ImageIcon(i));
	}

	public void keyPressed(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_SPACE) {
			setIcon(iconColors.YELLOW);
		}
	}

	public void keyReleased(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_SPACE) {

			startTimer();

			if (running) {
				running = false;
				setIcon(iconColors.RED);
				timerEnd();
				
			} else {
				running = true;
				setIcon(iconColors.GREEN);
			}
		}
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub

	}
}
