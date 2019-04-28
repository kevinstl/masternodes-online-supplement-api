package com.cryptocurrencyservices.masternodessuplement.api.repository;

import com.cryptocurrencyservices.masternodessuplement.api.domain.MasternodesOnlineSupplement;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;


/**
 * Spring Data MongoDB repository for the MasternodesOnlineSupplement entity.
 */
@SuppressWarnings("unused")
@Repository
public interface MasternodesOnlineSupplementRepository extends MongoRepository<MasternodesOnlineSupplement, String> {

}
