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
public class BreedingModel {
    private Integer id;
    private int breedingType;
    private int breedingQualification;
    private boolean isActive;

    public BreedingModel() {
    }

    public BreedingModel(Integer id, int breedingType, int breedingQualification, boolean isActive) {
        this.id = id;
        this.breedingType = breedingType;
        this.breedingQualification = breedingQualification;
        this.isActive = isActive;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int getBreedingType() {
        return breedingType;
    }

    public void setBreedingType(int breedingType) {
        this.breedingType = breedingType;
    }

    public int getBreedingQualification() {
        return breedingQualification;
    }

    public void setBreedingQualification(int breedingQualification) {
        this.breedingQualification = breedingQualification;
    }

    public boolean isIsActive() {
        return isActive;
    }

    public void setIsActive(boolean isActive) {
        this.isActive = isActive;
    }
}
