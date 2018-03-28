import javafx.scene.control.Button;

/**
 * @author Gianluca Parilli
 * @version 1.0
 * @Course : ITEC 3860, Fall, 2017 Written: October 15, 2017
 * 
 */

public class Controller {
	// Monster monster;
	Puzzles puzzle = new Puzzles();
	Monster monster = new Monster();
	Rooms room = new Rooms();
	Login login = new Login();
	Items item = new Items();
	Navigation nav = new Navigation();
	Character character = new Character("", "", "", 1000, 15,0, false, false);
	String dropdown;
	boolean isLooted;

	/*
	 * method that takes a button, then a listener is glued to the button the
	 * model's method is called within here model is the Rooms Class
	 */

	public void showInventoryListener(Button button) {
		button.setOnAction(e -> {

			item.inventoryPopUp(item.getInventory(), character, room);
		});
	}

	public void examineRoomListener(Button temp) {
		temp.setOnAction(e -> {
			room.addObserver(LostTreasureMain.gui);
			room.ExamineRoom();
			// goes through the room array and if they drop down equals a name in the
			// array it will only show the room object
			for (Rooms roomTemp : room.getRoomsArray()) {
				if (roomTemp.getRoomName().equals(dropdown)) {
					roomTemp.setExamined(true);
					room.enableButtons(LostTreasureMain.gui.searchRoom);
				}
			}
		});
	}

	/*
	 * pickupItemListener adds the listener to the pick up button in the gui
	 */
	public void pickupItemListener(Button temp) {
		temp.setOnAction(e -> {
			item.addObserver(LostTreasureMain.gui);
			String tempRoomID;
			@SuppressWarnings("unused")
			String tempitemID = "";
			for (Rooms roomTemp : room.getRoomsArray()) {
				// matches room item with drop down
				if (roomTemp.getRoomName().equals(dropdown)) {
					item.viewItems(roomTemp.getItem());
					tempRoomID = roomTemp.getItem();
					System.out.println("item in room " + tempRoomID);
					for (Items itemTemp : item.getItemsArray()) {
						if (itemTemp.getItemID().equals(tempRoomID)) {
							tempitemID = itemTemp.getItemID();

							if (roomTemp.isLooted() == false) {
								String name = itemTemp.getItemName();
								item.getInventory().add(name);
								roomTemp.setLooted(true);
							} else {

								System.out.println("Already Looted" + roomTemp.isLooted());
								item.setItemDescription("you already looted the room");
								room.disableButton(LostTreasureMain.gui.pickupItem);
							}
						}
					}
				}
			}

		});
	}

	public void viewPuzzleListener(Button temp) {
		temp.setId(puzzle.getPuzzleDescription());
		temp.setOnAction(e -> {
			puzzle.addObserver(LostTreasureMain.gui);
			// getPuzzlesArray().get(currentRoom).getPuzzleID())
			for (Puzzles p : puzzle.getPuzzlesArray()) {
				System.out.println("puzzle current room " + puzzle.getCurrentRoom());
				System.out.println("puzzle current room " + p.getCurrentRoom());
				if (p.getPuzzleID().equals("P1")) {
					puzzle.ViewPuzzle(puzzle.currentPuzzle("P1"));
					System.out.println("bla");
					puzzle.puzzleButtonClicked();
					break;
				} else if (p.getPuzzleID().equals("P2")) {
					puzzle.ViewPuzzle(puzzle.currentPuzzle("P2"));
					puzzle.puzzleButtonClicked();
				} else if (p.getPuzzleID().equals("P3")) {
					puzzle.ViewPuzzle(puzzle.currentPuzzle("P3"));
					puzzle.puzzleButtonClicked();
				} else if (p.getPuzzleID().equals("P4")) {
					puzzle.ViewPuzzle(puzzle.currentPuzzle("P4"));
					puzzle.puzzleButtonClicked();
				} else if (p.getPuzzleID().equals("P5")) {
					puzzle.ViewPuzzle(puzzle.currentPuzzle("P5"));
					puzzle.puzzleButtonClicked();
				} else if (p.getPuzzleID().equals("P6")) {
					puzzle.ViewPuzzle(puzzle.currentPuzzle("P6"));
					puzzle.puzzleButtonClicked();
				} else if (p.getPuzzleID().equals("P7")) {
					puzzle.ViewPuzzle(puzzle.currentPuzzle("P7"));
					puzzle.puzzleButtonClicked();
				} else if (p.getPuzzleID().equals("P8")) {
					puzzle.ViewPuzzle(puzzle.currentPuzzle("P8"));
					puzzle.puzzleButtonClicked();
				}
			}

			// go to puzzle pop up for the buttons listeners
			puzzle.puzzlePopUp(room);
		});
	}

	public void viewMonsterListener(Button temp) {
		temp.setId(monster.getMonsterDescription());
		temp.setOnAction(e -> {
			monster.addObserver(LostTreasureMain.gui);
			character.addObserver(LostTreasureMain.gui);

			for (Rooms roomTemp : room.getRoomsArray()) {
				if (roomTemp.getRoomName().equals(dropdown)) {
					if (roomTemp.getRoomName().equals(dropdown) && roomTemp.isSearched() == true) {
						// System.out.println("current room monster id " + roomTemp.getMonster());
						// System.out.println("sssmmm" + monster.currentMonster(roomTemp.getMonster()));

						// room.enableButtons(LostTreasureMain.gui.fightMonster);
						// room.enableButtons(LostTreasureMain.gui.fleeMonster);
						monster.setCurrentMonster(monster.currentMonster(roomTemp.getMonster()));
						System.out.println("get current monster index " + monster.getCurrentMonster());
					} else {
						System.out.println("Haven't Searched");
					}

				}

			}

			monster.ViewMonster(room.getCurrentRoom());
			monster.attackPopUp(LostTreasureMain.gui.fightMonster, LostTreasureMain.gui.fleeMonster, room, monster,
					character);

		});

	}

	public void viewAnswerListener(Button temp) {
		temp.setId(puzzle.getPuzzleAnswer());
		temp.setOnAction(e -> {
			puzzle.addObserver(LostTreasureMain.gui);
			puzzle.ViewAnswer(room.getCurrentRoom());
		});
	}

	public void attackMonsterListener(Button attack) {
		attack.setOnAction(e -> {
			System.out.println("you have attacked the monster");
			monster.addObserver(LostTreasureMain.gui);
			monster.AttackMonster(room.getCurrentRoom());

		});
	}

	public void searchRoomListener(Button temp) {
		temp.setId(item.getItemDescription());
		temp.setOnAction(e -> {
			item.addObserver(LostTreasureMain.gui);
			// System.out.println("current "+room.getCurrentRoom());

			for (Rooms roomTemp : room.getRoomsArray()) {
				// room object matches the drop down
				if (roomTemp.getRoomName().equals(dropdown)) {
					// checksfor dead monsters to disable examine monster

					if (roomTemp.getRoomName().equals(dropdown) && roomTemp.isLooted() == false) {

						if (roomTemp.getItem().equals("none")) {
							System.out.println("noneeeee");
							item.setItemDescription("you already searched the room");

							room.disableButton(LostTreasureMain.gui.pickupItem);
							room.disableButton(LostTreasureMain.gui.examine);
							room.disableButton(LostTreasureMain.gui.viewPuzzle);
							room.disableButton(LostTreasureMain.gui.examineMonster);
							room.disableButton(LostTreasureMain.gui.searchRoom);
							roomTemp.setLooted(true);

							item.setItemDescription("nothing");
						} else {
							item.viewItems(roomTemp.getItem());
							room.enableButtons(LostTreasureMain.gui.pickupItem);
							room.enableButtons(LostTreasureMain.gui.viewPuzzle);
							room.enableButtons(LostTreasureMain.gui.examineMonster);
							// roomTemp.setLooted(true);
						}
						if (roomTemp.getPuzzleID().equals("none")) {
							System.out.println("noneeeee");
							room.disableButton(LostTreasureMain.gui.viewPuzzle);
							item.setItemDescription("nothing");
						}
						// System.out.println(roomTemp.isSearched() + "sss");
						if (roomTemp.isSearched()) {
							item.setItemDescription("searched");
							room.disableButton((LostTreasureMain.gui.searchRoom));
						}
					} else {
						room.disableButton(LostTreasureMain.gui.pickupItem);
					}
					roomTemp.setSearched(true);

				}

			}
		});

	}

	// refreshes the map pane
	// re populates the drop-down with the available rooms
	@SuppressWarnings("unlikely-arg-type")
	public void refreshMap(Button temp) {

		temp.setOnAction(e -> {

			nav.addObserver(LostTreasureMain.gui);
			dropdown = GUI.gui.getRoomsDropDown().getValue();

			for (Rooms roomTemp : room.getRoomsArray()) {
				if (roomTemp.getRoomName().equals(dropdown)) {
					if (roomTemp.isLocked() == true) {
						if (item.getInventory().contains(item)) {
							room.doorUnlockedPopUp(roomTemp.getRoomName());
							// System.out.println("is the room locked? " + roomTemp.isLocked());
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
					// System.out.println("is the room locked? " + roomTemp.isLocked());
					// System.out.println("is the room examined? " + roomTemp.isExamined());
					room.enableButtons(LostTreasureMain.gui.examine);

					if (roomTemp.isExamined() == false) {
						room.disableButton(LostTreasureMain.gui.searchRoom);
					}
					if (roomTemp.isSearched() == false) {
						room.disableButton(LostTreasureMain.gui.pickupItem);
						room.disableButton(LostTreasureMain.gui.examineMonster);
						room.disableButton(LostTreasureMain.gui.viewPuzzle);

					}
				}
			}
			room.getCurrentRoom();
		});
	}

}
