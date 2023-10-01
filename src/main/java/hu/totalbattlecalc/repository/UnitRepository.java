package hu.totalbattlecalc.repository;

import hu.totalbattlecalc.domain.Unit;
import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 * Spring Data SQL repository for the Unit entity.
 */
@Repository
public interface UnitRepository extends JpaRepository<Unit, Long>, JpaSpecificationExecutor<Unit> {
    @Query(
        value = "select distinct unit from Unit unit left join fetch unit.features",
        countQuery = "select count(distinct unit) from Unit unit"
    )
    Page<Unit> findAllWithEagerRelationships(Pageable pageable);

    @Query("select distinct unit from Unit unit left join fetch unit.features")
    List<Unit> findAllWithEagerRelationships();

    @Query("select unit from Unit unit left join fetch unit.features where unit.id =:id")
    Optional<Unit> findOneWithEagerRelationships(@Param("id") Long id);
}
