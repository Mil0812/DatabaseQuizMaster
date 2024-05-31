package com.mil0812.persistence.entity.impl;

import com.mil0812.persistence.entity.Entity;
import java.util.UUID;

public record TestType(
    UUID id,
    String name,
    String description,
    int correctAnswerCount
) implements Entity, Comparable<TestType> {

  @Override
  public int compareTo(TestType tt) {
    return this.name.compareTo(tt.name);
  }

  @Override
  public String toString() {
    return STR."TestType{id=\{id}, name='\{name}\{'\''}, description='\{description}\{'\''}, correctAnswerCount=\{correctAnswerCount}\{'}'}";
  }
}