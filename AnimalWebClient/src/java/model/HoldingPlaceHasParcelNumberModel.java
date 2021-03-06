/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import javax.faces.bean.RequestScoped;

/**
 *
 * @author Patrik
 */
@RequestScoped
public class HoldingPlaceHasParcelNumberModel {
    private Integer id;
    private int holdingPlaceId;
    private int cityId;
    private String parcelNumber;

    public HoldingPlaceHasParcelNumberModel() {
    }

    public HoldingPlaceHasParcelNumberModel(Integer id, int holdingPlaceId, int cityId, String parcelNumber) {
        this.id = id;
        this.holdingPlaceId = holdingPlaceId;
        this.cityId = cityId;
        this.parcelNumber = parcelNumber;
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

    public int getCityId() {
        return cityId;
    }

    public void setCityId(int cityId) {
        this.cityId = cityId;
    }

    public String getParcelNumber() {
        return parcelNumber;
    }

    public void setParcelNumber(String parcelNumber) {
        this.parcelNumber = parcelNumber;
    }
}
