package hu.totalbattlecalc.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import hu.totalbattlecalc.IntegrationTest;
import hu.totalbattlecalc.domain.Feature;
import hu.totalbattlecalc.domain.Unit;
import hu.totalbattlecalc.repository.UnitRepository;
import hu.totalbattlecalc.service.UnitService;
import hu.totalbattlecalc.service.criteria.UnitCriteria;
import hu.totalbattlecalc.service.dto.UnitDTO;
import hu.totalbattlecalc.service.mapper.UnitMapper;
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
 * Integration tests for the {@link UnitResource} REST controller.
 */
@IntegrationTest
@ExtendWith(MockitoExtension.class)
@AutoConfigureMockMvc
@WithMockUser
class UnitResourceIT {

    private static final String DEFAULT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_NAME = "BBBBBBBBBB";

    private static final Integer DEFAULT_STRENGTH = 1;
    private static final Integer UPDATED_STRENGTH = 2;
    private static final Integer SMALLER_STRENGTH = 1 - 1;

    private static final Integer DEFAULT_HEALTH = 1;
    private static final Integer UPDATED_HEALTH = 2;
    private static final Integer SMALLER_HEALTH = 1 - 1;

    private static final Integer DEFAULT_LEADERSHIP = 1;
    private static final Integer UPDATED_LEADERSHIP = 2;
    private static final Integer SMALLER_LEADERSHIP = 1 - 1;

    private static final Integer DEFAULT_SPEED = 1;
    private static final Integer UPDATED_SPEED = 2;
    private static final Integer SMALLER_SPEED = 1 - 1;

    private static final Integer DEFAULT_INITIATIVE = 1;
    private static final Integer UPDATED_INITIATIVE = 2;
    private static final Integer SMALLER_INITIATIVE = 1 - 1;

    private static final Integer DEFAULT_FOOD_CONSUMPTION = 1;
    private static final Integer UPDATED_FOOD_CONSUMPTION = 2;
    private static final Integer SMALLER_FOOD_CONSUMPTION = 1 - 1;

    private static final Integer DEFAULT_CARRYING_CAPACITY = 1;
    private static final Integer UPDATED_CARRYING_CAPACITY = 2;
    private static final Integer SMALLER_CARRYING_CAPACITY = 1 - 1;

    private static final Integer DEFAULT_REVIVAL_COST_AFTER_AN_ATTACK_GOLD = 1;
    private static final Integer UPDATED_REVIVAL_COST_AFTER_AN_ATTACK_GOLD = 2;
    private static final Integer SMALLER_REVIVAL_COST_AFTER_AN_ATTACK_GOLD = 1 - 1;

    private static final Integer DEFAULT_REVIVAL_COST_AFTER_DEFENDING_SILVER = 1;
    private static final Integer UPDATED_REVIVAL_COST_AFTER_DEFENDING_SILVER = 2;
    private static final Integer SMALLER_REVIVAL_COST_AFTER_DEFENDING_SILVER = 1 - 1;

    private static final String ENTITY_API_URL = "/api/units";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private UnitRepository unitRepository;

    @Mock
    private UnitRepository unitRepositoryMock;

    @Autowired
    private UnitMapper unitMapper;

    @Mock
    private UnitService unitServiceMock;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restUnitMockMvc;

    private Unit unit;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Unit createEntity(EntityManager em) {
        Unit unit = new Unit()
            .name(DEFAULT_NAME)
            .strength(DEFAULT_STRENGTH)
            .health(DEFAULT_HEALTH)
            .leadership(DEFAULT_LEADERSHIP)
            .speed(DEFAULT_SPEED)
            .initiative(DEFAULT_INITIATIVE)
            .foodConsumption(DEFAULT_FOOD_CONSUMPTION)
            .carryingCapacity(DEFAULT_CARRYING_CAPACITY)
            .revivalCostAfterAnAttackGold(DEFAULT_REVIVAL_COST_AFTER_AN_ATTACK_GOLD)
            .revivalCostAfterDefendingSilver(DEFAULT_REVIVAL_COST_AFTER_DEFENDING_SILVER);
        return unit;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Unit createUpdatedEntity(EntityManager em) {
        Unit unit = new Unit()
            .name(UPDATED_NAME)
            .strength(UPDATED_STRENGTH)
            .health(UPDATED_HEALTH)
            .leadership(UPDATED_LEADERSHIP)
            .speed(UPDATED_SPEED)
            .initiative(UPDATED_INITIATIVE)
            .foodConsumption(UPDATED_FOOD_CONSUMPTION)
            .carryingCapacity(UPDATED_CARRYING_CAPACITY)
            .revivalCostAfterAnAttackGold(UPDATED_REVIVAL_COST_AFTER_AN_ATTACK_GOLD)
            .revivalCostAfterDefendingSilver(UPDATED_REVIVAL_COST_AFTER_DEFENDING_SILVER);
        return unit;
    }

    @BeforeEach
    public void initTest() {
        unit = createEntity(em);
    }

    @Test
    @Transactional
    void createUnit() throws Exception {
        int databaseSizeBeforeCreate = unitRepository.findAll().size();
        // Create the Unit
        UnitDTO unitDTO = unitMapper.toDto(unit);
        restUnitMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(unitDTO)))
            .andExpect(status().isCreated());

        // Validate the Unit in the database
        List<Unit> unitList = unitRepository.findAll();
        assertThat(unitList).hasSize(databaseSizeBeforeCreate + 1);
        Unit testUnit = unitList.get(unitList.size() - 1);
        assertThat(testUnit.getName()).isEqualTo(DEFAULT_NAME);
        assertThat(testUnit.getStrength()).isEqualTo(DEFAULT_STRENGTH);
        assertThat(testUnit.getHealth()).isEqualTo(DEFAULT_HEALTH);
        assertThat(testUnit.getLeadership()).isEqualTo(DEFAULT_LEADERSHIP);
        assertThat(testUnit.getSpeed()).isEqualTo(DEFAULT_SPEED);
        assertThat(testUnit.getInitiative()).isEqualTo(DEFAULT_INITIATIVE);
        assertThat(testUnit.getFoodConsumption()).isEqualTo(DEFAULT_FOOD_CONSUMPTION);
        assertThat(testUnit.getCarryingCapacity()).isEqualTo(DEFAULT_CARRYING_CAPACITY);
        assertThat(testUnit.getRevivalCostAfterAnAttackGold()).isEqualTo(DEFAULT_REVIVAL_COST_AFTER_AN_ATTACK_GOLD);
        assertThat(testUnit.getRevivalCostAfterDefendingSilver()).isEqualTo(DEFAULT_REVIVAL_COST_AFTER_DEFENDING_SILVER);
    }

    @Test
    @Transactional
    void createUnitWithExistingId() throws Exception {
        // Create the Unit with an existing ID
        unit.setId(1L);
        UnitDTO unitDTO = unitMapper.toDto(unit);

        int databaseSizeBeforeCreate = unitRepository.findAll().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        restUnitMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(unitDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Unit in the database
        List<Unit> unitList = unitRepository.findAll();
        assertThat(unitList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void getAllUnits() throws Exception {
        // Initialize the database
        unitRepository.saveAndFlush(unit);

        // Get all the unitList
        restUnitMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(unit.getId().intValue())))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME)))
            .andExpect(jsonPath("$.[*].strength").value(hasItem(DEFAULT_STRENGTH)))
            .andExpect(jsonPath("$.[*].health").value(hasItem(DEFAULT_HEALTH)))
            .andExpect(jsonPath("$.[*].leadership").value(hasItem(DEFAULT_LEADERSHIP)))
            .andExpect(jsonPath("$.[*].speed").value(hasItem(DEFAULT_SPEED)))
            .andExpect(jsonPath("$.[*].initiative").value(hasItem(DEFAULT_INITIATIVE)))
            .andExpect(jsonPath("$.[*].foodConsumption").value(hasItem(DEFAULT_FOOD_CONSUMPTION)))
            .andExpect(jsonPath("$.[*].carryingCapacity").value(hasItem(DEFAULT_CARRYING_CAPACITY)))
            .andExpect(jsonPath("$.[*].revivalCostAfterAnAttackGold").value(hasItem(DEFAULT_REVIVAL_COST_AFTER_AN_ATTACK_GOLD)))
            .andExpect(jsonPath("$.[*].revivalCostAfterDefendingSilver").value(hasItem(DEFAULT_REVIVAL_COST_AFTER_DEFENDING_SILVER)));
    }

    @SuppressWarnings({ "unchecked" })
    void getAllUnitsWithEagerRelationshipsIsEnabled() throws Exception {
        when(unitServiceMock.findAllWithEagerRelationships(any())).thenReturn(new PageImpl(new ArrayList<>()));

        restUnitMockMvc.perform(get(ENTITY_API_URL + "?eagerload=true")).andExpect(status().isOk());

        verify(unitServiceMock, times(1)).findAllWithEagerRelationships(any());
    }

    @SuppressWarnings({ "unchecked" })
    void getAllUnitsWithEagerRelationshipsIsNotEnabled() throws Exception {
        when(unitServiceMock.findAllWithEagerRelationships(any())).thenReturn(new PageImpl(new ArrayList<>()));

        restUnitMockMvc.perform(get(ENTITY_API_URL + "?eagerload=true")).andExpect(status().isOk());

        verify(unitServiceMock, times(1)).findAllWithEagerRelationships(any());
    }

    @Test
    @Transactional
    void getUnit() throws Exception {
        // Initialize the database
        unitRepository.saveAndFlush(unit);

        // Get the unit
        restUnitMockMvc
            .perform(get(ENTITY_API_URL_ID, unit.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(unit.getId().intValue()))
            .andExpect(jsonPath("$.name").value(DEFAULT_NAME))
            .andExpect(jsonPath("$.strength").value(DEFAULT_STRENGTH))
            .andExpect(jsonPath("$.health").value(DEFAULT_HEALTH))
            .andExpect(jsonPath("$.leadership").value(DEFAULT_LEADERSHIP))
            .andExpect(jsonPath("$.speed").value(DEFAULT_SPEED))
            .andExpect(jsonPath("$.initiative").value(DEFAULT_INITIATIVE))
            .andExpect(jsonPath("$.foodConsumption").value(DEFAULT_FOOD_CONSUMPTION))
            .andExpect(jsonPath("$.carryingCapacity").value(DEFAULT_CARRYING_CAPACITY))
            .andExpect(jsonPath("$.revivalCostAfterAnAttackGold").value(DEFAULT_REVIVAL_COST_AFTER_AN_ATTACK_GOLD))
            .andExpect(jsonPath("$.revivalCostAfterDefendingSilver").value(DEFAULT_REVIVAL_COST_AFTER_DEFENDING_SILVER));
    }

    @Test
    @Transactional
    void getUnitsByIdFiltering() throws Exception {
        // Initialize the database
        unitRepository.saveAndFlush(unit);

        Long id = unit.getId();

        defaultUnitShouldBeFound("id.equals=" + id);
        defaultUnitShouldNotBeFound("id.notEquals=" + id);

        defaultUnitShouldBeFound("id.greaterThanOrEqual=" + id);
        defaultUnitShouldNotBeFound("id.greaterThan=" + id);

        defaultUnitShouldBeFound("id.lessThanOrEqual=" + id);
        defaultUnitShouldNotBeFound("id.lessThan=" + id);
    }

    @Test
    @Transactional
    void getAllUnitsByNameIsEqualToSomething() throws Exception {
        // Initialize the database
        unitRepository.saveAndFlush(unit);

        // Get all the unitList where name equals to DEFAULT_NAME
        defaultUnitShouldBeFound("name.equals=" + DEFAULT_NAME);

        // Get all the unitList where name equals to UPDATED_NAME
        defaultUnitShouldNotBeFound("name.equals=" + UPDATED_NAME);
    }

    @Test
    @Transactional
    void getAllUnitsByNameIsNotEqualToSomething() throws Exception {
        // Initialize the database
        unitRepository.saveAndFlush(unit);

        // Get all the unitList where name not equals to DEFAULT_NAME
        defaultUnitShouldNotBeFound("name.notEquals=" + DEFAULT_NAME);

        // Get all the unitList where name not equals to UPDATED_NAME
        defaultUnitShouldBeFound("name.notEquals=" + UPDATED_NAME);
    }

    @Test
    @Transactional
    void getAllUnitsByNameIsInShouldWork() throws Exception {
        // Initialize the database
        unitRepository.saveAndFlush(unit);

        // Get all the unitList where name in DEFAULT_NAME or UPDATED_NAME
        defaultUnitShouldBeFound("name.in=" + DEFAULT_NAME + "," + UPDATED_NAME);

        // Get all the unitList where name equals to UPDATED_NAME
        defaultUnitShouldNotBeFound("name.in=" + UPDATED_NAME);
    }

    @Test
    @Transactional
    void getAllUnitsByNameIsNullOrNotNull() throws Exception {
        // Initialize the database
        unitRepository.saveAndFlush(unit);

        // Get all the unitList where name is not null
        defaultUnitShouldBeFound("name.specified=true");

        // Get all the unitList where name is null
        defaultUnitShouldNotBeFound("name.specified=false");
    }

    @Test
    @Transactional
    void getAllUnitsByNameContainsSomething() throws Exception {
        // Initialize the database
        unitRepository.saveAndFlush(unit);

        // Get all the unitList where name contains DEFAULT_NAME
        defaultUnitShouldBeFound("name.contains=" + DEFAULT_NAME);

        // Get all the unitList where name contains UPDATED_NAME
        defaultUnitShouldNotBeFound("name.contains=" + UPDATED_NAME);
    }

    @Test
    @Transactional
    void getAllUnitsByNameNotContainsSomething() throws Exception {
        // Initialize the database
        unitRepository.saveAndFlush(unit);

        // Get all the unitList where name does not contain DEFAULT_NAME
        defaultUnitShouldNotBeFound("name.doesNotContain=" + DEFAULT_NAME);

        // Get all the unitList where name does not contain UPDATED_NAME
        defaultUnitShouldBeFound("name.doesNotContain=" + UPDATED_NAME);
    }

    @Test
    @Transactional
    void getAllUnitsByStrengthIsEqualToSomething() throws Exception {
        // Initialize the database
        unitRepository.saveAndFlush(unit);

        // Get all the unitList where strength equals to DEFAULT_STRENGTH
        defaultUnitShouldBeFound("strength.equals=" + DEFAULT_STRENGTH);

        // Get all the unitList where strength equals to UPDATED_STRENGTH
        defaultUnitShouldNotBeFound("strength.equals=" + UPDATED_STRENGTH);
    }

    @Test
    @Transactional
    void getAllUnitsByStrengthIsNotEqualToSomething() throws Exception {
        // Initialize the database
        unitRepository.saveAndFlush(unit);

        // Get all the unitList where strength not equals to DEFAULT_STRENGTH
        defaultUnitShouldNotBeFound("strength.notEquals=" + DEFAULT_STRENGTH);

        // Get all the unitList where strength not equals to UPDATED_STRENGTH
        defaultUnitShouldBeFound("strength.notEquals=" + UPDATED_STRENGTH);
    }

    @Test
    @Transactional
    void getAllUnitsByStrengthIsInShouldWork() throws Exception {
        // Initialize the database
        unitRepository.saveAndFlush(unit);

        // Get all the unitList where strength in DEFAULT_STRENGTH or UPDATED_STRENGTH
        defaultUnitShouldBeFound("strength.in=" + DEFAULT_STRENGTH + "," + UPDATED_STRENGTH);

        // Get all the unitList where strength equals to UPDATED_STRENGTH
        defaultUnitShouldNotBeFound("strength.in=" + UPDATED_STRENGTH);
    }

    @Test
    @Transactional
    void getAllUnitsByStrengthIsNullOrNotNull() throws Exception {
        // Initialize the database
        unitRepository.saveAndFlush(unit);

        // Get all the unitList where strength is not null
        defaultUnitShouldBeFound("strength.specified=true");

        // Get all the unitList where strength is null
        defaultUnitShouldNotBeFound("strength.specified=false");
    }

    @Test
    @Transactional
    void getAllUnitsByStrengthIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        unitRepository.saveAndFlush(unit);

        // Get all the unitList where strength is greater than or equal to DEFAULT_STRENGTH
        defaultUnitShouldBeFound("strength.greaterThanOrEqual=" + DEFAULT_STRENGTH);

        // Get all the unitList where strength is greater than or equal to UPDATED_STRENGTH
        defaultUnitShouldNotBeFound("strength.greaterThanOrEqual=" + UPDATED_STRENGTH);
    }

    @Test
    @Transactional
    void getAllUnitsByStrengthIsLessThanOrEqualToSomething() throws Exception {
        // Initialize the database
        unitRepository.saveAndFlush(unit);

        // Get all the unitList where strength is less than or equal to DEFAULT_STRENGTH
        defaultUnitShouldBeFound("strength.lessThanOrEqual=" + DEFAULT_STRENGTH);

        // Get all the unitList where strength is less than or equal to SMALLER_STRENGTH
        defaultUnitShouldNotBeFound("strength.lessThanOrEqual=" + SMALLER_STRENGTH);
    }

    @Test
    @Transactional
    void getAllUnitsByStrengthIsLessThanSomething() throws Exception {
        // Initialize the database
        unitRepository.saveAndFlush(unit);

        // Get all the unitList where strength is less than DEFAULT_STRENGTH
        defaultUnitShouldNotBeFound("strength.lessThan=" + DEFAULT_STRENGTH);

        // Get all the unitList where strength is less than UPDATED_STRENGTH
        defaultUnitShouldBeFound("strength.lessThan=" + UPDATED_STRENGTH);
    }

    @Test
    @Transactional
    void getAllUnitsByStrengthIsGreaterThanSomething() throws Exception {
        // Initialize the database
        unitRepository.saveAndFlush(unit);

        // Get all the unitList where strength is greater than DEFAULT_STRENGTH
        defaultUnitShouldNotBeFound("strength.greaterThan=" + DEFAULT_STRENGTH);

        // Get all the unitList where strength is greater than SMALLER_STRENGTH
        defaultUnitShouldBeFound("strength.greaterThan=" + SMALLER_STRENGTH);
    }

    @Test
    @Transactional
    void getAllUnitsByHealthIsEqualToSomething() throws Exception {
        // Initialize the database
        unitRepository.saveAndFlush(unit);

        // Get all the unitList where health equals to DEFAULT_HEALTH
        defaultUnitShouldBeFound("health.equals=" + DEFAULT_HEALTH);

        // Get all the unitList where health equals to UPDATED_HEALTH
        defaultUnitShouldNotBeFound("health.equals=" + UPDATED_HEALTH);
    }

    @Test
    @Transactional
    void getAllUnitsByHealthIsNotEqualToSomething() throws Exception {
        // Initialize the database
        unitRepository.saveAndFlush(unit);

        // Get all the unitList where health not equals to DEFAULT_HEALTH
        defaultUnitShouldNotBeFound("health.notEquals=" + DEFAULT_HEALTH);

        // Get all the unitList where health not equals to UPDATED_HEALTH
        defaultUnitShouldBeFound("health.notEquals=" + UPDATED_HEALTH);
    }

    @Test
    @Transactional
    void getAllUnitsByHealthIsInShouldWork() throws Exception {
        // Initialize the database
        unitRepository.saveAndFlush(unit);

        // Get all the unitList where health in DEFAULT_HEALTH or UPDATED_HEALTH
        defaultUnitShouldBeFound("health.in=" + DEFAULT_HEALTH + "," + UPDATED_HEALTH);

        // Get all the unitList where health equals to UPDATED_HEALTH
        defaultUnitShouldNotBeFound("health.in=" + UPDATED_HEALTH);
    }

    @Test
    @Transactional
    void getAllUnitsByHealthIsNullOrNotNull() throws Exception {
        // Initialize the database
        unitRepository.saveAndFlush(unit);

        // Get all the unitList where health is not null
        defaultUnitShouldBeFound("health.specified=true");

        // Get all the unitList where health is null
        defaultUnitShouldNotBeFound("health.specified=false");
    }

    @Test
    @Transactional
    void getAllUnitsByHealthIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        unitRepository.saveAndFlush(unit);

        // Get all the unitList where health is greater than or equal to DEFAULT_HEALTH
        defaultUnitShouldBeFound("health.greaterThanOrEqual=" + DEFAULT_HEALTH);

        // Get all the unitList where health is greater than or equal to UPDATED_HEALTH
        defaultUnitShouldNotBeFound("health.greaterThanOrEqual=" + UPDATED_HEALTH);
    }

    @Test
    @Transactional
    void getAllUnitsByHealthIsLessThanOrEqualToSomething() throws Exception {
        // Initialize the database
        unitRepository.saveAndFlush(unit);

        // Get all the unitList where health is less than or equal to DEFAULT_HEALTH
        defaultUnitShouldBeFound("health.lessThanOrEqual=" + DEFAULT_HEALTH);

        // Get all the unitList where health is less than or equal to SMALLER_HEALTH
        defaultUnitShouldNotBeFound("health.lessThanOrEqual=" + SMALLER_HEALTH);
    }

    @Test
    @Transactional
    void getAllUnitsByHealthIsLessThanSomething() throws Exception {
        // Initialize the database
        unitRepository.saveAndFlush(unit);

        // Get all the unitList where health is less than DEFAULT_HEALTH
        defaultUnitShouldNotBeFound("health.lessThan=" + DEFAULT_HEALTH);

        // Get all the unitList where health is less than UPDATED_HEALTH
        defaultUnitShouldBeFound("health.lessThan=" + UPDATED_HEALTH);
    }

    @Test
    @Transactional
    void getAllUnitsByHealthIsGreaterThanSomething() throws Exception {
        // Initialize the database
        unitRepository.saveAndFlush(unit);

        // Get all the unitList where health is greater than DEFAULT_HEALTH
        defaultUnitShouldNotBeFound("health.greaterThan=" + DEFAULT_HEALTH);

        // Get all the unitList where health is greater than SMALLER_HEALTH
        defaultUnitShouldBeFound("health.greaterThan=" + SMALLER_HEALTH);
    }

    @Test
    @Transactional
    void getAllUnitsByLeadershipIsEqualToSomething() throws Exception {
        // Initialize the database
        unitRepository.saveAndFlush(unit);

        // Get all the unitList where leadership equals to DEFAULT_LEADERSHIP
        defaultUnitShouldBeFound("leadership.equals=" + DEFAULT_LEADERSHIP);

        // Get all the unitList where leadership equals to UPDATED_LEADERSHIP
        defaultUnitShouldNotBeFound("leadership.equals=" + UPDATED_LEADERSHIP);
    }

    @Test
    @Transactional
    void getAllUnitsByLeadershipIsNotEqualToSomething() throws Exception {
        // Initialize the database
        unitRepository.saveAndFlush(unit);

        // Get all the unitList where leadership not equals to DEFAULT_LEADERSHIP
        defaultUnitShouldNotBeFound("leadership.notEquals=" + DEFAULT_LEADERSHIP);

        // Get all the unitList where leadership not equals to UPDATED_LEADERSHIP
        defaultUnitShouldBeFound("leadership.notEquals=" + UPDATED_LEADERSHIP);
    }

    @Test
    @Transactional
    void getAllUnitsByLeadershipIsInShouldWork() throws Exception {
        // Initialize the database
        unitRepository.saveAndFlush(unit);

        // Get all the unitList where leadership in DEFAULT_LEADERSHIP or UPDATED_LEADERSHIP
        defaultUnitShouldBeFound("leadership.in=" + DEFAULT_LEADERSHIP + "," + UPDATED_LEADERSHIP);

        // Get all the unitList where leadership equals to UPDATED_LEADERSHIP
        defaultUnitShouldNotBeFound("leadership.in=" + UPDATED_LEADERSHIP);
    }

    @Test
    @Transactional
    void getAllUnitsByLeadershipIsNullOrNotNull() throws Exception {
        // Initialize the database
        unitRepository.saveAndFlush(unit);

        // Get all the unitList where leadership is not null
        defaultUnitShouldBeFound("leadership.specified=true");

        // Get all the unitList where leadership is null
        defaultUnitShouldNotBeFound("leadership.specified=false");
    }

    @Test
    @Transactional
    void getAllUnitsByLeadershipIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        unitRepository.saveAndFlush(unit);

        // Get all the unitList where leadership is greater than or equal to DEFAULT_LEADERSHIP
        defaultUnitShouldBeFound("leadership.greaterThanOrEqual=" + DEFAULT_LEADERSHIP);

        // Get all the unitList where leadership is greater than or equal to UPDATED_LEADERSHIP
        defaultUnitShouldNotBeFound("leadership.greaterThanOrEqual=" + UPDATED_LEADERSHIP);
    }

    @Test
    @Transactional
    void getAllUnitsByLeadershipIsLessThanOrEqualToSomething() throws Exception {
        // Initialize the database
        unitRepository.saveAndFlush(unit);

        // Get all the unitList where leadership is less than or equal to DEFAULT_LEADERSHIP
        defaultUnitShouldBeFound("leadership.lessThanOrEqual=" + DEFAULT_LEADERSHIP);

        // Get all the unitList where leadership is less than or equal to SMALLER_LEADERSHIP
        defaultUnitShouldNotBeFound("leadership.lessThanOrEqual=" + SMALLER_LEADERSHIP);
    }

    @Test
    @Transactional
    void getAllUnitsByLeadershipIsLessThanSomething() throws Exception {
        // Initialize the database
        unitRepository.saveAndFlush(unit);

        // Get all the unitList where leadership is less than DEFAULT_LEADERSHIP
        defaultUnitShouldNotBeFound("leadership.lessThan=" + DEFAULT_LEADERSHIP);

        // Get all the unitList where leadership is less than UPDATED_LEADERSHIP
        defaultUnitShouldBeFound("leadership.lessThan=" + UPDATED_LEADERSHIP);
    }

    @Test
    @Transactional
    void getAllUnitsByLeadershipIsGreaterThanSomething() throws Exception {
        // Initialize the database
        unitRepository.saveAndFlush(unit);

        // Get all the unitList where leadership is greater than DEFAULT_LEADERSHIP
        defaultUnitShouldNotBeFound("leadership.greaterThan=" + DEFAULT_LEADERSHIP);

        // Get all the unitList where leadership is greater than SMALLER_LEADERSHIP
        defaultUnitShouldBeFound("leadership.greaterThan=" + SMALLER_LEADERSHIP);
    }

    @Test
    @Transactional
    void getAllUnitsBySpeedIsEqualToSomething() throws Exception {
        // Initialize the database
        unitRepository.saveAndFlush(unit);

        // Get all the unitList where speed equals to DEFAULT_SPEED
        defaultUnitShouldBeFound("speed.equals=" + DEFAULT_SPEED);

        // Get all the unitList where speed equals to UPDATED_SPEED
        defaultUnitShouldNotBeFound("speed.equals=" + UPDATED_SPEED);
    }

    @Test
    @Transactional
    void getAllUnitsBySpeedIsNotEqualToSomething() throws Exception {
        // Initialize the database
        unitRepository.saveAndFlush(unit);

        // Get all the unitList where speed not equals to DEFAULT_SPEED
        defaultUnitShouldNotBeFound("speed.notEquals=" + DEFAULT_SPEED);

        // Get all the unitList where speed not equals to UPDATED_SPEED
        defaultUnitShouldBeFound("speed.notEquals=" + UPDATED_SPEED);
    }

    @Test
    @Transactional
    void getAllUnitsBySpeedIsInShouldWork() throws Exception {
        // Initialize the database
        unitRepository.saveAndFlush(unit);

        // Get all the unitList where speed in DEFAULT_SPEED or UPDATED_SPEED
        defaultUnitShouldBeFound("speed.in=" + DEFAULT_SPEED + "," + UPDATED_SPEED);

        // Get all the unitList where speed equals to UPDATED_SPEED
        defaultUnitShouldNotBeFound("speed.in=" + UPDATED_SPEED);
    }

    @Test
    @Transactional
    void getAllUnitsBySpeedIsNullOrNotNull() throws Exception {
        // Initialize the database
        unitRepository.saveAndFlush(unit);

        // Get all the unitList where speed is not null
        defaultUnitShouldBeFound("speed.specified=true");

        // Get all the unitList where speed is null
        defaultUnitShouldNotBeFound("speed.specified=false");
    }

    @Test
    @Transactional
    void getAllUnitsBySpeedIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        unitRepository.saveAndFlush(unit);

        // Get all the unitList where speed is greater than or equal to DEFAULT_SPEED
        defaultUnitShouldBeFound("speed.greaterThanOrEqual=" + DEFAULT_SPEED);

        // Get all the unitList where speed is greater than or equal to UPDATED_SPEED
        defaultUnitShouldNotBeFound("speed.greaterThanOrEqual=" + UPDATED_SPEED);
    }

    @Test
    @Transactional
    void getAllUnitsBySpeedIsLessThanOrEqualToSomething() throws Exception {
        // Initialize the database
        unitRepository.saveAndFlush(unit);

        // Get all the unitList where speed is less than or equal to DEFAULT_SPEED
        defaultUnitShouldBeFound("speed.lessThanOrEqual=" + DEFAULT_SPEED);

        // Get all the unitList where speed is less than or equal to SMALLER_SPEED
        defaultUnitShouldNotBeFound("speed.lessThanOrEqual=" + SMALLER_SPEED);
    }

    @Test
    @Transactional
    void getAllUnitsBySpeedIsLessThanSomething() throws Exception {
        // Initialize the database
        unitRepository.saveAndFlush(unit);

        // Get all the unitList where speed is less than DEFAULT_SPEED
        defaultUnitShouldNotBeFound("speed.lessThan=" + DEFAULT_SPEED);

        // Get all the unitList where speed is less than UPDATED_SPEED
        defaultUnitShouldBeFound("speed.lessThan=" + UPDATED_SPEED);
    }

    @Test
    @Transactional
    void getAllUnitsBySpeedIsGreaterThanSomething() throws Exception {
        // Initialize the database
        unitRepository.saveAndFlush(unit);

        // Get all the unitList where speed is greater than DEFAULT_SPEED
        defaultUnitShouldNotBeFound("speed.greaterThan=" + DEFAULT_SPEED);

        // Get all the unitList where speed is greater than SMALLER_SPEED
        defaultUnitShouldBeFound("speed.greaterThan=" + SMALLER_SPEED);
    }

    @Test
    @Transactional
    void getAllUnitsByInitiativeIsEqualToSomething() throws Exception {
        // Initialize the database
        unitRepository.saveAndFlush(unit);

        // Get all the unitList where initiative equals to DEFAULT_INITIATIVE
        defaultUnitShouldBeFound("initiative.equals=" + DEFAULT_INITIATIVE);

        // Get all the unitList where initiative equals to UPDATED_INITIATIVE
        defaultUnitShouldNotBeFound("initiative.equals=" + UPDATED_INITIATIVE);
    }

    @Test
    @Transactional
    void getAllUnitsByInitiativeIsNotEqualToSomething() throws Exception {
        // Initialize the database
        unitRepository.saveAndFlush(unit);

        // Get all the unitList where initiative not equals to DEFAULT_INITIATIVE
        defaultUnitShouldNotBeFound("initiative.notEquals=" + DEFAULT_INITIATIVE);

        // Get all the unitList where initiative not equals to UPDATED_INITIATIVE
        defaultUnitShouldBeFound("initiative.notEquals=" + UPDATED_INITIATIVE);
    }

    @Test
    @Transactional
    void getAllUnitsByInitiativeIsInShouldWork() throws Exception {
        // Initialize the database
        unitRepository.saveAndFlush(unit);

        // Get all the unitList where initiative in DEFAULT_INITIATIVE or UPDATED_INITIATIVE
        defaultUnitShouldBeFound("initiative.in=" + DEFAULT_INITIATIVE + "," + UPDATED_INITIATIVE);

        // Get all the unitList where initiative equals to UPDATED_INITIATIVE
        defaultUnitShouldNotBeFound("initiative.in=" + UPDATED_INITIATIVE);
    }

    @Test
    @Transactional
    void getAllUnitsByInitiativeIsNullOrNotNull() throws Exception {
        // Initialize the database
        unitRepository.saveAndFlush(unit);

        // Get all the unitList where initiative is not null
        defaultUnitShouldBeFound("initiative.specified=true");

        // Get all the unitList where initiative is null
        defaultUnitShouldNotBeFound("initiative.specified=false");
    }

    @Test
    @Transactional
    void getAllUnitsByInitiativeIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        unitRepository.saveAndFlush(unit);

        // Get all the unitList where initiative is greater than or equal to DEFAULT_INITIATIVE
        defaultUnitShouldBeFound("initiative.greaterThanOrEqual=" + DEFAULT_INITIATIVE);

        // Get all the unitList where initiative is greater than or equal to UPDATED_INITIATIVE
        defaultUnitShouldNotBeFound("initiative.greaterThanOrEqual=" + UPDATED_INITIATIVE);
    }

    @Test
    @Transactional
    void getAllUnitsByInitiativeIsLessThanOrEqualToSomething() throws Exception {
        // Initialize the database
        unitRepository.saveAndFlush(unit);

        // Get all the unitList where initiative is less than or equal to DEFAULT_INITIATIVE
        defaultUnitShouldBeFound("initiative.lessThanOrEqual=" + DEFAULT_INITIATIVE);

        // Get all the unitList where initiative is less than or equal to SMALLER_INITIATIVE
        defaultUnitShouldNotBeFound("initiative.lessThanOrEqual=" + SMALLER_INITIATIVE);
    }

    @Test
    @Transactional
    void getAllUnitsByInitiativeIsLessThanSomething() throws Exception {
        // Initialize the database
        unitRepository.saveAndFlush(unit);

        // Get all the unitList where initiative is less than DEFAULT_INITIATIVE
        defaultUnitShouldNotBeFound("initiative.lessThan=" + DEFAULT_INITIATIVE);

        // Get all the unitList where initiative is less than UPDATED_INITIATIVE
        defaultUnitShouldBeFound("initiative.lessThan=" + UPDATED_INITIATIVE);
    }

    @Test
    @Transactional
    void getAllUnitsByInitiativeIsGreaterThanSomething() throws Exception {
        // Initialize the database
        unitRepository.saveAndFlush(unit);

        // Get all the unitList where initiative is greater than DEFAULT_INITIATIVE
        defaultUnitShouldNotBeFound("initiative.greaterThan=" + DEFAULT_INITIATIVE);

        // Get all the unitList where initiative is greater than SMALLER_INITIATIVE
        defaultUnitShouldBeFound("initiative.greaterThan=" + SMALLER_INITIATIVE);
    }

    @Test
    @Transactional
    void getAllUnitsByFoodConsumptionIsEqualToSomething() throws Exception {
        // Initialize the database
        unitRepository.saveAndFlush(unit);

        // Get all the unitList where foodConsumption equals to DEFAULT_FOOD_CONSUMPTION
        defaultUnitShouldBeFound("foodConsumption.equals=" + DEFAULT_FOOD_CONSUMPTION);

        // Get all the unitList where foodConsumption equals to UPDATED_FOOD_CONSUMPTION
        defaultUnitShouldNotBeFound("foodConsumption.equals=" + UPDATED_FOOD_CONSUMPTION);
    }

    @Test
    @Transactional
    void getAllUnitsByFoodConsumptionIsNotEqualToSomething() throws Exception {
        // Initialize the database
        unitRepository.saveAndFlush(unit);

        // Get all the unitList where foodConsumption not equals to DEFAULT_FOOD_CONSUMPTION
        defaultUnitShouldNotBeFound("foodConsumption.notEquals=" + DEFAULT_FOOD_CONSUMPTION);

        // Get all the unitList where foodConsumption not equals to UPDATED_FOOD_CONSUMPTION
        defaultUnitShouldBeFound("foodConsumption.notEquals=" + UPDATED_FOOD_CONSUMPTION);
    }

    @Test
    @Transactional
    void getAllUnitsByFoodConsumptionIsInShouldWork() throws Exception {
        // Initialize the database
        unitRepository.saveAndFlush(unit);

        // Get all the unitList where foodConsumption in DEFAULT_FOOD_CONSUMPTION or UPDATED_FOOD_CONSUMPTION
        defaultUnitShouldBeFound("foodConsumption.in=" + DEFAULT_FOOD_CONSUMPTION + "," + UPDATED_FOOD_CONSUMPTION);

        // Get all the unitList where foodConsumption equals to UPDATED_FOOD_CONSUMPTION
        defaultUnitShouldNotBeFound("foodConsumption.in=" + UPDATED_FOOD_CONSUMPTION);
    }

    @Test
    @Transactional
    void getAllUnitsByFoodConsumptionIsNullOrNotNull() throws Exception {
        // Initialize the database
        unitRepository.saveAndFlush(unit);

        // Get all the unitList where foodConsumption is not null
        defaultUnitShouldBeFound("foodConsumption.specified=true");

        // Get all the unitList where foodConsumption is null
        defaultUnitShouldNotBeFound("foodConsumption.specified=false");
    }

    @Test
    @Transactional
    void getAllUnitsByFoodConsumptionIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        unitRepository.saveAndFlush(unit);

        // Get all the unitList where foodConsumption is greater than or equal to DEFAULT_FOOD_CONSUMPTION
        defaultUnitShouldBeFound("foodConsumption.greaterThanOrEqual=" + DEFAULT_FOOD_CONSUMPTION);

        // Get all the unitList where foodConsumption is greater than or equal to UPDATED_FOOD_CONSUMPTION
        defaultUnitShouldNotBeFound("foodConsumption.greaterThanOrEqual=" + UPDATED_FOOD_CONSUMPTION);
    }

    @Test
    @Transactional
    void getAllUnitsByFoodConsumptionIsLessThanOrEqualToSomething() throws Exception {
        // Initialize the database
        unitRepository.saveAndFlush(unit);

        // Get all the unitList where foodConsumption is less than or equal to DEFAULT_FOOD_CONSUMPTION
        defaultUnitShouldBeFound("foodConsumption.lessThanOrEqual=" + DEFAULT_FOOD_CONSUMPTION);

        // Get all the unitList where foodConsumption is less than or equal to SMALLER_FOOD_CONSUMPTION
        defaultUnitShouldNotBeFound("foodConsumption.lessThanOrEqual=" + SMALLER_FOOD_CONSUMPTION);
    }

    @Test
    @Transactional
    void getAllUnitsByFoodConsumptionIsLessThanSomething() throws Exception {
        // Initialize the database
        unitRepository.saveAndFlush(unit);

        // Get all the unitList where foodConsumption is less than DEFAULT_FOOD_CONSUMPTION
        defaultUnitShouldNotBeFound("foodConsumption.lessThan=" + DEFAULT_FOOD_CONSUMPTION);

        // Get all the unitList where foodConsumption is less than UPDATED_FOOD_CONSUMPTION
        defaultUnitShouldBeFound("foodConsumption.lessThan=" + UPDATED_FOOD_CONSUMPTION);
    }

    @Test
    @Transactional
    void getAllUnitsByFoodConsumptionIsGreaterThanSomething() throws Exception {
        // Initialize the database
        unitRepository.saveAndFlush(unit);

        // Get all the unitList where foodConsumption is greater than DEFAULT_FOOD_CONSUMPTION
        defaultUnitShouldNotBeFound("foodConsumption.greaterThan=" + DEFAULT_FOOD_CONSUMPTION);

        // Get all the unitList where foodConsumption is greater than SMALLER_FOOD_CONSUMPTION
        defaultUnitShouldBeFound("foodConsumption.greaterThan=" + SMALLER_FOOD_CONSUMPTION);
    }

    @Test
    @Transactional
    void getAllUnitsByCarryingCapacityIsEqualToSomething() throws Exception {
        // Initialize the database
        unitRepository.saveAndFlush(unit);

        // Get all the unitList where carryingCapacity equals to DEFAULT_CARRYING_CAPACITY
        defaultUnitShouldBeFound("carryingCapacity.equals=" + DEFAULT_CARRYING_CAPACITY);

        // Get all the unitList where carryingCapacity equals to UPDATED_CARRYING_CAPACITY
        defaultUnitShouldNotBeFound("carryingCapacity.equals=" + UPDATED_CARRYING_CAPACITY);
    }

    @Test
    @Transactional
    void getAllUnitsByCarryingCapacityIsNotEqualToSomething() throws Exception {
        // Initialize the database
        unitRepository.saveAndFlush(unit);

        // Get all the unitList where carryingCapacity not equals to DEFAULT_CARRYING_CAPACITY
        defaultUnitShouldNotBeFound("carryingCapacity.notEquals=" + DEFAULT_CARRYING_CAPACITY);

        // Get all the unitList where carryingCapacity not equals to UPDATED_CARRYING_CAPACITY
        defaultUnitShouldBeFound("carryingCapacity.notEquals=" + UPDATED_CARRYING_CAPACITY);
    }

    @Test
    @Transactional
    void getAllUnitsByCarryingCapacityIsInShouldWork() throws Exception {
        // Initialize the database
        unitRepository.saveAndFlush(unit);

        // Get all the unitList where carryingCapacity in DEFAULT_CARRYING_CAPACITY or UPDATED_CARRYING_CAPACITY
        defaultUnitShouldBeFound("carryingCapacity.in=" + DEFAULT_CARRYING_CAPACITY + "," + UPDATED_CARRYING_CAPACITY);

        // Get all the unitList where carryingCapacity equals to UPDATED_CARRYING_CAPACITY
        defaultUnitShouldNotBeFound("carryingCapacity.in=" + UPDATED_CARRYING_CAPACITY);
    }

    @Test
    @Transactional
    void getAllUnitsByCarryingCapacityIsNullOrNotNull() throws Exception {
        // Initialize the database
        unitRepository.saveAndFlush(unit);

        // Get all the unitList where carryingCapacity is not null
        defaultUnitShouldBeFound("carryingCapacity.specified=true");

        // Get all the unitList where carryingCapacity is null
        defaultUnitShouldNotBeFound("carryingCapacity.specified=false");
    }

    @Test
    @Transactional
    void getAllUnitsByCarryingCapacityIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        unitRepository.saveAndFlush(unit);

        // Get all the unitList where carryingCapacity is greater than or equal to DEFAULT_CARRYING_CAPACITY
        defaultUnitShouldBeFound("carryingCapacity.greaterThanOrEqual=" + DEFAULT_CARRYING_CAPACITY);

        // Get all the unitList where carryingCapacity is greater than or equal to UPDATED_CARRYING_CAPACITY
        defaultUnitShouldNotBeFound("carryingCapacity.greaterThanOrEqual=" + UPDATED_CARRYING_CAPACITY);
    }

    @Test
    @Transactional
    void getAllUnitsByCarryingCapacityIsLessThanOrEqualToSomething() throws Exception {
        // Initialize the database
        unitRepository.saveAndFlush(unit);

        // Get all the unitList where carryingCapacity is less than or equal to DEFAULT_CARRYING_CAPACITY
        defaultUnitShouldBeFound("carryingCapacity.lessThanOrEqual=" + DEFAULT_CARRYING_CAPACITY);

        // Get all the unitList where carryingCapacity is less than or equal to SMALLER_CARRYING_CAPACITY
        defaultUnitShouldNotBeFound("carryingCapacity.lessThanOrEqual=" + SMALLER_CARRYING_CAPACITY);
    }

    @Test
    @Transactional
    void getAllUnitsByCarryingCapacityIsLessThanSomething() throws Exception {
        // Initialize the database
        unitRepository.saveAndFlush(unit);

        // Get all the unitList where carryingCapacity is less than DEFAULT_CARRYING_CAPACITY
        defaultUnitShouldNotBeFound("carryingCapacity.lessThan=" + DEFAULT_CARRYING_CAPACITY);

        // Get all the unitList where carryingCapacity is less than UPDATED_CARRYING_CAPACITY
        defaultUnitShouldBeFound("carryingCapacity.lessThan=" + UPDATED_CARRYING_CAPACITY);
    }

    @Test
    @Transactional
    void getAllUnitsByCarryingCapacityIsGreaterThanSomething() throws Exception {
        // Initialize the database
        unitRepository.saveAndFlush(unit);

        // Get all the unitList where carryingCapacity is greater than DEFAULT_CARRYING_CAPACITY
        defaultUnitShouldNotBeFound("carryingCapacity.greaterThan=" + DEFAULT_CARRYING_CAPACITY);

        // Get all the unitList where carryingCapacity is greater than SMALLER_CARRYING_CAPACITY
        defaultUnitShouldBeFound("carryingCapacity.greaterThan=" + SMALLER_CARRYING_CAPACITY);
    }

    @Test
    @Transactional
    void getAllUnitsByRevivalCostAfterAnAttackGoldIsEqualToSomething() throws Exception {
        // Initialize the database
        unitRepository.saveAndFlush(unit);

        // Get all the unitList where revivalCostAfterAnAttackGold equals to DEFAULT_REVIVAL_COST_AFTER_AN_ATTACK_GOLD
        defaultUnitShouldBeFound("revivalCostAfterAnAttackGold.equals=" + DEFAULT_REVIVAL_COST_AFTER_AN_ATTACK_GOLD);

        // Get all the unitList where revivalCostAfterAnAttackGold equals to UPDATED_REVIVAL_COST_AFTER_AN_ATTACK_GOLD
        defaultUnitShouldNotBeFound("revivalCostAfterAnAttackGold.equals=" + UPDATED_REVIVAL_COST_AFTER_AN_ATTACK_GOLD);
    }

    @Test
    @Transactional
    void getAllUnitsByRevivalCostAfterAnAttackGoldIsNotEqualToSomething() throws Exception {
        // Initialize the database
        unitRepository.saveAndFlush(unit);

        // Get all the unitList where revivalCostAfterAnAttackGold not equals to DEFAULT_REVIVAL_COST_AFTER_AN_ATTACK_GOLD
        defaultUnitShouldNotBeFound("revivalCostAfterAnAttackGold.notEquals=" + DEFAULT_REVIVAL_COST_AFTER_AN_ATTACK_GOLD);

        // Get all the unitList where revivalCostAfterAnAttackGold not equals to UPDATED_REVIVAL_COST_AFTER_AN_ATTACK_GOLD
        defaultUnitShouldBeFound("revivalCostAfterAnAttackGold.notEquals=" + UPDATED_REVIVAL_COST_AFTER_AN_ATTACK_GOLD);
    }

    @Test
    @Transactional
    void getAllUnitsByRevivalCostAfterAnAttackGoldIsInShouldWork() throws Exception {
        // Initialize the database
        unitRepository.saveAndFlush(unit);

        // Get all the unitList where revivalCostAfterAnAttackGold in DEFAULT_REVIVAL_COST_AFTER_AN_ATTACK_GOLD or UPDATED_REVIVAL_COST_AFTER_AN_ATTACK_GOLD
        defaultUnitShouldBeFound(
            "revivalCostAfterAnAttackGold.in=" + DEFAULT_REVIVAL_COST_AFTER_AN_ATTACK_GOLD + "," + UPDATED_REVIVAL_COST_AFTER_AN_ATTACK_GOLD
        );

        // Get all the unitList where revivalCostAfterAnAttackGold equals to UPDATED_REVIVAL_COST_AFTER_AN_ATTACK_GOLD
        defaultUnitShouldNotBeFound("revivalCostAfterAnAttackGold.in=" + UPDATED_REVIVAL_COST_AFTER_AN_ATTACK_GOLD);
    }

    @Test
    @Transactional
    void getAllUnitsByRevivalCostAfterAnAttackGoldIsNullOrNotNull() throws Exception {
        // Initialize the database
        unitRepository.saveAndFlush(unit);

        // Get all the unitList where revivalCostAfterAnAttackGold is not null
        defaultUnitShouldBeFound("revivalCostAfterAnAttackGold.specified=true");

        // Get all the unitList where revivalCostAfterAnAttackGold is null
        defaultUnitShouldNotBeFound("revivalCostAfterAnAttackGold.specified=false");
    }

    @Test
    @Transactional
    void getAllUnitsByRevivalCostAfterAnAttackGoldIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        unitRepository.saveAndFlush(unit);

        // Get all the unitList where revivalCostAfterAnAttackGold is greater than or equal to DEFAULT_REVIVAL_COST_AFTER_AN_ATTACK_GOLD
        defaultUnitShouldBeFound("revivalCostAfterAnAttackGold.greaterThanOrEqual=" + DEFAULT_REVIVAL_COST_AFTER_AN_ATTACK_GOLD);

        // Get all the unitList where revivalCostAfterAnAttackGold is greater than or equal to UPDATED_REVIVAL_COST_AFTER_AN_ATTACK_GOLD
        defaultUnitShouldNotBeFound("revivalCostAfterAnAttackGold.greaterThanOrEqual=" + UPDATED_REVIVAL_COST_AFTER_AN_ATTACK_GOLD);
    }

    @Test
    @Transactional
    void getAllUnitsByRevivalCostAfterAnAttackGoldIsLessThanOrEqualToSomething() throws Exception {
        // Initialize the database
        unitRepository.saveAndFlush(unit);

        // Get all the unitList where revivalCostAfterAnAttackGold is less than or equal to DEFAULT_REVIVAL_COST_AFTER_AN_ATTACK_GOLD
        defaultUnitShouldBeFound("revivalCostAfterAnAttackGold.lessThanOrEqual=" + DEFAULT_REVIVAL_COST_AFTER_AN_ATTACK_GOLD);

        // Get all the unitList where revivalCostAfterAnAttackGold is less than or equal to SMALLER_REVIVAL_COST_AFTER_AN_ATTACK_GOLD
        defaultUnitShouldNotBeFound("revivalCostAfterAnAttackGold.lessThanOrEqual=" + SMALLER_REVIVAL_COST_AFTER_AN_ATTACK_GOLD);
    }

    @Test
    @Transactional
    void getAllUnitsByRevivalCostAfterAnAttackGoldIsLessThanSomething() throws Exception {
        // Initialize the database
        unitRepository.saveAndFlush(unit);

        // Get all the unitList where revivalCostAfterAnAttackGold is less than DEFAULT_REVIVAL_COST_AFTER_AN_ATTACK_GOLD
        defaultUnitShouldNotBeFound("revivalCostAfterAnAttackGold.lessThan=" + DEFAULT_REVIVAL_COST_AFTER_AN_ATTACK_GOLD);

        // Get all the unitList where revivalCostAfterAnAttackGold is less than UPDATED_REVIVAL_COST_AFTER_AN_ATTACK_GOLD
        defaultUnitShouldBeFound("revivalCostAfterAnAttackGold.lessThan=" + UPDATED_REVIVAL_COST_AFTER_AN_ATTACK_GOLD);
    }

    @Test
    @Transactional
    void getAllUnitsByRevivalCostAfterAnAttackGoldIsGreaterThanSomething() throws Exception {
        // Initialize the database
        unitRepository.saveAndFlush(unit);

        // Get all the unitList where revivalCostAfterAnAttackGold is greater than DEFAULT_REVIVAL_COST_AFTER_AN_ATTACK_GOLD
        defaultUnitShouldNotBeFound("revivalCostAfterAnAttackGold.greaterThan=" + DEFAULT_REVIVAL_COST_AFTER_AN_ATTACK_GOLD);

        // Get all the unitList where revivalCostAfterAnAttackGold is greater than SMALLER_REVIVAL_COST_AFTER_AN_ATTACK_GOLD
        defaultUnitShouldBeFound("revivalCostAfterAnAttackGold.greaterThan=" + SMALLER_REVIVAL_COST_AFTER_AN_ATTACK_GOLD);
    }

    @Test
    @Transactional
    void getAllUnitsByRevivalCostAfterDefendingSilverIsEqualToSomething() throws Exception {
        // Initialize the database
        unitRepository.saveAndFlush(unit);

        // Get all the unitList where revivalCostAfterDefendingSilver equals to DEFAULT_REVIVAL_COST_AFTER_DEFENDING_SILVER
        defaultUnitShouldBeFound("revivalCostAfterDefendingSilver.equals=" + DEFAULT_REVIVAL_COST_AFTER_DEFENDING_SILVER);

        // Get all the unitList where revivalCostAfterDefendingSilver equals to UPDATED_REVIVAL_COST_AFTER_DEFENDING_SILVER
        defaultUnitShouldNotBeFound("revivalCostAfterDefendingSilver.equals=" + UPDATED_REVIVAL_COST_AFTER_DEFENDING_SILVER);
    }

    @Test
    @Transactional
    void getAllUnitsByRevivalCostAfterDefendingSilverIsNotEqualToSomething() throws Exception {
        // Initialize the database
        unitRepository.saveAndFlush(unit);

        // Get all the unitList where revivalCostAfterDefendingSilver not equals to DEFAULT_REVIVAL_COST_AFTER_DEFENDING_SILVER
        defaultUnitShouldNotBeFound("revivalCostAfterDefendingSilver.notEquals=" + DEFAULT_REVIVAL_COST_AFTER_DEFENDING_SILVER);

        // Get all the unitList where revivalCostAfterDefendingSilver not equals to UPDATED_REVIVAL_COST_AFTER_DEFENDING_SILVER
        defaultUnitShouldBeFound("revivalCostAfterDefendingSilver.notEquals=" + UPDATED_REVIVAL_COST_AFTER_DEFENDING_SILVER);
    }

    @Test
    @Transactional
    void getAllUnitsByRevivalCostAfterDefendingSilverIsInShouldWork() throws Exception {
        // Initialize the database
        unitRepository.saveAndFlush(unit);

        // Get all the unitList where revivalCostAfterDefendingSilver in DEFAULT_REVIVAL_COST_AFTER_DEFENDING_SILVER or UPDATED_REVIVAL_COST_AFTER_DEFENDING_SILVER
        defaultUnitShouldBeFound(
            "revivalCostAfterDefendingSilver.in=" +
            DEFAULT_REVIVAL_COST_AFTER_DEFENDING_SILVER +
            "," +
            UPDATED_REVIVAL_COST_AFTER_DEFENDING_SILVER
        );

        // Get all the unitList where revivalCostAfterDefendingSilver equals to UPDATED_REVIVAL_COST_AFTER_DEFENDING_SILVER
        defaultUnitShouldNotBeFound("revivalCostAfterDefendingSilver.in=" + UPDATED_REVIVAL_COST_AFTER_DEFENDING_SILVER);
    }

    @Test
    @Transactional
    void getAllUnitsByRevivalCostAfterDefendingSilverIsNullOrNotNull() throws Exception {
        // Initialize the database
        unitRepository.saveAndFlush(unit);

        // Get all the unitList where revivalCostAfterDefendingSilver is not null
        defaultUnitShouldBeFound("revivalCostAfterDefendingSilver.specified=true");

        // Get all the unitList where revivalCostAfterDefendingSilver is null
        defaultUnitShouldNotBeFound("revivalCostAfterDefendingSilver.specified=false");
    }

    @Test
    @Transactional
    void getAllUnitsByRevivalCostAfterDefendingSilverIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        unitRepository.saveAndFlush(unit);

        // Get all the unitList where revivalCostAfterDefendingSilver is greater than or equal to DEFAULT_REVIVAL_COST_AFTER_DEFENDING_SILVER
        defaultUnitShouldBeFound("revivalCostAfterDefendingSilver.greaterThanOrEqual=" + DEFAULT_REVIVAL_COST_AFTER_DEFENDING_SILVER);

        // Get all the unitList where revivalCostAfterDefendingSilver is greater than or equal to UPDATED_REVIVAL_COST_AFTER_DEFENDING_SILVER
        defaultUnitShouldNotBeFound("revivalCostAfterDefendingSilver.greaterThanOrEqual=" + UPDATED_REVIVAL_COST_AFTER_DEFENDING_SILVER);
    }

    @Test
    @Transactional
    void getAllUnitsByRevivalCostAfterDefendingSilverIsLessThanOrEqualToSomething() throws Exception {
        // Initialize the database
        unitRepository.saveAndFlush(unit);

        // Get all the unitList where revivalCostAfterDefendingSilver is less than or equal to DEFAULT_REVIVAL_COST_AFTER_DEFENDING_SILVER
        defaultUnitShouldBeFound("revivalCostAfterDefendingSilver.lessThanOrEqual=" + DEFAULT_REVIVAL_COST_AFTER_DEFENDING_SILVER);

        // Get all the unitList where revivalCostAfterDefendingSilver is less than or equal to SMALLER_REVIVAL_COST_AFTER_DEFENDING_SILVER
        defaultUnitShouldNotBeFound("revivalCostAfterDefendingSilver.lessThanOrEqual=" + SMALLER_REVIVAL_COST_AFTER_DEFENDING_SILVER);
    }

    @Test
    @Transactional
    void getAllUnitsByRevivalCostAfterDefendingSilverIsLessThanSomething() throws Exception {
        // Initialize the database
        unitRepository.saveAndFlush(unit);

        // Get all the unitList where revivalCostAfterDefendingSilver is less than DEFAULT_REVIVAL_COST_AFTER_DEFENDING_SILVER
        defaultUnitShouldNotBeFound("revivalCostAfterDefendingSilver.lessThan=" + DEFAULT_REVIVAL_COST_AFTER_DEFENDING_SILVER);

        // Get all the unitList where revivalCostAfterDefendingSilver is less than UPDATED_REVIVAL_COST_AFTER_DEFENDING_SILVER
        defaultUnitShouldBeFound("revivalCostAfterDefendingSilver.lessThan=" + UPDATED_REVIVAL_COST_AFTER_DEFENDING_SILVER);
    }

    @Test
    @Transactional
    void getAllUnitsByRevivalCostAfterDefendingSilverIsGreaterThanSomething() throws Exception {
        // Initialize the database
        unitRepository.saveAndFlush(unit);

        // Get all the unitList where revivalCostAfterDefendingSilver is greater than DEFAULT_REVIVAL_COST_AFTER_DEFENDING_SILVER
        defaultUnitShouldNotBeFound("revivalCostAfterDefendingSilver.greaterThan=" + DEFAULT_REVIVAL_COST_AFTER_DEFENDING_SILVER);

        // Get all the unitList where revivalCostAfterDefendingSilver is greater than SMALLER_REVIVAL_COST_AFTER_DEFENDING_SILVER
        defaultUnitShouldBeFound("revivalCostAfterDefendingSilver.greaterThan=" + SMALLER_REVIVAL_COST_AFTER_DEFENDING_SILVER);
    }

    @Test
    @Transactional
    void getAllUnitsByFeatureIsEqualToSomething() throws Exception {
        // Initialize the database
        unitRepository.saveAndFlush(unit);
        Feature feature;
        if (TestUtil.findAll(em, Feature.class).isEmpty()) {
            feature = FeatureResourceIT.createEntity(em);
            em.persist(feature);
            em.flush();
        } else {
            feature = TestUtil.findAll(em, Feature.class).get(0);
        }
        em.persist(feature);
        em.flush();
        unit.addFeature(feature);
        unitRepository.saveAndFlush(unit);
        Long featureId = feature.getId();

        // Get all the unitList where feature equals to featureId
        defaultUnitShouldBeFound("featureId.equals=" + featureId);

        // Get all the unitList where feature equals to (featureId + 1)
        defaultUnitShouldNotBeFound("featureId.equals=" + (featureId + 1));
    }

    /**
     * Executes the search, and checks that the default entity is returned.
     */
    private void defaultUnitShouldBeFound(String filter) throws Exception {
        restUnitMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(unit.getId().intValue())))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME)))
            .andExpect(jsonPath("$.[*].strength").value(hasItem(DEFAULT_STRENGTH)))
            .andExpect(jsonPath("$.[*].health").value(hasItem(DEFAULT_HEALTH)))
            .andExpect(jsonPath("$.[*].leadership").value(hasItem(DEFAULT_LEADERSHIP)))
            .andExpect(jsonPath("$.[*].speed").value(hasItem(DEFAULT_SPEED)))
            .andExpect(jsonPath("$.[*].initiative").value(hasItem(DEFAULT_INITIATIVE)))
            .andExpect(jsonPath("$.[*].foodConsumption").value(hasItem(DEFAULT_FOOD_CONSUMPTION)))
            .andExpect(jsonPath("$.[*].carryingCapacity").value(hasItem(DEFAULT_CARRYING_CAPACITY)))
            .andExpect(jsonPath("$.[*].revivalCostAfterAnAttackGold").value(hasItem(DEFAULT_REVIVAL_COST_AFTER_AN_ATTACK_GOLD)))
            .andExpect(jsonPath("$.[*].revivalCostAfterDefendingSilver").value(hasItem(DEFAULT_REVIVAL_COST_AFTER_DEFENDING_SILVER)));

        // Check, that the count call also returns 1
        restUnitMockMvc
            .perform(get(ENTITY_API_URL + "/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(content().string("1"));
    }

    /**
     * Executes the search, and checks that the default entity is not returned.
     */
    private void defaultUnitShouldNotBeFound(String filter) throws Exception {
        restUnitMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$").isEmpty());

        // Check, that the count call also returns 0
        restUnitMockMvc
            .perform(get(ENTITY_API_URL + "/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(content().string("0"));
    }

    @Test
    @Transactional
    void getNonExistingUnit() throws Exception {
        // Get the unit
        restUnitMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putNewUnit() throws Exception {
        // Initialize the database
        unitRepository.saveAndFlush(unit);

        int databaseSizeBeforeUpdate = unitRepository.findAll().size();

        // Update the unit
        Unit updatedUnit = unitRepository.findById(unit.getId()).get();
        // Disconnect from session so that the updates on updatedUnit are not directly saved in db
        em.detach(updatedUnit);
        updatedUnit
            .name(UPDATED_NAME)
            .strength(UPDATED_STRENGTH)
            .health(UPDATED_HEALTH)
            .leadership(UPDATED_LEADERSHIP)
            .speed(UPDATED_SPEED)
            .initiative(UPDATED_INITIATIVE)
            .foodConsumption(UPDATED_FOOD_CONSUMPTION)
            .carryingCapacity(UPDATED_CARRYING_CAPACITY)
            .revivalCostAfterAnAttackGold(UPDATED_REVIVAL_COST_AFTER_AN_ATTACK_GOLD)
            .revivalCostAfterDefendingSilver(UPDATED_REVIVAL_COST_AFTER_DEFENDING_SILVER);
        UnitDTO unitDTO = unitMapper.toDto(updatedUnit);

        restUnitMockMvc
            .perform(
                put(ENTITY_API_URL_ID, unitDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(unitDTO))
            )
            .andExpect(status().isOk());

        // Validate the Unit in the database
        List<Unit> unitList = unitRepository.findAll();
        assertThat(unitList).hasSize(databaseSizeBeforeUpdate);
        Unit testUnit = unitList.get(unitList.size() - 1);
        assertThat(testUnit.getName()).isEqualTo(UPDATED_NAME);
        assertThat(testUnit.getStrength()).isEqualTo(UPDATED_STRENGTH);
        assertThat(testUnit.getHealth()).isEqualTo(UPDATED_HEALTH);
        assertThat(testUnit.getLeadership()).isEqualTo(UPDATED_LEADERSHIP);
        assertThat(testUnit.getSpeed()).isEqualTo(UPDATED_SPEED);
        assertThat(testUnit.getInitiative()).isEqualTo(UPDATED_INITIATIVE);
        assertThat(testUnit.getFoodConsumption()).isEqualTo(UPDATED_FOOD_CONSUMPTION);
        assertThat(testUnit.getCarryingCapacity()).isEqualTo(UPDATED_CARRYING_CAPACITY);
        assertThat(testUnit.getRevivalCostAfterAnAttackGold()).isEqualTo(UPDATED_REVIVAL_COST_AFTER_AN_ATTACK_GOLD);
        assertThat(testUnit.getRevivalCostAfterDefendingSilver()).isEqualTo(UPDATED_REVIVAL_COST_AFTER_DEFENDING_SILVER);
    }

    @Test
    @Transactional
    void putNonExistingUnit() throws Exception {
        int databaseSizeBeforeUpdate = unitRepository.findAll().size();
        unit.setId(count.incrementAndGet());

        // Create the Unit
        UnitDTO unitDTO = unitMapper.toDto(unit);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restUnitMockMvc
            .perform(
                put(ENTITY_API_URL_ID, unitDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(unitDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the Unit in the database
        List<Unit> unitList = unitRepository.findAll();
        assertThat(unitList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchUnit() throws Exception {
        int databaseSizeBeforeUpdate = unitRepository.findAll().size();
        unit.setId(count.incrementAndGet());

        // Create the Unit
        UnitDTO unitDTO = unitMapper.toDto(unit);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restUnitMockMvc
            .perform(
                put(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(unitDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the Unit in the database
        List<Unit> unitList = unitRepository.findAll();
        assertThat(unitList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamUnit() throws Exception {
        int databaseSizeBeforeUpdate = unitRepository.findAll().size();
        unit.setId(count.incrementAndGet());

        // Create the Unit
        UnitDTO unitDTO = unitMapper.toDto(unit);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restUnitMockMvc
            .perform(put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(unitDTO)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the Unit in the database
        List<Unit> unitList = unitRepository.findAll();
        assertThat(unitList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateUnitWithPatch() throws Exception {
        // Initialize the database
        unitRepository.saveAndFlush(unit);

        int databaseSizeBeforeUpdate = unitRepository.findAll().size();

        // Update the unit using partial update
        Unit partialUpdatedUnit = new Unit();
        partialUpdatedUnit.setId(unit.getId());

        partialUpdatedUnit
            .name(UPDATED_NAME)
            .strength(UPDATED_STRENGTH)
            .initiative(UPDATED_INITIATIVE)
            .revivalCostAfterAnAttackGold(UPDATED_REVIVAL_COST_AFTER_AN_ATTACK_GOLD);

        restUnitMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedUnit.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedUnit))
            )
            .andExpect(status().isOk());

        // Validate the Unit in the database
        List<Unit> unitList = unitRepository.findAll();
        assertThat(unitList).hasSize(databaseSizeBeforeUpdate);
        Unit testUnit = unitList.get(unitList.size() - 1);
        assertThat(testUnit.getName()).isEqualTo(UPDATED_NAME);
        assertThat(testUnit.getStrength()).isEqualTo(UPDATED_STRENGTH);
        assertThat(testUnit.getHealth()).isEqualTo(DEFAULT_HEALTH);
        assertThat(testUnit.getLeadership()).isEqualTo(DEFAULT_LEADERSHIP);
        assertThat(testUnit.getSpeed()).isEqualTo(DEFAULT_SPEED);
        assertThat(testUnit.getInitiative()).isEqualTo(UPDATED_INITIATIVE);
        assertThat(testUnit.getFoodConsumption()).isEqualTo(DEFAULT_FOOD_CONSUMPTION);
        assertThat(testUnit.getCarryingCapacity()).isEqualTo(DEFAULT_CARRYING_CAPACITY);
        assertThat(testUnit.getRevivalCostAfterAnAttackGold()).isEqualTo(UPDATED_REVIVAL_COST_AFTER_AN_ATTACK_GOLD);
        assertThat(testUnit.getRevivalCostAfterDefendingSilver()).isEqualTo(DEFAULT_REVIVAL_COST_AFTER_DEFENDING_SILVER);
    }

    @Test
    @Transactional
    void fullUpdateUnitWithPatch() throws Exception {
        // Initialize the database
        unitRepository.saveAndFlush(unit);

        int databaseSizeBeforeUpdate = unitRepository.findAll().size();

        // Update the unit using partial update
        Unit partialUpdatedUnit = new Unit();
        partialUpdatedUnit.setId(unit.getId());

        partialUpdatedUnit
            .name(UPDATED_NAME)
            .strength(UPDATED_STRENGTH)
            .health(UPDATED_HEALTH)
            .leadership(UPDATED_LEADERSHIP)
            .speed(UPDATED_SPEED)
            .initiative(UPDATED_INITIATIVE)
            .foodConsumption(UPDATED_FOOD_CONSUMPTION)
            .carryingCapacity(UPDATED_CARRYING_CAPACITY)
            .revivalCostAfterAnAttackGold(UPDATED_REVIVAL_COST_AFTER_AN_ATTACK_GOLD)
            .revivalCostAfterDefendingSilver(UPDATED_REVIVAL_COST_AFTER_DEFENDING_SILVER);

        restUnitMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedUnit.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedUnit))
            )
            .andExpect(status().isOk());

        // Validate the Unit in the database
        List<Unit> unitList = unitRepository.findAll();
        assertThat(unitList).hasSize(databaseSizeBeforeUpdate);
        Unit testUnit = unitList.get(unitList.size() - 1);
        assertThat(testUnit.getName()).isEqualTo(UPDATED_NAME);
        assertThat(testUnit.getStrength()).isEqualTo(UPDATED_STRENGTH);
        assertThat(testUnit.getHealth()).isEqualTo(UPDATED_HEALTH);
        assertThat(testUnit.getLeadership()).isEqualTo(UPDATED_LEADERSHIP);
        assertThat(testUnit.getSpeed()).isEqualTo(UPDATED_SPEED);
        assertThat(testUnit.getInitiative()).isEqualTo(UPDATED_INITIATIVE);
        assertThat(testUnit.getFoodConsumption()).isEqualTo(UPDATED_FOOD_CONSUMPTION);
        assertThat(testUnit.getCarryingCapacity()).isEqualTo(UPDATED_CARRYING_CAPACITY);
        assertThat(testUnit.getRevivalCostAfterAnAttackGold()).isEqualTo(UPDATED_REVIVAL_COST_AFTER_AN_ATTACK_GOLD);
        assertThat(testUnit.getRevivalCostAfterDefendingSilver()).isEqualTo(UPDATED_REVIVAL_COST_AFTER_DEFENDING_SILVER);
    }

    @Test
    @Transactional
    void patchNonExistingUnit() throws Exception {
        int databaseSizeBeforeUpdate = unitRepository.findAll().size();
        unit.setId(count.incrementAndGet());

        // Create the Unit
        UnitDTO unitDTO = unitMapper.toDto(unit);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restUnitMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, unitDTO.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(unitDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the Unit in the database
        List<Unit> unitList = unitRepository.findAll();
        assertThat(unitList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchUnit() throws Exception {
        int databaseSizeBeforeUpdate = unitRepository.findAll().size();
        unit.setId(count.incrementAndGet());

        // Create the Unit
        UnitDTO unitDTO = unitMapper.toDto(unit);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restUnitMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(unitDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the Unit in the database
        List<Unit> unitList = unitRepository.findAll();
        assertThat(unitList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamUnit() throws Exception {
        int databaseSizeBeforeUpdate = unitRepository.findAll().size();
        unit.setId(count.incrementAndGet());

        // Create the Unit
        UnitDTO unitDTO = unitMapper.toDto(unit);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restUnitMockMvc
            .perform(patch(ENTITY_API_URL).contentType("application/merge-patch+json").content(TestUtil.convertObjectToJsonBytes(unitDTO)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the Unit in the database
        List<Unit> unitList = unitRepository.findAll();
        assertThat(unitList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteUnit() throws Exception {
        // Initialize the database
        unitRepository.saveAndFlush(unit);

        int databaseSizeBeforeDelete = unitRepository.findAll().size();

        // Delete the unit
        restUnitMockMvc
            .perform(delete(ENTITY_API_URL_ID, unit.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Unit> unitList = unitRepository.findAll();
        assertThat(unitList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
