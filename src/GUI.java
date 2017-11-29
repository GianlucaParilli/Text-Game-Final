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
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

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
	private String character;
	private String correctAnswer;
	private ComboBox<String> roomsDropDown = new ComboBox<>();
	private String currentPicture = "0";
    boolean isCorrectAnswerSelected = true;
    boolean isSelected = true;
    Label hp;

    
	public ComboBox<String> getRoomsDropDown() {
		return roomsDropDown;
	}

	public void setCharacter(String character) {
		this.character = character;
	}
	
	public void setCorrectAnswer(String correctAnswer){
		this.correctAnswer = correctAnswer;
	}

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
		pane.setHgap(5);
		// node,column,row
		pane.add(mapPane(), 0, 0);
		pane.add(descriptionPane(), 1, 0);
		pane.add(roomButtonHPane(), 1, 2);
		//pane.add(monsterButtonHPane(), 1, 3);
		//pane.add(puzzleButtonHPane(), 1, 4);
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
				+ "-fx-border-insets: 10;" + "-fx-border-radius: 10;" + "-fx-border-color: black;");
		searchRoom =  new Button("Search Room");
		examine = new Button("Examine Room");
		examineMonster = new Button("Examine Monster");
		viewPuzzle = new Button("View Puzzle");
		pickupItem = new Button("Pickup Item");
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
	
	/*private VBox puzzlePopUpPane(ArrayList<String> puzzlesArray) {
		VBox vBox = new VBox();
		HBox hBox1 = new HBox(15);
		HBox hBox2 = new HBox(30);
		hBox1.setPadding(new Insets(15, 15, 15, 15));
		//Text input = new Text("Enter User Name");
		//userInput = new TextField();
		//hBox1.getChildren().add(input);
		//hBox1.getChildren().add(userInput);
		vBox.getChildren().add(hBox1);
		hBox2.setPadding(new Insets(15, 15, 15, 15));
		RadioButton StarsButton = new RadioButton("Stars");
		RadioButton TreesButton = new RadioButton("Trees");
		RadioButton FlowersButton = new RadioButton("Flowers");
		RadioButton MountainButton = new RadioButton("Mountain");
	
		
		hBox2.getChildren().add(StarsButton);
		hBox2.getChildren().add(TreesButton );
		hBox2.getChildren().add(FlowersButton);
		hBox2.getChildren().add(MountainButton);
		
		RadioButton cb; 
		RadioButton StarsButton;
		RadioButton TreesButton;
		RadioButton FlowersButton;
		RadioButton MountainButton;
		ToggleGroup toggleGroup = new ToggleGroup();

		for (String temp : puzzlesArray) {
			cb = new RadioButton(temp);
			cb.setFont(Font.font("Verdana", 16));

			cb.setToggleGroup(toggleGroup);
			vBox.getChildren().add(cb);
			if (temp.equals(cb.getText())) {
				cb.setOnAction(e -> {
					System.out.println(temp);
				});
			}
		}

		StarsButton.setToggleGroup(toggleGroup);
		TreesButton.setToggleGroup(toggleGroup);
		FlowersButton.setToggleGroup(toggleGroup);
		MountainButton.setToggleGroup(toggleGroup);	
		//});			
		vBox.getChildren().add(hBox2);
	
		return vBox;

	}*/




	private HBox descriptionPane() {
		HBox hBox = new HBox(15);
		hBox.setStyle("-fx-padding: 10;" + "-fx-border-style: solid inside;" + "-fx-border-width: 1;"
				+ "-fx-border-insets: 10;" + "-fx-border-radius: 10;" + "-fx-border-color: black;");
		hBox.setMinWidth(600);
		hBox.setMaxWidth(600);
		descriptionText = new Label();
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
				+ "-fx-border-insets: 10;" + "-fx-border-radius: 10;" + "-fx-border-color: black;");
		hBox.setPadding(new Insets(15, 15, 15, 15));
		mapView = new ImageView(new Image("Maps/r" + currentPicture + ".png"));
		mapView.setFitHeight(300);
		mapView.setFitWidth(300);
		hBox.getChildren().add(mapView);
		//hBox.setVisible(false);
		return hBox;
	}


	/*
	 * h box that assigns the exit button to an individual panel assigns a
	 * listener to the exit button to close the view
	 * 
	 */
	private HBox inventoryPane() {
		HBox hBox = new HBox(20);
		hBox.setStyle("-fx-padding: 10;" + "-fx-border-style: solid inside;" + "-fx-border-width: 1;"
				+ "-fx-border-insets: 10;" + "-fx-border-radius: 10;" + "-fx-border-color: black;");
		Button buttonInventory = new Button("Show Inventory");
		Label bag = new Label("Bag: 30/30");
		bag.setFont(Font.font("Verdana", 20));
		hBox.getChildren().add(buttonInventory);
		hBox.getChildren().add(bag);
		control.showInventoryListener(buttonInventory);
		return hBox;
	}
	private HBox healthPane() {
		HBox hBox = new HBox(20);
		hBox.setStyle("-fx-padding: 10;" + "-fx-border-style: solid inside;" + "-fx-border-width: 1;"
				+ "-fx-border-insets: 10;" + "-fx-border-radius: 10;" + "-fx-border-color: black;");
		hp = new Label("HP: 100/100");
		hp.setFont(Font.font("Verdana", 20));
		hBox.getChildren().add(hp);
		return hBox;
	}
	private HBox navButtonPane() {
		HBox hBox = new HBox(15);
		hBox.setStyle("-fx-padding: 10;" + "-fx-border-style: solid inside;" + "-fx-border-width: 1;"
				+ "-fx-border-insets: 10;" + "-fx-border-radius: 10;" + "-fx-border-color: black;");

		hBox.setPadding(new Insets(15, 15, 15, 15));
		goButton = new Button("Go!");
		Text text = new Text("Select Room");
		text.setFont(Font.font("Verdana", 20));
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
				+ "-fx-border-insets: 10;" + "-fx-border-radius: 10;" + "-fx-border-color: black;");
		Button exitButton = new Button("Exit");
		hBox.setPadding(new Insets(15, 15, 15, 15));
		hBox.getChildren().add(exitButton);
		exitButton.setOnAction(e -> {
			Stage stage = (Stage) exitButton.getScene().getWindow();
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

			mapView.setImage(new Image("Maps/r" + arg + ".png"));
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
		}
		else if( o instanceof Monster) {
			descriptionText.setText(arg.toString());
			if(((Monster) o).attackButtonClicked()) {
				((Monster)o).descriptionText.setText(arg.toString());
			}
			
		} 
		else if( o instanceof Items) {
			if(((Items)o).hasSearchedRoom(searchRoom.isArmed()) )
			{
				descriptionText.setText("There is a "+arg.toString() + " in the room");
			}
		
			else if(((Items)o).hasSearchedRoom(pickupItem.isArmed()))
			{
				descriptionText.setText("You picked up a "+arg.toString());
			}
		} 

	}		


}
