package com.cryptocurrencyservices.masternodessuplement.api.web.rest;

import com.cryptocurrencyservices.masternodessuplement.api.MasternodesOnlineSupplementApiApp;
<<<<<<< HEAD:src/test/java/com/cryptocurrencyservices/masternodessuplement/api/web/rest/MasternodesOnlineSupplementPublicResourceIntTest.java

import com.cryptocurrencyservices.masternodessuplement.api.domain.MasternodesOnlineSupplement;
import com.cryptocurrencyservices.masternodessuplement.api.repository.MasternodesOnlineSupplementRepository;
import com.cryptocurrencyservices.masternodessuplement.api.repository.search.MasternodesOnlineSupplementSearchRepository;
import com.cryptocurrencyservices.masternodessuplement.api.service.MasternodesOnlineSupplementService;
import com.cryptocurrencyservices.masternodessuplement.api.service.dto.MasternodesOnlineSupplementDTO;
import com.cryptocurrencyservices.masternodessuplement.api.service.mapper.MasternodesOnlineSupplementMapper;
=======
import com.cryptocurrencyservices.masternodessuplement.api.config.TestSecurityConfiguration;
import com.cryptocurrencyservices.masternodessuplement.api.domain.MasternodesOnlineSupplementPublic;
import com.cryptocurrencyservices.masternodessuplement.api.repository.MasternodesOnlineSupplementPublicRepository;
import com.cryptocurrencyservices.masternodessuplement.api.repository.search.MasternodesOnlineSupplementPublicSearchRepository;
import com.cryptocurrencyservices.masternodessuplement.api.service.MasternodesOnlineSupplementPublicService;
import com.cryptocurrencyservices.masternodessuplement.api.service.dto.MasternodesOnlineSupplementPublicDTO;
import com.cryptocurrencyservices.masternodessuplement.api.service.mapper.MasternodesOnlineSupplementPublicMapper;
>>>>>>> jhipster_upgrade:src/test/java/com/cryptocurrencyservices/masternodessuplement/api/web/rest/MasternodesOnlineSupplementPublicResourceIT.java
import com.cryptocurrencyservices.masternodessuplement.api.web.rest.errors.ExceptionTranslator;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.validation.Validator;


import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Collections;
import java.util.List;

import static com.cryptocurrencyservices.masternodessuplement.api.web.rest.TestUtil.createFormattingConversionService;
import static org.assertj.core.api.Assertions.assertThat;
import static org.elasticsearch.index.query.QueryBuilders.queryStringQuery;
import static org.hamcrest.Matchers.hasItem;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
<<<<<<< HEAD:src/test/java/com/cryptocurrencyservices/masternodessuplement/api/web/rest/MasternodesOnlineSupplementPublicResourceIntTest.java
 * Test class for the MasternodesOnlineSupplementResource REST controller.
 *
 * @see MasternodesOnlineSupplementPublicResource
=======
 * Integration tests for the {@link MasternodesOnlineSupplementPublicResource} REST controller.
>>>>>>> jhipster_upgrade:src/test/java/com/cryptocurrencyservices/masternodessuplement/api/web/rest/MasternodesOnlineSupplementPublicResourceIT.java
 */
@SpringBootTest(classes = {MasternodesOnlineSupplementApiApp.class, TestSecurityConfiguration.class})
public class MasternodesOnlineSupplementPublicResourceIT {

    private static final String DEFAULT_COIN = "AAAAAAAAAA";
    private static final String UPDATED_COIN = "BBBBBBBBBB";

    private static final String DEFAULT_PRICE = "AAAAAAAAAA";
    private static final String UPDATED_PRICE = "BBBBBBBBBB";

    private static final String DEFAULT_CHANGE = "AAAAAAAAAA";
    private static final String UPDATED_CHANGE = "BBBBBBBBBB";

    private static final String DEFAULT_VOLUME = "AAAAAAAAAA";
    private static final String UPDATED_VOLUME = "BBBBBBBBBB";

    private static final String DEFAULT_MARKETCAP = "AAAAAAAAAA";
    private static final String UPDATED_MARKETCAP = "BBBBBBBBBB";

    private static final String DEFAULT_ROI = "AAAAAAAAAA";
    private static final String UPDATED_ROI = "BBBBBBBBBB";

    private static final String DEFAULT_NODES = "AAAAAAAAAA";
    private static final String UPDATED_NODES = "BBBBBBBBBB";

    private static final String DEFAULT_NUMBER_REQUIRED = "AAAAAAAAAA";
    private static final String UPDATED_NUMBER_REQUIRED = "BBBBBBBBBB";

    private static final String DEFAULT_MINIMUM_WORTH = "AAAAAAAAAA";
    private static final String UPDATED_MINIMUM_WORTH = "BBBBBBBBBB";

    private static final String DEFAULT_PROJECT_ORIGIN = "AAAAAAAAAA";
    private static final String UPDATED_PROJECT_ORIGIN = "BBBBBBBBBB";

    private static final String DEFAULT_MASTERNODES_ONLINE_URL = "AAAAAAAAAA";
    private static final String UPDATED_MASTERNODES_ONLINE_URL = "BBBBBBBBBB";

    private static final String DEFAULT_GITHUB_URL = "AAAAAAAAAA";
    private static final String UPDATED_GITHUB_URL = "BBBBBBBBBB";

    private static final Integer DEFAULT_GITHUB_COMMITS = 1;
    private static final Integer UPDATED_GITHUB_COMMITS = 2;
    private static final Integer SMALLER_GITHUB_COMMITS = 1 - 1;

    private static final Instant DEFAULT_CREATED_AT = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_CREATED_AT = Instant.now().truncatedTo(ChronoUnit.MILLIS);
    private static final Instant SMALLER_CREATED_AT = Instant.ofEpochMilli(-1L);

    private static final Instant DEFAULT_PUSHED_AT = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_PUSHED_AT = Instant.now().truncatedTo(ChronoUnit.MILLIS);
    private static final Instant SMALLER_PUSHED_AT = Instant.ofEpochMilli(-1L);

    private static final String DEFAULT_NOTES = "AAAAAAAAAA";
    private static final String UPDATED_NOTES = "BBBBBBBBBB";

    @Autowired
    private MasternodesOnlineSupplementRepository masternodesOnlineSupplementPublicRepository;

    @Autowired
    private MasternodesOnlineSupplementMapper masternodesOnlineSupplementPublicMapper;

    @Autowired
    private MasternodesOnlineSupplementService masternodesOnlineSupplementPublicService;

    /**
     * This repository is mocked in the com.cryptocurrencyservices.masternodessuplement.api.repository.search test package.
     *
     * @see com.cryptocurrencyservices.masternodessuplement.api.repository.search.MasternodesOnlineSupplementSearchRepositoryMockConfiguration
     */
    @Autowired
    private MasternodesOnlineSupplementSearchRepository mockMasternodesOnlineSupplementSearchRepository;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private Validator validator;

    private MockMvc restMasternodesOnlineSupplementMockMvc;

    private MasternodesOnlineSupplement masternodesOnlineSupplementPublic;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final MasternodesOnlineSupplementPublicResource masternodesOnlineSupplementPublicResource = new MasternodesOnlineSupplementPublicResource(masternodesOnlineSupplementPublicService);
        this.restMasternodesOnlineSupplementMockMvc = MockMvcBuilders.standaloneSetup(masternodesOnlineSupplementPublicResource)
            .setCustomArgumentResolvers(pageableArgumentResolver)
            .setControllerAdvice(exceptionTranslator)
            .setConversionService(createFormattingConversionService())
            .setMessageConverters(jacksonMessageConverter)
            .setValidator(validator).build();
    }

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static MasternodesOnlineSupplement createEntity() {
        MasternodesOnlineSupplement masternodesOnlineSupplementPublic = new MasternodesOnlineSupplement()
            .coin(DEFAULT_COIN)
            .price(DEFAULT_PRICE)
            .change(DEFAULT_CHANGE)
            .volume(DEFAULT_VOLUME)
            .marketcap(DEFAULT_MARKETCAP)
            .roi(DEFAULT_ROI)
            .nodes(DEFAULT_NODES)
            .numberRequired(DEFAULT_NUMBER_REQUIRED)
            .minimumWorth(DEFAULT_MINIMUM_WORTH)
            .projectOrigin(DEFAULT_PROJECT_ORIGIN)
            .masternodesOnlineUrl(DEFAULT_MASTERNODES_ONLINE_URL)
            .githubUrl(DEFAULT_GITHUB_URL)
            .githubCommits(DEFAULT_GITHUB_COMMITS)
            .createdAt(DEFAULT_CREATED_AT)
            .pushedAt(DEFAULT_PUSHED_AT)
            .notes(DEFAULT_NOTES);
        return masternodesOnlineSupplementPublic;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static MasternodesOnlineSupplementPublic createUpdatedEntity() {
        MasternodesOnlineSupplementPublic masternodesOnlineSupplementPublic = new MasternodesOnlineSupplementPublic()
            .coin(UPDATED_COIN)
            .price(UPDATED_PRICE)
            .change(UPDATED_CHANGE)
            .volume(UPDATED_VOLUME)
            .marketcap(UPDATED_MARKETCAP)
            .roi(UPDATED_ROI)
            .nodes(UPDATED_NODES)
            .numberRequired(UPDATED_NUMBER_REQUIRED)
            .minimumWorth(UPDATED_MINIMUM_WORTH)
            .projectOrigin(UPDATED_PROJECT_ORIGIN)
            .masternodesOnlineUrl(UPDATED_MASTERNODES_ONLINE_URL)
            .githubUrl(UPDATED_GITHUB_URL)
            .githubCommits(UPDATED_GITHUB_COMMITS)
            .createdAt(UPDATED_CREATED_AT)
            .pushedAt(UPDATED_PUSHED_AT)
            .notes(UPDATED_NOTES);
        return masternodesOnlineSupplementPublic;
    }

    @BeforeEach
    public void initTest() {
        masternodesOnlineSupplementPublicRepository.deleteAll();
        masternodesOnlineSupplementPublic = createEntity();
    }

    @Test
    public void createMasternodesOnlineSupplement() throws Exception {
        int databaseSizeBeforeCreate = masternodesOnlineSupplementPublicRepository.findAll().size();

        // Create the MasternodesOnlineSupplement
        MasternodesOnlineSupplementDTO masternodesOnlineSupplementPublicDTO = masternodesOnlineSupplementPublicMapper.toDto(masternodesOnlineSupplementPublic);
        restMasternodesOnlineSupplementMockMvc.perform(post("/api/masternodes-online-supplement-publics")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(masternodesOnlineSupplementPublicDTO)))
            .andExpect(status().isCreated());

        // Validate the MasternodesOnlineSupplement in the database
        List<MasternodesOnlineSupplement> masternodesOnlineSupplementPublicList = masternodesOnlineSupplementPublicRepository.findAll();
        assertThat(masternodesOnlineSupplementPublicList).hasSize(databaseSizeBeforeCreate + 1);
        MasternodesOnlineSupplement testMasternodesOnlineSupplement = masternodesOnlineSupplementPublicList.get(masternodesOnlineSupplementPublicList.size() - 1);
        assertThat(testMasternodesOnlineSupplement.getCoin()).isEqualTo(DEFAULT_COIN);
        assertThat(testMasternodesOnlineSupplement.getPrice()).isEqualTo(DEFAULT_PRICE);
        assertThat(testMasternodesOnlineSupplement.getChange()).isEqualTo(DEFAULT_CHANGE);
        assertThat(testMasternodesOnlineSupplement.getVolume()).isEqualTo(DEFAULT_VOLUME);
        assertThat(testMasternodesOnlineSupplement.getMarketcap()).isEqualTo(DEFAULT_MARKETCAP);
        assertThat(testMasternodesOnlineSupplement.getRoi()).isEqualTo(DEFAULT_ROI);
        assertThat(testMasternodesOnlineSupplement.getNodes()).isEqualTo(DEFAULT_NODES);
        assertThat(testMasternodesOnlineSupplement.getNumberRequired()).isEqualTo(DEFAULT_NUMBER_REQUIRED);
        assertThat(testMasternodesOnlineSupplement.getMinimumWorth()).isEqualTo(DEFAULT_MINIMUM_WORTH);
        assertThat(testMasternodesOnlineSupplement.getProjectOrigin()).isEqualTo(DEFAULT_PROJECT_ORIGIN);
        assertThat(testMasternodesOnlineSupplement.getMasternodesOnlineUrl()).isEqualTo(DEFAULT_MASTERNODES_ONLINE_URL);
        assertThat(testMasternodesOnlineSupplement.getGithubUrl()).isEqualTo(DEFAULT_GITHUB_URL);
        assertThat(testMasternodesOnlineSupplement.getGithubCommits()).isEqualTo(DEFAULT_GITHUB_COMMITS);
        assertThat(testMasternodesOnlineSupplement.getCreatedAt()).isEqualTo(DEFAULT_CREATED_AT);
        assertThat(testMasternodesOnlineSupplement.getPushedAt()).isEqualTo(DEFAULT_PUSHED_AT);
        assertThat(testMasternodesOnlineSupplement.getNotes()).isEqualTo(DEFAULT_NOTES);

        // Validate the MasternodesOnlineSupplement in Elasticsearch
        verify(mockMasternodesOnlineSupplementSearchRepository, times(1)).save(testMasternodesOnlineSupplement);
    }

    @Test
    public void createMasternodesOnlineSupplementWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = masternodesOnlineSupplementPublicRepository.findAll().size();

        // Create the MasternodesOnlineSupplement with an existing ID
        masternodesOnlineSupplementPublic.setId("existing_id");
        MasternodesOnlineSupplementDTO masternodesOnlineSupplementPublicDTO = masternodesOnlineSupplementPublicMapper.toDto(masternodesOnlineSupplementPublic);

        // An entity with an existing ID cannot be created, so this API call must fail
        restMasternodesOnlineSupplementMockMvc.perform(post("/api/masternodes-online-supplement-publics")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(masternodesOnlineSupplementPublicDTO)))
            .andExpect(status().isBadRequest());

        // Validate the MasternodesOnlineSupplement in the database
        List<MasternodesOnlineSupplement> masternodesOnlineSupplementPublicList = masternodesOnlineSupplementPublicRepository.findAll();
        assertThat(masternodesOnlineSupplementPublicList).hasSize(databaseSizeBeforeCreate);

        // Validate the MasternodesOnlineSupplement in Elasticsearch
        verify(mockMasternodesOnlineSupplementSearchRepository, times(0)).save(masternodesOnlineSupplementPublic);
    }


    @Test
    public void getAllMasternodesOnlineSupplements() throws Exception {
        // Initialize the database
        masternodesOnlineSupplementPublicRepository.save(masternodesOnlineSupplementPublic);

        // Get all the masternodesOnlineSupplementPublicList
        restMasternodesOnlineSupplementMockMvc.perform(get("/api/masternodes-online-supplement-publics?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(masternodesOnlineSupplementPublic.getId())))
            .andExpect(jsonPath("$.[*].coin").value(hasItem(DEFAULT_COIN.toString())))
            .andExpect(jsonPath("$.[*].price").value(hasItem(DEFAULT_PRICE.toString())))
            .andExpect(jsonPath("$.[*].change").value(hasItem(DEFAULT_CHANGE.toString())))
            .andExpect(jsonPath("$.[*].volume").value(hasItem(DEFAULT_VOLUME.toString())))
            .andExpect(jsonPath("$.[*].marketcap").value(hasItem(DEFAULT_MARKETCAP.toString())))
            .andExpect(jsonPath("$.[*].roi").value(hasItem(DEFAULT_ROI.toString())))
            .andExpect(jsonPath("$.[*].nodes").value(hasItem(DEFAULT_NODES.toString())))
            .andExpect(jsonPath("$.[*].numberRequired").value(hasItem(DEFAULT_NUMBER_REQUIRED.toString())))
            .andExpect(jsonPath("$.[*].minimumWorth").value(hasItem(DEFAULT_MINIMUM_WORTH.toString())))
            .andExpect(jsonPath("$.[*].projectOrigin").value(hasItem(DEFAULT_PROJECT_ORIGIN.toString())))
            .andExpect(jsonPath("$.[*].masternodesOnlineUrl").value(hasItem(DEFAULT_MASTERNODES_ONLINE_URL.toString())))
            .andExpect(jsonPath("$.[*].githubUrl").value(hasItem(DEFAULT_GITHUB_URL.toString())))
            .andExpect(jsonPath("$.[*].githubCommits").value(hasItem(DEFAULT_GITHUB_COMMITS)))
            .andExpect(jsonPath("$.[*].createdAt").value(hasItem(DEFAULT_CREATED_AT.toString())))
            .andExpect(jsonPath("$.[*].pushedAt").value(hasItem(DEFAULT_PUSHED_AT.toString())))
            .andExpect(jsonPath("$.[*].notes").value(hasItem(DEFAULT_NOTES.toString())));
    }

    @Test
    public void getMasternodesOnlineSupplement() throws Exception {
        // Initialize the database
        masternodesOnlineSupplementPublicRepository.save(masternodesOnlineSupplementPublic);

        // Get the masternodesOnlineSupplementPublic
        restMasternodesOnlineSupplementMockMvc.perform(get("/api/masternodes-online-supplement-publics/{id}", masternodesOnlineSupplementPublic.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(masternodesOnlineSupplementPublic.getId()))
            .andExpect(jsonPath("$.coin").value(DEFAULT_COIN.toString()))
            .andExpect(jsonPath("$.price").value(DEFAULT_PRICE.toString()))
            .andExpect(jsonPath("$.change").value(DEFAULT_CHANGE.toString()))
            .andExpect(jsonPath("$.volume").value(DEFAULT_VOLUME.toString()))
            .andExpect(jsonPath("$.marketcap").value(DEFAULT_MARKETCAP.toString()))
            .andExpect(jsonPath("$.roi").value(DEFAULT_ROI.toString()))
            .andExpect(jsonPath("$.nodes").value(DEFAULT_NODES.toString()))
            .andExpect(jsonPath("$.numberRequired").value(DEFAULT_NUMBER_REQUIRED.toString()))
            .andExpect(jsonPath("$.minimumWorth").value(DEFAULT_MINIMUM_WORTH.toString()))
            .andExpect(jsonPath("$.projectOrigin").value(DEFAULT_PROJECT_ORIGIN.toString()))
            .andExpect(jsonPath("$.masternodesOnlineUrl").value(DEFAULT_MASTERNODES_ONLINE_URL.toString()))
            .andExpect(jsonPath("$.githubUrl").value(DEFAULT_GITHUB_URL.toString()))
            .andExpect(jsonPath("$.githubCommits").value(DEFAULT_GITHUB_COMMITS))
            .andExpect(jsonPath("$.createdAt").value(DEFAULT_CREATED_AT.toString()))
            .andExpect(jsonPath("$.pushedAt").value(DEFAULT_PUSHED_AT.toString()))
            .andExpect(jsonPath("$.notes").value(DEFAULT_NOTES.toString()));
    }

    @Test
    public void getNonExistingMasternodesOnlineSupplement() throws Exception {
        // Get the masternodesOnlineSupplementPublic
        restMasternodesOnlineSupplementMockMvc.perform(get("/api/masternodes-online-supplement-publics/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    public void updateMasternodesOnlineSupplement() throws Exception {
        // Initialize the database
        masternodesOnlineSupplementPublicRepository.save(masternodesOnlineSupplementPublic);

        int databaseSizeBeforeUpdate = masternodesOnlineSupplementPublicRepository.findAll().size();

        // Update the masternodesOnlineSupplementPublic
        MasternodesOnlineSupplement updatedMasternodesOnlineSupplement = masternodesOnlineSupplementPublicRepository.findById(masternodesOnlineSupplementPublic.getId()).get();
        updatedMasternodesOnlineSupplement
            .coin(UPDATED_COIN)
            .price(UPDATED_PRICE)
            .change(UPDATED_CHANGE)
            .volume(UPDATED_VOLUME)
            .marketcap(UPDATED_MARKETCAP)
            .roi(UPDATED_ROI)
            .nodes(UPDATED_NODES)
            .numberRequired(UPDATED_NUMBER_REQUIRED)
            .minimumWorth(UPDATED_MINIMUM_WORTH)
            .projectOrigin(UPDATED_PROJECT_ORIGIN)
            .masternodesOnlineUrl(UPDATED_MASTERNODES_ONLINE_URL)
            .githubUrl(UPDATED_GITHUB_URL)
            .githubCommits(UPDATED_GITHUB_COMMITS)
            .createdAt(UPDATED_CREATED_AT)
            .pushedAt(UPDATED_PUSHED_AT)
            .notes(UPDATED_NOTES);
        MasternodesOnlineSupplementDTO masternodesOnlineSupplementPublicDTO = masternodesOnlineSupplementPublicMapper.toDto(updatedMasternodesOnlineSupplement);

        restMasternodesOnlineSupplementMockMvc.perform(put("/api/masternodes-online-supplement-publics")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(masternodesOnlineSupplementPublicDTO)))
            .andExpect(status().isOk());

        // Validate the MasternodesOnlineSupplement in the database
        List<MasternodesOnlineSupplement> masternodesOnlineSupplementPublicList = masternodesOnlineSupplementPublicRepository.findAll();
        assertThat(masternodesOnlineSupplementPublicList).hasSize(databaseSizeBeforeUpdate);
        MasternodesOnlineSupplement testMasternodesOnlineSupplement = masternodesOnlineSupplementPublicList.get(masternodesOnlineSupplementPublicList.size() - 1);
        assertThat(testMasternodesOnlineSupplement.getCoin()).isEqualTo(UPDATED_COIN);
        assertThat(testMasternodesOnlineSupplement.getPrice()).isEqualTo(UPDATED_PRICE);
        assertThat(testMasternodesOnlineSupplement.getChange()).isEqualTo(UPDATED_CHANGE);
        assertThat(testMasternodesOnlineSupplement.getVolume()).isEqualTo(UPDATED_VOLUME);
        assertThat(testMasternodesOnlineSupplement.getMarketcap()).isEqualTo(UPDATED_MARKETCAP);
        assertThat(testMasternodesOnlineSupplement.getRoi()).isEqualTo(UPDATED_ROI);
        assertThat(testMasternodesOnlineSupplement.getNodes()).isEqualTo(UPDATED_NODES);
        assertThat(testMasternodesOnlineSupplement.getNumberRequired()).isEqualTo(UPDATED_NUMBER_REQUIRED);
        assertThat(testMasternodesOnlineSupplement.getMinimumWorth()).isEqualTo(UPDATED_MINIMUM_WORTH);
        assertThat(testMasternodesOnlineSupplement.getProjectOrigin()).isEqualTo(UPDATED_PROJECT_ORIGIN);
        assertThat(testMasternodesOnlineSupplement.getMasternodesOnlineUrl()).isEqualTo(UPDATED_MASTERNODES_ONLINE_URL);
        assertThat(testMasternodesOnlineSupplement.getGithubUrl()).isEqualTo(UPDATED_GITHUB_URL);
        assertThat(testMasternodesOnlineSupplement.getGithubCommits()).isEqualTo(UPDATED_GITHUB_COMMITS);
        assertThat(testMasternodesOnlineSupplement.getCreatedAt()).isEqualTo(UPDATED_CREATED_AT);
        assertThat(testMasternodesOnlineSupplement.getPushedAt()).isEqualTo(UPDATED_PUSHED_AT);
        assertThat(testMasternodesOnlineSupplement.getNotes()).isEqualTo(UPDATED_NOTES);

        // Validate the MasternodesOnlineSupplement in Elasticsearch
        verify(mockMasternodesOnlineSupplementSearchRepository, times(1)).save(testMasternodesOnlineSupplement);
    }

    @Test
    public void updateNonExistingMasternodesOnlineSupplement() throws Exception {
        int databaseSizeBeforeUpdate = masternodesOnlineSupplementPublicRepository.findAll().size();

        // Create the MasternodesOnlineSupplement
        MasternodesOnlineSupplementDTO masternodesOnlineSupplementPublicDTO = masternodesOnlineSupplementPublicMapper.toDto(masternodesOnlineSupplementPublic);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restMasternodesOnlineSupplementMockMvc.perform(put("/api/masternodes-online-supplement-publics")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(masternodesOnlineSupplementPublicDTO)))
            .andExpect(status().isBadRequest());

        // Validate the MasternodesOnlineSupplement in the database
        List<MasternodesOnlineSupplement> masternodesOnlineSupplementPublicList = masternodesOnlineSupplementPublicRepository.findAll();
        assertThat(masternodesOnlineSupplementPublicList).hasSize(databaseSizeBeforeUpdate);

        // Validate the MasternodesOnlineSupplement in Elasticsearch
        verify(mockMasternodesOnlineSupplementSearchRepository, times(0)).save(masternodesOnlineSupplementPublic);
    }

    @Test
    public void deleteMasternodesOnlineSupplement() throws Exception {
        // Initialize the database
        masternodesOnlineSupplementPublicRepository.save(masternodesOnlineSupplementPublic);

        int databaseSizeBeforeDelete = masternodesOnlineSupplementPublicRepository.findAll().size();

        // Delete the masternodesOnlineSupplementPublic
        restMasternodesOnlineSupplementMockMvc.perform(delete("/api/masternodes-online-supplement-publics/{id}", masternodesOnlineSupplementPublic.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

<<<<<<< HEAD:src/test/java/com/cryptocurrencyservices/masternodessuplement/api/web/rest/MasternodesOnlineSupplementPublicResourceIntTest.java
        // Validate the database is empty
        List<MasternodesOnlineSupplement> masternodesOnlineSupplementPublicList = masternodesOnlineSupplementPublicRepository.findAll();
=======
        // Validate the database contains one less item
        List<MasternodesOnlineSupplementPublic> masternodesOnlineSupplementPublicList = masternodesOnlineSupplementPublicRepository.findAll();
>>>>>>> jhipster_upgrade:src/test/java/com/cryptocurrencyservices/masternodessuplement/api/web/rest/MasternodesOnlineSupplementPublicResourceIT.java
        assertThat(masternodesOnlineSupplementPublicList).hasSize(databaseSizeBeforeDelete - 1);

        // Validate the MasternodesOnlineSupplement in Elasticsearch
        verify(mockMasternodesOnlineSupplementSearchRepository, times(1)).deleteById(masternodesOnlineSupplementPublic.getId());
    }

    @Test
    public void searchMasternodesOnlineSupplement() throws Exception {
        // Initialize the database
        masternodesOnlineSupplementPublicRepository.save(masternodesOnlineSupplementPublic);
        when(mockMasternodesOnlineSupplementSearchRepository.search(queryStringQuery("id:" + masternodesOnlineSupplementPublic.getId()), PageRequest.of(0, 20)))
            .thenReturn(new PageImpl<>(Collections.singletonList(masternodesOnlineSupplementPublic), PageRequest.of(0, 1), 1));
        // Search the masternodesOnlineSupplementPublic
        restMasternodesOnlineSupplementMockMvc.perform(get("/api/_search/masternodes-online-supplement-publics?query=id:" + masternodesOnlineSupplementPublic.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(masternodesOnlineSupplementPublic.getId())))
            .andExpect(jsonPath("$.[*].coin").value(hasItem(DEFAULT_COIN)))
            .andExpect(jsonPath("$.[*].price").value(hasItem(DEFAULT_PRICE)))
            .andExpect(jsonPath("$.[*].change").value(hasItem(DEFAULT_CHANGE)))
            .andExpect(jsonPath("$.[*].volume").value(hasItem(DEFAULT_VOLUME)))
            .andExpect(jsonPath("$.[*].marketcap").value(hasItem(DEFAULT_MARKETCAP)))
            .andExpect(jsonPath("$.[*].roi").value(hasItem(DEFAULT_ROI)))
            .andExpect(jsonPath("$.[*].nodes").value(hasItem(DEFAULT_NODES)))
            .andExpect(jsonPath("$.[*].numberRequired").value(hasItem(DEFAULT_NUMBER_REQUIRED)))
            .andExpect(jsonPath("$.[*].minimumWorth").value(hasItem(DEFAULT_MINIMUM_WORTH)))
            .andExpect(jsonPath("$.[*].projectOrigin").value(hasItem(DEFAULT_PROJECT_ORIGIN)))
            .andExpect(jsonPath("$.[*].masternodesOnlineUrl").value(hasItem(DEFAULT_MASTERNODES_ONLINE_URL)))
            .andExpect(jsonPath("$.[*].githubUrl").value(hasItem(DEFAULT_GITHUB_URL)))
            .andExpect(jsonPath("$.[*].githubCommits").value(hasItem(DEFAULT_GITHUB_COMMITS)))
            .andExpect(jsonPath("$.[*].createdAt").value(hasItem(DEFAULT_CREATED_AT.toString())))
            .andExpect(jsonPath("$.[*].pushedAt").value(hasItem(DEFAULT_PUSHED_AT.toString())))
            .andExpect(jsonPath("$.[*].notes").value(hasItem(DEFAULT_NOTES)));
    }

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(MasternodesOnlineSupplement.class);
        MasternodesOnlineSupplement masternodesOnlineSupplementPublic1 = new MasternodesOnlineSupplement();
        masternodesOnlineSupplementPublic1.setId("id1");
        MasternodesOnlineSupplement masternodesOnlineSupplementPublic2 = new MasternodesOnlineSupplement();
        masternodesOnlineSupplementPublic2.setId(masternodesOnlineSupplementPublic1.getId());
        assertThat(masternodesOnlineSupplementPublic1).isEqualTo(masternodesOnlineSupplementPublic2);
        masternodesOnlineSupplementPublic2.setId("id2");
        assertThat(masternodesOnlineSupplementPublic1).isNotEqualTo(masternodesOnlineSupplementPublic2);
        masternodesOnlineSupplementPublic1.setId(null);
        assertThat(masternodesOnlineSupplementPublic1).isNotEqualTo(masternodesOnlineSupplementPublic2);
    }

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(MasternodesOnlineSupplementDTO.class);
        MasternodesOnlineSupplementDTO masternodesOnlineSupplementPublicDTO1 = new MasternodesOnlineSupplementDTO();
        masternodesOnlineSupplementPublicDTO1.setId("id1");
        MasternodesOnlineSupplementDTO masternodesOnlineSupplementPublicDTO2 = new MasternodesOnlineSupplementDTO();
        assertThat(masternodesOnlineSupplementPublicDTO1).isNotEqualTo(masternodesOnlineSupplementPublicDTO2);
        masternodesOnlineSupplementPublicDTO2.setId(masternodesOnlineSupplementPublicDTO1.getId());
        assertThat(masternodesOnlineSupplementPublicDTO1).isEqualTo(masternodesOnlineSupplementPublicDTO2);
        masternodesOnlineSupplementPublicDTO2.setId("id2");
        assertThat(masternodesOnlineSupplementPublicDTO1).isNotEqualTo(masternodesOnlineSupplementPublicDTO2);
        masternodesOnlineSupplementPublicDTO1.setId(null);
        assertThat(masternodesOnlineSupplementPublicDTO1).isNotEqualTo(masternodesOnlineSupplementPublicDTO2);
    }
}
