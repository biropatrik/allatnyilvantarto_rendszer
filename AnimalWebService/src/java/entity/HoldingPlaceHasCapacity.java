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
@Table(name = "holding_place_has_capacity")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "HoldingPlaceHasCapacity.findAll", query = "SELECT h FROM HoldingPlaceHasCapacity h"),
    @NamedQuery(name = "HoldingPlaceHasCapacity.findById", query = "SELECT h FROM HoldingPlaceHasCapacity h WHERE h.id = :id"),
    @NamedQuery(name = "HoldingPlaceHasCapacity.findByHoldingPlaceId", query = "SELECT h FROM HoldingPlaceHasCapacity h WHERE h.holdingPlaceId = :holdingPlaceId"),
    @NamedQuery(name = "HoldingPlaceHasCapacity.findByCapacityType", query = "SELECT h FROM HoldingPlaceHasCapacity h WHERE h.capacityType = :capacityType"),
    @NamedQuery(name = "HoldingPlaceHasCapacity.findBySize", query = "SELECT h FROM HoldingPlaceHasCapacity h WHERE h.size = :size"),
    @NamedQuery(name = "HoldingPlaceHasCapacity.findByStartDate", query = "SELECT h FROM HoldingPlaceHasCapacity h WHERE h.startDate = :startDate")})
public class HoldingPlaceHasCapacity implements Serializable {

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
    @Column(name = "capacity_type")
    private int capacityType;
    @Basic(optional = false)
    @NotNull
    @Column(name = "size")
    private int size;
    @Basic(optional = false)
    @NotNull
    @Column(name = "start_date")
    private long startDate;

    public HoldingPlaceHasCapacity() {
    }

    public HoldingPlaceHasCapacity(Integer id) {
        this.id = id;
    }

    public HoldingPlaceHasCapacity(Integer id, int holdingPlaceId, int capacityType, int size, long startDate) {
        this.id = id;
        this.holdingPlaceId = holdingPlaceId;
        this.capacityType = capacityType;
        this.size = size;
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

    public int getCapacityType() {
        return capacityType;
    }

    public void setCapacityType(int capacityType) {
        this.capacityType = capacityType;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public long getStartDate() {
        return startDate;
    }

    public void setStartDate(long startDate) {
        this.startDate = startDate;
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
        if (!(object instanceof HoldingPlaceHasCapacity)) {
            return false;
        }
        HoldingPlaceHasCapacity other = (HoldingPlaceHasCapacity) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.HoldingPlaceHasCapacity[ id=" + id + " ]";
    }
    
}
