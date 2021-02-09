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
@Table(name = "holding_place_has_breeding")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "HoldingPlaceHasBreeding.findAll", query = "SELECT h FROM HoldingPlaceHasBreeding h"),
    @NamedQuery(name = "HoldingPlaceHasBreeding.findById", query = "SELECT h FROM HoldingPlaceHasBreeding h WHERE h.id = :id"),
    @NamedQuery(name = "HoldingPlaceHasBreeding.findByHoldingPlaceId", query = "SELECT h FROM HoldingPlaceHasBreeding h WHERE h.holdingPlaceId = :holdingPlaceId"),
    @NamedQuery(name = "HoldingPlaceHasBreeding.findByBreedingId", query = "SELECT h FROM HoldingPlaceHasBreeding h WHERE h.breedingId = :breedingId")})
public class HoldingPlaceHasBreeding implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Column(name = "holding_place_id")
    private int holdingPlaceId;
    @Basic(optional = false)
    @NotNull
    @Column(name = "breeding_id")
    private int breedingId;

    public HoldingPlaceHasBreeding() {
    }

    public HoldingPlaceHasBreeding(Integer id) {
        this.id = id;
    }

    public HoldingPlaceHasBreeding(Integer id, int holdingPlaceId, int breedingId) {
        this.id = id;
        this.holdingPlaceId = holdingPlaceId;
        this.breedingId = breedingId;
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

    public int getBreedingId() {
        return breedingId;
    }

    public void setBreedingId(int breedingId) {
        this.breedingId = breedingId;
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
        if (!(object instanceof HoldingPlaceHasBreeding)) {
            return false;
        }
        HoldingPlaceHasBreeding other = (HoldingPlaceHasBreeding) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.HoldingPlaceHasBreeding[ id=" + id + " ]";
    }
    
}
