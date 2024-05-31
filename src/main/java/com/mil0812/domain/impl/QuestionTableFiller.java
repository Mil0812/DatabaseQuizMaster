package com.mil0812.domain.impl;

import com.mil0812.Main;
import com.mil0812.domain.TableFiller;
import com.mil0812.domain.entities_names.QuestionsNames;
import com.mil0812.domain.entities_names.TestsNames;
import com.mil0812.persistence.entity.impl.Question;
import com.mil0812.persistence.entity.impl.Test;
import com.mil0812.persistence.entity.impl.TestType;
import com.mil0812.persistence.repository.interfaces.TestRepository;
import com.mil0812.persistence.unit_of_work.PersistenceContext;
import java.util.Optional;
import java.util.UUID;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@Order(4)
public class QuestionTableFiller implements TableFiller {
  private final TestRepository testRepository;

  private final PersistenceContext persistenceContext;

  public QuestionTableFiller(TestRepository testRepository, PersistenceContext persistenceContext) {
    this.testRepository = testRepository;
    this.persistenceContext = persistenceContext;
  }

  @Override
  public void fill() {

    // «Загальні відомості про бази даних» | 7 питань

      persistenceContext.questions.registerNew(
          new Question(
              null,
              currentTestId(TestsNames.GENERAL_ABOUT_DATABASES.getName()),
              QuestionsNames.DATABASES_FIRST_QUESTION.getName()
              )
      );

    persistenceContext.questions.registerNew(
        new Question(
            null,
            currentTestId(TestsNames.GENERAL_ABOUT_DATABASES.getName()),
            QuestionsNames.DATABASES_SECOND_QUESTION.getName()
        )
    );

    persistenceContext.questions.registerNew(
        new Question(
            null,
            currentTestId(TestsNames.GENERAL_ABOUT_DATABASES.getName()),
            QuestionsNames.DATABASES_THIRD_QUESTION.getName()
        )
    );

    persistenceContext.questions.registerNew(
        new Question(
            null,
            currentTestId(TestsNames.GENERAL_ABOUT_DATABASES.getName()),
            QuestionsNames.DATABASES_FORTH_QUESTION.getName()
        )
    );

    persistenceContext.questions.registerNew(
        new Question(
            null,
            currentTestId(TestsNames.GENERAL_ABOUT_DATABASES.getName()),
            QuestionsNames.DATABASES_FIFTH_QUESTION.getName()
        )
    );

    persistenceContext.questions.registerNew(
        new Question(
            null,
            currentTestId(TestsNames.GENERAL_ABOUT_DATABASES.getName()),
            QuestionsNames.DATABASES_SIXTH_QUESTION.getName()
        )
    );
    persistenceContext.questions.registerNew(
        new Question(
            null,
            currentTestId(TestsNames.GENERAL_ABOUT_DATABASES.getName()),
            QuestionsNames.DATABASES_SEVENTH_QUESTION.getName()
        )
    );
      persistenceContext.questions.commit();

    // «Структура таблиць в Access» | 5 питань

    persistenceContext.questions.registerNew(
        new Question(
            null,
            currentTestId(TestsNames.TABLES_STRUCTURE_ACCESS.getName()),
            QuestionsNames.ACCESS_TABLES_FIRST_QUESTION.getName()
        )
    );
    persistenceContext.questions.registerNew(
        new Question(
            null,
            currentTestId(TestsNames.TABLES_STRUCTURE_ACCESS.getName()),
            QuestionsNames.ACCESS_TABLES_SECOND_QUESTION.getName()
        )
    );
    persistenceContext.questions.registerNew(
        new Question(
            null,
            currentTestId(TestsNames.TABLES_STRUCTURE_ACCESS.getName()),
            QuestionsNames.ACCESS_TABLES_THIRD_QUESTION.getName()
        )
    );
    persistenceContext.questions.registerNew(
        new Question(
            null,
            currentTestId(TestsNames.TABLES_STRUCTURE_ACCESS.getName()),
            QuestionsNames.ACCESS_TABLES_FORTH_QUESTION.getName()
        )
    );
    persistenceContext.questions.registerNew(
        new Question(
            null,
            currentTestId(TestsNames.TABLES_STRUCTURE_ACCESS.getName()),
            QuestionsNames.ACCESS_TABLES_FIFTH_QUESTION.getName()
        )
    );
    persistenceContext.questions.commit();
  }

  private UUID currentTestId(String testTitle) {
    Optional<Test> currentTest;

    try{
      currentTest = testRepository.findByTitle(testTitle);
      return currentTest.get().id();
    }
    catch (Exception e){
      Main.logger.error(STR."Не вдалося отримати тест через назву: \{e}");
      return null;
    }
  }
}
