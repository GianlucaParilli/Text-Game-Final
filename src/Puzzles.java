import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Scanner;
import javafx.geometry.Insets;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;

public class Puzzles extends Observable {

	/**
	 * @author Gianluca Parilli
	 * @version 1.0
	 * @Course : ITEC 3860, Fall, 2017 Written: November 8, 2017
	 * 
	 */
	private String puzzleID;
	private String puzzleDescription;
	private String puzzleAnswer;
	private String puzzleHint;
	private int currentRoom;
	private boolean solved;
	private static ArrayList<Puzzles> puzzlesArray = new ArrayList<>();
	Label descriptionText = new Label();
/*
 * puzzle constructor that calls the file reader for the puzzle.txt file
 */
	public Puzzles() {
		try {
			puzzleReader();

		} catch (FileNotFoundException e) {
			System.out.println("No File Found");
		}
	}

	public Puzzles(String puzzleID, String puzzleDescription, String puzzleAnswer, String puzzleHint, boolean solved) {
		this.puzzleID = puzzleID;
		this.puzzleDescription = puzzleDescription;
		this.puzzleAnswer = puzzleAnswer;
		this.puzzleHint = puzzleHint;
		this.solved = solved;

	}

	public int currentPuzzle(String puzzleID) {
		int currentID = 0;
		// System.out.println("monsters position " +getMonstersArray().get(0));
		for (Puzzles p : getPuzzlesArray()) {
			if (p.getPuzzleID().equals(puzzleID)) {
				currentID = getPuzzlesArray().indexOf(p);
				// System.out.println("current monster " + m.getPuzzleDescription());
				setPuzzleDescription((p.getPuzzleDescription()));

			}
		}
		System.out.println("current puzzle position " + currentID);

		return currentID;
	}



	// public void puzzlePopUp(Button answer, Button hint, Button exit, Rooms room)
	// {
	public void puzzlePopUp(Rooms room) {
		Alert popUp = new Alert(AlertType.CONFIRMATION);
		popUp.setTitle("Puzzle");
		popUp.setHeaderText("Puzzle");
		popUp.setResizable(true);
		popUp.setWidth(150);
		ImageView logo = new ImageView("logo.png");
		logo.setFitWidth(64);
		logo.setFitHeight(64);
		popUp.setGraphic(logo);
		popUp.getButtonTypes().add(ButtonType.CLOSE);
		popUp.hide();
		popUp.getButtonTypes().remove(ButtonType.CLOSE);

		HBox puzzleDescription = new HBox(15);
		puzzleDescription.setMinHeight(300);
		puzzleDescription.setStyle("-fx-padding: 10;" + "-fx-border-style: solid inside;" + "-fx-border-width: 1;"
				+ "-fx-border-insets: 10;" + "-fx-border-radius: 10;" + "-fx-border-color: black;");
		puzzleDescription.setMinWidth(300);
		puzzleDescription.setMaxHeight(200);
		puzzleDescription.setMaxWidth(200);

		descriptionText = new Label();
		descriptionText.setFont(Font.font("Verdana", 22));
		descriptionText.setWrapText(true);

		puzzleDescription.setPadding(new Insets(15, 15, 15, 15));
		Button button;
		VBox picPane = new VBox(10);
		picPane.setStyle("-fx-padding: 15;" + "-fx-border-insets: 10;" + "-fx-border-radius: 10;");
		VBox picPane1 = new VBox(10);
		picPane1.setStyle("-fx-padding: 15;" + "-fx-border-insets: 10;" + "-fx-border-radius: 10;");

		int counter = 0;
		for (Puzzles temp : getPuzzlesArray()) {
			if (counter < 4) {
				Image img1 = new Image(getClass().getResourceAsStream("puzzles/" + temp.getPuzzleID() + ".png"));
				ImageView image = new ImageView(img1);
				image.setFitWidth(48);
				image.setFitHeight(48);
				button = new Button("", image);
				button.setId(temp.getPuzzleID());

				picPane.getChildren().add(button);
				if (temp.getPuzzleID().equals(button.getId())) {
					button.setOnAction(e -> {
						// System.out.println(getPuzzlesArray().get(currentRoom).getPuzzleID());
						// System.out.println(temp.getPuzzleID());
						if (getPuzzlesArray().get(currentRoom).getPuzzleID().equals(temp.getPuzzleID())) {
							System.out.println("correct");
							addObserver(LostTreasureMain.gui);
							setPuzzleHint("correct");
							temp.setSolved(true);
							popUp.close();
						} else {
							System.out.println("not correct");
							addObserver(LostTreasureMain.gui);
							setPuzzleHint("you clicked on the wrong answer, try again");
						}
						// System.out.println(temp.getPuzzleAnswer());
					});
				}
			}
			if (counter >= 4 && counter < 9) {
				Image img1 = new Image(getClass().getResourceAsStream("puzzles/" + temp.getPuzzleID() + ".png"));
				ImageView image = new ImageView(img1);
				image.setFitWidth(48);
				image.setFitHeight(48);
				button = new Button("", image);
				button.setId(temp.getPuzzleID());

				picPane1.getChildren().add(button);
				if (temp.getPuzzleID().equals(button.getId())) {
					button.setOnAction(e -> {
						// System.out.println(getPuzzlesArray().get(currentRoom).getPuzzleID());
						// System.out.println(temp.getPuzzleID());
						if (getPuzzlesArray().get(currentRoom).getPuzzleID().equals(temp.getPuzzleID())) {
							// System.out.println("correct");
							addObserver(LostTreasureMain.gui);
							setPuzzleHint("You answered the puzzle correctly!!!");
							temp.setSolved(true);
							popUp.close();
						} else {
							System.out.println("not correct");
							addObserver(LostTreasureMain.gui);
							setPuzzleHint("You clicked on the wrong answer, try again");
						}
						// System.out.println(temp.getPuzzleAnswer());
					});
				}

			}
			counter++;
		}

		// sets the text from the radio buttons to the description box
		descriptionText.setText(getPuzzleDescription());
		puzzleDescription.getChildren().add(descriptionText);
		// pane for buttons
		BorderPane hBox = new BorderPane();

		// adding the action listener from the controller class
		GridPane pane = new GridPane();
		pane.setHgap(5);
		// node,column,row
		pane.add(puzzleDescription, 0, 0);
		pane.add(picPane, 1, 0);
		pane.add(picPane1, 2, 0);
		pane.add(hBox, 0, 2);
		popUp.getDialogPane().setContent(pane);
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

	public String ViewAnswer(int currentRoom) {
		System.out.println("View puzzle "+currentRoom);
		setPuzzleAnswer(getPuzzlesArray().get(currentRoom).getPuzzleAnswer());
		return puzzleAnswer;

	}

	public boolean puzzleButtonClicked() {
		return true;
	}

	public void puzzleReader() throws FileNotFoundException {
		@SuppressWarnings("resource")
		Scanner reader = new Scanner(new File("puzzle.txt"));

		while (reader.hasNext()) {

			String puzzleID = reader.nextLine();
			String puzzleDescription = reader.nextLine();
			String puzzleAnswer = reader.nextLine();
			String puzzleHint = reader.nextLine();
			boolean solved = false;

			Puzzles puzzle = new Puzzles(puzzleID, puzzleDescription, puzzleAnswer, puzzleHint, solved);
			puzzlesArray.add(puzzle);

		}
	}

	@Override
	public String toString() {
		return puzzleID + " | " + puzzleDescription + " | " + puzzleAnswer + " | " + puzzleHint + " | ";
	}

//	public void puzzleAnswers(ArrayList<Puzzles> temp) {
//		for (Puzzles pz : temp) {
//			getPuzzlesAnswerArray().add(pz.getPuzzleAnswer());
//		}
//	}

	public String getPuzzleID() {
		return puzzleID;
	}

//	public void setPuzzleID(String puzzleID) {
//		this.puzzleID = puzzleID;
//	}

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

	public boolean isSolved() {
		return solved;
	}

	public void setSolved(boolean solved) {
		this.solved = solved;
	}

	public int getCurrentRoom() {
		return currentRoom;
	}
	

}