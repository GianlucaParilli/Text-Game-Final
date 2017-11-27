import java.util.ArrayList;

import javafx.geometry.Insets;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.VBox;

public class Controller {
	// Monster monster;
	Commands command;
	Puzzles puzzle = new Puzzles();
	Monster monster = new Monster();
	Rooms room = new Rooms();
	Login login = new Login();
	Items item = new Items();
	Navigation nav = new Navigation();
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

	public void examineRoomListener(Button temp) {
		// System.out.println("examine room");
		temp.setId("r" + room.getRoomID());

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
		temp.setId(item.getItemID() + "Added to Inventory");
		temp.setOnAction(e -> {
			item.addObserver(LostTreasureMain.gui);
			item.viewItems(room.getCurrentRoom());
			String tempRoomID;
			String tempitemID = "";
			for (Rooms roomTemp : room.getRoomsArray()) {
				if (roomTemp.getRoomName().equals(dropdown) ) {	
					tempRoomID = roomTemp.getItem();
					System.out.println("tt" + tempRoomID);
					for(Items itemTemp : item.getItemsArray()) {
						if(itemTemp.getItemID().equals(tempRoomID)) {
							
							System.out.println("2" + itemTemp.getItemID());
							tempitemID = item.getItemID();
							System.out.println(" " +roomTemp.isLooted());

							if(roomTemp.isLooted()==false) {	
							String name = itemTemp.getItemName();
							item.getInventory().add(name);
							System.out.println("picked item");
							roomTemp.setLooted(true);
							}
						}
					}									
				}
				//unlocks room 9
				if(roomTemp.getItem().equals("W5")) {//item.getKeysID().get(0))) {
					room.getRoomsArray().get(9).setLocked(false);
					//System.out.println(roomTemp.isLocked() + "  wwww");
				}
			}
			

		});
	}

	public void viewPuzzleListener(Button temp) {
		temp.setId(puzzle.getPuzzleDescription());
		temp.setOnAction(e -> {
			puzzle.addObserver(LostTreasureMain.gui);
			puzzle.ViewPuzzle(room.getCurrentRoom());

		});
	}

	public void viewMonsterListener(Button temp) {
		temp.setId(monster.getMonsterDescription());
		temp.setOnAction(e -> {
			monster.addObserver(LostTreasureMain.gui);
			monster.ViewMonster(room.getCurrentRoom());
		});
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
				monster.addObserver(LostTreasureMain.gui);
				monster.FleeMonster(room.getCurrentRoom());
				
			});
		}
		public void attackMonsterListener(Button attack) {
			attack.setOnAction(e->{
				System.out.println("you have attacked the monster");
				monster.addObserver(LostTreasureMain.gui);
				monster.AttackMonster(room.getCurrentRoom());
				
				
			});
		}

		public void searchRoomListener(Button temp){
			temp.setId(item.getItemDescription());
			temp.setOnAction(e -> {
				item.addObserver(LostTreasureMain.gui);
				item.viewItems(room.getCurrentRoom());
				for(Rooms roomTemp : room.getRoomsArray()){
					if (roomTemp.getRoomName().equals(dropdown)) {
						if(roomTemp.getRoomName().equals(dropdown) && roomTemp.isLooted() == false)
						{
							roomTemp.setSearched(true);
							System.out.println(roomTemp.isSearched() + "sss");
							room.enableButtons(LostTreasureMain.gui.pickupItem);	
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
							System.out.println("is the room locked? " + roomTemp.isLocked());
							break;
						}
						room.loadPopUp(roomTemp.getRoomName());

						break;
					}
					nav.setCurrentRoom(roomTemp.getNumRoomID());
					nav.refreshMap(roomTemp.getNumRoomID());
					room.setCurrentRoom(roomTemp.getNumRoomID());
					GUI.gui.getRoomsDropDown().getItems().clear();// clears the previous drop-down
					room.availableRoom(room.getCurrentRoom());// calls the available room method wit the room number
																// that user went to
					// GUI.gui.getRoomsDropDown().getItems().addAll(roomTemp.getRoomIDArray());
					// System.out.println("is the room locked? " + roomTemp.isLocked());
					System.out.println("is the room examined? " + roomTemp.isExamined());
					room.enableButtons(LostTreasureMain.gui.examine);

					if (roomTemp.isExamined() == false) {
						room.disableButton(LostTreasureMain.gui.searchRoom);
					}
					if (roomTemp.isSearched() == false) {
						room.disableButton(LostTreasureMain.gui.pickupItem);
					}

				}

				//

			}

			room.getCurrentRoom();
		});
	}



}
