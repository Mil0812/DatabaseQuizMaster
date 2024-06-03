package com.mil0812.presentation.controllers;

import static com.mil0812.Main.springContext;

import com.mil0812.persistence.entity.impl.User;
import com.mil0812.persistence.unit_of_work.impl.UserUnitOfWork;
import com.mil0812.presentation.SpringFXMLLoader;
import com.mil0812.presentation.util.CurrentTest;
import com.mil0812.presentation.util.CurrentUser;
import com.mil0812.presentation.util.FXMLLoaderResult;
import com.mil0812.presentation.util.ImageLoader;
import com.mil0812.presentation.util.PageSwitcher;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.UUID;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MainPageController implements PageSwitcher{

  @FXML
  public AnchorPane mainArea;
  @FXML
  public ImageView quizImageView;
  @FXML
  public AnchorPane mainPageArea;
  @FXML
  public AnchorPane topPanelForTestingMode;
  @FXML
  public Label quizTitle;
  @FXML
  public Label quizDescription;
  @FXML
  private AnchorPane bottomPanel;
  @FXML
  private AnchorPane topPanel;

  @FXML
  private ImageView avatarImageView;
  @FXML
  private ImageView quizImageViewSmall;
  @FXML
  private Label userNameLabel;
  @FXML
  private Button mainPageButton;
  @FXML
  private Button resultsPageButton;
  @FXML
  private Button testsPageButton;
  String selectedAvatarPath;
  private byte[] avatarInBytes;

  private final UserUnitOfWork userUnitOfWork;
  private final CurrentTest currentTest;

  final static Logger logger = LoggerFactory.getLogger(MainPageController.class);


  @Autowired
  public MainPageController(UserUnitOfWork userUnitOfWork, CurrentTest currentTest) {
    this.userUnitOfWork = userUnitOfWork;
    this.currentTest = currentTest;
  }

  @FXML
  void initialize() {
    Image quizImage = ImageLoader.loadImage("/com/mil0812/images/quiz.png");
    if (quizImage != null) {
      quizImageView.setImage(quizImage);
      quizImageViewSmall.setImage(quizImage);
    }
/*
    try {
      quizImageView.setImage(new Image(Objects.requireNonNull(
          Objects.requireNonNull(getClass().getResource("/com/mil0812/images/quiz.png"))
              .toExternalForm())));
      quizImageSmall.setImage(new Image(Objects.requireNonNull(
          Objects.requireNonNull(getClass().getResource("/com/mil0812/images/quiz.png"))
              .toExternalForm())));
    } catch (Exception e) {
      logger.error(STR."Помилка при завантаженні зображення: \{e}");
    }*/

    switchPane("/com/mil0812/view/enter-page-view.fxml");

    try {
      mainPageButton.setOnMouseClicked(mouseEvent -> {
        highlightButton(mainPageButton);
        switchPane("/com/mil0812/view/welcome-view.fxml");
      });

      testsPageButton.setOnMouseClicked(mouseEvent -> {
        highlightButton(testsPageButton);
        switchPane("/com/mil0812/view/tests-view.fxml");

      });

      resultsPageButton.setOnMouseClicked(mouseEvent -> {
        highlightButton(resultsPageButton);
        switchPane("/com/mil0812/view/results-view.fxml");
      });
    }
    catch(Exception e){
      logger.error(STR."Помилка при перемиканні між сторінками... \{e}");
    }

    updateUserData();

    avatarImageView.setOnMouseClicked(mouseEvent -> uploadAvatar());

    //Subscription)
    CurrentUser.getInstance().userAvatarProperty().addListener((observable, oldImage, newImage) -> {
      if (newImage != null) {
        avatarImageView.setImage(newImage);
        //Ім'я
        userNameLabel.setText(STR."\{CurrentUser.getInstance().getCurrentUser()
            .firstName()} \{CurrentUser.getInstance().getCurrentUser().lastName()}");
      }
    });
  }

  private void updateUserData() {
    try {
      Image currentUserAvatar = CurrentUser.getInstance().getCurrentUserAvatar();

      if (currentUserAvatar != null) {
        avatarImageView.setImage(currentUserAvatar);
      }

    } catch (Exception e) {
      logger.error(STR."Помилка при завантаженні користувача...\{e}");
    }
  }

    private void uploadAvatar() {
      FileChooser fileChooser = new FileChooser();
      fileChooser.setTitle("Вибір зображення профілю");
      fileChooser.getExtensionFilters().addAll(
          new ExtensionFilter("Файли зображення", "*.png", "*.jpg")
      );
      File selectedFile = fileChooser.showOpenDialog(avatarImageView.getScene().getWindow());
      if (selectedFile != null) {
        selectedAvatarPath = selectedFile.getPath();
        Image image = new Image(selectedFile.toURI().toString());
        avatarImageView.setImage(image);
        avatarInBytes = readImageToBytes(selectedFile);

        //Оновлення юзера
        String firstName, lastName, login, password;
        UUID id;
        firstName = CurrentUser.getInstance().getCurrentUser().firstName();
        lastName = CurrentUser.getInstance().getCurrentUser().lastName();
        password = CurrentUser.getInstance().getCurrentUser().password();
        login = CurrentUser.getInstance().getCurrentUser().login();
        id = CurrentUser.getInstance().getCurrentUser().id();

        //Реєстрація його в базу даних
        User currentUser = new User(id, login, password, firstName, lastName, avatarInBytes);
        userUnitOfWork.registerModified(currentUser);
        userUnitOfWork.commit();

        //Оновлення стану користувача ("Observer")
        CurrentUser.getInstance().setUserAvatar(image);
      }
    }

  private byte[] readImageToBytes(File file) {
    try (FileInputStream fis = new FileInputStream(file)) {
      byte[] data = new byte[(int) file.length()];
      fis.read(data);
      return data;
    } catch (IOException e) {
      e.printStackTrace();
      return null;
    }
  }

  @Override
  public void switchPane(String fxmlFilePath) {
    try {
      var fxmlLoader = new SpringFXMLLoader(springContext);
      FXMLLoaderResult result = fxmlLoader.load(MainPageController.class.getResource(fxmlFilePath));
      Pane newPage = (Pane) result.root();
      mainArea.getChildren().clear();
      mainArea.getChildren().add(newPage);

      // Передаємо посилання на PageSwitcher до нового контролера
      Object controller = result.controller();
      if (controller instanceof ChildController) {
        ((ChildController) controller).setPageSwitcher(this);
      }
    } catch (IOException e) {
      logger.error(STR."Помилка при переключенні сторінок: \{e}");
    }
  }

  public void highlightButton(Button activeButton){
    mainPageButton.getStyleClass().remove("buttons_inverted");
    testsPageButton.getStyleClass().remove("buttons_inverted");
    resultsPageButton.getStyleClass().remove("buttons_inverted");

    mainPageButton.getStyleClass().add("buttons");
    testsPageButton.getStyleClass().add("buttons");
    resultsPageButton.getStyleClass().add("buttons");

    activeButton.getStyleClass().add("buttons_inverted");

  }
  public void updateLayoutForCollectorPage() {
    quizImageView.setVisible(false);
    topPanel.setVisible(true);
    bottomPanel.setVisible(true);
    mainArea.setLayoutY(100.0);
    topPanelForTestingMode.setVisible(false);
  }

  public void updateLayoutForActiveTestingMode() {
    topPanel.setVisible(false);
    bottomPanel.setVisible(false);
    mainArea.setLayoutY(100.0);
    topPanelForTestingMode.setVisible(true);

    quizTitle.setText(currentTest.getTestName());
    quizDescription.setText(currentTest.getTestDescription());
  }
}
