package logger;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class Logger {

	public static final String FILE_NAME = "log.txt";
	private static Scanner fileReader;

	private static ArrayList<SolveTime> solveList = new ArrayList<>();

	public static ArrayList<SolveTime> getSolveList() {
		return solveList;
	}

	public static int getCurrentId() {
		int id = 0;

		if (solveList.size() > 0)
			id = solveList.get(solveList.size() - 1).getId();

		return id;
	}

	public static void buildList() {
		System.out.println("Building Database...");
		openFile();
		scanFile();
		System.out.println("Database built.");

	}

	private static void openFile() {
		try {
			fileReader = new Scanner(new File(FILE_NAME));
		}

		catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	private static void scanFile() {
		int id = 0;
		String date = null;
		String timeSolved = null;
		long realTime = 0;
		String scramble = null;

		while (fileReader.hasNext()) {

			String str = fileReader.next();

			if (str.contains("ID:")) {
				str = str.replace("ID:", "");
				id = Integer.parseInt(str);
			}

			else if (str.contains("Date:")) {
				str = str.replace("Date:", "");
				date = str;
			}

			else if (str.contains("Time-solved:")) {
				str = str.replace("Time-solved:", "");
				timeSolved = str;
			}

			else if (str.contains("Real-Time:")) {
				str = str.replace("Real-Time:", "");
				realTime = Long.parseLong(str);
			}

			else if (str.contains("Scramble:")) {
				str = str.replace("Scramble:", "");
				scramble = str;
			}

			else if (str.contains("End")) {
				scramble = scramble.replace("_", " ");
				date = date.replace("_", " ");
				SolveTime st = new SolveTime(id, date, timeSolved, realTime, scramble);
				addToList(st);
			}
		}
	}

	private static void addToList(SolveTime st) {
		solveList.add(st);
	}

	public static void removeFromList(SolveTime st) {
		solveList.remove(st);
		openFile();
			
		try (FileWriter fw = new FileWriter(FILE_NAME, false);
				BufferedWriter bw = new BufferedWriter(fw);
				PrintWriter out = new PrintWriter(bw)) {

			SolveTime currentST = null;	

			for(int i = 0; i < solveList.size(); i++) {
				currentST = solveList.get(i);
				currentST.setId(i + 1);
				
				out.print(currentST.log() + "\n\n");
			}
				
		} catch (IOException e) {
			System.out.println(e);
		}

	}
	
	public static void writeFile(SolveTime st) {
		try (FileWriter fw = new FileWriter(FILE_NAME, true);
				BufferedWriter bw = new BufferedWriter(fw);
				PrintWriter out = new PrintWriter(bw)) {

			st.setId(getCurrentId() + 1);
			out.print(st.log() + "\n\n");
			System.out.println("Solve added to database.");

		} catch (IOException e) {
			System.out.println(e);
		}

		addToList(st);
	}

}