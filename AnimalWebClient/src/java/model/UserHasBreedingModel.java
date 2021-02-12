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
public class UserHasBreedingModel {
    private Integer id;
    private int userId;
    private int breedingId;

    public UserHasBreedingModel() {
    }
    
    public UserHasBreedingModel(Integer id, int userId, int breedingId) {
        this.id = id;
        this.userId = userId;
        this.breedingId = breedingId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getBreedingId() {
        return breedingId;
    }

    public void setBreedingId(int breedingId) {
        this.breedingId = breedingId;
    }
}
