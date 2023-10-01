package hu.totalbattlecalc.service;

import hu.totalbattlecalc.domain.*; // for static metamodels
import hu.totalbattlecalc.domain.BattleUnit;
import hu.totalbattlecalc.repository.BattleUnitRepository;
import hu.totalbattlecalc.service.criteria.BattleUnitCriteria;
import hu.totalbattlecalc.service.dto.BattleUnitDTO;
import hu.totalbattlecalc.service.mapper.BattleUnitMapper;
import java.util.List;
import javax.persistence.criteria.JoinType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tech.jhipster.service.QueryService;

/**
 * Service for executing complex queries for {@link BattleUnit} entities in the database.
 * The main input is a {@link BattleUnitCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link BattleUnitDTO} or a {@link Page} of {@link BattleUnitDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class BattleUnitQueryService extends QueryService<BattleUnit> {

    private final Logger log = LoggerFactory.getLogger(BattleUnitQueryService.class);

    private final BattleUnitRepository battleUnitRepository;

    private final BattleUnitMapper battleUnitMapper;

    public BattleUnitQueryService(BattleUnitRepository battleUnitRepository, BattleUnitMapper battleUnitMapper) {
        this.battleUnitRepository = battleUnitRepository;
        this.battleUnitMapper = battleUnitMapper;
    }

    /**
     * Return a {@link List} of {@link BattleUnitDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<BattleUnitDTO> findByCriteria(BattleUnitCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<BattleUnit> specification = createSpecification(criteria);
        return battleUnitMapper.toDto(battleUnitRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link BattleUnitDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<BattleUnitDTO> findByCriteria(BattleUnitCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<BattleUnit> specification = createSpecification(criteria);
        return battleUnitRepository.findAll(specification, page).map(battleUnitMapper::toDto);
    }

    /**
     * Return the number of matching entities in the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(BattleUnitCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<BattleUnit> specification = createSpecification(criteria);
        return battleUnitRepository.count(specification);
    }

    /**
     * Function to convert {@link BattleUnitCriteria} to a {@link Specification}
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching {@link Specification} of the entity.
     */
    protected Specification<BattleUnit> createSpecification(BattleUnitCriteria criteria) {
        Specification<BattleUnit> specification = Specification.where(null);
        if (criteria != null) {
            // This has to be called first, because the distinct method returns null
            if (criteria.getDistinct() != null) {
                specification = specification.and(distinct(criteria.getDistinct()));
            }
            if (criteria.getId() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getId(), BattleUnit_.id));
            }
            if (criteria.getNumber() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getNumber(), BattleUnit_.number));
            }
            if (criteria.getUnitId() != null) {
                specification =
                    specification.and(
                        buildSpecification(criteria.getUnitId(), root -> root.join(BattleUnit_.unit, JoinType.LEFT).get(Unit_.id))
                    );
            }
            if (criteria.getBonusId() != null) {
                specification =
                    specification.and(
                        buildSpecification(criteria.getBonusId(), root -> root.join(BattleUnit_.bonuses, JoinType.LEFT).get(Bonus_.id))
                    );
            }
        }
        return specification;
    }
}
