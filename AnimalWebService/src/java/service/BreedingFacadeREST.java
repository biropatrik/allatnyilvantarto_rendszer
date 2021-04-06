/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import entity.Breeding;
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
@Path("entity.breeding")
public class BreedingFacadeREST extends AbstractFacade<Breeding> {

    @PersistenceContext(unitName = "AnimalWebServicePU")
    private EntityManager em;

    public BreedingFacadeREST() {
        super(Breeding.class);
    }

    @POST
    @Override
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public void create(Breeding entity) {
        super.create(entity);
    }

    @PUT
    @Path("{id}")
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public void edit(@PathParam("id") Integer id, Breeding entity) {
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
    public Breeding find(@PathParam("id") Integer id) {
        return super.find(id);
    }

    @GET
    @Override
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Breeding> findAll() {
        return super.findAll();
    }

    @GET
    @Path("{from}/{to}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Breeding> findRange(@PathParam("from") Integer from, @PathParam("to") Integer to) {
        return super.findRange(new int[]{from, to});
    }

    @GET
    @Path("count")
    @Produces(MediaType.TEXT_PLAIN)
    public String countREST() {
        return String.valueOf(super.count());
    }
    
    @GET
    @Path("findBreedingByUserId/{id}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public ArrayList<Breeding> findBreedingByUserId(@PathParam("id") Integer id){
        ArrayList<Breeding> breedings = (ArrayList<Breeding>) em.createNamedQuery("Breeding.findByUserId")
                                     .setParameter("userId", id)
                                     .getResultList();
           
        return breedings;
    }
    
    @GET
    @Path("findByIsActive/{isActive}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public ArrayList<Breeding> findByIsActive(@PathParam("isActive") boolean isActive){
        ArrayList<Breeding> breedings = (ArrayList<Breeding>) em.createNamedQuery("Breeding.findByIsActive")
                                     .setParameter("isActive", isActive)
                                     .getResultList();
           
        return breedings;
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
    
}
