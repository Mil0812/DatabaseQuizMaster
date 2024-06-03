package com.mil0812.presentation.controllers;

import com.mil0812.presentation.util.CurrentTest;
import com.mil0812.presentation.util.CurrentUser;
import com.mil0812.presentation.util.ImageLoader;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import org.springframework.stereotype.Component;

@Component
public class TestWelcomePageController extends ChildController{
  @FXML
  public ImageView girlImageView;
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

    Image girlImage = ImageLoader.loadImage("/com/mil0812/images/girl1.png");
    if (girlImage != null) {
      girlImageView.setImage(girlImage);
    }
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
