package com.mil0812.domain.entities_names;

public enum SectionsNames {
  ACCESS("Access"),
  QUERIES("Запити"),
  TABLES("Таблиці"),
  SQL("Мова SQL"),
  GENERAL_THEORY("Загальна теорія");

  private final String name;

  SectionsNames(String name) {
    this.name = name;
  }

  public String getName() {
    return name;
  }
}
