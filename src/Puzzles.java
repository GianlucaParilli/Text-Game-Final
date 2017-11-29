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
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;

public class Puzzles extends Observable {

	private String puzzleID;
	private String puzzleDescription;
	private String puzzleAnswer;
	private String puzzleHint;
	private int currentRoom;
	private static ArrayList<Puzzles> puzzlesArray = new ArrayList<>();
	private static ArrayList<String> puzzlesAnswerArray = new ArrayList<>();
	Label descriptionText = new Label();


	//private ArrayList<> puzzles = new ArrayList<>();

	public Puzzles() {
		try {
			puzzleReader();
			puzzleAnswers(getPuzzlesArray());

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
	
	
	
	
	/*public int currentPuzzle(String puzzleID) {
		int currentID=0;
		//System.out.println("monsters position " +getMonstersArray().get(0));
		for(Puzzles m : getPuzzlesArray()) {
			if(m.getPuzzleID().equals(puzzleID)) {
				currentID = getPuzzlesArray().indexOf(m);
				System.out.println("current monster " + m.getPuzzleDescription());
				setPuzzleDescription((m.getPuzzleDescription()));

			}
		}
		System.out.println("current monster position " + currentID);

		return currentID;
	}*/

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

		for (String temp : puzzleArray) {
			System.out.println("h"+temp);
			//cb = new RadioButton(temp.getPuzzleAnswer());
			egg = new RadioButton("  "+temp);
			//cb.setFont(Font.font("Verdana", 16));

			egg.setToggleGroup(toggleGroup);
			pop.getChildren().add(egg);
			//System.out.println(puzzleArray.get(0));
			if (puzzlesArray.get(0).getPuzzleAnswer().equals("Mountain")) {
				//pop.getChildren().add(cb);
				egg.setOnAction(e -> {
					//System.out.println("wrong");
				});
			}
		}
		popUp.getDialogPane().setContent(pop);
		popUp.show();
	}
	public void puzzlePopUp(Button answer, Button hint, Button exit, Rooms room) {
		
		Alert popUp = new Alert(AlertType.NONE);
		popUp.setTitle("Puzzle");
		popUp.setHeaderText("Puzzle");
		popUp.setResizable(true);
		popUp.setWidth(150);
		ImageView logo = new ImageView("logo.png");
		logo.setFitWidth(64);
	    logo.setFitHeight(64);
		popUp.setGraphic(logo);
		answer = new Button("answer");
		hint = new Button("Hint");	
		exit = new Button("Exit");
		popUp.getButtonTypes().add(ButtonType.CANCEL);
		popUp.hide();
		popUp.getButtonTypes().remove(ButtonType.CANCEL);

		answer.setOnAction(e->{
			System.out.println("answer");
			
			
		});
		hint.setOnAction(e->{

			
			
		});
		exit.setOnAction(e -> {
			addObserver(LostTreasureMain.gui);
			
			//FleeMonster(room.getCurrentRoom());
			// quits and closes the gui
			popUp.close();

			System.out.println("closed");

		});	
		//description pane for popUp
		HBox puzzleDescription = new HBox(15);
		puzzleDescription.setMinHeight(300);
		puzzleDescription.setStyle("-fx-padding: 10;" + "-fx-border-style: solid inside;" + "-fx-border-width: 1;"
				+ "-fx-border-insets: 10;" + "-fx-border-radius: 10;" + "-fx-border-color: black;");
		puzzleDescription.setMinWidth(300);
		puzzleDescription.setMaxHeight(200);
		//descriptionText = new Label();
		//descriptionText.setFont(Font.font("Verdana", 15));
		//descriptionText.setWrapText(true);
		puzzleDescription.setPadding(new Insets(15, 15, 15, 15));
		RadioButton cb; 
		RadioButton StarsButton;
		RadioButton TreesButton;
		RadioButton FlowersButton;
		RadioButton MountainButton;
		ToggleGroup toggleGroup = new ToggleGroup();

		for (String temp :puzzlesAnswerArray) {
			cb = new RadioButton(temp);
			StarsButton = new RadioButton(temp);
			cb.setFont(Font.font("Verdana", 16));

			cb.setToggleGroup(toggleGroup);
			puzzleDescription.getChildren().add(cb);
			if (temp.equals(cb.getText())) {
				cb.setOnAction(e -> {
					System.out.println(temp);
				});
			}
		}

		// sets the text from the radio buttons to the description box
		
		//descriptionText.setText("jsd");
	
		//puzzleDescription.getChildren().add(descriptionText);
		
		//pane for buttons
		BorderPane hBox = new BorderPane();
		//answer.setTranslateX(20);
		//hint.setTranslateX(50);
		//exit.setTranslateX(90);
		
		hBox.setLeft(answer);
		hBox.setCenter(hint);
		hBox.setRight(exit);
		//hBox.getChildren().add(answer);
		//hBox.getChildren().add(hint);
		//hBox.getChildren().add(exit);

		

		// adding the action listener from the controller class

		GridPane pane = new GridPane();
		pane.setHgap(5);
		// node,column,row
		pane.add(puzzleDescription, 0, 0);
		pane.add(hBox, 0, 1);
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
	
	public String ViewAnswer(int currentRoom){
		System.out.println(currentRoom);
		setPuzzleAnswer(getPuzzlesArray().get(currentRoom).getPuzzleAnswer());
		return puzzleAnswer;

	}
	

	public void puzzleReader() throws FileNotFoundException {
		@SuppressWarnings("resource")
		Scanner reader = new Scanner(new File("puzzle.txt"));

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
	public void puzzleAnswers(ArrayList<Puzzles>temp) {
		for(Puzzles pz : temp) {
			getPuzzlesAnswerArray().add(pz.getPuzzleAnswer());
		}
	}
	//d
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

	public  ArrayList<String> getPuzzlesAnswerArray() {
		return puzzlesAnswerArray;
	}

	public void setPuzzlesAnswerArray(ArrayList<String> puzzlesAnswerArray) {
		Puzzles.puzzlesAnswerArray = puzzlesAnswerArray;
	}


}