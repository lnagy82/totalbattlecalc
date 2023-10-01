package hu.totalbattlecalc.repository;

import hu.totalbattlecalc.domain.BattleUnit;
import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 * Spring Data SQL repository for the BattleUnit entity.
 */
@Repository
public interface BattleUnitRepository extends JpaRepository<BattleUnit, Long>, JpaSpecificationExecutor<BattleUnit> {
    @Query(
        value = "select distinct battleUnit from BattleUnit battleUnit left join fetch battleUnit.bonuses",
        countQuery = "select count(distinct battleUnit) from BattleUnit battleUnit"
    )
    Page<BattleUnit> findAllWithEagerRelationships(Pageable pageable);

    @Query("select distinct battleUnit from BattleUnit battleUnit left join fetch battleUnit.bonuses")
    List<BattleUnit> findAllWithEagerRelationships();

    @Query("select battleUnit from BattleUnit battleUnit left join fetch battleUnit.bonuses where battleUnit.id =:id")
    Optional<BattleUnit> findOneWithEagerRelationships(@Param("id") Long id);
}
