package wordle;

public class GameConfiguration {
  
  //Length of the word used for the secret word and guesses.
  public final int wordLength;

  //The number of guesses available to the user.
  public final int numGuesses;

  //Testing mode or not
  public final boolean testMode;

  public GameConfiguration(int wordLength, int numGuesses, boolean testMode) {
    this.wordLength = wordLength;
    this.numGuesses = numGuesses;
    this.testMode = testMode;
  }  
}


