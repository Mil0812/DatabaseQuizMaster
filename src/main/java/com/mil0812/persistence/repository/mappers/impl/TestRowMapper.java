package com.mil0812.persistence.repository.mappers.impl;

import com.mil0812.persistence.entity.impl.Test;
import com.mil0812.persistence.repository.mappers.RowMapper;
import com.mil0812.presentation.util.IdChecker;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;
import org.springframework.stereotype.Component;

@Component
public class TestRowMapper extends IdChecker implements RowMapper<Test> {

  @Override
  public Test mapRow(ResultSet rs) throws SQLException {
    UUID id = checkedId(rs);
    UUID testTypeId = UUID.fromString(rs.getString("type_id"));
    UUID sectionId = UUID.fromString(rs.getString("section_id"));
    return new Test(
        id,
        testTypeId,
        sectionId,
        rs.getString("title"),
        rs.getInt("question_count")
    );
  }
}