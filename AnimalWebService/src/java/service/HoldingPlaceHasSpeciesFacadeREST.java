/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import entity.HoldingPlaceHasSpecies;
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
@Path("entity.holdingplacehasspecies")
public class HoldingPlaceHasSpeciesFacadeREST extends AbstractFacade<HoldingPlaceHasSpecies> {

    @PersistenceContext(unitName = "AnimalWebServicePU")
    private EntityManager em;

    public HoldingPlaceHasSpeciesFacadeREST() {
        super(HoldingPlaceHasSpecies.class);
    }

    @POST
    @Override
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public void create(HoldingPlaceHasSpecies entity) {
        super.create(entity);
    }

    @PUT
    @Path("{id}")
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public void edit(@PathParam("id") Integer id, HoldingPlaceHasSpecies entity) {
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
    public HoldingPlaceHasSpecies find(@PathParam("id") Integer id) {
        return super.find(id);
    }

    @GET
    @Override
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<HoldingPlaceHasSpecies> findAll() {
        return super.findAll();
    }

    @GET
    @Path("{from}/{to}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<HoldingPlaceHasSpecies> findRange(@PathParam("from") Integer from, @PathParam("to") Integer to) {
        return super.findRange(new int[]{from, to});
    }

    @GET
    @Path("count")
    @Produces(MediaType.TEXT_PLAIN)
    public String countREST() {
        return String.valueOf(super.count());
    }
    
    @GET
    @Path("holdingplacespecies_by_holdingplaceid/{id}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public ArrayList<HoldingPlaceHasSpecies> findAllHoldingPlaceHasSpeciesById(@PathParam("id") int id){
        List<HoldingPlaceHasSpecies> allSpecies = super.findAll();
        ArrayList<HoldingPlaceHasSpecies> species = new ArrayList<>();
        for(int i=0; i<allSpecies.size(); i++){
            if(allSpecies.get(i).getHoldingPlaceId() == id){
                species.add(allSpecies.get(i));
            }
        }
        return species;
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
    
}
