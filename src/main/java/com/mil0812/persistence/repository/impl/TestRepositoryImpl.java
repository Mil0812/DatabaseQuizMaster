package com.mil0812.persistence.repository.impl;

import com.mil0812.persistence.connection.ConnectionManager;
import com.mil0812.persistence.entity.impl.Section;
import com.mil0812.persistence.entity.impl.Test;
import com.mil0812.persistence.repository.GenericJdbcRepository;
import com.mil0812.persistence.repository.interfaces.TableTitles;
import com.mil0812.persistence.repository.interfaces.TestRepository;
import com.mil0812.persistence.repository.mappers.impl.SectionRowMapper;
import com.mil0812.persistence.repository.mappers.impl.TestRowMapper;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;
import org.springframework.stereotype.Repository;

@Repository
public class TestRepositoryImpl extends GenericJdbcRepository<Test>
    implements TestRepository {

  public TestRepositoryImpl(ConnectionManager connectionManager,
      TestRowMapper testRowMapper) {
    super(connectionManager, testRowMapper, TableTitles.TEST.getName());
  }

  @Override
  protected Map<String, Object> tableValues(Test test) {
    Map<String, Object> values = new LinkedHashMap<>();

    if (Objects.nonNull(test.testTypeId())) {
      values.put("type_id", test.testTypeId());
    }
    if (Objects.nonNull(test.sectionId())) {
      values.put("section_id", test.sectionId());
    }
    if (!test.title().isBlank()) {
      values.put("title", test.title());
    }
    values.put("question_count", test.questionCount());

    return values;
  }

  @Override
  public Optional<Test> findByTestType(UUID testTypeId) {
    return findBy("type_id", testTypeId);
  }

  @Override
  public Optional<Test> findByTitle(String title) {
    return findBy("title", title);
  }

  @Override
  public Set<Test> findAllTests() {
    return super.findAll();
  }

}
