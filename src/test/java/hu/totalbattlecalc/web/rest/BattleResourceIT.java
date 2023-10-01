package hu.totalbattlecalc.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import hu.totalbattlecalc.IntegrationTest;
import hu.totalbattlecalc.domain.Battle;
import hu.totalbattlecalc.repository.BattleRepository;
import hu.totalbattlecalc.service.criteria.BattleCriteria;
import hu.totalbattlecalc.service.dto.BattleDTO;
import hu.totalbattlecalc.service.mapper.BattleMapper;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
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
 * Integration tests for the {@link BattleResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class BattleResourceIT {

    private static final Instant DEFAULT_DATE = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_DATE = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final String ENTITY_API_URL = "/api/battles";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private BattleRepository battleRepository;

    @Autowired
    private BattleMapper battleMapper;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restBattleMockMvc;

    private Battle battle;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Battle createEntity(EntityManager em) {
        Battle battle = new Battle().date(DEFAULT_DATE);
        return battle;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Battle createUpdatedEntity(EntityManager em) {
        Battle battle = new Battle().date(UPDATED_DATE);
        return battle;
    }

    @BeforeEach
    public void initTest() {
        battle = createEntity(em);
    }

    @Test
    @Transactional
    void createBattle() throws Exception {
        int databaseSizeBeforeCreate = battleRepository.findAll().size();
        // Create the Battle
        BattleDTO battleDTO = battleMapper.toDto(battle);
        restBattleMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(battleDTO)))
            .andExpect(status().isCreated());

        // Validate the Battle in the database
        List<Battle> battleList = battleRepository.findAll();
        assertThat(battleList).hasSize(databaseSizeBeforeCreate + 1);
        Battle testBattle = battleList.get(battleList.size() - 1);
        assertThat(testBattle.getDate()).isEqualTo(DEFAULT_DATE);
    }

    @Test
    @Transactional
    void createBattleWithExistingId() throws Exception {
        // Create the Battle with an existing ID
        battle.setId(1L);
        BattleDTO battleDTO = battleMapper.toDto(battle);

        int databaseSizeBeforeCreate = battleRepository.findAll().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        restBattleMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(battleDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Battle in the database
        List<Battle> battleList = battleRepository.findAll();
        assertThat(battleList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void getAllBattles() throws Exception {
        // Initialize the database
        battleRepository.saveAndFlush(battle);

        // Get all the battleList
        restBattleMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(battle.getId().intValue())))
            .andExpect(jsonPath("$.[*].date").value(hasItem(DEFAULT_DATE.toString())));
    }

    @Test
    @Transactional
    void getBattle() throws Exception {
        // Initialize the database
        battleRepository.saveAndFlush(battle);

        // Get the battle
        restBattleMockMvc
            .perform(get(ENTITY_API_URL_ID, battle.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(battle.getId().intValue()))
            .andExpect(jsonPath("$.date").value(DEFAULT_DATE.toString()));
    }

    @Test
    @Transactional
    void getBattlesByIdFiltering() throws Exception {
        // Initialize the database
        battleRepository.saveAndFlush(battle);

        Long id = battle.getId();

        defaultBattleShouldBeFound("id.equals=" + id);
        defaultBattleShouldNotBeFound("id.notEquals=" + id);

        defaultBattleShouldBeFound("id.greaterThanOrEqual=" + id);
        defaultBattleShouldNotBeFound("id.greaterThan=" + id);

        defaultBattleShouldBeFound("id.lessThanOrEqual=" + id);
        defaultBattleShouldNotBeFound("id.lessThan=" + id);
    }

    @Test
    @Transactional
    void getAllBattlesByDateIsEqualToSomething() throws Exception {
        // Initialize the database
        battleRepository.saveAndFlush(battle);

        // Get all the battleList where date equals to DEFAULT_DATE
        defaultBattleShouldBeFound("date.equals=" + DEFAULT_DATE);

        // Get all the battleList where date equals to UPDATED_DATE
        defaultBattleShouldNotBeFound("date.equals=" + UPDATED_DATE);
    }

    @Test
    @Transactional
    void getAllBattlesByDateIsNotEqualToSomething() throws Exception {
        // Initialize the database
        battleRepository.saveAndFlush(battle);

        // Get all the battleList where date not equals to DEFAULT_DATE
        defaultBattleShouldNotBeFound("date.notEquals=" + DEFAULT_DATE);

        // Get all the battleList where date not equals to UPDATED_DATE
        defaultBattleShouldBeFound("date.notEquals=" + UPDATED_DATE);
    }

    @Test
    @Transactional
    void getAllBattlesByDateIsInShouldWork() throws Exception {
        // Initialize the database
        battleRepository.saveAndFlush(battle);

        // Get all the battleList where date in DEFAULT_DATE or UPDATED_DATE
        defaultBattleShouldBeFound("date.in=" + DEFAULT_DATE + "," + UPDATED_DATE);

        // Get all the battleList where date equals to UPDATED_DATE
        defaultBattleShouldNotBeFound("date.in=" + UPDATED_DATE);
    }

    @Test
    @Transactional
    void getAllBattlesByDateIsNullOrNotNull() throws Exception {
        // Initialize the database
        battleRepository.saveAndFlush(battle);

        // Get all the battleList where date is not null
        defaultBattleShouldBeFound("date.specified=true");

        // Get all the battleList where date is null
        defaultBattleShouldNotBeFound("date.specified=false");
    }

    /**
     * Executes the search, and checks that the default entity is returned.
     */
    private void defaultBattleShouldBeFound(String filter) throws Exception {
        restBattleMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(battle.getId().intValue())))
            .andExpect(jsonPath("$.[*].date").value(hasItem(DEFAULT_DATE.toString())));

        // Check, that the count call also returns 1
        restBattleMockMvc
            .perform(get(ENTITY_API_URL + "/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(content().string("1"));
    }

    /**
     * Executes the search, and checks that the default entity is not returned.
     */
    private void defaultBattleShouldNotBeFound(String filter) throws Exception {
        restBattleMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$").isEmpty());

        // Check, that the count call also returns 0
        restBattleMockMvc
            .perform(get(ENTITY_API_URL + "/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(content().string("0"));
    }

    @Test
    @Transactional
    void getNonExistingBattle() throws Exception {
        // Get the battle
        restBattleMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putNewBattle() throws Exception {
        // Initialize the database
        battleRepository.saveAndFlush(battle);

        int databaseSizeBeforeUpdate = battleRepository.findAll().size();

        // Update the battle
        Battle updatedBattle = battleRepository.findById(battle.getId()).get();
        // Disconnect from session so that the updates on updatedBattle are not directly saved in db
        em.detach(updatedBattle);
        updatedBattle.date(UPDATED_DATE);
        BattleDTO battleDTO = battleMapper.toDto(updatedBattle);

        restBattleMockMvc
            .perform(
                put(ENTITY_API_URL_ID, battleDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(battleDTO))
            )
            .andExpect(status().isOk());

        // Validate the Battle in the database
        List<Battle> battleList = battleRepository.findAll();
        assertThat(battleList).hasSize(databaseSizeBeforeUpdate);
        Battle testBattle = battleList.get(battleList.size() - 1);
        assertThat(testBattle.getDate()).isEqualTo(UPDATED_DATE);
    }

    @Test
    @Transactional
    void putNonExistingBattle() throws Exception {
        int databaseSizeBeforeUpdate = battleRepository.findAll().size();
        battle.setId(count.incrementAndGet());

        // Create the Battle
        BattleDTO battleDTO = battleMapper.toDto(battle);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restBattleMockMvc
            .perform(
                put(ENTITY_API_URL_ID, battleDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(battleDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the Battle in the database
        List<Battle> battleList = battleRepository.findAll();
        assertThat(battleList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchBattle() throws Exception {
        int databaseSizeBeforeUpdate = battleRepository.findAll().size();
        battle.setId(count.incrementAndGet());

        // Create the Battle
        BattleDTO battleDTO = battleMapper.toDto(battle);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restBattleMockMvc
            .perform(
                put(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(battleDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the Battle in the database
        List<Battle> battleList = battleRepository.findAll();
        assertThat(battleList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamBattle() throws Exception {
        int databaseSizeBeforeUpdate = battleRepository.findAll().size();
        battle.setId(count.incrementAndGet());

        // Create the Battle
        BattleDTO battleDTO = battleMapper.toDto(battle);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restBattleMockMvc
            .perform(put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(battleDTO)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the Battle in the database
        List<Battle> battleList = battleRepository.findAll();
        assertThat(battleList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateBattleWithPatch() throws Exception {
        // Initialize the database
        battleRepository.saveAndFlush(battle);

        int databaseSizeBeforeUpdate = battleRepository.findAll().size();

        // Update the battle using partial update
        Battle partialUpdatedBattle = new Battle();
        partialUpdatedBattle.setId(battle.getId());

        partialUpdatedBattle.date(UPDATED_DATE);

        restBattleMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedBattle.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedBattle))
            )
            .andExpect(status().isOk());

        // Validate the Battle in the database
        List<Battle> battleList = battleRepository.findAll();
        assertThat(battleList).hasSize(databaseSizeBeforeUpdate);
        Battle testBattle = battleList.get(battleList.size() - 1);
        assertThat(testBattle.getDate()).isEqualTo(UPDATED_DATE);
    }

    @Test
    @Transactional
    void fullUpdateBattleWithPatch() throws Exception {
        // Initialize the database
        battleRepository.saveAndFlush(battle);

        int databaseSizeBeforeUpdate = battleRepository.findAll().size();

        // Update the battle using partial update
        Battle partialUpdatedBattle = new Battle();
        partialUpdatedBattle.setId(battle.getId());

        partialUpdatedBattle.date(UPDATED_DATE);

        restBattleMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedBattle.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedBattle))
            )
            .andExpect(status().isOk());

        // Validate the Battle in the database
        List<Battle> battleList = battleRepository.findAll();
        assertThat(battleList).hasSize(databaseSizeBeforeUpdate);
        Battle testBattle = battleList.get(battleList.size() - 1);
        assertThat(testBattle.getDate()).isEqualTo(UPDATED_DATE);
    }

    @Test
    @Transactional
    void patchNonExistingBattle() throws Exception {
        int databaseSizeBeforeUpdate = battleRepository.findAll().size();
        battle.setId(count.incrementAndGet());

        // Create the Battle
        BattleDTO battleDTO = battleMapper.toDto(battle);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restBattleMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, battleDTO.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(battleDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the Battle in the database
        List<Battle> battleList = battleRepository.findAll();
        assertThat(battleList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchBattle() throws Exception {
        int databaseSizeBeforeUpdate = battleRepository.findAll().size();
        battle.setId(count.incrementAndGet());

        // Create the Battle
        BattleDTO battleDTO = battleMapper.toDto(battle);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restBattleMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(battleDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the Battle in the database
        List<Battle> battleList = battleRepository.findAll();
        assertThat(battleList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamBattle() throws Exception {
        int databaseSizeBeforeUpdate = battleRepository.findAll().size();
        battle.setId(count.incrementAndGet());

        // Create the Battle
        BattleDTO battleDTO = battleMapper.toDto(battle);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restBattleMockMvc
            .perform(
                patch(ENTITY_API_URL).contentType("application/merge-patch+json").content(TestUtil.convertObjectToJsonBytes(battleDTO))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the Battle in the database
        List<Battle> battleList = battleRepository.findAll();
        assertThat(battleList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteBattle() throws Exception {
        // Initialize the database
        battleRepository.saveAndFlush(battle);

        int databaseSizeBeforeDelete = battleRepository.findAll().size();

        // Delete the battle
        restBattleMockMvc
            .perform(delete(ENTITY_API_URL_ID, battle.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Battle> battleList = battleRepository.findAll();
        assertThat(battleList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
