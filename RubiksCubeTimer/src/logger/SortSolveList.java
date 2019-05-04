package logger;

import java.util.ArrayList;

public class SortSolveList {
	
	private static ArrayList<SolveTime> stl = Logger.getSolveList();;

	public static double getAvg(int amount) {

		double avg = 0;
		
			for (int i = stl.size(); i > stl.size() - amount; i--) {
				if(i <= amount)
					break;
				
				SolveTime s = stl.get(i - 1);
				avg += s.getRealTime();
			}
			avg = avg / amount;
		

		return avg;
	}

	public static String getBestTime(boolean getWorstTime) {

		long time = 0;
		SolveTime st = null;
		String returnTime = null;
		
		if (getWorstTime == false && stl.size() > 0)
			time = 999999999;
		
		for (int x = 0; x < stl.size(); x++) {
			SolveTime currentSolve = stl.get(x);
			long time2 = currentSolve.getRealTime();
			
			if ((time2 < time) && getWorstTime == false || (time < time2) && getWorstTime) {
				time = time2;
				st = currentSolve;
			}		
		}
		
		if(st != null) {
			returnTime = st.getTimeSolved();
			System.out.println(st.getTimeSolved() + getWorstTime);
		}

		return returnTime;
	}

}
