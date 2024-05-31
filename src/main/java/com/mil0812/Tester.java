package com.mil0812;

import com.mil0812.persistence.entity.impl.Answer;
import com.mil0812.persistence.entity.impl.Question;
import com.mil0812.persistence.entity.impl.Test;
import com.mil0812.persistence.repository.interfaces.AnswerRepository;
import com.mil0812.persistence.repository.interfaces.QuestionRepository;
import com.mil0812.persistence.repository.interfaces.TestRepository;
import java.util.Set;
import java.util.UUID;
import org.springframework.stereotype.Component;

@Component
public class Tester {
  private final QuestionRepository questionRepository;
  private final AnswerRepository answerRepository;

  public Tester(QuestionRepository questionRepository,
      AnswerRepository answerRepository) {
    this.questionRepository = questionRepository;
    this.answerRepository = answerRepository;
  }

  public void test(){
    try{
    Set<Question> questions = questionRepository.findAllByTestId(UUID.fromString("0a0efd01-ceb2-4490-953b-1b9870818426"));
    //Set<Answer> answers = answerRepository.findAllByQuestionId(UUID.fromString("6443920d-5308-4710-b93d-b0fdd0479f61"));
    Main.logger.info(STR."Questions: \{questions}");
    //Main.logger.info(STR."Answers: \{answers}");
  }
    catch (Exception e){
    Main.logger.info(STR."Чот пошло не так...\{e}");
  }

  }
}
