import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Scanner;
import javafx.geometry.Insets;
import javafx.scene.control.Alert;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.ImageView;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;

/**
 * @author Gianluca Parilli, Humberto Michael Lopez
 * @version 1.0
 * @Course : ITEC 3860, Fall, 2017 Written: November 8, 2017
 * 
 */

/*
 * item class, reads from a text file and creates item objects according to the
 * text file generates the popup for item inventory and assigns listeners to
 * those buttons
 */
public class Items extends Observable {

	private String itemID;
	private String itemName;
	private String itemDescription;
	private String itemType;
	private String itemUsage;
	private int itemStrength;
	private int availability;
	private ArrayList<Items> itemsArray = new ArrayList<>();
	private ArrayList<String> inventory = new ArrayList<>();
	private ArrayList<String> keys = new ArrayList<>();
	private ArrayList<String> keysID = new ArrayList<>();

	/*
	 * Constructor that creates a item object with its specific attributes depending
	 * on what it is in the text file
	 */
	public Items(String itemID, String itemName, String itemDescription, String itemType, String itemUsage,
			int itemStrength, int availability) {
		this.itemID = itemID;
		this.itemName = itemName;
		this.itemDescription = itemDescription;
		this.itemType = itemType;
		this.itemUsage = itemUsage;
		this.itemStrength = itemStrength;
		this.availability = availability;
	}

	/*
	 * empty constructor used to call the class object and it creates the objects
	 * using the file i/o reader
	 */
	public Items() {
		try {
			// calls the reader for teh artifact.txt file
			// sets all the lines in the text file into an object item.
			itemsReader();
		} catch (FileNotFoundException e) {
			System.out.println("No File Found");
		}
	}

	/*
	 * method that creates a alert pop up with all the panes required to format the
	 * look of the pop up takes and inventory, character and room object in order to
	 * call it from teh controller class which is the only class that has those
	 * object initialized
	 */
	public void inventoryPopUp(ArrayList<String> inventoryArray, Character character, Rooms room) {
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
		ToggleGroup toggleGroup = new ToggleGroup();
		// it reads what is in the inventory array and creates the appropriate radio
		// button for the item
		for (String temp : inventoryArray) {
			cb = new RadioButton(temp);
			cb.setFont(Font.font("Verdana", 16));
			cb.setToggleGroup(toggleGroup);
			// adds the radio button into the pane to display it
			pop.getChildren().add(cb);
			// matches the radionButton to the actual inventory item in the inventory array
			if (temp.equals(cb.getText())) {
				cb.setOnAction(e -> {
					System.out.println("k " + temp);
					//
					for (Items itemTemp : getItemsArray()) {
						if (itemTemp.getItemName().equals(temp)) {
							System.out.println("" + temp);
							// if there object has a type of weapon it will set the damage according to the
							// weapon logic
							if (itemTemp.getItemType().equals("Weapon")) {
								int itemBonus = itemTemp.getItemStrength() + character.getCharDamage();
								character.setCharDamage(itemBonus);
								// .out.println(itemBonus + "w" );
							}
							if (itemTemp.getItemType().equals("Health Item")) {
								int itemBonus = itemTemp.getItemStrength() + character.getCharHealth();
								character.setCharHealth(itemBonus);
								System.out.println(itemBonus + "health");
							}
							if (itemTemp.getItemType().equals("Navigation")) {
								character.setNav(true);
							}
							if (itemTemp.getItemType().equals("Storage")) {
								character.setBag(true);
							}
							// if the item object is type key and the ID matches then it unlocks the right
							// room
							if (itemTemp.getItemType().equals("Key")) {
								if (itemTemp.getItemID().equals("K1")) {
									room.getRoomsArray().get(9).setLocked(false);
								}
								if (itemTemp.getItemID().equals("K2")) {
									room.getRoomsArray().get(9).setLocked(false);
								}
								if (itemTemp.getItemID().equals("K3")) {
									room.getRoomsArray().get(9).setLocked(false);
								}
								if (itemTemp.getItemID().equals("K4")) {
									room.getRoomsArray().get(9).setLocked(false);
								}
								if (itemTemp.getItemID().equals("K5")) {
									room.getRoomsArray().get(9).setLocked(false);
								}
								if (itemTemp.getItemID().equals("K6")) {
									room.getRoomsArray().get(9).setLocked(false);
								}
								if (itemTemp.getItemID().equals("K7")) {
									room.getRoomsArray().get(9).setLocked(false);
								}
							}
						}
					}
				});
			}
		}
		popUp.getDialogPane().setContent(pop);
		popUp.show();
	}

	/*
	 * method that sets the description of the item and notifies the observer in
	 * which then updates the gui
	 */
	public void setItemDescription(String itemDescription) {
		this.itemDescription = itemDescription;
		setChanged();
		notifyObservers(itemDescription);
	}

	/*
	 * method that sets the name of the item and notifies the observer in which then
	 * updates the gui
	 */
	public void setItemName(String itemName) {
		this.itemName = itemName;
		setChanged();
		notifyObservers(itemName);
	}

	/*
	 * method that takes a string with the item id and calls the setter which then
	 * updates the text in the gui, using the MVC
	 */
	public void viewItems(String itemID) {
		setItemDescription(getItemsArray().get(currentItem(itemID)).getItemName());
		// return itemDescription;
	}

	/*
	 * method that take the item id and finds the current index of the specific item
	 */
	public int currentItem(String itemID) {
		int currentID = 0;
		System.out.println(getItemsArray().get(1));
		for (Items temp : getItemsArray()) {
			if (temp.getItemID().equals(itemID)) {
				currentID = getItemsArray().indexOf(temp);
				System.out.println("current item position " + currentID);
			}
		}
		return currentID;
	}

	/*
	 * method that reads the artifact.txt file and creates item objects with their
	 * respective attributes
	 */
	public void itemsReader() throws FileNotFoundException {
		@SuppressWarnings("resource")
		Scanner reader = new Scanner(new File("artifact.txt"));
		while (reader.hasNext()) {
			String itemID = reader.nextLine();
			String itemName = reader.nextLine();
			String itemDescription = reader.nextLine();
			String itemType = reader.nextLine();
			String itemUsage = reader.nextLine();
			String itmStrStr = reader.nextLine();
			String digits = itmStrStr.replaceAll("[^0-9.]", "");
			int itemStrength = Integer.parseInt(digits);
			int availability = 1;
			Items items = new Items(itemID, itemName, itemDescription, itemType, itemUsage, itemStrength, availability);
			itemsArray.add(items);
		}
	}

	/*
	 * method used to indicate that an item has been searched, if called, disables
	 * the button for the used to not search the room again
	 */
	public boolean hasSearchedRoom(boolean bool) {
		return bool;
	}

	public ArrayList<Items> getItemsArray() {
		return itemsArray;
	}

	public String getItemID() {
		return itemID;
	}

	public ArrayList<String> getInventory() {
		return inventory;
	}

	public ArrayList<String> getKeys() {
		return keys;
	}

	public void setKeys(ArrayList<String> keys) {
		this.keys = keys;
	}

	public void setInventory(ArrayList<String> inventory) {
		this.inventory = inventory;
	}

	public void setItemID(String itemID) {
		this.itemID = itemID;
	}

	public String getItemDescription() {
		return itemDescription;
	}

	public String getItemType() {
		return itemType;
	}

	public void setItemType(String itemType) {
		this.itemType = itemType;
	}

	public String getItemUsage() {
		return itemUsage;
	}

	public void setItemUsage(String itemUsage) {
		this.itemUsage = itemUsage;
	}

	public int getItemStrength() {
		return itemStrength;
	}

	public void setItemStrength(int itemStrength) {
		this.itemStrength = itemStrength;
	}

	public String getItemName() {
		return itemName;
	}

	public ArrayList<String> getKeysID() {
		return keysID;
	}

	public int getAvailability() {
		return availability;
	}

	public void setAvailability(int availability) {
		this.availability = availability;
	}

	@Override
	public String toString() {
		return itemDescription;

	}
}