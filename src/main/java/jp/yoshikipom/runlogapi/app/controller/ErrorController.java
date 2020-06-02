package jp.yoshikipom.runlogapi.app.controller;

import javax.servlet.http.HttpServletRequest;
import jp.yoshikipom.runlogapi.app.response.ErrorResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Slf4j
public class ErrorController {

  // 予想外のエラーはここでハンドリング
  @ExceptionHandler(Exception.class)
  @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
  public ErrorResponse handleServerError(HttpServletRequest req, Exception e) {
    log.error("system error.", e);
    return ErrorResponse.builder()
        .status(HttpStatus.INTERNAL_SERVER_ERROR.value())
        .error(HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase())
        .message(e.getMessage())
        .path(req.getRequestURI())
        .build();
  }

}
