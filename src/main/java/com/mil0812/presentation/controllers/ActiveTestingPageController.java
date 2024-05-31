package com.mil0812.presentation.controllers;

import com.mil0812.Main;
import com.mil0812.persistence.entity.impl.Answer;
import com.mil0812.persistence.entity.impl.Question;
import com.mil0812.persistence.repository.interfaces.AnswerRepository;
import com.mil0812.persistence.repository.interfaces.QuestionRepository;
import com.mil0812.presentation.util.CurrentTest;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ActiveTestingPageController extends ChildController{

  @FXML
  public FlowPane answersPane;
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
  private VBox quizArea;
  private String correctAnswerImagePath = "/com/mil0812/images/correct.png";
  private String wrongAnswerImagePath = "/com/mil0812/images/wrong.png";

  private final MainPageController mainPageController;
  private final CurrentTest currentTest;

  private final QuestionRepository questionRepository;
  private final AnswerRepository answerRepository;

  @Autowired
  public ActiveTestingPageController(MainPageController mainPageController, CurrentTest currentTest,
      QuestionRepository questionRepository, AnswerRepository answerRepository) {
    this.mainPageController = mainPageController;
    this.currentTest = currentTest;
    this.questionRepository = questionRepository;
    this.answerRepository = answerRepository;
  }

  @FXML
  public void initialize(){
    try {
      girlImage.setImage(new Image(Objects.requireNonNull(
          Objects.requireNonNull(getClass().getResource("/com/mil0812/images/girl1.png"))
              .toExternalForm())));
     /* correctnessImage.setImage(new Image(Objects.requireNonNull(
          Objects.requireNonNull(getClass().getResource(correctAnswerImagePath))
              .toExternalForm())));*/
      backArrowOnQuiz.setImage(new Image(Objects.requireNonNull(
          Objects.requireNonNull(getClass().getResource("/com/mil0812/images/back.png"))
              .toExternalForm())));
    }
    catch (Exception e){
      Main.logger.error(STR."Не вдалося завантажити зображення... \{e}");
    }

    questionsCountLabel.setText(String.valueOf(currentTest.getQuestionCount()));
    loadQuizData();

    confirmButton.setOnMouseClicked(mouseEvent -> confirmAnswer());
    nextQuestionButton.setOnMouseClicked(mouseEvent -> moveToNextQuestion());
    backArrowOnQuiz.setOnMouseClicked(mouseEvent -> returnToMainPage());
    quizArea.setOnMouseClicked(mouseEvent -> pageSwitcher.switchPane(
        "/com/mil0812/view/test-finished-view.fxml"
    ));
  }

  // Метод для завантаження вмісту тесту
  private void loadQuizData() {
    Main.logger.info("Loading started!");
    Set<Question> questions = questionRepository.findAllByTestId(currentTest.getCurrentTest().id());
    Main.logger.info(STR."Questions - \{questions}");
    if (!questions.isEmpty()) {
      Question firstQuestion = questions.iterator().next();
      Main.logger.info(STR."First question -  \{firstQuestion}");
      displayQuestion(firstQuestion);
    }
  }

  // Метод для безпосереднього показу вмісту тестового блоку
  private void displayQuestion(Question question) {
    Main.logger.info("~ displaying");
    questionTextLabel.setText(question.questionText());
    answersPane.getChildren().clear();

    Set<Answer> answers = (Set<Answer>) answerRepository.findAllByQuestionId(question.id());
    for (Answer answer : answers) {
      Label answerLabel = new Label(answer.answerText());
      answerLabel.setFont(new Font(16));
      answerLabel.setWrapText(true);
      answersPane.getChildren().add(answerLabel);
    }
  }

  // Метод повернення на головну при натисненні користувачем на кнопку "BACK"
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

  // Метод переходу до наступного питання. Зміна вмісту у блоці.
  private void moveToNextQuestion() {
    dialogPane.setVisible(false);
    confirmButton.setVisible(true);
    nextQuestionButton.setVisible(false);
    girlImage.setImage(new Image(Objects.requireNonNull(
        Objects.requireNonNull(getClass().getResource("/com/mil0812/images/girl1.png"))
            .toExternalForm())));
  }

  // Метод після натиснення на кнопку "Відповісти"
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
