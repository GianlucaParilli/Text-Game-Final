import java.io.File;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Scanner;

import javafx.geometry.Insets;
import javafx.scene.control.Alert;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;

public class Puzzles extends Observable {

	private String puzzleID;
	private String puzzleDescription;
	private String puzzleAnswer;
	private String puzzleHint;
	private static ArrayList<Puzzles> puzzlesArray = new ArrayList<>();
	//private ArrayList<> puzzles = new ArrayList<>();

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

	public void puzzlePopUp(ArrayList<String> puzzleArray) {
		Alert popUp = new Alert(AlertType.INFORMATION);
		popUp.setTitle("Inventory");
		popUp.setHeaderText("Select an item");
		ImageView logo = new ImageView("logo.png");
		logo.setFitWidth(64);
	    logo.setFitHeight(64);
		popUp.setGraphic(logo);
		VBox pop = new VBox();
		pop.setStyle("-fx-padding: 10;" + "-fx-border-style: solid inside;" + "-fx-border-width: 1;"
				+ "-fx-border-insets: 10;" + "-fx-border-radius: 10;" + "-fx-border-color: black;");
		pop.setPadding(new Insets(50, 50, 50, 50));
		RadioButton cb; 
		RadioButton egg;
		ToggleGroup toggleGroup = new ToggleGroup();

		for (Puzzles temp : puzzlesArray) {
			cb = new RadioButton(temp.getPuzzleAnswer());
			egg = new RadioButton(temp.getPuzzleAnswer());
			cb.setFont(Font.font("Verdana", 16));

			cb.setToggleGroup(toggleGroup);
			pop.getChildren().add(egg);
			System.out.println(puzzleArray.get(0));
			if (temp.equals(egg.getText())) {
				pop.getChildren().add(cb);
				egg.setOnAction(e -> {
					System.out.println("wrong");
				});
			}
		}
		popUp.getDialogPane().setContent(pop);
		popUp.show();
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