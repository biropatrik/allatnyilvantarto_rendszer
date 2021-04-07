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
@Table(name = "vet_has_county")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "VetHasCounty.findAll", query = "SELECT v FROM VetHasCounty v"),
    @NamedQuery(name = "VetHasCounty.findById", query = "SELECT v FROM VetHasCounty v WHERE v.id = :id"),
    @NamedQuery(name = "VetHasCounty.findByUserId", query = "SELECT v FROM VetHasCounty v WHERE v.userId = :userId"),
    @NamedQuery(name = "VetHasCounty.findByCountyId", query = "SELECT v FROM VetHasCounty v WHERE v.countyId = :countyId")})
public class VetHasCounty implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Column(name = "user_id")
    private int userId;
    @Basic(optional = false)
    @NotNull
    @Column(name = "county_id")
    private int countyId;

    public VetHasCounty() {
    }

    public VetHasCounty(Integer id) {
        this.id = id;
    }

    public VetHasCounty(Integer id, int userId, int countyId) {
        this.id = id;
        this.userId = userId;
        this.countyId = countyId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getCountyId() {
        return countyId;
    }

    public void setCountyId(int countyId) {
        this.countyId = countyId;
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
        if (!(object instanceof VetHasCounty)) {
            return false;
        }
        VetHasCounty other = (VetHasCounty) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.VetHasCounty[ id=" + id + " ]";
    }
    
}
