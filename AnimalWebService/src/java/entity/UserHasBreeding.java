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
@Table(name = "user_has_breeding")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "UserHasBreeding.findAll", query = "SELECT u FROM UserHasBreeding u"),
    @NamedQuery(name = "UserHasBreeding.findById", query = "SELECT u FROM UserHasBreeding u WHERE u.id = :id"),
    @NamedQuery(name = "UserHasBreeding.findByUserId", query = "SELECT u FROM UserHasBreeding u WHERE u.userId = :userId"),
    @NamedQuery(name = "UserHasBreeding.findByBreedingId", query = "SELECT u FROM UserHasBreeding u WHERE u.breedingId = :breedingId"),
    @NamedQuery(name = "UserHasBreeding.findBreedingIdByUserId", query = "SELECT u.breedingId FROM UserHasBreeding u WHERE u.userId = :userId"),
    
    @NamedQuery(name = "UserHasBreeding.findByHoldingPlaceIdAndUserId", query = "SELECT u FROM UserHasBreeding u, HoldingPlaceHasBreeding hp "
                                                                               +"WHERE hp.holdingPlaceId = :holdingPlaceId "
                                                                               +"AND u.breedingId = hp.breedingId "
                                                                               +"AND u.userId = :userId"),
    
    @NamedQuery(name = "UserHasBreeding.findUserIdByEarTag", query = "SELECT u FROM BreedingHasAnimal b, UserHasBreeding u "
                                                    + "WHERE u.breedingId = b.breedingId "
                                                    + "AND b.animalEarTag = :earTag "
                                                    + "AND (b.endDate IS NULL OR b.endDate = 0)")
})
public class UserHasBreeding implements Serializable {

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
    @Column(name = "breeding_id")
    private int breedingId;

    public UserHasBreeding() {
    }

    public UserHasBreeding(Integer id) {
        this.id = id;
    }

    public UserHasBreeding(Integer id, int userId, int breedingId) {
        this.id = id;
        this.userId = userId;
        this.breedingId = breedingId;
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
        if (!(object instanceof UserHasBreeding)) {
            return false;
        }
        UserHasBreeding other = (UserHasBreeding) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.UserHasBreeding[ id=" + id + " ]";
    }
    
}
