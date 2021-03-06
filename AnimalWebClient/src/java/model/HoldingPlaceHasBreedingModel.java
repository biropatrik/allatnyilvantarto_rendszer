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
public class HoldingPlaceHasBreedingModel {
    private Integer id;
    private int holdingPlaceId;
    private int breedingId;

    public HoldingPlaceHasBreedingModel() {
    }

    public HoldingPlaceHasBreedingModel(Integer id, int holdingPlaceId, int breedingId) {
        this.id = id;
        this.holdingPlaceId = holdingPlaceId;
        this.breedingId = breedingId;
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

    public int getBreedingId() {
        return breedingId;
    }

    public void setBreedingId(int breedingId) {
        this.breedingId = breedingId;
    }
}
