package com.mil0812.presentation;

import com.mil0812.presentation.util.FXMLLoaderResult;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

/**
 * Фабрика контролерів.
 * Потрібна для налаштування FXMLLoader так, щоб він використовував Spring для створення контролерів.
 */

public class SpringFXMLLoader {
  private final ApplicationContext context;

  public SpringFXMLLoader(ApplicationContext context) {
    this.context = context;
  }

  public FXMLLoaderResult load(URL url) throws IOException {
    FXMLLoader loader = new FXMLLoader(url);
    loader.setControllerFactory(context::getBean);
    Parent root = loader.load();
    Object controller = loader.getController();
    return new FXMLLoaderResult(root, controller);
  }
}
