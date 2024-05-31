package com.mil0812.presentation.util;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

public class IdChecker {
  public UUID checkedId (ResultSet rs) throws SQLException {
    String idString = rs.getString("id");
    if (idString == null) {
      throw new SQLException("ID = 0");
    }

    try {
      return UUID.fromString(idString);
    } catch (IllegalArgumentException e) {
      throw new SQLException(STR."Некоректний UUID формат для ID: \{idString}", e);
    }
  }
}
