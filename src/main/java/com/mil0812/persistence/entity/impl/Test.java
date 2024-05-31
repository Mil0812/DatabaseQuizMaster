package com.mil0812.persistence.entity.impl;

import com.mil0812.persistence.entity.Entity;
import java.util.Objects;
import java.util.Set;
import java.util.UUID;

public record Test(
    UUID id,
    UUID testTypeId,
    UUID sectionId,
    String title,
    int questionCount
) implements Entity, Comparable<Test> {

  @Override
  public int compareTo(Test t) {
    return this.title.compareTo(t.title);
  }

  @Override
  public String toString() {
    return STR."Test{id=\{id}, testTypeId=\{testTypeId}, sectionId=\{sectionId}, title='\{title}\{'\''}, questionCount=\{questionCount}\{'}'}";
  }
}
