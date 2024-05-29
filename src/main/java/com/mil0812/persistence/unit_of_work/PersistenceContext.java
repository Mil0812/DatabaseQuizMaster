package com.mil0812.persistence.unit_of_work;

import com.mil0812.persistence.unit_of_work.impl.AnswerUnitOfWork;
import com.mil0812.persistence.unit_of_work.impl.QuestionUnitOfWork;
import com.mil0812.persistence.unit_of_work.impl.ResultUnitOfWork;
import com.mil0812.persistence.unit_of_work.impl.SectionUnitOfWork;
import com.mil0812.persistence.unit_of_work.impl.TestTypeUnitOfWork;
import com.mil0812.persistence.unit_of_work.impl.TestUnitOfWork;
import com.mil0812.persistence.unit_of_work.impl.UserUnitOfWork;
import org.springframework.stereotype.Component;


/**
 * Клас, в якому ми отримуємо залежності як сінглтони
 * і представляємо відкриті поля для них
 */
@Component
public class PersistenceContext {

  public final UserUnitOfWork users;
  public final SectionUnitOfWork sections;
  public final TestUnitOfWork tests;
  public final TestTypeUnitOfWork testTypes;
  public final QuestionUnitOfWork questions;
  public final AnswerUnitOfWork answers;
  public final ResultUnitOfWork results;

  public PersistenceContext(
      UserUnitOfWork userUnitOfWork,
      SectionUnitOfWork sectionUnitOfWork,
      TestUnitOfWork testUnitOfWork,
      TestTypeUnitOfWork testTypeUnitOfWork,
      QuestionUnitOfWork questionUnitOfWork,
      AnswerUnitOfWork answerUnitOfWork,
      ResultUnitOfWork resultUnitOfWork
  ) {
    this.users = userUnitOfWork;
    this.sections = sectionUnitOfWork;
    this.tests = testUnitOfWork;
    this.testTypes = testTypeUnitOfWork;
    this.questions = questionUnitOfWork;
    this.answers = answerUnitOfWork;
    this.results = resultUnitOfWork;
  }
}