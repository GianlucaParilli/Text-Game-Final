import java.io.File;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Scanner;

public class Puzzles extends Observable {

	private String puzzleID;
	private String puzzleDescription;
	private String puzzleAnswer;
	private String puzzleHint;
	private static ArrayList<Puzzles> puzzlesArray = new ArrayList<>();

	public Puzzles() {
		try {
			puzzleReader();

		} catch(FileNotFoundException e){
			System.out.println("No File Found");
		}
	}

	public Puzzles(String puzzleID, String puzzleDescription, String puzzleAnswer, String puzzleHint)
	{
		this.puzzleID = puzzleID;
		this.puzzleDescription = puzzleDescription;
		this.puzzleAnswer = puzzleAnswer;
		this.puzzleHint = puzzleHint;

	}

	public void setPuzzleDescription(String puzzleDescription) {
		this.puzzleDescription = puzzleDescription;
		setChanged();
		notifyObservers(puzzleDescription);
	}

	public void setPuzzleHint(String puzzleHint) {
		this.puzzleHint = puzzleHint;
		setChanged();
		notifyObservers(puzzleHint);
	}
	
	public void setPuzzleAnswer(String puzzleAnswer) {
		this.puzzleAnswer = puzzleAnswer;
		setChanged();
		notifyObservers(puzzleAnswer);
	}

	public String ViewPuzzle(int currentRoom) { 
		System.out.println(currentRoom);
		setPuzzleDescription(getPuzzlesArray().get(currentRoom).getPuzzleDescription());
		return puzzleDescription;

	}

	public String ViewHint(int currentRoom) {
		System.out.println(currentRoom);
		setPuzzleHint(getPuzzlesArray().get(currentRoom).getPuzzleHint());
		return puzzleHint;
	}
	
	public String ViewAnswer(int currentRoom){
		System.out.println(currentRoom);
		setPuzzleAnswer(getPuzzlesArray().get(currentRoom).getPuzzleAnswer());
		return puzzleAnswer;

	}
	

	public void puzzleReader() throws FileNotFoundException {
		@SuppressWarnings("resource")
		Scanner reader = new Scanner(new File("puzzle.txt"));
		;

		while(reader.hasNext()){

			String puzzleID = reader.nextLine();
			String puzzleDescription = reader.nextLine();
			String puzzleAnswer = reader.nextLine();
			String puzzleHint = reader.nextLine();

			Puzzles puzzle = new Puzzles(puzzleID, puzzleDescription, puzzleAnswer, puzzleHint);
			puzzlesArray.add(puzzle);

		}
	}

	@Override
	public String toString() {
		return puzzleID + " | " + puzzleDescription + " | " + puzzleAnswer + " | " + puzzleHint + " | ";
	}

	public String getPuzzleID() {
		return puzzleID;
	}

	public void setPuzzleID(String puzzleID) {
		this.puzzleID = puzzleID;
	}
	public String getPuzzleDescription() {
		return puzzleDescription;
	}

	public String getPuzzleAnswer() {
		return puzzleAnswer;
	}

	public String getPuzzleHint() {
		return puzzleHint;
	}

	public ArrayList<Puzzles> getPuzzlesArray() {
		return puzzlesArray;
	}


}