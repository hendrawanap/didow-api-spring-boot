package com.didow.didowapi.adapters.repositories.firebase;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.stream.Collectors;
import org.springframework.stereotype.Repository;
import com.didow.didowapi.adapters.repositories.IWordRepository;
import com.didow.didowapi.adapters.repositories.in_memory.InMemoryWordRepository;
import com.didow.didowapi.entities.Word;
import com.didow.didowapi.frameworks.services.FirebaseService;

@Repository("firebaseWordRepository")
public class WordRepository implements IWordRepository {
  private FirebaseService firebaseService;
  private InMemoryWordRepository inMemoryWordRepository;

  public WordRepository(FirebaseService firebaseService) {
    this.firebaseService = firebaseService;
    this.inMemoryWordRepository = new InMemoryWordRepository();
  }

  @Override
  public CompletableFuture<Word> createWord(Word word) {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public CompletableFuture<List<Word>> createWords(List<Word> words) {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public CompletableFuture<List<Word>> getWords() throws InterruptedException, ExecutionException {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public CompletableFuture<List<Word>> getWords(int syllables) throws InterruptedException, ExecutionException {
    var cachedWordsFuture = inMemoryWordRepository.getWords(syllables);
    var cachedWords = cachedWordsFuture.get();

    if (cachedWords.isEmpty()) {
      return CompletableFuture.supplyAsync(() -> syncGetWords(syllables));
    } else {
      return cachedWordsFuture;
    }
  }

  private List<Word> syncGetWords(int syllables) {
    try {
      var query = firebaseService.getDB().collection("words").whereEqualTo("syllables", syllables).get();
      var querySnapshot = query.get();
      var documents = querySnapshot.getDocuments();
      var words = documents.stream()
        .map(document -> new Word(
          document.getString("word"),
          document.getLong("syllables").intValue(),
          document.getString("hintImg")))
        .collect(Collectors.toList());

      cacheWords(words);
      return words;
    } catch (InterruptedException e) {
      e.printStackTrace();
      Thread.currentThread().interrupt();
      return List.of();
    } catch (ExecutionException e) {
      e.printStackTrace();
      return List.of();
    }
  }

  private void cacheWords(List<Word> words) {
    this.inMemoryWordRepository.createWords(words);
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
