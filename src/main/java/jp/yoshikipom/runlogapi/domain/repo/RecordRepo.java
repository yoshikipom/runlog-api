package jp.yoshikipom.runlogapi.domain.repo;

import java.util.List;
import java.util.Optional;
import jp.yoshikipom.runlogapi.domain.model.Record;

public interface RecordRepo {

  List<Record> findRecords();

  List<Record> findRecordsByMonth(int year, int month);

  Record register(Record record);

  void unregister(Integer id);

  Optional<Record> findRecordBybDay(int year, int month, int day);
}
