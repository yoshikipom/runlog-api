package jp.yoshikipom.runlogapi.infra;

import java.time.LocalDate;
import java.util.List;
import jp.yoshikipom.runlogapi.domain.model.Record;
import jp.yoshikipom.runlogapi.domain.repo.RecordRepo;
import org.springframework.stereotype.Repository;

@Repository
public class RecordRepoDummy implements RecordRepo {


  @Override
  public List<Record> findRecords() {
    var record1 = Record.builder()
        .date(LocalDate.now())
        .distance(10)
        .memo("test memo1")
        .build();

    var record2 = Record.builder()
        .date(LocalDate.now().plusDays(-1))
        .distance(5)
        .memo("test memo2")
        .build();

    return List.of(record1, record2);
  }

}
