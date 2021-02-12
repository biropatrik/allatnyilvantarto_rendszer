/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import entity.UserHasMail;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author Patrik
 */
@Stateless
@Path("entity.userhasmail")
public class UserHasMailFacadeREST extends AbstractFacade<UserHasMail> {

    @PersistenceContext(unitName = "AnimalWebServicePU")
    private EntityManager em;

    public UserHasMailFacadeREST() {
        super(UserHasMail.class);
    }

    @POST
    @Override
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public void create(UserHasMail entity) {
        System.out.println("FacadeREST: " + entity.getWhendate());
        super.create(entity);
    }

    @PUT
    @Path("{id}")
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public void edit(@PathParam("id") Integer id, UserHasMail entity) {
        super.edit(entity);
    }

    @DELETE
    @Path("{id}")
    public void remove(@PathParam("id") Integer id) {
        super.remove(super.find(id));
    }

    @GET
    @Path("{id}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public UserHasMail find(@PathParam("id") Integer id) {
        return super.find(id);
    }

    @GET
    @Override
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<UserHasMail> findAll() {
        return super.findAll();
    }

    @GET
    @Path("{from}/{to}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<UserHasMail> findRange(@PathParam("from") Integer from, @PathParam("to") Integer to) {
        return super.findRange(new int[]{from, to});
    }

    @GET
    @Path("count")
    @Produces(MediaType.TEXT_PLAIN)
    public String countREST() {
        return String.valueOf(super.count());
    }
    
    @GET
    @Path("emails_with_id/{id}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<UserHasMail> findAll_withRecId(@PathParam("id") Integer id) {
        List<UserHasMail> mails = super.findAll();
        List<UserHasMail> receivedMails = new ArrayList<>();
        for(int i=0; i < mails.size(); i++){
            if(mails.get(i).getReceiverUserId() == id){
                receivedMails.add(mails.get(i));
            }
        }
        receivedMails.sort((d1,d2) -> Long.valueOf(d2.getWhendate()).compareTo(d1.getWhendate()));
        
        return receivedMails;
    }
    
    @GET
    @Path("new_mails_with_id/{id}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
        public String count_newMails(@PathParam("id") Integer id) {
        List<UserHasMail> mails = super.findAll();
        
        int mail_count = 0;
        for(int i=0; i < mails.size(); i++){
            if(mails.get(i).getReceiverUserId() == id && mails.get(i).getIsNew()){
                mail_count++;
            }
        }
        return String.valueOf(mail_count);
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
}
