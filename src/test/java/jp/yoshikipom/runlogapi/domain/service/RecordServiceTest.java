package jp.yoshikipom.runlogapi.domain.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.List;
import jp.yoshikipom.runlogapi.domain.model.Record;
import jp.yoshikipom.runlogapi.domain.repo.RecordRepo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

@SpringBootTest
class RecordServiceTest {

  @Autowired
  private RecordService target;
  @MockBean
  private RecordRepo recordRepo;

  @Mock
  private List<Record> dummyRecords;

  @BeforeEach
  void setUp() {
    when(recordRepo.findRecords()).thenReturn(dummyRecords);
  }

  @Test
  void findRecords_success() {
    var actual = target.findRecords();
    assertEquals(dummyRecords, actual);
  }

  @Test
  void findMonthRecords_success() {
    when(recordRepo.findRecordsByMonth(2020, 5)).thenReturn(dummyRecords);
    var actual = target.findMonthRecords(2020, 5);
    assertEquals(dummyRecords, actual);
  }
}
