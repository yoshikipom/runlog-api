package jp.yoshikipom.runlogapi.domain.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
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
  @Mock
  private Record dummyRecord;
  @Mock
  private Record createdRecord;

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
  void register_success() {
    when(recordRepo.register(dummyRecord)).thenReturn(createdRecord);
    var actual = target.register(dummyRecord);
    assertEquals(createdRecord, actual);
  }

  @Test
  void unregister_success() {
    LocalDate date = LocalDate.now();
    doNothing().when(recordRepo).unregister(date);
    target.unregister(date);
    verify(recordRepo).unregister(date);
  }

  @Test
  void findMonthRecords_success() {
    when(recordRepo.findRecordsByMonth(2020, 5)).thenReturn(dummyRecords);
    var actual = target.findMonthRecords(2020, 5);
    assertEquals(dummyRecords, actual);
  }

  @Test
  void findDayRecord_success() {
    when(recordRepo.findRecordBybDay(2020, 5, 1)).thenReturn(Optional.of(dummyRecord));
    var actual = target.findDayRecord(2020, 5, 1);
    //noinspection OptionalGetWithoutIsPresent
    assertEquals(dummyRecord, actual.get());
  }
}
