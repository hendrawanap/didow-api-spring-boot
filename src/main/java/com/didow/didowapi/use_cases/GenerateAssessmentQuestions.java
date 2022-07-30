package com.didow.didowapi.use_cases;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ExecutionException;
import org.springframework.stereotype.Service;
import com.didow.didowapi.adapters.repositories.IWordRepository;
import com.didow.didowapi.entities.Question;
import com.didow.didowapi.entities.QuestionHandwriting;
import com.didow.didowapi.entities.QuestionMultipleChoice;
import com.didow.didowapi.entities.QuestionScrambleWords;
import com.didow.didowapi.entities.Word;

@Service("generateAssessmentQuestions")
public class GenerateAssessmentQuestions implements IGenerateAssessmentQuestions {
  private static final Random randomizer = new Random();
  private static final int QUESTIONS_QTY_PER_LEVEL = 3;
  private static final int LEVELS_QTY = 3;
  private static final int TOTAL_QUESTIONS_QTY = QUESTIONS_QTY_PER_LEVEL * LEVELS_QTY;

  @Override
  public List<Question> apply(IWordRepository wordRepository) throws InterruptedException, ExecutionException {
    var easyWordsFuture = wordRepository.getWords(2);
    var mediumWordsFuture = wordRepository.getWords(3);
    var hardWordsFuture = wordRepository.getWords(4);
    var words = new ArrayList<Word>(TOTAL_QUESTIONS_QTY);

    words.addAll(getRandomWords(easyWordsFuture.get(), QUESTIONS_QTY_PER_LEVEL));
    words.addAll(getRandomWords(mediumWordsFuture.get(), QUESTIONS_QTY_PER_LEVEL));
    words.addAll(getRandomWords(hardWordsFuture.get(), QUESTIONS_QTY_PER_LEVEL));

    List<Question> questions;

    if (words.isEmpty()) {
      questions = List.of();
    } else {
      questions = new ArrayList<>(words.size());
      var iterator = words.listIterator();
  
      while (iterator.hasNext()) {
        var index = iterator.nextIndex();
        var word = iterator.next();
        Question question;

        if (index % 3 == 0) {
          question = new QuestionMultipleChoice(word);
        } else if (index % 3 == 1) {
          question = new QuestionScrambleWords(word);
        } else {
          question = new QuestionHandwriting(word);
        }

        questions.add(question);
      }
    }

    return questions;
  }

  private List<Word> getRandomWords(List<Word> words, int qty) {
    if (qty == 0 || words.isEmpty()) {
      return List.of();
    }

    var copyWords = new ArrayList<Word>(words.size());
    var randomWords = new ArrayList<Word>(qty);
    copyWords.addAll(words);

    while (randomWords.size() < qty && !copyWords.isEmpty()) {
      var randomIndex = randomizer.nextInt(copyWords.size());
      randomWords.add(copyWords.remove(randomIndex));
    }

    return randomWords;
  }
}
