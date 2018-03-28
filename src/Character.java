import java.util.Observable;

public class Character extends Observable{


/**
 * @author  Humberto Michael Lopez
 * @version 1.0 
 * @Course : ITEC 3860, Fall, 2017 Written: November 8, 2017
 *  
 */
	public static String charID;
	public static String charName;
	public static String charDescription;
	public int charHealth;
	public int charDamage;
	public int charXP;
	private boolean nav;
	private boolean bag;
	private boolean monsterDefeated;
	
	public Character(String charID, String charName, String charDescription, int charHealth, int charDamage, int charXP, boolean nav,boolean bag){
		
		Character.charID = charID;
		Character.charName = charName;
		Character.charDescription = charDescription;
		this.charHealth = charHealth;
		this.charDamage = charDamage;
		this.charXP = charXP;
		this.nav = nav;
		this.bag = bag;
	}
	
	public Character() {
		
	}

	public int getCharDamage() {
		return charDamage;
	}
	public boolean isDead(boolean temp) {
		setMonsterDefeated(temp);
		return temp;
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
		setChanged();
		notifyObservers(charHealth);
		return charHealth;
		
	}
	

	public int getCharXP() {
		setChanged();
		notifyObservers(charXP);
		return charXP;
	}

	public void setCharXP(int charXP) {
		this.charXP = charXP;
		setChanged();
		notifyObservers(charXP);
	}

	public boolean isNav() {
		return nav;
	}

	public void setNav(boolean nav) {
		this.nav = nav;
		setChanged();
		notifyObservers(nav);
	}

	public boolean isBag() {
		return bag;
	}

	public void setBag(boolean bag) {
		this.bag = bag;
		setChanged();
		notifyObservers(bag);
	}

	public void setCharHealth(int charHealth) {
		this.charHealth = charHealth;
		setChanged();
		notifyObservers(charHealth);
	}

	public boolean isMonsterDefeated() {
		return monsterDefeated;
	}

	public void setMonsterDefeated(boolean monsterDefeated) {
		this.monsterDefeated = monsterDefeated;
	}
	public String toString() {
		return charID + " | " + charName + " | " + charDescription + " | " + charHealth + " | " + charDamage + " | " +
				charXP + " | " + nav + " | " + bag + " |";
		
	}

}
