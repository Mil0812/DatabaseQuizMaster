package com.mil0812.persistence.repository.impl;

import com.mil0812.persistence.connection.ConnectionManager;
import com.mil0812.persistence.entity.impl.Result;
import com.mil0812.persistence.repository.GenericJdbcRepository;
import com.mil0812.persistence.repository.interfaces.ResultRepository;
import com.mil0812.persistence.repository.interfaces.TableTitles;
import com.mil0812.persistence.repository.mappers.impl.ResultRowMapper;
import java.sql.Timestamp;
import java.time.format.DateTimeFormatter;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;
import org.springframework.stereotype.Repository;

@Repository
public class ResultRepositoryImpl extends GenericJdbcRepository<Result>
    implements ResultRepository {

  public ResultRepositoryImpl(ConnectionManager connectionManager,
      ResultRowMapper resultRowMapper) {
    super(connectionManager, resultRowMapper, TableTitles.RESULT.getName());
  }

  @Override
  public Optional<Result> findByTestId(UUID testId) {
    return findBy("test_id", testId);
  }

  @Override
  public Optional<Result> findByDate(Timestamp date) {
    return findBy("date_of_test", date);
  }

  @Override
  public Set<Result> findAllWhere(String whereQuery) {
    return super.findAllWhere(whereQuery);
  }

  @Override
  protected Map<String, Object> tableValues(Result result) {
    Map<String, Object> values = new LinkedHashMap<>();

    if (Objects.nonNull(result.userId())) {
      values.put("user_id", result.userId());
    }
    if (Objects.nonNull(result.testId())) {
      values.put("test_id", result.testId());
    }
    values.put("grade", result.grade());
    if (Objects.nonNull(result.dateOfTest())) {
      values.put("date_of_test", result.getFormattedDateOfTest());
    }
    return values;
  }
}