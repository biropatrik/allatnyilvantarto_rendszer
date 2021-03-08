/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import entity.User;
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
@Path("entity.user")
public class UserFacadeREST extends AbstractFacade<User> {

    @PersistenceContext(unitName = "AnimalWebServicePU")
    private EntityManager em;

    public UserFacadeREST() {
        super(User.class);
    }

    @POST
    @Override
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public void create(User entity) {
        super.create(entity);
    }

    @PUT
    @Path("{id}")
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public void edit(@PathParam("id") Integer id, User entity) {
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
    public User find(@PathParam("id") Integer id) {
        return super.find(id);
    }
    
    @GET
    @Path("email/{email}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public User find_withEmail(@PathParam("email") String email) {
        List<User> users = super.findAll();
        for(int i=0; i<users.size(); i++){
            if(users.get(i).getEmail().equals(email)){
                return users.get(i);
            }
        }
        return null;
    }

    @GET
    @Override
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<User> findAll() {
        return super.findAll();
    }

    @GET
    @Path("{from}/{to}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<User> findRange(@PathParam("from") Integer from, @PathParam("to") Integer to) {
        return super.findRange(new int[]{from, to});
    }

    @GET
    @Path("count")
    @Produces(MediaType.TEXT_PLAIN)
    public String countREST() {
        return String.valueOf(super.count());
    }
    
    @GET
    @Path("login/{e}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public String checkLogin(@PathParam("e") String loginData){
        String[] parts = loginData.split("_");
        String email = parts[0];
        String password = parts[1];
        
        List<User> users = super.findAll();
        for(int i=0; i<users.size(); i++){
            if(users.get(i).getEmail().equals(email)){
                if(users.get(i).getPassword().equals(password)){
                    if(users.get(i).getIsActive() == true){
                        return "correct";
                    }
                    return "notactive";
                }
                return "passworderror";
            }
        }
        return "emailerror";
    }
    
    @GET
    @Path("get_all_userid_by_role/{role}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public ArrayList<String> getAllUserIdByRole(@PathParam("role") String role){
        ArrayList<String> ids = new ArrayList<>();
        List<User> users = super.findAll();
        for(int i=0; i<users.size(); i++){
            if(users.get(i).getRoleId() == Integer.parseInt(role)){
                ids.add(String.valueOf(users.get(i).getId()));
            }
        }
        return ids;
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
    
}
