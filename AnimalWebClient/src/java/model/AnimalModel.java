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
public class AnimalModel {
    private String earTag;
    private String motherId;
    private String name;
    private boolean sex;
    private long birthdate;
    private long deathdate;
    private int speciesId;
    private int breedId;
    private int colorId;
    private boolean twinning;
    private int calvingId;
    private int calvingWeight;
    private boolean isAccepted;
    private long inseminationDate;
    
    public AnimalModel(){
        
    }

    public AnimalModel(String earTag, String motherId, String name, boolean sex, long birthdate, long deathdate, int speciesId, int breedId, int colorId, boolean twinning, int calvingId, int calvingWeight, boolean isAccepted, long inseminationDate) {
        this.earTag = earTag;
        this.motherId = motherId;
        this.name = name;
        this.sex = sex;
        this.birthdate = birthdate;
        this.deathdate = deathdate;
        this.speciesId = speciesId;
        this.breedId = breedId;
        this.colorId = colorId;
        this.twinning = twinning;
        this.calvingId = calvingId;
        this.calvingWeight = calvingWeight;
        this.isAccepted = isAccepted;
        this.inseminationDate = inseminationDate;
    }

    

    public String getEarTag() {
        return earTag;
    }

    public void setEarTag(String earTag) {
        this.earTag = earTag;
    }

    public String getMotherId() {
        return motherId;
    }

    public void setMotherId(String motherId) {
        this.motherId = motherId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isSex() {
        return sex;
    }

    public void setSex(boolean sex) {
        this.sex = sex;
    }

    public long getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(long birthdate) {
        this.birthdate = birthdate;
    }

    public long getDeathdate() {
        return deathdate;
    }

    public void setDeathdate(long deathdate) {
        this.deathdate = deathdate;
    }

    public int getSpeciesId() {
        return speciesId;
    }

    public void setSpeciesId(int speciesId) {
        this.speciesId = speciesId;
    }

    public int getBreedId() {
        return breedId;
    }

    public void setBreedId(int breedId) {
        this.breedId = breedId;
    }

    public int getColorId() {
        return colorId;
    }

    public void setColorId(int colorId) {
        this.colorId = colorId;
    }

    public boolean isTwinning() {
        return twinning;
    }

    public void setTwinning(boolean twinning) {
        this.twinning = twinning;
    }

    public int getCalvingId() {
        return calvingId;
    }

    public void setCalvingId(int calvingId) {
        this.calvingId = calvingId;
    }

    public int getCalvingWeight() {
        return calvingWeight;
    }

    public void setCalvingWeight(int calvingWeight) {
        this.calvingWeight = calvingWeight;
    }
    
    

    public boolean isIsAccepted() {
        return isAccepted;
    }

    public void setIsAccepted(boolean isAccepted) {
        this.isAccepted = isAccepted;
    }

    public long getInseminationDate() {
        return inseminationDate;
    }

    public void setInseminationDate(long inseminationDate) {
        this.inseminationDate = inseminationDate;
    }
    
    public String getBirthdateString() {
        if(birthdate == 0){
            return ("");
        }
        
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        Date correctDate = new Date(birthdate);
        return formatter.format(correctDate);
    }

    public void setBirthdateString(String dateString) {
        
        if(dateString != null){
            long milliseconds = 0;
            SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd");
            try {
                Date d = f.parse(dateString);
                milliseconds = d.getTime();
            } catch (ParseException e) {
                e.printStackTrace();
            }
            this.birthdate = milliseconds;
        }
    }
    
    public String getDeathdateString() {
        if(deathdate == 0){
            return ("");
        }
        
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        Date correctDate = new Date(deathdate);
        return formatter.format(correctDate);
    }

    public void setDeathdateString(String dateString) {
        
        if(dateString != null){
            long milliseconds = 0;
            SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd");
            try {
                Date d = f.parse(dateString);
                milliseconds = d.getTime();
            } catch (ParseException e) {
                e.printStackTrace();
            }
            this.deathdate = milliseconds;
        }
    }
    
    public String getInseminationdateString() {
        if(inseminationDate == 0){
            return ("");
        }
        
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        Date correctDate = new Date(inseminationDate);
        return formatter.format(correctDate);
    }

    public void setInseminationdateString(String dateString) {
        
        if(dateString != null){
            long milliseconds = 0;
            SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd");
            try {
                Date d = f.parse(dateString);
                milliseconds = d.getTime();
            } catch (ParseException e) {
                e.printStackTrace();
            }
            this.inseminationDate = milliseconds;
        }
    }
}
