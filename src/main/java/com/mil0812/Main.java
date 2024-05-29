package com.mil0812;

import atlantafx.base.theme.NordLight;
import com.mil0812.persistence.ApplicationConfig;
import com.mil0812.persistence.connection.ConnectionManager;
import com.mil0812.persistence.connection.DatabaseInitializer;
import com.mil0812.presentation.SpringFXMLLoader;
import com.mil0812.presentation.util.FXMLLoaderResult;
import java.nio.file.Path;
import java.util.Objects;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main extends Application {
  public static AnnotationConfigApplicationContext springContext;
  public final static Logger logger = LoggerFactory.getLogger(Main.class);

    @Override
  public void start(Stage stage) throws Exception {
      // Підключення atlantaFX
      Application.setUserAgentStylesheet(new NordLight().getUserAgentStylesheet());

      var fxmlLoader = new SpringFXMLLoader(springContext);
      stage.getIcons().add(new Image(
          Objects.requireNonNull(getClass().getResourceAsStream("/com/mil0812/images/quiz.png"))));

      var mainFxmlResource = Main.class.getResource("/com/mil0812/view/main-page-view.fxml");
      FXMLLoaderResult result = fxmlLoader.load(mainFxmlResource);
      Parent parent = result.root();

      Scene scene = new Scene(parent, 900, 600);
      scene.getStylesheets().add(Objects.requireNonNull(getClass().getResource("/com/mil0812/style/style.css")).toExternalForm());

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
      launch(args);
    } finally {
      connectionManager.closePool();
    }
  }
}