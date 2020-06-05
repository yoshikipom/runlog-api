package jp.yoshikipom.runlogapi.infra.record;

import java.sql.Date;
import java.time.LocalDate;
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

  @Override
  public List<Record> findRecordsByMonth(int year, int month) {
    LocalDate firstDay = LocalDate.of(year, month, 1);
    LocalDate lastDay = LocalDate.of(year, month, 1).plusMonths(1).plusDays(-1);
    var entityList = this.jpaRepository
        .findByDataDateBetween(Date.valueOf(firstDay), Date.valueOf(lastDay));
    return entityList.stream()
        .map(entity -> modelMapper.map(entity, Record.class))
        .collect(Collectors.toList());
  }

  @Override
  public Record register(Record record) {
    RecordEntity entity = modelMapper.map(record, RecordEntity.class);
    RecordEntity registeredEntity = this.jpaRepository.save(entity);
    return modelMapper.map(registeredEntity, Record.class);
  }
}
