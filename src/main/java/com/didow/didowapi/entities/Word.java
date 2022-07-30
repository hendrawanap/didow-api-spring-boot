package com.didow.didowapi.entities;

public class Word {
  private String word;
  private int syllables;
  private String hintImgUrl;

  public Word(String word, int syllables, String hintImgUrl) {
    this.word = word;
    this.syllables = syllables;
    this.hintImgUrl = hintImgUrl;
  }

  public void setWord(String word) {
    this.word = word;
  }

  public int getSyllables() {
    return this.syllables;
  }

  public void setSyllables(int syllables) {
    this.syllables = syllables;
  }

  public String getHintImgUrl() {
    return this.hintImgUrl;
  }

  public void setHintImgUrl(String hintImgUrl) {
    this.hintImgUrl = hintImgUrl;
  }

  @Override
  public String toString() {
    return this.word;
  }
}
