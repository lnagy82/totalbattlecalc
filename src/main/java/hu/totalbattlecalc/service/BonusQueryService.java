package hu.totalbattlecalc.service;

import hu.totalbattlecalc.domain.*; // for static metamodels
import hu.totalbattlecalc.domain.Bonus;
import hu.totalbattlecalc.repository.BonusRepository;
import hu.totalbattlecalc.service.criteria.BonusCriteria;
import hu.totalbattlecalc.service.dto.BonusDTO;
import hu.totalbattlecalc.service.mapper.BonusMapper;
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
 * Service for executing complex queries for {@link Bonus} entities in the database.
 * The main input is a {@link BonusCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link BonusDTO} or a {@link Page} of {@link BonusDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class BonusQueryService extends QueryService<Bonus> {

    private final Logger log = LoggerFactory.getLogger(BonusQueryService.class);

    private final BonusRepository bonusRepository;

    private final BonusMapper bonusMapper;

    public BonusQueryService(BonusRepository bonusRepository, BonusMapper bonusMapper) {
        this.bonusRepository = bonusRepository;
        this.bonusMapper = bonusMapper;
    }

    /**
     * Return a {@link List} of {@link BonusDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<BonusDTO> findByCriteria(BonusCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<Bonus> specification = createSpecification(criteria);
        return bonusMapper.toDto(bonusRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link BonusDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<BonusDTO> findByCriteria(BonusCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<Bonus> specification = createSpecification(criteria);
        return bonusRepository.findAll(specification, page).map(bonusMapper::toDto);
    }

    /**
     * Return the number of matching entities in the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(BonusCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<Bonus> specification = createSpecification(criteria);
        return bonusRepository.count(specification);
    }

    /**
     * Function to convert {@link BonusCriteria} to a {@link Specification}
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching {@link Specification} of the entity.
     */
    protected Specification<Bonus> createSpecification(BonusCriteria criteria) {
        Specification<Bonus> specification = Specification.where(null);
        if (criteria != null) {
            // This has to be called first, because the distinct method returns null
            if (criteria.getDistinct() != null) {
                specification = specification.and(distinct(criteria.getDistinct()));
            }
            if (criteria.getId() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getId(), Bonus_.id));
            }
            if (criteria.getName() != null) {
                specification = specification.and(buildSpecification(criteria.getName(), Bonus_.name));
            }
            if (criteria.getValue() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getValue(), Bonus_.value));
            }
            if (criteria.getUnit() != null) {
                specification = specification.and(buildSpecification(criteria.getUnit(), Bonus_.unit));
            }
            if (criteria.getBattleUnitId() != null) {
                specification =
                    specification.and(
                        buildSpecification(
                            criteria.getBattleUnitId(),
                            root -> root.join(Bonus_.battleUnits, JoinType.LEFT).get(BattleUnit_.id)
                        )
                    );
            }
        }
        return specification;
    }
}
