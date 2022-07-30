package com.didow.didowapi.adapters.repositories.in_memory;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.stream.Collectors;
import com.didow.didowapi.adapters.repositories.IWordRepository;
import com.didow.didowapi.entities.Word;

public class InMemoryWordRepository implements IWordRepository {
  private static List<Word> words = new ArrayList<>();

  @Override
  public CompletableFuture<Word> createWord(Word word) {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public CompletableFuture<List<Word>> createWords(List<Word> words) {
    InMemoryWordRepository.words.addAll(words);
    return CompletableFuture.completedFuture(InMemoryWordRepository.words);
  }

  @Override
  public CompletableFuture<List<Word>> getWords() throws InterruptedException, ExecutionException {
    // TODO Auto-generated method stub
    return null;
  }

  private void populateWordList() {
    if (words.isEmpty()) {
      words.add(new Word("buku", 2, "buku.jpg"));
      words.add(new Word("kaki", 2, "kaki.jpg"));
      words.add(new Word("kuku", 2, "kuku.jpg"));
      words.add(new Word("makan", 2, "makan.jpg"));
      words.add(new Word("gitar", 2, "gitar.jpg"));

      words.add(new Word("kepala", 3, "kepala.jpg"));
      words.add(new Word("kereta", 3, "kereta.jpg"));
      words.add(new Word("lemari", 3, "lemari.jpg"));
      words.add(new Word("radio", 3, "radio.jpg"));
      words.add(new Word("pelangi", 3, "pelangi.jpg"));

      words.add(new Word("apartemen", 4, "apartemen.jpg"));
      words.add(new Word("pengusaha", 4, "pengusaha.jpg"));
      words.add(new Word("sutradara", 4, "sutradara.jpg"));
      words.add(new Word("sekretaris", 4, "sekretaris.jpg"));
      words.add(new Word("pengacara", 4, "pengacara.jpg"));
    }
  }

  @Override
  public CompletableFuture<List<Word>> getWords(int syllables) throws InterruptedException, ExecutionException {

    if (words.isEmpty()) {
      return CompletableFuture.completedFuture(List.of());
    }

    return CompletableFuture.completedFuture(
      words.stream()
        .filter(word -> word.getSyllables() == syllables)
        .collect(Collectors.toList())
    );
  }

  @Override
  public CompletableFuture<Word> getWord(String id) {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public CompletableFuture<Word> update(Word word) {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public CompletableFuture<Void> destroy(String id) {
    // TODO Auto-generated method stub
    return null;
  }
}
