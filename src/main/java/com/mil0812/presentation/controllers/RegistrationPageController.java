package com.mil0812.presentation.controllers;

import com.mil0812.persistence.entity.impl.User;
import com.mil0812.persistence.unit_of_work.impl.UserUnitOfWork;
import com.mil0812.presentation.util.AlertUtil;
import com.mil0812.presentation.util.CurrentUser;
import com.mil0812.presentation.util.ImageLoader;
import com.mil0812.presentation.util.PasswordHashing;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RegistrationPageController extends ChildController {

  @FXML
  public ImageView backArrowImageView;
  @FXML
  public Label loginErrorsLabel;
  @FXML
  public Label passwordErrorsLabel;
  @FXML
  public Label allErrorsLabel;
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

  private String firstName;
  private String lastName;
  private String login;
  private String password;
  private byte[] imageData;

  private boolean successValidation;
  private List<String> loginErrorsList;
  private List<String> passwordErrorsList;
  private List<String> allErrorsList;
  private static final Logger logger = LoggerFactory.getLogger(RegistrationPageController.class);

  private final UserUnitOfWork userUnitOfWork;
  private final PasswordHashing passwordHashing;

  @Autowired
  public RegistrationPageController(UserUnitOfWork userUnitOfWork,
      MainPageController mainPageController) {
    this.userUnitOfWork = userUnitOfWork;
    this.passwordHashing = PasswordHashing.getInstance();
    imageData = defaultImageInBytes();
  }

  @FXML
  void initialize() {
    signUpButton.setOnMouseClicked(mouseEvent -> signUp());
    initializeElements();

    Image backArrowImage = ImageLoader.loadImage("/com/mil0812/images/back.png");
    if (backArrowImage != null) {
      backArrowImageView.setImage(backArrowImage);
    }

    backArrowImageView.setOnMouseClicked(mouseEvent -> pageSwitcher.switchPane
        ("/com/mil0812/view/enter-page-view.fxml"));
    // Observer - лістенери на зміну вмісту текст філдів
    loginTextField.textProperty().addListener((obs, oldText, newText) -> {
      initializeElements();
      validateUser();
      loginErrorsLabel.setText(loginErrorsList.toString());
    });

    passwordTextField.textProperty().addListener((obs, oldText, newText) -> {
      initializeElements();
      validateUser();
      passwordErrorsLabel.setText(passwordErrorsList.toString());
    });
  }

  @FXML
  void signUp() {
    validateUser();
    initializeElements();
    checkAllFields();

    if (allErrorsList.isEmpty()) {
      successValidation = true;
    }

    if(successValidation) {
      // Хешування паролю
      String hashedPassword = passwordHashing.hashedPassword(password);

      // Registration
      User user = new User(null, login, hashedPassword, firstName, lastName, imageData);
      userUnitOfWork.registerNew(user);
      userUnitOfWork.commit();

      // Setting current user for other pages
      CurrentUser.getInstance().setCurrentUser(user);

      AlertUtil.showInfoAlert("Успішна реєстрація!");

      // Switching
      pageSwitcher.switchPane("/com/mil0812/view/enter-page-view.fxml");

    } else {
      AlertUtil.showInfoAlert("Помилка при реєстрації...");
    }
  }

  private byte[] readImage(File file){
    try (FileInputStream fis = new FileInputStream(file)) {
      byte[] data = new byte[(int) file.length()];
      fis.read(data);
      return data;
    } catch (IOException e) {
      logger.error(STR."Помилка зчитування байтів із зображення: \{e}");
      return null;
    }
  }

  private byte[] defaultImageInBytes() {
    try (InputStream is = getClass().getResourceAsStream("/com/mil0812/images/girl_avatar.jpg")) {
      if (is == null) {
        throw new IOException("Дефолтне зображення не знайдено...");
      }
      return is.readAllBytes();
    } catch (IOException e) {
      e.printStackTrace();
      return null;
    }
  }

  private void initializeElements() {
    firstName = firstNameTextField.getText();
    lastName = lastNameTextField.getText();
    login = loginTextField.getText();
    password = passwordTextField.getText();
  }

  private void checkAllFields() {
    allErrorsList = new ArrayList<>();

    String emptyFieldsError = "Заповніть, будь ласка, всі поля";
    String errorsInFieldsError = "Будь ласка, заповніть всі поля коректно";
    String userAlreadyExists = "Користувач з таким іменем вже зареєстрований...";

    if (firstName.isBlank() || lastName.isBlank() || login.isEmpty()
        || password.isEmpty()) {
      allErrorsList.add(emptyFieldsError);
    } else {
      allErrorsList.remove(emptyFieldsError);
    }
    if (!(loginErrorsList.isEmpty() && passwordErrorsList.isEmpty())){
      allErrorsList.add(errorsInFieldsError);
      validateUser();
    } else {
      allErrorsList.remove(errorsInFieldsError);
    }
    if (userUnitOfWork.repository.findByLogin(login).isPresent()) {
      allErrorsList.add(userAlreadyExists);
    } else {
      allErrorsList.remove(userAlreadyExists);
    }
    allErrorsLabel.setText(allErrorsList.toString());
  }

  private void validateUser() {
    loginErrorsList = new ArrayList<>();
    passwordErrorsList = new ArrayList<>();

    String onlyLatinPattern = "^[a-zA-Z\\d]+$";
    String passwordPattern = "^(?=.*[A-Z])(?=.*\\d)[A-Za-z\\d]+$";

    String blankFieldError = "Заповніть поле, будь ласка";
    String lessThan5SymbolsError = "Поле має містити більше 5 символів";
    String notOnlyLatinLettersError = "Лише латинські літери";
    String notLikePasswordPattern = "Лише латинські літери + принаймні 1 цифра та 1 велика літера";

    if(!login.isBlank()) {
      loginErrorsList.remove(blankFieldError);
      if (login.length() <= 5) {
        loginErrorsList.add(lessThan5SymbolsError);
      } else {
        loginErrorsList.remove(lessThan5SymbolsError);
      }
      if (!login.matches(onlyLatinPattern)) {
        loginErrorsList.add(notOnlyLatinLettersError);
      } else {
        loginErrorsList.remove(notOnlyLatinLettersError);
      }
    }
    else{
      loginErrorsList.add(blankFieldError);
    }

    if(!password.isBlank()) {
      passwordErrorsList.remove(lessThan5SymbolsError);
      if (password.length() <= 5) {
        passwordErrorsList.add(lessThan5SymbolsError);
      } else {
        passwordErrorsList.remove(lessThan5SymbolsError);
      }

      if (!password.matches(passwordPattern)) {
        passwordErrorsList.add(notLikePasswordPattern);
      } else {
        passwordErrorsList.remove(notLikePasswordPattern);
      }
    }
    else{
      passwordErrorsList.add(blankFieldError);
    }
  }
}
