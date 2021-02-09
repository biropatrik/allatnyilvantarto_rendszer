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
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Patrik
 */
@Entity
@Table(name = "breeding_has_animal")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "BreedingHasAnimal.findAll", query = "SELECT b FROM BreedingHasAnimal b"),
    @NamedQuery(name = "BreedingHasAnimal.findById", query = "SELECT b FROM BreedingHasAnimal b WHERE b.id = :id"),
    @NamedQuery(name = "BreedingHasAnimal.findByBreedingId", query = "SELECT b FROM BreedingHasAnimal b WHERE b.breedingId = :breedingId"),
    @NamedQuery(name = "BreedingHasAnimal.findByAnimalEarTag", query = "SELECT b FROM BreedingHasAnimal b WHERE b.animalEarTag = :animalEarTag"),
    @NamedQuery(name = "BreedingHasAnimal.findByStartDate", query = "SELECT b FROM BreedingHasAnimal b WHERE b.startDate = :startDate"),
    @NamedQuery(name = "BreedingHasAnimal.findByEndDate", query = "SELECT b FROM BreedingHasAnimal b WHERE b.endDate = :endDate")})
public class BreedingHasAnimal implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Column(name = "breeding_id")
    private int breedingId;
    @Basic(optional = false)
    @NotNull
    @Column(name = "animal_ear_tag")
    private int animalEarTag;
    @Basic(optional = false)
    @NotNull
    @Column(name = "start_date")
    private long startDate;
    @Column(name = "end_date")
    private BigInteger endDate;

    public BreedingHasAnimal() {
    }

    public BreedingHasAnimal(Integer id) {
        this.id = id;
    }

    public BreedingHasAnimal(Integer id, int breedingId, int animalEarTag, long startDate) {
        this.id = id;
        this.breedingId = breedingId;
        this.animalEarTag = animalEarTag;
        this.startDate = startDate;
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

    public int getAnimalEarTag() {
        return animalEarTag;
    }

    public void setAnimalEarTag(int animalEarTag) {
        this.animalEarTag = animalEarTag;
    }

    public long getStartDate() {
        return startDate;
    }

    public void setStartDate(long startDate) {
        this.startDate = startDate;
    }

    public BigInteger getEndDate() {
        return endDate;
    }

    public void setEndDate(BigInteger endDate) {
        this.endDate = endDate;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof BreedingHasAnimal)) {
            return false;
        }
        BreedingHasAnimal other = (BreedingHasAnimal) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.BreedingHasAnimal[ id=" + id + " ]";
    }
    
}
