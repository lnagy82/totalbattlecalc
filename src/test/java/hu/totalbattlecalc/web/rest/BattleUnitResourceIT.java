package hu.totalbattlecalc.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import hu.totalbattlecalc.IntegrationTest;
import hu.totalbattlecalc.domain.BattleUnit;
import hu.totalbattlecalc.domain.Bonus;
import hu.totalbattlecalc.domain.Unit;
import hu.totalbattlecalc.repository.BattleUnitRepository;
import hu.totalbattlecalc.service.BattleUnitService;
import hu.totalbattlecalc.service.criteria.BattleUnitCriteria;
import hu.totalbattlecalc.service.dto.BattleUnitDTO;
import hu.totalbattlecalc.service.mapper.BattleUnitMapper;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.atomic.AtomicLong;
import javax.persistence.EntityManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

/**
 * Integration tests for the {@link BattleUnitResource} REST controller.
 */
@IntegrationTest
@ExtendWith(MockitoExtension.class)
@AutoConfigureMockMvc
@WithMockUser
class BattleUnitResourceIT {

    private static final Integer DEFAULT_NUMBER = 1;
    private static final Integer UPDATED_NUMBER = 2;
    private static final Integer SMALLER_NUMBER = 1 - 1;

    private static final String ENTITY_API_URL = "/api/battle-units";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private BattleUnitRepository battleUnitRepository;

    @Mock
    private BattleUnitRepository battleUnitRepositoryMock;

    @Autowired
    private BattleUnitMapper battleUnitMapper;

    @Mock
    private BattleUnitService battleUnitServiceMock;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restBattleUnitMockMvc;

    private BattleUnit battleUnit;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static BattleUnit createEntity(EntityManager em) {
        BattleUnit battleUnit = new BattleUnit().number(DEFAULT_NUMBER);
        return battleUnit;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static BattleUnit createUpdatedEntity(EntityManager em) {
        BattleUnit battleUnit = new BattleUnit().number(UPDATED_NUMBER);
        return battleUnit;
    }

    @BeforeEach
    public void initTest() {
        battleUnit = createEntity(em);
    }

    @Test
    @Transactional
    void createBattleUnit() throws Exception {
        int databaseSizeBeforeCreate = battleUnitRepository.findAll().size();
        // Create the BattleUnit
        BattleUnitDTO battleUnitDTO = battleUnitMapper.toDto(battleUnit);
        restBattleUnitMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(battleUnitDTO)))
            .andExpect(status().isCreated());

        // Validate the BattleUnit in the database
        List<BattleUnit> battleUnitList = battleUnitRepository.findAll();
        assertThat(battleUnitList).hasSize(databaseSizeBeforeCreate + 1);
        BattleUnit testBattleUnit = battleUnitList.get(battleUnitList.size() - 1);
        assertThat(testBattleUnit.getNumber()).isEqualTo(DEFAULT_NUMBER);
    }

    @Test
    @Transactional
    void createBattleUnitWithExistingId() throws Exception {
        // Create the BattleUnit with an existing ID
        battleUnit.setId(1L);
        BattleUnitDTO battleUnitDTO = battleUnitMapper.toDto(battleUnit);

        int databaseSizeBeforeCreate = battleUnitRepository.findAll().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        restBattleUnitMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(battleUnitDTO)))
            .andExpect(status().isBadRequest());

        // Validate the BattleUnit in the database
        List<BattleUnit> battleUnitList = battleUnitRepository.findAll();
        assertThat(battleUnitList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void getAllBattleUnits() throws Exception {
        // Initialize the database
        battleUnitRepository.saveAndFlush(battleUnit);

        // Get all the battleUnitList
        restBattleUnitMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(battleUnit.getId().intValue())))
            .andExpect(jsonPath("$.[*].number").value(hasItem(DEFAULT_NUMBER)));
    }

    @SuppressWarnings({ "unchecked" })
    void getAllBattleUnitsWithEagerRelationshipsIsEnabled() throws Exception {
        when(battleUnitServiceMock.findAllWithEagerRelationships(any())).thenReturn(new PageImpl(new ArrayList<>()));

        restBattleUnitMockMvc.perform(get(ENTITY_API_URL + "?eagerload=true")).andExpect(status().isOk());

        verify(battleUnitServiceMock, times(1)).findAllWithEagerRelationships(any());
    }

    @SuppressWarnings({ "unchecked" })
    void getAllBattleUnitsWithEagerRelationshipsIsNotEnabled() throws Exception {
        when(battleUnitServiceMock.findAllWithEagerRelationships(any())).thenReturn(new PageImpl(new ArrayList<>()));

        restBattleUnitMockMvc.perform(get(ENTITY_API_URL + "?eagerload=true")).andExpect(status().isOk());

        verify(battleUnitServiceMock, times(1)).findAllWithEagerRelationships(any());
    }

    @Test
    @Transactional
    void getBattleUnit() throws Exception {
        // Initialize the database
        battleUnitRepository.saveAndFlush(battleUnit);

        // Get the battleUnit
        restBattleUnitMockMvc
            .perform(get(ENTITY_API_URL_ID, battleUnit.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(battleUnit.getId().intValue()))
            .andExpect(jsonPath("$.number").value(DEFAULT_NUMBER));
    }

    @Test
    @Transactional
    void getBattleUnitsByIdFiltering() throws Exception {
        // Initialize the database
        battleUnitRepository.saveAndFlush(battleUnit);

        Long id = battleUnit.getId();

        defaultBattleUnitShouldBeFound("id.equals=" + id);
        defaultBattleUnitShouldNotBeFound("id.notEquals=" + id);

        defaultBattleUnitShouldBeFound("id.greaterThanOrEqual=" + id);
        defaultBattleUnitShouldNotBeFound("id.greaterThan=" + id);

        defaultBattleUnitShouldBeFound("id.lessThanOrEqual=" + id);
        defaultBattleUnitShouldNotBeFound("id.lessThan=" + id);
    }

    @Test
    @Transactional
    void getAllBattleUnitsByNumberIsEqualToSomething() throws Exception {
        // Initialize the database
        battleUnitRepository.saveAndFlush(battleUnit);

        // Get all the battleUnitList where number equals to DEFAULT_NUMBER
        defaultBattleUnitShouldBeFound("number.equals=" + DEFAULT_NUMBER);

        // Get all the battleUnitList where number equals to UPDATED_NUMBER
        defaultBattleUnitShouldNotBeFound("number.equals=" + UPDATED_NUMBER);
    }

    @Test
    @Transactional
    void getAllBattleUnitsByNumberIsNotEqualToSomething() throws Exception {
        // Initialize the database
        battleUnitRepository.saveAndFlush(battleUnit);

        // Get all the battleUnitList where number not equals to DEFAULT_NUMBER
        defaultBattleUnitShouldNotBeFound("number.notEquals=" + DEFAULT_NUMBER);

        // Get all the battleUnitList where number not equals to UPDATED_NUMBER
        defaultBattleUnitShouldBeFound("number.notEquals=" + UPDATED_NUMBER);
    }

    @Test
    @Transactional
    void getAllBattleUnitsByNumberIsInShouldWork() throws Exception {
        // Initialize the database
        battleUnitRepository.saveAndFlush(battleUnit);

        // Get all the battleUnitList where number in DEFAULT_NUMBER or UPDATED_NUMBER
        defaultBattleUnitShouldBeFound("number.in=" + DEFAULT_NUMBER + "," + UPDATED_NUMBER);

        // Get all the battleUnitList where number equals to UPDATED_NUMBER
        defaultBattleUnitShouldNotBeFound("number.in=" + UPDATED_NUMBER);
    }

    @Test
    @Transactional
    void getAllBattleUnitsByNumberIsNullOrNotNull() throws Exception {
        // Initialize the database
        battleUnitRepository.saveAndFlush(battleUnit);

        // Get all the battleUnitList where number is not null
        defaultBattleUnitShouldBeFound("number.specified=true");

        // Get all the battleUnitList where number is null
        defaultBattleUnitShouldNotBeFound("number.specified=false");
    }

    @Test
    @Transactional
    void getAllBattleUnitsByNumberIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        battleUnitRepository.saveAndFlush(battleUnit);

        // Get all the battleUnitList where number is greater than or equal to DEFAULT_NUMBER
        defaultBattleUnitShouldBeFound("number.greaterThanOrEqual=" + DEFAULT_NUMBER);

        // Get all the battleUnitList where number is greater than or equal to UPDATED_NUMBER
        defaultBattleUnitShouldNotBeFound("number.greaterThanOrEqual=" + UPDATED_NUMBER);
    }

    @Test
    @Transactional
    void getAllBattleUnitsByNumberIsLessThanOrEqualToSomething() throws Exception {
        // Initialize the database
        battleUnitRepository.saveAndFlush(battleUnit);

        // Get all the battleUnitList where number is less than or equal to DEFAULT_NUMBER
        defaultBattleUnitShouldBeFound("number.lessThanOrEqual=" + DEFAULT_NUMBER);

        // Get all the battleUnitList where number is less than or equal to SMALLER_NUMBER
        defaultBattleUnitShouldNotBeFound("number.lessThanOrEqual=" + SMALLER_NUMBER);
    }

    @Test
    @Transactional
    void getAllBattleUnitsByNumberIsLessThanSomething() throws Exception {
        // Initialize the database
        battleUnitRepository.saveAndFlush(battleUnit);

        // Get all the battleUnitList where number is less than DEFAULT_NUMBER
        defaultBattleUnitShouldNotBeFound("number.lessThan=" + DEFAULT_NUMBER);

        // Get all the battleUnitList where number is less than UPDATED_NUMBER
        defaultBattleUnitShouldBeFound("number.lessThan=" + UPDATED_NUMBER);
    }

    @Test
    @Transactional
    void getAllBattleUnitsByNumberIsGreaterThanSomething() throws Exception {
        // Initialize the database
        battleUnitRepository.saveAndFlush(battleUnit);

        // Get all the battleUnitList where number is greater than DEFAULT_NUMBER
        defaultBattleUnitShouldNotBeFound("number.greaterThan=" + DEFAULT_NUMBER);

        // Get all the battleUnitList where number is greater than SMALLER_NUMBER
        defaultBattleUnitShouldBeFound("number.greaterThan=" + SMALLER_NUMBER);
    }

    @Test
    @Transactional
    void getAllBattleUnitsByUnitIsEqualToSomething() throws Exception {
        // Initialize the database
        battleUnitRepository.saveAndFlush(battleUnit);
        Unit unit;
        if (TestUtil.findAll(em, Unit.class).isEmpty()) {
            unit = UnitResourceIT.createEntity(em);
            em.persist(unit);
            em.flush();
        } else {
            unit = TestUtil.findAll(em, Unit.class).get(0);
        }
        em.persist(unit);
        em.flush();
        battleUnit.setUnit(unit);
        battleUnitRepository.saveAndFlush(battleUnit);
        Long unitId = unit.getId();

        // Get all the battleUnitList where unit equals to unitId
        defaultBattleUnitShouldBeFound("unitId.equals=" + unitId);

        // Get all the battleUnitList where unit equals to (unitId + 1)
        defaultBattleUnitShouldNotBeFound("unitId.equals=" + (unitId + 1));
    }

    @Test
    @Transactional
    void getAllBattleUnitsByBonusIsEqualToSomething() throws Exception {
        // Initialize the database
        battleUnitRepository.saveAndFlush(battleUnit);
        Bonus bonus;
        if (TestUtil.findAll(em, Bonus.class).isEmpty()) {
            bonus = BonusResourceIT.createEntity(em);
            em.persist(bonus);
            em.flush();
        } else {
            bonus = TestUtil.findAll(em, Bonus.class).get(0);
        }
        em.persist(bonus);
        em.flush();
        battleUnit.addBonus(bonus);
        battleUnitRepository.saveAndFlush(battleUnit);
        Long bonusId = bonus.getId();

        // Get all the battleUnitList where bonus equals to bonusId
        defaultBattleUnitShouldBeFound("bonusId.equals=" + bonusId);

        // Get all the battleUnitList where bonus equals to (bonusId + 1)
        defaultBattleUnitShouldNotBeFound("bonusId.equals=" + (bonusId + 1));
    }

    /**
     * Executes the search, and checks that the default entity is returned.
     */
    private void defaultBattleUnitShouldBeFound(String filter) throws Exception {
        restBattleUnitMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(battleUnit.getId().intValue())))
            .andExpect(jsonPath("$.[*].number").value(hasItem(DEFAULT_NUMBER)));

        // Check, that the count call also returns 1
        restBattleUnitMockMvc
            .perform(get(ENTITY_API_URL + "/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(content().string("1"));
    }

    /**
     * Executes the search, and checks that the default entity is not returned.
     */
    private void defaultBattleUnitShouldNotBeFound(String filter) throws Exception {
        restBattleUnitMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$").isEmpty());

        // Check, that the count call also returns 0
        restBattleUnitMockMvc
            .perform(get(ENTITY_API_URL + "/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(content().string("0"));
    }

    @Test
    @Transactional
    void getNonExistingBattleUnit() throws Exception {
        // Get the battleUnit
        restBattleUnitMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putNewBattleUnit() throws Exception {
        // Initialize the database
        battleUnitRepository.saveAndFlush(battleUnit);

        int databaseSizeBeforeUpdate = battleUnitRepository.findAll().size();

        // Update the battleUnit
        BattleUnit updatedBattleUnit = battleUnitRepository.findById(battleUnit.getId()).get();
        // Disconnect from session so that the updates on updatedBattleUnit are not directly saved in db
        em.detach(updatedBattleUnit);
        updatedBattleUnit.number(UPDATED_NUMBER);
        BattleUnitDTO battleUnitDTO = battleUnitMapper.toDto(updatedBattleUnit);

        restBattleUnitMockMvc
            .perform(
                put(ENTITY_API_URL_ID, battleUnitDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(battleUnitDTO))
            )
            .andExpect(status().isOk());

        // Validate the BattleUnit in the database
        List<BattleUnit> battleUnitList = battleUnitRepository.findAll();
        assertThat(battleUnitList).hasSize(databaseSizeBeforeUpdate);
        BattleUnit testBattleUnit = battleUnitList.get(battleUnitList.size() - 1);
        assertThat(testBattleUnit.getNumber()).isEqualTo(UPDATED_NUMBER);
    }

    @Test
    @Transactional
    void putNonExistingBattleUnit() throws Exception {
        int databaseSizeBeforeUpdate = battleUnitRepository.findAll().size();
        battleUnit.setId(count.incrementAndGet());

        // Create the BattleUnit
        BattleUnitDTO battleUnitDTO = battleUnitMapper.toDto(battleUnit);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restBattleUnitMockMvc
            .perform(
                put(ENTITY_API_URL_ID, battleUnitDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(battleUnitDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the BattleUnit in the database
        List<BattleUnit> battleUnitList = battleUnitRepository.findAll();
        assertThat(battleUnitList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchBattleUnit() throws Exception {
        int databaseSizeBeforeUpdate = battleUnitRepository.findAll().size();
        battleUnit.setId(count.incrementAndGet());

        // Create the BattleUnit
        BattleUnitDTO battleUnitDTO = battleUnitMapper.toDto(battleUnit);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restBattleUnitMockMvc
            .perform(
                put(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(battleUnitDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the BattleUnit in the database
        List<BattleUnit> battleUnitList = battleUnitRepository.findAll();
        assertThat(battleUnitList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamBattleUnit() throws Exception {
        int databaseSizeBeforeUpdate = battleUnitRepository.findAll().size();
        battleUnit.setId(count.incrementAndGet());

        // Create the BattleUnit
        BattleUnitDTO battleUnitDTO = battleUnitMapper.toDto(battleUnit);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restBattleUnitMockMvc
            .perform(put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(battleUnitDTO)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the BattleUnit in the database
        List<BattleUnit> battleUnitList = battleUnitRepository.findAll();
        assertThat(battleUnitList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateBattleUnitWithPatch() throws Exception {
        // Initialize the database
        battleUnitRepository.saveAndFlush(battleUnit);

        int databaseSizeBeforeUpdate = battleUnitRepository.findAll().size();

        // Update the battleUnit using partial update
        BattleUnit partialUpdatedBattleUnit = new BattleUnit();
        partialUpdatedBattleUnit.setId(battleUnit.getId());

        restBattleUnitMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedBattleUnit.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedBattleUnit))
            )
            .andExpect(status().isOk());

        // Validate the BattleUnit in the database
        List<BattleUnit> battleUnitList = battleUnitRepository.findAll();
        assertThat(battleUnitList).hasSize(databaseSizeBeforeUpdate);
        BattleUnit testBattleUnit = battleUnitList.get(battleUnitList.size() - 1);
        assertThat(testBattleUnit.getNumber()).isEqualTo(DEFAULT_NUMBER);
    }

    @Test
    @Transactional
    void fullUpdateBattleUnitWithPatch() throws Exception {
        // Initialize the database
        battleUnitRepository.saveAndFlush(battleUnit);

        int databaseSizeBeforeUpdate = battleUnitRepository.findAll().size();

        // Update the battleUnit using partial update
        BattleUnit partialUpdatedBattleUnit = new BattleUnit();
        partialUpdatedBattleUnit.setId(battleUnit.getId());

        partialUpdatedBattleUnit.number(UPDATED_NUMBER);

        restBattleUnitMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedBattleUnit.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedBattleUnit))
            )
            .andExpect(status().isOk());

        // Validate the BattleUnit in the database
        List<BattleUnit> battleUnitList = battleUnitRepository.findAll();
        assertThat(battleUnitList).hasSize(databaseSizeBeforeUpdate);
        BattleUnit testBattleUnit = battleUnitList.get(battleUnitList.size() - 1);
        assertThat(testBattleUnit.getNumber()).isEqualTo(UPDATED_NUMBER);
    }

    @Test
    @Transactional
    void patchNonExistingBattleUnit() throws Exception {
        int databaseSizeBeforeUpdate = battleUnitRepository.findAll().size();
        battleUnit.setId(count.incrementAndGet());

        // Create the BattleUnit
        BattleUnitDTO battleUnitDTO = battleUnitMapper.toDto(battleUnit);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restBattleUnitMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, battleUnitDTO.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(battleUnitDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the BattleUnit in the database
        List<BattleUnit> battleUnitList = battleUnitRepository.findAll();
        assertThat(battleUnitList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchBattleUnit() throws Exception {
        int databaseSizeBeforeUpdate = battleUnitRepository.findAll().size();
        battleUnit.setId(count.incrementAndGet());

        // Create the BattleUnit
        BattleUnitDTO battleUnitDTO = battleUnitMapper.toDto(battleUnit);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restBattleUnitMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(battleUnitDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the BattleUnit in the database
        List<BattleUnit> battleUnitList = battleUnitRepository.findAll();
        assertThat(battleUnitList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamBattleUnit() throws Exception {
        int databaseSizeBeforeUpdate = battleUnitRepository.findAll().size();
        battleUnit.setId(count.incrementAndGet());

        // Create the BattleUnit
        BattleUnitDTO battleUnitDTO = battleUnitMapper.toDto(battleUnit);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restBattleUnitMockMvc
            .perform(
                patch(ENTITY_API_URL).contentType("application/merge-patch+json").content(TestUtil.convertObjectToJsonBytes(battleUnitDTO))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the BattleUnit in the database
        List<BattleUnit> battleUnitList = battleUnitRepository.findAll();
        assertThat(battleUnitList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteBattleUnit() throws Exception {
        // Initialize the database
        battleUnitRepository.saveAndFlush(battleUnit);

        int databaseSizeBeforeDelete = battleUnitRepository.findAll().size();

        // Delete the battleUnit
        restBattleUnitMockMvc
            .perform(delete(ENTITY_API_URL_ID, battleUnit.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<BattleUnit> battleUnitList = battleUnitRepository.findAll();
        assertThat(battleUnitList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
