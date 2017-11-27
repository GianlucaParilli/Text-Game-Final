import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;


public class Commands  {
	//private String username;
	
	public void writer(ArrayList<String> array) {
		PrintWriter writer = null;
		try {
			writer = new PrintWriter(new File("usernameSave.txt"));
		} catch (FileNotFoundException e) {
			System.out.println("File not found");
		}
		for (String temp : array) {
			writer.println(temp);
		}
		writer.close();
	}

	public void newGame() {

		System.out.println("gggg");

	}

	public void loadGame() {

	
	}

	public void exitGame() {

	}

	public void examineRoom(String temp){
		System.out.println("examine room");
		
		System.out.println(""+ temp);
		

		
	}


	
}
