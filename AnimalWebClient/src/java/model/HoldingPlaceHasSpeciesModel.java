/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

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

    public String getStartDateString() {
        if(startDate == 0){
            return ("");
        }
        
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        Date correctDate = new Date(startDate);
        return formatter.format(correctDate);
    }

    public void setStartDateString(String startDateString) {
        
        if(startDateString != null){
            long milliseconds = 0;
            SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd");
            try {
                Date d = f.parse(startDateString);
                milliseconds = d.getTime();
            } catch (ParseException e) {
                e.printStackTrace();
            }
            this.startDate = milliseconds;
        }
    }

    public String getUtilization() {
        return utilization;
    }

    public void setUtilization(String utilization) {
        this.utilization = utilization;
    }
}
