/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.io.Serializable;
import java.math.BigInteger;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Patrik
 */
@Entity
@Table(name = "animal")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Animal.findAll", query = "SELECT a FROM Animal a"),
    @NamedQuery(name = "Animal.findByEarTag", query = "SELECT a FROM Animal a WHERE a.earTag = :earTag"),
    @NamedQuery(name = "Animal.findByMotherId", query = "SELECT a FROM Animal a WHERE a.motherId = :motherId"),
    @NamedQuery(name = "Animal.findByName", query = "SELECT a FROM Animal a WHERE a.name = :name"),
    @NamedQuery(name = "Animal.findBySex", query = "SELECT a FROM Animal a WHERE a.sex = :sex"),
    @NamedQuery(name = "Animal.findByBirthdate", query = "SELECT a FROM Animal a WHERE a.birthdate = :birthdate"),
    @NamedQuery(name = "Animal.findByDeathdate", query = "SELECT a FROM Animal a WHERE a.deathdate = :deathdate"),
    @NamedQuery(name = "Animal.findBySpeciesId", query = "SELECT a FROM Animal a WHERE a.speciesId = :speciesId"),
    @NamedQuery(name = "Animal.findByBreedId", query = "SELECT a FROM Animal a WHERE a.breedId = :breedId"),
    @NamedQuery(name = "Animal.findByColorId", query = "SELECT a FROM Animal a WHERE a.colorId = :colorId"),
    @NamedQuery(name = "Animal.findByIsAccepted", query = "SELECT a FROM Animal a WHERE a.isAccepted = :isAccepted"),
    @NamedQuery(name = "Animal.findByInseminationDate", query = "SELECT a FROM Animal a WHERE a.inseminationDate = :inseminationDate")})
public class Animal implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "ear_tag")
    private Integer earTag;
    @Column(name = "mother_id")
    private Integer motherId;
    @Size(max = 250)
    @Column(name = "name")
    private String name;
    @Basic(optional = false)
    @NotNull
    @Column(name = "sex")
    private boolean sex;
    @Basic(optional = false)
    @NotNull
    @Column(name = "birthdate")
    private long birthdate;
    @Column(name = "deathdate")
    private BigInteger deathdate;
    @Basic(optional = false)
    @NotNull
    @Column(name = "species_id")
    private int speciesId;
    @Basic(optional = false)
    @NotNull
    @Column(name = "breed_id")
    private int breedId;
    @Basic(optional = false)
    @NotNull
    @Column(name = "color_id")
    private int colorId;
    @Basic(optional = false)
    @NotNull
    @Column(name = "isAccepted")
    private boolean isAccepted;
    @Column(name = "insemination_date")
    private BigInteger inseminationDate;

    public Animal() {
    }

    public Animal(Integer earTag) {
        this.earTag = earTag;
    }

    public Animal(Integer earTag, boolean sex, long birthdate, int speciesId, int breedId, int colorId, boolean isAccepted) {
        this.earTag = earTag;
        this.sex = sex;
        this.birthdate = birthdate;
        this.speciesId = speciesId;
        this.breedId = breedId;
        this.colorId = colorId;
        this.isAccepted = isAccepted;
    }

    public Integer getEarTag() {
        return earTag;
    }

    public void setEarTag(Integer earTag) {
        this.earTag = earTag;
    }

    public Integer getMotherId() {
        return motherId;
    }

    public void setMotherId(Integer motherId) {
        this.motherId = motherId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean getSex() {
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

    public BigInteger getDeathdate() {
        return deathdate;
    }

    public void setDeathdate(BigInteger deathdate) {
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

    public boolean getIsAccepted() {
        return isAccepted;
    }

    public void setIsAccepted(boolean isAccepted) {
        this.isAccepted = isAccepted;
    }

    public BigInteger getInseminationDate() {
        return inseminationDate;
    }

    public void setInseminationDate(BigInteger inseminationDate) {
        this.inseminationDate = inseminationDate;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (earTag != null ? earTag.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Animal)) {
            return false;
        }
        Animal other = (Animal) object;
        if ((this.earTag == null && other.earTag != null) || (this.earTag != null && !this.earTag.equals(other.earTag))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.Animal[ earTag=" + earTag + " ]";
    }
    
}