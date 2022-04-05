package wordle;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

public class GuessLog {
	private ArrayList<Guess> log;
	private int numGuesses;
	
	public GuessLog(int guesses) {
		 log = new ArrayList<Guess>();
		 numGuesses = guesses;
	}
	
	public int remainingGuesses() {
		return numGuesses-log.size();
	}
	
	public void addGuess(Guess g) {
		log.add(g);
	}
	
	public void printHistory(String secretWord) {
		
		HashMap<Character, Integer> green = new HashMap<Character,Integer>();
		HashMap<Character, Integer> yellowGreen = new HashMap<Character, Integer>();
		HashMap<Character, Integer> maxGreen = new HashMap<Character, Integer>();
		HashMap<Character, Integer> maxYellowGreen = new HashMap<Character, Integer>();
		for(char c: secretWord.toCharArray()) {
			maxGreen.put(c, 0);
			maxYellowGreen.put(c, 0);
		}
		HashSet<Character> absent = new HashSet<Character>();
		HashSet<Character> unused = new HashSet<Character>();
		String alpha = "abcdefghijklmnopqrstuvwxyz";
		for(char c: alpha.toCharArray()) {
			unused.add(c);
		}
		for(Guess g: log) {
			for(char c: secretWord.toCharArray()) {
				green.put(c, 0);
				yellowGreen.put(c, 0);
			}
			String word = g.getWord();
			String feedback = g.getFeedback();
			System.out.println(word + "->" + feedback);
			for(int i = 0; i < word.length();i++) {
				if(feedback.charAt(i) == '_') {
					if(unused.contains(word.charAt(i))) {
						unused.remove(word.charAt(i));
						absent.add(word.charAt(i));
					}
				}else if(feedback.charAt(i) == 'G') {
					if(unused.contains(word.charAt(i))) {
						unused.remove(word.charAt(i));
					}
					if(absent.contains(word.charAt(i))) {
						absent.remove(word.charAt(i));
					}
					green.put(word.charAt(i), green.get(word.charAt(i))+1);
					yellowGreen.put(word.charAt(i), yellowGreen.get(word.charAt(i))+1);
				}else if(feedback.charAt(i) == 'Y') {
					if(unused.contains(word.charAt(i))) {
						unused.remove(word.charAt(i));
					}
					yellowGreen.put(word.charAt(i), yellowGreen.get(word.charAt(i))+1);
				}else {
					System.out.println("PROBLEM WITH HISTORY");
				}
			}
			for(char c: green.keySet()) {
				if(green.get(c)>maxGreen.get(c)) {
					maxGreen.put(c, green.get(c));
				}
				if(yellowGreen.get(c)>maxYellowGreen.get(c)) {
					maxYellowGreen.put(c, yellowGreen.get(c));
				}
			}
		}
		HashMap<Character, Integer> maxYellow = new HashMap<Character, Integer>();
		for(char c: maxGreen.keySet()) {
			maxYellow.put(c, maxYellowGreen.get(c)-maxGreen.get(c));
		}
		System.out.println("--------");
		printCategory("Green", maxGreen);
		printCategory("Yellow", maxYellow);
		printCategory("Absent", absent);
		printCategory("Unused", unused);
	}
	
	public static void printCategory(String name, HashSet<Character> set) {
		String out = "";
		for(char c: set) {
			out += c + ", ";
		}
		if(out.length()>0) {
			out = out.substring(0,out.length()-2);
		}
		System.out.println(name + ": " + out);
	}
	
	public static void printCategory(String name, HashMap<Character,Integer> h) {
		String out = "";
		for(char c: h.keySet()) {
			for(int i = 0; i<h.get(c); i++) {
				out += c + ", ";
			}
		}
		if(out.length()>0) {
			out = out.substring(0,out.length()-2);
		}
		System.out.println(name + ": " + out);
	}
}
