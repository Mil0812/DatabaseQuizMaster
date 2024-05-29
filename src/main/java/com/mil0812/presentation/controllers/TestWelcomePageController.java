package com.mil0812.presentation.controllers;

import java.util.Objects;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.shape.Rectangle;
import org.springframework.stereotype.Component;

@Component
public class TestWelcomePageController extends ChildController{
  @FXML
  public ImageView girlImage;
  @FXML
  public AnchorPane welcomePane;

  @FXML
  private Label labelWithFirstName;

  @FXML
  private Label labelWithQuestionCount;

  @FXML
  private Label labelWithTestTitle;

  @FXML
  private Label labelWithTestType;


  @FXML
  public void initialize(){
    girlImage.setImage(new Image(Objects.requireNonNull(
        Objects.requireNonNull(getClass().getResource("/com/mil0812/images/girl1.png"))
            .toExternalForm())));
    welcomePane.setOnMouseClicked(mouseEvent -> pageSwitcher.switchPane
        ("/com/mil0812/view/active-testing-view.fxml"));
  }
}
