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
public class BreedingHasAnimalModel {
    private Integer id;
    private int breedingId;
    private String animalEarTag;
    private long startDate;
    private long endDate;

    public BreedingHasAnimalModel() {
    }

    public BreedingHasAnimalModel(Integer id, int breedingId, String animalEarTag, long startDate, long endDate) {
        this.id = id;
        this.breedingId = breedingId;
        this.animalEarTag = animalEarTag;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int getBreedingId() {
        return breedingId;
    }

    public void setBreedingId(int breedingId) {
        this.breedingId = breedingId;
    }

    public String getAnimalEarTag() {
        return animalEarTag;
    }

    public void setAnimalEarTag(String animalEarTag) {
        this.animalEarTag = animalEarTag;
    }

    public long getStartDate() {
        return startDate;
    }

    public void setStartDate(long startDate) {
        this.startDate = startDate;
    }

    public long getEndDate() {
        return endDate;
    }

    public void setEndDate(long endDate) {
        this.endDate = endDate;
    }
    
    public String getStartDateString() {
        if(startDate == 0){
            return ("");
        }
        
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        Date correctDate = new Date(startDate);
        return formatter.format(correctDate);
    }

    public void setStartDateString(String dateString) {
        
        if(dateString != null){
            long milliseconds = 0;
            SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd");
            try {
                Date d = f.parse(dateString);
                milliseconds = d.getTime();
            } catch (ParseException e) {
                e.printStackTrace();
            }
            this.startDate = milliseconds;
        }
    }
    
    public String getEndDateString() {
        if(endDate == 0){
            return ("");
        }
        
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        Date correctDate = new Date(endDate);
        return formatter.format(correctDate);
    }

    public void setEndDateString(String dateString) {
        
        if(dateString != null){
            long milliseconds = 0;
            SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd");
            try {
                Date d = f.parse(dateString);
                milliseconds = d.getTime();
            } catch (ParseException e) {
                e.printStackTrace();
            }
            this.endDate = milliseconds;
        }
    }
}
