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
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import model.BreedingClassificationModel;
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
    private List<String> breedingIds;
    private List<BreedingModel> breedings;
    
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
    
    private List<String> getBreedingIdsByUserId(){
        id = Session.getUserId();
        userHasBreedingClient = new UserHasBreedingClient();
        List<String> breedings = (List<String>) userHasBreedingClient.findAllBreedingIdByUserId_JSON(List.class, id);
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
        if(this.breedingIds == null || this.breedingIds.size() < 1){
            this.breedingIds = getBreedingIdsByUserId();
        }
        
        breedingClient = new BreedingClient();
        List<BreedingModel> breedings = new ArrayList<>();
        for(int i=0; i<this.breedingIds.size(); i++){
            BreedingModel model = breedingClient.find_JSON(BreedingModel.class, this.breedingIds.get(i));
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
}
