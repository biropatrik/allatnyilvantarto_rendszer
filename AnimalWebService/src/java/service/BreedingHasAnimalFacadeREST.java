/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import entity.BreedingHasAnimal;
import java.math.BigInteger;
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
@Path("entity.breedinghasanimal")
public class BreedingHasAnimalFacadeREST extends AbstractFacade<BreedingHasAnimal> {

    @PersistenceContext(unitName = "AnimalWebServicePU")
    private EntityManager em;

    public BreedingHasAnimalFacadeREST() {
        super(BreedingHasAnimal.class);
    }

    @POST
    @Override
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public void create(BreedingHasAnimal entity) {
        super.create(entity);
    }

    @PUT
    @Path("{id}")
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public void edit(@PathParam("id") Integer id, BreedingHasAnimal entity) {
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
    public BreedingHasAnimal find(@PathParam("id") Integer id) {
        return super.find(id);
    }

    @GET
    @Override
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<BreedingHasAnimal> findAll() {
        return super.findAll();
    }

    @GET
    @Path("{from}/{to}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<BreedingHasAnimal> findRange(@PathParam("from") Integer from, @PathParam("to") Integer to) {
        return super.findRange(new int[]{from, to});
    }

    @GET
    @Path("count")
    @Produces(MediaType.TEXT_PLAIN)
    public String countREST() {
        return String.valueOf(super.count());
    }
    
    
    @GET
    @Path("eartags_by_breedingid/{id}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public ArrayList<BreedingHasAnimal> findAllEarTagsByBreedingId(@PathParam("id") Integer id){
        List<BreedingHasAnimal> allBreedings = super.findAll();
        ArrayList<BreedingHasAnimal> breedings = new ArrayList<>();
        for(int i=0; i<allBreedings.size(); i++){
            BigInteger endDate = allBreedings.get(i).getEndDate();
            if(allBreedings.get(i).getBreedingId() == id && endDate == null ){
                breedings.add(allBreedings.get(i));
            }
        }
        return breedings;
    }
    
    @GET
    @Path("breedingids_by_eartag/{id}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public ArrayList<BreedingHasAnimal> findAllBreedingIdsByEarTag(@PathParam("id") String id){
        List<BreedingHasAnimal> allBreedings = super.findAll();
        ArrayList<BreedingHasAnimal> breedings = new ArrayList<>();
        for(int i=0; i<allBreedings.size(); i++){
            if(allBreedings.get(i).getAnimalEarTag().equals(id)){
                breedings.add(allBreedings.get(i));
            }
        }
        return breedings;
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
    
}
