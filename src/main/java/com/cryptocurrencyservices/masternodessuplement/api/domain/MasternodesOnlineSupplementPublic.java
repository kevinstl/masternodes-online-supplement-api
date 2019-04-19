package com.cryptocurrencyservices.masternodessuplement.api.domain;


import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DBRef;

import java.io.Serializable;
import java.util.Objects;

/**
 * A MasternodesOnlineSupplementPublic.
 */
@Document(collection = "masternodes_online_supplement_public")
@org.springframework.data.elasticsearch.annotations.Document(indexName = "masternodesonlinesupplementpublic")
public class MasternodesOnlineSupplementPublic implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @Id
    private String id;

    @Field("test")
    private String test;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTest() {
        return test;
    }

    public MasternodesOnlineSupplementPublic test(String test) {
        this.test = test;
        return this;
    }

    public void setTest(String test) {
        this.test = test;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        MasternodesOnlineSupplementPublic masternodesOnlineSupplementPublic = (MasternodesOnlineSupplementPublic) o;
        if (masternodesOnlineSupplementPublic.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), masternodesOnlineSupplementPublic.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "MasternodesOnlineSupplementPublic{" +
            "id=" + getId() +
            ", test='" + getTest() + "'" +
            "}";
    }
}
