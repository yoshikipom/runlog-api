package jp.yoshikipom.runlogapi.domain.service;

import java.util.List;
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
}
