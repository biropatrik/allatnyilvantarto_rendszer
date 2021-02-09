/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

/**
 *
 * @author Patrik
 */

import client.UserClient;
import client.UserHasMailClient;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import model.Pairs;
import model.UserHasMailModel;
import model.UserModel;

@ManagedBean(name="userHasMailController", eager = true)
@SessionScoped
public class UserHasMailController {
    private static final long serialVersionUID = 1L;
    private String id;
    private String openedId = "-1";
    
    private String receiver_email;
    private String subject;
    private String mail_text;
    
    List<Pairs<String, Boolean>> validatePairs;

    public UserHasMailController() {
        this.validatePairs = new ArrayList<>();
        
        validatePairs.add(new Pairs("receiver_email", false));
        validatePairs.add(new Pairs("subject", false));
        validatePairs.add(new Pairs("mail_text", false));
    }
    
    public List<UserHasMailModel> getAllMailsWithReceiverId(){
        id = Session.getUserId();
        UserHasMailClient mailClient = new UserHasMailClient();
        List<UserHasMailModel> mails = (List<UserHasMailModel>) mailClient.findAll_withRecId_JSON(List.class, id);
        mailClient.close();
        return mails;
    }
    
    public String getMailDetails(){
        UserHasMailClient mailClient = new UserHasMailClient();
        UserHasMailModel mail = mailClient.find_JSON(UserHasMailModel.class, id);
        mailClient.close();
        
        return mail.getMailText();
    }
    
    public String getUserName(int id){
        UserClient userClient = new UserClient();
        UserModel userModel = userClient.find_JSON(UserModel.class, String.valueOf(id));
        userClient.close();
        return userModel.getLastName() + " " + userModel.getFirstName();
    }
    
    public String getDateFormat(long date){
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy.MM.dd. HH:mm");
        Date correctDate = new Date(date);
        return formatter.format(correctDate);
    }
    
    public String sendMail(){
        UserClient userClient = new UserClient();
        UserModel receiver = userClient.find_withEmail_JSON(UserModel.class, this.receiver_email);
        userClient.close();
        
        UserHasMailModel mailModel = new UserHasMailModel();
        mailModel.setId(null);
        mailModel.setSenderUserId(Integer.parseInt(this.id));
        mailModel.setSubject(this.subject);
        mailModel.setMailText(this.mail_text);
        mailModel.setWhendate(System.currentTimeMillis());
        mailModel.setIsNew(true);
        
        if(isValidatePairsOk()){
            mailModel.setReceiverUserId(receiver.getId());
            
            UserHasMailClient mailClient = new UserHasMailClient();
            mailClient.create_JSON(mailModel);
            mailClient.close();
            cleanData();
            return "/faces/index.xhtml?faces-redirect=true"; 
        }else{
            return "/private/newMail.xhtml";
        }
    }
    
    public void theMailHasBeenRead(String id){
        this.openedId = id;
        UserHasMailClient mailClient = new UserHasMailClient();
        UserHasMailModel mailModel = mailClient.find_JSON(UserHasMailModel.class, id);
        mailModel.setIsNew(false);
        mailClient.edit_JSON(mailModel, id);
        mailClient.close();
    }
    
    public String getNewMailCount(){
        UserHasMailClient mailClient = new UserHasMailClient();
        String count = mailClient.count_newMails_JSON(this.id);
        mailClient.close();
        return count;
    }
    
    private void cleanData(){
        this.receiver_email = "";
        this.subject = "";
        this.mail_text = "";
    }
    
    public String getOpenedId() {
        return openedId;
    }
    
    public void setOpenedId(String openedId){
        this.openedId = openedId;
    }
    
    private void setValidatePairs(String key, boolean value){
        for (Pairs<String, Boolean> validatePair : validatePairs) {
            if(validatePair.getKey().equals(key)){
                validatePair.setValue(value);
            }
        }
    }
    
    private boolean isValidatePairsOk(){
        for (Pairs<String, Boolean> validatePair : validatePairs) {
            if(validatePair.getValue().equals(false)){
                return false;
            }
        }
        return true;
    }

    public String getReceiver_email() {
        return receiver_email;
    }

    public void setReceiver_email(String receiver_email) {
        if(receiver_email == null || receiver_email.isEmpty()){
            FacesContext.getCurrentInstance().addMessage("newMail:receiver_email", new FacesMessage("A mező kitöltése kötelező!"));
            setValidatePairs("receiver_email",false);
        } else {
            setValidatePairs("receiver_email",true);
            this.receiver_email = receiver_email;
        }
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        if(subject == null || subject.isEmpty()){
            FacesContext.getCurrentInstance().addMessage("newMail:subject", new FacesMessage("A mező kitöltése kötelező!"));
            setValidatePairs("subject",false);
        } else {
            setValidatePairs("subject",true);
            this.subject = subject;
        }
    }

    public String getMail_text() {
        return mail_text;
    }

    public void setMail_text(String mail_text) {
        if(mail_text == null || mail_text.isEmpty()){
            FacesContext.getCurrentInstance().addMessage("newMail:mail_text", new FacesMessage("A mező kitöltése kötelező!"));
            setValidatePairs("mail_text",false);
        } else {
            setValidatePairs("mail_text",true);
            this.mail_text = mail_text;
        }
    }
    
    public void deleteMail(String id){
        UserHasMailClient mailClient = new UserHasMailClient();
        mailClient.remove(id);
        mailClient.close();
    }
}
