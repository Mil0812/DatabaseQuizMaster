package com.mil0812.persistence.repository.impl;

import com.mil0812.persistence.connection.ConnectionManager;
import com.mil0812.persistence.entity.impl.TestType;
import com.mil0812.persistence.repository.GenericJdbcRepository;
import com.mil0812.persistence.repository.interfaces.TableTitles;
import com.mil0812.persistence.repository.interfaces.TestTypeRepository;
import com.mil0812.persistence.repository.mappers.impl.TestTypeRowMapper;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Optional;
import org.springframework.stereotype.Repository;

@Repository
public class TestTypeRepositoryImpl extends GenericJdbcRepository<TestType>
    implements TestTypeRepository {

  public TestTypeRepositoryImpl(
      ConnectionManager connectionManager,
      TestTypeRowMapper testTypeRowMapper) {
    super(connectionManager, testTypeRowMapper, TableTitles.TEST_TYPE.getName());
  }

  @Override
  protected Map<String, Object> tableValues(TestType testType) {
    Map<String, Object> values = new LinkedHashMap<>();

    if (!testType.name().isBlank()) {
      values.put("name", testType.name());
    }
    if (!testType.description().isBlank()) {
      values.put("description", testType.description());
    }
    values.put("correct_answer_count", testType.correctAnswerCount());
    return values;
  }

  @Override
  public Optional<TestType> findByName(String name) {
    return findBy("name", name);
  }
}
