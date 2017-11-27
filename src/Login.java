import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;
import java.util.Optional;
import javafx.geometry.*;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class Login extends LostTreasureMain implements Observer {
	TextField userInput;
	protected ArrayList<String> usernamesArray = new ArrayList<>();
	protected ArrayList<Button> arrayButtons = new ArrayList<>();
	boolean isCharacterSelected = false;
	boolean invalidUsername = true;

	// ImageView imageView = new ImageView(new Image("logo.png"));
	Button newGameButton;
	Button loadGameButton;
	Rooms room = new Rooms();
	// GUI gui = new GUI();



	public void start(Stage primaryStage) throws InterruptedException {
		BorderPane bPane = new BorderPane();
		bPane.setCenter(mainPane());

		// Create a scene and place it in the stage
		Scene scene = new Scene(bPane);
		primaryStage.setTitle("The Lost Treasure"); // Set the stage title
		primaryStage.setScene(scene); // Place the scene in the stage
		primaryStage.setWidth(240);
		primaryStage.setHeight(300);
		primaryStage.setResizable(false);
		primaryStage.show(); // Display the stage
	}

	// adds all the buttons to a V box
	// sets the text for the buttons
	// adds the topPane method(picture) to the v pane
	// sets all the listeners for the buttons
	private VBox mainPane() {
		VBox vpane = new VBox();
		newGameButton = new Button("  New Game  ");
		loadGameButton = new Button("  Load  ");
		Button exitButton = new Button("  Exit  ");
		newGameButton.setTranslateX(70);
		loadGameButton.setTranslateX(87);
		exitButton.setTranslateX(91);
		vpane.getChildren().add(picturePane());
		vpane.getChildren().add(newGameButton);
		vpane.getChildren().add(loadGameButton);
		vpane.getChildren().add(exitButton);

		// control.addButtonListener(loadGameButton);

		newGameButton.setOnAction(e -> {

			Alert popUp = new Alert(AlertType.CONFIRMATION);
			popUp.getDialogPane().setContent(popUpPane());
			popUp.setTitle("Create UserName");
			Optional<ButtonType> result = popUp.showAndWait();
			ButtonType button = result.orElse(ButtonType.CANCEL);
			// when ok button pressed on the alert, the user name will be store
			// into an array
			// verifies that there is'nt a duplicate user name

			if(userInput.getText().equals("") )  {
				Alert errorPopUp = new Alert(AlertType.ERROR);
				errorPopUp.setHeaderText("You Entered an invalid user name");
				errorPopUp.show();
			}
			else {
				if (button == ButtonType.OK && isCharacterSelected) {
					String usernameString = userInput.getText();

					for (int i = usernamesArray.size() - 1; i > 0; i--) {
						if (usernamesArray.get(i).equals(userInput.getText())) {
							// usernamesArray.remove(usernamesArray.size()-1);
							System.out.println("error duplicate");
							usernamesArray.remove(userInput.getText());
						}

					}
					usernamesArray.add(usernameString);

					// writes user name into a txt file for the load feature
					// writer(usernamesArray);

					// gui = new GUI();
					try {
						gui.start(guiStage);

						// remember to close previous stage
					} catch (InterruptedException e1) {
						System.out.println("GUI failed to start");
					}

				} else if(button == ButtonType.OK && isCharacterSelected==false){
					Alert errorPopUp = new Alert(AlertType.ERROR);
					errorPopUp.setHeaderText("You did not select a Character");
					errorPopUp.show();
				}
				else {
					System.out.println("canceled");
				}

			}});


		// load game listener
		loadGameButton.setOnAction(e -> {
			if (usernamesArray.size() == 0) {
				Alert errorPopUp = new Alert(AlertType.ERROR);
				errorPopUp.setHeaderText("there are no saved profiles");
				errorPopUp.show();
			} else {
				loadPopUp(usernamesArray);

			}
		});
		// listener that closes the gui
		exitButton.setOnAction(e -> {
			// quits and closes the gui
			Stage stage = (Stage) exitButton.getScene().getWindow();
			stage.close();
		});

		return vpane;
	}

	// pop up for the load button
	private void loadPopUp(ArrayList<String> userStringtemp) {
		Alert popUp = new Alert(AlertType.INFORMATION);
		popUp.setTitle("Load Game");
		VBox pop = new VBox();
		pop.setPadding(new Insets(20, 20, 20, 20));
		Button b = new Button();

		for (String temp : userStringtemp) {
			b = new Button(temp);
			pop.getChildren().add(b);
			if (temp.equals(b.getText())) {
				b.setOnAction(e -> {
					System.out.println(temp);
				});
			}
		}
		popUp.getDialogPane().setContent(pop);
		popUp.show();
	}

	// add an image view into a H box
	// sets image size and placement inside the hbox
	private HBox picturePane() {
		HBox hBox = new HBox(15);
		hBox.setPadding(new Insets(15, 15, 15, 15));
		ImageView imageView = new ImageView(new Image("logo.png"));
		imageView.setFitHeight(130);
		imageView.setFitWidth(130);
		imageView.setTranslateX(40);
		hBox.getChildren().add(imageView);
		return hBox;
	}

	private VBox popUpPane() {
		VBox vBox = new VBox();
		// organizes the form into a h box within a v box
		HBox hBox1 = new HBox(15);
		HBox hBox2 = new HBox(30);
		hBox1.setPadding(new Insets(15, 15, 15, 15));
		Text input = new Text("Enter User Name");
		userInput = new TextField();
		hBox1.getChildren().add(input);
		hBox1.getChildren().add(userInput);
		vBox.getChildren().add(hBox1);
		hBox2.setPadding(new Insets(15, 15, 15, 15));

		RadioButton archeologistButton = new RadioButton("Archeologist");
		RadioButton thiefButton = new RadioButton("Thief");
		
		hBox2.getChildren().add(archeologistButton);
		hBox2.getChildren().add(thiefButton);
		ToggleGroup toggleGroup = new ToggleGroup();

		archeologistButton.setToggleGroup(toggleGroup);
		thiefButton.setToggleGroup(toggleGroup);
		//setCharacterDescription("Archeologist");

		archeologistButton.setOnAction(e->{
			Login.gui.setCharacter("You are an Archeologist");
			isCharacterSelected = true;
		});
		thiefButton.setOnAction(e->{
			Login.gui.setCharacter("You are a Thief");
			isCharacterSelected = true;

		});


		vBox.getChildren().add(hBox2);

		return vBox;

	}


	@Override
	public void update(Observable o, Object arg) {
		// TODO Auto-generated method stub

	}

}
