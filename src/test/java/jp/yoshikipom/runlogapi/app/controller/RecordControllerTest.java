package jp.yoshikipom.runlogapi.app.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import jp.yoshikipom.runlogapi.TestUtil;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
class RecordControllerTest {

  @Autowired
  private MockMvc mockMvc;

  @Test
  void getList_success() throws Exception {
    String expectedResponse = new TestUtil().readFile("data/app/response/records-200.json");

    this.mockMvc.perform(get("/records"))
        .andExpect(status().is2xxSuccessful())
        .andExpect(content().json(expectedResponse));
  }

}