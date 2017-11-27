import java.lang.Math;
public class levelUpSystem {

	public static void main(String[] args) {
		double nextLevel = 0;
		//int count = 1;
		for(int lvl = 1; lvl <= 40; lvl++) {
			
			nextLevel = nextLevel + nextLevel(lvl);
			//System.out.println("\n+" + nextLevel(count++));
			System.out.println("EXP " + nextLevel + " - lvl " + lvl);
			
		}

	}
	
	//Next Level
	static double nextLevel(int level) {
		
		return round((4 * (level ^ 3)) / 5);
	}
	
	//Round
	static double round(double n) {
		
		if (n < 0) {

			return Math.ceil(n - 0.5);
		}
	    else {
	 
	    	return Math.floor(n + 0.5);
	    }
	}
	

}
