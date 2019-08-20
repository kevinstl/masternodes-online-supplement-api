package com.cryptocurrencyservices.masternodessuplement.api.domain;


import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DBRef;

import java.io.Serializable;
import java.time.Instant;
import java.util.Objects;

/**
 * A MasternodesOnlineSupplement.
 */
@Document(collection = "masternodes_online_supplement")
@org.springframework.data.elasticsearch.annotations.Document(indexName = "masternodesonlinesupplement")
public class MasternodesOnlineSupplement implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @Id
    private String id;

    @Field("coin")
    private String coin;

    @Field("price")
    private String price;

    @Field("change")
    private String change;

    @Field("volume")
    private String volume;

    @Field("marketcap")
    private String marketcap;

    @Field("roi")
    private String roi;

    @Field("nodes")
    private String nodes;

    @Field("number_required")
    private String numberRequired;

    @Field("minimum_worth")
    private String minimumWorth;

    @Field("project_origin")
    private String projectOrigin;

    @Field("masternodes_online_url")
    private String masternodesOnlineUrl;

    @Field("github_url")
    private String githubUrl;

    @Field("github_commits")
    private Integer githubCommits;

    @Field("created_at")
    private Instant createdAt;

    @Field("pushed_at")
    private Instant pushedAt;

    @Field("notes")
    private String notes;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCoin() {
        return coin;
    }

    public MasternodesOnlineSupplement coin(String coin) {
        this.coin = coin;
        return this;
    }

    public void setCoin(String coin) {
        this.coin = coin;
    }

    public String getPrice() {
        return price;
    }

    public MasternodesOnlineSupplement price(String price) {
        this.price = price;
        return this;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getChange() {
        return change;
    }

    public MasternodesOnlineSupplement change(String change) {
        this.change = change;
        return this;
    }

    public void setChange(String change) {
        this.change = change;
    }

    public String getVolume() {
        return volume;
    }

    public MasternodesOnlineSupplement volume(String volume) {
        this.volume = volume;
        return this;
    }

    public void setVolume(String volume) {
        this.volume = volume;
    }

    public String getMarketcap() {
        return marketcap;
    }

    public MasternodesOnlineSupplement marketcap(String marketcap) {
        this.marketcap = marketcap;
        return this;
    }

    public void setMarketcap(String marketcap) {
        this.marketcap = marketcap;
    }

    public String getRoi() {
        return roi;
    }

    public MasternodesOnlineSupplement roi(String roi) {
        this.roi = roi;
        return this;
    }

    public void setRoi(String roi) {
        this.roi = roi;
    }

    public String getNodes() {
        return nodes;
    }

    public MasternodesOnlineSupplement nodes(String nodes) {
        this.nodes = nodes;
        return this;
    }

    public void setNodes(String nodes) {
        this.nodes = nodes;
    }

    public String getNumberRequired() {
        return numberRequired;
    }

    public MasternodesOnlineSupplement numberRequired(String numberRequired) {
        this.numberRequired = numberRequired;
        return this;
    }

    public void setNumberRequired(String numberRequired) {
        this.numberRequired = numberRequired;
    }

    public String getMinimumWorth() {
        return minimumWorth;
    }

    public MasternodesOnlineSupplement minimumWorth(String minimumWorth) {
        this.minimumWorth = minimumWorth;
        return this;
    }

    public void setMinimumWorth(String minimumWorth) {
        this.minimumWorth = minimumWorth;
    }

    public String getProjectOrigin() {
        return projectOrigin;
    }

    public MasternodesOnlineSupplement projectOrigin(String projectOrigin) {
        this.projectOrigin = projectOrigin;
        return this;
    }

    public void setProjectOrigin(String projectOrigin) {
        this.projectOrigin = projectOrigin;
    }

    public String getMasternodesOnlineUrl() {
        return masternodesOnlineUrl;
    }

    public MasternodesOnlineSupplement masternodesOnlineUrl(String masternodesOnlineUrl) {
        this.masternodesOnlineUrl = masternodesOnlineUrl;
        return this;
    }

    public void setMasternodesOnlineUrl(String masternodesOnlineUrl) {
        this.masternodesOnlineUrl = masternodesOnlineUrl;
    }

    public String getGithubUrl() {
        return githubUrl;
    }

    public MasternodesOnlineSupplement githubUrl(String githubUrl) {
        this.githubUrl = githubUrl;
        return this;
    }

    public void setGithubUrl(String githubUrl) {
        this.githubUrl = githubUrl;
    }

    public Integer getGithubCommits() {
        return githubCommits;
    }

    public MasternodesOnlineSupplement githubCommits(Integer githubCommits) {
        this.githubCommits = githubCommits;
        return this;
    }

    public void setGithubCommits(Integer githubCommits) {
        this.githubCommits = githubCommits;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    public MasternodesOnlineSupplement createdAt(Instant createdAt) {
        this.createdAt = createdAt;
        return this;
    }

    public void setCreatedAt(Instant createdAt) {
        this.createdAt = createdAt;
    }

    public Instant getPushedAt() {
        return pushedAt;
    }

    public MasternodesOnlineSupplement pushedAt(Instant pushedAt) {
        this.pushedAt = pushedAt;
        return this;
    }

    public void setPushedAt(Instant pushedAt) {
        this.pushedAt = pushedAt;
    }

    public String getNotes() {
        return notes;
    }

    public MasternodesOnlineSupplement notes(String notes) {
        this.notes = notes;
        return this;
    }

    public void setNotes(String notes) {
        this.notes = notes;
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
        MasternodesOnlineSupplement masternodesOnlineSupplement = (MasternodesOnlineSupplement) o;
        if (masternodesOnlineSupplement.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), masternodesOnlineSupplement.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "MasternodesOnlineSupplement{" +
            "id=" + getId() +
            ", coin='" + getCoin() + "'" +
            ", price='" + getPrice() + "'" +
            ", change='" + getChange() + "'" +
            ", volume='" + getVolume() + "'" +
            ", marketcap='" + getMarketcap() + "'" +
            ", roi='" + getRoi() + "'" +
            ", nodes='" + getNodes() + "'" +
            ", numberRequired='" + getNumberRequired() + "'" +
            ", minimumWorth='" + getMinimumWorth() + "'" +
            ", projectOrigin='" + getProjectOrigin() + "'" +
            ", masternodesOnlineUrl='" + getMasternodesOnlineUrl() + "'" +
            ", githubUrl='" + getGithubUrl() + "'" +
            ", githubCommits=" + getGithubCommits() +
            ", createdAt='" + getCreatedAt() + "'" +
            ", pushedAt='" + getPushedAt() + "'" +
            ", notes='" + getNotes() + "'" +
            "}";
    }
}
