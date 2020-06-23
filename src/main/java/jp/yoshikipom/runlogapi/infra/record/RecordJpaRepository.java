package jp.yoshikipom.runlogapi.infra.record;

import java.sql.Date;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RecordJpaRepository extends JpaRepository<RecordEntity, Integer> {

  List<RecordEntity> findAll();

  List<RecordEntity> findByDataDateBetween(Date from, Date to);

  List<RecordEntity> findByDataDate(Date date);

  void deleteByDataDate(Date date);
}
