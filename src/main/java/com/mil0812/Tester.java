package com.mil0812;

import com.mil0812.persistence.entity.impl.Answer;
import com.mil0812.persistence.entity.impl.Question;
import com.mil0812.persistence.entity.impl.Result;
import com.mil0812.persistence.entity.impl.Test;
import com.mil0812.persistence.repository.interfaces.AnswerRepository;
import com.mil0812.persistence.repository.interfaces.QuestionRepository;
import com.mil0812.persistence.repository.interfaces.TestRepository;
import com.mil0812.persistence.unit_of_work.PersistenceContext;
import com.mil0812.presentation.util.CurrentUser;
import java.time.LocalDateTime;
import java.util.Set;
import java.util.UUID;
import org.springframework.stereotype.Component;

@Component
public class Tester {
  private final QuestionRepository questionRepository;
  private final AnswerRepository answerRepository;
  private final PersistenceContext persistenceContext;

  public Tester(QuestionRepository questionRepository,
      AnswerRepository answerRepository, PersistenceContext persistenceContext) {
    this.questionRepository = questionRepository;
    this.answerRepository = answerRepository;
    this.persistenceContext = persistenceContext;
  }

  public void test(){
    try{
//      LocalDateTime dateOfTest = LocalDateTime.now();
//        persistenceContext.results.registerNew(
//            new Result(
//                null,
//                UUID.fromString("8b4c0843-d169-4631-a1e3-5d6484352205"),
//                UUID.fromString("15e0daa7-8936-414a-8d5d-18e43b682d67"),
//                100,
//                dateOfTest
//            )
//        );
//        persistenceContext.results.commit();

    //Set<Question> questions = questionRepository.findAllByTestId(UUID.fromString("0a0efd01-ceb2-4490-953b-1b9870818426"));
    //Set<Answer> answers = answerRepository.findAllByQuestionId(UUID.fromString("6443920d-5308-4710-b93d-b0fdd0479f61"));
    //Main.logger.info(STR."Questions: \{questions}");
    //Main.logger.info(STR."Answers: \{answers}");
  }
    catch (Exception e){
    Main.logger.info(STR."Чот пошло не так...\{e}");
  }

  }
}
