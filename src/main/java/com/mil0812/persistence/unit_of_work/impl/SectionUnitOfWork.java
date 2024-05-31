package com.mil0812.persistence.unit_of_work.impl;

import com.mil0812.persistence.entity.impl.Section;
import com.mil0812.persistence.repository.interfaces.SectionRepository;
import com.mil0812.persistence.unit_of_work.GeneralUnitOfWork;
import org.springframework.stereotype.Component;

@Component
public class SectionUnitOfWork extends GeneralUnitOfWork<Section> {
  public final SectionRepository sectionRepository;

  protected SectionUnitOfWork(SectionRepository sectionRepository) {
    super(sectionRepository);
    this.sectionRepository = sectionRepository;
  }
}
