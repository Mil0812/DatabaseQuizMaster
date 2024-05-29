package com.mil0812.presentation.controllers;

import com.mil0812.persistence.entity.impl.User;
import java.util.UUID;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import org.springframework.stereotype.Component;

@Component
public class TestsPageController{

  private final MainPageController mainPageController;

  @FXML
  public TableView<User> testsTable;
  @FXML
  public Button test;

  public TestsPageController(MainPageController mainPageController) {
    this.mainPageController = mainPageController;
  }

  @FXML
  public void initialize(){
    test.setOnMouseClicked(mouseEvent -> {
      mainPageController.switchPane
          ("/com/mil0812/view/test-welcome-view.fxml");
      mainPageController.updateLayoutForActiveTestingMode();
    });
   // fillTheTable();
  }

/*
  private void fillTheTable() {
    testsTable = new TableView<>();
    TableColumn<User, String> firstNameColumn = new TableColumn<>("FirstName");
    firstNameColumn.setCellValueFactory(new PropertyValueFactory<>("firstName"));

    TableColumn<User, String> lastNameColumn = new TableColumn<>("LastName");
    lastNameColumn.setCellValueFactory(new PropertyValueFactory<>("lastName"));

    testsTable.getColumns().add(firstNameColumn);
    testsTable.getColumns().add(lastNameColumn);


    testsTable.getItems().add(new User(
        UUID.fromString("b98f4849-5ad8-4a5b-a20b-ff830f399875"),
        "test1",
        "Test1p",
        "TEST",
        "NICE"
    ));

    testsTable.getItems().add(new User(
        UUID.fromString("9927f5c2-affc-4a7d-bf2d-3bc9924729c1"),
        "test2",
        "Test2p",
        "TEST2",
        "GREAT"
    ));
  }
*/


}