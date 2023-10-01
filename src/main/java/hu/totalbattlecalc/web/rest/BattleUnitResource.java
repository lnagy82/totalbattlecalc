package hu.totalbattlecalc.web.rest;

import hu.totalbattlecalc.repository.BattleUnitRepository;
import hu.totalbattlecalc.service.BattleUnitQueryService;
import hu.totalbattlecalc.service.BattleUnitService;
import hu.totalbattlecalc.service.criteria.BattleUnitCriteria;
import hu.totalbattlecalc.service.dto.BattleUnitDTO;
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
 * REST controller for managing {@link hu.totalbattlecalc.domain.BattleUnit}.
 */
@RestController
@RequestMapping("/api")
public class BattleUnitResource {

    private final Logger log = LoggerFactory.getLogger(BattleUnitResource.class);

    private static final String ENTITY_NAME = "battleUnit";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final BattleUnitService battleUnitService;

    private final BattleUnitRepository battleUnitRepository;

    private final BattleUnitQueryService battleUnitQueryService;

    public BattleUnitResource(
        BattleUnitService battleUnitService,
        BattleUnitRepository battleUnitRepository,
        BattleUnitQueryService battleUnitQueryService
    ) {
        this.battleUnitService = battleUnitService;
        this.battleUnitRepository = battleUnitRepository;
        this.battleUnitQueryService = battleUnitQueryService;
    }

    /**
     * {@code POST  /battle-units} : Create a new battleUnit.
     *
     * @param battleUnitDTO the battleUnitDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new battleUnitDTO, or with status {@code 400 (Bad Request)} if the battleUnit has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/battle-units")
    public ResponseEntity<BattleUnitDTO> createBattleUnit(@RequestBody BattleUnitDTO battleUnitDTO) throws URISyntaxException {
        log.debug("REST request to save BattleUnit : {}", battleUnitDTO);
        if (battleUnitDTO.getId() != null) {
            throw new BadRequestAlertException("A new battleUnit cannot already have an ID", ENTITY_NAME, "idexists");
        }
        BattleUnitDTO result = battleUnitService.save(battleUnitDTO);
        return ResponseEntity
            .created(new URI("/api/battle-units/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /battle-units/:id} : Updates an existing battleUnit.
     *
     * @param id the id of the battleUnitDTO to save.
     * @param battleUnitDTO the battleUnitDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated battleUnitDTO,
     * or with status {@code 400 (Bad Request)} if the battleUnitDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the battleUnitDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/battle-units/{id}")
    public ResponseEntity<BattleUnitDTO> updateBattleUnit(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody BattleUnitDTO battleUnitDTO
    ) throws URISyntaxException {
        log.debug("REST request to update BattleUnit : {}, {}", id, battleUnitDTO);
        if (battleUnitDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, battleUnitDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!battleUnitRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        BattleUnitDTO result = battleUnitService.save(battleUnitDTO);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, battleUnitDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code PATCH  /battle-units/:id} : Partial updates given fields of an existing battleUnit, field will ignore if it is null
     *
     * @param id the id of the battleUnitDTO to save.
     * @param battleUnitDTO the battleUnitDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated battleUnitDTO,
     * or with status {@code 400 (Bad Request)} if the battleUnitDTO is not valid,
     * or with status {@code 404 (Not Found)} if the battleUnitDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the battleUnitDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/battle-units/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<BattleUnitDTO> partialUpdateBattleUnit(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody BattleUnitDTO battleUnitDTO
    ) throws URISyntaxException {
        log.debug("REST request to partial update BattleUnit partially : {}, {}", id, battleUnitDTO);
        if (battleUnitDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, battleUnitDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!battleUnitRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<BattleUnitDTO> result = battleUnitService.partialUpdate(battleUnitDTO);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, battleUnitDTO.getId().toString())
        );
    }

    /**
     * {@code GET  /battle-units} : get all the battleUnits.
     *
     * @param pageable the pagination information.
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of battleUnits in body.
     */
    @GetMapping("/battle-units")
    public ResponseEntity<List<BattleUnitDTO>> getAllBattleUnits(BattleUnitCriteria criteria, Pageable pageable) {
        log.debug("REST request to get BattleUnits by criteria: {}", criteria);
        Page<BattleUnitDTO> page = battleUnitQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /battle-units/count} : count all the battleUnits.
     *
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the count in body.
     */
    @GetMapping("/battle-units/count")
    public ResponseEntity<Long> countBattleUnits(BattleUnitCriteria criteria) {
        log.debug("REST request to count BattleUnits by criteria: {}", criteria);
        return ResponseEntity.ok().body(battleUnitQueryService.countByCriteria(criteria));
    }

    /**
     * {@code GET  /battle-units/:id} : get the "id" battleUnit.
     *
     * @param id the id of the battleUnitDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the battleUnitDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/battle-units/{id}")
    public ResponseEntity<BattleUnitDTO> getBattleUnit(@PathVariable Long id) {
        log.debug("REST request to get BattleUnit : {}", id);
        Optional<BattleUnitDTO> battleUnitDTO = battleUnitService.findOne(id);
        return ResponseUtil.wrapOrNotFound(battleUnitDTO);
    }

    /**
     * {@code DELETE  /battle-units/:id} : delete the "id" battleUnit.
     *
     * @param id the id of the battleUnitDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/battle-units/{id}")
    public ResponseEntity<Void> deleteBattleUnit(@PathVariable Long id) {
        log.debug("REST request to delete BattleUnit : {}", id);
        battleUnitService.delete(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }
}
