/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import entity.Animal;
import entity.Species;
import entity.User;
import entity.UserHasMail;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
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

    @EJB
    private UserHasMailFacadeREST userHasMailFacadeREST;

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
    public User findByEmail(@PathParam("email") String email) {
         ArrayList<User> users = (ArrayList<User>) em.createNamedQuery("User.findByEmail")
                                .setParameter("email", email)
                                .getResultList();
         
         return users.get(0);
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
        
        ArrayList<User> users = (ArrayList<User>) em.createNamedQuery("User.login")
                                .setParameter("email", email)
                                .setParameter("password", password)
                                .getResultList();
        
        if(users.size() < 1){
            return "passworderror";
        }
        else if(!users.get(0).getIsActive() == true){
            return "inactive";
        }
        else{
            sendInseminationAlertMessage(users.get(0).getId());
            return "correct";
        }
    }
    
    @GET
    @Path("get_all_userid_by_role/{role}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public ArrayList<Integer> getAllUserIdByRole(@PathParam("role") Integer role){
        ArrayList<Integer> ids = (ArrayList<Integer>) em.createNamedQuery("User.findIdsByRoleId")
                                .setParameter("roleId", role)
                                .getResultList();
        return ids;
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
    
    private void sendInseminationAlertMessage(Integer userId){
        List<Animal> animals = (List<Animal>) em.createNamedQuery("Animal.findByUserId")
                                    .setParameter("userId", userId)
                                    .getResultList();
        
        List<Species> species = (List<Species>) em.createNamedQuery("Species.findAll")
                                    .getResultList();
        
        List<UserHasMail> mails = (List<UserHasMail>) em.createNamedQuery("UserHasMail.findByReceiverUserId")
                                    .setParameter("receiverUserId", userId)
                                    .getResultList();
        
        List<String> earTags = new ArrayList<>();
        
        for(int i=0; i<mails.size(); i++){
            String[] subject = mails.get(i).getSubject().split(" ", 3);
            if(mails.get(i).getSenderUserId() == 0 && subject.length == 3){
                earTags.add(subject[2]);
            }
        }
        
        for(int i=0; i<animals.size(); i++){
            for(int j=0; j<species.size(); j++){
                if(animals.get(i).getInseminationDate() != null && species.get(j).getDurationOfPregnancy() != null && !earTags.contains(animals.get(i).getEarTag())){
                    if(BigInteger.valueOf(species.get(j).getDurationOfPregnancy()).subtract(BigInteger.valueOf(System.currentTimeMillis()).subtract(animals.get(i).getInseminationDate())).compareTo(BigInteger.TEN) == -1){
                        
                        UserHasMail newMessage = new UserHasMail();
                        newMessage.setSenderUserId(0);
                        newMessage.setReceiverUserId(userId);
                        newMessage.setSubject("Tájékoztatás - "+animals.get(i).getEarTag());
                        newMessage.setMailText("Tisztelt Felhasználó!\n\nTajékoztatjuk, hogy a "+ animals.get(i).getEarTag() + " azonosító számú" + (animals.get(i).getName().isEmpty() ? "" : ", és "+animals.get(i).getName().isEmpty()+" nevű") + " egyed körübelül 10 napon belül elleni fog!");
                        newMessage.setWhendate(System.currentTimeMillis());
                        newMessage.setIsNew(true);
                        
                        userHasMailFacadeREST.create(newMessage);
                        break;
                    }
                }
            }
        }
    }
}
