package window;

import javax.swing.JTextArea;

import logger.SortSolveList;

public class StatsTextArea extends JTextArea {

	private static final long serialVersionUID = 1L;
	
	public StatsTextArea() {
		setAlignmentX(CENTER_ALIGNMENT);
		setText(getStats());
	}

	private String getStats() {
		double avg5 = SortSolveList.getAvg(5);
		double avg12 = SortSolveList.getAvg(12);
		String bestTime = SortSolveList.getBestTime(false);
		String worstTime = SortSolveList.getBestTime(true);
		
		String stats = String.format("Average of 5: %f%n%nAverage of 12: %f%n%nBest Time: %s%n%nWorstTime: %s%n%n", avg5, avg12, bestTime, worstTime);
		
		return stats;
	}
}
