package jp.yoshikipom.runlogapi.domain.model;

import java.time.LocalDate;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Record {

  @NotNull
  private LocalDate date;
  @PositiveOrZero
  private float distance;
  @NotNull
  private String memo;
}
