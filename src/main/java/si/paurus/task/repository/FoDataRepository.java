package si.paurus.task.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import si.paurus.task.model.FoData;

@Repository
public interface FoDataRepository extends JpaRepository<FoData, Long> {

    @Query("SELECT MIN(f.dateInsert), MAX(f.dateInsert) FROM FoData f")
    Object[] findMinMaxDateInsert();
}

