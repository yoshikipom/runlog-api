package jp.yoshikipom.runlogapi.domain.repo;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import jp.yoshikipom.runlogapi.domain.model.Record;

public interface RecordRepo {

  List<Record> findRecords();

  List<Record> findRecordsByMonth(int year, int month);

  List<Record> findRecordsByYear(int year);

  Record register(Record record);

  void unregister(LocalDate date);

  Optional<Record> findRecordBybDay(int year, int month, int day);
}
