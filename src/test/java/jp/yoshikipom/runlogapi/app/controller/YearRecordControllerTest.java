package jp.yoshikipom.runlogapi.app.controller;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.HashMap;
import java.util.Map;
import jp.yoshikipom.runlogapi.TestUtil;
import jp.yoshikipom.runlogapi.domain.model.YearRecord;
import jp.yoshikipom.runlogapi.domain.service.RecordService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
class YearRecordControllerTest {

  @Autowired
  private MockMvc mockMvc;
  @MockBean
  private RecordService mockedService;

  @Test
  void getYearRecords__success() throws Exception {
    when(mockedService.findYearRecords(2020)).thenReturn(createData());

    String expectedResponse = new TestUtil().readFile("data/app/response/year-records-200.json");

    this.mockMvc.perform(get("/yearRecords?year=2020"))
        .andExpect(status().isOk())
        .andExpect(content().json(expectedResponse));
  }

  private Map<Integer, YearRecord> createData() {
    Map<Integer, YearRecord> data = new HashMap<>();
    for (int i = 1; i < 13; i++) {
      data.put(i, YearRecord.builder().sum((float) (i * 100)).build());
    }
    return data;
  }
}
