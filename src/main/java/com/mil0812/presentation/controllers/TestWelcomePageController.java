package com.mil0812.presentation.controllers;

import com.mil0812.presentation.util.CurrentTest;
import com.mil0812.presentation.util.CurrentUser;
import java.util.Objects;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TestWelcomePageController extends ChildController{
  @FXML
  public ImageView girlImage;
  @FXML
  public AnchorPane welcomePane;
  @FXML
  public Label labelWithWishes;

  @FXML
  private Label labelWithFirstName;

  @FXML
  private Label labelWithQuestionCount;

  @FXML
  private Label labelWithTestTitle;

  private final CurrentTest currentTest;

  public TestWelcomePageController(CurrentTest currentTest) {
    this.currentTest = currentTest;
  }


  @FXML
  public void initialize(){

      girlImage.setImage(new Image(Objects.requireNonNull(
          Objects.requireNonNull(getClass().getResource("/com/mil0812/images/girl1.png"))
              .toExternalForm())));
      welcomePane.setOnMouseClicked(mouseEvent -> pageSwitcher.switchPane
          ("/com/mil0812/view/active-testing-view.fxml"));
      fillLabels();

  }

  private void fillLabels() {
    labelWithFirstName.setText(STR."Вітаю, \{CurrentUser.getInstance().getCurrentUser().firstName()}!");
    labelWithTestTitle.setText(STR."Це тест під назвою '\{currentTest.getTestName()}'");
    labelWithQuestionCount.setText(STR."Він містить \{currentTest.getQuestionCount()} запитань.");
    labelWithWishes.setText("Успіхів у проходженні!");

  }
}
