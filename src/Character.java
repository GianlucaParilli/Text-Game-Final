import java.util.Observable;

public class Character extends Observable{

	public String charID;
	public String charName;
	public String charDescription;
	public String charHealth;
	
	public Character(String charID, String charName, String charDescription, String charHealth){
		
		this.charID = charID;
		this.charName = charName;
		this.charDescription = charDescription;
		this.charHealth = charHealth;
				
	}

	public String getCharID() {
		return charID;
	}

	public void setCharID(String charID) {
		this.charID = charID;
	}

	public String getCharName() {
		return charName;
	}

	public void setCharName(String charName) {
		this.charName = charName;
	}

	public String getCharDescription() {
		return charDescription;
	}

	public void setCharDescription(String charDescription) {
		this.charDescription = charDescription;
	}

	public String getCharHealth() {
		return charHealth;
	}

	public void setCharHealth(String charHealth) {
		this.charHealth = charHealth;
	}

}
