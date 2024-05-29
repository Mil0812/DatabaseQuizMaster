package com.mil0812.presentation.controllers;

import java.util.Objects;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ActiveTestingPageController extends ChildController{

  @FXML
  private Label answerLabel;

  @FXML
  private Label answerResultLabel;

  @FXML
  private ImageView backArrowOnQuiz;

  @FXML
  private Button confirmButton;

  @FXML
  private ImageView correctnessImage;

  @FXML
  private HBox countPanel;

  @FXML
  private Label currentMarkLabel;

  @FXML
  private Pane dialogPane;

  @FXML
  private ImageView girlImage;

  @FXML
  private Button nextQuestionButton;

  @FXML
  private Label questionTextLabel;

  @FXML
  private Label questionsCountLabel;

  @FXML
  private VBox textArea;

  private final MainPageController mainPageController;

  @Autowired
  public ActiveTestingPageController(MainPageController mainPageController) {
    this.mainPageController = mainPageController;
  }

  @FXML
  public void initialize(){
    girlImage.setImage(new Image(Objects.requireNonNull(
        Objects.requireNonNull(getClass().getResource("/com/mil0812/images/girl1.png"))
            .toExternalForm())));
    correctnessImage.setImage(new Image(Objects.requireNonNull(
        Objects.requireNonNull(getClass().getResource("/com/mil0812/images/correct.png"))
            .toExternalForm())));
    backArrowOnQuiz.setImage(new Image(Objects.requireNonNull(
        Objects.requireNonNull(getClass().getResource("/com/mil0812/images/back.png"))
            .toExternalForm())));

    confirmButton.setOnMouseClicked(mouseEvent -> confirmAnswer());
    nextQuestionButton.setOnMouseClicked(mouseEvent -> moveToNextQuestion());
    backArrowOnQuiz.setOnMouseClicked(mouseEvent -> returnToMainPage());
    textArea.setOnMouseClicked(mouseEvent -> pageSwitcher.switchPane(
        "/com/mil0812/view/test-finished-view.fxml"
    ));
  }

  private void returnToMainPage() {

      Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
      alert.setTitle("Підтвердження переходу");
      alert.setHeaderText("Ви впевнені?");
      alert.setContentText("Ви дійсно хочете перейти на головну сторінку?");

      ButtonType yesButton = new ButtonType("Так");
      ButtonType noButton = new ButtonType("Ні");

      alert.getButtonTypes().setAll(yesButton, noButton);

      alert.showAndWait().ifPresent(type -> {
        if (type == yesButton) {

          mainPageController.switchPane("/com/mil0812/view/tests-view.fxml");
          mainPageController.updateLayoutForCollectorPage();
        } else {
          alert.close();
        }
      });

  }

  private void moveToNextQuestion() {
    dialogPane.setVisible(false);
    confirmButton.setVisible(true);
    nextQuestionButton.setVisible(false);
    girlImage.setImage(new Image(Objects.requireNonNull(
        Objects.requireNonNull(getClass().getResource("/com/mil0812/images/girl1.png"))
            .toExternalForm())));
  }

  private void confirmAnswer() {

    dialogPane.setVisible(true);
    confirmButton.setVisible(false);
    nextQuestionButton.setVisible(true);

    //if correct
    girlImage.setImage(new Image(Objects.requireNonNull(
        Objects.requireNonNull(getClass().getResource("/com/mil0812/images/girl2.png"))
            .toExternalForm())));
    answerResultLabel.setText("Чудова робота! Так тримати!");

    //if incorrect
    girlImage.setImage(new Image(Objects.requireNonNull(
        Objects.requireNonNull(getClass().getResource("/com/mil0812/images/girl4.png"))
            .toExternalForm())));
    answerResultLabel.setText("На жаль, відповідь невірна...");
  }

}
