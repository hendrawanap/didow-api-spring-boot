package com.didow.didowapi.adapters.responses;

import java.util.List;
import java.util.stream.Collectors;
import com.didow.didowapi.entities.Question;
import com.didow.didowapi.entities.QuestionHandwriting;
import com.didow.didowapi.entities.QuestionMultipleChoice;
import com.didow.didowapi.entities.QuestionScrambleWords;

public class QuestionResponse extends BaseResponse {
  private List<Data> data;

  public QuestionResponse(List<Question> questionEntities) {
    this.data = questionEntities.stream().map(Data::new).collect(Collectors.toList());
  }

  public List<Data> getData() {
    return this.data;
  }

  class Data {
    private String word;
    private int syllables;
    private String hintImg;
    private String type;
    private MultipleChoice multipleChoice;
    private ScrambleWords scrambleWords;
    private Handwriting handwriting;
  
    public Data(Question question) {
      this.word = question.getWord();
      this.syllables = question.getSyllables();
      this.hintImg = question.getHintImgUrl();
  
      if (question instanceof QuestionMultipleChoice) {
        type = "multipleChoice";
        var multipleChoiceQuestion = (QuestionMultipleChoice) question;
        this.multipleChoice = new MultipleChoice(multipleChoiceQuestion.getChoices());
      } else if (question instanceof QuestionScrambleWords) {
        type = "scrambleWords";
        var scrambleWordsQuestion = (QuestionScrambleWords) question;
        this.scrambleWords = new ScrambleWords(scrambleWordsQuestion.getLetters(), scrambleWordsQuestion.getHintHangman());
      } else if (question instanceof QuestionHandwriting) {
        type = "handwriting";
        var handwritingQuestion = (QuestionHandwriting) question;
        this.handwriting = new Handwriting(handwritingQuestion.getHintHangman());
      }
    }

    public String getWord() {
      return this.word;
    }
  
    public int getSyllables() {
      return this.syllables;
    }
  
    public String getHintImg() {
      return this.hintImg;
    }
  
    public String getType() {
      return this.type;
    }
  
    public MultipleChoice getMultipleChoice() {
      return this.multipleChoice;
    }
  
    public ScrambleWords getScrambleWords() {
      return this.scrambleWords;
    }
  
    public Handwriting getHandwriting() {
      return this.handwriting;
    }
  }

  class MultipleChoice {
    private List<String> choices;
  
    public MultipleChoice(List<String> choices) {
      this.choices = choices;
    }
  
    public List<String> getChoices() {
      return this.choices;
    }
  }

  class ScrambleWords {
    private List<String> letters;
    private List<String> hintHangman;
  
    public ScrambleWords(List<String> letters, List<String> hintHangman) {
      this.letters = letters;
      this.hintHangman = hintHangman;
    }
  
    public List<String> getLetters() {
      return this.letters;
    }
  
    public List<String> getHintHangman() {
      return this.hintHangman;
    }
  }

  class Handwriting {
    private List<String> hintHangman;
  
    public Handwriting(List<String> hintHangman) {
      this.hintHangman = hintHangman;
    }
  
    public List<String> getHintHangman() {
      return this.hintHangman;
    }
  }
}
