import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import javafx.stage.Stage;


/**
 * @author Gianluca Parilli
 * @version 1.0 
 * @Course : ITEC 3860, Fall, 2017 Written: October 15, 2017
 *  
 */
public class GUI extends Login implements Observer {
	Button examine, goButton, searchRoom, fightMonster, 
	fleeMonster, examineMonster, viewPuzzle, answerPuzzle, pickupItem, hintPuzzle, exitPuzzle;
	static Stage guiStage = new Stage();
	static Stage inventoryStage = new Stage();
	Label descriptionText;
	ImageView mapView;
	Rooms room = new Rooms();
	Navigation nav = new Navigation();
	Controller control = new Controller();
	Character playerChar = new Character("char1", "Luca", "Thief", 1000, 15, 0, false ,false);
	Monster monster = new Monster();
	private String character;
	@SuppressWarnings("unused")
	private String correctAnswer;
	private ComboBox<String> roomsDropDown = new ComboBox<>();
	private String currentPicture = "0";
    boolean isCorrectAnswerSelected = true;
    boolean isSelected = true;
    Label hp, bag, xp;
  


	public void start(Stage primaryStage) throws InterruptedException {
		BorderPane bPane = new BorderPane();
		guiStage = primaryStage;
		guiStage.setResizable(false);
		bPane.setCenter(combinedPanes());
		// Create a scene and place it in the stage
		Scene scene = new Scene(bPane);
		primaryStage.setTitle("The Lost treasure"); // Set the stage title
		primaryStage.setScene(scene); // Place the scene in the stage
		primaryStage.setWidth(1040);
		primaryStage.setHeight(585);
		
		primaryStage.show(); // Display the stage
	}

	private Pane combinedPanes() {
		GridPane pane = new GridPane();
		BackgroundImage background= new BackgroundImage(new Image("/cave.jpg",1040,585,false,true),
		        BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,
		          BackgroundSize.DEFAULT);
		//then you set to your node
		pane.setBackground(new Background(background));
		pane.setHgap(5);
		// node,column,row
		pane.add(mapPane(), 0, 0);
		pane.add(descriptionPane(), 1, 0);
		pane.add(roomButtonHPane(), 1, 2);
		pane.add(navButtonPane(), 1, 1);
		pane.add(inventoryPane(), 0, 1);
		pane.add(healthPane(), 0, 2);
		pane.add(exitPane(), 2, 5);
		return pane;

	}

	/*
	 * Sets an individual hbox to format where the buttons are located Then the
	 * hbox will be added to the main pane
	 *
	 **/
	private HBox roomButtonHPane() {
		HBox hBox = new HBox(5);
		hBox.setStyle("-fx-padding: 10;" + "-fx-border-style: solid inside;" + "-fx-border-width: 1;"
				+ "-fx-border-insets: 10;" + "-fx-border-radius: 10;" + "-fx-border-color: white;");
		searchRoom =  new Button("Search Room");
		examine = new Button("Examine Room");
		examineMonster = new Button("Examine Monster");
		viewPuzzle = new Button("View Puzzle");
		pickupItem = new Button("Pick Up Item");
		searchRoom.setDisable(true);
		pickupItem.setDisable(true);
		hBox.getChildren().add(examine);
		hBox.getChildren().add(searchRoom);
		hBox.getChildren().add(examineMonster);
		hBox.getChildren().add(viewPuzzle);
		hBox.getChildren().add(pickupItem);
		//adding the action listener from the controller class
		control.examineRoomListener(examine);
		control.searchRoomListener(searchRoom);
		control.pickupItemListener(pickupItem);
		control.viewMonsterListener(examineMonster);
		control.viewPuzzleListener(viewPuzzle);

		return hBox;
	}
	


	private HBox descriptionPane() {
		HBox hBox = new HBox(15);
		hBox.setStyle("-fx-padding: 10;" + "-fx-border-style: solid inside;" + "-fx-border-width: 1;"
				+ "-fx-border-insets: 10;" + "-fx-border-radius: 10;" + "-fx-border-color: white;");
		hBox.setMinWidth(600);
		hBox.setMaxWidth(600);
		descriptionText = new Label();
		descriptionText.setStyle("-fx-text-fill: white;");
		descriptionText.setFont(Font.font("Verdana", 26));
		descriptionText.setWrapText(true);
		hBox.setPadding(new Insets(15, 15, 15, 15));
		//sets the text from the radio buttons to the description box 
		descriptionText.setText(character);
		hBox.getChildren().add(descriptionText);
		return hBox;
	}

	public HBox mapPane() {
		HBox hBox = new HBox(15);
		hBox.setStyle("-fx-padding: 10;" + "-fx-border-style: solid inside;" + "-fx-border-width: 1;"
				+ "-fx-border-insets: 10;" + "-fx-border-radius: 10;" + "-fx-border-color: white;");
		hBox.setPadding(new Insets(15, 15, 15, 15));
		mapView = new ImageView(new Image("Map/r" + currentPicture + ".png"));
		mapView.setFitHeight(300);
		mapView.setFitWidth(300);
		hBox.getChildren().add(mapView);
		return hBox;
	}


	/*
	 * h box that assigns the inventory button to an individual panel 
	 * and a lot of formating 
	 */
	private HBox inventoryPane() {
		HBox hBox = new HBox(20);
		hBox.setStyle("-fx-padding: 10;" + "-fx-border-style: solid inside;" + "-fx-border-width: 1;"
				+ "-fx-border-insets: 10;" + "-fx-border-radius: 10;" + "-fx-border-color: white;");
		Button buttonInventory = new Button("Show Inventory");
		bag = new Label("Bag: 30/30");
		bag.setStyle("-fx-text-fill: white;");

		bag.setFont(Font.font("Verdana", 20));
		hBox.getChildren().add(buttonInventory);
		hBox.getChildren().add(bag);
		control.showInventoryListener(buttonInventory);
		return hBox;
	}

	/*
	 * h box that assigns the health text to an individual panel 
	 */
	private HBox healthPane() {
		HBox hBox = new HBox(20);
		hBox.setStyle("-fx-padding: 10;" + "-fx-border-style: solid inside;" + "-fx-border-width: 1;"
				+ "-fx-border-insets: 10;" + "-fx-border-radius: 10;" + "-fx-border-color: white;");
		hp = new Label(playerChar.getCharHealth() + "/1000 HP");
		hp.setStyle("-fx-text-fill: white;");
		hp.setFont(Font.font("Verdana", 20));
		xp = new Label(playerChar.getCharXP() + " XP");
		xp.setStyle("-fx-text-fill: white;");
		xp.setFont(Font.font("Verdana", 20));
		hBox.getChildren().add(hp);
		hBox.getChildren().add(xp);
		return hBox;
	}
	private HBox navButtonPane() {
		HBox hBox = new HBox(15);
		hBox.setStyle("-fx-padding: 10;" + "-fx-border-style: solid inside;" + "-fx-border-width: 1;"
				+ "-fx-border-insets: 10;" + "-fx-border-radius: 10;" + "-fx-border-color: white;");

		hBox.setPadding(new Insets(15, 15, 15, 15));
		goButton = new Button("Go!");
		Label text = new Label("Select Room:");
		text.setFont(Font.font("Verdana", 20));
		text	.setStyle("-fx-text-fill: white;");
		roomsDropDown = new ComboBox<>();
		roomsDropDown.setEditable(false);

		ArrayList<String> roomNameArray = new ArrayList<>();
		//get name from array -- fix
		for(int i = 1; i<10;i++){
			roomNameArray.add(room.getRoomsArray().get(i).getRoomName());
		}
		
		roomsDropDown.getItems().addAll(roomNameArray);
		roomsDropDown.setPromptText("Entrance Hall");
		
		if(room.getCurrentRoom()==0) {
			room.setCurrentRoom(room.getNumRoomID());				
			room.enableButtons(searchRoom);
			room.disableButton(examineMonster);
			room.disableButton(viewPuzzle);

		}
		hBox.getChildren().add(text);
		hBox.getChildren().add(roomsDropDown);
		hBox.getChildren().add(goButton);
		//adds the listener to the go button
		control.refreshMap(goButton);

		return hBox;
	}

	private HBox exitPane() {
		HBox hBox = new HBox(15);
		hBox.setStyle("-fx-padding: 10;" + "-fx-border-style: solid inside;" + "-fx-border-width: 1;"
				+ "-fx-border-insets: 10;" + "-fx-border-radius: 10;" + "-fx-border-color: white;");
		Button exitButton = new Button("Exit");
		hBox.setPadding(new Insets(15, 15, 15, 15));
		hBox.getChildren().add(exitButton);
		exitButton.setOnAction(e -> {
			Stage stage = (Stage) exitButton.getScene().getWindow();
			writer();
			stage.close();
		});
		return hBox;
	}
	// observer, observable methods that will update the gui
	public void setRoomsDropDown(ComboBox<String> roomsDropDown) {
		this.roomsDropDown = roomsDropDown;
	}
	@Override
	public void update(Observable o, Object arg) {

		if (o instanceof Navigation) {
			//System.out.println("update nav "+ arg);

			mapView.setImage(new Image("Map/r" + arg + ".png"));
		} 
		else if (o instanceof Rooms) {
			if(((Rooms) o).hasExaminedRoom(examine.isArmed()))
			{
				descriptionText.setText(arg.toString());
			}
			else if(((Rooms) o).hasExaminedRoom(goButton.isArmed()))
			{
				System.out.println("f");
			}
		}
		else if( o instanceof Puzzles) {
			descriptionText.setText(arg.toString());
			if(((Puzzles) o).puzzleButtonClicked()) {
				((Puzzles)o).descriptionText.setText(arg.toString());
			
			}
		}
		else if( o instanceof Monster) {
			descriptionText.setText(arg.toString());
			if(((Monster) o).attackButtonClicked()) {
				((Monster)o).descriptionText.setText(arg.toString());
			}	
			
			
		} 	else if( o instanceof Character) {
			//descriptionText.setText(arg.toString());
			hp.setText(arg.toString()+ "/1000");
			//System.out.println(""+((Character)o).isMonsterDefeated());
			xp.setText(50 +" HP");
			
		}  
		else if( o instanceof Items) {
			if(((Items)o).hasSearchedRoom(searchRoom.isArmed()) )
			{
				descriptionText.setText("There is a "+arg.toString() + " in the room");
			}
		
			else if(((Items)o).hasSearchedRoom(pickupItem.isArmed()))
			{
				descriptionText.setText(arg.toString());
			}
			
		} 

	}		
	public void writer() {
	try {
		PrintWriter write = new PrintWriter(new File("saveState.txt"));
		write.println(playerChar.toString());
		write.close();
	} catch (FileNotFoundException e) {
		// TODO Auto-generated catch block
		System.out.println("There is an error with the saveState.txt file");
	}
	
	}
    
	public ComboBox<String> getRoomsDropDown() {
		return roomsDropDown;
	}

	public void setCharacter(String character) {
		this.character = character;
	}
	
	public void setCorrectAnswer(String correctAnswer){
		this.correctAnswer = correctAnswer;
	}

}
