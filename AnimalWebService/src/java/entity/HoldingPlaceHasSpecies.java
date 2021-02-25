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
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Patrik
 */
@Entity
@Table(name = "holding_place_has_species")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "HoldingPlaceHasSpecies.findAll", query = "SELECT h FROM HoldingPlaceHasSpecies h"),
    @NamedQuery(name = "HoldingPlaceHasSpecies.findById", query = "SELECT h FROM HoldingPlaceHasSpecies h WHERE h.id = :id"),
    @NamedQuery(name = "HoldingPlaceHasSpecies.findByHoldingPlaceId", query = "SELECT h FROM HoldingPlaceHasSpecies h WHERE h.holdingPlaceId = :holdingPlaceId"),
    @NamedQuery(name = "HoldingPlaceHasSpecies.findBySpeciesId", query = "SELECT h FROM HoldingPlaceHasSpecies h WHERE h.speciesId = :speciesId"),
    @NamedQuery(name = "HoldingPlaceHasSpecies.findByStartDate", query = "SELECT h FROM HoldingPlaceHasSpecies h WHERE h.startDate = :startDate"),
    @NamedQuery(name = "HoldingPlaceHasSpecies.findByUtilization", query = "SELECT h FROM HoldingPlaceHasSpecies h WHERE h.utilization = :utilization")})
public class HoldingPlaceHasSpecies implements Serializable {

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
    @Column(name = "species_id")
    private int speciesId;
    @Basic(optional = false)
    @NotNull
    @Column(name = "start_date")
    private long startDate;
    @Size(max = 250)
    @Column(name = "utilization")
    private String utilization;

    public HoldingPlaceHasSpecies() {
    }

    public HoldingPlaceHasSpecies(Integer id) {
        this.id = id;
    }

    public HoldingPlaceHasSpecies(Integer id, int holdingPlaceId, int speciesId, long startDate) {
        this.id = id;
        this.holdingPlaceId = holdingPlaceId;
        this.speciesId = speciesId;
        this.startDate = startDate;
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

    public int getSpeciesId() {
        return speciesId;
    }

    public void setSpeciesId(int speciesId) {
        this.speciesId = speciesId;
    }

    public long getStartDate() {
        return startDate;
    }

    public void setStartDate(long startDate) {
        this.startDate = startDate;
    }

    public String getUtilization() {
        return utilization;
    }

    public void setUtilization(String utilization) {
        this.utilization = utilization;
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
        if (!(object instanceof HoldingPlaceHasSpecies)) {
            return false;
        }
        HoldingPlaceHasSpecies other = (HoldingPlaceHasSpecies) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.HoldingPlaceHasSpecies[ id=" + id + " ]";
    }
    
}
