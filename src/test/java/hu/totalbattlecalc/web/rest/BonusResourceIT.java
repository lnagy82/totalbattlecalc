package hu.totalbattlecalc.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import hu.totalbattlecalc.IntegrationTest;
import hu.totalbattlecalc.domain.BattleUnit;
import hu.totalbattlecalc.domain.Bonus;
import hu.totalbattlecalc.domain.enumeration.BonusName;
import hu.totalbattlecalc.domain.enumeration.MeasurementUnit;
import hu.totalbattlecalc.repository.BonusRepository;
import hu.totalbattlecalc.service.criteria.BonusCriteria;
import hu.totalbattlecalc.service.dto.BonusDTO;
import hu.totalbattlecalc.service.mapper.BonusMapper;
import java.util.List;
import java.util.Random;
import java.util.concurrent.atomic.AtomicLong;
import javax.persistence.EntityManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

/**
 * Integration tests for the {@link BonusResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class BonusResourceIT {

    private static final BonusName DEFAULT_NAME = BonusName.STRENGTH;
    private static final BonusName UPDATED_NAME = BonusName.HEALTH;

    private static final Double DEFAULT_VALUE = 1D;
    private static final Double UPDATED_VALUE = 2D;
    private static final Double SMALLER_VALUE = 1D - 1D;

    private static final MeasurementUnit DEFAULT_UNIT = MeasurementUnit.NONE;
    private static final MeasurementUnit UPDATED_UNIT = MeasurementUnit.PERCENT;

    private static final String ENTITY_API_URL = "/api/bonuses";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private BonusRepository bonusRepository;

    @Autowired
    private BonusMapper bonusMapper;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restBonusMockMvc;

    private Bonus bonus;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Bonus createEntity(EntityManager em) {
        Bonus bonus = new Bonus().name(DEFAULT_NAME).value(DEFAULT_VALUE).unit(DEFAULT_UNIT);
        return bonus;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Bonus createUpdatedEntity(EntityManager em) {
        Bonus bonus = new Bonus().name(UPDATED_NAME).value(UPDATED_VALUE).unit(UPDATED_UNIT);
        return bonus;
    }

    @BeforeEach
    public void initTest() {
        bonus = createEntity(em);
    }

    @Test
    @Transactional
    void createBonus() throws Exception {
        int databaseSizeBeforeCreate = bonusRepository.findAll().size();
        // Create the Bonus
        BonusDTO bonusDTO = bonusMapper.toDto(bonus);
        restBonusMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(bonusDTO)))
            .andExpect(status().isCreated());

        // Validate the Bonus in the database
        List<Bonus> bonusList = bonusRepository.findAll();
        assertThat(bonusList).hasSize(databaseSizeBeforeCreate + 1);
        Bonus testBonus = bonusList.get(bonusList.size() - 1);
        assertThat(testBonus.getName()).isEqualTo(DEFAULT_NAME);
        assertThat(testBonus.getValue()).isEqualTo(DEFAULT_VALUE);
        assertThat(testBonus.getUnit()).isEqualTo(DEFAULT_UNIT);
    }

    @Test
    @Transactional
    void createBonusWithExistingId() throws Exception {
        // Create the Bonus with an existing ID
        bonus.setId(1L);
        BonusDTO bonusDTO = bonusMapper.toDto(bonus);

        int databaseSizeBeforeCreate = bonusRepository.findAll().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        restBonusMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(bonusDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Bonus in the database
        List<Bonus> bonusList = bonusRepository.findAll();
        assertThat(bonusList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void getAllBonuses() throws Exception {
        // Initialize the database
        bonusRepository.saveAndFlush(bonus);

        // Get all the bonusList
        restBonusMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(bonus.getId().intValue())))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME.toString())))
            .andExpect(jsonPath("$.[*].value").value(hasItem(DEFAULT_VALUE.doubleValue())))
            .andExpect(jsonPath("$.[*].unit").value(hasItem(DEFAULT_UNIT.toString())));
    }

    @Test
    @Transactional
    void getBonus() throws Exception {
        // Initialize the database
        bonusRepository.saveAndFlush(bonus);

        // Get the bonus
        restBonusMockMvc
            .perform(get(ENTITY_API_URL_ID, bonus.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(bonus.getId().intValue()))
            .andExpect(jsonPath("$.name").value(DEFAULT_NAME.toString()))
            .andExpect(jsonPath("$.value").value(DEFAULT_VALUE.doubleValue()))
            .andExpect(jsonPath("$.unit").value(DEFAULT_UNIT.toString()));
    }

    @Test
    @Transactional
    void getBonusesByIdFiltering() throws Exception {
        // Initialize the database
        bonusRepository.saveAndFlush(bonus);

        Long id = bonus.getId();

        defaultBonusShouldBeFound("id.equals=" + id);
        defaultBonusShouldNotBeFound("id.notEquals=" + id);

        defaultBonusShouldBeFound("id.greaterThanOrEqual=" + id);
        defaultBonusShouldNotBeFound("id.greaterThan=" + id);

        defaultBonusShouldBeFound("id.lessThanOrEqual=" + id);
        defaultBonusShouldNotBeFound("id.lessThan=" + id);
    }

    @Test
    @Transactional
    void getAllBonusesByNameIsEqualToSomething() throws Exception {
        // Initialize the database
        bonusRepository.saveAndFlush(bonus);

        // Get all the bonusList where name equals to DEFAULT_NAME
        defaultBonusShouldBeFound("name.equals=" + DEFAULT_NAME);

        // Get all the bonusList where name equals to UPDATED_NAME
        defaultBonusShouldNotBeFound("name.equals=" + UPDATED_NAME);
    }

    @Test
    @Transactional
    void getAllBonusesByNameIsNotEqualToSomething() throws Exception {
        // Initialize the database
        bonusRepository.saveAndFlush(bonus);

        // Get all the bonusList where name not equals to DEFAULT_NAME
        defaultBonusShouldNotBeFound("name.notEquals=" + DEFAULT_NAME);

        // Get all the bonusList where name not equals to UPDATED_NAME
        defaultBonusShouldBeFound("name.notEquals=" + UPDATED_NAME);
    }

    @Test
    @Transactional
    void getAllBonusesByNameIsInShouldWork() throws Exception {
        // Initialize the database
        bonusRepository.saveAndFlush(bonus);

        // Get all the bonusList where name in DEFAULT_NAME or UPDATED_NAME
        defaultBonusShouldBeFound("name.in=" + DEFAULT_NAME + "," + UPDATED_NAME);

        // Get all the bonusList where name equals to UPDATED_NAME
        defaultBonusShouldNotBeFound("name.in=" + UPDATED_NAME);
    }

    @Test
    @Transactional
    void getAllBonusesByNameIsNullOrNotNull() throws Exception {
        // Initialize the database
        bonusRepository.saveAndFlush(bonus);

        // Get all the bonusList where name is not null
        defaultBonusShouldBeFound("name.specified=true");

        // Get all the bonusList where name is null
        defaultBonusShouldNotBeFound("name.specified=false");
    }

    @Test
    @Transactional
    void getAllBonusesByValueIsEqualToSomething() throws Exception {
        // Initialize the database
        bonusRepository.saveAndFlush(bonus);

        // Get all the bonusList where value equals to DEFAULT_VALUE
        defaultBonusShouldBeFound("value.equals=" + DEFAULT_VALUE);

        // Get all the bonusList where value equals to UPDATED_VALUE
        defaultBonusShouldNotBeFound("value.equals=" + UPDATED_VALUE);
    }

    @Test
    @Transactional
    void getAllBonusesByValueIsNotEqualToSomething() throws Exception {
        // Initialize the database
        bonusRepository.saveAndFlush(bonus);

        // Get all the bonusList where value not equals to DEFAULT_VALUE
        defaultBonusShouldNotBeFound("value.notEquals=" + DEFAULT_VALUE);

        // Get all the bonusList where value not equals to UPDATED_VALUE
        defaultBonusShouldBeFound("value.notEquals=" + UPDATED_VALUE);
    }

    @Test
    @Transactional
    void getAllBonusesByValueIsInShouldWork() throws Exception {
        // Initialize the database
        bonusRepository.saveAndFlush(bonus);

        // Get all the bonusList where value in DEFAULT_VALUE or UPDATED_VALUE
        defaultBonusShouldBeFound("value.in=" + DEFAULT_VALUE + "," + UPDATED_VALUE);

        // Get all the bonusList where value equals to UPDATED_VALUE
        defaultBonusShouldNotBeFound("value.in=" + UPDATED_VALUE);
    }

    @Test
    @Transactional
    void getAllBonusesByValueIsNullOrNotNull() throws Exception {
        // Initialize the database
        bonusRepository.saveAndFlush(bonus);

        // Get all the bonusList where value is not null
        defaultBonusShouldBeFound("value.specified=true");

        // Get all the bonusList where value is null
        defaultBonusShouldNotBeFound("value.specified=false");
    }

    @Test
    @Transactional
    void getAllBonusesByValueIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        bonusRepository.saveAndFlush(bonus);

        // Get all the bonusList where value is greater than or equal to DEFAULT_VALUE
        defaultBonusShouldBeFound("value.greaterThanOrEqual=" + DEFAULT_VALUE);

        // Get all the bonusList where value is greater than or equal to UPDATED_VALUE
        defaultBonusShouldNotBeFound("value.greaterThanOrEqual=" + UPDATED_VALUE);
    }

    @Test
    @Transactional
    void getAllBonusesByValueIsLessThanOrEqualToSomething() throws Exception {
        // Initialize the database
        bonusRepository.saveAndFlush(bonus);

        // Get all the bonusList where value is less than or equal to DEFAULT_VALUE
        defaultBonusShouldBeFound("value.lessThanOrEqual=" + DEFAULT_VALUE);

        // Get all the bonusList where value is less than or equal to SMALLER_VALUE
        defaultBonusShouldNotBeFound("value.lessThanOrEqual=" + SMALLER_VALUE);
    }

    @Test
    @Transactional
    void getAllBonusesByValueIsLessThanSomething() throws Exception {
        // Initialize the database
        bonusRepository.saveAndFlush(bonus);

        // Get all the bonusList where value is less than DEFAULT_VALUE
        defaultBonusShouldNotBeFound("value.lessThan=" + DEFAULT_VALUE);

        // Get all the bonusList where value is less than UPDATED_VALUE
        defaultBonusShouldBeFound("value.lessThan=" + UPDATED_VALUE);
    }

    @Test
    @Transactional
    void getAllBonusesByValueIsGreaterThanSomething() throws Exception {
        // Initialize the database
        bonusRepository.saveAndFlush(bonus);

        // Get all the bonusList where value is greater than DEFAULT_VALUE
        defaultBonusShouldNotBeFound("value.greaterThan=" + DEFAULT_VALUE);

        // Get all the bonusList where value is greater than SMALLER_VALUE
        defaultBonusShouldBeFound("value.greaterThan=" + SMALLER_VALUE);
    }

    @Test
    @Transactional
    void getAllBonusesByUnitIsEqualToSomething() throws Exception {
        // Initialize the database
        bonusRepository.saveAndFlush(bonus);

        // Get all the bonusList where unit equals to DEFAULT_UNIT
        defaultBonusShouldBeFound("unit.equals=" + DEFAULT_UNIT);

        // Get all the bonusList where unit equals to UPDATED_UNIT
        defaultBonusShouldNotBeFound("unit.equals=" + UPDATED_UNIT);
    }

    @Test
    @Transactional
    void getAllBonusesByUnitIsNotEqualToSomething() throws Exception {
        // Initialize the database
        bonusRepository.saveAndFlush(bonus);

        // Get all the bonusList where unit not equals to DEFAULT_UNIT
        defaultBonusShouldNotBeFound("unit.notEquals=" + DEFAULT_UNIT);

        // Get all the bonusList where unit not equals to UPDATED_UNIT
        defaultBonusShouldBeFound("unit.notEquals=" + UPDATED_UNIT);
    }

    @Test
    @Transactional
    void getAllBonusesByUnitIsInShouldWork() throws Exception {
        // Initialize the database
        bonusRepository.saveAndFlush(bonus);

        // Get all the bonusList where unit in DEFAULT_UNIT or UPDATED_UNIT
        defaultBonusShouldBeFound("unit.in=" + DEFAULT_UNIT + "," + UPDATED_UNIT);

        // Get all the bonusList where unit equals to UPDATED_UNIT
        defaultBonusShouldNotBeFound("unit.in=" + UPDATED_UNIT);
    }

    @Test
    @Transactional
    void getAllBonusesByUnitIsNullOrNotNull() throws Exception {
        // Initialize the database
        bonusRepository.saveAndFlush(bonus);

        // Get all the bonusList where unit is not null
        defaultBonusShouldBeFound("unit.specified=true");

        // Get all the bonusList where unit is null
        defaultBonusShouldNotBeFound("unit.specified=false");
    }

    @Test
    @Transactional
    void getAllBonusesByBattleUnitIsEqualToSomething() throws Exception {
        // Initialize the database
        bonusRepository.saveAndFlush(bonus);
        BattleUnit battleUnit;
        if (TestUtil.findAll(em, BattleUnit.class).isEmpty()) {
            battleUnit = BattleUnitResourceIT.createEntity(em);
            em.persist(battleUnit);
            em.flush();
        } else {
            battleUnit = TestUtil.findAll(em, BattleUnit.class).get(0);
        }
        em.persist(battleUnit);
        em.flush();
        bonus.addBattleUnit(battleUnit);
        bonusRepository.saveAndFlush(bonus);
        Long battleUnitId = battleUnit.getId();

        // Get all the bonusList where battleUnit equals to battleUnitId
        defaultBonusShouldBeFound("battleUnitId.equals=" + battleUnitId);

        // Get all the bonusList where battleUnit equals to (battleUnitId + 1)
        defaultBonusShouldNotBeFound("battleUnitId.equals=" + (battleUnitId + 1));
    }

    /**
     * Executes the search, and checks that the default entity is returned.
     */
    private void defaultBonusShouldBeFound(String filter) throws Exception {
        restBonusMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(bonus.getId().intValue())))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME.toString())))
            .andExpect(jsonPath("$.[*].value").value(hasItem(DEFAULT_VALUE.doubleValue())))
            .andExpect(jsonPath("$.[*].unit").value(hasItem(DEFAULT_UNIT.toString())));

        // Check, that the count call also returns 1
        restBonusMockMvc
            .perform(get(ENTITY_API_URL + "/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(content().string("1"));
    }

    /**
     * Executes the search, and checks that the default entity is not returned.
     */
    private void defaultBonusShouldNotBeFound(String filter) throws Exception {
        restBonusMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$").isEmpty());

        // Check, that the count call also returns 0
        restBonusMockMvc
            .perform(get(ENTITY_API_URL + "/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(content().string("0"));
    }

    @Test
    @Transactional
    void getNonExistingBonus() throws Exception {
        // Get the bonus
        restBonusMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putNewBonus() throws Exception {
        // Initialize the database
        bonusRepository.saveAndFlush(bonus);

        int databaseSizeBeforeUpdate = bonusRepository.findAll().size();

        // Update the bonus
        Bonus updatedBonus = bonusRepository.findById(bonus.getId()).get();
        // Disconnect from session so that the updates on updatedBonus are not directly saved in db
        em.detach(updatedBonus);
        updatedBonus.name(UPDATED_NAME).value(UPDATED_VALUE).unit(UPDATED_UNIT);
        BonusDTO bonusDTO = bonusMapper.toDto(updatedBonus);

        restBonusMockMvc
            .perform(
                put(ENTITY_API_URL_ID, bonusDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(bonusDTO))
            )
            .andExpect(status().isOk());

        // Validate the Bonus in the database
        List<Bonus> bonusList = bonusRepository.findAll();
        assertThat(bonusList).hasSize(databaseSizeBeforeUpdate);
        Bonus testBonus = bonusList.get(bonusList.size() - 1);
        assertThat(testBonus.getName()).isEqualTo(UPDATED_NAME);
        assertThat(testBonus.getValue()).isEqualTo(UPDATED_VALUE);
        assertThat(testBonus.getUnit()).isEqualTo(UPDATED_UNIT);
    }

    @Test
    @Transactional
    void putNonExistingBonus() throws Exception {
        int databaseSizeBeforeUpdate = bonusRepository.findAll().size();
        bonus.setId(count.incrementAndGet());

        // Create the Bonus
        BonusDTO bonusDTO = bonusMapper.toDto(bonus);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restBonusMockMvc
            .perform(
                put(ENTITY_API_URL_ID, bonusDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(bonusDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the Bonus in the database
        List<Bonus> bonusList = bonusRepository.findAll();
        assertThat(bonusList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchBonus() throws Exception {
        int databaseSizeBeforeUpdate = bonusRepository.findAll().size();
        bonus.setId(count.incrementAndGet());

        // Create the Bonus
        BonusDTO bonusDTO = bonusMapper.toDto(bonus);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restBonusMockMvc
            .perform(
                put(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(bonusDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the Bonus in the database
        List<Bonus> bonusList = bonusRepository.findAll();
        assertThat(bonusList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamBonus() throws Exception {
        int databaseSizeBeforeUpdate = bonusRepository.findAll().size();
        bonus.setId(count.incrementAndGet());

        // Create the Bonus
        BonusDTO bonusDTO = bonusMapper.toDto(bonus);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restBonusMockMvc
            .perform(put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(bonusDTO)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the Bonus in the database
        List<Bonus> bonusList = bonusRepository.findAll();
        assertThat(bonusList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateBonusWithPatch() throws Exception {
        // Initialize the database
        bonusRepository.saveAndFlush(bonus);

        int databaseSizeBeforeUpdate = bonusRepository.findAll().size();

        // Update the bonus using partial update
        Bonus partialUpdatedBonus = new Bonus();
        partialUpdatedBonus.setId(bonus.getId());

        restBonusMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedBonus.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedBonus))
            )
            .andExpect(status().isOk());

        // Validate the Bonus in the database
        List<Bonus> bonusList = bonusRepository.findAll();
        assertThat(bonusList).hasSize(databaseSizeBeforeUpdate);
        Bonus testBonus = bonusList.get(bonusList.size() - 1);
        assertThat(testBonus.getName()).isEqualTo(DEFAULT_NAME);
        assertThat(testBonus.getValue()).isEqualTo(DEFAULT_VALUE);
        assertThat(testBonus.getUnit()).isEqualTo(DEFAULT_UNIT);
    }

    @Test
    @Transactional
    void fullUpdateBonusWithPatch() throws Exception {
        // Initialize the database
        bonusRepository.saveAndFlush(bonus);

        int databaseSizeBeforeUpdate = bonusRepository.findAll().size();

        // Update the bonus using partial update
        Bonus partialUpdatedBonus = new Bonus();
        partialUpdatedBonus.setId(bonus.getId());

        partialUpdatedBonus.name(UPDATED_NAME).value(UPDATED_VALUE).unit(UPDATED_UNIT);

        restBonusMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedBonus.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedBonus))
            )
            .andExpect(status().isOk());

        // Validate the Bonus in the database
        List<Bonus> bonusList = bonusRepository.findAll();
        assertThat(bonusList).hasSize(databaseSizeBeforeUpdate);
        Bonus testBonus = bonusList.get(bonusList.size() - 1);
        assertThat(testBonus.getName()).isEqualTo(UPDATED_NAME);
        assertThat(testBonus.getValue()).isEqualTo(UPDATED_VALUE);
        assertThat(testBonus.getUnit()).isEqualTo(UPDATED_UNIT);
    }

    @Test
    @Transactional
    void patchNonExistingBonus() throws Exception {
        int databaseSizeBeforeUpdate = bonusRepository.findAll().size();
        bonus.setId(count.incrementAndGet());

        // Create the Bonus
        BonusDTO bonusDTO = bonusMapper.toDto(bonus);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restBonusMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, bonusDTO.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(bonusDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the Bonus in the database
        List<Bonus> bonusList = bonusRepository.findAll();
        assertThat(bonusList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchBonus() throws Exception {
        int databaseSizeBeforeUpdate = bonusRepository.findAll().size();
        bonus.setId(count.incrementAndGet());

        // Create the Bonus
        BonusDTO bonusDTO = bonusMapper.toDto(bonus);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restBonusMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(bonusDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the Bonus in the database
        List<Bonus> bonusList = bonusRepository.findAll();
        assertThat(bonusList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamBonus() throws Exception {
        int databaseSizeBeforeUpdate = bonusRepository.findAll().size();
        bonus.setId(count.incrementAndGet());

        // Create the Bonus
        BonusDTO bonusDTO = bonusMapper.toDto(bonus);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restBonusMockMvc
            .perform(patch(ENTITY_API_URL).contentType("application/merge-patch+json").content(TestUtil.convertObjectToJsonBytes(bonusDTO)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the Bonus in the database
        List<Bonus> bonusList = bonusRepository.findAll();
        assertThat(bonusList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteBonus() throws Exception {
        // Initialize the database
        bonusRepository.saveAndFlush(bonus);

        int databaseSizeBeforeDelete = bonusRepository.findAll().size();

        // Delete the bonus
        restBonusMockMvc
            .perform(delete(ENTITY_API_URL_ID, bonus.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Bonus> bonusList = bonusRepository.findAll();
        assertThat(bonusList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
