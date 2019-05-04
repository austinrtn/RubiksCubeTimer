package logger;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import scrambles.Scramble;
import timer.Timer;

public class SolveTime {

	private int id;
	private String date;
	private String timeSolved;
	private long realTime = 0;
	private String scramble;

	public SolveTime(Timer timer, Scramble scramble) {
		this.id = Logger.getCurrentId() + 1;
		this.timeSolved = timer.getLastTime();
		this.realTime = timer.getMilisElapsed();
		this.scramble = scramble.getStrScramble();
		
		DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
		Date date = new Date();
		this.date = dateFormat.format(date);
	}
	
	public SolveTime(int id, String date, String timeSolved, long realTime, String scramble) {
		this.id = id;
		this.date = date;
		this.timeSolved = timeSolved;
		this.realTime = realTime;
		this.scramble = scramble;
	}

	public String log() {
		String logScramble = scramble.replace(" ", "_");
		String logDate = date.replace(" ", "_");
		String log = String.format("ID:%d%nDate:%s%nTime-solved:%s%nReal-Time:%d%nScramble:%s%nEnd", id, logDate, timeSolved,
				realTime, logScramble);
		return log;
	}

	// GETTERS AND SETTERS
	
	public void setId(int id) {
		this.id = id;
	}

	public int getId() {
		return id;
	}

	public long getRealTime() {
		return realTime;
	}

	public String getTimeSolved() {
		return timeSolved;
	}

	public String getScramble() {
		return scramble;
	}

	public String getDate() {
		return date;
	}
}