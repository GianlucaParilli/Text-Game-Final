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

public class Weapons extends Items{

	private int attackPoints;
	private static ArrayList<Weapons> weaponsArray = new ArrayList<>();
	

	public int getAttackPoints() {
		return attackPoints;
	}

	public void setAttackPoints(int attackPoints) {
		this.attackPoints = attackPoints;
	}
	
	public void equipItem() { //Placeholder
		
	}
	
	public void enequipItem() { //Placeholder
		
	}
	
	public Weapons(String itemID, String itemName, String itemDescription, int attackPoints) {
		setItemID(itemID);
		setItemName(itemName);
		setItemDescription(itemDescription);
		setAttackPoints(attackPoints);
	}
	
	
	
	public static void weaponsReader() throws FileNotFoundException {
		@SuppressWarnings("resource")
		Scanner reader = new Scanner(new File("weapons.txt"));
		;

		while (reader.hasNext()) {
			String itemID = reader.nextLine();
			String itemName = reader.nextLine();
			String itemDescription = reader.nextLine();
			String attackPoints = reader.nextLine();
			Integer result = Integer.valueOf(attackPoints);
			
			Weapons weapons = new Weapons(itemID, itemName, itemDescription, result);
			weaponsArray.add(weapons);
		}
		
	}
}
