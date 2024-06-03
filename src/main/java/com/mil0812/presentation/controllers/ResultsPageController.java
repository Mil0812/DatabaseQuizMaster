package com.mil0812.presentation.controllers;

import com.mil0812.Main;
import com.mil0812.persistence.entity.impl.Result;
import com.mil0812.persistence.entity.impl.Test;
import com.mil0812.persistence.entity.impl.TestType;
import com.mil0812.persistence.repository.interfaces.ResultRepository;
import com.mil0812.persistence.repository.interfaces.TestRepository;
import com.mil0812.presentation.util.CurrentUser;
import com.mil0812.presentation.util.ImageLoader;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class ResultsPageController {
  @FXML
  public TableView<Result> resultsTable;
  @FXML
  public TableColumn<Result, String> testTitleColumn;
  @FXML
  public TableColumn<Result, String> dateOfTestColumn;
  @FXML
  public TableColumn<Result, Integer> gradeColumn;
  private final TestRepository testRepository;
  private final ResultRepository resultRepository;

  final static Logger logger = LoggerFactory.getLogger(ResultsPageController.class);


  public ResultsPageController(TestRepository testRepository, ResultRepository resultRepository) {
    this.testRepository = testRepository;
    this.resultRepository = resultRepository;
  }

  @FXML
  public void initialize() {
    initializeTheTable();
  }

  private void initializeTheTable() {

    testTitleColumn.setCellValueFactory(cellData -> {
      UUID testId = cellData.getValue().testId();
      Optional<Test> testOptional = testRepository.findById(testId);
      logger.info(STR."Test found by id - \{testOptional}");

      return new javafx.beans.property.SimpleStringProperty(testOptional.map(Test::title).orElse(""));
        });
    dateOfTestColumn.setCellValueFactory(cellData -> new javafx.beans.property.SimpleStringProperty(cellData.getValue().getFormattedDateOfTest()));
    gradeColumn.setCellValueFactory(cellData -> new javafx.beans.property.SimpleIntegerProperty(cellData.getValue().grade()).asObject());

    resultsTable.setItems(getTestData());
  }

  private ObservableList<Result> getTestData() {
    Set<Result> results = resultRepository.findAllWhere(STR."user_id='\{CurrentUser.getInstance().getCurrentUser().id()}'");
    return FXCollections.observableArrayList(results);
  }

  }
