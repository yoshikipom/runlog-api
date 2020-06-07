package jp.yoshikipom.runlogapi.app.request;

import java.time.LocalDate;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;
import lombok.Data;

@Data
public class RecordRequest {

  @NotNull
  private LocalDate date;
  @PositiveOrZero
  private float distance;
  @NotNull
  private String memo;
}
