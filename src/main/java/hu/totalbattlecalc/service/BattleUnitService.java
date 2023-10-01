package hu.totalbattlecalc.service;

import hu.totalbattlecalc.domain.BattleUnit;
import hu.totalbattlecalc.repository.BattleUnitRepository;
import hu.totalbattlecalc.service.dto.BattleUnitDTO;
import hu.totalbattlecalc.service.mapper.BattleUnitMapper;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link BattleUnit}.
 */
@Service
@Transactional
public class BattleUnitService {

    private final Logger log = LoggerFactory.getLogger(BattleUnitService.class);

    private final BattleUnitRepository battleUnitRepository;

    private final BattleUnitMapper battleUnitMapper;

    public BattleUnitService(BattleUnitRepository battleUnitRepository, BattleUnitMapper battleUnitMapper) {
        this.battleUnitRepository = battleUnitRepository;
        this.battleUnitMapper = battleUnitMapper;
    }

    /**
     * Save a battleUnit.
     *
     * @param battleUnitDTO the entity to save.
     * @return the persisted entity.
     */
    public BattleUnitDTO save(BattleUnitDTO battleUnitDTO) {
        log.debug("Request to save BattleUnit : {}", battleUnitDTO);
        BattleUnit battleUnit = battleUnitMapper.toEntity(battleUnitDTO);
        battleUnit = battleUnitRepository.save(battleUnit);
        return battleUnitMapper.toDto(battleUnit);
    }

    /**
     * Partially update a battleUnit.
     *
     * @param battleUnitDTO the entity to update partially.
     * @return the persisted entity.
     */
    public Optional<BattleUnitDTO> partialUpdate(BattleUnitDTO battleUnitDTO) {
        log.debug("Request to partially update BattleUnit : {}", battleUnitDTO);

        return battleUnitRepository
            .findById(battleUnitDTO.getId())
            .map(existingBattleUnit -> {
                battleUnitMapper.partialUpdate(existingBattleUnit, battleUnitDTO);

                return existingBattleUnit;
            })
            .map(battleUnitRepository::save)
            .map(battleUnitMapper::toDto);
    }

    /**
     * Get all the battleUnits.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<BattleUnitDTO> findAll(Pageable pageable) {
        log.debug("Request to get all BattleUnits");
        return battleUnitRepository.findAll(pageable).map(battleUnitMapper::toDto);
    }

    /**
     * Get all the battleUnits with eager load of many-to-many relationships.
     *
     * @return the list of entities.
     */
    public Page<BattleUnitDTO> findAllWithEagerRelationships(Pageable pageable) {
        return battleUnitRepository.findAllWithEagerRelationships(pageable).map(battleUnitMapper::toDto);
    }

    /**
     * Get one battleUnit by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<BattleUnitDTO> findOne(Long id) {
        log.debug("Request to get BattleUnit : {}", id);
        return battleUnitRepository.findOneWithEagerRelationships(id).map(battleUnitMapper::toDto);
    }

    /**
     * Delete the battleUnit by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete BattleUnit : {}", id);
        battleUnitRepository.deleteById(id);
    }
}
