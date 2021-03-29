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
    
    public String animalAdd(){
        this.activePageName = "animalAdd";
        return "animalAdd.xhtml?faces-redirect=true";
    }
    
    public String animalEdit(){
        this.activePageName = "animalEdit";
        return "animalEdit.xhtml?faces-redirect=true";
    }
    
    public String breedingList(){
        this.activePageName = "breedingList";
        return "breedingList.xhtml?faces-redirect=true";
    }
    
    public String breedingAdd(){
        this.activePageName = "breedingAdd";
        return "breedingAdd.xhtml?faces-redirect=true";
    }
    
    public String breedingEdit(){
        this.activePageName = "breedingEdit";
        return "breedingEdit.xhtml?faces-redirect=true";
    }
    
    public String holdingPlaceList(){
        this.activePageName = "holdingPlaceList";
        return "holdingPlaceList.xhtml?faces-redirect=true";
    }
    
    public String holdingPlaceAdd(){
        this.activePageName = "holdingPlaceAdd";
        return "holdingPlaceAdd.xhtml?faces-redirect=true";
    }
    
    public String holdingPlaceEdit(){
        this.activePageName = "holdingPlaceEdit";
        return "holdingPlaceEdit.xhtml?faces-redirect=true";
    }
    
    public String animalSearch(){
        this.activePageName = "animalSearch";
        return "animalSearch.xhtml?faces-redirect=true";
    }
}
