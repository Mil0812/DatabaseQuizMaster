package com.mil0812.domain.impl;

import com.mil0812.domain.TableFiller;
import com.mil0812.domain.entities_names.TestTypesNames;
import com.mil0812.persistence.entity.impl.TestType;
import com.mil0812.persistence.unit_of_work.PersistenceContext;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@Order(2)
public class TestTypeTableFiller implements TableFiller {
  private final PersistenceContext persistenceContext;

  public TestTypeTableFiller(PersistenceContext persistenceContext) {
    this.persistenceContext = persistenceContext;
  }
  @Override
  public void fill() {
    persistenceContext.testTypes.registerNew(
        new TestType(
            null,
            TestTypesNames.ONE_RIGHT_ANSWER.getName(),
            "* питання з одним правильним варіантом відповіді",
            2));
    persistenceContext.testTypes.registerNew(
        new TestType(
            null,
            TestTypesNames.MANY_RIGHT_ANSWERS.getName(),
            "* питання з декількома правильними варіантами відповіді",
            1));
    persistenceContext.testTypes.commit();
  }
}
