package com.didow.didowapi.entities;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class QuestionScrambleWords extends Question {
  private static final Random randomizer = new Random();
  private static final int RANDOM_LETTERS_QTY = 2;
  private List<String> letters;
  private List<String> hintHangman;

  public QuestionScrambleWords(Word word) {
    super(word);
    this.letters = generateLetters(word);
    this.hintHangman = generateHintHangman(word);
  }

  public List<String> getLetters() {
    return this.letters;
  }

  public List<String> getHintHangman() {
    return this.hintHangman;
  }

  private static List<String> generateLetters(Word word) {
    var letters = new ArrayList<String>(word.toString().length());
    letters.addAll(List.of(word.toString().split("")));
  
    for (int i = 0; i < RANDOM_LETTERS_QTY; i++) {
      var randomLetter = getRandomLetterFrom(letters);
      letters.add(Character.toString(randomLetter));
    }

    Collections.shuffle(letters);
    return letters;
  }

  private static char getRandomLetterFrom(List<String> letters) {
    var targetLetter = letters.get(randomizer.nextInt(letters.size()));
    var dyslexiaLetters = DyslexiaLettersDict.getLetters(targetLetter.toCharArray()[0]);
    return dyslexiaLetters.get(randomizer.nextInt(dyslexiaLetters.size()));
  }

  private static List<String> generateHintHangman(Word word) {
    var hintHangman = new ArrayList<String>();
    hintHangman.addAll(List.of(word.toString().split("")));

    var randomIndexes = new ArrayList<Integer>();
    var limit = hintHangman.size() / 2;

    while (randomIndexes.size() < limit) {
      var randomIndex = randomizer.nextInt(hintHangman.size());
      if (!randomIndexes.contains(randomIndex)) {
        hintHangman.set(randomIndex, "_");
        randomIndexes.add(randomIndex);
      }
    }

    return hintHangman;
  }
}
