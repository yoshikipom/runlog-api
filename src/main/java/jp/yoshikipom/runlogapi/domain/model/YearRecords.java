package jp.yoshikipom.runlogapi.domain.model;

import java.util.HashMap;
import java.util.Map;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class YearRecords {

  @Builder.Default
  private Map<Integer, YearRecord> yearRecords = new HashMap<>();
}
