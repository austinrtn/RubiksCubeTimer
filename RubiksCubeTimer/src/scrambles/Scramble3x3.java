package scrambles;

public class Scramble3x3 extends Scramble {

	private static int numOfMoves = 12;
	public static final String[] possibleMoves = { "F", "B", "L", "R", "U", "D" };
	public static final String[] frontFaces = { "F", "B" };
	public static final String[] sideFaces = { "R", "L" };
	public static final String[] topFaces = { "U", "D" };

	public Scramble3x3() {
		super(numOfMoves, possibleMoves, frontFaces, sideFaces, topFaces);
	}

}
