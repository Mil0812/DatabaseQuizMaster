package com.mil0812.persistence.repository.mappers.impl;

import com.mil0812.persistence.entity.impl.Answer;
import com.mil0812.persistence.repository.mappers.RowMapper;
import com.mil0812.presentation.util.IdChecker;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;
import org.springframework.stereotype.Component;

@Component
public class AnswerRowMapper extends IdChecker implements RowMapper<Answer> {

  @Override
  public Answer mapRow(ResultSet rs) throws SQLException {
    UUID id = checkedId(rs);
    UUID questionId = UUID.fromString(rs.getString("question_id"));

    return new Answer(
        id,
        questionId,
        rs.getString("answer_text"),
        rs.getBoolean("correct")
    );
  }
}
