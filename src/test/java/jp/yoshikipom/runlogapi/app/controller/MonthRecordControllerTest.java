package jp.yoshikipom.runlogapi.app.controller;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;
import jp.yoshikipom.runlogapi.TestUtil;
import jp.yoshikipom.runlogapi.domain.model.MonthRecord;
import jp.yoshikipom.runlogapi.domain.service.RecordService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
class MonthRecordControllerTest {

  @Autowired
  private MockMvc mockMvc;
  @MockBean
  private RecordService mockedService;

  @Test
  void getMonthRecords__success() throws Exception {
    when(mockedService.findMonthRecords(2020)).thenReturn(createData());

    String expectedResponse = new TestUtil().readFile("data/app/response/month-records-200.json");

    this.mockMvc.perform(get("/monthRecords?year=2020"))
        .andDo(e -> System.out.println(e.getResponse().getContentAsString()))
        .andExpect(status().isOk())
        .andExpect(content().json(expectedResponse));
  }

  private List<MonthRecord> createData() {
    List<MonthRecord> data = new ArrayList<>();
    for (int i = 1; i < 13; i++) {
      data.add(
          MonthRecord.builder()
              .year(2020)
              .month(i)
              .sum((float) (i * 100))
              .build()
      );
    }
    return data;
  }
}
