package jp.yoshikipom.runlogapi.domain.repo;

import java.util.List;
import jp.yoshikipom.runlogapi.domain.model.Record;

public interface RecordRepo {

  List<Record> findRecords();

  List<Record> findRecordsByMonth(int year, int month);
}
