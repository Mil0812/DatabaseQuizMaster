package com.mil0812.presentation.controllers;

import com.mil0812.Main;
import com.mil0812.persistence.entity.impl.Test;
import com.mil0812.persistence.entity.impl.Section;
import com.mil0812.persistence.entity.impl.TestType;
import com.mil0812.persistence.repository.interfaces.SectionRepository;
import com.mil0812.persistence.repository.interfaces.TestRepository;
import com.mil0812.persistence.repository.interfaces.TestTypeRepository;
import com.mil0812.presentation.util.AlertUtil;
import com.mil0812.presentation.util.CurrentTest;
import com.mil0812.presentation.util.ImageLoader;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

@Component
public class TestsPageController {

  private final MainPageController mainPageController;

  @FXML
  public TableView<Test> testsTable;
  @FXML
  public TableColumn<Test, String> testTypeColumn;
  @FXML
  private TableColumn<Test, String> testTitleColumn;
  @FXML
  private TableColumn<Test, String> testSectionColumn;
  @FXML
  private TableColumn<Test, Integer> questionCountColumn;

  @FXML
  public Button test;
  private final TestRepository testRepository;
  private final SectionRepository sectionRepository;
  private final TestTypeRepository testTypeRepository;
  private final CurrentTest currentTest;

  final static Logger logger = LoggerFactory.getLogger(TestsPageController.class);


  public TestsPageController(MainPageController mainPageController, TestRepository testRepository,
      SectionRepository sectionRepository, TestTypeRepository testTypeRepository,
      CurrentTest currentTest) {
    this.mainPageController = mainPageController;
    this.testRepository = testRepository;
    this.sectionRepository = sectionRepository;
    this.testTypeRepository = testTypeRepository;
    this.currentTest = currentTest;
  }

  @FXML
  public void initialize() {
    initializeTheTable();

  }

  private void initializeTheTable() {
    testTitleColumn.setCellValueFactory(cellData -> new javafx.beans.property.SimpleStringProperty(cellData.getValue().title()));
    testSectionColumn.setCellValueFactory(cellData -> {
      UUID sectionId = cellData.getValue().sectionId();
      Optional<Section> sectionOptional = sectionRepository.findById(sectionId);
      logger.info(STR."Section found by id - \{sectionOptional}");

      return new javafx.beans.property.SimpleStringProperty(sectionOptional.map(Section::name).orElse(""));
    });
    testTypeColumn.setCellValueFactory(cellData -> {
      UUID typeId = cellData.getValue().testTypeId();
      Optional<TestType> testTypeOptional = testTypeRepository.findById(typeId);
      logger.info(STR."Test type found by id - \{testTypeOptional}");

      return new javafx.beans.property.SimpleStringProperty(testTypeOptional.map(TestType::name).orElse(""));
    });
    questionCountColumn.setCellValueFactory(cellData -> new javafx.beans.property.SimpleIntegerProperty(cellData.getValue().questionCount()).asObject());

    testsTable.setItems(getTestData());

    // При виборі рядка з таблиці
    testsTable.setRowFactory(tv -> {
      TableRow<Test> row = new TableRow<>();
      row.setOnMouseClicked(event -> {
        if (!row.isEmpty()) {
          Test rowData = row.getItem();
          currentTest.setCurrentTest(rowData);
          logger.info(STR."Current test - \{ currentTest}");

          startTesting();
        }
      });
      return row;
    });
  }

  private void startTesting() {
    Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
    alert.setTitle("Підтвердження переходу");
    alert.setHeaderText("Ви впевнені?");
    alert.setContentText("Ви дійсно хочете почати проходити тест?");

    ButtonType yesButton = new ButtonType("Так");
    ButtonType noButton = new ButtonType("Ні");

    alert.getButtonTypes().setAll(yesButton, noButton);

    alert.showAndWait().ifPresent(type -> {
      if (type == yesButton) {
        mainPageController.switchPane("/com/mil0812/view/test-welcome-view.fxml");
        mainPageController.updateLayoutForActiveTestingMode();
      } else {
        alert.close();
      }
    });
  }

  private ObservableList<Test> getTestData() {
    Set<Test> tests = testRepository.findAllTests();
    return FXCollections.observableArrayList(tests);
  }
}
