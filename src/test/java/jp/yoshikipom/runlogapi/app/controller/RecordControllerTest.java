package jp.yoshikipom.runlogapi.app.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import jp.yoshikipom.runlogapi.TestUtil;
import jp.yoshikipom.runlogapi.domain.model.Record;
import jp.yoshikipom.runlogapi.domain.service.RecordService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.MediaType;
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
        .andExpect(status().isOk())
        .andExpect(content().json(expectedResponse));
  }

  @Test
  void getRecords_successByMonth() throws Exception {
    when(mockedService.findMonthRecords(2020, 5)).thenReturn(createList());

    String expectedResponse = new TestUtil().readFile("data/app/response/records-200.json");

    this.mockMvc.perform(get("/records?month=2020-05"))
        .andExpect(status().isOk())
        .andExpect(content().json(expectedResponse));
  }

  @Test
  void getRecords_invalidMonth() throws Exception {
    this.mockMvc.perform(get("/records?month=12345"))
        .andExpect(status().isBadRequest());
  }

  @Test
  void getRecord_success() throws Exception {
    int year = 2020;
    int month = 5;
    int day = 29;
    when(mockedService.findDayRecord(year, month, day)).thenReturn(Optional.of(createOne()));

    String expectedResponse = new TestUtil().readFile("data/app/response/record-200.json");
    String requestUrl = String.format("/records/2020-05-29");

    this.mockMvc.perform(get(requestUrl))
        .andExpect(status().isOk())
        .andExpect(content().json(expectedResponse));
  }

  @Test
  void putRecord_success() throws Exception {
    when(mockedService.register(any())).thenReturn(createOne());

    String request = new TestUtil().readFile("data/app/request/record-put-201.json");

    this.mockMvc.perform(put("/records")
        .content(request)
        .contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON))
        .andExpect(status().isNoContent());
  }

  @Test
  void putRecord_badRequest() throws Exception {
    String request = new TestUtil().readFile("data/app/request/record-put-400.json");

    this.mockMvc.perform(put("/records")
        .content(request)
        .contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON))
        .andExpect(status().isBadRequest());
  }

  @Test
  void deleteRecord_success() throws Exception {
    LocalDate date = LocalDate.of(2020, 5, 29);
    doNothing().when(mockedService).unregister(date);

    this.mockMvc.perform(delete("/records/2020-05-29"))
        .andExpect(status().isNoContent());
  }

  @Test
  void deleteRecord_badRequest() throws Exception {
    LocalDate date = LocalDate.of(2020, 5, 29);
    var error = mock(EmptyResultDataAccessException.class);
    doThrow(error).when(mockedService).unregister(date);

    this.mockMvc.perform(delete("/records/2020-05-29"))
        .andExpect(status().isBadRequest());
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

  Record createOne() {
    return Record.builder()
        .date(LocalDate.of(2020, 5, 29))
        .distance(10)
        .memo("test memo1")
        .build();
  }
}
