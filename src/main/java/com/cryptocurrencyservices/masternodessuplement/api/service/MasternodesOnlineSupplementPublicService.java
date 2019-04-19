package com.cryptocurrencyservices.masternodessuplement.api.service;

import com.cryptocurrencyservices.masternodessuplement.api.domain.MasternodesOnlineSupplementPublic;
import com.cryptocurrencyservices.masternodessuplement.api.repository.MasternodesOnlineSupplementPublicRepository;
import com.cryptocurrencyservices.masternodessuplement.api.repository.search.MasternodesOnlineSupplementPublicSearchRepository;
import com.cryptocurrencyservices.masternodessuplement.api.service.dto.MasternodesOnlineSupplementPublicDTO;
import com.cryptocurrencyservices.masternodessuplement.api.service.mapper.MasternodesOnlineSupplementPublicMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static org.elasticsearch.index.query.QueryBuilders.*;

/**
 * Service Implementation for managing MasternodesOnlineSupplementPublic.
 */
@Service
public class MasternodesOnlineSupplementPublicService {

    private final Logger log = LoggerFactory.getLogger(MasternodesOnlineSupplementPublicService.class);

    private final MasternodesOnlineSupplementPublicRepository masternodesOnlineSupplementPublicRepository;

    private final MasternodesOnlineSupplementPublicMapper masternodesOnlineSupplementPublicMapper;

    private final MasternodesOnlineSupplementPublicSearchRepository masternodesOnlineSupplementPublicSearchRepository;

    public MasternodesOnlineSupplementPublicService(MasternodesOnlineSupplementPublicRepository masternodesOnlineSupplementPublicRepository, MasternodesOnlineSupplementPublicMapper masternodesOnlineSupplementPublicMapper, MasternodesOnlineSupplementPublicSearchRepository masternodesOnlineSupplementPublicSearchRepository) {
        this.masternodesOnlineSupplementPublicRepository = masternodesOnlineSupplementPublicRepository;
        this.masternodesOnlineSupplementPublicMapper = masternodesOnlineSupplementPublicMapper;
        this.masternodesOnlineSupplementPublicSearchRepository = masternodesOnlineSupplementPublicSearchRepository;
    }

    /**
     * Save a masternodesOnlineSupplementPublic.
     *
     * @param masternodesOnlineSupplementPublicDTO the entity to save
     * @return the persisted entity
     */
    public MasternodesOnlineSupplementPublicDTO save(MasternodesOnlineSupplementPublicDTO masternodesOnlineSupplementPublicDTO) {
        log.debug("Request to save MasternodesOnlineSupplementPublic : {}", masternodesOnlineSupplementPublicDTO);
        MasternodesOnlineSupplementPublic masternodesOnlineSupplementPublic = masternodesOnlineSupplementPublicMapper.toEntity(masternodesOnlineSupplementPublicDTO);
        masternodesOnlineSupplementPublic = masternodesOnlineSupplementPublicRepository.save(masternodesOnlineSupplementPublic);
        MasternodesOnlineSupplementPublicDTO result = masternodesOnlineSupplementPublicMapper.toDto(masternodesOnlineSupplementPublic);
        masternodesOnlineSupplementPublicSearchRepository.save(masternodesOnlineSupplementPublic);
        return result;
    }

    /**
     * Get all the masternodesOnlineSupplementPublics.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    public Page<MasternodesOnlineSupplementPublicDTO> findAll(Pageable pageable) {
        log.debug("Request to get all MasternodesOnlineSupplementPublics");
        return masternodesOnlineSupplementPublicRepository.findAll(pageable)
            .map(masternodesOnlineSupplementPublicMapper::toDto);
    }


    /**
     * Get one masternodesOnlineSupplementPublic by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    public Optional<MasternodesOnlineSupplementPublicDTO> findOne(String id) {
        log.debug("Request to get MasternodesOnlineSupplementPublic : {}", id);
        return masternodesOnlineSupplementPublicRepository.findById(id)
            .map(masternodesOnlineSupplementPublicMapper::toDto);
    }

    /**
     * Delete the masternodesOnlineSupplementPublic by id.
     *
     * @param id the id of the entity
     */
    public void delete(String id) {
        log.debug("Request to delete MasternodesOnlineSupplementPublic : {}", id);
        masternodesOnlineSupplementPublicRepository.deleteById(id);
        masternodesOnlineSupplementPublicSearchRepository.deleteById(id);
    }

    /**
     * Search for the masternodesOnlineSupplementPublic corresponding to the query.
     *
     * @param query the query of the search
     * @param pageable the pagination information
     * @return the list of entities
     */
    public Page<MasternodesOnlineSupplementPublicDTO> search(String query, Pageable pageable) {
        log.debug("Request to search for a page of MasternodesOnlineSupplementPublics for query {}", query);
        return masternodesOnlineSupplementPublicSearchRepository.search(queryStringQuery(query), pageable)
            .map(masternodesOnlineSupplementPublicMapper::toDto);
    }
}
