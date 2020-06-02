package jp.yoshikipom.runlogapi;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/error")
public class ErrorControllerMock {

  @GetMapping("/500")
  public void internalServerError() {
    throw new RuntimeException("error-message");
  }
}
