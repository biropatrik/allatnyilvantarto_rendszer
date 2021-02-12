/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import client.UserClient;
import java.util.Base64;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;
import model.UserModel;

/**
 *
 * @author Patrik
 */
@ManagedBean(name="loginController", eager = true)
@SessionScoped
public class LoginController {
    private static final long serialVersionUID = 1L;
    private String email;
    private String password;
    
    public String login() {
        
        if(email == null || email.isEmpty()){
            FacesContext.getCurrentInstance().addMessage("login:email", new FacesMessage("A mező kitöltése kötelező!"));
            return "login.xhtml";
        }
        if(password == null || password.isEmpty()){
            FacesContext.getCurrentInstance().addMessage("login:password", new FacesMessage("A mező kitöltése kötelező!"));
            return "login.xhtml";
        }
        
        if (checkLoginData(email, password).equals("correct")) {
            HttpSession session = Session.getSession();
            session.setAttribute("userrole", getRole(email));
            session.setAttribute("userid", getId(email));
            
            return "private/index.xhtml?faces-redirect=true";
            
        } else if(checkLoginData(email, password).equals("emailerror")){
            FacesContext.getCurrentInstance().addMessage("login:email", new FacesMessage("Hibás e-mail cím!"));
            
        } else if(checkLoginData(email, password).equals("passworderror")){
            FacesContext.getCurrentInstance().addMessage("login:password", new FacesMessage("Hibás jelszó!"));
            
        }else if(checkLoginData(email, password).equals("notactive")){
            FacesContext.getCurrentInstance().addMessage("login:email", new FacesMessage("Ez a felhasználó még nincs felülvizsgálva!"));
        }
        return "login.xhtml";
    }
    
    public void logout() {
        HttpSession session = Session.getSession();
        session.invalidate();
        try{
            FacesContext.getCurrentInstance().getExternalContext().redirect("../login.xhtml");
        }catch(Exception e){
            
        }
    }
    
    public String checkLogin(String path, int expectedRole){
        if(Session.getUserRole() == null || Integer.parseInt(Session.getUserRole()) > expectedRole){
            try{
                FacesContext.getCurrentInstance().getExternalContext().redirect("../login.xhtml");
            }catch(Exception e){

            }
        }
        return path;
    }
    
    public String checkLoginData(String email, String password){
        byte[] encodedBytes = Base64.getEncoder().encode(password.getBytes());
        
        UserClient userClient = new UserClient();
        String loginData = email.concat("_" + new String(encodedBytes));
        String response = userClient.checkLogin_JSON(loginData);
        userClient.close();
        
        return response;
    }
    
    public int getRole(String email){
        UserClient userClient = new UserClient();
        UserModel userModel = userClient.find_withEmail_JSON(UserModel.class, email);
        userClient.close();
        if(userModel != null){
            return userModel.getRoleId();
        }
        return -1;
    }
    
    public int getId(String email){
        UserClient userClient = new UserClient();
        UserModel userModel = userClient.find_withEmail_JSON(UserModel.class, email);
        userClient.close();
        return userModel.getId();
    }
    
    public String displayRegisterPage(){
        return "private/register.xhtml?faces-redirect=true";
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
