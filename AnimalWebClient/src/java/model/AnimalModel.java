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
    private boolean isAccepted;
    private long inseminationDate;
    
    public AnimalModel(){
        
    }

    public AnimalModel(String earTag, String motherId, String name, boolean sex, long birthdate, long deathdate, int speciesId, int breedId, int colorId, boolean isAccepted, long inseminationDate) {
        this.earTag = earTag;
        this.motherId = motherId;
        this.name = name;
        this.sex = sex;
        this.birthdate = birthdate;
        this.deathdate = deathdate;
        this.speciesId = speciesId;
        this.breedId = breedId;
        this.colorId = colorId;
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
    
    
}
