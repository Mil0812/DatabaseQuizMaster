package com.mil0812.persistence.entity.impl;

import com.mil0812.persistence.entity.Entity;
import java.util.UUID;

public record Question(
    UUID id,
    UUID testId,
    String questionText
) implements Entity, Comparable<Question> {

  @Override
  public int compareTo(Question q) {
    return this.questionText.compareTo(q.questionText);
  }

  @Override
  public String toString() {
    return STR."Question{id=\{id}, testId=\{testId}, questionText='\{questionText}\{'\''}\{'}'}";
  }
}
