package com.mil0812.persistence.repository.interfaces;

import com.mil0812.persistence.entity.impl.Section;
import com.mil0812.persistence.entity.impl.Test;
import com.mil0812.persistence.repository.Repository;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

public interface SectionRepository extends Repository<Section> {

  Optional<Section> findByName(String name);
}
