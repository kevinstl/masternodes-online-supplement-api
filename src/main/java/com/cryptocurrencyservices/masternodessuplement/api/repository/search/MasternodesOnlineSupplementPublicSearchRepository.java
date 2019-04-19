package com.cryptocurrencyservices.masternodessuplement.api.repository.search;

import com.cryptocurrencyservices.masternodessuplement.api.domain.MasternodesOnlineSupplementPublic;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * Spring Data Elasticsearch repository for the MasternodesOnlineSupplementPublic entity.
 */
public interface MasternodesOnlineSupplementPublicSearchRepository extends ElasticsearchRepository<MasternodesOnlineSupplementPublic, String> {
}
