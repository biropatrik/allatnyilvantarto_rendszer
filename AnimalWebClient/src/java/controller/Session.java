/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Patrik
 */
public class Session {
    public static HttpSession getSession() {
        return (HttpSession) FacesContext.getCurrentInstance()
                        .getExternalContext().getSession(false);
    }

    public static HttpServletRequest getRequest() {
        return (HttpServletRequest) FacesContext.getCurrentInstance()
                        .getExternalContext().getRequest();
    }

    public static String getUserRole() {
        HttpSession session = (HttpSession) FacesContext.getCurrentInstance()
                        .getExternalContext().getSession(false);
        try{
            return session.getAttribute("userrole").toString();
        }catch(Exception ex){
            return null;
        }
    }

    public static String getUserId() {
        HttpSession session = getSession();
        if (session != null && session.getAttribute("userid") != null)
            return session.getAttribute("userid").toString();
        else
            return null;
    }
}
