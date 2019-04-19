package com.cryptocurrencyservices.masternodessuplement.api.service.dto;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the MasternodesOnlineSupplementPublic entity.
 */
public class MasternodesOnlineSupplementPublicDTO implements Serializable {

    private String id;

    private String test;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTest() {
        return test;
    }

    public void setTest(String test) {
        this.test = test;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        MasternodesOnlineSupplementPublicDTO masternodesOnlineSupplementPublicDTO = (MasternodesOnlineSupplementPublicDTO) o;
        if (masternodesOnlineSupplementPublicDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), masternodesOnlineSupplementPublicDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "MasternodesOnlineSupplementPublicDTO{" +
            "id=" + getId() +
            ", test='" + getTest() + "'" +
            "}";
    }
}
