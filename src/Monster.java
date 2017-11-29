import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Scanner;

import javafx.geometry.Insets;
import javafx.scene.control.Alert;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class Monster extends Observable {
	
	private String monsterID;
	private String monsterName;
	private String monsterDescription;
	private String EXP;
	private int damageGiven;
	private String healthPoints;
	private String attackPercentage;
	private String artifactsDropped;
	private String fleeMonster;
	private int currentRoom = 0;
	private int currentMonster;
	private int HP;
	private int currentHP;
	private int monsterDamage;
	private String currentMonsterName;
	private static ArrayList<Monster> monstersArray = new ArrayList<>();
	Label descriptionText = new Label();
	
	public Monster() {
		try {
			monsterReader();
			
		} catch(FileNotFoundException e){
			System.out.println("No File Found");
		}
	}

	public Monster(String monsterID, int currentMonster, String monsterName, String monsterDescription,
			String EXP, int damageGiven, String healthPoints, int HP, String attackPercentage, 
			String artifactsDropped)
	{
		this.monsterID = monsterID;
		this.currentMonster = currentMonster;
		this.monsterName = monsterName;
		this.monsterDescription = monsterDescription;
		this.EXP = EXP;
		this.damageGiven = damageGiven;
		this.healthPoints = healthPoints;
		this.HP = HP;
		this.attackPercentage = attackPercentage;
		this.artifactsDropped = artifactsDropped;
	}
	
	public void setMonsterDescription(String monsterDescription) {
		this.monsterDescription = monsterDescription;
		setChanged();
		notifyObservers(monsterDescription);
	}
	
	public void setMonsterName(String monsterName) {
		this.monsterName = monsterName;
	
	}
	
	public String ViewMonster(int currentRoom) {
		System.out.println("mmmaaa "+ getCurrentMonsterName());
		System.out.println("setter " + getMonstersArray().get(currentMonster(getCurrentMonsterName())).getMonsterName());
		setMonsterDescription("You have encountered a " + getCurrentMonsterName() + "\n\n\n" + "Wanna fight it?"
							 + "\n\n" + "Wanna Flee?");
		return monsterDescription;
		
	}
	public void setAttackPercentage(String attackPercentage) {
		this.attackPercentage = attackPercentage;
		setChanged();
		notifyObservers(attackPercentage);
		
	}
	
	public void setHealthPoints(String healthPoints) {
		this.healthPoints = healthPoints;
	}

	
	public String AttackMonster(int currentRoom){
		System.out.println(currentRoom);
	//	setAttackPercentage(getMonstersArray().get(currentRoom).getArtifactsDropped());
		//getMonstersArray().get(currentMonster).setHP(THIS SHOULD BE THE DAMAGE DONE);
		monsterDamage = getMonstersArray().get(currentMonster).getDamageGiven();
		return attackPercentage;
	
	
	}

	public void attackPopUp(Button attack, Button flee, Rooms room, Monster monster) {

		Alert popUp = new Alert(AlertType.NONE);
		popUp.setTitle("Battle");
		popUp.setHeaderText("Battle");
		popUp.setResizable(true);
		popUp.setWidth(150);
		ImageView logo = new ImageView("logo.png");
		logo.setFitWidth(64);
		logo.setFitHeight(64);
		popUp.setGraphic(logo);
		attack = new Button("Attack");
		flee = new Button("Flee");

		popUp.getButtonTypes().add(ButtonType.CANCEL);
		popUp.hide();
		popUp.getButtonTypes().remove(ButtonType.CANCEL);

		attack.setOnAction(e -> {
			if (monster.getMonstersArray().get(monster.getCurrentMonster()).getHP() >= 0) {
				System.out.println("AttackedNOWWW");
				monster.setCurrentHP(monster.getMonstersArray().get(monster.getCurrentMonster()).getCurrentHP() - 10);
			} else if (monster.getMonstersArray().get(monster.getCurrentMonster()).getHP() <= 0) {
				System.out.println("This Monster Is Dead!");
			}
		});
		flee.setOnAction(e -> {
			monster.addObserver(LostTreasureMain.gui);

			monster.FleeMonster(room.getCurrentRoom());
			// quits and closes the gui
			popUp.close();

			System.out.println("flee");

		});
		// description pane for popUp
		HBox monsterDescription = new HBox(15);
		monsterDescription.setMinHeight(300);
		monsterDescription.setStyle("-fx-padding: 10;" + "-fx-border-style: solid inside;" + "-fx-border-width: 1;"
				+ "-fx-border-insets: 10;" + "-fx-border-radius: 10;" + "-fx-border-color: black;");
		monsterDescription.setMinWidth(300);
		monsterDescription.setMaxHeight(200);
		monsterDescription.setMaxWidth(200);
		monster.descriptionText = new Label();
		monster.descriptionText.setFont(Font.font("Verdana", 15));
		monster.descriptionText.setWrapText(true);
		monsterDescription.setPadding(new Insets(15, 15, 15, 15));
		// sets the text from the radio buttons to the description box
		monster.descriptionText
				.setText(monster.getMonstersArray().get(monster.getCurrentMonster()).getMonsterDescription());
		monsterDescription.getChildren().add(monster.descriptionText);

		// pane for buttons
		BorderPane hBox = new BorderPane();
		attack.setTranslateX(40);
		flee.setTranslateX(-40);
		hBox.setLeft(attack);
		hBox.setRight(flee);

		// adding the action listener from the controller class

		GridPane pane = new GridPane();
		pane.setHgap(5);
		// node,column,row
		pane.add(monsterDescription, 0, 0);
		pane.add(hBox, 0, 1);
		popUp.getDialogPane().setContent(pane);
		popUp.show();

	}

	public void setFleeMonster(String fleeMonster) {
		this.fleeMonster = fleeMonster;
		System.out.println("You have fled the monster, no experience gained");
		setChanged();
		notifyObservers(fleeMonster);
		
	}
	
	public String FleeMonster(int currentRoom){
		System.out.println(currentRoom);
		setFleeMonster("You have fled the monster, no experience gained");
		return EXP;
		
	}
	public int currentMonster(String monsterID) {
		int currentID=0;
		//System.out.println("monsters position " +getMonstersArray().get(0));
		for(Monster m : getMonstersArray()) {
			if(m.getMonsterID().equals(monsterID)) {
				currentID = getMonstersArray().indexOf(m);
				System.out.println("current monster " + m.getMonsterName());
				setCurrentMonsterName((m.getMonsterName()));

			}
		}
		System.out.println("current monster position " + currentID);

		return currentID;
	}
/*	public String currentMonsterName(String monsterID) {
		int currentID=0;
		String currentName="";
		//System.out.println("monsters position " +getMonstersArray().get(0));
		for(Monster m : getMonstersArray()) {
			if(m.getMonsterID().equals(monsterID)) {
				currentID = getMonstersArray().indexOf(m);
				System.out.println("current monster " + m.getMonsterName());
				
			currentName = m.getMonsterName();

			}
		}
		System.out.println("current monster namwww " + currentName);

		return currentName;
	}*/
	/*public void fleeMonster() {
		 System.out.println("You have fled the monster, no experience gained");
	}
	
	*/
	public void monsterReader() throws FileNotFoundException {
		@SuppressWarnings("resource")
		Scanner reader = new Scanner(new File("monster.txt"));
		;

		while(reader.hasNext()){
			String monsterID = reader.nextLine();
			String digits = monsterID.replaceAll("[^0-9.]","");
			int currentMonster = Integer.parseInt(digits);
			
			String monsterName = reader.nextLine();
			String monsterDescription= reader.nextLine();
			String EXP = reader.nextLine();
			
			String attackDamage = reader.nextLine();
			String digitsD = attackDamage.replaceAll("[^0-9.]","");
			int damageGiven = Integer.parseInt(digitsD);
			
			String healthPoints = reader.nextLine();
			String hpDigit = healthPoints.replaceAll("[^0-9.]","");
			int HP = Integer.parseInt(hpDigit);
			
			String attackPercentage = reader.nextLine();
			String artifactsDropped = reader.nextLine();

			Monster monster = new Monster( monsterID, currentMonster, monsterName, monsterDescription, EXP, 
					damageGiven, healthPoints, HP, attackPercentage, artifactsDropped);
			monstersArray.add(monster);
		}
	}


	public String toString() {
		return monsterID + " | " + monsterDescription + " | " + EXP + " | " + damageGiven + " | " + healthPoints + "|" +
				attackPercentage + "|" + artifactsDropped + "|";
	}

	public String getMonsterID() {
		return monsterID;
	}

	public void setMonsterID(String monsterID) {
		this.monsterID = monsterID;
	}

	public String getMonsterName() {
		return monsterName;
	}


	public String getMonsterDescription() {
		return monsterDescription;
	}

	public String getEXP() {
		return EXP;
	}

	public void setEXP(String eXP) {
		EXP = eXP;
	}

	public int getDamageGiven() {
		return damageGiven;
	}

	public void setDamageGiven(int damageGiven) {
		this.damageGiven = damageGiven;
	}

	public String getHealthPoints() {
		return healthPoints;
	}

	public String getAttackPercentage() {
		return attackPercentage;
	}


	public String getArtifactsDropped() {
		return artifactsDropped;
	}

	public void setArtifactsDropped(String artifactsDropped) {
		this.artifactsDropped = artifactsDropped;
	}
	
	
	public int getCurrentRoom() {
		return currentRoom ;
		
	}
	public void setCurrentRoom(int currentRoom) {
		this.currentRoom = currentRoom;
		
	}
	public String getFleeMonster() {
		return fleeMonster;
	}

	public int getCurrentMonster() {
		return currentMonster;
	}
	
	public void setCurrentMonster(int currentMonster) {
		this.currentMonster = currentMonster;
		
	}
	
	public ArrayList<Monster> getMonstersArray() {
		return monstersArray;
	}

	public String getCurrentMonsterName() {
		return currentMonsterName;
	}

	public void setCurrentMonsterName(String currentMonsterName) {
		this.currentMonsterName = currentMonsterName;
	}

	public int getHP() {
		return HP;
	}

	public void setHP(int hP) {
		HP = hP;
	}

	public int getCurrentHP() {
		return currentHP;
	}

	public void setCurrentHP(int currentHP) {
		this.currentHP = currentHP;
	}
	
	
}
