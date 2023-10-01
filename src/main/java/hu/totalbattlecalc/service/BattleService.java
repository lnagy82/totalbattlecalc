package hu.totalbattlecalc.service;

import hu.totalbattlecalc.domain.Battle;
import hu.totalbattlecalc.repository.BattleRepository;
import hu.totalbattlecalc.service.dto.BattleDTO;
import hu.totalbattlecalc.service.mapper.BattleMapper;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link Battle}.
 */
@Service
@Transactional
public class BattleService {

    private final Logger log = LoggerFactory.getLogger(BattleService.class);

    private final BattleRepository battleRepository;

    private final BattleMapper battleMapper;

    public BattleService(BattleRepository battleRepository, BattleMapper battleMapper) {
        this.battleRepository = battleRepository;
        this.battleMapper = battleMapper;
    }

    /**
     * Save a battle.
     *
     * @param battleDTO the entity to save.
     * @return the persisted entity.
     */
    public BattleDTO save(BattleDTO battleDTO) {
        log.debug("Request to save Battle : {}", battleDTO);
        Battle battle = battleMapper.toEntity(battleDTO);
        battle = battleRepository.save(battle);
        return battleMapper.toDto(battle);
    }

    /**
     * Partially update a battle.
     *
     * @param battleDTO the entity to update partially.
     * @return the persisted entity.
     */
    public Optional<BattleDTO> partialUpdate(BattleDTO battleDTO) {
        log.debug("Request to partially update Battle : {}", battleDTO);

        return battleRepository
            .findById(battleDTO.getId())
            .map(existingBattle -> {
                battleMapper.partialUpdate(existingBattle, battleDTO);

                return existingBattle;
            })
            .map(battleRepository::save)
            .map(battleMapper::toDto);
    }

    /**
     * Get all the battles.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<BattleDTO> findAll(Pageable pageable) {
        log.debug("Request to get all Battles");
        return battleRepository.findAll(pageable).map(battleMapper::toDto);
    }

    /**
     * Get one battle by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<BattleDTO> findOne(Long id) {
        log.debug("Request to get Battle : {}", id);
        return battleRepository.findById(id).map(battleMapper::toDto);
    }

    /**
     * Delete the battle by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete Battle : {}", id);
        battleRepository.deleteById(id);
    }
}
