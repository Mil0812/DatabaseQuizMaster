package com.mil0812.presentation.util;

import javafx.scene.Parent;

/**
 * Клас для зберігання результату завантаження FXML, включаючи кореневий вузол і контролер.
 */

public record FXMLLoaderResult(Parent root, Object controller) {

}
