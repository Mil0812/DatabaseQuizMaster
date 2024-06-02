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
  ACCESS_TABLES_FIFTH_QUESTION("Які типи обмежень можна встановлювати для полів таблиць у середовищі Access?"),

  // Test "Tables in database" | One right answer
  TABLES_IN_DB_FIRST_QUESTION("Яка основна характеристика полів?"),
  TABLES_IN_DB_SECOND_QUESTION("Що являє собою СУБД?"),
  TABLES_IN_DB_THIRD_QUESTION("Як називаються стовпчики в таблицях бази даних?"),
  TABLES_IN_DB_FORTH_QUESTION("Як називаються рядки в таблицях бази даних?"),
  TABLES_IN_DB_FIFTH_QUESTION("Об’єкти, безпосередньо призначені для зберігання інформації?"),

  // Test "SQL commands" | One right answer
  SQL_COMMANDS_FIRST_QUESTION("Яка команда SQL використовується для вибору даних з бази даних?"),
  SQL_COMMANDS_SECOND_QUESTION("Яка команда SQL використовується для додавання нового рядка до таблиці?"),
  SQL_COMMANDS_THIRD_QUESTION("Яка команда SQL видаляє таблицю з бази даних?"),
  SQL_COMMANDS_FORTH_QUESTION("Яка з наступних команд SQL використовується для оновлення запису в таблиці?"),
  SQL_COMMANDS_FIFTH_QUESTION("Яка з наступних команд SQL використовується для вибору унікальних значень з колонки?"),
  SQL_COMMANDS_SIXTH_QUESTION("Яка функція SQL використовується для обчислення середнього значення у вибраній колонці?"),
  SQL_COMMANDS_SEVENTH_QUESTION("Яка команда SQL повертає кількість рядків у таблиці?"),

  ;

  private final String name;

  QuestionsNames(String name) {
    this.name = name;
  }

  public String getName() {
    return name;
  }
}
