import java.util.Observable;

/**
 * @author Gianluca Parilli
 * @version 1.0
 * @Course : ITEC 3860, Fall, 2017 Written: October 15, 2017
 * 
 */
public class Navigation extends Observable {
	private int currentRoom;

	public void refreshMap(int dropdownString) {

		// System.out.println("refresh map" + dropdownString);
		setCurrentRoom(dropdownString);

	}

	public void setCurrentRoom(int current) {
		this.currentRoom = current;
		setChanged();
		notifyObservers(currentRoom);
		// System.out.println("setCurrentRoom" + currentRoom);
	}
}
