package com.mil0812.presentation.controllers;

import com.mil0812.Main;
import com.mil0812.persistence.repository.interfaces.UserRepository;
import com.mil0812.presentation.util.PageSwitcher;
import java.util.Objects;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.w3c.dom.events.MouseEvent;

@Component
public class RegistrationPageController extends ChildController {

  @FXML
  public ImageView backArrowImage;
  @FXML
  private TextField firstNameTextField;

  @FXML
  private TextField lastNameTextField;

  @FXML
  private TextField loginTextField;

  @FXML
  private PasswordField passwordTextField;


  @FXML
  private Button signUpButton;

  private final UserRepository userRepository;

  @Autowired
  public RegistrationPageController(UserRepository userRepository) {
    this.userRepository = userRepository;
  }

  @FXML
  void initialize() {
    signUpButton.setOnMouseClicked(mouseEvent -> signUp());

    backArrowImage.setImage(new Image(Objects.requireNonNull(
        Objects.requireNonNull(getClass().getResource("/com/mil0812/images/back.png"))
            .toExternalForm())));
    backArrowImage.setOnMouseClicked(mouseEvent -> pageSwitcher.switchPane
        ("/com/mil0812/view/enter-page-view.fxml"));
  }

  @FXML
  void signUp() {
    pageSwitcher.switchPane("/com/mil0812/view/enter-page-view.fxml");
  }
}
