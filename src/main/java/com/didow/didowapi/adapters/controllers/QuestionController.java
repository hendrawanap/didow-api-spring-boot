package com.didow.didowapi.adapters.controllers;

import java.util.concurrent.ExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.didow.didowapi.adapters.repositories.IWordRepository;
import com.didow.didowapi.adapters.responses.QuestionResponse;
import com.didow.didowapi.use_cases.IGenerateAssessmentQuestions;

@RestController
public class QuestionController {
  @Autowired
  @Qualifier("firebaseWordRepository")
  private IWordRepository repository;

  @Autowired
  @Qualifier("generateAssessmentQuestions")
  private IGenerateAssessmentQuestions generateAssessmentQuestions;

  @GetMapping("/api/v1/questions")
  public QuestionResponse getQuestions(
    @RequestParam(value = "type", required = true) String exerciseType,
    @RequestParam(value = "weightPoint", required = false) Integer weightPoint,
    @RequestParam(value = "qty", required = false) Integer qty,
    @RequestParam(value = "easy", required = false) Boolean easy,
    @RequestParam(value = "medium", required = false) Boolean medium,
    @RequestParam(value = "hard", required = false) Boolean hard
  ) throws InterruptedException, ExecutionException {
    var questionEntities = this.generateAssessmentQuestions.apply(this.repository);
    return new QuestionResponse(questionEntities);
  }
}
