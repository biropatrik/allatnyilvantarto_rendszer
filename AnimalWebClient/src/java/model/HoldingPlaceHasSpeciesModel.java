/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author Patrik
 */
public class HoldingPlaceHasSpeciesModel {
    private Integer id;
    private int holdingPlaceId;
    private int speciesId;
    private long startDate;
    private String utilization;

    public HoldingPlaceHasSpeciesModel() {
    }

    public HoldingPlaceHasSpeciesModel(Integer id, int holdingPlaceId, int speciesId, long startDate, String utilization) {
        this.id = id;
        this.holdingPlaceId = holdingPlaceId;
        this.speciesId = speciesId;
        this.startDate = startDate;
        this.utilization = utilization;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int getHoldingPlaceId() {
        return holdingPlaceId;
    }

    public void setHoldingPlaceId(int holdingPlaceId) {
        this.holdingPlaceId = holdingPlaceId;
    }

    public int getSpeciesId() {
        return speciesId;
    }

    public void setSpeciesId(int speciesId) {
        this.speciesId = speciesId;
    }

    public long getStartDate() {
        return startDate;
    }

    public void setStartDate(long startDate) {
        this.startDate = startDate;
    }

    public String getUtilization() {
        return utilization;
    }

    public void setUtilization(String utilization) {
        this.utilization = utilization;
    }
}
