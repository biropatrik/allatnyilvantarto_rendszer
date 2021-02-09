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
@Table(name = "breeding_qualification")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "BreedingQualification.findAll", query = "SELECT b FROM BreedingQualification b"),
    @NamedQuery(name = "BreedingQualification.findById", query = "SELECT b FROM BreedingQualification b WHERE b.id = :id"),
    @NamedQuery(name = "BreedingQualification.findByQualification", query = "SELECT b FROM BreedingQualification b WHERE b.qualification = :qualification")})
public class BreedingQualification implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 250)
    @Column(name = "qualification")
    private String qualification;

    public BreedingQualification() {
    }

    public BreedingQualification(Integer id) {
        this.id = id;
    }

    public BreedingQualification(Integer id, String qualification) {
        this.id = id;
        this.qualification = qualification;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getQualification() {
        return qualification;
    }

    public void setQualification(String qualification) {
        this.qualification = qualification;
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
        if (!(object instanceof BreedingQualification)) {
            return false;
        }
        BreedingQualification other = (BreedingQualification) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.BreedingQualification[ id=" + id + " ]";
    }
    
}
