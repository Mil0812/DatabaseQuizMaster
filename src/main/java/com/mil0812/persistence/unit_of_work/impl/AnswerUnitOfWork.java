package com.mil0812.persistence.unit_of_work.impl;

import com.mil0812.persistence.entity.impl.Answer;
import com.mil0812.persistence.repository.interfaces.AnswerRepository;
import com.mil0812.persistence.unit_of_work.GeneralUnitOfWork;
import org.springframework.stereotype.Component;

@Component
public class AnswerUnitOfWork extends GeneralUnitOfWork<Answer> {
  public final AnswerRepository answerRepository;

  public AnswerUnitOfWork(AnswerRepository answerRepository) {
    super(answerRepository);
    this.answerRepository = answerRepository;
  }
}
