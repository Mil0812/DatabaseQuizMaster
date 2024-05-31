package com.mil0812.persistence.entity.impl;

import com.mil0812.persistence.entity.Entity;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

public record Result(
    UUID id,
    UUID userId,
    UUID testId,
    int grade,
    LocalDateTime dateOfTest
)
    implements Entity, Comparable<Result> {


  // Конвертація дати для SQLite
  private static final DateTimeFormatter formatter = DateTimeFormatter.ISO_LOCAL_DATE_TIME;

  public String getFormattedDateOfTest() {
    return dateOfTest != null ? dateOfTest.format(formatter) : null;
  }

  public static LocalDateTime parseFormattedDateOfTest(String createdAt) {
    return createdAt != null ? LocalDateTime.parse(createdAt, formatter) : null;
  }

  @Override
  public int compareTo(Result r) {
    return this.testId.compareTo(r.testId);
  }
}
