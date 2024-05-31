package com.mil0812.domain.impl;

import com.mil0812.domain.TableFiller;
import com.mil0812.domain.entities_names.SectionsNames;
import com.mil0812.persistence.entity.impl.Section;
import com.mil0812.persistence.unit_of_work.PersistenceContext;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@Order(1)
public class SectionTableFiller implements TableFiller {

  private final PersistenceContext persistenceContext;

  public SectionTableFiller(PersistenceContext persistenceContext) {
    this.persistenceContext = persistenceContext;
  }

  @Override
  public void fill() {
    persistenceContext.sections.registerNew(
        new Section(
            null,
            SectionsNames.ACCESS.getName()
        )
    );
    persistenceContext.sections.registerNew(
        new Section(
            null,
            SectionsNames.QUERIES.getName()
        )
    );
    persistenceContext.sections.registerNew(
        new Section(
            null,
            SectionsNames.TABLES.getName()
        )
    );
    persistenceContext.sections.registerNew(
        new Section(
            null,
            SectionsNames.SQL.getName()
        )
    );
    persistenceContext.sections.registerNew(
        new Section(
            null,
            SectionsNames.GENERAL_THEORY.getName()
        )
    );
    persistenceContext.sections.commit();
  }
}