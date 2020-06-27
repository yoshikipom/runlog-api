package jp.yoshikipom.runlogapi.domain.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import jp.yoshikipom.runlogapi.domain.model.MonthRecord;
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

  public List<Record> findRecordsByMonth(int year, int month) {
    return this.recordRepo.findRecordsByMonth(year, month);
  }

  public List<MonthRecord> findMonthRecords(int year) {
    List<MonthRecord> monthRecords = new ArrayList<>();
    for (int month = 1; month < 13; month++) {
      var monthRecord = MonthRecord.builder()
          .year(year)
          .month(month)
          .sum(0f)
          .build();
      monthRecords.add(monthRecord);
    }

    List<Record> recordsInYear = this.recordRepo.findRecordsByYear(year);
    for (Record record : recordsInYear) {
      int month = record.getDate().getMonthValue();
      var targetMonthRecord = monthRecords.get(month - 1);
      float distance = targetMonthRecord.getSum();
      distance += record.getDistance();
      targetMonthRecord.setSum(distance);
    }
    return monthRecords;
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
