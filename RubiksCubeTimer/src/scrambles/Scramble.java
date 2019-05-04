package scrambles;

import java.util.Random;

public class Scramble {

	private Random random = new Random();

	private int numOfMoves;
	private String[] possibleMoves;
	private String strScramble;

	private String[] frontFaces;
	private String[] sideFaces;
	private String[] topFaces;

	public String getStrScramble() {
		return strScramble;
	}

	public Scramble(int numOfMoves, String[] possibleMoves, String[] frontFaces, String[] sideFaces,
			String[] topFaces) {

		this.numOfMoves = numOfMoves;
		this.possibleMoves = possibleMoves;
		possibleMoves = new String[possibleMoves.length];
		this.frontFaces = frontFaces;
		this.sideFaces = sideFaces;
		this.topFaces = topFaces;

		scramble();
	}

	private void scramble() {
		String scramble = "";
		String temp = "";

		for (int i = 0; i < numOfMoves; i++) {
			boolean repeat;

			do {
				repeat = false;

				String randMove = possibleMoves[random.nextInt(possibleMoves.length)];
				
				if (checkFace(frontFaces, randMove) && checkFace(frontFaces, temp))
					repeat = true;

				else if (checkFace(sideFaces, randMove) && checkFace(sideFaces, temp))
					repeat = true;

				else if (checkFace(topFaces, randMove) && checkFace(topFaces, temp)) {
					repeat = true;

				} else {
					temp = randMove;

					if (random.nextBoolean())
						randMove += "2";
					else if (random.nextBoolean())
						randMove += "'";

					scramble += randMove + " ";
				}
			} while (repeat);
		}

		strScramble = scramble;
	}

	private boolean checkFace(String[] faces, String face) {

		for (int i = 0; i < faces.length; i++) {
			String check = faces[i];
			if (check.equals(face)) {
				return true;
			}
		}

		return false;
	}

}
