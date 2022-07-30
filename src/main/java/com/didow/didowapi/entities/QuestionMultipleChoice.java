package com.didow.didowapi.entities;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class QuestionMultipleChoice extends Question {
  private static final Random randomizer = new Random();
  private static final int CHOICES_QTY = 4;
  private List<String> choices;

  public QuestionMultipleChoice(Word word) {
    super(word);
    this.choices = generateChoices(word);
  }

  public List<String> getChoices() {
    return this.choices;
  }

  private static List<String> generateChoices(Word word) {
    var originalLetters = word.toString().toCharArray();
    var choices = new ArrayList<String>(CHOICES_QTY);
    var randomizedIndex = new ArrayList<Integer>(originalLetters.length);
    var randomLettersQtyLimit = originalLetters.length / 2;
    choices.add(word.toString());

    for (int i = 0; i < CHOICES_QTY - 1; i++) {
      var modifiedLetters = Arrays.copyOf(originalLetters, originalLetters.length);

      while (randomizedIndex.size() < randomLettersQtyLimit) {
        var targetIndex = randomizer.nextInt(originalLetters.length);
        var letters = DyslexiaLettersDict.getLetters(word.toString().charAt(targetIndex));
        modifiedLetters[targetIndex] = letters.get(randomizer.nextInt(letters.size()));
        randomizedIndex.add(targetIndex);
      }

      choices.add(String.valueOf(modifiedLetters));
      randomizedIndex.clear();
    }

    Collections.shuffle(choices);
    return choices;
  }
}
