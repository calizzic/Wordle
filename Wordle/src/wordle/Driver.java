//Name: Christopher Calizzi
//EID: csc3322
//Section: 17360
package wordle;

import java.util.Scanner;

public class Driver {
	
	public static void main(String[] args) {
        // Use this for your testing.  We will not be calling this method.
		GameConfiguration config = new GameConfiguration(6, 6, true);
		Driver d = new Driver();
		d.start(config);
	}
	
	public void start(GameConfiguration config) {
        // TODO: complete this method
		// We will call this method from our JUnit test cases.
		Scanner s = new Scanner(System.in);
		//start new game
		System.out.println("Hello! Welcome to Wordle.");
		String choice = "";
		while(!choice.equals("n")){
			System.out.println("Do you want to play a new game? (y/n)");
			choice = s.next();
			if(choice.equals("y")) {
				Game game = new Game(config, s);
				game.runGame();
			}
		}
    }
}
