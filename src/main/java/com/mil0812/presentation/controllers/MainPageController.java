package com.mil0812.presentation.controllers;

import static com.mil0812.Main.logger;
import static com.mil0812.Main.springContext;

import com.mil0812.Main;
import com.mil0812.presentation.SpringFXMLLoader;
import com.mil0812.presentation.util.FXMLLoaderResult;
import com.mil0812.presentation.util.PageSwitcher;
import java.io.IOException;
import java.util.Objects;
import java.util.Set;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import org.springframework.stereotype.Component;

@Component
public class MainPageController implements PageSwitcher{

  @FXML
  public AnchorPane mainArea;
  @FXML
  public ImageView quizImageView;
  @FXML
  public static ImageView backArrowImage;
  @FXML
  public AnchorPane mainPageArea;
  @FXML
  public AnchorPane topPanelForTestingMode;
  @FXML
  public Label quizTitle;
  @FXML
  public ImageView backArrowOnQuiz;
  @FXML
  public Label quizDescription;
  @FXML
  private AnchorPane bottomPanel;
  @FXML
  private AnchorPane topPanel;

  @FXML
  private ImageView avatarImage;
  @FXML
  private ImageView quizImageSmall;
  @FXML
  private Label userNameLabel;

  @FXML
  private Button mainPageButton;
  @FXML
  private Button resultsPageButton;
  @FXML
  private Button testsPageButton;


  @FXML
  void initialize() {
    try {
      quizImageView.setImage(new Image(Objects.requireNonNull(
          Objects.requireNonNull(getClass().getResource("/com/mil0812/images/quiz.png"))
              .toExternalForm())));
      quizImageSmall.setImage(new Image(Objects.requireNonNull(
          Objects.requireNonNull(getClass().getResource("/com/mil0812/images/quiz.png"))
              .toExternalForm())));
      avatarImage.setImage(new Image(Objects.requireNonNull(
          Objects.requireNonNull(getClass().getResource("/com/mil0812/images/girl_avatar.jpg"))
              .toExternalForm())));
    } catch (Exception e) {
      Main.logger.error(STR."Помилка при завантаженні зображення: \{e}");
    }

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

  public void updateLayoutForEnterAndAuthorizationPages() {
    quizImageView.setVisible(true);
    topPanel.setVisible(false);
    bottomPanel.setVisible(false);
    mainArea.setLayoutY(210.0);
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
  }


  /*public void disableElements(boolean inactive){

    //bottom panel
    bottomPanel.getChildren().forEach(node -> {
      if (node instanceof Button) {
        ((Button) node).setDisable(inactive);
      }
    });

    //top panel
    topPanel.getChildren().forEach(node -> {
      if (node instanceof ImageView) {
        ((ImageView) node).setDisable(inactive);
      }
    });
  }*/
}
