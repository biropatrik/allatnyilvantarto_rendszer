/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import entity.HoldingPlaceHasParcelNumber;
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
@Path("entity.holdingplacehasparcelnumber")
public class HoldingPlaceHasParcelNumberFacadeREST extends AbstractFacade<HoldingPlaceHasParcelNumber> {

    @PersistenceContext(unitName = "AnimalWebServicePU")
    private EntityManager em;

    public HoldingPlaceHasParcelNumberFacadeREST() {
        super(HoldingPlaceHasParcelNumber.class);
    }

    @POST
    @Override
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public void create(HoldingPlaceHasParcelNumber entity) {
        super.create(entity);
    }

    @PUT
    @Path("{id}")
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public void edit(@PathParam("id") Integer id, HoldingPlaceHasParcelNumber entity) {
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
    public HoldingPlaceHasParcelNumber find(@PathParam("id") Integer id) {
        return super.find(id);
    }

    @GET
    @Override
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<HoldingPlaceHasParcelNumber> findAll() {
        return super.findAll();
    }

    @GET
    @Path("{from}/{to}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<HoldingPlaceHasParcelNumber> findRange(@PathParam("from") Integer from, @PathParam("to") Integer to) {
        return super.findRange(new int[]{from, to});
    }

    @GET
    @Path("count")
    @Produces(MediaType.TEXT_PLAIN)
    public String countREST() {
        return String.valueOf(super.count());
    }
    
    @GET
    @Path("holdingplaceparcelnumbers_by_holdingplaceid/{id}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public ArrayList<HoldingPlaceHasParcelNumber> findAllHoldingPlaceHasParcelNumbersById(@PathParam("id") int id){
        List<HoldingPlaceHasParcelNumber> allParcelNumbers = super.findAll();
        ArrayList<HoldingPlaceHasParcelNumber> parcelNumbers = new ArrayList<>();
        for(int i=0; i<allParcelNumbers.size(); i++){
            if(allParcelNumbers.get(i).getHoldingPlaceId() == id){
                parcelNumbers.add(allParcelNumbers.get(i));
            }
        }
        return parcelNumbers;
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
    
}
