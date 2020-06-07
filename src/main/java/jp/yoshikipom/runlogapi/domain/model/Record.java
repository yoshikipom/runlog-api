package jp.yoshikipom.runlogapi.domain.model;

import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Record {

  private Integer id;
  private LocalDate date;
  private float distance;
  private String memo;
}
