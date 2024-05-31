package com.mil0812.persistence.entity.impl;

import com.mil0812.persistence.entity.Entity;
import java.util.Set;
import java.util.UUID;

public record Section(
    UUID id,
    String name
) implements Entity, Comparable<Section>{

  @Override
  public int compareTo(Section st) {
    return this.name.compareTo(st.name);
  }
}

