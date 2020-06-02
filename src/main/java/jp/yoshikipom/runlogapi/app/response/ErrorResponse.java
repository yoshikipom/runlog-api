package jp.yoshikipom.runlogapi.app.response;

import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ErrorResponse {

  @Builder.Default
  private LocalDateTime timestamp = LocalDateTime.now();
  private int status;
  private String error;
  private String message;
  private String path;
}
