package hu.totalbattlecalc.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import hu.totalbattlecalc.IntegrationTest;
import hu.totalbattlecalc.domain.Feature;
import hu.totalbattlecalc.domain.Unit;
import hu.totalbattlecalc.domain.enumeration.FeatureName;
import hu.totalbattlecalc.domain.enumeration.MeasurementUnit;
import hu.totalbattlecalc.repository.FeatureRepository;
import hu.totalbattlecalc.service.criteria.FeatureCriteria;
import hu.totalbattlecalc.service.dto.FeatureDTO;
import hu.totalbattlecalc.service.mapper.FeatureMapper;
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
 * Integration tests for the {@link FeatureResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class FeatureResourceIT {

    private static final FeatureName DEFAULT_NAME = FeatureName.HUMAN;
    private static final FeatureName UPDATED_NAME = FeatureName.RANGED;

    private static final Double DEFAULT_VALUE = 1D;
    private static final Double UPDATED_VALUE = 2D;
    private static final Double SMALLER_VALUE = 1D - 1D;

    private static final MeasurementUnit DEFAULT_UNIT = MeasurementUnit.NONE;
    private static final MeasurementUnit UPDATED_UNIT = MeasurementUnit.PERCENT;

    private static final String ENTITY_API_URL = "/api/features";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private FeatureRepository featureRepository;

    @Autowired
    private FeatureMapper featureMapper;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restFeatureMockMvc;

    private Feature feature;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Feature createEntity(EntityManager em) {
        Feature feature = new Feature().name(DEFAULT_NAME).value(DEFAULT_VALUE).unit(DEFAULT_UNIT);
        return feature;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Feature createUpdatedEntity(EntityManager em) {
        Feature feature = new Feature().name(UPDATED_NAME).value(UPDATED_VALUE).unit(UPDATED_UNIT);
        return feature;
    }

    @BeforeEach
    public void initTest() {
        feature = createEntity(em);
    }

    @Test
    @Transactional
    void createFeature() throws Exception {
        int databaseSizeBeforeCreate = featureRepository.findAll().size();
        // Create the Feature
        FeatureDTO featureDTO = featureMapper.toDto(feature);
        restFeatureMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(featureDTO)))
            .andExpect(status().isCreated());

        // Validate the Feature in the database
        List<Feature> featureList = featureRepository.findAll();
        assertThat(featureList).hasSize(databaseSizeBeforeCreate + 1);
        Feature testFeature = featureList.get(featureList.size() - 1);
        assertThat(testFeature.getName()).isEqualTo(DEFAULT_NAME);
        assertThat(testFeature.getValue()).isEqualTo(DEFAULT_VALUE);
        assertThat(testFeature.getUnit()).isEqualTo(DEFAULT_UNIT);
    }

    @Test
    @Transactional
    void createFeatureWithExistingId() throws Exception {
        // Create the Feature with an existing ID
        feature.setId(1L);
        FeatureDTO featureDTO = featureMapper.toDto(feature);

        int databaseSizeBeforeCreate = featureRepository.findAll().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        restFeatureMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(featureDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Feature in the database
        List<Feature> featureList = featureRepository.findAll();
        assertThat(featureList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void getAllFeatures() throws Exception {
        // Initialize the database
        featureRepository.saveAndFlush(feature);

        // Get all the featureList
        restFeatureMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(feature.getId().intValue())))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME.toString())))
            .andExpect(jsonPath("$.[*].value").value(hasItem(DEFAULT_VALUE.doubleValue())))
            .andExpect(jsonPath("$.[*].unit").value(hasItem(DEFAULT_UNIT.toString())));
    }

    @Test
    @Transactional
    void getFeature() throws Exception {
        // Initialize the database
        featureRepository.saveAndFlush(feature);

        // Get the feature
        restFeatureMockMvc
            .perform(get(ENTITY_API_URL_ID, feature.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(feature.getId().intValue()))
            .andExpect(jsonPath("$.name").value(DEFAULT_NAME.toString()))
            .andExpect(jsonPath("$.value").value(DEFAULT_VALUE.doubleValue()))
            .andExpect(jsonPath("$.unit").value(DEFAULT_UNIT.toString()));
    }

    @Test
    @Transactional
    void getFeaturesByIdFiltering() throws Exception {
        // Initialize the database
        featureRepository.saveAndFlush(feature);

        Long id = feature.getId();

        defaultFeatureShouldBeFound("id.equals=" + id);
        defaultFeatureShouldNotBeFound("id.notEquals=" + id);

        defaultFeatureShouldBeFound("id.greaterThanOrEqual=" + id);
        defaultFeatureShouldNotBeFound("id.greaterThan=" + id);

        defaultFeatureShouldBeFound("id.lessThanOrEqual=" + id);
        defaultFeatureShouldNotBeFound("id.lessThan=" + id);
    }

    @Test
    @Transactional
    void getAllFeaturesByNameIsEqualToSomething() throws Exception {
        // Initialize the database
        featureRepository.saveAndFlush(feature);

        // Get all the featureList where name equals to DEFAULT_NAME
        defaultFeatureShouldBeFound("name.equals=" + DEFAULT_NAME);

        // Get all the featureList where name equals to UPDATED_NAME
        defaultFeatureShouldNotBeFound("name.equals=" + UPDATED_NAME);
    }

    @Test
    @Transactional
    void getAllFeaturesByNameIsNotEqualToSomething() throws Exception {
        // Initialize the database
        featureRepository.saveAndFlush(feature);

        // Get all the featureList where name not equals to DEFAULT_NAME
        defaultFeatureShouldNotBeFound("name.notEquals=" + DEFAULT_NAME);

        // Get all the featureList where name not equals to UPDATED_NAME
        defaultFeatureShouldBeFound("name.notEquals=" + UPDATED_NAME);
    }

    @Test
    @Transactional
    void getAllFeaturesByNameIsInShouldWork() throws Exception {
        // Initialize the database
        featureRepository.saveAndFlush(feature);

        // Get all the featureList where name in DEFAULT_NAME or UPDATED_NAME
        defaultFeatureShouldBeFound("name.in=" + DEFAULT_NAME + "," + UPDATED_NAME);

        // Get all the featureList where name equals to UPDATED_NAME
        defaultFeatureShouldNotBeFound("name.in=" + UPDATED_NAME);
    }

    @Test
    @Transactional
    void getAllFeaturesByNameIsNullOrNotNull() throws Exception {
        // Initialize the database
        featureRepository.saveAndFlush(feature);

        // Get all the featureList where name is not null
        defaultFeatureShouldBeFound("name.specified=true");

        // Get all the featureList where name is null
        defaultFeatureShouldNotBeFound("name.specified=false");
    }

    @Test
    @Transactional
    void getAllFeaturesByValueIsEqualToSomething() throws Exception {
        // Initialize the database
        featureRepository.saveAndFlush(feature);

        // Get all the featureList where value equals to DEFAULT_VALUE
        defaultFeatureShouldBeFound("value.equals=" + DEFAULT_VALUE);

        // Get all the featureList where value equals to UPDATED_VALUE
        defaultFeatureShouldNotBeFound("value.equals=" + UPDATED_VALUE);
    }

    @Test
    @Transactional
    void getAllFeaturesByValueIsNotEqualToSomething() throws Exception {
        // Initialize the database
        featureRepository.saveAndFlush(feature);

        // Get all the featureList where value not equals to DEFAULT_VALUE
        defaultFeatureShouldNotBeFound("value.notEquals=" + DEFAULT_VALUE);

        // Get all the featureList where value not equals to UPDATED_VALUE
        defaultFeatureShouldBeFound("value.notEquals=" + UPDATED_VALUE);
    }

    @Test
    @Transactional
    void getAllFeaturesByValueIsInShouldWork() throws Exception {
        // Initialize the database
        featureRepository.saveAndFlush(feature);

        // Get all the featureList where value in DEFAULT_VALUE or UPDATED_VALUE
        defaultFeatureShouldBeFound("value.in=" + DEFAULT_VALUE + "," + UPDATED_VALUE);

        // Get all the featureList where value equals to UPDATED_VALUE
        defaultFeatureShouldNotBeFound("value.in=" + UPDATED_VALUE);
    }

    @Test
    @Transactional
    void getAllFeaturesByValueIsNullOrNotNull() throws Exception {
        // Initialize the database
        featureRepository.saveAndFlush(feature);

        // Get all the featureList where value is not null
        defaultFeatureShouldBeFound("value.specified=true");

        // Get all the featureList where value is null
        defaultFeatureShouldNotBeFound("value.specified=false");
    }

    @Test
    @Transactional
    void getAllFeaturesByValueIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        featureRepository.saveAndFlush(feature);

        // Get all the featureList where value is greater than or equal to DEFAULT_VALUE
        defaultFeatureShouldBeFound("value.greaterThanOrEqual=" + DEFAULT_VALUE);

        // Get all the featureList where value is greater than or equal to UPDATED_VALUE
        defaultFeatureShouldNotBeFound("value.greaterThanOrEqual=" + UPDATED_VALUE);
    }

    @Test
    @Transactional
    void getAllFeaturesByValueIsLessThanOrEqualToSomething() throws Exception {
        // Initialize the database
        featureRepository.saveAndFlush(feature);

        // Get all the featureList where value is less than or equal to DEFAULT_VALUE
        defaultFeatureShouldBeFound("value.lessThanOrEqual=" + DEFAULT_VALUE);

        // Get all the featureList where value is less than or equal to SMALLER_VALUE
        defaultFeatureShouldNotBeFound("value.lessThanOrEqual=" + SMALLER_VALUE);
    }

    @Test
    @Transactional
    void getAllFeaturesByValueIsLessThanSomething() throws Exception {
        // Initialize the database
        featureRepository.saveAndFlush(feature);

        // Get all the featureList where value is less than DEFAULT_VALUE
        defaultFeatureShouldNotBeFound("value.lessThan=" + DEFAULT_VALUE);

        // Get all the featureList where value is less than UPDATED_VALUE
        defaultFeatureShouldBeFound("value.lessThan=" + UPDATED_VALUE);
    }

    @Test
    @Transactional
    void getAllFeaturesByValueIsGreaterThanSomething() throws Exception {
        // Initialize the database
        featureRepository.saveAndFlush(feature);

        // Get all the featureList where value is greater than DEFAULT_VALUE
        defaultFeatureShouldNotBeFound("value.greaterThan=" + DEFAULT_VALUE);

        // Get all the featureList where value is greater than SMALLER_VALUE
        defaultFeatureShouldBeFound("value.greaterThan=" + SMALLER_VALUE);
    }

    @Test
    @Transactional
    void getAllFeaturesByUnitIsEqualToSomething() throws Exception {
        // Initialize the database
        featureRepository.saveAndFlush(feature);

        // Get all the featureList where unit equals to DEFAULT_UNIT
        defaultFeatureShouldBeFound("unit.equals=" + DEFAULT_UNIT);

        // Get all the featureList where unit equals to UPDATED_UNIT
        defaultFeatureShouldNotBeFound("unit.equals=" + UPDATED_UNIT);
    }

    @Test
    @Transactional
    void getAllFeaturesByUnitIsNotEqualToSomething() throws Exception {
        // Initialize the database
        featureRepository.saveAndFlush(feature);

        // Get all the featureList where unit not equals to DEFAULT_UNIT
        defaultFeatureShouldNotBeFound("unit.notEquals=" + DEFAULT_UNIT);

        // Get all the featureList where unit not equals to UPDATED_UNIT
        defaultFeatureShouldBeFound("unit.notEquals=" + UPDATED_UNIT);
    }

    @Test
    @Transactional
    void getAllFeaturesByUnitIsInShouldWork() throws Exception {
        // Initialize the database
        featureRepository.saveAndFlush(feature);

        // Get all the featureList where unit in DEFAULT_UNIT or UPDATED_UNIT
        defaultFeatureShouldBeFound("unit.in=" + DEFAULT_UNIT + "," + UPDATED_UNIT);

        // Get all the featureList where unit equals to UPDATED_UNIT
        defaultFeatureShouldNotBeFound("unit.in=" + UPDATED_UNIT);
    }

    @Test
    @Transactional
    void getAllFeaturesByUnitIsNullOrNotNull() throws Exception {
        // Initialize the database
        featureRepository.saveAndFlush(feature);

        // Get all the featureList where unit is not null
        defaultFeatureShouldBeFound("unit.specified=true");

        // Get all the featureList where unit is null
        defaultFeatureShouldNotBeFound("unit.specified=false");
    }

    @Test
    @Transactional
    void getAllFeaturesByUnitIsEqualToSomething1() throws Exception {
        // Initialize the database
        featureRepository.saveAndFlush(feature);
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
        feature.addUnit(unit);
        featureRepository.saveAndFlush(feature);
        Long unitId = unit.getId();

        // Get all the featureList where unit equals to unitId
        defaultFeatureShouldBeFound("unitId.equals=" + unitId);

        // Get all the featureList where unit equals to (unitId + 1)
        defaultFeatureShouldNotBeFound("unitId.equals=" + (unitId + 1));
    }

    /**
     * Executes the search, and checks that the default entity is returned.
     */
    private void defaultFeatureShouldBeFound(String filter) throws Exception {
        restFeatureMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(feature.getId().intValue())))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME.toString())))
            .andExpect(jsonPath("$.[*].value").value(hasItem(DEFAULT_VALUE.doubleValue())))
            .andExpect(jsonPath("$.[*].unit").value(hasItem(DEFAULT_UNIT.toString())));

        // Check, that the count call also returns 1
        restFeatureMockMvc
            .perform(get(ENTITY_API_URL + "/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(content().string("1"));
    }

    /**
     * Executes the search, and checks that the default entity is not returned.
     */
    private void defaultFeatureShouldNotBeFound(String filter) throws Exception {
        restFeatureMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$").isEmpty());

        // Check, that the count call also returns 0
        restFeatureMockMvc
            .perform(get(ENTITY_API_URL + "/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(content().string("0"));
    }

    @Test
    @Transactional
    void getNonExistingFeature() throws Exception {
        // Get the feature
        restFeatureMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putNewFeature() throws Exception {
        // Initialize the database
        featureRepository.saveAndFlush(feature);

        int databaseSizeBeforeUpdate = featureRepository.findAll().size();

        // Update the feature
        Feature updatedFeature = featureRepository.findById(feature.getId()).get();
        // Disconnect from session so that the updates on updatedFeature are not directly saved in db
        em.detach(updatedFeature);
        updatedFeature.name(UPDATED_NAME).value(UPDATED_VALUE).unit(UPDATED_UNIT);
        FeatureDTO featureDTO = featureMapper.toDto(updatedFeature);

        restFeatureMockMvc
            .perform(
                put(ENTITY_API_URL_ID, featureDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(featureDTO))
            )
            .andExpect(status().isOk());

        // Validate the Feature in the database
        List<Feature> featureList = featureRepository.findAll();
        assertThat(featureList).hasSize(databaseSizeBeforeUpdate);
        Feature testFeature = featureList.get(featureList.size() - 1);
        assertThat(testFeature.getName()).isEqualTo(UPDATED_NAME);
        assertThat(testFeature.getValue()).isEqualTo(UPDATED_VALUE);
        assertThat(testFeature.getUnit()).isEqualTo(UPDATED_UNIT);
    }

    @Test
    @Transactional
    void putNonExistingFeature() throws Exception {
        int databaseSizeBeforeUpdate = featureRepository.findAll().size();
        feature.setId(count.incrementAndGet());

        // Create the Feature
        FeatureDTO featureDTO = featureMapper.toDto(feature);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restFeatureMockMvc
            .perform(
                put(ENTITY_API_URL_ID, featureDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(featureDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the Feature in the database
        List<Feature> featureList = featureRepository.findAll();
        assertThat(featureList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchFeature() throws Exception {
        int databaseSizeBeforeUpdate = featureRepository.findAll().size();
        feature.setId(count.incrementAndGet());

        // Create the Feature
        FeatureDTO featureDTO = featureMapper.toDto(feature);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restFeatureMockMvc
            .perform(
                put(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(featureDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the Feature in the database
        List<Feature> featureList = featureRepository.findAll();
        assertThat(featureList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamFeature() throws Exception {
        int databaseSizeBeforeUpdate = featureRepository.findAll().size();
        feature.setId(count.incrementAndGet());

        // Create the Feature
        FeatureDTO featureDTO = featureMapper.toDto(feature);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restFeatureMockMvc
            .perform(put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(featureDTO)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the Feature in the database
        List<Feature> featureList = featureRepository.findAll();
        assertThat(featureList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateFeatureWithPatch() throws Exception {
        // Initialize the database
        featureRepository.saveAndFlush(feature);

        int databaseSizeBeforeUpdate = featureRepository.findAll().size();

        // Update the feature using partial update
        Feature partialUpdatedFeature = new Feature();
        partialUpdatedFeature.setId(feature.getId());

        partialUpdatedFeature.name(UPDATED_NAME).value(UPDATED_VALUE).unit(UPDATED_UNIT);

        restFeatureMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedFeature.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedFeature))
            )
            .andExpect(status().isOk());

        // Validate the Feature in the database
        List<Feature> featureList = featureRepository.findAll();
        assertThat(featureList).hasSize(databaseSizeBeforeUpdate);
        Feature testFeature = featureList.get(featureList.size() - 1);
        assertThat(testFeature.getName()).isEqualTo(UPDATED_NAME);
        assertThat(testFeature.getValue()).isEqualTo(UPDATED_VALUE);
        assertThat(testFeature.getUnit()).isEqualTo(UPDATED_UNIT);
    }

    @Test
    @Transactional
    void fullUpdateFeatureWithPatch() throws Exception {
        // Initialize the database
        featureRepository.saveAndFlush(feature);

        int databaseSizeBeforeUpdate = featureRepository.findAll().size();

        // Update the feature using partial update
        Feature partialUpdatedFeature = new Feature();
        partialUpdatedFeature.setId(feature.getId());

        partialUpdatedFeature.name(UPDATED_NAME).value(UPDATED_VALUE).unit(UPDATED_UNIT);

        restFeatureMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedFeature.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedFeature))
            )
            .andExpect(status().isOk());

        // Validate the Feature in the database
        List<Feature> featureList = featureRepository.findAll();
        assertThat(featureList).hasSize(databaseSizeBeforeUpdate);
        Feature testFeature = featureList.get(featureList.size() - 1);
        assertThat(testFeature.getName()).isEqualTo(UPDATED_NAME);
        assertThat(testFeature.getValue()).isEqualTo(UPDATED_VALUE);
        assertThat(testFeature.getUnit()).isEqualTo(UPDATED_UNIT);
    }

    @Test
    @Transactional
    void patchNonExistingFeature() throws Exception {
        int databaseSizeBeforeUpdate = featureRepository.findAll().size();
        feature.setId(count.incrementAndGet());

        // Create the Feature
        FeatureDTO featureDTO = featureMapper.toDto(feature);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restFeatureMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, featureDTO.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(featureDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the Feature in the database
        List<Feature> featureList = featureRepository.findAll();
        assertThat(featureList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchFeature() throws Exception {
        int databaseSizeBeforeUpdate = featureRepository.findAll().size();
        feature.setId(count.incrementAndGet());

        // Create the Feature
        FeatureDTO featureDTO = featureMapper.toDto(feature);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restFeatureMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(featureDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the Feature in the database
        List<Feature> featureList = featureRepository.findAll();
        assertThat(featureList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamFeature() throws Exception {
        int databaseSizeBeforeUpdate = featureRepository.findAll().size();
        feature.setId(count.incrementAndGet());

        // Create the Feature
        FeatureDTO featureDTO = featureMapper.toDto(feature);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restFeatureMockMvc
            .perform(
                patch(ENTITY_API_URL).contentType("application/merge-patch+json").content(TestUtil.convertObjectToJsonBytes(featureDTO))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the Feature in the database
        List<Feature> featureList = featureRepository.findAll();
        assertThat(featureList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteFeature() throws Exception {
        // Initialize the database
        featureRepository.saveAndFlush(feature);

        int databaseSizeBeforeDelete = featureRepository.findAll().size();

        // Delete the feature
        restFeatureMockMvc
            .perform(delete(ENTITY_API_URL_ID, feature.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Feature> featureList = featureRepository.findAll();
        assertThat(featureList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
