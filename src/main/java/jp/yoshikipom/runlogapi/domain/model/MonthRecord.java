package jp.yoshikipom.runlogapi.domain.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MonthRecord {

  private Integer year;
  private Integer month;
  @Builder.Default
  private Float sum = 0f;
}
