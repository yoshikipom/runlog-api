package jp.yoshikipom.runlogapi.infra.record;

import static java.util.Collections.emptyList;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import jp.yoshikipom.runlogapi.domain.model.Record;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

@SpringBootTest
class RecordRepoImplTest {

  @Autowired
  private RecordRepoImpl target;
  @MockBean
  private RecordJpaRepository jpaRepository;
  @MockBean
  private ModelMapper modelMapper;

  @Mock
  private RecordEntity recordEntity1;
  @Mock
  private RecordEntity recordEntity2;
  @Mock
  private Record record1;
  @Mock
  private Record record2;

  @BeforeEach
  void setUp() {
    when(modelMapper.map(recordEntity1, Record.class)).thenReturn(record1);
    when(modelMapper.map(recordEntity2, Record.class)).thenReturn(record2);
  }

  @Test
  void findRecords_success() {
    when(jpaRepository.findAll()).thenReturn(List.of(recordEntity1, recordEntity2));
    var actual = target.findRecords();
    assertEquals(record1, actual.get(0));
    assertEquals(record2, actual.get(1));
  }

  @Test
  void findRecordsByYear_success() {
    var firstDay = Date.valueOf(LocalDate.of(2020, 1, 1));
    var lastDay = Date.valueOf(LocalDate.of(2020, 12, 31));
    when(jpaRepository.findByDataDateBetween(firstDay, lastDay)).thenReturn(List.of(recordEntity1));

    var actual = target.findRecordsByYear(2020);

    assertEquals(record1, actual.get(0));
    verify(jpaRepository, times(1)).findByDataDateBetween(firstDay, lastDay);
  }

  @Test
  void findRecordsByMonth_success() {
    var firstDay = Date.valueOf(LocalDate.of(2020, 6, 1));
    var lastDay = Date.valueOf(LocalDate.of(2020, 6, 30));
    when(jpaRepository.findByDataDateBetween(firstDay, lastDay)).thenReturn(List.of(recordEntity1));

    var actual = target.findRecordsByMonth(2020, 6);

    assertEquals(record1, actual.get(0));
    verify(jpaRepository, times(1)).findByDataDateBetween(firstDay, lastDay);
  }

  @Test
  void register_success() {
    when(modelMapper.map(record1, RecordEntity.class)).thenReturn(recordEntity1);
    when(jpaRepository.save(recordEntity1)).thenReturn(recordEntity2);
    when(modelMapper.map(recordEntity2, Record.class)).thenReturn(record2);

    var actual = target.register(record1);

    assertEquals(record2, actual);
  }

  @Test
  void unregister_success() {
    LocalDate date = LocalDate.now();
    Date sqlDate = Date.valueOf(date);
    doNothing().when(jpaRepository).deleteById(sqlDate);
    target.unregister(date);
    verify(jpaRepository).deleteById(sqlDate);
  }

  @Test
  void findRecordBybDay_success() {
    when(jpaRepository.findByDataDate(Date.valueOf("2020-1-1"))).thenReturn(List.of(recordEntity1));
    when(modelMapper.map(recordEntity1, Record.class)).thenReturn(record1);
    var actual = target.findRecordBybDay(2020, 1, 1);
    //noinspection OptionalGetWithoutIsPresent
    assertEquals(record1, actual.get());
  }

  @Test
  void findRecordBybDay_invalidDate() {
    var actual = target.findRecordBybDay(12345, 12345, 12345);
    assertTrue(actual.isEmpty());
  }

  @Test
  void findRecordBybDay_notFound() {
    when(jpaRepository.findByDataDate(Date.valueOf("2020-1-1"))).thenReturn(emptyList());
    var actual = target.findRecordBybDay(2020, 1, 1);
    assertTrue(actual.isEmpty());
  }
}
