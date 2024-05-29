package com.mil0812.presentation.util;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

/**
 * Клас для показу сповіщень
 */
public class AlertUtil {

  public static void showErrorAlert(String message) {
    showAlert(Alert.AlertType.ERROR, "Помилка", message);
  }

  public static void showInfoAlert(String message) {
    showAlert(Alert.AlertType.INFORMATION, "Інформація", message);
  }

  public static void showWarningAlert(String message) {
    showAlert(Alert.AlertType.WARNING, "Попередження", message);
  }

  private static void showAlert(Alert.AlertType alertType, String title, String message) {
    Alert alert = new Alert(alertType);
    alert.setTitle(title);
    alert.setHeaderText(null);
    alert.setContentText(message);
    alert.showAndWait();
  }
}
