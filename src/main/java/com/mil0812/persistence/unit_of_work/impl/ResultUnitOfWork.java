package com.mil0812.persistence.unit_of_work.impl;

import com.mil0812.persistence.entity.impl.Result;
import com.mil0812.persistence.repository.interfaces.ResultRepository;
import com.mil0812.persistence.unit_of_work.GeneralUnitOfWork;
import org.springframework.stereotype.Component;

@Component
public class ResultUnitOfWork extends GeneralUnitOfWork<Result> {

  public final ResultRepository repository;

  protected ResultUnitOfWork(ResultRepository resultRepository) {
    super(resultRepository);
    this.repository = resultRepository;
  }
}
