package hu.totalbattlecalc.service;

import hu.totalbattlecalc.domain.*; // for static metamodels
import hu.totalbattlecalc.domain.Feature;
import hu.totalbattlecalc.repository.FeatureRepository;
import hu.totalbattlecalc.service.criteria.FeatureCriteria;
import hu.totalbattlecalc.service.dto.FeatureDTO;
import hu.totalbattlecalc.service.mapper.FeatureMapper;
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
 * Service for executing complex queries for {@link Feature} entities in the database.
 * The main input is a {@link FeatureCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link FeatureDTO} or a {@link Page} of {@link FeatureDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class FeatureQueryService extends QueryService<Feature> {

    private final Logger log = LoggerFactory.getLogger(FeatureQueryService.class);

    private final FeatureRepository featureRepository;

    private final FeatureMapper featureMapper;

    public FeatureQueryService(FeatureRepository featureRepository, FeatureMapper featureMapper) {
        this.featureRepository = featureRepository;
        this.featureMapper = featureMapper;
    }

    /**
     * Return a {@link List} of {@link FeatureDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<FeatureDTO> findByCriteria(FeatureCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<Feature> specification = createSpecification(criteria);
        return featureMapper.toDto(featureRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link FeatureDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<FeatureDTO> findByCriteria(FeatureCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<Feature> specification = createSpecification(criteria);
        return featureRepository.findAll(specification, page).map(featureMapper::toDto);
    }

    /**
     * Return the number of matching entities in the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(FeatureCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<Feature> specification = createSpecification(criteria);
        return featureRepository.count(specification);
    }

    /**
     * Function to convert {@link FeatureCriteria} to a {@link Specification}
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching {@link Specification} of the entity.
     */
    protected Specification<Feature> createSpecification(FeatureCriteria criteria) {
        Specification<Feature> specification = Specification.where(null);
        if (criteria != null) {
            // This has to be called first, because the distinct method returns null
            if (criteria.getDistinct() != null) {
                specification = specification.and(distinct(criteria.getDistinct()));
            }
            if (criteria.getId() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getId(), Feature_.id));
            }
            if (criteria.getName() != null) {
                specification = specification.and(buildSpecification(criteria.getName(), Feature_.name));
            }
            if (criteria.getValue() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getValue(), Feature_.value));
            }
            if (criteria.getUnit() != null) {
                specification = specification.and(buildSpecification(criteria.getUnit(), Feature_.unit));
            }
            if (criteria.getUnitId() != null) {
                specification =
                    specification.and(
                        buildSpecification(criteria.getUnitId(), root -> root.join(Feature_.units, JoinType.LEFT).get(Unit_.id))
                    );
            }
        }
        return specification;
    }
}
