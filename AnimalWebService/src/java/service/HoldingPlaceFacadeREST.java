/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import entity.HoldingPlace;
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
@Path("entity.holdingplace")
public class HoldingPlaceFacadeREST extends AbstractFacade<HoldingPlace> {

    @PersistenceContext(unitName = "AnimalWebServicePU")
    private EntityManager em;

    public HoldingPlaceFacadeREST() {
        super(HoldingPlace.class);
    }

    @POST
    @Override
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public void create(HoldingPlace entity) {
        super.create(entity);
    }

    @PUT
    @Path("{id}")
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public void edit(@PathParam("id") Integer id, HoldingPlace entity) {
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
    public HoldingPlace find(@PathParam("id") Integer id) {
        return super.find(id);
    }

    @GET
    @Override
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<HoldingPlace> findAll() {
        return super.findAll();
    }

    @GET
    @Path("{from}/{to}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<HoldingPlace> findRange(@PathParam("from") Integer from, @PathParam("to") Integer to) {
        return super.findRange(new int[]{from, to});
    }

    @GET
    @Path("count")
    @Produces(MediaType.TEXT_PLAIN)
    public String countREST() {
        return String.valueOf(super.count());
    }
    
    @GET
    @Path("get_all_holding_place_ids")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public ArrayList<String> getAllHoldingPlaceIds() {
        List<HoldingPlace> allHoldingPlaces = super.findAll();
        ArrayList<String> ids = new ArrayList<>();
        for(int i=0; i<allHoldingPlaces.size(); i++){
            ids.add(String.valueOf(allHoldingPlaces.get(i).getId()));
        }
        return ids;
    }
    
    @GET
    @Path("findByAnimalEarTag/{earTag}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public ArrayList<HoldingPlace> findByAnimalEarTag(@PathParam("earTag") String earTag) {
        ArrayList<HoldingPlace> holdingPlaces = (ArrayList<HoldingPlace>) em.createNamedQuery("HoldingPlace.findByAnimalEarTag")
                                    .setParameter("animalEarTag", earTag)
                                    .getResultList();
        return holdingPlaces;
    }
    
    @GET
    @Path("findByUserId/{userId}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public ArrayList<HoldingPlace> findByUserId(@PathParam("userId") Integer userId) {
        ArrayList<HoldingPlace> holdingPlaces = (ArrayList<HoldingPlace>) em.createNamedQuery("HoldingPlace.findByUserId")
                                    .setParameter("userId", userId)
                                    .getResultList();
        return holdingPlaces;
    }
    
    @GET
    @Path("findByIsActive/{isActive}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public ArrayList<HoldingPlace> findByIsActive(@PathParam("isActive") boolean isActive) {
        ArrayList<HoldingPlace> holdingPlaces = (ArrayList<HoldingPlace>) em.createNamedQuery("HoldingPlace.findByIsActive")
                                    .setParameter("isActive", isActive)
                                    .getResultList();
        return holdingPlaces;
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
    
}
