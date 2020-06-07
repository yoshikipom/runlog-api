package jp.yoshikipom.runlogapi.infra.record;

import jp.yoshikipom.runlogapi.domain.model.Record;
import org.modelmapper.AbstractConverter;
import org.springframework.stereotype.Component;

@Component
public class RecordConverter extends AbstractConverter<RecordEntity, Record> {

  @Override
  protected Record convert(RecordEntity source) {
    return Record.builder()
        .id(source.getId())
        .date(source.getDataDate().toLocalDate())
        .distance(source.getDistance())
        .memo(source.getMemo())
        .build();
  }
}
