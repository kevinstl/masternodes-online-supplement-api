package com.cryptocurrencyservices.masternodessuplement.api.service;

import com.cryptocurrencyservices.masternodessuplement.api.domain.MasternodesOnlineSupplement;
import com.cryptocurrencyservices.masternodessuplement.api.repository.MasternodesOnlineSupplementRepository;
import com.cryptocurrencyservices.masternodessuplement.api.repository.search.MasternodesOnlineSupplementSearchRepository;
import com.cryptocurrencyservices.masternodessuplement.api.service.dto.MasternodesOnlineSupplementDTO;
import com.cryptocurrencyservices.masternodessuplement.api.service.mapper.MasternodesOnlineSupplementMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static org.elasticsearch.index.query.QueryBuilders.*;

/**
 * Service Implementation for managing MasternodesOnlineSupplement.
 */
@Service
public class MasternodesOnlineSupplementService {

    private final Logger log = LoggerFactory.getLogger(MasternodesOnlineSupplementService.class);

    private final MasternodesOnlineSupplementRepository masternodesOnlineSupplementRepository;

    private final MasternodesOnlineSupplementMapper masternodesOnlineSupplementMapper;

    private final MasternodesOnlineSupplementSearchRepository masternodesOnlineSupplementSearchRepository;

    public MasternodesOnlineSupplementService(MasternodesOnlineSupplementRepository masternodesOnlineSupplementRepository, MasternodesOnlineSupplementMapper masternodesOnlineSupplementMapper, MasternodesOnlineSupplementSearchRepository masternodesOnlineSupplementSearchRepository) {
        this.masternodesOnlineSupplementRepository = masternodesOnlineSupplementRepository;
        this.masternodesOnlineSupplementMapper = masternodesOnlineSupplementMapper;
        this.masternodesOnlineSupplementSearchRepository = masternodesOnlineSupplementSearchRepository;
    }

    /**
     * Save a masternodesOnlineSupplement.
     *
     * @param masternodesOnlineSupplementDTO the entity to save
     * @return the persisted entity
     */
    public MasternodesOnlineSupplementDTO save(MasternodesOnlineSupplementDTO masternodesOnlineSupplementDTO) {
        log.debug("Request to save MasternodesOnlineSupplement : {}", masternodesOnlineSupplementDTO);
        MasternodesOnlineSupplement masternodesOnlineSupplement = masternodesOnlineSupplementMapper.toEntity(masternodesOnlineSupplementDTO);
        masternodesOnlineSupplement = masternodesOnlineSupplementRepository.save(masternodesOnlineSupplement);
        MasternodesOnlineSupplementDTO result = masternodesOnlineSupplementMapper.toDto(masternodesOnlineSupplement);
        masternodesOnlineSupplementSearchRepository.save(masternodesOnlineSupplement);
        return result;
    }

    /**
     * Get all the masternodesOnlineSupplements.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    public Page<MasternodesOnlineSupplementDTO> findAll(Pageable pageable) {
        log.debug("Request to get all MasternodesOnlineSupplements");
        return masternodesOnlineSupplementRepository.findAll(pageable)
            .map(masternodesOnlineSupplementMapper::toDto);
    }


    /**
     * Get one masternodesOnlineSupplement by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    public Optional<MasternodesOnlineSupplementDTO> findOne(String id) {
        log.debug("Request to get MasternodesOnlineSupplement : {}", id);
        return masternodesOnlineSupplementRepository.findById(id)
            .map(masternodesOnlineSupplementMapper::toDto);
    }

    /**
     * Delete the masternodesOnlineSupplement by id.
     *
     * @param id the id of the entity
     */
    public void delete(String id) {
        log.debug("Request to delete MasternodesOnlineSupplement : {}", id);
        masternodesOnlineSupplementRepository.deleteById(id);
        masternodesOnlineSupplementSearchRepository.deleteById(id);
    }

    /**
     * Search for the masternodesOnlineSupplement corresponding to the query.
     *
     * @param query the query of the search
     * @param pageable the pagination information
     * @return the list of entities
     */
    public Page<MasternodesOnlineSupplementDTO> search(String query, Pageable pageable) {
        log.debug("Request to search for a page of MasternodesOnlineSupplements for query {}", query);
        return masternodesOnlineSupplementSearchRepository.search(queryStringQuery(query), pageable)
            .map(masternodesOnlineSupplementMapper::toDto);
    }
}
