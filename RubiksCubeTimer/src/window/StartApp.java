package window;

import logger.Logger;

public class StartApp {

	public static void main(String[] args) {
		loadDataBase();
		startGui();
	}
	
	private static void loadDataBase() {
		Logger.buildList();
		System.out.println("Loaded " + Logger.getSolveList().size() + " solve entries.");
	}
	
	private static void startGui() {
		Mainframe.startGui();
		System.out.println("Loaded frame.");
	}
}
