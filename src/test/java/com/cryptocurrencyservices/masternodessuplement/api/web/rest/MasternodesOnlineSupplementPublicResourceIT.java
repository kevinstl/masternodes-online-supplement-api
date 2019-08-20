package com.cryptocurrencyservices.masternodessuplement.api.web.rest;

import com.cryptocurrencyservices.masternodessuplement.api.MasternodesOnlineSupplementApiApp;
import com.cryptocurrencyservices.masternodessuplement.api.config.TestSecurityConfiguration;
import com.cryptocurrencyservices.masternodessuplement.api.domain.MasternodesOnlineSupplementPublic;
import com.cryptocurrencyservices.masternodessuplement.api.repository.MasternodesOnlineSupplementPublicRepository;
import com.cryptocurrencyservices.masternodessuplement.api.repository.search.MasternodesOnlineSupplementPublicSearchRepository;
import com.cryptocurrencyservices.masternodessuplement.api.service.MasternodesOnlineSupplementPublicService;
import com.cryptocurrencyservices.masternodessuplement.api.service.dto.MasternodesOnlineSupplementPublicDTO;
import com.cryptocurrencyservices.masternodessuplement.api.service.mapper.MasternodesOnlineSupplementPublicMapper;
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
 * Integration tests for the {@link MasternodesOnlineSupplementPublicResource} REST controller.
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
    private MasternodesOnlineSupplementPublicRepository masternodesOnlineSupplementPublicRepository;

    @Autowired
    private MasternodesOnlineSupplementPublicMapper masternodesOnlineSupplementPublicMapper;

    @Autowired
    private MasternodesOnlineSupplementPublicService masternodesOnlineSupplementPublicService;

    /**
     * This repository is mocked in the com.cryptocurrencyservices.masternodessuplement.api.repository.search test package.
     *
     * @see com.cryptocurrencyservices.masternodessuplement.api.repository.search.MasternodesOnlineSupplementPublicSearchRepositoryMockConfiguration
     */
    @Autowired
    private MasternodesOnlineSupplementPublicSearchRepository mockMasternodesOnlineSupplementPublicSearchRepository;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private Validator validator;

    private MockMvc restMasternodesOnlineSupplementPublicMockMvc;

    private MasternodesOnlineSupplementPublic masternodesOnlineSupplementPublic;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final MasternodesOnlineSupplementPublicResource masternodesOnlineSupplementPublicResource = new MasternodesOnlineSupplementPublicResource(masternodesOnlineSupplementPublicService);
        this.restMasternodesOnlineSupplementPublicMockMvc = MockMvcBuilders.standaloneSetup(masternodesOnlineSupplementPublicResource)
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
    public static MasternodesOnlineSupplementPublic createEntity() {
        MasternodesOnlineSupplementPublic masternodesOnlineSupplementPublic = new MasternodesOnlineSupplementPublic()
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
    public void createMasternodesOnlineSupplementPublic() throws Exception {
        int databaseSizeBeforeCreate = masternodesOnlineSupplementPublicRepository.findAll().size();

        // Create the MasternodesOnlineSupplementPublic
        MasternodesOnlineSupplementPublicDTO masternodesOnlineSupplementPublicDTO = masternodesOnlineSupplementPublicMapper.toDto(masternodesOnlineSupplementPublic);
        restMasternodesOnlineSupplementPublicMockMvc.perform(post("/api/masternodes-online-supplement-publics")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(masternodesOnlineSupplementPublicDTO)))
            .andExpect(status().isCreated());

        // Validate the MasternodesOnlineSupplementPublic in the database
        List<MasternodesOnlineSupplementPublic> masternodesOnlineSupplementPublicList = masternodesOnlineSupplementPublicRepository.findAll();
        assertThat(masternodesOnlineSupplementPublicList).hasSize(databaseSizeBeforeCreate + 1);
        MasternodesOnlineSupplementPublic testMasternodesOnlineSupplementPublic = masternodesOnlineSupplementPublicList.get(masternodesOnlineSupplementPublicList.size() - 1);
        assertThat(testMasternodesOnlineSupplementPublic.getCoin()).isEqualTo(DEFAULT_COIN);
        assertThat(testMasternodesOnlineSupplementPublic.getPrice()).isEqualTo(DEFAULT_PRICE);
        assertThat(testMasternodesOnlineSupplementPublic.getChange()).isEqualTo(DEFAULT_CHANGE);
        assertThat(testMasternodesOnlineSupplementPublic.getVolume()).isEqualTo(DEFAULT_VOLUME);
        assertThat(testMasternodesOnlineSupplementPublic.getMarketcap()).isEqualTo(DEFAULT_MARKETCAP);
        assertThat(testMasternodesOnlineSupplementPublic.getRoi()).isEqualTo(DEFAULT_ROI);
        assertThat(testMasternodesOnlineSupplementPublic.getNodes()).isEqualTo(DEFAULT_NODES);
        assertThat(testMasternodesOnlineSupplementPublic.getNumberRequired()).isEqualTo(DEFAULT_NUMBER_REQUIRED);
        assertThat(testMasternodesOnlineSupplementPublic.getMinimumWorth()).isEqualTo(DEFAULT_MINIMUM_WORTH);
        assertThat(testMasternodesOnlineSupplementPublic.getProjectOrigin()).isEqualTo(DEFAULT_PROJECT_ORIGIN);
        assertThat(testMasternodesOnlineSupplementPublic.getMasternodesOnlineUrl()).isEqualTo(DEFAULT_MASTERNODES_ONLINE_URL);
        assertThat(testMasternodesOnlineSupplementPublic.getGithubUrl()).isEqualTo(DEFAULT_GITHUB_URL);
        assertThat(testMasternodesOnlineSupplementPublic.getGithubCommits()).isEqualTo(DEFAULT_GITHUB_COMMITS);
        assertThat(testMasternodesOnlineSupplementPublic.getCreatedAt()).isEqualTo(DEFAULT_CREATED_AT);
        assertThat(testMasternodesOnlineSupplementPublic.getPushedAt()).isEqualTo(DEFAULT_PUSHED_AT);
        assertThat(testMasternodesOnlineSupplementPublic.getNotes()).isEqualTo(DEFAULT_NOTES);

        // Validate the MasternodesOnlineSupplementPublic in Elasticsearch
        verify(mockMasternodesOnlineSupplementPublicSearchRepository, times(1)).save(testMasternodesOnlineSupplementPublic);
    }

    @Test
    public void createMasternodesOnlineSupplementPublicWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = masternodesOnlineSupplementPublicRepository.findAll().size();

        // Create the MasternodesOnlineSupplementPublic with an existing ID
        masternodesOnlineSupplementPublic.setId("existing_id");
        MasternodesOnlineSupplementPublicDTO masternodesOnlineSupplementPublicDTO = masternodesOnlineSupplementPublicMapper.toDto(masternodesOnlineSupplementPublic);

        // An entity with an existing ID cannot be created, so this API call must fail
        restMasternodesOnlineSupplementPublicMockMvc.perform(post("/api/masternodes-online-supplement-publics")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(masternodesOnlineSupplementPublicDTO)))
            .andExpect(status().isBadRequest());

        // Validate the MasternodesOnlineSupplementPublic in the database
        List<MasternodesOnlineSupplementPublic> masternodesOnlineSupplementPublicList = masternodesOnlineSupplementPublicRepository.findAll();
        assertThat(masternodesOnlineSupplementPublicList).hasSize(databaseSizeBeforeCreate);

        // Validate the MasternodesOnlineSupplementPublic in Elasticsearch
        verify(mockMasternodesOnlineSupplementPublicSearchRepository, times(0)).save(masternodesOnlineSupplementPublic);
    }


    @Test
    public void getAllMasternodesOnlineSupplementPublics() throws Exception {
        // Initialize the database
        masternodesOnlineSupplementPublicRepository.save(masternodesOnlineSupplementPublic);

        // Get all the masternodesOnlineSupplementPublicList
        restMasternodesOnlineSupplementPublicMockMvc.perform(get("/api/masternodes-online-supplement-publics?sort=id,desc"))
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
    public void getMasternodesOnlineSupplementPublic() throws Exception {
        // Initialize the database
        masternodesOnlineSupplementPublicRepository.save(masternodesOnlineSupplementPublic);

        // Get the masternodesOnlineSupplementPublic
        restMasternodesOnlineSupplementPublicMockMvc.perform(get("/api/masternodes-online-supplement-publics/{id}", masternodesOnlineSupplementPublic.getId()))
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
    public void getNonExistingMasternodesOnlineSupplementPublic() throws Exception {
        // Get the masternodesOnlineSupplementPublic
        restMasternodesOnlineSupplementPublicMockMvc.perform(get("/api/masternodes-online-supplement-publics/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    public void updateMasternodesOnlineSupplementPublic() throws Exception {
        // Initialize the database
        masternodesOnlineSupplementPublicRepository.save(masternodesOnlineSupplementPublic);

        int databaseSizeBeforeUpdate = masternodesOnlineSupplementPublicRepository.findAll().size();

        // Update the masternodesOnlineSupplementPublic
        MasternodesOnlineSupplementPublic updatedMasternodesOnlineSupplementPublic = masternodesOnlineSupplementPublicRepository.findById(masternodesOnlineSupplementPublic.getId()).get();
        updatedMasternodesOnlineSupplementPublic
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
        MasternodesOnlineSupplementPublicDTO masternodesOnlineSupplementPublicDTO = masternodesOnlineSupplementPublicMapper.toDto(updatedMasternodesOnlineSupplementPublic);

        restMasternodesOnlineSupplementPublicMockMvc.perform(put("/api/masternodes-online-supplement-publics")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(masternodesOnlineSupplementPublicDTO)))
            .andExpect(status().isOk());

        // Validate the MasternodesOnlineSupplementPublic in the database
        List<MasternodesOnlineSupplementPublic> masternodesOnlineSupplementPublicList = masternodesOnlineSupplementPublicRepository.findAll();
        assertThat(masternodesOnlineSupplementPublicList).hasSize(databaseSizeBeforeUpdate);
        MasternodesOnlineSupplementPublic testMasternodesOnlineSupplementPublic = masternodesOnlineSupplementPublicList.get(masternodesOnlineSupplementPublicList.size() - 1);
        assertThat(testMasternodesOnlineSupplementPublic.getCoin()).isEqualTo(UPDATED_COIN);
        assertThat(testMasternodesOnlineSupplementPublic.getPrice()).isEqualTo(UPDATED_PRICE);
        assertThat(testMasternodesOnlineSupplementPublic.getChange()).isEqualTo(UPDATED_CHANGE);
        assertThat(testMasternodesOnlineSupplementPublic.getVolume()).isEqualTo(UPDATED_VOLUME);
        assertThat(testMasternodesOnlineSupplementPublic.getMarketcap()).isEqualTo(UPDATED_MARKETCAP);
        assertThat(testMasternodesOnlineSupplementPublic.getRoi()).isEqualTo(UPDATED_ROI);
        assertThat(testMasternodesOnlineSupplementPublic.getNodes()).isEqualTo(UPDATED_NODES);
        assertThat(testMasternodesOnlineSupplementPublic.getNumberRequired()).isEqualTo(UPDATED_NUMBER_REQUIRED);
        assertThat(testMasternodesOnlineSupplementPublic.getMinimumWorth()).isEqualTo(UPDATED_MINIMUM_WORTH);
        assertThat(testMasternodesOnlineSupplementPublic.getProjectOrigin()).isEqualTo(UPDATED_PROJECT_ORIGIN);
        assertThat(testMasternodesOnlineSupplementPublic.getMasternodesOnlineUrl()).isEqualTo(UPDATED_MASTERNODES_ONLINE_URL);
        assertThat(testMasternodesOnlineSupplementPublic.getGithubUrl()).isEqualTo(UPDATED_GITHUB_URL);
        assertThat(testMasternodesOnlineSupplementPublic.getGithubCommits()).isEqualTo(UPDATED_GITHUB_COMMITS);
        assertThat(testMasternodesOnlineSupplementPublic.getCreatedAt()).isEqualTo(UPDATED_CREATED_AT);
        assertThat(testMasternodesOnlineSupplementPublic.getPushedAt()).isEqualTo(UPDATED_PUSHED_AT);
        assertThat(testMasternodesOnlineSupplementPublic.getNotes()).isEqualTo(UPDATED_NOTES);

        // Validate the MasternodesOnlineSupplementPublic in Elasticsearch
        verify(mockMasternodesOnlineSupplementPublicSearchRepository, times(1)).save(testMasternodesOnlineSupplementPublic);
    }

    @Test
    public void updateNonExistingMasternodesOnlineSupplementPublic() throws Exception {
        int databaseSizeBeforeUpdate = masternodesOnlineSupplementPublicRepository.findAll().size();

        // Create the MasternodesOnlineSupplementPublic
        MasternodesOnlineSupplementPublicDTO masternodesOnlineSupplementPublicDTO = masternodesOnlineSupplementPublicMapper.toDto(masternodesOnlineSupplementPublic);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restMasternodesOnlineSupplementPublicMockMvc.perform(put("/api/masternodes-online-supplement-publics")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(masternodesOnlineSupplementPublicDTO)))
            .andExpect(status().isBadRequest());

        // Validate the MasternodesOnlineSupplementPublic in the database
        List<MasternodesOnlineSupplementPublic> masternodesOnlineSupplementPublicList = masternodesOnlineSupplementPublicRepository.findAll();
        assertThat(masternodesOnlineSupplementPublicList).hasSize(databaseSizeBeforeUpdate);

        // Validate the MasternodesOnlineSupplementPublic in Elasticsearch
        verify(mockMasternodesOnlineSupplementPublicSearchRepository, times(0)).save(masternodesOnlineSupplementPublic);
    }

    @Test
    public void deleteMasternodesOnlineSupplementPublic() throws Exception {
        // Initialize the database
        masternodesOnlineSupplementPublicRepository.save(masternodesOnlineSupplementPublic);

        int databaseSizeBeforeDelete = masternodesOnlineSupplementPublicRepository.findAll().size();

        // Delete the masternodesOnlineSupplementPublic
        restMasternodesOnlineSupplementPublicMockMvc.perform(delete("/api/masternodes-online-supplement-publics/{id}", masternodesOnlineSupplementPublic.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<MasternodesOnlineSupplementPublic> masternodesOnlineSupplementPublicList = masternodesOnlineSupplementPublicRepository.findAll();
        assertThat(masternodesOnlineSupplementPublicList).hasSize(databaseSizeBeforeDelete - 1);

        // Validate the MasternodesOnlineSupplementPublic in Elasticsearch
        verify(mockMasternodesOnlineSupplementPublicSearchRepository, times(1)).deleteById(masternodesOnlineSupplementPublic.getId());
    }

    @Test
    public void searchMasternodesOnlineSupplementPublic() throws Exception {
        // Initialize the database
        masternodesOnlineSupplementPublicRepository.save(masternodesOnlineSupplementPublic);
        when(mockMasternodesOnlineSupplementPublicSearchRepository.search(queryStringQuery("id:" + masternodesOnlineSupplementPublic.getId()), PageRequest.of(0, 20)))
            .thenReturn(new PageImpl<>(Collections.singletonList(masternodesOnlineSupplementPublic), PageRequest.of(0, 1), 1));
        // Search the masternodesOnlineSupplementPublic
        restMasternodesOnlineSupplementPublicMockMvc.perform(get("/api/_search/masternodes-online-supplement-publics?query=id:" + masternodesOnlineSupplementPublic.getId()))
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
        TestUtil.equalsVerifier(MasternodesOnlineSupplementPublic.class);
        MasternodesOnlineSupplementPublic masternodesOnlineSupplementPublic1 = new MasternodesOnlineSupplementPublic();
        masternodesOnlineSupplementPublic1.setId("id1");
        MasternodesOnlineSupplementPublic masternodesOnlineSupplementPublic2 = new MasternodesOnlineSupplementPublic();
        masternodesOnlineSupplementPublic2.setId(masternodesOnlineSupplementPublic1.getId());
        assertThat(masternodesOnlineSupplementPublic1).isEqualTo(masternodesOnlineSupplementPublic2);
        masternodesOnlineSupplementPublic2.setId("id2");
        assertThat(masternodesOnlineSupplementPublic1).isNotEqualTo(masternodesOnlineSupplementPublic2);
        masternodesOnlineSupplementPublic1.setId(null);
        assertThat(masternodesOnlineSupplementPublic1).isNotEqualTo(masternodesOnlineSupplementPublic2);
    }

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(MasternodesOnlineSupplementPublicDTO.class);
        MasternodesOnlineSupplementPublicDTO masternodesOnlineSupplementPublicDTO1 = new MasternodesOnlineSupplementPublicDTO();
        masternodesOnlineSupplementPublicDTO1.setId("id1");
        MasternodesOnlineSupplementPublicDTO masternodesOnlineSupplementPublicDTO2 = new MasternodesOnlineSupplementPublicDTO();
        assertThat(masternodesOnlineSupplementPublicDTO1).isNotEqualTo(masternodesOnlineSupplementPublicDTO2);
        masternodesOnlineSupplementPublicDTO2.setId(masternodesOnlineSupplementPublicDTO1.getId());
        assertThat(masternodesOnlineSupplementPublicDTO1).isEqualTo(masternodesOnlineSupplementPublicDTO2);
        masternodesOnlineSupplementPublicDTO2.setId("id2");
        assertThat(masternodesOnlineSupplementPublicDTO1).isNotEqualTo(masternodesOnlineSupplementPublicDTO2);
        masternodesOnlineSupplementPublicDTO1.setId(null);
        assertThat(masternodesOnlineSupplementPublicDTO1).isNotEqualTo(masternodesOnlineSupplementPublicDTO2);
    }
}
