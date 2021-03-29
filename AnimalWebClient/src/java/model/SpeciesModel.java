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
public class SpeciesModel {
    private Integer id;
    private String name;
    private Integer durationOfPregnancy;

    public SpeciesModel() {
    }

    public SpeciesModel(Integer id, String name, Integer durationOfPregnancy) {
        this.id = id;
        this.name = name;
        this.durationOfPregnancy = durationOfPregnancy;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getDurationOfPregnancy() {
        return durationOfPregnancy;
    }

    public void setDurationOfPregnancy(Integer durationOfPregnancy) {
        this.durationOfPregnancy = durationOfPregnancy;
    }
}
