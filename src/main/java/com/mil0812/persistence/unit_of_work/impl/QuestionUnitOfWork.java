package com.mil0812.persistence.unit_of_work.impl;

import com.mil0812.persistence.entity.impl.Question;
import com.mil0812.persistence.repository.interfaces.QuestionRepository;
import com.mil0812.persistence.unit_of_work.GeneralUnitOfWork;
import org.springframework.stereotype.Component;

@Component
public class QuestionUnitOfWork extends GeneralUnitOfWork<Question> {

  public QuestionUnitOfWork(QuestionRepository questionRepository) {
    super(questionRepository);
  }
}
