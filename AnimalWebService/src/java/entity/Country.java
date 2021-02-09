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
@Table(name = "country")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Country.findAll", query = "SELECT c FROM Country c"),
    @NamedQuery(name = "Country.findByIso2", query = "SELECT c FROM Country c WHERE c.iso2 = :iso2"),
    @NamedQuery(name = "Country.findByName", query = "SELECT c FROM Country c WHERE c.name = :name")})
public class Country implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 2)
    @Column(name = "iso2")
    private String iso2;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 250)
    @Column(name = "name")
    private String name;

    public Country() {
    }

    public Country(String iso2) {
        this.iso2 = iso2;
    }

    public Country(String iso2, String name) {
        this.iso2 = iso2;
        this.name = name;
    }

    public String getIso2() {
        return iso2;
    }

    public void setIso2(String iso2) {
        this.iso2 = iso2;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (iso2 != null ? iso2.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Country)) {
            return false;
        }
        Country other = (Country) object;
        if ((this.iso2 == null && other.iso2 != null) || (this.iso2 != null && !this.iso2.equals(other.iso2))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.Country[ iso2=" + iso2 + " ]";
    }
    
}
