package hu.totalbattlecalc.web.rest;

import hu.totalbattlecalc.repository.BonusRepository;
import hu.totalbattlecalc.service.BonusQueryService;
import hu.totalbattlecalc.service.BonusService;
import hu.totalbattlecalc.service.criteria.BonusCriteria;
import hu.totalbattlecalc.service.dto.BonusDTO;
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
 * REST controller for managing {@link hu.totalbattlecalc.domain.Bonus}.
 */
@RestController
@RequestMapping("/api")
public class BonusResource {

    private final Logger log = LoggerFactory.getLogger(BonusResource.class);

    private static final String ENTITY_NAME = "bonus";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final BonusService bonusService;

    private final BonusRepository bonusRepository;

    private final BonusQueryService bonusQueryService;

    public BonusResource(BonusService bonusService, BonusRepository bonusRepository, BonusQueryService bonusQueryService) {
        this.bonusService = bonusService;
        this.bonusRepository = bonusRepository;
        this.bonusQueryService = bonusQueryService;
    }

    /**
     * {@code POST  /bonuses} : Create a new bonus.
     *
     * @param bonusDTO the bonusDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new bonusDTO, or with status {@code 400 (Bad Request)} if the bonus has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/bonuses")
    public ResponseEntity<BonusDTO> createBonus(@RequestBody BonusDTO bonusDTO) throws URISyntaxException {
        log.debug("REST request to save Bonus : {}", bonusDTO);
        if (bonusDTO.getId() != null) {
            throw new BadRequestAlertException("A new bonus cannot already have an ID", ENTITY_NAME, "idexists");
        }
        BonusDTO result = bonusService.save(bonusDTO);
        return ResponseEntity
            .created(new URI("/api/bonuses/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /bonuses/:id} : Updates an existing bonus.
     *
     * @param id the id of the bonusDTO to save.
     * @param bonusDTO the bonusDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated bonusDTO,
     * or with status {@code 400 (Bad Request)} if the bonusDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the bonusDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/bonuses/{id}")
    public ResponseEntity<BonusDTO> updateBonus(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody BonusDTO bonusDTO
    ) throws URISyntaxException {
        log.debug("REST request to update Bonus : {}, {}", id, bonusDTO);
        if (bonusDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, bonusDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!bonusRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        BonusDTO result = bonusService.save(bonusDTO);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, bonusDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code PATCH  /bonuses/:id} : Partial updates given fields of an existing bonus, field will ignore if it is null
     *
     * @param id the id of the bonusDTO to save.
     * @param bonusDTO the bonusDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated bonusDTO,
     * or with status {@code 400 (Bad Request)} if the bonusDTO is not valid,
     * or with status {@code 404 (Not Found)} if the bonusDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the bonusDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/bonuses/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<BonusDTO> partialUpdateBonus(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody BonusDTO bonusDTO
    ) throws URISyntaxException {
        log.debug("REST request to partial update Bonus partially : {}, {}", id, bonusDTO);
        if (bonusDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, bonusDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!bonusRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<BonusDTO> result = bonusService.partialUpdate(bonusDTO);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, bonusDTO.getId().toString())
        );
    }

    /**
     * {@code GET  /bonuses} : get all the bonuses.
     *
     * @param pageable the pagination information.
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of bonuses in body.
     */
    @GetMapping("/bonuses")
    public ResponseEntity<List<BonusDTO>> getAllBonuses(BonusCriteria criteria, Pageable pageable) {
        log.debug("REST request to get Bonuses by criteria: {}", criteria);
        Page<BonusDTO> page = bonusQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /bonuses/count} : count all the bonuses.
     *
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the count in body.
     */
    @GetMapping("/bonuses/count")
    public ResponseEntity<Long> countBonuses(BonusCriteria criteria) {
        log.debug("REST request to count Bonuses by criteria: {}", criteria);
        return ResponseEntity.ok().body(bonusQueryService.countByCriteria(criteria));
    }

    /**
     * {@code GET  /bonuses/:id} : get the "id" bonus.
     *
     * @param id the id of the bonusDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the bonusDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/bonuses/{id}")
    public ResponseEntity<BonusDTO> getBonus(@PathVariable Long id) {
        log.debug("REST request to get Bonus : {}", id);
        Optional<BonusDTO> bonusDTO = bonusService.findOne(id);
        return ResponseUtil.wrapOrNotFound(bonusDTO);
    }

    /**
     * {@code DELETE  /bonuses/:id} : delete the "id" bonus.
     *
     * @param id the id of the bonusDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/bonuses/{id}")
    public ResponseEntity<Void> deleteBonus(@PathVariable Long id) {
        log.debug("REST request to delete Bonus : {}", id);
        bonusService.delete(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }
}
