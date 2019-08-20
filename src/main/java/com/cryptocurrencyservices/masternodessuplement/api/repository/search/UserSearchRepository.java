package com.cryptocurrencyservices.masternodessuplement.api.repository.search;

import com.cryptocurrencyservices.masternodessuplement.api.domain.User;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * Spring Data Elasticsearch repository for the User entity.
 */
public interface UserSearchRepository extends ElasticsearchRepository<User, String> {
}
