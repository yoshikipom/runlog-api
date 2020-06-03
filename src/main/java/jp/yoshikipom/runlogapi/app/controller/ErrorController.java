package jp.yoshikipom.runlogapi.app.controller;

import javax.servlet.http.HttpServletRequest;
import jp.yoshikipom.runlogapi.app.exception.BadRequestException;
import jp.yoshikipom.runlogapi.app.exception.NotFoundException;
import jp.yoshikipom.runlogapi.app.response.ErrorResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.NoHandlerFoundException;

@RestControllerAdvice
@Slf4j
public class ErrorController {

  @ExceptionHandler(BadRequestException.class)
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  public ErrorResponse handleBadRequest(HttpServletRequest req, Exception e) {
    log.info("bad request.", e);
    return ErrorResponse.builder()
        .status(HttpStatus.BAD_REQUEST.value())
        .error(HttpStatus.BAD_REQUEST.getReasonPhrase())
        .message(e.getMessage())
        .path(req.getRequestURI())
        .build();
  }

  @ExceptionHandler({NoHandlerFoundException.class, NotFoundException.class})
  @ResponseStatus(HttpStatus.NOT_FOUND)
  public ErrorResponse handleNotFOund(HttpServletRequest req, Exception e) {
    log.info("bad request.", e);
    return ErrorResponse.builder()
        .status(HttpStatus.NOT_FOUND.value())
        .error(HttpStatus.NOT_FOUND.getReasonPhrase())
        .message(e.getMessage())
        .path(req.getRequestURI())
        .build();
  }

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
