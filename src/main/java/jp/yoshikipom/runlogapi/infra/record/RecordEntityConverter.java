package jp.yoshikipom.runlogapi.infra.record;

import java.sql.Date;
import jp.yoshikipom.runlogapi.domain.model.Record;
import org.modelmapper.AbstractConverter;
import org.springframework.stereotype.Component;

@Component
public class RecordEntityConverter extends AbstractConverter<Record, RecordEntity> {

  @Override
  protected RecordEntity convert(Record source) {
    return RecordEntity.builder()
        .dataDate(Date.valueOf(source.getDate()))
        .distance(source.getDistance())
        .memo(source.getMemo())
        .build();
  }
}
