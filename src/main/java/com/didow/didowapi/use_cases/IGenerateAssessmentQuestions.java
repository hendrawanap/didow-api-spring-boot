package com.didow.didowapi.use_cases;

import java.util.List;
import java.util.concurrent.CancellationException;
import java.util.concurrent.ExecutionException;
import com.didow.didowapi.adapters.repositories.IWordRepository;
import com.didow.didowapi.entities.Question;

public interface IGenerateAssessmentQuestions {
  List<Question> apply(IWordRepository wordRepository) throws CancellationException, InterruptedException, ExecutionException;
}
