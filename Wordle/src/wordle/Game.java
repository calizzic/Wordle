package wordle;

import java.util.Scanner;

public class Game{
	private GameConfiguration config;
	private Scanner s;
	private GuessLog log;
	private Dictionary words;
	private String secretWord;
	
	public Game(GameConfiguration configuration, Scanner scan) {
		config = configuration;
		s = scan;
	}
	
	public void runGame() {
		//initialize secretword
		//r-if-at
		String filename = config.wordLength + "_letter_words.txt";
		words = new Dictionary(filename);
		secretWord = words.getRandomWord();
		if(config.testMode) {
			System.out.println(secretWord);
		}
		log = new GuessLog(config.numGuesses);
		boolean won = false;
		String winFeedback = "";
		for(int i = 0; i<config.wordLength; i++) {
			winFeedback += "G";
		}
		
		while(log.remainingGuesses()>0 && !won) {
			String guess = getGuess();
			Guess g = new Guess(guess);
			String feedback = g.generateFeedback(secretWord);
			log.addGuess(g);
			System.out.println(feedback);
			if(feedback.equals(winFeedback)) {
				won = true;
			} else if(log.remainingGuesses()>0) {
				System.out.println("You have " + log.remainingGuesses() + " guess(es) remaining.");
			}
		}
		if (won){
			System.out.println("Congratulations! You have guessed the word correctly.");
		}else {
			System.out.println("You have run out of guesses.\nThe correct word was \"" + secretWord + "\".");
		}
	}
	
	public String getGuess() {
		boolean valid = false;
		String guess = "";
		while(!valid) {
			System.out.println("Enter your guess:");
			guess = s.next();
			guess = guess.toLowerCase();
			valid = guess.length() == config.wordLength && words.containsWord(guess);
			if(guess.equals("[history]")) {
				log.printHistory(secretWord);
			}else if(guess.length() != config.wordLength) {
				System.out.println("This word has an incorrect length. Please try again.");
			}else if(!(words.containsWord(guess))) {
				System.out.println("This word is not in the dictionary. Please try again.");
				//valid = true;//COMMENT OUT
			}else {
				valid = true;
			}
		}
		return guess;
	}
}
