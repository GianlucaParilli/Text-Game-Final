import java.util.Observable;

public class Character extends Observable{

	public static String charID;
	public static String charName;
	public static String charDescription;
	public int charHealth;
	public int charDamage;
	
	public Character(String charID, String charName, String charDescription, int charHealth, int charDamage){
		
		Character.charID = charID;
		Character.charName = charName;
		Character.charDescription = charDescription;
		this.charHealth = charHealth;
		this.charDamage = charDamage;
	}
	
	public Character() {
		
	}
	
	static Character player = new Character(charID, charName, charDescription, 1000, 15) ;

	public int getCharDamage() {
		return charDamage;
	}

	public void setCharDamage(int charDamage) {
		this.charDamage = charDamage;
	}

	public String getCharID() {
		return charID;
	}

	public void setCharID(String charID) {
		Character.charID = charID;
	}

	public String getCharName() {
		return charName;
	}

	public void setCharName(String charName) {
		Character.charName = charName;
	}

	public String getCharDescription() {
		return charDescription;
	}

	public void setCharDescription(String charDescription) {
		Character.charDescription = charDescription;
	}

	public int getCharHealth() {
		return charHealth;
	}

	public void setCharHealth(int charHealth) {
		this.charHealth = charHealth;
	}
	

}
