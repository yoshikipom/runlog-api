package jp.yoshikipom.runlogapi.domain.service;

import java.util.List;
import java.util.Optional;
import jp.yoshikipom.runlogapi.domain.model.Record;
import jp.yoshikipom.runlogapi.domain.repo.RecordRepo;
import org.springframework.stereotype.Service;

@Service
public class RecordService {

  private RecordRepo recordRepo;

  public RecordService(RecordRepo recordRepo) {
    this.recordRepo = recordRepo;
  }

  public List<Record> findRecords() {
    return this.recordRepo.findRecords();
  }

  public List<Record> findMonthRecords(int year, int month) {
    return this.recordRepo.findRecordsByMonth(year, month);
  }

  public Record register(Record record) {
    return this.recordRepo.register(record);
  }

  public void unregister(Integer id) {
    this.recordRepo.unregister(id);
  }

  public Optional<Record> findDayRecord(int year, int month, int day) {
    return this.recordRepo.findRecordBybDay(year, month, day);
  }
}
