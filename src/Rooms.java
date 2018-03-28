import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Random;
import java.util.Scanner;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;

/**
 * @author Gianluca Parilli
 * @version 1.0
 * @Course : ITEC 3860, Fall, 2017 Written: November 2, 2017
 * 
 */

public class Rooms extends Observable {

	private String roomID;
	private String roomName;
	private String roomDescription;
	private String availableRoom;
	private String item;
	private String monster;
	private String puzzleID;
	private String randomProbability;
	private boolean isLocked;
	private boolean isExamined;
	private boolean isSearched;
	private boolean isLooted;
	private int numRoomID;
	private int currentRoom;
	private ArrayList<Rooms> roomsArray = new ArrayList<>();
	ArrayList<String> roomIDArray = new ArrayList<>();
	ArrayList<String> roomNameArray = new ArrayList<>();

	public ArrayList<String> getRoomIDArray() {
		return roomIDArray;
	}

	public Rooms() {
		try {
			roomReader();
			randomPuzzle();

		} catch (FileNotFoundException e) {
			System.out.println("No File Found");
		}
	}

	public Rooms(String roomID, int numRoomID, String roomName, String roomDescription, String availableRoom,
			String monster, String puzzleID, String item, String randomProbability, boolean isLocked,
			boolean isExamined, boolean isSearched, boolean isLooted) {
		this.roomID = roomID;
		this.numRoomID = numRoomID;
		this.roomName = roomName;
		this.roomDescription = roomDescription;
		this.availableRoom = availableRoom;
		this.monster = monster;
		this.puzzleID = puzzleID;
		this.item = item;
		this.randomProbability = randomProbability;
		this.isLocked = isLocked;
		this.isExamined = isExamined;
	}

	public void setRoomDescription(String roomDescriptionn) {
		this.roomDescription = roomDescriptionn;
		setChanged();
		notifyObservers(roomDescription);

	}

	public String ExamineRoom() {
		// randomPuzzle();
		setRoomDescription(getRoomsArray().get(getCurrentRoom()).getRoomDescription());
		// System.out.println(roomDescription);

		return roomDescription;
	}

	// used to make sure the update method in the gui is updating the mvc properly
	public boolean hasExaminedRoom(boolean bool) {

		return bool;
	}

	/*
	 * method that finds if there is a item in the room and sets it to the
	 * description pane in the gui
	 */
	public String SearchRoom() {

		setRoomDescription(getRoomsArray().get(getCurrentRoom()).getItem());
		// System.out.println(item);

		return item;
	}

	/*
	 * method that randomizes the possibility that there might be a puzzle in the
	 * room
	 */
	public void randomPuzzle() {
		Random rand = new Random();

		for (Rooms temp : roomsArray) {
			int randomProb = Integer.parseInt(temp.randomProbability);
			// finds room with prob higher than 0, if greater than 0, randomize puzzle.
			if (randomProb > 0) {
				// System.out.println(rand.nextInt(randomProb) + 1);
				temp.setMonster("" + rand.nextInt(randomProb) + 1);
			}
		}
	}

	/*
	 * method that enables a given button
	 */
	public void enableButtons(Button disabledButton) {
		disabledButton.setDisable(false);
	}

	/*
	 * method that disables a given button
	 */
	public void disableButton(Button enableButton) {
		enableButton.setDisable(true);
	}

	/*
	 * method that reads the rooms.txt file and creates room objects with their
	 * respective attributes
	 */
	public void roomReader() throws FileNotFoundException {
		@SuppressWarnings("resource")
		Scanner reader = new Scanner(new File("rooms.txt"));

		while (reader.hasNext()) {
			String roomID = reader.nextLine();
			String digits = roomID.replaceAll("[^0-9.]", "");
			int roomNumID = Integer.parseInt(digits);
			String roomName = reader.nextLine();
			String roomDescription = reader.nextLine();
			String puzzle = reader.nextLine();
			String item = reader.nextLine();
			String monster = reader.nextLine();
			String puzzleID = reader.nextLine();
			String randomProbability = reader.nextLine();
			String lockedString = reader.nextLine();
			boolean isLocked = Boolean.parseBoolean(lockedString);
			boolean isExamined = false;
			boolean isSearched = false;
			boolean isLooted = false;
			Rooms room = new Rooms(roomID, roomNumID, roomName, roomDescription, puzzle, item, monster, puzzleID,
					randomProbability, isLocked, isExamined, isSearched, isLooted);
			roomsArray.add(room);
		}
	}

	/*
	 * reads the available room attribute of each room and clears the drop down and
	 * re populates it with the available rooms
	 */
	public void availableRoom(int room) {
		roomNameArray.clear();
		roomIDArray.clear();
		for (Rooms tempAvailable : roomsArray) {
			// roomNameArray.clear();
			int tempID = tempAvailable.getNumRoomID();
			if (tempID == room) {
				String tempString = tempAvailable.getExit();
				// roomIDArray.clear();
				setCurrentRoom(tempAvailable.numRoomID);
				String[] splitAr = tempString.split(",\\s+");
				for (int i = 0; i < splitAr.length; i++) {
					roomIDArray.add(splitAr[i]);

				}
				// clears the drop down
				Login.gui.getRoomsDropDown().getItems().clear();

				for (String temp : roomIDArray) {
					int replacement = Integer.parseInt(temp.replaceAll("[^0-9.]", ""));
					roomNameArray.add(roomsArray.get(replacement).roomName);
				}
				// re populates the drop down with new rooms
				Login.gui.getRoomsDropDown().getItems().addAll(roomNameArray);
				Login.gui.getRoomsDropDown().setPromptText(tempAvailable.getRoomName());
			}
		}
	}

	/*
	 * method that creates an error alert to tell user that the door is locked and a
	 * key is needed
	 */
	public void lockedPopUp(String lockedRoom) {
		Alert errorPopUp = new Alert(AlertType.ERROR);
		errorPopUp.setHeaderText("The Room " + lockedRoom + " is locked \n Please Equip key first");
		errorPopUp.show();
	}

	/*
	 * method that creates an information alert to tell user that the door is
	 * unlocked
	 */
	public void doorUnlockedPopUp(String lockedRoom) {
		Alert errorPopUp = new Alert(AlertType.INFORMATION);
		errorPopUp.setHeaderText("The Room " + lockedRoom + " is now unlocked!");
		errorPopUp.show();
	}

	@Override
	public String toString() {
		return roomID + " | " + roomName + " | " + roomDescription + " | " + availableRoom + " | " + monster + " | "
				+ item + " | " + randomProbability + " | " + isLocked;
	}

	/*
	 * bunch of setters and getters
	 */
	public String getExit() {
		return availableRoom;
	}

	public void setExit(String availableRoom) {
		this.availableRoom = availableRoom;
	}

	public String getItem() {
		return item;
	}

	public String getMonster() {
		return monster;
	}

	public void setMonster(String monster) {

		this.monster = monster;
	}

	public String getRoomID() {
		return roomID;
	}

	public void setRoomID(String roomID) {
		this.roomID = roomID;
	}

	public ArrayList<Rooms> getRoomsArray() {
		return roomsArray;
	}

	public String getRoomName() {
		return roomName;
	}

	public void setRoomName(String roomName) {
		this.roomName = roomName;
	}

	public String getRoomDescription() {

		return roomDescription;
	}

	public int getCurrentRoom() {
		return currentRoom;
	}

	public void setCurrentRoom(int currentRoom) {
		this.currentRoom = currentRoom;
	}

	public boolean isLocked() {
		return isLocked;
	}

	public void setLocked(boolean isLocked) {
		this.isLocked = isLocked;
	}

	public boolean isExamined() {
		return isExamined;
	}

	public void setExamined(boolean isExamined) {
		this.isExamined = isExamined;
	}

	public boolean isSearched() {
		return isSearched;
	}

	public void setSearched(boolean isSearched) {
		this.isSearched = isSearched;
	}

	public boolean isLooted() {
		return isLooted;
	}

	public void setLooted(boolean isLooted) {
		this.isLooted = isLooted;
	}

	public String getPuzzleID() {
		return puzzleID;
	}

	public void setPuzzleID(String puzzleID) {
		this.puzzleID = puzzleID;
	}

	public int getNumRoomID() {
		return numRoomID;
	}

	public void setNumRoomID(int numRoomID) {
		this.numRoomID = numRoomID;
	}

}
