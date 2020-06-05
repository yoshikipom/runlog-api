package jp.yoshikipom.runlogapi.app.exception;

public class NotFoundException extends RuntimeException {

  public NotFoundException(String message, Throwable cause) {
    super(message, cause);
  }
}
