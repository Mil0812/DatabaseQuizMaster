package com.mil0812.persistence.repository.mappers.impl;

import com.mil0812.persistence.entity.impl.TestType;
import com.mil0812.persistence.repository.mappers.RowMapper;
import com.mil0812.presentation.util.IdChecker;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;
import org.springframework.stereotype.Component;

@Component
public class TestTypeRowMapper extends IdChecker implements RowMapper<TestType> {

  @Override
  public TestType mapRow(ResultSet rs) throws SQLException {
    UUID id = checkedId(rs);

    return new TestType(
        id,
        rs.getString("name"),
        rs.getString("description"),
        rs.getInt("correct_answer_count")
    );
  }
}
