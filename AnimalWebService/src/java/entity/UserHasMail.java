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
@Table(name = "user_has_mail")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "UserHasMail.findAll", query = "SELECT u FROM UserHasMail u"),
    @NamedQuery(name = "UserHasMail.findById", query = "SELECT u FROM UserHasMail u WHERE u.id = :id"),
    @NamedQuery(name = "UserHasMail.findBySenderUserId", query = "SELECT u FROM UserHasMail u WHERE u.senderUserId = :senderUserId"),
    @NamedQuery(name = "UserHasMail.findByReceiverUserId", query = "SELECT u FROM UserHasMail u WHERE u.receiverUserId = :receiverUserId"),
    @NamedQuery(name = "UserHasMail.findByWhendate", query = "SELECT u FROM UserHasMail u WHERE u.whendate = :whendate"),
    @NamedQuery(name = "UserHasMail.findBySubject", query = "SELECT u FROM UserHasMail u WHERE u.subject = :subject"),
    @NamedQuery(name = "UserHasMail.findByMailText", query = "SELECT u FROM UserHasMail u WHERE u.mailText = :mailText"),
    @NamedQuery(name = "UserHasMail.findByIsNew", query = "SELECT u FROM UserHasMail u WHERE u.isNew = :isNew")})
public class UserHasMail implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Column(name = "sender_user_id")
    private int senderUserId;
    @Basic(optional = false)
    @NotNull
    @Column(name = "receiver_user_id")
    private int receiverUserId;
    @Basic(optional = false)
    @NotNull
    @Column(name = "whendate")
    private long whendate;
    @Size(max = 50)
    @Column(name = "subject")
    private String subject;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 500)
    @Column(name = "mail_text")
    private String mailText;
    @Basic(optional = false)
    @NotNull
    @Column(name = "isNew")
    private boolean isNew;

    public UserHasMail() {
    }

    public UserHasMail(Integer id) {
        this.id = id;
    }

    public UserHasMail(Integer id, int senderUserId, int receiverUserId, long whendate, String mailText, boolean isNew) {
        this.id = id;
        this.senderUserId = senderUserId;
        this.receiverUserId = receiverUserId;
        this.whendate = whendate;
        this.mailText = mailText;
        this.isNew = isNew;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int getSenderUserId() {
        return senderUserId;
    }

    public void setSenderUserId(int senderUserId) {
        this.senderUserId = senderUserId;
    }

    public int getReceiverUserId() {
        return receiverUserId;
    }

    public void setReceiverUserId(int receiverUserId) {
        this.receiverUserId = receiverUserId;
    }

    public long getWhendate() {
        return whendate;
    }

    public void setWhendate(long whendate) {
        this.whendate = whendate;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getMailText() {
        return mailText;
    }

    public void setMailText(String mailText) {
        this.mailText = mailText;
    }

    public boolean getIsNew() {
        return isNew;
    }

    public void setIsNew(boolean isNew) {
        this.isNew = isNew;
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
        if (!(object instanceof UserHasMail)) {
            return false;
        }
        UserHasMail other = (UserHasMail) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.UserHasMail[ id=" + id + " ]";
    }
    
}
