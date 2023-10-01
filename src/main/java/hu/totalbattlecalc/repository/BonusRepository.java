package hu.totalbattlecalc.repository;

import hu.totalbattlecalc.domain.Bonus;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data SQL repository for the Bonus entity.
 */
@SuppressWarnings("unused")
@Repository
public interface BonusRepository extends JpaRepository<Bonus, Long>, JpaSpecificationExecutor<Bonus> {}
