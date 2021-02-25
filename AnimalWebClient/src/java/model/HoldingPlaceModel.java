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
public class HoldingPlaceModel {
    private Integer id;
    private String countryIso2;
    private int countyId;
    private int cityId;
    private String street;
    private Integer breedingType;
    private Integer userVetId;
    private boolean isActive;

    public HoldingPlaceModel() {
    }

    public HoldingPlaceModel(Integer id, String countryIso2, int countyId, int cityId, String street, Integer breedingType, Integer userVetId, boolean isActive) {
        this.id = id;
        this.countryIso2 = countryIso2;
        this.countyId = countyId;
        this.cityId = cityId;
        this.street = street;
        this.breedingType = breedingType;
        this.userVetId = userVetId;
        this.isActive = isActive;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCountryIso2() {
        return countryIso2;
    }

    public void setCountryIso2(String countryIso2) {
        this.countryIso2 = countryIso2;
    }

    public int getCountyId() {
        return countyId;
    }

    public void setCountyId(int countyId) {
        this.countyId = countyId;
    }

    public int getCityId() {
        return cityId;
    }

    public void setCityId(int cityId) {
        this.cityId = cityId;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public Integer getBreedingType() {
        return breedingType;
    }

    public void setBreedingType(Integer breedingType) {
        this.breedingType = breedingType;
    }

    public Integer getUserVetId() {
        return userVetId;
    }

    public void setUserVetId(Integer userVetId) {
        this.userVetId = userVetId;
    }

    public boolean isIsActive() {
        return isActive;
    }

    public void setIsActive(boolean isActive) {
        this.isActive = isActive;
    }
}
