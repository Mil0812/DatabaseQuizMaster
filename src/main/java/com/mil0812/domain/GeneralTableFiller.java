package com.mil0812.domain;

import com.mil0812.Main;
import com.mil0812.domain.impl.AnswerTableFiller;
import com.mil0812.domain.impl.QuestionTableFiller;
import com.mil0812.domain.impl.ResultTableFiller;
import com.mil0812.domain.impl.SectionTableFiller;
import com.mil0812.domain.impl.TestTableFiller;
import com.mil0812.domain.impl.TestTypeTableFiller;
import com.mil0812.persistence.ApplicationConfig;
import com.mil0812.persistence.entity.impl.Section;
import com.mil0812.persistence.entity.impl.TestType;
import com.mil0812.persistence.unit_of_work.PersistenceContext;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.core.annotation.Order;

/**
 * Клас, що відповідає за ініціалізацію Spring-context
 * та виклик методів заповнення таблиць для кожної сутності,
 * що реалізує інтерфейс "TableFiller"
 */
@ComponentScan(basePackages = "com.mil0812")
public class GeneralTableFiller {
  public static AnnotationConfigApplicationContext springContext;

  public static void fillTables(){
    springContext = new AnnotationConfigApplicationContext(ApplicationConfig.class);
    try {

      List<TableFiller> fillers = springContext.getBeansOfType(TableFiller.class).values().stream()
          .sorted((f1, f2) -> {
            Order o1 = f1.getClass().getAnnotation(Order.class);
            Order o2 = f2.getClass().getAnnotation(Order.class);
            return Integer.compare(
                o1 != null ? o1.value() : Integer.MAX_VALUE,
                o2 != null ? o2.value() : Integer.MAX_VALUE);
          })
          .collect(Collectors.toList());

      for (TableFiller filler : fillers) {
        filler.fill();
      }
    } catch (Exception e) {
      Main.logger.error(STR."Помилка при заповненні таблиць даними: \{e}");
    }
  }
}

