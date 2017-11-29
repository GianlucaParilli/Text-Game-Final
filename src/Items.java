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
 * @author Humberto Michael Lopez
 * @version 1.0 
 * @Course : ITEC 3860, Fall, 2017 Written: November 8, 2017
 *  
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


	public Items( String itemID, String itemName, String itemDescription, String itemType, String itemUsage, int itemStrength,  int availability){
		this.itemID = itemID;
		this.itemName = itemName;
		this.itemDescription = itemDescription;
		this.itemType = itemType;
		this.itemUsage = itemUsage;
		this.itemStrength = itemStrength;
		this.availability = availability;
	}
	
	public Items() {
		try {
			itemsReader();
			keys(itemsArray);
		} catch (FileNotFoundException e) {
			System.out.println("No File Found");
		}
	}
	
	public void inventoryPopUp(ArrayList<String> inventoryArray) {
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

		for (String temp : inventoryArray) {
			cb = new RadioButton(temp);
			cb.setFont(Font.font("Verdana", 16));

			cb.setToggleGroup(toggleGroup);
			pop.getChildren().add(cb);
			if (temp.equals(cb.getText())) {
				cb.setOnAction(e -> {
					for (Items itemTemp : getItemsArray()) {
						if (itemTemp.getItemID() == "W1") {
							int currentDamage = Character.player.getCharDamage();
							int itemBonus = 10;
							System.out.println("HEYYY");
							Character.player.setCharDamage(currentDamage + itemBonus);
						}else if(itemTemp.getItemID() == "W2") {
							int currentDamage = Character.player.getCharDamage();
							int itemBonus = 10;
							System.out.println("HEYYY");
							Character.player.setCharDamage(currentDamage + itemBonus);
						}else if(itemTemp.getItemID() == "W3") {
							int currentHealth = Character.player.getCharHealth();
							int itemBonus = 50;
							System.out.println("HEYYY");
							Character.player.setCharHealth(currentHealth + itemBonus);
						}else if(itemTemp.getItemID() == "W4") {
							int currentDamage = Character.player.getCharDamage();
							int itemBonus = 50;
							System.out.println("HEYYY");
							Character.player.setCharDamage(currentDamage + itemBonus);
						}else if(itemTemp.getItemID() == "W6") {
							int currentHealth = Character.player.getCharHealth();
							int itemBonus = 50;
							System.out.println("HEYYY");
							Character.player.setCharHealth(currentHealth + itemBonus);
						}else if(itemTemp.getItemID() == "H1") {
							int currentHealth = Character.player.getCharHealth();
							int itemBonus = 10;
							System.out.println("HEYYY");
							Character.player.setCharHealth(currentHealth + itemBonus);
						}else if(itemTemp.getItemID() == "H2") {
							int currentHealth = Character.player.getCharHealth();
							int itemBonus = 100;
							System.out.println("HEYYY");
							Character.player.setCharHealth(currentHealth + itemBonus);
						}else if(itemTemp.getItemID() == "H3") {
							int currentHealth = Character.player.getCharHealth();
							int itemBonus = 20;
							System.out.println("HEYYY");
							Character.player.setCharHealth(currentHealth + itemBonus);
						}else if(itemTemp.getItemID() == "H4") {
							int currentHealth = Character.player.getCharHealth();
							int itemBonus = 10;
							System.out.println("HEYYY");
							Character.player.setCharHealth(currentHealth + itemBonus);
						}else if(itemTemp.getItemID() == "H5") {
							int currentHealth = Character.player.getCharHealth();
							int itemBonus = 5;
							System.out.println("HEYYY");
							Character.player.setCharHealth(currentHealth + itemBonus);
						}
					}
				});
			}
		}
		popUp.getDialogPane().setContent(pop);
		popUp.show();
	}
	
	public boolean unlockDoor() {
		return false;
	}
	public void keys(ArrayList<Items> keys) {
		for(Items keyTemp : keys) {
			if(keyTemp.itemType.equals("Key")) {
				getKeys().add(keyTemp.itemName);
				getKeysID().add(keyTemp.itemID);
				//System.out.println(""+keyTemp.getItemName());
				//System.out.println(""+keyTemp.getItemID());

			}
		}
	}
	public void setItemDescription(String itemDescription) {
		this.itemDescription = itemDescription;
		setChanged();
		notifyObservers(itemDescription);
	}
	
	public void setItemName(String itemName) {
		this.itemName = itemName;
		setChanged();
		notifyObservers(itemName);
	}


	public void viewItems(String itemID){
		//System.out.println(getItemsArray().get(currentRoom).getItemName());
		
		setItemDescription(getItemsArray().get(currentItem(itemID)).getItemName());
		//return itemDescription;
	}

	public int currentItem(String itemID) {
		int currentID=0;
		System.out.println(getItemsArray().get(1));
		for(Items temp : getItemsArray()) {
			if(temp.getItemID().equals(itemID)) {
				currentID = getItemsArray().indexOf(temp);
				System.out.println("current item position " + currentID);
			}
		}
		return currentID;
	}
	public void itemsReader() throws FileNotFoundException {
		@SuppressWarnings("resource")
		Scanner reader = new Scanner(new File("artifact.txt"));
		

		while(reader.hasNext()){
			String itemID = reader.nextLine();
			String itemName = reader.nextLine();
			String itemDescription = reader.nextLine();
			String itemType = reader.nextLine();
			String itemUsage = reader.nextLine();
			
			String itmStrStr = reader.nextLine();
			String digits = itmStrStr.replaceAll("[^0-9.]","");
			int itemStrength = Integer.parseInt(digits);
			
			int availability = 1;
			Items items = new Items(itemID, itemName, itemDescription, itemType, itemUsage, itemStrength, availability);
			itemsArray.add(items);
		}
	}
	public boolean hasSearchedRoom(boolean bool){

		return bool;
	}
	public ArrayList<Items> getItemsArray() {
		return itemsArray;
	}

	public String getItemID() {
		return itemID;
	}
	
	public  ArrayList<String> getInventory() {
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
	public void equipItem() {
		
	}
	
	public void unequipItem() {
		
	}
	
	public void useItem() {
		
	}

	@Override
	public String toString() {
		return itemDescription;
		
	}
}