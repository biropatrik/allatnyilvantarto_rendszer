/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author Patrik
 */

@ManagedBean(name="navigationController", eager = true)
@SessionScoped
public class NavigationController {
    
    public String newMail(){
        return "newMail.xhtml?faces-redirect=true"; 
    }
    
    public String index(){
        return "index.xhtml?faces-redirect=true"; 
    }
    
    public String account(){
        return "account.xhtml?faces-redirect=true";
    }
    
    public String accountEdit(){
        return "accountEdit.xhtml?faces-redirect=true";
    }
    
    public String animalList(){
        return "animalList.xhtml?faces-redirect=true";
    }
}
