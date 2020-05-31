package jp.yoshikipom.runlogapi.infra;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RecordJpaRepository extends JpaRepository<RecordEntity, Integer> {

  List<RecordEntity> findAll();
}
