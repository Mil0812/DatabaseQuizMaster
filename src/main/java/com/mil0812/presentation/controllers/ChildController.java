package com.mil0812.presentation.controllers;

import com.mil0812.presentation.util.PageSwitcher;

/**
 * Базовий контролер для сторінок,
 * що підтримують перемикання сторінок
 */
public abstract class ChildController {

  protected PageSwitcher pageSwitcher;

  public void setPageSwitcher(PageSwitcher pageSwitcher) {
    this.pageSwitcher = pageSwitcher;
  }
}
