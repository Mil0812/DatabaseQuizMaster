package com.mil0812.domain.impl;

import com.mil0812.Main;
import com.mil0812.domain.TableFiller;
import com.mil0812.domain.entities_names.SectionsNames;
import com.mil0812.domain.entities_names.TestTypesNames;
import com.mil0812.domain.entities_names.TestsNames;
import com.mil0812.persistence.entity.impl.Section;
import com.mil0812.persistence.entity.impl.Test;
import com.mil0812.persistence.entity.impl.TestType;
import com.mil0812.persistence.repository.interfaces.SectionRepository;
import com.mil0812.persistence.repository.interfaces.TestTypeRepository;
import com.mil0812.persistence.unit_of_work.PersistenceContext;
import com.mil0812.persistence.unit_of_work.impl.TestTypeUnitOfWork;
import java.util.Optional;
import java.util.UUID;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@Order(3)
public class TestTableFiller implements TableFiller {
  private final TestTypeRepository testTypeRepository;
  private final SectionRepository sectionRepository;
  private final PersistenceContext persistenceContext;

  public TestTableFiller(TestTypeRepository testTypeRepository, SectionRepository sectionRepository, PersistenceContext persistenceContext) {
    this.testTypeRepository = testTypeRepository;
    this.sectionRepository = sectionRepository;
    this.persistenceContext = persistenceContext;
  }

  @Override
  public void fill() {

    persistenceContext.tests.registerNew(
        new Test(
            null,
            currentTestTypeId(TestTypesNames.ONE_RIGHT_ANSWER.getName()),
            currentSectionId(SectionsNames.GENERAL_THEORY.getName()),
            TestsNames.GENERAL_ABOUT_DATABASES.getName(),
            7
        )
    );

    persistenceContext.tests.registerNew(
        new Test(
            null,
            currentTestTypeId(TestTypesNames.MANY_RIGHT_ANSWERS.getName()),
            currentSectionId(SectionsNames.ACCESS.getName()),
            TestsNames.TABLES_STRUCTURE_ACCESS.getName(),
            5
        )
    );
    persistenceContext.tests.commit();
  }

  private UUID currentTestTypeId(String testTypeName){
    Optional<TestType> currentTestType;

    try{
      currentTestType = testTypeRepository.findByName(testTypeName);
      return currentTestType.get().id();
    }
    catch (Exception e){
      Main.logger.error(STR."Не вдалося отримати тип тесту через назву: \{e}");
      return null;
    }
  }

  private UUID currentSectionId(String sectionName){
    Optional<Section> currentSection;

    try{
      currentSection = sectionRepository.findByName(sectionName);
      return currentSection.get().id();
    }
    catch (Exception e){
      Main.logger.error(STR."Не вдалося отримати розділ тесту через назву: \{e}");
      return null;
    }
  }
}
