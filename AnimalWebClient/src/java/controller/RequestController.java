/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import client.AnimalClient;
import client.BreedingClient;
import client.CityClient;
import client.CountryClient;
import client.CountyClient;
import client.HoldingPlaceClient;
import client.RoleClient;
import client.UserClient;
import client.UserHasBreedingClient;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import model.AnimalModel;
import model.BreedingModel;
import model.CityModel;
import model.CountryModel;
import model.CountyModel;
import model.HoldingPlaceModel;
import model.RoleModel;
import model.UserModel;
import org.json.JSONObject;

/**
 *
 * @author Patrik
 */
@ManagedBean(name="requestController", eager = true)
@SessionScoped
public class RequestController {
    UserClient userClient;
    CountryClient countryClient;
    RoleClient roleClient;
    CountyClient countyClient;
    CityClient cityClient;
    HoldingPlaceClient holdingPlaceClient;
    BreedingClient breedingClient;
    UserHasBreedingClient userHasBreedingClient;
    AnimalClient animalClient;
    
    List<UserModel> registrations = new ArrayList<>();
    private String searchedId;
    private UserModel searchedUser;
    private String password;

    private List<UserModel> findUsersByIsActive(boolean isActive){
        userClient = new UserClient();
        List<UserModel> list = userClient.findByIsActive_JSON(List.class, String.valueOf(isActive));
        userClient.close();
        
        ObjectMapper mapper = new ObjectMapper();
        List<UserModel> users = mapper.convertValue(list, new TypeReference<List<UserModel>>(){});
        return users;
    }
    
    public void searchUserWithId(String id){
        if(id != null){
            this.searchedId = id;
            searchUser();
        }
    }
    
    public void searchUser(){
        if(this.searchedId == null){
            return;
        }
        for(int i=0; i<registrations.size(); i++){
            if(String.valueOf(registrations.get(i).getId()).equals(searchedId)){
                this.searchedUser = registrations.get(i);
            }
        }
    }
    
    public List<HoldingPlaceModel> getHoldingPlacesByIsActive(boolean isActive){
        holdingPlaceClient = new HoldingPlaceClient();
        List<HoldingPlaceModel> list = holdingPlaceClient.findByIsActive_JSON(List.class, String.valueOf(isActive));
        holdingPlaceClient.close();
        ObjectMapper mapper = new ObjectMapper();
        List<HoldingPlaceModel> holdingPlaceList = mapper.convertValue(list, new TypeReference<List<HoldingPlaceModel>>(){});
        return holdingPlaceList;
    }
    
    public List<BreedingModel> getBreedingsByIsActive(boolean isActive){
        breedingClient = new BreedingClient();
        List<BreedingModel> list = breedingClient.findByIsActive_JSON(List.class, String.valueOf(isActive));
        breedingClient.close();
        ObjectMapper mapper = new ObjectMapper();
        List<BreedingModel> breedings = mapper.convertValue(list, new TypeReference<List<BreedingModel>>(){});
        return breedings;
    }
    
    public List<AnimalModel> getAnimalsByIsAccepted(boolean isAccepted){
        animalClient = new AnimalClient();
        List<AnimalModel> list = animalClient.findAnimalsByIsAccepted_JSON(List.class, String.valueOf(isAccepted));
        animalClient.close();
        ObjectMapper mapper = new ObjectMapper();
        List<AnimalModel> animalList = mapper.convertValue(list, new TypeReference<List<AnimalModel>>(){});
        return animalList;
    }
    
    public List<RoleModel> getAllRoles(){
        roleClient = new RoleClient();
        List<RoleModel> roles = roleClient.findAll_JSON(List.class);
        roleClient.close();
        return roles;
    }
    
    public void acceptRegistration(String userId){
        if(this.password == null || this.password.isEmpty()){
            FacesContext.getCurrentInstance().addMessage("checkRegistrations:password", new FacesMessage("A mező kitöltése kötelező!"));
            return;
        }
        
        userClient = new UserClient();
        int response = -1;
        try{
            response = userClient.validateUser_JSON(int.class, Session.getUserId()+"_"+password);
        }catch(Exception e){
            FacesContext.getCurrentInstance().addMessage("checkRegistrations:password", new FacesMessage("Hiba történt!"));
            return;
        }
        
        if(response == 0){
            FacesContext.getCurrentInstance().addMessage("checkRegistrations:password", new FacesMessage("Hibás jelszó!"));
            return;
        }else if(userId != null && !userId.isEmpty() && response == 1){
            UserModel user = userClient.find_JSON(UserModel.class, userId);
            user.setIsActive(true);
            user.setIsAccepted(true);
            user.setRoleId(searchedUser.getRoleId());
            
            userClient.edit_JSON(user, userId);
            userClient.close();
        }else{
            FacesContext.getCurrentInstance().addMessage("checkRegistrations:password", new FacesMessage("Hiba történt! Nem azonosítható felhasználó!"));
            return;
        }
        userClient.close();
    }
    
    public void acceptHoldingPlace(String holdingPlaceId){
        if(this.password == null || this.password.isEmpty()){
            FacesContext.getCurrentInstance().addMessage("checkHoldingPlace:password", new FacesMessage("A mező kitöltése kötelező!"));
            return;
        }
        
        userClient = new UserClient();
        int response = -1;
        try{
            response = userClient.validateUser_JSON(int.class, Session.getUserId()+"_"+password);
        }catch(Exception e){
            FacesContext.getCurrentInstance().addMessage("checkHoldingPlace:password", new FacesMessage("Hiba történt!"));
            return;
        }
        
        if(response == 0){
            FacesContext.getCurrentInstance().addMessage("checkHoldingPlace:password", new FacesMessage("Hibás jelszó!"));
            return;
        }
        if(holdingPlaceId != null && !holdingPlaceId.isEmpty() && response == 1){
            holdingPlaceClient = new HoldingPlaceClient();
            HoldingPlaceModel holdingPlace = holdingPlaceClient.find_JSON(HoldingPlaceModel.class, holdingPlaceId);
            holdingPlace.setIsActive(true);
            
            holdingPlaceClient.edit_JSON(holdingPlace, holdingPlaceId);
        }else{
            FacesContext.getCurrentInstance().addMessage("checkHoldingPlace:password", new FacesMessage("Hiba történt! Nem azonosítható tartási hely!"));
            return;
        }
        holdingPlaceClient.close();
    }
    
    public void acceptBreeding(String breedingId){
        if(this.password == null || this.password.isEmpty()){
            FacesContext.getCurrentInstance().addMessage("checkBreeding:password", new FacesMessage("A mező kitöltése kötelező!"));
            return;
        }
        
        userClient = new UserClient();
        int response = -1;
        try{
            response = userClient.validateUser_JSON(int.class, Session.getUserId()+"_"+password);
        }catch(Exception e){
            FacesContext.getCurrentInstance().addMessage("checkBreeding:password", new FacesMessage("Hiba történt!"));
            return;
        }
        
        if(response == 0){
            FacesContext.getCurrentInstance().addMessage("checkBreeding:password", new FacesMessage("Hibás jelszó!"));
            return;
        }
        if(breedingId != null && !breedingId.isEmpty() && response == 1){
            breedingClient = new BreedingClient();
            BreedingModel breeding = breedingClient.find_JSON(BreedingModel.class, breedingId);
            breeding.setIsActive(true);
            
            breedingClient.edit_JSON(breeding, breedingId);
        }else{
            FacesContext.getCurrentInstance().addMessage("checkBreeding:password", new FacesMessage("Hiba történt! Nem azonosítható tenyészet!"));
            return;
        }
        breedingClient.close();
    }
    
    public void acceptAnimal(String earTag){
        if(this.password == null || this.password.isEmpty()){
            FacesContext.getCurrentInstance().addMessage("checkAnimal:password", new FacesMessage("A mező kitöltése kötelező!"));
            return;
        }
        
        userClient = new UserClient();
        int response = -1;
        try{
            response = userClient.validateUser_JSON(int.class, Session.getUserId()+"_"+password);
        }catch(Exception e){
            FacesContext.getCurrentInstance().addMessage("checkAnimal:password", new FacesMessage("Hiba történt!"));
            return;
        }
        
        if(response == 0){
            FacesContext.getCurrentInstance().addMessage("checkAnimal:password", new FacesMessage("Hibás jelszó!"));
            return;
        }
        if(earTag != null && !earTag.isEmpty() && response == 1){
            animalClient = new AnimalClient();
            AnimalModel animal = animalClient.find_JSON(AnimalModel.class, earTag);
            animal.setIsAccepted(true);
            String jsonAnimal = new JSONObject()
                            .put("earTag", animal.getEarTag())
                            .put("motherId", animal.getMotherId())
                            .put("name", animal.getName())
                            .put("sex", animal.isSex())
                            .put("birthdate", animal.getBirthdate())
                            .put("deathdate", animal.getDeathdate())
                            .put("speciesId", animal.getSpeciesId())
                            .put("breedId", animal.getBreedId())
                            .put("colorId", animal.getColorId())
                            .put("twinning", animal.isTwinning())
                            .put("calvingId", animal.getCalvingId())
                            .put("calvingWeight", animal.getCalvingWeight())
                            .put("isAccepted", animal.isIsAccepted())
                            .put("inseminationDate", animal.getInseminationDate())
                            .toString();
                
            animalClient.edit_JSON(jsonAnimal, String.valueOf(animal.getEarTag()));
            
        }else{
            FacesContext.getCurrentInstance().addMessage("checkAnimal:password", new FacesMessage("Hiba történt! Nem azonosítható egyed!"));
            return;
        }
        animalClient.close();
    }
    
    public int getUserIdByBreedingId(String breedingId){
        userHasBreedingClient = new UserHasBreedingClient();
        int userId = userHasBreedingClient.findUserIdByBreedingId_JSON(Integer.class, breedingId);
        userHasBreedingClient.close();
        return userId;
    }
    
    public int getUserIdByEarTag(String earTag){
        userHasBreedingClient = new UserHasBreedingClient();
        int userId = userHasBreedingClient.findUserIdByEarTag_JSON(Integer.class, earTag);
        userHasBreedingClient.close();
        return userId;
    }
    
    public UserModel getUserById(String id){
        if(id == null || id.isEmpty()){
            return new UserModel();
        }
        userClient = new UserClient();
        UserModel model = userClient.find_JSON(UserModel.class, id);
        userClient.close();
        return model;
    }
    
    public String getCountryName(String iso2){
        if(iso2 == null || iso2.isEmpty()){
            return "";
        }
        countryClient = new CountryClient();
        CountryModel country = countryClient.find_JSON(CountryModel.class, iso2);
        countryClient.close();
        return country.getName();
    }
    
    public String getCountyName(int id){
        countyClient = new CountyClient();
        CountyModel county = countyClient.find_JSON(CountyModel.class, String.valueOf(id));
        countyClient.close();
        if(county == null){
            return "Hiba történt!";
        }
        return county.getName();
    }
    
    public String getCityName(int id){
        cityClient = new CityClient();
        CityModel city = cityClient.find_JSON(CityModel.class, String.valueOf(id));
        cityClient.close();
        if(city == null){
            return "Hiba történt!";
        }
        return city.getName();
    }
    
    public String getCityPostalCode(int id){
        cityClient = new CityClient();
        CityModel city = cityClient.find_JSON(CityModel.class, String.valueOf(id));
        cityClient.close();
        if(city == null){
            return "Hiba történt!";
        }
        return city.getPostalCode();
    }
    
    public String getRoleName(int id){
        roleClient = new RoleClient();
        RoleModel role = roleClient.find_JSON(RoleModel.class, String.valueOf(id));
        roleClient.close();
        if(role == null){
            return "Hiba történt!";
        }
        return role.getName();
    }
    
    public List<UserModel> getRegistrations() {
        registrations = findUsersByIsActive(false);
        return registrations;
    }

    public void setRegistrations(List<UserModel> registrations) {
        this.registrations = registrations;
    }

    public String getSearchedId() {
        return searchedId;
    }

    public UserModel getSearchedUser() {
        return searchedUser;
    }

    public void setPassword(String password) {
        if(password != null || !password.isEmpty()){
            byte[] encodedBytes = Base64.getEncoder().encode(password.getBytes());
            this.password = new String(encodedBytes);
        }
    }

    public String getPassword() {
        return password;
    }
}
