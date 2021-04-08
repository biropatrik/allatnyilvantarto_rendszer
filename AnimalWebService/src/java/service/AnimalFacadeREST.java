/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import entity.Animal;
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
@Path("entity.animal")
public class AnimalFacadeREST extends AbstractFacade<Animal> {

    @PersistenceContext(unitName = "AnimalWebServicePU")
    private EntityManager em;

    public AnimalFacadeREST() {
        super(Animal.class);
    }

    @POST
    @Override
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public void create(Animal entity) {
        super.create(entity);
    }

    @PUT
    @Path("{id}")
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public void edit(@PathParam("id") String id, Animal entity) {
        super.edit(entity);
    }

    @DELETE
    @Path("{id}")
    public void remove(@PathParam("id") String id) {
        super.remove(super.find(id));
    }

    @GET
    @Path("{id}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Animal find(@PathParam("id") String id) {
        return super.find(id);
    }

    @GET
    @Override
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Animal> findAll() {
        return super.findAll();
    }

    @GET
    @Path("{from}/{to}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Animal> findRange(@PathParam("from") Integer from, @PathParam("to") Integer to) {
        return super.findRange(new int[]{from, to});
    }

    @GET
    @Path("count")
    @Produces(MediaType.TEXT_PLAIN)
    public String countREST() {
        return String.valueOf(super.count());
    }
    
    @GET
    @Path("findAnimalsByUserId/{id}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public ArrayList<Animal> findAnimalsByUserId(@PathParam("id") Integer id){
        ArrayList<Animal> animals = (ArrayList<Animal>) em.createNamedQuery("Animal.findByUserId")
                                     .setParameter("userId", id)
                                     .getResultList();
           
        return animals;
    }
    
    @GET
    @Path("findAnimalsByIsAccepted/{isAccepted}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public ArrayList<Animal> findAnimalsByIsAccepted(@PathParam("isAccepted") boolean isAccepted){
        ArrayList<Animal> animals = (ArrayList<Animal>) em.createNamedQuery("Animal.findByIsAccepted")
                                     .setParameter("isAccepted", isAccepted)
                                     .getResultList();
           
        return animals;
    }
    
    @GET
    @Path("findByIsNotActive/{userId}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public ArrayList<Animal> findByIsNotActive(@PathParam("userId") Integer userId){
        ArrayList<Animal> animals = new ArrayList<>();
        ArrayList<Integer> roles = (ArrayList<Integer>) em.createNamedQuery("User.findRoleIdByUserId")
                                    .setParameter("id", userId)
                                    .getResultList();
        if(roles.size() < 1){
            return animals;
        }
        
        if(roles.get(0) == 2){
            animals = (ArrayList<Animal>) em.createNamedQuery("Animal.findByIsNotActiveAndVetCountyId")
                                .setParameter("userId", userId)
                                .getResultList();
        }else if(roles.get(0) == 1){
            animals = (ArrayList<Animal>) em.createNamedQuery("Animal.findByIsAccepted")
                                .setParameter("isAccepted", false)
                                .getResultList();
        }
        return animals;
    }
    
    @GET
    @Path("findByVetUserId/{userId}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public ArrayList<Animal> findByVetUserId(@PathParam("userId") Integer userId){
        ArrayList<Animal> animals = new ArrayList<>();
        ArrayList<Integer> roles = (ArrayList<Integer>) em.createNamedQuery("User.findRoleIdByUserId")
                                    .setParameter("id", userId)
                                    .getResultList();
        if(roles.size() < 1){
            return animals;
        }
        
        if(roles.get(0) == 2){
            animals = (ArrayList<Animal>) em.createNamedQuery("Animal.findByVetCountyId")
                                .setParameter("userId", userId)
                                .getResultList();
        }else if(roles.get(0) == 1){
            animals = (ArrayList<Animal>) em.createNamedQuery("Animal.findAll")
                                .getResultList();
        }
        return animals;
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
    
}
