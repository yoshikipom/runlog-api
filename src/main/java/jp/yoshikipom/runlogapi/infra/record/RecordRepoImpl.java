package jp.yoshikipom.runlogapi.infra.record;

import java.util.List;
import java.util.stream.Collectors;
import jp.yoshikipom.runlogapi.domain.model.Record;
import jp.yoshikipom.runlogapi.domain.repo.RecordRepo;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Repository;

@Repository
public class RecordRepoImpl implements RecordRepo {

  private RecordJpaRepository jpaRepository;
  private ModelMapper modelMapper;

  public RecordRepoImpl(RecordJpaRepository jpaRepository, ModelMapper modelMapper) {
    this.jpaRepository = jpaRepository;
    this.modelMapper = modelMapper;
  }

  @Override
  public List<Record> findRecords() {
    var entityList = this.jpaRepository.findAll();
    return entityList.stream()
        .map(entity -> modelMapper.map(entity, Record.class))
        .collect(Collectors.toList());
  }

}
