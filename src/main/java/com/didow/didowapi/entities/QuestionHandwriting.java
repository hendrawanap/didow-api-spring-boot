package com.didow.didowapi.entities;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class QuestionHandwriting extends Question {
  private static final Random randomizer = new Random();
  private List<String> hintHangman;

  public QuestionHandwriting(Word word) {
    super(word);
    this.hintHangman = generateHintHangman(word);
  }

  public List<String> getHintHangman() {
    return this.hintHangman;
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
