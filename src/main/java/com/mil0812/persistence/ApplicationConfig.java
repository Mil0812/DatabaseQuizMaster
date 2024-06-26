package com.mil0812.persistence;

import com.mil0812.presentation.SpringFXMLLoader;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@ComponentScan("com.mil0812")
@PropertySource("classpath:application.properties")
public class ApplicationConfig {
  @Bean
  public SpringFXMLLoader springFXMLLoader(ApplicationContext context) {
    return new SpringFXMLLoader(context);
  }

}
