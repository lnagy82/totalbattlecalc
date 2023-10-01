package hu.totalbattlecalc.repository;

import hu.totalbattlecalc.domain.Battle;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data SQL repository for the Battle entity.
 */
@SuppressWarnings("unused")
@Repository
public interface BattleRepository extends JpaRepository<Battle, Long>, JpaSpecificationExecutor<Battle> {}
