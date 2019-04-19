package com.cryptocurrencyservices.masternodessuplement.api.web.rest;

import com.cryptocurrencyservices.masternodessuplement.api.MasternodesOnlineSupplementApiApp;

import com.cryptocurrencyservices.masternodessuplement.api.domain.MasternodesOnlineSupplementPublic;
import com.cryptocurrencyservices.masternodessuplement.api.repository.MasternodesOnlineSupplementPublicRepository;
import com.cryptocurrencyservices.masternodessuplement.api.repository.search.MasternodesOnlineSupplementPublicSearchRepository;
import com.cryptocurrencyservices.masternodessuplement.api.service.MasternodesOnlineSupplementPublicService;
import com.cryptocurrencyservices.masternodessuplement.api.service.dto.MasternodesOnlineSupplementPublicDTO;
import com.cryptocurrencyservices.masternodessuplement.api.service.mapper.MasternodesOnlineSupplementPublicMapper;
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
 * Test class for the MasternodesOnlineSupplementPublicResource REST controller.
 *
 * @see MasternodesOnlineSupplementPublicResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = MasternodesOnlineSupplementApiApp.class)
public class MasternodesOnlineSupplementPublicResourceIntTest {

    private static final String DEFAULT_TEST = "AAAAAAAAAA";
    private static final String UPDATED_TEST = "BBBBBBBBBB";

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

    @Before
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
            .test(DEFAULT_TEST);
        return masternodesOnlineSupplementPublic;
    }

    @Before
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
        assertThat(testMasternodesOnlineSupplementPublic.getTest()).isEqualTo(DEFAULT_TEST);

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
            .andExpect(jsonPath("$.[*].test").value(hasItem(DEFAULT_TEST.toString())));
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
            .andExpect(jsonPath("$.test").value(DEFAULT_TEST.toString()));
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
            .test(UPDATED_TEST);
        MasternodesOnlineSupplementPublicDTO masternodesOnlineSupplementPublicDTO = masternodesOnlineSupplementPublicMapper.toDto(updatedMasternodesOnlineSupplementPublic);

        restMasternodesOnlineSupplementPublicMockMvc.perform(put("/api/masternodes-online-supplement-publics")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(masternodesOnlineSupplementPublicDTO)))
            .andExpect(status().isOk());

        // Validate the MasternodesOnlineSupplementPublic in the database
        List<MasternodesOnlineSupplementPublic> masternodesOnlineSupplementPublicList = masternodesOnlineSupplementPublicRepository.findAll();
        assertThat(masternodesOnlineSupplementPublicList).hasSize(databaseSizeBeforeUpdate);
        MasternodesOnlineSupplementPublic testMasternodesOnlineSupplementPublic = masternodesOnlineSupplementPublicList.get(masternodesOnlineSupplementPublicList.size() - 1);
        assertThat(testMasternodesOnlineSupplementPublic.getTest()).isEqualTo(UPDATED_TEST);

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
            .andExpect(status().isOk());

        // Validate the database is empty
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
            .andExpect(jsonPath("$.[*].test").value(hasItem(DEFAULT_TEST)));
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
