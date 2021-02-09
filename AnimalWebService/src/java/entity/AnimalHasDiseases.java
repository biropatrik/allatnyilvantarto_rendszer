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
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Patrik
 */
@Entity
@Table(name = "animal_has_diseases")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "AnimalHasDiseases.findAll", query = "SELECT a FROM AnimalHasDiseases a"),
    @NamedQuery(name = "AnimalHasDiseases.findById", query = "SELECT a FROM AnimalHasDiseases a WHERE a.id = :id"),
    @NamedQuery(name = "AnimalHasDiseases.findByAnimalEarTag", query = "SELECT a FROM AnimalHasDiseases a WHERE a.animalEarTag = :animalEarTag"),
    @NamedQuery(name = "AnimalHasDiseases.findByAnimalDiseasesId", query = "SELECT a FROM AnimalHasDiseases a WHERE a.animalDiseasesId = :animalDiseasesId"),
    @NamedQuery(name = "AnimalHasDiseases.findByStartDate", query = "SELECT a FROM AnimalHasDiseases a WHERE a.startDate = :startDate"),
    @NamedQuery(name = "AnimalHasDiseases.findByEndDate", query = "SELECT a FROM AnimalHasDiseases a WHERE a.endDate = :endDate"),
    @NamedQuery(name = "AnimalHasDiseases.findByComment", query = "SELECT a FROM AnimalHasDiseases a WHERE a.comment = :comment")})
public class AnimalHasDiseases implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Column(name = "animal_ear_tag")
    private int animalEarTag;
    @Basic(optional = false)
    @NotNull
    @Column(name = "animal_diseases_id")
    private int animalDiseasesId;
    @Basic(optional = false)
    @NotNull
    @Column(name = "start_date")
    private long startDate;
    @Column(name = "end_date")
    private BigInteger endDate;
    @Size(max = 300)
    @Column(name = "comment")
    private String comment;

    public AnimalHasDiseases() {
    }

    public AnimalHasDiseases(Integer id) {
        this.id = id;
    }

    public AnimalHasDiseases(Integer id, int animalEarTag, int animalDiseasesId, long startDate) {
        this.id = id;
        this.animalEarTag = animalEarTag;
        this.animalDiseasesId = animalDiseasesId;
        this.startDate = startDate;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int getAnimalEarTag() {
        return animalEarTag;
    }

    public void setAnimalEarTag(int animalEarTag) {
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

    public BigInteger getEndDate() {
        return endDate;
    }

    public void setEndDate(BigInteger endDate) {
        this.endDate = endDate;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
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
        if (!(object instanceof AnimalHasDiseases)) {
            return false;
        }
        AnimalHasDiseases other = (AnimalHasDiseases) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.AnimalHasDiseases[ id=" + id + " ]";
    }
    
}
