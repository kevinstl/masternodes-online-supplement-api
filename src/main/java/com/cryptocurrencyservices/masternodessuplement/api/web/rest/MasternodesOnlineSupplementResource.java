package com.cryptocurrencyservices.masternodessuplement.api.web.rest;

import com.cryptocurrencyservices.masternodessuplement.api.service.MasternodesOnlineSupplementService;
import com.cryptocurrencyservices.masternodessuplement.api.web.rest.errors.BadRequestAlertException;
import com.cryptocurrencyservices.masternodessuplement.api.service.dto.MasternodesOnlineSupplementDTO;

import io.github.jhipster.web.util.HeaderUtil;
import io.github.jhipster.web.util.PaginationUtil;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;

import java.util.List;
import java.util.Optional;
import java.util.stream.StreamSupport;

import static org.elasticsearch.index.query.QueryBuilders.*;

/**
 * REST controller for managing {@link com.cryptocurrencyservices.masternodessuplement.api.domain.MasternodesOnlineSupplement}.
 */
@RestController
@RequestMapping("/api")
public class MasternodesOnlineSupplementResource {

    private final Logger log = LoggerFactory.getLogger(MasternodesOnlineSupplementResource.class);

    private static final String ENTITY_NAME = "masternodesOnlineSupplementApiMasternodesOnlineSupplement";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final MasternodesOnlineSupplementService masternodesOnlineSupplementService;

    public MasternodesOnlineSupplementResource(MasternodesOnlineSupplementService masternodesOnlineSupplementService) {
        this.masternodesOnlineSupplementService = masternodesOnlineSupplementService;
    }

    /**
     * {@code POST  /masternodes-online-supplements} : Create a new masternodesOnlineSupplement.
     *
     * @param masternodesOnlineSupplementDTO the masternodesOnlineSupplementDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new masternodesOnlineSupplementDTO, or with status {@code 400 (Bad Request)} if the masternodesOnlineSupplement has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/masternodes-online-supplements")
    public ResponseEntity<MasternodesOnlineSupplementDTO> createMasternodesOnlineSupplement(@RequestBody MasternodesOnlineSupplementDTO masternodesOnlineSupplementDTO) throws URISyntaxException {
        log.debug("REST request to save MasternodesOnlineSupplement : {}", masternodesOnlineSupplementDTO);
        if (masternodesOnlineSupplementDTO.getId() != null) {
            throw new BadRequestAlertException("A new masternodesOnlineSupplement cannot already have an ID", ENTITY_NAME, "idexists");
        }
        MasternodesOnlineSupplementDTO result = masternodesOnlineSupplementService.save(masternodesOnlineSupplementDTO);
        return ResponseEntity.created(new URI("/api/masternodes-online-supplements/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /masternodes-online-supplements} : Updates an existing masternodesOnlineSupplement.
     *
     * @param masternodesOnlineSupplementDTO the masternodesOnlineSupplementDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated masternodesOnlineSupplementDTO,
     * or with status {@code 400 (Bad Request)} if the masternodesOnlineSupplementDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the masternodesOnlineSupplementDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/masternodes-online-supplements")
    public ResponseEntity<MasternodesOnlineSupplementDTO> updateMasternodesOnlineSupplement(@RequestBody MasternodesOnlineSupplementDTO masternodesOnlineSupplementDTO) throws URISyntaxException {
        log.debug("REST request to update MasternodesOnlineSupplement : {}", masternodesOnlineSupplementDTO);
        if (masternodesOnlineSupplementDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        MasternodesOnlineSupplementDTO result = masternodesOnlineSupplementService.save(masternodesOnlineSupplementDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, masternodesOnlineSupplementDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /masternodes-online-supplements} : get all the masternodesOnlineSupplements.
     *

     * @param pageable the pagination information.

     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of masternodesOnlineSupplements in body.
     */
    @GetMapping("/masternodes-online-supplements")
    public ResponseEntity<List<MasternodesOnlineSupplementDTO>> getAllMasternodesOnlineSupplements(Pageable pageable) {
        log.debug("REST request to get a page of MasternodesOnlineSupplements");
        Page<MasternodesOnlineSupplementDTO> page = masternodesOnlineSupplementService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /masternodes-online-supplements/:id} : get the "id" masternodesOnlineSupplement.
     *
     * @param id the id of the masternodesOnlineSupplementDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the masternodesOnlineSupplementDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/masternodes-online-supplements/{id}")
    public ResponseEntity<MasternodesOnlineSupplementDTO> getMasternodesOnlineSupplement(@PathVariable String id) {
        log.debug("REST request to get MasternodesOnlineSupplement : {}", id);
        Optional<MasternodesOnlineSupplementDTO> masternodesOnlineSupplementDTO = masternodesOnlineSupplementService.findOne(id);
        return ResponseUtil.wrapOrNotFound(masternodesOnlineSupplementDTO);
    }

    /**
     * {@code DELETE  /masternodes-online-supplements/:id} : delete the "id" masternodesOnlineSupplement.
     *
     * @param id the id of the masternodesOnlineSupplementDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/masternodes-online-supplements/{id}")
    public ResponseEntity<Void> deleteMasternodesOnlineSupplement(@PathVariable String id) {
        log.debug("REST request to delete MasternodesOnlineSupplement : {}", id);
        masternodesOnlineSupplementService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id)).build();
    }

    /**
     * {@code SEARCH  /_search/masternodes-online-supplements?query=:query} : search for the masternodesOnlineSupplement corresponding
     * to the query.
     *
     * @param query the query of the masternodesOnlineSupplement search.
     * @param pageable the pagination information.
     * @return the result of the search.
     */
    @GetMapping("/_search/masternodes-online-supplements")
    public ResponseEntity<List<MasternodesOnlineSupplementDTO>> searchMasternodesOnlineSupplements(@RequestParam String query, Pageable pageable) {
        log.debug("REST request to search for a page of MasternodesOnlineSupplements for query {}", query);
        Page<MasternodesOnlineSupplementDTO> page = masternodesOnlineSupplementService.search(query, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

}
