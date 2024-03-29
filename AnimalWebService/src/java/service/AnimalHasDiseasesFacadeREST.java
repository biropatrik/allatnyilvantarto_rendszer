/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import entity.AnimalHasDiseases;
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
@Path("entity.animalhasdiseases")
public class AnimalHasDiseasesFacadeREST extends AbstractFacade<AnimalHasDiseases> {

    @PersistenceContext(unitName = "AnimalWebServicePU")
    private EntityManager em;

    public AnimalHasDiseasesFacadeREST() {
        super(AnimalHasDiseases.class);
    }

    @POST
    @Override
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public void create(AnimalHasDiseases entity) {
        super.create(entity);
    }

    @PUT
    @Path("{id}")
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public void edit(@PathParam("id") Integer id, AnimalHasDiseases entity) {
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
    public AnimalHasDiseases find(@PathParam("id") Integer id) {
        return super.find(id);
    }

    @GET
    @Override
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<AnimalHasDiseases> findAll() {
        return super.findAll();
    }

    @GET
    @Path("{from}/{to}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<AnimalHasDiseases> findRange(@PathParam("from") Integer from, @PathParam("to") Integer to) {
        return super.findRange(new int[]{from, to});
    }

    @GET
    @Path("count")
    @Produces(MediaType.TEXT_PLAIN)
    public String countREST() {
        return String.valueOf(super.count());
    }
    
    @GET
    @Path("diseases_by_eartag/{eartag}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public ArrayList<AnimalHasDiseases> findByAnimalEarTag(@PathParam("eartag") String eartag){
        ArrayList<AnimalHasDiseases> diseases = (ArrayList<AnimalHasDiseases>) em.createNamedQuery("AnimalHasDiseases.findByAnimalEarTag")
                                        .setParameter("animalEarTag", eartag)
                                        .getResultList();
        diseases.sort((d1,d2) -> Long.valueOf(d2.getStartDate()).compareTo(d1.getStartDate()));
        return diseases;
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
    
}
