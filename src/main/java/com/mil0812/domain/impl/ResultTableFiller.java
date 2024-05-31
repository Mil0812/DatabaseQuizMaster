package com.mil0812.domain.impl;

import com.mil0812.domain.TableFiller;
import com.mil0812.persistence.unit_of_work.PersistenceContext;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
public class ResultTableFiller implements TableFiller {

  private final PersistenceContext persistenceContext;

  public ResultTableFiller(PersistenceContext persistenceContext) {
    this.persistenceContext = persistenceContext;
  }

  @Override
  public void fill() {
//   persistenceContext.results.registerNew();
  }
}
