package com.mil0812.domain.impl;

import com.mil0812.Main;
import com.mil0812.domain.TableFiller;
import com.mil0812.domain.entities_names.QuestionsNames;
import com.mil0812.persistence.entity.impl.Answer;
import com.mil0812.persistence.entity.impl.Question;
import com.mil0812.persistence.repository.interfaces.QuestionRepository;
import com.mil0812.persistence.unit_of_work.PersistenceContext;
import java.util.Optional;
import java.util.UUID;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@Order(5)
public class AnswerTableFiller implements TableFiller {

  private final QuestionRepository questionRepository;

  private final PersistenceContext persistenceContext;

  public AnswerTableFiller(QuestionRepository questionRepository, PersistenceContext persistenceContext) {
    this.questionRepository = questionRepository;
    this.persistenceContext = persistenceContext;
  }

  @Override
  public void fill() {

    String firstQuestionNameDB = QuestionsNames.DATABASES_FIRST_QUESTION.getName();
    String secondQuestionNameDB = QuestionsNames.DATABASES_SECOND_QUESTION.getName();
    String thirdQuestionNameDB = QuestionsNames.DATABASES_THIRD_QUESTION.getName();
    String forthQuestionNameDB = QuestionsNames.DATABASES_FORTH_QUESTION.getName();
    String fifthQuestionNameDB = QuestionsNames.DATABASES_FIFTH_QUESTION.getName();
    String sixthQuestionNameDB = QuestionsNames.DATABASES_SIXTH_QUESTION.getName();
    String seventhQuestionNameDB = QuestionsNames.DATABASES_SEVENTH_QUESTION.getName();


    String firstQuestionNameAccess = QuestionsNames.ACCESS_TABLES_FIRST_QUESTION.getName();
    String secondQuestionNameAccess = QuestionsNames.ACCESS_TABLES_SECOND_QUESTION.getName();
    String thirdQuestionNameAccess = QuestionsNames.ACCESS_TABLES_THIRD_QUESTION.getName();
    String forthQuestionNameAccess = QuestionsNames.ACCESS_TABLES_FORTH_QUESTION.getName();
    String fifthQuestionNameAccess = QuestionsNames.ACCESS_TABLES_FIFTH_QUESTION.getName();

    // Тест "Основні поняття..." з 1 варіантом відповіді. Питання #1
    persistenceContext.answers.registerNew(
        new Answer(
            null,
            currentQuestionId(firstQuestionNameDB),
            "Властивостей",
            true
        )
    );
    persistenceContext.answers.registerNew(
        new Answer(
            null,
            currentQuestionId(firstQuestionNameDB),
            "Полів",
            false
        )
    );
    persistenceContext.answers.registerNew(
        new Answer(
            null,
            currentQuestionId(firstQuestionNameDB),
            "Записів",
            false
        )
    );
    persistenceContext.answers.registerNew(
        new Answer(
            null,
            currentQuestionId(firstQuestionNameDB),
            "Полів і записів",
            false
        )
    );
    persistenceContext.answers.commit();

    // Тест "Основні поняття..." з 1 варіантом відповіді. Питання #2

    persistenceContext.answers.registerNew(
        new Answer(
            null,
            currentQuestionId(secondQuestionNameDB),
            "Ієрархічна",
            false
        )
    );

    persistenceContext.answers.registerNew(
        new Answer(
            null,
            currentQuestionId(secondQuestionNameDB),
            "Мережна",
            false
        )
    );

    persistenceContext.answers.registerNew(
        new Answer(
            null,
            currentQuestionId(secondQuestionNameDB),
            "Багаторангова",
            true
        )
    );

    persistenceContext.answers.registerNew(
        new Answer(
            null,
            currentQuestionId(secondQuestionNameDB),
            "Реляційна",
            false
        )
    );
    persistenceContext.answers.commit();

    // Тест "Основні поняття..." з 1 варіантом відповіді. Питання #3

    persistenceContext.answers.registerNew(
        new Answer(
            null,
            currentQuestionId(thirdQuestionNameDB),
            "Запит",
            false
        )
    );
    persistenceContext.answers.registerNew(
        new Answer(
            null,
            currentQuestionId(thirdQuestionNameDB),
            "Таблиця",
            true
        )
    );
    persistenceContext.answers.registerNew(
        new Answer(
            null,
            currentQuestionId(thirdQuestionNameDB),
            "Файл",
            false
        )
    );
    persistenceContext.answers.registerNew(
        new Answer(
            null,
            currentQuestionId(thirdQuestionNameDB),
            "Форма",
            false
        )
    );
    persistenceContext.answers.commit();

    // Тест "Основні поняття..." з 1 варіантом відповіді. Питання #4

    persistenceContext.answers.registerNew(
        new Answer(
            null,
            currentQuestionId(forthQuestionNameDB),
            "Мережевою",
            false
        )
    );
    persistenceContext.answers.registerNew(
        new Answer(
            null,
            currentQuestionId(forthQuestionNameDB),
            "Ієрархічною",
            false
        )
    );
    persistenceContext.answers.registerNew(
        new Answer(
            null,
            currentQuestionId(forthQuestionNameDB),
            "Реляційною",
            true
        )
    );
    persistenceContext.answers.registerNew(
        new Answer(
            null,
            currentQuestionId(forthQuestionNameDB),
            "Табличною",
            false
        )
    );
    persistenceContext.answers.commit();

    // Тест "Основні поняття..." з 1 варіантом відповіді. Питання #5
    persistenceContext.answers.registerNew(
        new Answer(
            null,
            currentQuestionId(fifthQuestionNameDB),
            "SQL",
            true
        )
    );
    persistenceContext.answers.registerNew(
        new Answer(
            null,
            currentQuestionId(fifthQuestionNameDB),
            "HTML",
            false
        )
    );
    persistenceContext.answers.registerNew(
        new Answer(
            null,
            currentQuestionId(fifthQuestionNameDB),
            "XML",
            false
        )
    );
    persistenceContext.answers.registerNew(
        new Answer(
            null,
            currentQuestionId(fifthQuestionNameDB),
            "CSS",
            false
        )
    );
    persistenceContext.answers.commit();

    // Тест "Основні поняття..." з 1 варіантом відповіді. Питання #6
    persistenceContext.answers.registerNew(
        new Answer(
            null,
            currentQuestionId(sixthQuestionNameDB),
            "Індекс",
            false
        )
    );
    persistenceContext.answers.registerNew(
        new Answer(
            null,
            currentQuestionId(sixthQuestionNameDB),
            "Унікальний ключ",
            false
        )
    );
    persistenceContext.answers.registerNew(
        new Answer(
            null,
            currentQuestionId(sixthQuestionNameDB),
            "Первинний ключ",
            true
        )
    );
    persistenceContext.answers.registerNew(
        new Answer(
            null,
            currentQuestionId(sixthQuestionNameDB),
            "Зовнішній ключ",
            false
        )
    );
    persistenceContext.answers.commit();

    // Тест "Основні поняття..." з 1 варіантом відповіді. Питання #7

    persistenceContext.answers.registerNew(
        new Answer(
            null,
            currentQuestionId(seventhQuestionNameDB),
            "Збереження даних у вигляді текстових файлів",
            false
        )
    );
    persistenceContext.answers.registerNew(
        new Answer(
            null,
            currentQuestionId(seventhQuestionNameDB),
            "Мінімізація дублювання даних",
            true
        )
    );
    persistenceContext.answers.registerNew(
        new Answer(
            null,
            currentQuestionId(seventhQuestionNameDB),
            "Максимізація кількості таблиць",
            false
        )
    );
    persistenceContext.answers.registerNew(
        new Answer(
            null,
            currentQuestionId(seventhQuestionNameDB),
            "Використання тільки первинних ключів",
            false
        )
    );
    persistenceContext.answers.commit();

    ////////////////////////////////////

    // Тест "Структура таблиць в Access" з декількома варіантами відповіді. Питання #1
    persistenceContext.answers.registerNew(
        new Answer(
            null,
            currentQuestionId(firstQuestionNameAccess),
            "Майстер таблиць",
            true
        )
    );
    persistenceContext.answers.registerNew(
        new Answer(
            null,
            currentQuestionId(firstQuestionNameAccess),
            "Майстер підстановок",
            false
        )
    );
    persistenceContext.answers.registerNew(
        new Answer(
            null,
            currentQuestionId(firstQuestionNameAccess),
            "Конструктор таблиць",
            true
        )
    );
    persistenceContext.answers.registerNew(
        new Answer(
            null,
            currentQuestionId(firstQuestionNameAccess),
            "Структура таблиць",
            false
        )
    );
    persistenceContext.answers.commit();


    // Тест "Структура таблиць в Access" з декількома варіантами відповіді. Питання #2
    persistenceContext.answers.registerNew(
        new Answer(
            null,
            currentQuestionId(secondQuestionNameAccess),
            "Text",
            false
        )
    );
    persistenceContext.answers.registerNew(
        new Answer(
            null,
            currentQuestionId(secondQuestionNameAccess),
            "Int",
            true
        )
    );
    persistenceContext.answers.registerNew(
        new Answer(
            null,
            currentQuestionId(secondQuestionNameAccess),
            "Integer",
            true
        )
    );
    persistenceContext.answers.registerNew(
        new Answer(
            null,
            currentQuestionId(secondQuestionNameAccess),
            "Double",
            false
        )
    );
    persistenceContext.answers.commit();

    // Тест "Структура таблиць в Access" з декількома варіантами відповіді. Питання #3
    persistenceContext.answers.registerNew(
        new Answer(
            null,
            currentQuestionId(thirdQuestionNameAccess),
            "Швидкий пошук",
            true
        )
    );
    persistenceContext.answers.registerNew(
        new Answer(
            null,
            currentQuestionId(thirdQuestionNameAccess),
            "Автоматичне сортування",
            false
        )
    );
    persistenceContext.answers.registerNew(
        new Answer(
            null,
            currentQuestionId(thirdQuestionNameAccess),
            "Валідація даних",
            false
        )
    );
    persistenceContext.answers.registerNew(
        new Answer(
            null,
            currentQuestionId(thirdQuestionNameAccess),
            "Заборона дублювання значень",
            true
        )
    );
    persistenceContext.answers.commit();

    // Тест "Структура таблиць в Access" з декількома варіантами відповіді. Питання #4
    persistenceContext.answers.registerNew(
        new Answer(
            null,
            currentQuestionId(forthQuestionNameAccess),
            "Видалення даних",
            false
        )
    );
    persistenceContext.answers.registerNew(
        new Answer(
            null,
            currentQuestionId(forthQuestionNameAccess),
            "Вибірка даних",
            true
        )
    );persistenceContext.answers.registerNew(
        new Answer(
            null,
            currentQuestionId(forthQuestionNameAccess),
            "Оновлення даних",
            true
        )
    );persistenceContext.answers.registerNew(
        new Answer(
            null,
            currentQuestionId(forthQuestionNameAccess),
            "Створення таблиць",
            false
        )
    );
    persistenceContext.answers.commit();

    // Тест "Структура таблиць в Access" з декількома варіантами відповіді. Питання #5
    persistenceContext.answers.registerNew(
        new Answer(
            null,
            currentQuestionId(fifthQuestionNameAccess),
            "Обов'язкове поле",
            false
        )
    );
    persistenceContext.answers.registerNew(
        new Answer(
            null,
            currentQuestionId(fifthQuestionNameAccess),
            "Унікальне поле",
            false
        )
    );
    persistenceContext.answers.registerNew(
        new Answer(
            null,
            currentQuestionId(fifthQuestionNameAccess),
            "Первинний ключ",
            true
        )
    );
    persistenceContext.answers.registerNew(
        new Answer(
            null,
            currentQuestionId(fifthQuestionNameAccess),
            "Зовнішній ключ",
            true
        )
    );
    persistenceContext.answers.commit();

  }

  private UUID currentQuestionId(String questionText) {
    Optional<Question> currentQuestion;
    try{
      currentQuestion = questionRepository.findByQuestionText(questionText);
      return  currentQuestion.get().id();
    }
    catch (Exception e){
      Main.logger.error(STR."Не вдалося отримати питання через назву: \{e}");
      return null;
    }

  }
}
