package com.cryptocurrencyservices.masternodessuplement.api.service.mapper;

import com.cryptocurrencyservices.masternodessuplement.api.domain.*;
import com.cryptocurrencyservices.masternodessuplement.api.service.dto.MasternodesOnlineSupplementDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity MasternodesOnlineSupplement and its DTO MasternodesOnlineSupplementDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface MasternodesOnlineSupplementMapper extends EntityMapper<MasternodesOnlineSupplementDTO, MasternodesOnlineSupplement> {



    default MasternodesOnlineSupplement fromId(String id) {
        if (id == null) {
            return null;
        }
        MasternodesOnlineSupplement masternodesOnlineSupplement = new MasternodesOnlineSupplement();
        masternodesOnlineSupplement.setId(id);
        return masternodesOnlineSupplement;
    }
}
