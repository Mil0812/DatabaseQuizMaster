package com.mil0812.domain.entities_names;

public enum TestTypesNames {
  MANY_RIGHT_ANSWERS("Багато варіантів"),
  ONE_RIGHT_ANSWER("Варіант лише один");


  private final String name;

  TestTypesNames(String name) {
    this.name = name;
  }

  public String getName() {
    return name;
  }
}