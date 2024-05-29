package com.mil0812.presentation.controllers;

import com.mil0812.persistence.entity.impl.User;
import java.util.UUID;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import org.springframework.stereotype.Component;

@Component
public class TestsPageController {

  @FXML
  public TableView<User> testsTable;

  @FXML
  public void initialize(){
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