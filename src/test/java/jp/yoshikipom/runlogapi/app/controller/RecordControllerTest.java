package jp.yoshikipom.runlogapi.app.controller;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.time.LocalDate;
import java.util.List;
import jp.yoshikipom.runlogapi.TestUtil;
import jp.yoshikipom.runlogapi.domain.model.Record;
import jp.yoshikipom.runlogapi.domain.service.RecordService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
class RecordControllerTest {

  @Autowired
  private MockMvc mockMvc;
  @MockBean
  private RecordService mockedService;

  @Test
  void getRecords_success() throws Exception {
    when(mockedService.findRecords()).thenReturn(createList());

    String expectedResponse = new TestUtil().readFile("data/app/response/records-200.json");

    this.mockMvc.perform(get("/records"))
        .andExpect(status().is2xxSuccessful())
        .andExpect(content().json(expectedResponse));
  }

  @Test
  void getMonthRecords_success() throws Exception {
    when(mockedService.findRecords()).thenReturn(createList());

    String expectedResponse = new TestUtil().readFile("data/app/response/records-200.json");

    this.mockMvc.perform(get("/records"))
        .andExpect(status().is2xxSuccessful())
        .andExpect(content().json(expectedResponse));
  }

  List<Record> createList() {
    Record record1 = Record.builder()
        .date(LocalDate.of(2020, 5, 29))
        .distance(10)
        .memo("test memo1")
        .build();
    Record record2 = Record.builder()
        .date(LocalDate.of(2020, 5, 30))
        .distance(5)
        .memo("test memo2")
        .build();
    return List.of(record1, record2);
  }
}
