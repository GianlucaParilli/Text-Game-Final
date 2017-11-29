import javafx.geometry.Insets;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;

public class Controller {
	// Monster monster;
	Commands command;
	Puzzles puzzle = new Puzzles();
	Monster monster = new Monster();
	Rooms room = new Rooms();
	Login login = new Login();
	Items item = new Items();
	Navigation nav = new Navigation();
	Character character = new Character();
	String dropdown;
	boolean isLooted;

	public void newGameListener(Button button) {
		button.setOnAction(e -> {

		});
	}

	public void loadGameListener(Button button) {

		// command = new Commands();
		button.setOnAction(e -> {
			// command.loadGame();
			System.out.println("worked");
		});
	}
	// method that takes a button, then a listener is glued to the button
	// the model's method is called within here
	// model is the Rooms Class

	public void showInventoryListener(Button button) {
		button.setOnAction(e -> {
			
			item.inventoryPopUp(item.getInventory());
		});
	}

	public void showPuzzleListener(Button button){
		button.setOnAction(e ->{
			
			puzzle.puzzlePopUp(puzzle.getPuzzlesAnswerArray());
			
		});
	}
	public void examineRoomListener(Button temp) {
		// System.out.println("examine room");
		//temp.setId("r" + room.getRoomID());

		temp.setOnAction(e -> {
			room.addObserver(LostTreasureMain.gui);
			room.ExamineRoom();
			for (Rooms roomTemp : room.getRoomsArray()) {
				if (roomTemp.getRoomName().equals(dropdown)) {
					roomTemp.setExamined(true);
					room.enableButtons(LostTreasureMain.gui.searchRoom);
				}
			}

		});
	}

	public void pickupItemListener(Button temp) {
		// temp.setId(item.getItemID() + "Added to Inventory");
		temp.setOnAction(e -> {
			item.addObserver(LostTreasureMain.gui);
			String tempRoomID;
			String tempitemID = "";
			for (Rooms roomTemp : room.getRoomsArray()) {
				if (roomTemp.getRoomName().equals(dropdown)) {
					item.viewItems(roomTemp.getItem());
					tempRoomID = roomTemp.getItem();
					System.out.println("item in room " + tempRoomID);
					for (Items itemTemp : item.getItemsArray()) {
						if (itemTemp.getItemID().equals(tempRoomID)) {

							// System.out.println("2" + itemTemp.getItemID());
							tempitemID = itemTemp.getItemID();
							// System.out.println("looted " +roomTemp.isLooted());
							// System.out.println("tempID" + tempitemID);
							if (roomTemp.isLooted() == false) {
								String name = itemTemp.getItemName();
								item.getInventory().add(name);
								// System.out.println("picked item");
								roomTemp.setLooted(true);
							}

						}

					}
					// unlocks room 9
					if (tempitemID.equals("K1")) {
						room.getRoomsArray().get(9).setLocked(false);

					}

				}
			}

		});
	}

	public void viewPuzzleListener(Button temp) {
		temp.setId(puzzle.getPuzzleDescription());
		temp.setOnAction(e -> {
			//puzzle.addObserver(LostTreasureMain.gui);
			//puzzle.ViewPuzzle(room.getCurrentRoom());
			
			//go to puzzle pop up for the buttons listeners
			puzzle.puzzlePopUp(LostTreasureMain.gui.answerPuzzle, LostTreasureMain.gui.hintPuzzle,
							   LostTreasureMain.gui.exitPuzzle, room);
		});
	}

	public void viewMonsterListener(Button temp) {
		temp.setId(monster.getMonsterDescription());
		temp.setOnAction(e -> {
			monster.addObserver(LostTreasureMain.gui);
			for(Rooms roomTemp : room.getRoomsArray()){
				if (roomTemp.getRoomName().equals(dropdown)) {
					if(roomTemp.getRoomName().equals(dropdown) && roomTemp.isSearched() == true)
					{
						//System.out.println("current room monster id " + roomTemp.getMonster());
						//System.out.println("sssmmm" + monster.currentMonster(roomTemp.getMonster()));

						//room.enableButtons(LostTreasureMain.gui.fightMonster);
						//room.enableButtons(LostTreasureMain.gui.fleeMonster);
						monster.setCurrentMonster(monster.currentMonster(roomTemp.getMonster()));
						System.out.println("get current monster index "+monster.getCurrentMonster());
					} else 
					{
						System.out.println("Haven't Searched");
					}
				}
				
			}
			monster.ViewMonster(room.getCurrentRoom());
			attackPopUp(LostTreasureMain.gui.fightMonster, LostTreasureMain.gui.fleeMonster, room);

		});
		
		
		
		
		
		
		
		
		
		
	}
	public void attackPopUp(Button attack, Button flee, Rooms room) {
		
		Alert popUp = new Alert(AlertType.NONE);
		popUp.setTitle("Battle");
		popUp.setHeaderText("Battle");
		popUp.setResizable(true);
		popUp.setWidth(150);
		ImageView logo = new ImageView("logo.png");
		logo.setFitWidth(64);
	    logo.setFitHeight(64);
		popUp.setGraphic(logo);
		attack = new Button("Attack");
		flee = new Button("Flee");	
		
		popUp.getButtonTypes().add(ButtonType.CANCEL);
		popUp.hide();
		popUp.getButtonTypes().remove(ButtonType.CANCEL);

		attack.setOnAction(e->{
			if(monster.getMonstersArray().get(monster.getCurrentMonster()).getHP() <= 0) {
				System.out.println("AttackedNOWWW");
			}
			System.out.println("attackedTHIS");
		});
		flee.setOnAction(e -> {
			monster.addObserver(LostTreasureMain.gui);
			
			monster.FleeMonster(room.getCurrentRoom());
			// quits and closes the gui
			popUp.close();

			System.out.println("flee");

		});	
		//description pane for popUp
		HBox monsterDescription = new HBox(15);
		monsterDescription.setMinHeight(300);
		monsterDescription.setStyle("-fx-padding: 10;" + "-fx-border-style: solid inside;" + "-fx-border-width: 1;"
				+ "-fx-border-insets: 10;" + "-fx-border-radius: 10;" + "-fx-border-color: black;");
		monsterDescription.setMinWidth(300);
		monsterDescription.setMaxHeight(200);
		monster.descriptionText = new Label();
		monster.descriptionText.setFont(Font.font("Verdana", 15));
		monster.descriptionText.setWrapText(true);
		monsterDescription.setPadding(new Insets(15, 15, 15, 15));
		// sets the text from the radio buttons to the description box
		monster.descriptionText.setText(monster.getMonstersArray().get(monster.getCurrentMonster()).getMonsterDescription());
		monsterDescription.getChildren().add(monster.descriptionText);
		
		//pane for buttons
		BorderPane hBox = new BorderPane();
		attack.setTranslateX(40);
		flee.setTranslateX(-40);
		hBox.setLeft(attack);
		hBox.setRight(flee);

		// adding the action listener from the controller class

		GridPane pane = new GridPane();
		pane.setHgap(5);
		// node,column,row
		pane.add(monsterDescription, 0, 0);
		pane.add(hBox, 0, 1);
		popUp.getDialogPane().setContent(pane);
		popUp.show();		
	
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
		public void viewHintListener(Button temp){
			temp.setId(puzzle.getPuzzleHint());
			temp.setOnAction(e -> {
				puzzle.addObserver(LostTreasureMain.gui);
				puzzle.ViewHint(room.getCurrentRoom());
			});
		}
		public void viewAnswerListener(Button temp){
			temp.setId(puzzle.getPuzzleAnswer());
			temp.setOnAction(e -> {
				puzzle.addObserver(LostTreasureMain.gui);
				puzzle.ViewAnswer(room.getCurrentRoom());
			});
		}
		public void fleeMonsterListener(Button flee) {
			flee.setOnAction(e->{
				//monster.addObserver(LostTreasureMain.gui);
				//monster.FleeMonster(room.getCurrentRoom());
				
			});
		}
		public void attackMonsterListener(Button attack) {
		attack.setOnAction(e->{
				System.out.println("you have attacked the monster");
				monster.addObserver(LostTreasureMain.gui);
				monster.AttackMonster(room.getCurrentRoom());
				//creates a pop up with the attack and flee buttons, go to monsters for listeners
				attackPopUp(LostTreasureMain.gui.fightMonster, LostTreasureMain.gui.fleeMonster, room);
				
				
			
			});
			}
		

		public void searchRoomListener(Button temp){
			temp.setId(item.getItemDescription());
			temp.setOnAction(e -> {
				item.addObserver(LostTreasureMain.gui);
				//item.viewItems(room.getCurrentRoom());
				//System.out.println("current "+room.getCurrentRoom());
				
				for(Rooms roomTemp : room.getRoomsArray()){
					if (roomTemp.getRoomName().equals(dropdown)) {
						
						if(roomTemp.getRoomName().equals(dropdown) && roomTemp.isLooted() == false)
						{
							roomTemp.setSearched(true);
							//System.out.println(roomTemp.isSearched() + "sss");

							room.enableButtons(LostTreasureMain.gui.pickupItem);
							room.enableButtons(LostTreasureMain.gui.examineMonster);
							room.enableButtons(LostTreasureMain.gui.pickupItem);	
							room.enableButtons(LostTreasureMain.gui.viewPuzzle);				

						} else 
						{
							System.out.println("Already Looted");
						}
					}
					
				}
			});
			
		}
		
		
	
//refreshes the map pane
//re populates the drop-down with the available rooms

	// refreshes the map pane
	// re populates the drop-down with the available rooms
	public void refreshMap(Button temp) {

		temp.setOnAction(e -> {

			nav.addObserver(LostTreasureMain.gui);
			dropdown = GUI.gui.getRoomsDropDown().getValue();

			for (Rooms roomTemp : room.getRoomsArray()) {
				if (roomTemp.getRoomName().equals(dropdown)) {
					if (roomTemp.isLocked() == true) {
						if (item.getInventory().contains(item)) {
							roomTemp.setLocked(item.unlockDoor());
							room.doorUnlockedPopUp(roomTemp.getRoomName());
							//System.out.println("is the room locked? " + roomTemp.isLocked());
							break;
						}
						room.lockedPopUp(roomTemp.getRoomName());

						break;
					}

					nav.setCurrentRoom(roomTemp.getNumRoomID());
					nav.refreshMap(roomTemp.getNumRoomID());
					room.setCurrentRoom(roomTemp.getNumRoomID());
					GUI.gui.getRoomsDropDown().getItems().clear();// clears the previous drop-down
					room.availableRoom(room.getCurrentRoom());// calls the available room method wit the room number
																// that user went to
					// GUI.gui.getRoomsDropDown().getItems().addAll(roomTemp.getRoomIDArray());
					//System.out.println("is the room locked? " + roomTemp.isLocked());
					//System.out.println("is the room examined? " + roomTemp.isExamined());
					room.enableButtons(LostTreasureMain.gui.examine);

					if (roomTemp.isExamined() == false) {
						room.disableButton(LostTreasureMain.gui.searchRoom);
					}
					if (roomTemp.isSearched() == false) {
						room.disableButton(LostTreasureMain.gui.pickupItem);
						room.disableButton(LostTreasureMain.gui.examineMonster);
					}

				}

				//

			}

			room.getCurrentRoom();
		});
	}



}
