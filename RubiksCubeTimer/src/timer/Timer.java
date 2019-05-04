 package timer;

public class Timer implements Runnable {

	private long milisPassed = 0;
	private long secondsPassed = 0;
	private long minutesPassed = 0;

	private String lastTime;
	private long milisElapsed;

	private boolean running = false;

	public boolean isRunning() {
		return running;
	}

	public void run() {
		if (running == false) {
			running = true;
			resetTimer();
			startTimer();
		} else {
			running = false;
			System.out.println("Final Time: " + lastTime);
		}
	}

	public void startTimer() {
		long beginSolve = System.currentTimeMillis();
		long startTime = System.currentTimeMillis();
		long currentTime = 0;
		long elapsed = 0;

		while (running) {
			currentTime = System.currentTimeMillis();
			elapsed = currentTime - startTime;
			milisPassed = elapsed;

			if (elapsed >= 1000) {
				secondsPassed++;
				startTime = System.currentTimeMillis();
				milisPassed = 0;
				elapsed = 0;
				if (secondsPassed == 60) {
					secondsPassed = 0;
					minutesPassed++;
				}
			}
			
			lastTime = timerToString();
			milisElapsed = System.currentTimeMillis() - beginSolve;
		}
	}

	private void resetTimer() {
		milisPassed = 0;
		secondsPassed = 0;
		minutesPassed = 0;
		lastTime = "";
	}

	public String timerToString() {

		String miliDigit = "0";
		String secDigit = "0";
		String minDigit = "0";
		if (minutesPassed > 9)
			minDigit = "";
		if (secondsPassed > 9)
			secDigit = "";
		if (milisPassed > 99) {
			miliDigit = "";
		}

		String time = (minDigit + minutesPassed + ":" + secDigit + secondsPassed + "." + miliDigit + milisPassed);

		return time;
	}
	
	//GETTERS AND SETTERS 
	
	public String getLastTime() {
		return lastTime;
	}

	public void setLastTime(String lastTime) {
		this.lastTime = lastTime;
	}

	public long getMilisElapsed() {
		return milisElapsed;
	}

	public void setMilisElapsed(long milisElapsed) {
		this.milisElapsed = milisElapsed;
	}
}
