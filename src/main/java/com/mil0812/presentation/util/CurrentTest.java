package com.mil0812.presentation.util;

import com.mil0812.persistence.entity.impl.Test;
import com.mil0812.persistence.entity.impl.TestType;
import com.mil0812.persistence.repository.interfaces.TestTypeRepository;
import java.util.Optional;
import java.util.UUID;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CurrentTest {
  private Test currentTest;
  private final SimpleStringProperty testName = new SimpleStringProperty();
  private final SimpleStringProperty testDescription = new SimpleStringProperty();
  private final SimpleIntegerProperty questionCount = new SimpleIntegerProperty();

  @Autowired
  private TestTypeRepository testTypeRepository;

  private CurrentTest(){
  }

  public Test getCurrentTest() {
    return currentTest;
  }

  public void setCurrentTest(Test test) {
    this.currentTest = test;

    if (test != null) {
      setTestName(test.title());
      setQuestionCount(test.questionCount());
      setTestDescriptionFromTestType(test.testTypeId());
    }
  }

  private void setTestDescriptionFromTestType(UUID testTypeId) {
    if (testTypeId != null) {

      Optional<TestType> testTypeOptional = testTypeRepository.findById(testTypeId);
      if (testTypeOptional.isPresent()) {
        setTestDescription(testTypeOptional.get().description());
      } else {
        setTestDescription("");
      }
    } else {
      setTestDescription("");
    }
  }

  public SimpleStringProperty testNameProperty() {
    return testName;
  }

  public String getTestName() {
    return testName.get();
  }

  public void setTestName(String testName) {
    this.testName.set(testName);
  }

  public SimpleStringProperty testDescriptionProperty() {
    return testDescription;
  }

  public String getTestDescription() {
    return testDescription.get();
  }

  public void setTestDescription(String testDescription) {
    this.testDescription.set(testDescription);
  }

  public SimpleIntegerProperty questionCountProperty() {
    return questionCount;
  }

  public int getQuestionCount() {
    return questionCount.get();
  }

  public void setQuestionCount(int questionCount) {
    this.questionCount.set(questionCount);
  }
}
