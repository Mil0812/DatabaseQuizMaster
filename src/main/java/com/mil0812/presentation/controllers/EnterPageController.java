package com.mil0812.presentation.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import org.springframework.stereotype.Component;

@Component
public class EnterPageController extends ChildController{

  @FXML
  private Hyperlink signUpLink;

  @FXML
  private TextField loginTextField;

  @FXML
  private PasswordField passwordTextField;

  @FXML
  private Button signInButton;

  private final MainPageController mainPageController;

  public EnterPageController(MainPageController mainPageController) {
    this.mainPageController = mainPageController;
  }

  @FXML
  void initialize(){
    signUpLink.setOnAction(actionEvent -> pageSwitcher.switchPane
        ("/com/mil0812/view/registration-page-view.fxml"));

    signInButton.setOnMouseClicked(mouseEvent -> signIn());
  }

  private void signIn() {
    mainPageController.switchPane("/com/mil0812/view/welcome-view.fxml");
    mainPageController.updateLayoutForCollectorPage();
  }
}
