package com.cryptocurrencyservices.masternodessuplement.api.web.rest;

import com.cryptocurrencyservices.masternodessuplement.api.MasternodesOnlineSupplementApiApp;

import com.cryptocurrencyservices.masternodessuplement.api.domain.MasternodesOnlineSupplement;
import com.cryptocurrencyservices.masternodessuplement.api.repository.MasternodesOnlineSupplementRepository;
import com.cryptocurrencyservices.masternodessuplement.api.repository.search.MasternodesOnlineSupplementSearchRepository;
import com.cryptocurrencyservices.masternodessuplement.api.service.MasternodesOnlineSupplementService;
import com.cryptocurrencyservices.masternodessuplement.api.service.dto.MasternodesOnlineSupplementDTO;
import com.cryptocurrencyservices.masternodessuplement.api.service.mapper.MasternodesOnlineSupplementMapper;
import com.cryptocurrencyservices.masternodessuplement.api.web.rest.errors.ExceptionTranslator;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.context.junit4.SpringRunner;
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
 * Test class for the MasternodesOnlineSupplementResource REST controller.
 *
 * @see MasternodesOnlineSupplementResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = MasternodesOnlineSupplementApiApp.class)
public class MasternodesOnlineSupplementResourceIntTest {

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

    private static final Instant DEFAULT_CREATED_AT = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_CREATED_AT = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final Instant DEFAULT_PUSHED_AT = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_PUSHED_AT = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final String DEFAULT_NOTES = "AAAAAAAAAA";
    private static final String UPDATED_NOTES = "BBBBBBBBBB";

    @Autowired
    private MasternodesOnlineSupplementRepository masternodesOnlineSupplementRepository;

    @Autowired
    private MasternodesOnlineSupplementMapper masternodesOnlineSupplementMapper;

    @Autowired
    private MasternodesOnlineSupplementService masternodesOnlineSupplementService;

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

    private MasternodesOnlineSupplement masternodesOnlineSupplement;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final MasternodesOnlineSupplementResource masternodesOnlineSupplementResource = new MasternodesOnlineSupplementResource(masternodesOnlineSupplementService);
        this.restMasternodesOnlineSupplementMockMvc = MockMvcBuilders.standaloneSetup(masternodesOnlineSupplementResource)
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
        MasternodesOnlineSupplement masternodesOnlineSupplement = new MasternodesOnlineSupplement()
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
        return masternodesOnlineSupplement;
    }

    @Before
    public void initTest() {
        masternodesOnlineSupplementRepository.deleteAll();
        masternodesOnlineSupplement = createEntity();
    }

    @Test
    public void createMasternodesOnlineSupplement() throws Exception {
        int databaseSizeBeforeCreate = masternodesOnlineSupplementRepository.findAll().size();

        // Create the MasternodesOnlineSupplement
        MasternodesOnlineSupplementDTO masternodesOnlineSupplementDTO = masternodesOnlineSupplementMapper.toDto(masternodesOnlineSupplement);
        restMasternodesOnlineSupplementMockMvc.perform(post("/api/masternodes-online-supplements")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(masternodesOnlineSupplementDTO)))
            .andExpect(status().isCreated());

        // Validate the MasternodesOnlineSupplement in the database
        List<MasternodesOnlineSupplement> masternodesOnlineSupplementList = masternodesOnlineSupplementRepository.findAll();
        assertThat(masternodesOnlineSupplementList).hasSize(databaseSizeBeforeCreate + 1);
        MasternodesOnlineSupplement testMasternodesOnlineSupplement = masternodesOnlineSupplementList.get(masternodesOnlineSupplementList.size() - 1);
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
        int databaseSizeBeforeCreate = masternodesOnlineSupplementRepository.findAll().size();

        // Create the MasternodesOnlineSupplement with an existing ID
        masternodesOnlineSupplement.setId("existing_id");
        MasternodesOnlineSupplementDTO masternodesOnlineSupplementDTO = masternodesOnlineSupplementMapper.toDto(masternodesOnlineSupplement);

        // An entity with an existing ID cannot be created, so this API call must fail
        restMasternodesOnlineSupplementMockMvc.perform(post("/api/masternodes-online-supplements")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(masternodesOnlineSupplementDTO)))
            .andExpect(status().isBadRequest());

        // Validate the MasternodesOnlineSupplement in the database
        List<MasternodesOnlineSupplement> masternodesOnlineSupplementList = masternodesOnlineSupplementRepository.findAll();
        assertThat(masternodesOnlineSupplementList).hasSize(databaseSizeBeforeCreate);

        // Validate the MasternodesOnlineSupplement in Elasticsearch
        verify(mockMasternodesOnlineSupplementSearchRepository, times(0)).save(masternodesOnlineSupplement);
    }

    @Test
    public void getAllMasternodesOnlineSupplements() throws Exception {
        // Initialize the database
        masternodesOnlineSupplementRepository.save(masternodesOnlineSupplement);

        // Get all the masternodesOnlineSupplementList
        restMasternodesOnlineSupplementMockMvc.perform(get("/api/masternodes-online-supplements?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(masternodesOnlineSupplement.getId())))
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
        masternodesOnlineSupplementRepository.save(masternodesOnlineSupplement);

        // Get the masternodesOnlineSupplement
        restMasternodesOnlineSupplementMockMvc.perform(get("/api/masternodes-online-supplements/{id}", masternodesOnlineSupplement.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(masternodesOnlineSupplement.getId()))
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
        // Get the masternodesOnlineSupplement
        restMasternodesOnlineSupplementMockMvc.perform(get("/api/masternodes-online-supplements/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    public void updateMasternodesOnlineSupplement() throws Exception {
        // Initialize the database
        masternodesOnlineSupplementRepository.save(masternodesOnlineSupplement);

        int databaseSizeBeforeUpdate = masternodesOnlineSupplementRepository.findAll().size();

        // Update the masternodesOnlineSupplement
        MasternodesOnlineSupplement updatedMasternodesOnlineSupplement = masternodesOnlineSupplementRepository.findById(masternodesOnlineSupplement.getId()).get();
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
        MasternodesOnlineSupplementDTO masternodesOnlineSupplementDTO = masternodesOnlineSupplementMapper.toDto(updatedMasternodesOnlineSupplement);

        restMasternodesOnlineSupplementMockMvc.perform(put("/api/masternodes-online-supplements")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(masternodesOnlineSupplementDTO)))
            .andExpect(status().isOk());

        // Validate the MasternodesOnlineSupplement in the database
        List<MasternodesOnlineSupplement> masternodesOnlineSupplementList = masternodesOnlineSupplementRepository.findAll();
        assertThat(masternodesOnlineSupplementList).hasSize(databaseSizeBeforeUpdate);
        MasternodesOnlineSupplement testMasternodesOnlineSupplement = masternodesOnlineSupplementList.get(masternodesOnlineSupplementList.size() - 1);
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
        int databaseSizeBeforeUpdate = masternodesOnlineSupplementRepository.findAll().size();

        // Create the MasternodesOnlineSupplement
        MasternodesOnlineSupplementDTO masternodesOnlineSupplementDTO = masternodesOnlineSupplementMapper.toDto(masternodesOnlineSupplement);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restMasternodesOnlineSupplementMockMvc.perform(put("/api/masternodes-online-supplements")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(masternodesOnlineSupplementDTO)))
            .andExpect(status().isBadRequest());

        // Validate the MasternodesOnlineSupplement in the database
        List<MasternodesOnlineSupplement> masternodesOnlineSupplementList = masternodesOnlineSupplementRepository.findAll();
        assertThat(masternodesOnlineSupplementList).hasSize(databaseSizeBeforeUpdate);

        // Validate the MasternodesOnlineSupplement in Elasticsearch
        verify(mockMasternodesOnlineSupplementSearchRepository, times(0)).save(masternodesOnlineSupplement);
    }

    @Test
    public void deleteMasternodesOnlineSupplement() throws Exception {
        // Initialize the database
        masternodesOnlineSupplementRepository.save(masternodesOnlineSupplement);

        int databaseSizeBeforeDelete = masternodesOnlineSupplementRepository.findAll().size();

        // Delete the masternodesOnlineSupplement
        restMasternodesOnlineSupplementMockMvc.perform(delete("/api/masternodes-online-supplements/{id}", masternodesOnlineSupplement.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<MasternodesOnlineSupplement> masternodesOnlineSupplementList = masternodesOnlineSupplementRepository.findAll();
        assertThat(masternodesOnlineSupplementList).hasSize(databaseSizeBeforeDelete - 1);

        // Validate the MasternodesOnlineSupplement in Elasticsearch
        verify(mockMasternodesOnlineSupplementSearchRepository, times(1)).deleteById(masternodesOnlineSupplement.getId());
    }

    @Test
    public void searchMasternodesOnlineSupplement() throws Exception {
        // Initialize the database
        masternodesOnlineSupplementRepository.save(masternodesOnlineSupplement);
        when(mockMasternodesOnlineSupplementSearchRepository.search(queryStringQuery("id:" + masternodesOnlineSupplement.getId()), PageRequest.of(0, 20)))
            .thenReturn(new PageImpl<>(Collections.singletonList(masternodesOnlineSupplement), PageRequest.of(0, 1), 1));
        // Search the masternodesOnlineSupplement
        restMasternodesOnlineSupplementMockMvc.perform(get("/api/_search/masternodes-online-supplements?query=id:" + masternodesOnlineSupplement.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(masternodesOnlineSupplement.getId())))
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
        MasternodesOnlineSupplement masternodesOnlineSupplement1 = new MasternodesOnlineSupplement();
        masternodesOnlineSupplement1.setId("id1");
        MasternodesOnlineSupplement masternodesOnlineSupplement2 = new MasternodesOnlineSupplement();
        masternodesOnlineSupplement2.setId(masternodesOnlineSupplement1.getId());
        assertThat(masternodesOnlineSupplement1).isEqualTo(masternodesOnlineSupplement2);
        masternodesOnlineSupplement2.setId("id2");
        assertThat(masternodesOnlineSupplement1).isNotEqualTo(masternodesOnlineSupplement2);
        masternodesOnlineSupplement1.setId(null);
        assertThat(masternodesOnlineSupplement1).isNotEqualTo(masternodesOnlineSupplement2);
    }

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(MasternodesOnlineSupplementDTO.class);
        MasternodesOnlineSupplementDTO masternodesOnlineSupplementDTO1 = new MasternodesOnlineSupplementDTO();
        masternodesOnlineSupplementDTO1.setId("id1");
        MasternodesOnlineSupplementDTO masternodesOnlineSupplementDTO2 = new MasternodesOnlineSupplementDTO();
        assertThat(masternodesOnlineSupplementDTO1).isNotEqualTo(masternodesOnlineSupplementDTO2);
        masternodesOnlineSupplementDTO2.setId(masternodesOnlineSupplementDTO1.getId());
        assertThat(masternodesOnlineSupplementDTO1).isEqualTo(masternodesOnlineSupplementDTO2);
        masternodesOnlineSupplementDTO2.setId("id2");
        assertThat(masternodesOnlineSupplementDTO1).isNotEqualTo(masternodesOnlineSupplementDTO2);
        masternodesOnlineSupplementDTO1.setId(null);
        assertThat(masternodesOnlineSupplementDTO1).isNotEqualTo(masternodesOnlineSupplementDTO2);
    }
}
