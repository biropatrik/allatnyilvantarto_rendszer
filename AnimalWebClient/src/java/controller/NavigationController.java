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
    private String activePageName;

    public String getActivePageName() {
        return activePageName;
    }
    
    public String newMail(){
        this.activePageName = "newMail";
        return "newMail.xhtml?faces-redirect=true"; 
    }
    
    public String index(){
        this.activePageName = "index";
        return "index.xhtml?faces-redirect=true"; 
    }
    
    public String account(){
        this.activePageName = "account";
        return "account.xhtml?faces-redirect=true";
    }
    
    public String accountEdit(){
        this.activePageName = "accountEdit";
        return "accountEdit.xhtml?faces-redirect=true";
    }
    
    public String animalList(){
        this.activePageName = "animalList";
        return "animalList.xhtml?faces-redirect=true";
    }
    
    public String breedingList(){
        this.activePageName = "breedingList";
        return "breedingList.xhtml?faces-redirect=true";
    }
    
    public String animalSearch(){
        this.activePageName = "animalSearch";
        return "animalSearch.xhtml?faces-redirect=true";
    }
}
