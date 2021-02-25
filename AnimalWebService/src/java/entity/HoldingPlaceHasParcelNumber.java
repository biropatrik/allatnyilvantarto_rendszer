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
@Table(name = "holding_place_has_parcel_number")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "HoldingPlaceHasParcelNumber.findAll", query = "SELECT h FROM HoldingPlaceHasParcelNumber h"),
    @NamedQuery(name = "HoldingPlaceHasParcelNumber.findById", query = "SELECT h FROM HoldingPlaceHasParcelNumber h WHERE h.id = :id"),
    @NamedQuery(name = "HoldingPlaceHasParcelNumber.findByHoldingPlaceId", query = "SELECT h FROM HoldingPlaceHasParcelNumber h WHERE h.holdingPlaceId = :holdingPlaceId"),
    @NamedQuery(name = "HoldingPlaceHasParcelNumber.findByCityId", query = "SELECT h FROM HoldingPlaceHasParcelNumber h WHERE h.cityId = :cityId"),
    @NamedQuery(name = "HoldingPlaceHasParcelNumber.findByParcelNumber", query = "SELECT h FROM HoldingPlaceHasParcelNumber h WHERE h.parcelNumber = :parcelNumber")})
public class HoldingPlaceHasParcelNumber implements Serializable {

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
    @Column(name = "city_id")
    private int cityId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 250)
    @Column(name = "parcel_number")
    private String parcelNumber;

    public HoldingPlaceHasParcelNumber() {
    }

    public HoldingPlaceHasParcelNumber(Integer id) {
        this.id = id;
    }

    public HoldingPlaceHasParcelNumber(Integer id, int holdingPlaceId, int cityId, String parcelNumber) {
        this.id = id;
        this.holdingPlaceId = holdingPlaceId;
        this.cityId = cityId;
        this.parcelNumber = parcelNumber;
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

    public int getCityId() {
        return cityId;
    }

    public void setCityId(int cityId) {
        this.cityId = cityId;
    }

    public String getParcelNumber() {
        return parcelNumber;
    }

    public void setParcelNumber(String parcelNumber) {
        this.parcelNumber = parcelNumber;
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
        if (!(object instanceof HoldingPlaceHasParcelNumber)) {
            return false;
        }
        HoldingPlaceHasParcelNumber other = (HoldingPlaceHasParcelNumber) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.HoldingPlaceHasParcelNumber[ id=" + id + " ]";
    }
    
}
