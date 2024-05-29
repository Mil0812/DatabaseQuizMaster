module com.mil0812 {
  requires javafx.controls;
  requires javafx.fxml;
  requires javafx.web;
  requires atlantafx.base;

  requires org.controlsfx.controls;
  requires com.dlsc.formsfx;
  requires net.synedra.validatorfx;
  requires org.kordamp.ikonli.javafx;
  requires org.kordamp.bootstrapfx.core;
  requires eu.hansolo.tilesfx;
  requires com.almasb.fxgl.all;
  requires spring.core;
  requires spring.beans;
  requires spring.context;
  requires java.sql;
  requires org.slf4j;

  opens com.mil0812 to javafx.fxml;
  opens com.mil0812.persistence to spring.core, spring.beans, spring.context;
  opens com.mil0812.presentation.util to spring.core, spring.beans, spring.context;
  opens com.mil0812.persistence.unit_of_work.impl to spring.core;
  opens com.mil0812.presentation to spring.core, javafx.fxml;
  opens com.mil0812.presentation.controllers to javafx.fxml, spring.core;


  exports com.mil0812;
  exports com.mil0812.persistence.entity;
  exports com.mil0812.persistence.entity.impl;
  exports com.mil0812.persistence.entity.proxy.impl;
  exports com.mil0812.persistence.connection;
  exports com.mil0812.persistence.repository;
  exports com.mil0812.persistence.repository.impl;
  exports com.mil0812.persistence.repository.interfaces;
  exports com.mil0812.persistence.repository.mappers;
  exports com.mil0812.persistence.repository.mappers.impl;
  exports com.mil0812.persistence.unit_of_work;
  exports com.mil0812.persistence.unit_of_work.impl;
  exports com.mil0812.presentation;
  exports com.mil0812.presentation.controllers;
  exports com.mil0812.presentation.util;
}