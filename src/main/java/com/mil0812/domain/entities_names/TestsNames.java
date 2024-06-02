package com.mil0812.domain.entities_names;

public enum TestsNames {
  GENERAL_ABOUT_DATABASES("Загальні відомості про бази даних"),
  GENERAL_ABOUT_SQL("Основні команди SQL"),
  TABLES_IN_DATABASES("Таблиці бази даних"),
  TABLES_STRUCTURE_ACCESS("Структура таблиць в Access"),
  SEARCHING_AND_FILTERING("Пошук та фільтрація в Access"),
  QUERIES_WITH_SUBQUERIES("Запити з підзапитами");

  private final String name;

  TestsNames(String name) {
    this.name = name;
  }

  public String getName() {
    return name;
  }
}
