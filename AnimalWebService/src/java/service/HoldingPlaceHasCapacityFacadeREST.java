/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import entity.HoldingPlaceHasCapacity;
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
@Path("entity.holdingplacehascapacity")
public class HoldingPlaceHasCapacityFacadeREST extends AbstractFacade<HoldingPlaceHasCapacity> {

    @PersistenceContext(unitName = "AnimalWebServicePU")
    private EntityManager em;

    public HoldingPlaceHasCapacityFacadeREST() {
        super(HoldingPlaceHasCapacity.class);
    }

    @POST
    @Override
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public void create(HoldingPlaceHasCapacity entity) {
        super.create(entity);
    }

    @PUT
    @Path("{id}")
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public void edit(@PathParam("id") Integer id, HoldingPlaceHasCapacity entity) {
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
    public HoldingPlaceHasCapacity find(@PathParam("id") Integer id) {
        return super.find(id);
    }

    @GET
    @Override
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<HoldingPlaceHasCapacity> findAll() {
        return super.findAll();
    }

    @GET
    @Path("{from}/{to}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<HoldingPlaceHasCapacity> findRange(@PathParam("from") Integer from, @PathParam("to") Integer to) {
        return super.findRange(new int[]{from, to});
    }

    @GET
    @Path("count")
    @Produces(MediaType.TEXT_PLAIN)
    public String countREST() {
        return String.valueOf(super.count());
    }
    
    @GET
    @Path("holdingplacecapacity_by_holdingplaceid/{id}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public ArrayList<HoldingPlaceHasCapacity> findAllHoldingPlaceHasCapacityById(@PathParam("id") int id){
        List<HoldingPlaceHasCapacity> allCapacity = super.findAll();
        ArrayList<HoldingPlaceHasCapacity> capacity = new ArrayList<>();
        for(int i=0; i<allCapacity.size(); i++){
            if(allCapacity.get(i).getHoldingPlaceId() == id){
                capacity.add(allCapacity.get(i));
            }
        }
        return capacity;
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
    
}
