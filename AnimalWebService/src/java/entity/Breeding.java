/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.io.Serializable;
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
@Table(name = "breeding")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Breeding.findAll", query = "SELECT b FROM Breeding b"),
    @NamedQuery(name = "Breeding.findById", query = "SELECT b FROM Breeding b WHERE b.id = :id"),
    @NamedQuery(name = "Breeding.findByName", query = "SELECT b FROM Breeding b WHERE b.name = :name"),
    @NamedQuery(name = "Breeding.findByBreedingType", query = "SELECT b FROM Breeding b WHERE b.breedingType = :breedingType"),
    @NamedQuery(name = "Breeding.findByBreedingQualification", query = "SELECT b FROM Breeding b WHERE b.breedingQualification = :breedingQualification"),
    @NamedQuery(name = "Breeding.findByBreedingClassification", query = "SELECT b FROM Breeding b WHERE b.breedingClassification = :breedingClassification"),
    @NamedQuery(name = "Breeding.findByIsActive", query = "SELECT b FROM Breeding b WHERE b.isActive = :isActive")})
public class Breeding implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "id")
    private Integer id;
    @Size(max = 250)
    @Column(name = "name")
    private String name;
    @Basic(optional = false)
    @NotNull
    @Column(name = "breeding_type")
    private int breedingType;
    @Basic(optional = false)
    @NotNull
    @Column(name = "breeding_qualification")
    private int breedingQualification;
    @Basic(optional = false)
    @NotNull
    @Column(name = "breeding_classification")
    private int breedingClassification;
    @Basic(optional = false)
    @NotNull
    @Column(name = "isActive")
    private boolean isActive;

    public Breeding() {
    }

    public Breeding(Integer id) {
        this.id = id;
    }

    public Breeding(Integer id, int breedingType, int breedingQualification, int breedingClassification, boolean isActive) {
        this.id = id;
        this.breedingType = breedingType;
        this.breedingQualification = breedingQualification;
        this.breedingClassification = breedingClassification;
        this.isActive = isActive;
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

    public int getBreedingType() {
        return breedingType;
    }

    public void setBreedingType(int breedingType) {
        this.breedingType = breedingType;
    }

    public int getBreedingQualification() {
        return breedingQualification;
    }

    public void setBreedingQualification(int breedingQualification) {
        this.breedingQualification = breedingQualification;
    }

    public int getBreedingClassification() {
        return breedingClassification;
    }

    public void setBreedingClassification(int breedingClassification) {
        this.breedingClassification = breedingClassification;
    }

    public boolean getIsActive() {
        return isActive;
    }

    public void setIsActive(boolean isActive) {
        this.isActive = isActive;
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
        if (!(object instanceof Breeding)) {
            return false;
        }
        Breeding other = (Breeding) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.Breeding[ id=" + id + " ]";
    }
    
}
