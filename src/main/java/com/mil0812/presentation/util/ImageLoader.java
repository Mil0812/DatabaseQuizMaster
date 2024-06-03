package com.mil0812.presentation.util;

import com.mil0812.presentation.controllers.ActiveTestingPageController;
import java.util.Objects;
import javafx.scene.image.Image;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ImageLoader {

  final static Logger logger = LoggerFactory.getLogger(ImageLoader.class);

  public static Image loadImage(String path) {
    try {
      return new Image(Objects.requireNonNull(ImageLoader.class.getResource(path)).toExternalForm());
    } catch (NullPointerException e) {
      logger.error(STR."Картинки не знайдено: \{path}");
      return null;
    }
  }
  public static boolean isResourceAvailable(String path) {
    return ImageLoader.class.getResource(path) != null;
  }

}
