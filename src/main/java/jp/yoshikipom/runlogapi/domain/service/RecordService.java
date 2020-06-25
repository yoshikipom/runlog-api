package jp.yoshikipom.runlogapi.domain.service;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import jp.yoshikipom.runlogapi.domain.model.Record;
import jp.yoshikipom.runlogapi.domain.model.YearRecord;
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

  public Map<Integer, YearRecord> findYearRecords(int year) {
    Map<Integer, YearRecord> yearRecords = new HashMap<>();
    for (int month = 1; month < 13; month++) {
      yearRecords.put(month, new YearRecord());
    }

    List<Record> recordsInYear = this.recordRepo.findRecordsByYear(year);
    for (Record record : recordsInYear) {
      int month = record.getDate().getMonthValue();
      float distance = yearRecords.get(month).getSum();
      distance += record.getDistance();
      yearRecords.get(month).setSum(distance);
    }
    return yearRecords;
  }

  public Record register(Record record) {
    return this.recordRepo.register(record);
  }

  public void unregister(LocalDate date) {
    this.recordRepo.unregister(date);
  }

  public Optional<Record> findDayRecord(int year, int month, int day) {
    return this.recordRepo.findRecordBybDay(year, month, day);
  }
}
