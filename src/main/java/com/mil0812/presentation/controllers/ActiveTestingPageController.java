package com.mil0812.presentation.controllers;

import com.mil0812.Main;
import com.mil0812.persistence.entity.impl.Answer;
import com.mil0812.persistence.entity.impl.Question;
import com.mil0812.persistence.repository.interfaces.AnswerRepository;
import com.mil0812.persistence.repository.interfaces.QuestionRepository;
import com.mil0812.presentation.util.AlertUtil;
import com.mil0812.presentation.util.CurrentTest;
import com.mil0812.presentation.util.ImageLoader;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ActiveTestingPageController extends ChildController {

  @FXML
  public Label currentQuestionLabel;
  @FXML
  public Label pointsLabel;
  @FXML
  private Label answerResultLabel;

  @FXML
  private ImageView backArrowImageView;

  @FXML
  private Button confirmButton;

  @FXML
  private HBox countPanel;

  @FXML
  private Pane dialogPane;

  @FXML
  private ImageView girlImageView;

  @FXML
  private Button nextQuestionButton;

  @FXML
  private Label questionsCountLabel;

  @FXML
  private VBox quizArea;

  private final MainPageController mainPageController;
  private final CurrentTest currentTest;
  private String pathToCorrectnessImage;

  private List<Question> questionList;
  private int currentQuestionIndex;
  private int points;
  private List<FlowPane> selectedAnswers;
  private Answer selectedAnswer;

  private final QuestionRepository questionRepository;
  private final AnswerRepository answerRepository;
  final static Logger logger = LoggerFactory.getLogger(ActiveTestingPageController.class);

  @Autowired
  public ActiveTestingPageController(MainPageController mainPageController, CurrentTest currentTest,
      QuestionRepository questionRepository, AnswerRepository answerRepository) {
    this.mainPageController = mainPageController;
    this.currentTest = currentTest;
    this.questionRepository = questionRepository;
    this.answerRepository = answerRepository;
  }

  @FXML
  public void initialize() {
      Image girlImage = ImageLoader.loadImage("/com/mil0812/images/girl1.png");
      if (girlImage != null) {
        girlImageView.setImage(girlImage);
      }

      Image backImage = ImageLoader.loadImage("/com/mil0812/images/back.png");
      if (backImage != null) {
        backArrowImageView.setImage(backImage);
      }

    questionsCountLabel.setText(String.valueOf(currentTest.getQuestionCount()));
    points=0;
    loadQuizData();

    confirmButton.setOnMouseClicked(mouseEvent -> confirmAnswer());
    nextQuestionButton.setOnMouseClicked(mouseEvent -> moveToNextQuestion());
    backArrowImageView.setOnMouseClicked(mouseEvent -> returnToMainPage());
  }

  // Метод для завантаження вмісту тесту
  private void loadQuizData() {
    Set<Question> questions = questionRepository.findAllByTestId(currentTest.getCurrentTest().id());
    logger.info(STR."Questions - \{questions}");
    if (!questions.isEmpty()) {
      questionList = new ArrayList<>(questions);
      currentQuestionIndex = 0;
      displayQuestion(questionList.get(currentQuestionIndex));
    }
  }

  // Метод для безпосереднього показу вмісту тестового блоку
  private void displayQuestion(Question question) {
    selectedAnswer=null;
    quizArea.getChildren().clear();

    Label questionTextLabel = new Label();
    questionTextLabel.setText(question.questionText());
    questionTextLabel.getStyleClass().add("dark_label");

    quizArea.getChildren().add(questionTextLabel);

    Set<Answer> answers = (Set<Answer>) answerRepository.findAllByQuestionId(question.id());
    for (Answer answer : answers) {

      FlowPane answerPane = new FlowPane();
      answerPane.setAlignment(Pos.CENTER);
      answerPane.setPrefHeight(47.0);
      answerPane.getStyleClass().add("answers_pane");

      Label answerLabel = new Label(answer.answerText());
      answerLabel.getStyleClass().add("light_text");
      answerPane.getChildren().add(answerLabel);

      if (answer.correct()) {
        pathToCorrectnessImage = "/com/mil0812/images/correct.png";
      } else {
        pathToCorrectnessImage = "/com/mil0812/images/wrong.png";
      }
      Image correctnessImage = ImageLoader.loadImage(pathToCorrectnessImage);

      if (correctnessImage != null) {
        ImageView correctnessImageView = new ImageView(correctnessImage);
        correctnessImageView.setFitHeight(20);
        correctnessImageView.setFitWidth(20);
        correctnessImageView.setVisible(false);
        answerPane.getChildren().add(correctnessImageView);
      }


      VBox.setMargin(answerPane, new Insets(5));
      quizArea.getChildren().add(answerPane);

      answerPane.setOnMouseClicked(event -> {
        // if(currentTest.getTestDescription().equals(TestTypesNames.ONE_RIGHT_ANSWER.getName())) {
        for (Node node : quizArea.getChildren()) {
          node.getStyleClass().remove("selected_answers_pane");
        }
        answerPane.getStyleClass().add("selected_answers_pane");
        selectedAnswer = answer;
      });
    }
  }

  // Метод повернення на головну при натисненні користувачем на кнопку "BACK"
  private void returnToMainPage() {

    Alert alert = new Alert(AlertType.CONFIRMATION);
    alert.setTitle("Підтвердження переходу");
    alert.setHeaderText("Ви впевнені?");
    alert.setContentText("Ви дійсно хочете повернутися на головну сторінку?");

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
    Image girlImage = ImageLoader.loadImage("/com/mil0812/images/girl1.png");
    if (girlImage != null) {
      girlImageView.setImage(girlImage);
    }
    showNextQuestion();
  }

  // Метод показу безпосередньо наступного запитання
  private void showNextQuestion() {
    if (currentQuestionIndex < questionList.size() - 1) {
      currentQuestionIndex++;
      displayQuestion(questionList.get(currentQuestionIndex));
      currentQuestionLabel.setText(String.valueOf(currentQuestionIndex + 1));
    } else {
      currentTest.setUserGrade(points);
      pageSwitcher.switchPane(
          "/com/mil0812/view/test-finished-view.fxml");
    }
  }


  // Метод після натиснення на кнопку "Відповісти"
  private void confirmAnswer() {
    if (selectedAnswer != null) {
      dialogPane.setVisible(true);
      confirmButton.setVisible(false);

      if (currentQuestionIndex == questionList.size() - 1) {
        nextQuestionButton.setText("ЗАВЕРШИТИ ТЕСТ");
      }
      nextQuestionButton.setVisible(true);

      makeAllImagesVisible(quizArea);

      if (selectedAnswer.correct()) {
        Image happyGirl = ImageLoader.loadImage("/com/mil0812/images/girl2.png");
        if (happyGirl != null) {
          girlImageView.setImage(happyGirl);
        }
        answerResultLabel.setText("Чудова робота! Так тримати!");
        points += 10;
      } else {
        Image sadGirl = ImageLoader.loadImage("/com/mil0812/images/girl4.png");
        if (sadGirl != null) {
          girlImageView.setImage(sadGirl);
        }
        answerResultLabel.setText("На жаль, відповідь невірна...");
      }
      pointsLabel.setText(STR."Ваш результат: \{points} балів");
    } else {
      AlertUtil.showWarningAlert("Оберіть варіант, будь ласка!");
    }
  }

  // Рекурсивний метод для отримання всіх зображень
  private void makeAllImagesVisible(Node node) {
    if (node instanceof ImageView) {
      node.setVisible(true);
    } else if (node instanceof Parent) {
      for (Node child : ((Parent) node).getChildrenUnmodifiable()) {
        makeAllImagesVisible(child);
      }
    }
  }
}
