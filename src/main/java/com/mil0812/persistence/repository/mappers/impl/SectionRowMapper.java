package com.mil0812.persistence.repository.mappers.impl;


import com.mil0812.persistence.entity.impl.Section;
import com.mil0812.persistence.repository.mappers.RowMapper;
import com.mil0812.presentation.util.IdChecker;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;
import org.springframework.stereotype.Component;

@Component
public class SectionRowMapper extends IdChecker implements RowMapper<Section> {

  @Override
  public Section mapRow(ResultSet rs) throws SQLException {
    UUID id = checkedId(rs);
    return new Section(
        id,
        rs.getString("name")
    );
  }
}
