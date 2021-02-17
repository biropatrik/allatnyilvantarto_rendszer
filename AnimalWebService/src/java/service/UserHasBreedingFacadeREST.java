/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import entity.UserHasBreeding;
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
@Path("entity.userhasbreeding")
public class UserHasBreedingFacadeREST extends AbstractFacade<UserHasBreeding> {

    @PersistenceContext(unitName = "AnimalWebServicePU")
    private EntityManager em;

    public UserHasBreedingFacadeREST() {
        super(UserHasBreeding.class);
    }

    @POST
    @Override
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public void create(UserHasBreeding entity) {
        super.create(entity);
    }

    @PUT
    @Path("{id}")
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public void edit(@PathParam("id") Integer id, UserHasBreeding entity) {
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
    public UserHasBreeding find(@PathParam("id") Integer id) {
        return super.find(id);
    }

    @GET
    @Override
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<UserHasBreeding> findAll() {
        return super.findAll();
    }

    @GET
    @Path("{from}/{to}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<UserHasBreeding> findRange(@PathParam("from") Integer from, @PathParam("to") Integer to) {
        return super.findRange(new int[]{from, to});
    }

    @GET
    @Path("count")
    @Produces(MediaType.TEXT_PLAIN)
    public String countREST() {
        return String.valueOf(super.count());
    }
    
    @GET
    @Path("breedings_by_id/{id}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public ArrayList<String> findAllBreedingIdByUserId(@PathParam("id") Integer id){
        List<UserHasBreeding> allBreedings = super.findAll();
        ArrayList<String> breedings = new ArrayList<>();
        for(int i=0; i<allBreedings.size(); i++){
            if(allBreedings.get(i).getUserId() == id){
                breedings.add(String.valueOf(allBreedings.get(i).getBreedingId()));
            }
        }
        return breedings;
    }
    
    @GET
    @Path("userid_by_breedingid/{id}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public int findUserIdByBreedingId(@PathParam("id") Integer id){
        List<UserHasBreeding> allBreedings = super.findAll();
        for(int i=0; i<allBreedings.size(); i++){
            if(allBreedings.get(i).getBreedingId() == id){
                return allBreedings.get(i).getUserId();
            }
        }
        return -1;
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
    
}
