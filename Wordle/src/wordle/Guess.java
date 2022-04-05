package wordle;
import java.util.HashMap;

public class Guess {
	private String word;
	private String fb;
	public Guess(String word) {
		this.word = word;
		fb = "";
	}
	
	public String getWord() {
		return word;
	}
	
	public String getFeedback() {
		return fb;
	}
	
	public String generateFeedback(String secretWord) {
		String feedback = "";
		HashMap<Character, Integer> letters = new HashMap<Character,Integer>();
		for(int i = 97; i<123; i++) {
			letters.put((char)i, 0);
		}
		for(int i = 0; i<secretWord.length(); i++) {
			feedback += "_";
			letters.put(secretWord.charAt(i), letters.get(secretWord.charAt(i)) + 1);
		}
		for(int i = 0; i<secretWord.length(); i++) {
			if(word.charAt(i) == secretWord.charAt(i)) {
				feedback = feedback.substring(0,i) + "G" + feedback.substring(i+1);
				letters.put(word.charAt(i),letters.get(word.charAt(i))-1);
			}
		}
		for(int i = 0; i<secretWord.length(); i++) {
			if(letters.get(word.charAt(i))>0 && feedback.charAt(i) != 'G') {
				feedback = feedback.substring(0,i) + "Y" + feedback.substring(i+1);
				letters.put(word.charAt(i),letters.get(word.charAt(i))-1);
			}
		}
		fb = feedback;
		return feedback;
	}
}
