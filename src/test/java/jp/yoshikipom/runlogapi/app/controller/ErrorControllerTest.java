package jp.yoshikipom.runlogapi.app.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
class ErrorControllerTest {

  @Autowired
  private MockMvc mockMvc;

  @Test
  void handleServerError_success() throws Exception {
    mockMvc.perform(get("/error/500"))
        .andExpect(status().isInternalServerError())
        .andExpect(jsonPath("status").value(500))
        .andExpect(jsonPath("error").value("Internal Server Error"))
        .andExpect(jsonPath("message").value("error-message"))
        .andExpect(jsonPath("path").value("/error/500"));
  }
}
