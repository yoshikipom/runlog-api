package jp.yoshikipom.runlogapi;

import jp.yoshikipom.runlogapi.app.exception.BadRequestException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/error")
public class ErrorControllerMock {

  @GetMapping("/400")
  public void badRequest() {
    throw new BadRequestException("error-message", new Exception());
  }

  @GetMapping("/500")
  public void internalServerError() {
    throw new RuntimeException("error-message");
  }
}
