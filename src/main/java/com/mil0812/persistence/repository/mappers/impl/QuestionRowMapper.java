package com.mil0812.persistence.repository.mappers.impl;

import com.mil0812.persistence.entity.impl.Question;
import com.mil0812.persistence.repository.mappers.RowMapper;
import com.mil0812.presentation.util.IdChecker;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;
import org.springframework.stereotype.Component;

@Component
public class QuestionRowMapper extends IdChecker implements RowMapper<Question> {

  @Override
  public Question mapRow(ResultSet rs) throws SQLException {
    UUID id = checkedId(rs);
    UUID testId = UUID.fromString(rs.getString("test_id"));

    return new Question(
        id,
        testId,
        rs.getString("question_text")
    );
  }
}
