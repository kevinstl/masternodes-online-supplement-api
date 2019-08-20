package com.cryptocurrencyservices.masternodessuplement.api.repository.search;

import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Configuration;

/**
 * Configure a Mock version of MasternodesOnlineSupplementSearchRepository to test the
 * application without starting Elasticsearch.
 */
@Configuration
public class MasternodesOnlineSupplementSearchRepositoryMockConfiguration {

    @MockBean
    private MasternodesOnlineSupplementSearchRepository mockMasternodesOnlineSupplementSearchRepository;

}
