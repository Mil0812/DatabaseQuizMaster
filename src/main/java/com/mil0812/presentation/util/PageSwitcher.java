package com.mil0812.presentation.util;

/**
 * Інтерфейс для передачі подій між контролерами.
 * Він визначає метод для перемикання сторінок.
 * Це дозволяє будь-якому класу, що його реалізує, виконувати перемикання сторінок.
 */
public interface PageSwitcher {
  void switchPane(String fxmlFilePath);
}
