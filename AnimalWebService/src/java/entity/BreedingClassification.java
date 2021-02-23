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
@Table(name = "breeding_classification")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "BreedingClassification.findAll", query = "SELECT b FROM BreedingClassification b"),
    @NamedQuery(name = "BreedingClassification.findById", query = "SELECT b FROM BreedingClassification b WHERE b.id = :id"),
    @NamedQuery(name = "BreedingClassification.findByName", query = "SELECT b FROM BreedingClassification b WHERE b.name = :name")})
public class BreedingClassification implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 250)
    @Column(name = "name")
    private String name;

    public BreedingClassification() {
    }

    public BreedingClassification(Integer id) {
        this.id = id;
    }

    public BreedingClassification(Integer id, String name) {
        this.id = id;
        this.name = name;
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

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof BreedingClassification)) {
            return false;
        }
        BreedingClassification other = (BreedingClassification) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.BreedingClassification[ id=" + id + " ]";
    }
    
}
