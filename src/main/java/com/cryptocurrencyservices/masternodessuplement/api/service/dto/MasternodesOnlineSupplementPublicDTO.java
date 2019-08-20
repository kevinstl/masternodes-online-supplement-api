package com.cryptocurrencyservices.masternodessuplement.api.service.dto;
import java.time.Instant;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link com.cryptocurrencyservices.masternodessuplement.api.domain.MasternodesOnlineSupplementPublic} entity.
 */
public class MasternodesOnlineSupplementPublicDTO implements Serializable {

    private String id;

    private String coin;

    private String price;

    private String change;

    private String volume;

    private String marketcap;

    private String roi;

    private String nodes;

    private String numberRequired;

    private String minimumWorth;

    private String projectOrigin;

    private String masternodesOnlineUrl;

    private String githubUrl;

    private Integer githubCommits;

    private Instant createdAt;

    private Instant pushedAt;

    private String notes;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCoin() {
        return coin;
    }

    public void setCoin(String coin) {
        this.coin = coin;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getChange() {
        return change;
    }

    public void setChange(String change) {
        this.change = change;
    }

    public String getVolume() {
        return volume;
    }

    public void setVolume(String volume) {
        this.volume = volume;
    }

    public String getMarketcap() {
        return marketcap;
    }

    public void setMarketcap(String marketcap) {
        this.marketcap = marketcap;
    }

    public String getRoi() {
        return roi;
    }

    public void setRoi(String roi) {
        this.roi = roi;
    }

    public String getNodes() {
        return nodes;
    }

    public void setNodes(String nodes) {
        this.nodes = nodes;
    }

    public String getNumberRequired() {
        return numberRequired;
    }

    public void setNumberRequired(String numberRequired) {
        this.numberRequired = numberRequired;
    }

    public String getMinimumWorth() {
        return minimumWorth;
    }

    public void setMinimumWorth(String minimumWorth) {
        this.minimumWorth = minimumWorth;
    }

    public String getProjectOrigin() {
        return projectOrigin;
    }

    public void setProjectOrigin(String projectOrigin) {
        this.projectOrigin = projectOrigin;
    }

    public String getMasternodesOnlineUrl() {
        return masternodesOnlineUrl;
    }

    public void setMasternodesOnlineUrl(String masternodesOnlineUrl) {
        this.masternodesOnlineUrl = masternodesOnlineUrl;
    }

    public String getGithubUrl() {
        return githubUrl;
    }

    public void setGithubUrl(String githubUrl) {
        this.githubUrl = githubUrl;
    }

    public Integer getGithubCommits() {
        return githubCommits;
    }

    public void setGithubCommits(Integer githubCommits) {
        this.githubCommits = githubCommits;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Instant createdAt) {
        this.createdAt = createdAt;
    }

    public Instant getPushedAt() {
        return pushedAt;
    }

    public void setPushedAt(Instant pushedAt) {
        this.pushedAt = pushedAt;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
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
