package com.didow.didowapi.adapters.repositories;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import com.didow.didowapi.entities.Word;

public interface IWordRepository {
  public CompletableFuture<Word> createWord(Word word);
  public CompletableFuture<List<Word>> createWords(List<Word> words);
  public CompletableFuture<List<Word>> getWords() throws InterruptedException, ExecutionException;
  public CompletableFuture<List<Word>> getWords(int syllables) throws InterruptedException, ExecutionException;
  public CompletableFuture<Word> getWord(String id);
  public CompletableFuture<Word> update(Word word);
  public CompletableFuture<Void> destroy(String id);
}
