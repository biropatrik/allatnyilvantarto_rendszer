/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import client.BreedingClassificationClient;
import client.BreedingClient;
import client.BreedingQualificationClient;
import client.BreedingTypeClient;
import client.CityClient;
import client.CountryClient;
import client.CountyClient;
import client.HoldingPlaceClient;
import client.HoldingPlaceHasBreedingClient;
import client.UserClient;
import client.UserHasBreedingClient;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import model.BreedingClassificationModel;
import model.BreedingHasAnimalModel;
import model.BreedingModel;
import model.BreedingQualificationModel;
import model.BreedingTypeModel;
import model.CityModel;
import model.CountryModel;
import model.CountyModel;
import model.HoldingPlaceHasBreedingModel;
import model.HoldingPlaceModel;
import model.UserHasBreedingModel;
import model.UserModel;

/**
 *
 * @author Patrik
 */
@ManagedBean(name="breedingController", eager = true)
@SessionScoped
public class BreedingController {
    private String id;
    private String searchedId;
    private BreedingModel searchedBreeding;
    private List<Integer> breedingIds = new ArrayList<>();
    private List<BreedingModel> breedings;
    
    private BreedingModel newBreeding = new BreedingModel();
    private UserHasBreedingModel newUserHasBreeding = new UserHasBreedingModel();
    private HoldingPlaceHasBreedingModel newHoldingPlaceHasBreeding = new HoldingPlaceHasBreedingModel();
    
    private UserHasBreedingClient userHasBreedingClient;
    private BreedingClient breedingClient;
    private BreedingTypeClient breedingTypeClient;
    private BreedingQualificationClient breedingQualificationClient;
    private BreedingClassificationClient breedingClassificationClient;
    private UserClient userClient;
    private HoldingPlaceHasBreedingClient holdingPlaceHasBreedingClient;
    private HoldingPlaceClient holdingPlaceClient;
    private CountryClient countryClient;
    private CountyClient countyClient;
    private CityClient cityClient;
    
    private List<Integer> getBreedingIdsByUserId(){
        id = Session.getUserId();
        userHasBreedingClient = new UserHasBreedingClient();
        List<Integer> breedings = (List<Integer>) userHasBreedingClient.findAllBreedingIdByUserId_JSON(List.class, id);
        userHasBreedingClient.close();
        return breedings;
    }
    
    private HoldingPlaceHasBreedingModel getHoldingPlaceHasBreedingByBreedingId(String breedingId){
        holdingPlaceHasBreedingClient = new HoldingPlaceHasBreedingClient();
        HoldingPlaceHasBreedingModel model = holdingPlaceHasBreedingClient.findHoldingPlaceByBreedingId_JSON(HoldingPlaceHasBreedingModel.class, breedingId);
        holdingPlaceHasBreedingClient.close();
        return model;
    }
    
    public List<BreedingModel> getAllBreedingByBreedingIds(){
        this.breedingIds.clear();
        this.breedingIds = getBreedingIdsByUserId();
        
        breedingClient = new BreedingClient();
        List<BreedingModel> breedings = new ArrayList<>();
        for(int i=0; i<this.breedingIds.size(); i++){
            BreedingModel model = breedingClient.find_JSON(BreedingModel.class, String.valueOf(this.breedingIds.get(i)));
            breedings.add(model);
        }
        breedingClient.close();
        return breedings;
    }
    
    public String getBreedingTypeName(String typeId){
        breedingTypeClient = new BreedingTypeClient();
        BreedingTypeModel model = breedingTypeClient.find_JSON(BreedingTypeModel.class, typeId);
        breedingTypeClient.close();
        return model.getName();
    }
    
    public String getBreedingQualificationName(String qualificationId){
        breedingQualificationClient = new BreedingQualificationClient();
        BreedingQualificationModel model = breedingQualificationClient.find_JSON(BreedingQualificationModel.class, qualificationId);
        breedingQualificationClient.close();
        return model.getName();
    }
    
    public String getBreedingClassificationName(String classificationId){
        breedingClassificationClient = new BreedingClassificationClient();
        BreedingClassificationModel model = breedingClassificationClient.find_JSON(BreedingClassificationModel.class, classificationId);
        breedingClassificationClient.close();
        return model.getName();
    }
    
    public String getUserNameByBreedingId(String breedingId){
        userHasBreedingClient = new UserHasBreedingClient();
        userClient = new UserClient();
        int userId = userHasBreedingClient.findUserIdByBreedingId_JSON(Integer.class, breedingId);
        UserModel model = userClient.find_JSON(UserModel.class, String.valueOf(userId));
        userClient.close();
        userHasBreedingClient.close();
        
        return model.getLastName() + " " + model.getFirstName();
    }
    
    public String getUserNameByUserId(String userId){
        userClient = new UserClient();
        UserModel model = userClient.find_JSON(UserModel.class, userId);
        userClient.close();
        
        return model.getLastName() + " " + model.getFirstName();
    }
    
    public HoldingPlaceModel getHoldingPlaceByHoldingPlaceId(String holdingPlaceId){
        holdingPlaceClient = new HoldingPlaceClient();
        HoldingPlaceModel model = holdingPlaceClient.find_JSON(HoldingPlaceModel.class, holdingPlaceId);
        holdingPlaceClient.close();
        return model;
    }
    
    private String getCountryName(String iso2){
        countryClient = new CountryClient();
        CountryModel country = countryClient.find_JSON(CountryModel.class, iso2);
        countryClient.close();
        return country.getName();
    }
    
    private String getCountyName(int id){
        countyClient = new CountyClient();
        CountyModel county = countyClient.find_JSON(CountyModel.class, String.valueOf(id));
        countyClient.close();
        if(county == null){
            return "Hiba történt!";
        }
        return county.getName();
    }
    
    private String getCityName(int id){
        cityClient = new CityClient();
        CityModel city = cityClient.find_JSON(CityModel.class, String.valueOf(id));
        cityClient.close();
        if(city == null){
            return "Hiba történt!";
        }
        return city.getName();
    }
    
    private String getCityPostalCode(int id){
        cityClient = new CityClient();
        CityModel city = cityClient.find_JSON(CityModel.class, String.valueOf(id));
        cityClient.close();
        if(city == null){
            return "Hiba történt!";
        }
        return city.getPostalCode();
    }
    
    public String getBreedAddressByBreedingId(String breedingId){
        int holdingPlaceId = getHoldingPlaceHasBreedingByBreedingId(breedingId).getHoldingPlaceId();
        HoldingPlaceModel holdingPlace = getHoldingPlaceByHoldingPlaceId(String.valueOf(holdingPlaceId));
        
        String address = "(" + holdingPlace.getCountryIso2() + ") " +
                  getCountyName(holdingPlace.getCountyId()) + " " +
                  getCityPostalCode(holdingPlace.getCityId()) + " " +
                  getCityName(holdingPlace.getCityId()) + " " +
                  holdingPlace.getStreet();
        
        return address;
    }
    
    public String getVetIdByBreedingId(String breedingId){
        int holdingPlaceId = getHoldingPlaceHasBreedingByBreedingId(breedingId).getHoldingPlaceId();
        HoldingPlaceModel holdingPlace = getHoldingPlaceByHoldingPlaceId(String.valueOf(holdingPlaceId));
        
        return String.valueOf(holdingPlace.getUserVetId());
    }

    public String getSearchedId() {
        return searchedId;
    }

    public BreedingModel getSearchedBreeding() {
        return searchedBreeding;
    }
    
    public void searchBreedingWithId(String id){
        if(id != null){
            this.searchedId = id;
            searchBreeding();
        }
    }
    
    public void searchBreeding(){
        if(this.searchedId == null){
            return;
        }
        
        breedingClient = new BreedingClient();
        this.searchedBreeding = breedingClient.find_JSON(BreedingModel.class, this.searchedId);
        breedingClient.close();
    }
    
    public List<String> getAllUserIdByRole(){
        userClient = new UserClient();
        List<String> ids = userClient.getAllUserIdByRole_JSON(List.class, "1");
        ids.addAll(userClient.getAllUserIdByRole_JSON(List.class, "2"));
        ids.addAll(userClient.getAllUserIdByRole_JSON(List.class, "3"));
        userClient.close();
        return ids;
    }
    
    public List<BreedingTypeModel> getAllBreedingTypes(){
        breedingTypeClient = new BreedingTypeClient();
        List<BreedingTypeModel> model = breedingTypeClient.findAll_JSON(List.class);
        breedingTypeClient.close();
        return model;
    }
    
    public List<BreedingClassificationModel> getAllBreedingClassification(){
        breedingClassificationClient = new BreedingClassificationClient();
        List<BreedingClassificationModel> model = breedingClassificationClient.findAll_JSON(List.class);
        breedingClassificationClient.close();
        return model;
    }
    
    public List<BreedingQualificationModel> getAllBreedingQualification(){
        breedingQualificationClient = new BreedingQualificationClient();
        List<BreedingQualificationModel> model = breedingQualificationClient.findAll_JSON(List.class);
        breedingQualificationClient.close();
        return model;
    }
    
    public List<String> getAllHoldingPlaceIds(){
        holdingPlaceClient = new HoldingPlaceClient();
        List<String> model = holdingPlaceClient.getAllHoldingPlaceIds_JSON(List.class);
        holdingPlaceClient.close();
        return model;
    }

    public BreedingModel getNewBreeding() {
        return newBreeding;
    }

    public void setNewBreeding(BreedingModel newBreeding) {
        this.newBreeding = newBreeding;
    }

    public UserHasBreedingModel getNewUserHasBreeding() {
        return newUserHasBreeding;
    }

    public void setNewUserHasBreeding(UserHasBreedingModel newUserHasBreeding) {
        this.newUserHasBreeding = newUserHasBreeding;
    }

    public HoldingPlaceHasBreedingModel getNewHoldingPlaceHasBreeding() {
        return newHoldingPlaceHasBreeding;
    }

    public void setNewHoldingPlaceHasBreeding(HoldingPlaceHasBreedingModel newHoldingPlaceHasBreeding) {
        this.newHoldingPlaceHasBreeding = newHoldingPlaceHasBreeding;
    }
    
    private UserHasBreedingModel getUserHasBreedingByBreedingId(int breedingId){
        userHasBreedingClient = new UserHasBreedingClient();
        List<UserHasBreedingModel> list = userHasBreedingClient.findByBreedingId_JSON(List.class, String.valueOf(breedingId));
        userHasBreedingClient.close();
        
        if(list.size() < 0){
            return new UserHasBreedingModel();
        }
        //java.lang.ClassCastException: java.util.LinkedHashMap cannot be cast to model.UserHasBreedingModel
        //Hiba ellen: ObjectMapper!
        ObjectMapper mapper = new ObjectMapper();
        List<UserHasBreedingModel> userHasBreedingList = mapper.convertValue(list, new TypeReference<List<UserHasBreedingModel>>(){});
        return userHasBreedingList.get(0);
    }
    
    private HoldingPlaceHasBreedingModel getHoldingPlaceHasBreedingByBreedingId(int breedingId){
        holdingPlaceHasBreedingClient = new HoldingPlaceHasBreedingClient();
        List<HoldingPlaceHasBreedingModel> list = holdingPlaceHasBreedingClient.findByBreedingId_JSON(List.class, String.valueOf(breedingId));
        holdingPlaceHasBreedingClient.close();
        //java.lang.ClassCastException: java.util.LinkedHashMap cannot be cast to model.UserHasBreedingModel
        //Hiba ellen: ObjectMapper!
        ObjectMapper mapper = new ObjectMapper();
        List<HoldingPlaceHasBreedingModel> holdingPlaceHasBreedingList = mapper.convertValue(list, new TypeReference<List<HoldingPlaceHasBreedingModel>>(){});
        return holdingPlaceHasBreedingList.get(0);
    }
    
    public void saveNewBreeding(){
        
        //Ellenőrzés!
        
        this.newBreeding.setIsActive(false);
        breedingClient = new BreedingClient();
        breedingClient.create_JSON(this.newBreeding);
        breedingClient.close();
        
        this.newUserHasBreeding.setBreedingId(this.newBreeding.getId());
        userHasBreedingClient = new UserHasBreedingClient();
        userHasBreedingClient.create_JSON(this.newUserHasBreeding);
        userHasBreedingClient.close();
        
        this.newHoldingPlaceHasBreeding.setBreedingId(this.newBreeding.getId());
        holdingPlaceHasBreedingClient = new HoldingPlaceHasBreedingClient();
        holdingPlaceHasBreedingClient.create_JSON(this.newHoldingPlaceHasBreeding);
        holdingPlaceHasBreedingClient.close();
    }
    
    public void saveModifiedBreeding(){
        this.newBreeding.setIsActive(false);
        breedingClient = new BreedingClient();
        breedingClient.edit_JSON(this.newBreeding, String.valueOf(this.newBreeding.getId()));
        breedingClient.close();
        
        this.newUserHasBreeding.setBreedingId(this.newBreeding.getId());
        userHasBreedingClient = new UserHasBreedingClient();
        userHasBreedingClient.edit_JSON(this.newUserHasBreeding, String.valueOf(this.newUserHasBreeding.getId()));
        userHasBreedingClient.close();
        
        this.newHoldingPlaceHasBreeding.setBreedingId(this.newBreeding.getId());
        holdingPlaceHasBreedingClient = new HoldingPlaceHasBreedingClient();
        holdingPlaceHasBreedingClient.edit_JSON(this.newHoldingPlaceHasBreeding, String.valueOf(this.newHoldingPlaceHasBreeding.getId()));
        holdingPlaceHasBreedingClient.close();
    }
    
    public String loadEditPage(int breedingId){
        breedingClient = new BreedingClient();
        this.newBreeding = breedingClient.find_JSON(BreedingModel.class, String.valueOf(breedingId));
        breedingClient.close();
        resetSearchedBreeding();
        
        this.newUserHasBreeding = getUserHasBreedingByBreedingId(breedingId);
        this.newHoldingPlaceHasBreeding = getHoldingPlaceHasBreedingByBreedingId(breedingId);
        
        NavigationController c = new NavigationController();
        return c.breedingEdit();
    }
    
    private void resetSearchedBreeding(){
        this.searchedId = "";
        this.searchedBreeding = null;
    }
}
