package com.mil0812.presentation.controllers;

import java.util.Objects;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TestFinishedPageController extends ChildController{
  @FXML
  private ImageView girlImage;

  @FXML
  private Label labelWithResultPoints;

  @FXML
  private Label labelWithSummaries;

  @FXML
  private AnchorPane finishPane;

  private final MainPageController mainPageController;

  @Autowired
  public TestFinishedPageController(MainPageController mainPageController) {
    this.mainPageController = mainPageController;
  }

  @FXML
  public void initialize(){
    girlImage.setImage(new Image(Objects.requireNonNull(
        Objects.requireNonNull(getClass().getResource("/com/mil0812/images/girl3.png"))
            .toExternalForm())));
    finishPane.setOnMouseClicked(mouseEvent -> returnToMainPage());
  }

  private void returnToMainPage() {
      mainPageController.switchPane
          ("/com/mil0812/view/tests-view.fxml");
      mainPageController.updateLayoutForCollectorPage();
  }

}
