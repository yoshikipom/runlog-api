package jp.yoshikipom.runlogapi.infra.record;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

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
  private Record result1;
  @Mock
  private Record result2;

  @BeforeEach
  void setUp() {
    when(jpaRepository.findAll()).thenReturn(List.of(recordEntity1, recordEntity2));
    when(modelMapper.map(recordEntity1, Record.class)).thenReturn(result1);
    when(modelMapper.map(recordEntity2, Record.class)).thenReturn(result2);
  }

  @Test
  void findRecords_success() {
    var actual = target.findRecords();
    assertEquals(result1, actual.get(0));
    assertEquals(result2, actual.get(1));
  }
}
