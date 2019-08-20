package com.cryptocurrencyservices.masternodessuplement.api.repository.search;

import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Configuration;

/**
 * Configure a Mock version of {@link MasternodesOnlineSupplementPublicSearchRepository} to test the
 * application without starting Elasticsearch.
 */
@Configuration
public class MasternodesOnlineSupplementPublicSearchRepositoryMockConfiguration {

    @MockBean
    private MasternodesOnlineSupplementPublicSearchRepository mockMasternodesOnlineSupplementPublicSearchRepository;

}
