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
public class AnimalHasDiseasesModel {
    private Integer id;
    private String animalEarTag;
    private int animalDiseasesId;
    private long startDate;
    private long endDate;
    private String comment;

    public AnimalHasDiseasesModel() {
    }

    public AnimalHasDiseasesModel(Integer id, String animalEarTag, int animalDiseasesId, long startDate, long endDate, String comment) {
        this.id = id;
        this.animalEarTag = animalEarTag;
        this.animalDiseasesId = animalDiseasesId;
        this.startDate = startDate;
        this.endDate = endDate;
        this.comment = comment;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAnimalEarTag() {
        return animalEarTag;
    }

    public void setAnimalEarTag(String animalEarTag) {
        this.animalEarTag = animalEarTag;
    }

    public int getAnimalDiseasesId() {
        return animalDiseasesId;
    }

    public void setAnimalDiseasesId(int animalDiseasesId) {
        this.animalDiseasesId = animalDiseasesId;
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

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
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
