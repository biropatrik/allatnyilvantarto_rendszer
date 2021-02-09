/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.math.BigDecimal;

/**
 *
 * @author Patrik
 */
public class UserHasMailModel {
    private Integer id;
    private int senderUserId;
    private int receiverUserId;
    private long whendate;
    private String subject;
    private String mailText;
    private boolean isNew;
    
    public UserHasMailModel(){}

    public UserHasMailModel(Integer id, int senderUserId, int receiverUserId, long whandate, String subject, String mailText, boolean isNew) {
        this.id = id;
        this.senderUserId = senderUserId;
        this.receiverUserId = receiverUserId;
        this.whendate = whandate;
        this.subject = subject;
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

    public boolean isIsNew() {
        return isNew;
    }

    public void setIsNew(boolean isNew) {
        this.isNew = isNew;
    }
}
