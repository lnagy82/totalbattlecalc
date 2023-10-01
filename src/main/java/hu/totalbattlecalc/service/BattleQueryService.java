package hu.totalbattlecalc.service;

import hu.totalbattlecalc.domain.*; // for static metamodels
import hu.totalbattlecalc.domain.Battle;
import hu.totalbattlecalc.repository.BattleRepository;
import hu.totalbattlecalc.service.criteria.BattleCriteria;
import hu.totalbattlecalc.service.dto.BattleDTO;
import hu.totalbattlecalc.service.mapper.BattleMapper;
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
 * Service for executing complex queries for {@link Battle} entities in the database.
 * The main input is a {@link BattleCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link BattleDTO} or a {@link Page} of {@link BattleDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class BattleQueryService extends QueryService<Battle> {

    private final Logger log = LoggerFactory.getLogger(BattleQueryService.class);

    private final BattleRepository battleRepository;

    private final BattleMapper battleMapper;

    public BattleQueryService(BattleRepository battleRepository, BattleMapper battleMapper) {
        this.battleRepository = battleRepository;
        this.battleMapper = battleMapper;
    }

    /**
     * Return a {@link List} of {@link BattleDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<BattleDTO> findByCriteria(BattleCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<Battle> specification = createSpecification(criteria);
        return battleMapper.toDto(battleRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link BattleDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<BattleDTO> findByCriteria(BattleCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<Battle> specification = createSpecification(criteria);
        return battleRepository.findAll(specification, page).map(battleMapper::toDto);
    }

    /**
     * Return the number of matching entities in the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(BattleCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<Battle> specification = createSpecification(criteria);
        return battleRepository.count(specification);
    }

    /**
     * Function to convert {@link BattleCriteria} to a {@link Specification}
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching {@link Specification} of the entity.
     */
    protected Specification<Battle> createSpecification(BattleCriteria criteria) {
        Specification<Battle> specification = Specification.where(null);
        if (criteria != null) {
            // This has to be called first, because the distinct method returns null
            if (criteria.getDistinct() != null) {
                specification = specification.and(distinct(criteria.getDistinct()));
            }
            if (criteria.getId() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getId(), Battle_.id));
            }
            if (criteria.getDate() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getDate(), Battle_.date));
            }
        }
        return specification;
    }
}
