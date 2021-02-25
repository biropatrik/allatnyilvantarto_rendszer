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
@Table(name = "holding_place")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "HoldingPlace.findAll", query = "SELECT h FROM HoldingPlace h"),
    @NamedQuery(name = "HoldingPlace.findById", query = "SELECT h FROM HoldingPlace h WHERE h.id = :id"),
    @NamedQuery(name = "HoldingPlace.findByCountryIso2", query = "SELECT h FROM HoldingPlace h WHERE h.countryIso2 = :countryIso2"),
    @NamedQuery(name = "HoldingPlace.findByCountyId", query = "SELECT h FROM HoldingPlace h WHERE h.countyId = :countyId"),
    @NamedQuery(name = "HoldingPlace.findByCityId", query = "SELECT h FROM HoldingPlace h WHERE h.cityId = :cityId"),
    @NamedQuery(name = "HoldingPlace.findByStreet", query = "SELECT h FROM HoldingPlace h WHERE h.street = :street"),
    @NamedQuery(name = "HoldingPlace.findByBreedingType", query = "SELECT h FROM HoldingPlace h WHERE h.breedingType = :breedingType"),
    @NamedQuery(name = "HoldingPlace.findByUserVetId", query = "SELECT h FROM HoldingPlace h WHERE h.userVetId = :userVetId"),
    @NamedQuery(name = "HoldingPlace.findByIsActive", query = "SELECT h FROM HoldingPlace h WHERE h.isActive = :isActive")})
public class HoldingPlace implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 2)
    @Column(name = "country_iso2")
    private String countryIso2;
    @Basic(optional = false)
    @NotNull
    @Column(name = "county_id")
    private int countyId;
    @Basic(optional = false)
    @NotNull
    @Column(name = "city_id")
    private int cityId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 250)
    @Column(name = "street")
    private String street;
    @Column(name = "breeding_type")
    private Integer breedingType;
    @Column(name = "user_vet_id")
    private Integer userVetId;
    @Basic(optional = false)
    @NotNull
    @Column(name = "isActive")
    private boolean isActive;

    public HoldingPlace() {
    }

    public HoldingPlace(Integer id) {
        this.id = id;
    }

    public HoldingPlace(Integer id, String countryIso2, int countyId, int cityId, String street, boolean isActive) {
        this.id = id;
        this.countryIso2 = countryIso2;
        this.countyId = countyId;
        this.cityId = cityId;
        this.street = street;
        this.isActive = isActive;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCountryIso2() {
        return countryIso2;
    }

    public void setCountryIso2(String countryIso2) {
        this.countryIso2 = countryIso2;
    }

    public int getCountyId() {
        return countyId;
    }

    public void setCountyId(int countyId) {
        this.countyId = countyId;
    }

    public int getCityId() {
        return cityId;
    }

    public void setCityId(int cityId) {
        this.cityId = cityId;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public Integer getBreedingType() {
        return breedingType;
    }

    public void setBreedingType(Integer breedingType) {
        this.breedingType = breedingType;
    }

    public Integer getUserVetId() {
        return userVetId;
    }

    public void setUserVetId(Integer userVetId) {
        this.userVetId = userVetId;
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
        if (!(object instanceof HoldingPlace)) {
            return false;
        }
        HoldingPlace other = (HoldingPlace) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.HoldingPlace[ id=" + id + " ]";
    }
    
}
