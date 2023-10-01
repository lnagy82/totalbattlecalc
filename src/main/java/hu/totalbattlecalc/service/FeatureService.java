package hu.totalbattlecalc.service;

import hu.totalbattlecalc.domain.Feature;
import hu.totalbattlecalc.repository.FeatureRepository;
import hu.totalbattlecalc.service.dto.FeatureDTO;
import hu.totalbattlecalc.service.mapper.FeatureMapper;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link Feature}.
 */
@Service
@Transactional
public class FeatureService {

    private final Logger log = LoggerFactory.getLogger(FeatureService.class);

    private final FeatureRepository featureRepository;

    private final FeatureMapper featureMapper;

    public FeatureService(FeatureRepository featureRepository, FeatureMapper featureMapper) {
        this.featureRepository = featureRepository;
        this.featureMapper = featureMapper;
    }

    /**
     * Save a feature.
     *
     * @param featureDTO the entity to save.
     * @return the persisted entity.
     */
    public FeatureDTO save(FeatureDTO featureDTO) {
        log.debug("Request to save Feature : {}", featureDTO);
        Feature feature = featureMapper.toEntity(featureDTO);
        feature = featureRepository.save(feature);
        return featureMapper.toDto(feature);
    }

    /**
     * Partially update a feature.
     *
     * @param featureDTO the entity to update partially.
     * @return the persisted entity.
     */
    public Optional<FeatureDTO> partialUpdate(FeatureDTO featureDTO) {
        log.debug("Request to partially update Feature : {}", featureDTO);

        return featureRepository
            .findById(featureDTO.getId())
            .map(existingFeature -> {
                featureMapper.partialUpdate(existingFeature, featureDTO);

                return existingFeature;
            })
            .map(featureRepository::save)
            .map(featureMapper::toDto);
    }

    /**
     * Get all the features.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<FeatureDTO> findAll(Pageable pageable) {
        log.debug("Request to get all Features");
        return featureRepository.findAll(pageable).map(featureMapper::toDto);
    }

    /**
     * Get one feature by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<FeatureDTO> findOne(Long id) {
        log.debug("Request to get Feature : {}", id);
        return featureRepository.findById(id).map(featureMapper::toDto);
    }

    /**
     * Delete the feature by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete Feature : {}", id);
        featureRepository.deleteById(id);
    }
}
