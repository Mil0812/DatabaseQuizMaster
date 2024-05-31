package com.mil0812.persistence.repository.impl;

import com.mil0812.persistence.connection.ConnectionManager;
import com.mil0812.persistence.entity.impl.Section;
import com.mil0812.persistence.entity.impl.Test;
import com.mil0812.persistence.repository.GenericJdbcRepository;
import com.mil0812.persistence.repository.interfaces.SectionRepository;
import com.mil0812.persistence.repository.interfaces.TableTitles;
import com.mil0812.persistence.repository.mappers.RowMapper;
import com.mil0812.persistence.repository.mappers.impl.TestRowMapper;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;
import org.springframework.stereotype.Repository;

@Repository
public class SectionRepositoryImpl extends GenericJdbcRepository<Section>
    implements SectionRepository {


  public SectionRepositoryImpl
      (ConnectionManager connectionManager,
          RowMapper<Section> rowMapper) {
    super(connectionManager, rowMapper, TableTitles.SECTION.getName());

  }
  @Override
  protected Map<String, Object> tableValues(Section section) {
    Map<String, Object> values = new LinkedHashMap<>();
    if (!section.name().isBlank()) {
      values.put("name", section.name());
    }
    return values;
  }

  @Override
  public Optional<Section> findByName(String name) {
    return findBy("name", name);
  }
}
