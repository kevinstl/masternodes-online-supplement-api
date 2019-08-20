package com.cryptocurrencyservices.masternodessuplement.api.service.mapper;

import com.cryptocurrencyservices.masternodessuplement.api.domain.*;
import com.cryptocurrencyservices.masternodessuplement.api.service.dto.MasternodesOnlineSupplementPublicDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link MasternodesOnlineSupplementPublic} and its DTO {@link MasternodesOnlineSupplementPublicDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface MasternodesOnlineSupplementPublicMapper extends EntityMapper<MasternodesOnlineSupplementPublicDTO, MasternodesOnlineSupplementPublic> {



    default MasternodesOnlineSupplementPublic fromId(String id) {
        if (id == null) {
            return null;
        }
        MasternodesOnlineSupplementPublic masternodesOnlineSupplementPublic = new MasternodesOnlineSupplementPublic();
        masternodesOnlineSupplementPublic.setId(id);
        return masternodesOnlineSupplementPublic;
    }
}
