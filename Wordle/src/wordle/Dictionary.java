
package wordle;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Random;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;


public class Dictionary {

  private HashSet<String> wordSet;
  
  public Dictionary(String fileName) {
    this.wordSet = Dictionary.initializeWordSet(fileName);
  }

  private static HashSet<String> initializeWordSet(String fileName) {
    Scanner fileScanner = null;
    try {
      fileScanner = new Scanner(new File(fileName));
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    }
    HashSet<String> wordSet = new HashSet<>();
    while (fileScanner.hasNextLine()) {
      wordSet.add(fileScanner.nextLine());
    }
    return wordSet;
  }

  public boolean containsWord(String word) {
    return wordSet.contains(word);
  }

  public String getRandomWord() {
    int randomIndex = new Random().nextInt(wordSet.size());
    Iterator<String> iter = wordSet.iterator();
    for (int i = 0; i < randomIndex; i++) {
      iter.next();
    }
    return iter.next();
  }

}

