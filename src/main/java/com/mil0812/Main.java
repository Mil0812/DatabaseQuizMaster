package com.mil0812;

import atlantafx.base.theme.NordLight;
import com.mil0812.persistence.ApplicationConfig;
import com.mil0812.persistence.connection.ConnectionManager;
import com.mil0812.persistence.connection.DatabaseInitializer;
import com.mil0812.persistence.entity.impl.Question;
import com.mil0812.persistence.repository.interfaces.QuestionRepository;
import com.mil0812.presentation.SpringFXMLLoader;
import com.mil0812.presentation.util.AlertUtil;
import com.mil0812.presentation.util.FXMLLoaderResult;
import com.mil0812.domain.GeneralTableFiller;
import com.mil0812.presentation.util.ImageLoader;
import java.util.Objects;
import java.util.Set;
import java.util.UUID;
import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main extends Application {
  public static AnnotationConfigApplicationContext springContext;
  /*public final static Logger logger = LoggerFactory.getLogger(Main.class);
*/
  @Override
  public void start(Stage stage) throws Exception {
      // Підключення atlantaFX
      Application.setUserAgentStylesheet(new NordLight().getUserAgentStylesheet());

      var fxmlLoader = new SpringFXMLLoader(springContext);

      String iconPath = "/com/mil0812/images/quiz.png";
    if (ImageLoader.isResourceAvailable(iconPath)) {
      stage.getIcons().add(new Image(Objects.requireNonNull(getClass().getResourceAsStream(iconPath))));
    }

      var mainFxmlResource = Main.class.getResource("/com/mil0812/view/main-page-view.fxml");
      FXMLLoaderResult result = fxmlLoader.load(mainFxmlResource);
      Parent parent = result.root();

      Scene scene = new Scene(parent, 900, 600);
    // Перевірка наявності файлу стилів
    String cssPath = "/com/mil0812/style/style.css";
    if (ImageLoader.isResourceAvailable(cssPath)) {
      scene.getStylesheets().add(Objects.requireNonNull(getClass().getResource(cssPath)).toExternalForm());
    } else {
      AlertUtil.showWarningAlert("Файл стилю не знайдено: " + cssPath);
    }
      stage.setTitle("Database Quiz Master");
      stage.setResizable(false);
      stage.setScene(scene);
      stage.show();
  }

  public static void main(String[] args) {

    springContext = new AnnotationConfigApplicationContext(ApplicationConfig.class);
    var connectionManager = springContext.getBean(ConnectionManager.class);
    var databaseInitializer = springContext.getBean(DatabaseInitializer.class);

    try {
      //databaseInitializer.init();
      //GeneralTableFiller.fillTables();
      launch(args);
    }
    finally {
      connectionManager.closePool();
    }
  }
}