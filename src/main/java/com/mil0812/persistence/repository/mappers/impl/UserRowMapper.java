package com.mil0812.persistence.repository.mappers.impl;

import com.mil0812.persistence.entity.impl.User;
import com.mil0812.persistence.repository.mappers.RowMapper;
import com.mil0812.presentation.util.IdChecker;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;
import org.springframework.stereotype.Component;

@Component
public class UserRowMapper extends IdChecker implements RowMapper<User> {

  @Override
  public User mapRow(ResultSet rs) throws SQLException {
    UUID id = checkedId(rs);

    return new User(
        id,
        rs.getString("login"),
        rs.getString("password"),
        rs.getString("firstName"),
        rs.getString("lastName"),
        rs.getBytes("avatar")
    );
  }
}
