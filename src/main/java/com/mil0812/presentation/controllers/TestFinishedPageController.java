package com.mil0812.presentation.controllers;

import com.mil0812.persistence.entity.impl.Result;
import com.mil0812.persistence.unit_of_work.PersistenceContext;
import com.mil0812.presentation.util.CurrentTest;
import com.mil0812.presentation.util.CurrentUser;
import com.mil0812.presentation.util.ImageLoader;
import java.time.LocalDateTime;
import java.util.Objects;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TestFinishedPageController extends ChildController{
  @FXML
  private ImageView girlImageView;

  @FXML
  private Label labelWithResultPoints;

  @FXML
  private Label labelWithSummaries;

  @FXML
  private AnchorPane finishPane;

  private int currentUserGrade;
  private int currentMaxPointsCount;
  private String summaryTest;
  private LocalDateTime dateOfTest;

  private final MainPageController mainPageController;
  private final CurrentTest currentTest;
  private final PersistenceContext persistenceContext;

  private static final Logger logger = LoggerFactory.getLogger(TestFinishedPageController.class);



  @Autowired
  public TestFinishedPageController(MainPageController mainPageController, CurrentTest currentTest,
     PersistenceContext persistenceContext) {
    this.mainPageController = mainPageController;
    this.currentTest = currentTest;
    this.persistenceContext = persistenceContext;
  }

  @FXML
  public void initialize(){
    Image girlImage = ImageLoader.loadImage("/com/mil0812/images/girl3.png");
    if (girlImage != null) {
      girlImageView.setImage(girlImage);
    }
    finishPane.setOnMouseClicked(mouseEvent -> returnToMainPage());

    setLabelsText();
    registerResult();
  }

  private void registerResult() {
    dateOfTest = LocalDateTime.now();
    persistenceContext.results.registerNew(
        new Result(
            null,
            CurrentUser.getInstance().getCurrentUser().id(),
            currentTest.getCurrentTest().id(),
            currentUserGrade,
            dateOfTest
        )
    );
    persistenceContext.results.commit();
  }

  private void setLabelsText() {
    currentUserGrade = currentTest.getUserGrade();
    currentMaxPointsCount = currentTest.getMaxPointsCount();

    labelWithResultPoints.setText(STR."Результат: \{currentUserGrade} зі \{currentMaxPointsCount} балів!");

    if(currentUserGrade == currentMaxPointsCount){
      summaryTest = "Ого! Молодець! Ідеш на звання DatabaseMaster!";
    }
    else if(currentUserGrade==0){
      summaryTest = "Оу, тобі б варто підтягнутися... Пробуй ще, удосконалюй свої знання та досягай класних результатів!";
    }
    else{
      summaryTest = "Нот бед! Продовжуй в тому ж дусі та удосконалюй свої результати ще більше!";
    }
    labelWithSummaries.setText(summaryTest);

  }

  private void returnToMainPage() {
      mainPageController.switchPane
          ("/com/mil0812/view/tests-view.fxml");
      mainPageController.updateLayoutForCollectorPage();
  }
}
