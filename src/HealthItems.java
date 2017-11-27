import java.util.ArrayList;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * @author Humberto Michael Lopez
 * @version 1.0 
 * @Course : ITEC 3860, Fall, 2017 Written: November 8, 2017
 *  
 */

public class HealthItems extends Items{
	
	private int healthPointsAdded;
	private static ArrayList<HealthItems> healthItemsArray = new ArrayList<>();
	
	public int getHealthPointsAdded() {
		return healthPointsAdded;
	}
	public void setHealthPointsAdded(int healthPointsAdded) {
		this.healthPointsAdded = healthPointsAdded;
	}
	
	public void useItem() { //placeholder
		
	}
	
	public HealthItems(String itemID, String itemName, String itemDescription, int healthPointsAdded) {
		setItemID(itemID);
		setItemName(itemName);
		setItemDescription(itemDescription);
		setHealthPointsAdded(healthPointsAdded);
	}
	
	public static void healthItemsReader() throws FileNotFoundException {
		@SuppressWarnings("resource")
		Scanner reader = new Scanner(new File("healthItems.txt"));
		;

		while (reader.hasNext()) {
			String itemID = reader.nextLine();
			String itemName = reader.nextLine();
			String itemDescription = reader.nextLine();
			String healthPointsAdded = reader.nextLine();
			Integer result = Integer.valueOf(healthPointsAdded);
			
			HealthItems healthItems = new HealthItems(itemID, itemName, itemDescription, result);
			healthItemsArray.add(healthItems);
		}
		
	}
}
