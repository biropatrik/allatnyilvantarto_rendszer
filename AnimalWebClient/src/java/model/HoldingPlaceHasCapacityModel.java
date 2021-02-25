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
public class HoldingPlaceHasCapacityModel {
    private Integer id;
    private int holdingPlaceId;
    private int capacityType;
    private int size;
    private long startDate;

    public HoldingPlaceHasCapacityModel() {
    }

    public HoldingPlaceHasCapacityModel(Integer id, int holdingPlaceId, int capacityType, int size, long startDate) {
        this.id = id;
        this.holdingPlaceId = holdingPlaceId;
        this.capacityType = capacityType;
        this.size = size;
        this.startDate = startDate;
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

    public int getCapacityType() {
        return capacityType;
    }

    public void setCapacityType(int capacityType) {
        this.capacityType = capacityType;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public long getStartDate() {
        return startDate;
    }

    public void setStartDate(long startDate) {
        this.startDate = startDate;
    }
    
    
}
