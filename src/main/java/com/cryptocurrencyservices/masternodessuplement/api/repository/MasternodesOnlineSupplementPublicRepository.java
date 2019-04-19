package com.cryptocurrencyservices.masternodessuplement.api.repository;

import com.cryptocurrencyservices.masternodessuplement.api.domain.MasternodesOnlineSupplementPublic;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;


/**
 * Spring Data MongoDB repository for the MasternodesOnlineSupplementPublic entity.
 */
@SuppressWarnings("unused")
@Repository
public interface MasternodesOnlineSupplementPublicRepository extends MongoRepository<MasternodesOnlineSupplementPublic, String> {

}
