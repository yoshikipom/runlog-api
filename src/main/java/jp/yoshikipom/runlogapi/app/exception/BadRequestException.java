package jp.yoshikipom.runlogapi.app.exception;

public class BadRequestException extends RuntimeException {

  public BadRequestException(String message, Throwable cause) {
    super(message, cause);
  }
}