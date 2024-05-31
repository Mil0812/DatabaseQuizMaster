package com.mil0812.presentation.controllers;

import com.mil0812.persistence.entity.impl.User;
import com.mil0812.persistence.repository.interfaces.UserRepository;
import com.mil0812.presentation.util.AlertUtil;
import com.mil0812.presentation.util.CurrentUser;
import com.mil0812.presentation.util.PasswordHashing;
import java.util.Optional;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class EnterPageController extends ChildController {

  @FXML
  private TextField loginTextField;

  @FXML
  private PasswordField passwordTextField;

  @FXML
  private Button signInButton;

  @FXML
  private Hyperlink signUpLink;
  private User foundUser;
  private final UserRepository userRepository;
  private final PasswordHashing passwordHashing;

  private boolean successValidation = false;

  private final MainPageController mainPageController;

  @Autowired
  public EnterPageController(UserRepository userRepository, MainPageController mainPageController) {
    this.userRepository = userRepository;
    this.mainPageController = mainPageController;
    this.passwordHashing = PasswordHashing.getInstance();
  }

  @FXML
  void initialize() {
    signUpLink.setOnAction(actionEvent -> pageSwitcher.switchPane
        ("/com/mil0812/view/registration-page-view.fxml"));

    signInButton.setOnMouseClicked(mouseEvent -> signIn());
  }

  private void signIn() {
    checkUserData();
    if (successValidation) {
      AlertUtil.showInfoAlert("Успішний вхід в аккаунт!");

      // Setting default user
      CurrentUser.getInstance().setCurrentUser(foundUser);

      mainPageController.switchPane("/com/mil0812/view/welcome-view.fxml");
      mainPageController.updateLayoutForCollectorPage();
    }
  }

  private void checkUserData() {
    String loginText = loginTextField.getText().trim();
    String passwordText = passwordTextField.getText().trim();

    String fieldsAreEmptyError = "Заповніть, будь ласка, всі поля";
    String userDoesNotExistError = "На жаль, користувача з таким іменем не знайдено";
    String incorrectPasswordError = "Неправильний пароль";

    if (!loginText.isEmpty() && !passwordText.isEmpty()) {
      Optional<User> optionalUser = userRepository.findByLogin(loginText);

      if (optionalUser.isPresent()) {
        foundUser = optionalUser.get();
        // Хешування введеного паролю перед порівнянням
        String hashedPassword = passwordHashing.hashedPassword(passwordText);
        if (foundUser.password().equals(hashedPassword)) {
          successValidation = true;
        } else {
          // Incorrect password
          AlertUtil.showWarningAlert(incorrectPasswordError);
        }
      } else {
        // User does not exist
        AlertUtil.showWarningAlert(userDoesNotExistError);
      }
    } else {
      // Fields are empty
      AlertUtil.showWarningAlert(fieldsAreEmptyError);
    }
  }
}
