package jp.yoshikipom.runlogapi.infra.record;

import java.sql.Date;
import java.time.DateTimeException;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import jp.yoshikipom.runlogapi.domain.model.Record;
import jp.yoshikipom.runlogapi.domain.repo.RecordRepo;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Repository;

@Repository
@Slf4j
public class RecordRepoImpl implements RecordRepo {

  private final RecordJpaRepository jpaRepository;
  private final ModelMapper modelMapper;

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
  public List<Record> findRecordsByYear(int year) {
    LocalDate firstDay = LocalDate.of(year, 1, 1);
    LocalDate lastDay = LocalDate.of(year, 1, 1).plusYears(1).plusDays(-1);
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

  @Override
  public void unregister(LocalDate date) {
    Date sqlDate = Date.valueOf(date);
    this.jpaRepository.deleteById(sqlDate);
  }

  @Override
  public Optional<Record> findRecordBybDay(int year, int month, int day) {
    LocalDate date;
    try {
      date = LocalDate.of(year, month, day);
    } catch (DateTimeException e) {
      log.info(e.getMessage(), e);
      return Optional.empty();
    }
    List<RecordEntity> recordEntities = this.jpaRepository.findByDataDate(Date.valueOf(date));
    if (recordEntities.isEmpty()) {
      return Optional.empty();
    }

    // RecordEntityはyear, month, dayでuniqueなので1件目しか存在しない
    Record record = modelMapper.map(recordEntities.get(0), Record.class);
    return Optional.of(record);
  }
}
