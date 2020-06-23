package jp.yoshikipom.runlogapi.infra.record;

import java.sql.Date;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "record")
public class RecordEntity {

  @Id
  private Date dataDate;
  private Float distance;
  private String memo;
}
