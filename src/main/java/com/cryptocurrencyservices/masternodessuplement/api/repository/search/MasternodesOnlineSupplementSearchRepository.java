package com.cryptocurrencyservices.masternodessuplement.api.repository.search;

import com.cryptocurrencyservices.masternodessuplement.api.domain.MasternodesOnlineSupplement;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * Spring Data Elasticsearch repository for the {@link MasternodesOnlineSupplement} entity.
 */
public interface MasternodesOnlineSupplementSearchRepository extends ElasticsearchRepository<MasternodesOnlineSupplement, String> {
}
