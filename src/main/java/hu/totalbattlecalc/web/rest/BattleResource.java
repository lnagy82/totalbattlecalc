package hu.totalbattlecalc.web.rest;

import hu.totalbattlecalc.repository.BattleRepository;
import hu.totalbattlecalc.service.BattleQueryService;
import hu.totalbattlecalc.service.BattleService;
import hu.totalbattlecalc.service.criteria.BattleCriteria;
import hu.totalbattlecalc.service.dto.BattleDTO;
import hu.totalbattlecalc.web.rest.errors.BadRequestAlertException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import tech.jhipster.web.util.HeaderUtil;
import tech.jhipster.web.util.PaginationUtil;
import tech.jhipster.web.util.ResponseUtil;

/**
 * REST controller for managing {@link hu.totalbattlecalc.domain.Battle}.
 */
@RestController
@RequestMapping("/api")
public class BattleResource {

    private final Logger log = LoggerFactory.getLogger(BattleResource.class);

    private static final String ENTITY_NAME = "battle";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final BattleService battleService;

    private final BattleRepository battleRepository;

    private final BattleQueryService battleQueryService;

    public BattleResource(BattleService battleService, BattleRepository battleRepository, BattleQueryService battleQueryService) {
        this.battleService = battleService;
        this.battleRepository = battleRepository;
        this.battleQueryService = battleQueryService;
    }

    /**
     * {@code POST  /battles} : Create a new battle.
     *
     * @param battleDTO the battleDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new battleDTO, or with status {@code 400 (Bad Request)} if the battle has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/battles")
    public ResponseEntity<BattleDTO> createBattle(@RequestBody BattleDTO battleDTO) throws URISyntaxException {
        log.debug("REST request to save Battle : {}", battleDTO);
        if (battleDTO.getId() != null) {
            throw new BadRequestAlertException("A new battle cannot already have an ID", ENTITY_NAME, "idexists");
        }
        BattleDTO result = battleService.save(battleDTO);
        return ResponseEntity
            .created(new URI("/api/battles/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /battles/:id} : Updates an existing battle.
     *
     * @param id the id of the battleDTO to save.
     * @param battleDTO the battleDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated battleDTO,
     * or with status {@code 400 (Bad Request)} if the battleDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the battleDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/battles/{id}")
    public ResponseEntity<BattleDTO> updateBattle(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody BattleDTO battleDTO
    ) throws URISyntaxException {
        log.debug("REST request to update Battle : {}, {}", id, battleDTO);
        if (battleDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, battleDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!battleRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        BattleDTO result = battleService.save(battleDTO);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, battleDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code PATCH  /battles/:id} : Partial updates given fields of an existing battle, field will ignore if it is null
     *
     * @param id the id of the battleDTO to save.
     * @param battleDTO the battleDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated battleDTO,
     * or with status {@code 400 (Bad Request)} if the battleDTO is not valid,
     * or with status {@code 404 (Not Found)} if the battleDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the battleDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/battles/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<BattleDTO> partialUpdateBattle(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody BattleDTO battleDTO
    ) throws URISyntaxException {
        log.debug("REST request to partial update Battle partially : {}, {}", id, battleDTO);
        if (battleDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, battleDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!battleRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<BattleDTO> result = battleService.partialUpdate(battleDTO);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, battleDTO.getId().toString())
        );
    }

    /**
     * {@code GET  /battles} : get all the battles.
     *
     * @param pageable the pagination information.
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of battles in body.
     */
    @GetMapping("/battles")
    public ResponseEntity<List<BattleDTO>> getAllBattles(BattleCriteria criteria, Pageable pageable) {
        log.debug("REST request to get Battles by criteria: {}", criteria);
        Page<BattleDTO> page = battleQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /battles/count} : count all the battles.
     *
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the count in body.
     */
    @GetMapping("/battles/count")
    public ResponseEntity<Long> countBattles(BattleCriteria criteria) {
        log.debug("REST request to count Battles by criteria: {}", criteria);
        return ResponseEntity.ok().body(battleQueryService.countByCriteria(criteria));
    }

    /**
     * {@code GET  /battles/:id} : get the "id" battle.
     *
     * @param id the id of the battleDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the battleDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/battles/{id}")
    public ResponseEntity<BattleDTO> getBattle(@PathVariable Long id) {
        log.debug("REST request to get Battle : {}", id);
        Optional<BattleDTO> battleDTO = battleService.findOne(id);
        return ResponseUtil.wrapOrNotFound(battleDTO);
    }

    /**
     * {@code DELETE  /battles/:id} : delete the "id" battle.
     *
     * @param id the id of the battleDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/battles/{id}")
    public ResponseEntity<Void> deleteBattle(@PathVariable Long id) {
        log.debug("REST request to delete Battle : {}", id);
        battleService.delete(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }
}
