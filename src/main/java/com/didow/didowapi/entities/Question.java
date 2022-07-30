package com.didow.didowapi.entities;

import java.util.Objects;

public class Question implements Comparable<Question> {
  private Word word;

  public Question(Word word) {
    this.word = word;
  }

  public String getWord() {
    return this.word.toString();
  }

  public int getSyllables() {
    return this.word.getSyllables();
  }

  public String getHintImgUrl() {
    return this.word.getHintImgUrl();
  }

  @Override
  public boolean equals(Object obj) {
    if (obj == this) {
      return true;
    }

    if (!(obj instanceof Question)) {
      return false;
    }

    var question = (Question) obj;

    return question.getWord().equals(this.getWord())
      && question.getSyllables() == this.getSyllables()
      && question.getHintImgUrl().equals(this.getHintImgUrl());
  }

  @Override
  public int hashCode() {
    return Objects.hash(this.getWord() + this.getSyllables() + this.getHintImgUrl());
  }

  @Override
  public int compareTo(Question o) {
    return this.getWord().compareTo(o.getWord());
  }
}
