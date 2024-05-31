package com.mil0812.persistence.repository.mappers.impl;

import com.mil0812.persistence.entity.impl.Result;
import com.mil0812.persistence.repository.mappers.RowMapper;
import com.mil0812.presentation.util.IdChecker;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.UUID;
import org.springframework.stereotype.Component;

@Component
public class ResultRowMapper extends IdChecker implements RowMapper<Result> {

  private static final DateTimeFormatter formatter = DateTimeFormatter.ISO_LOCAL_DATE_TIME;

  @Override
  public Result mapRow(ResultSet rs) throws SQLException {
    UUID id = checkedId(rs);
    UUID userId = UUID.fromString(rs.getString("user_id"));
    UUID testId = UUID.fromString(rs.getString("test_id"));

    String dateOfTestString = rs.getString("date_of_test");
    LocalDateTime dateOfTest = Result.parseFormattedDateOfTest(dateOfTestString);

    return new Result(
        id,
        userId,
        testId,
        rs.getInt("grade"),
        dateOfTest
    );
  }
}
