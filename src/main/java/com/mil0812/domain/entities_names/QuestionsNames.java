package com.mil0812.domain.entities_names;

public enum QuestionsNames {

  // Test "General about databases" | One right answer
  DATABASES_FIRST_QUESTION("Об'єкт бази даних характеризується сукупністю:"),
  DATABASES_SECOND_QUESTION("Якої моделі даних НЕ існує?"),
  DATABASES_THIRD_QUESTION("Основним об'єктом реляційної бази даних є:"),
  DATABASES_FORTH_QUESTION("База даних з табличною формою організації називається"),
  DATABASES_FIFTH_QUESTION("Яка мова найчастіше використовується для управління базами даних?"),
  DATABASES_SIXTH_QUESTION("Який тип ключа використовується для унікальної ідентифікації запису в таблиці бази даних?"),
  DATABASES_SEVENTH_QUESTION("Яка з наведених нижче властивостей характеризує нормалізовану базу даних?"),

  // Test "Table structure in Access" | Few right answers
  ACCESS_TABLES_FIRST_QUESTION("Які з цих інструментаріїв створення таблиць наявні у середовищі Access?"),
  ACCESS_TABLES_SECOND_QUESTION("Який тип даних використовується для зберігання цілих чисел в Access?"),
  ACCESS_TABLES_THIRD_QUESTION("Які можливості надаються при встановленні індексів для поля в Access?"),
  ACCESS_TABLES_FORTH_QUESTION("Які операції можна виконувати за допомогою запитів у Access?"),
  ACCESS_TABLES_FIFTH_QUESTION("Які типи обмежень можна встановлювати для полів таблиць у середовищі Access?")
  ;

  private final String name;

  QuestionsNames(String name) {
    this.name = name;
  }

  public String getName() {
    return name;
  }
}
