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
import client.CapacityTypeClient;
import client.CityClient;
import client.CountryClient;
import client.CountyClient;
import client.HoldingPlaceClient;
import client.HoldingPlaceHasBreedingClient;
import client.HoldingPlaceHasCapacityClient;
import client.HoldingPlaceHasParcelNumberClient;
import client.HoldingPlaceHasSpeciesClient;
import client.SpeciesClient;
import client.UserClient;
import client.UserHasBreedingClient;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import model.BreedingModel;
import model.CapacityTypeModel;
import model.CityModel;
import model.CountryModel;
import model.CountyModel;
import model.HoldingPlaceHasBreedingModel;
import model.HoldingPlaceHasCapacityModel;
import model.HoldingPlaceHasParcelNumberModel;
import model.HoldingPlaceHasSpeciesModel;
import model.HoldingPlaceModel;
import model.SpeciesModel;
import model.UserModel;

/**
 *
 * @author Patrik
 */
@ManagedBean(name="holdingPlaceController", eager = true)
@SessionScoped
public class HoldingPlaceController {
    private String id;
    private String searchedId;
    private HoldingPlaceModel searchedHoldingPlace;
    private List<String> breedingIds;
    
    private UserHasBreedingClient userHasBreedingClient;
    private UserClient userClient;
    private HoldingPlaceHasBreedingClient holdingPlaceHasBreedingClient;
    private HoldingPlaceClient holdingPlaceClient;
    private CountryClient countryClient;
    private CountyClient countyClient;
    private CityClient cityClient;
    private HoldingPlaceHasCapacityClient holdingPlaceHasCapacityClient;
    private HoldingPlaceHasSpeciesClient holdingPlaceHasSpeciesClient;
    private HoldingPlaceHasParcelNumberClient holdingPlaceHasParcelNumberClient;
    private SpeciesClient speciesClient;
    private CapacityTypeClient capacityTypeClient;
    
    public List<HoldingPlaceModel> getAllHoldingPlacesByBreedingIds(){
        if(this.breedingIds == null || this.breedingIds.size() < 1){
            this.breedingIds = getBreedingIdsByUserId();
        }
        
        List<String> holdingPlaceIds = new ArrayList<>();
        for(int i=0; i<this.breedingIds.size(); i++){
            holdingPlaceIds.add(String.valueOf(getHoldingPlaceHasBreedingByBreedingId(this.breedingIds.get(i)).getHoldingPlaceId()));
        }
        
        holdingPlaceClient = new HoldingPlaceClient();
        List<HoldingPlaceModel> holdingPlaces = new ArrayList<>();
        for(int i=0; i<holdingPlaceIds.size(); i++){
           HoldingPlaceModel model = holdingPlaceClient.find_JSON(HoldingPlaceModel.class, holdingPlaceIds.get(i));
           holdingPlaces.add(model);
        }
        
        return holdingPlaces;
    }
    
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
    
    public List<HoldingPlaceHasSpeciesModel> getAllHoldingPlaceHasSpeciesById(String holdingPlaceId){
        holdingPlaceHasSpeciesClient = new HoldingPlaceHasSpeciesClient();
        List<HoldingPlaceHasSpeciesModel> model = holdingPlaceHasSpeciesClient.findAllHoldingPlaceHasSpeciesById_JSON(List.class, holdingPlaceId);
        holdingPlaceHasSpeciesClient.close();
        return model;
    }
    
    public List<HoldingPlaceHasCapacityModel> getAllHoldingPlaceHasCapacityById(String holdingPlaceId){
        holdingPlaceHasCapacityClient = new HoldingPlaceHasCapacityClient();
        List<HoldingPlaceHasCapacityModel> model = holdingPlaceHasCapacityClient.findAllHoldingPlaceHasCapacityById_JSON(List.class, holdingPlaceId);
        holdingPlaceHasCapacityClient.close();
        return model;
    }
    
    public List<HoldingPlaceHasParcelNumberModel> getAllHoldingPlaceHasParcelNumberById(String holdingPlaceId){
        holdingPlaceHasParcelNumberClient = new HoldingPlaceHasParcelNumberClient();
        List<HoldingPlaceHasParcelNumberModel> model = holdingPlaceHasParcelNumberClient.findAllHoldingPlaceHasParcelNumbersById_JSON(List.class, holdingPlaceId);
        holdingPlaceHasParcelNumberClient.close();
        return model;
    }
    
    public String getAddressByHoldingPlaceId(String holdingPlaceId){
        HoldingPlaceModel holdingPlace = getHoldingPlaceByHoldingPlaceId(String.valueOf(holdingPlaceId));
        
        String address = "(" + holdingPlace.getCountryIso2() + ") " +
                  getCountyName(holdingPlace.getCountyId()) + " " +
                  getCityPostalCode(holdingPlace.getCityId()) + " " +
                  getCityName(holdingPlace.getCityId()) + " " +
                  holdingPlace.getStreet();
        
        return address;
    }
    
    public HoldingPlaceModel getHoldingPlaceByHoldingPlaceId(String holdingPlaceId){
        holdingPlaceClient = new HoldingPlaceClient();
        HoldingPlaceModel model = holdingPlaceClient.find_JSON(HoldingPlaceModel.class, holdingPlaceId);
        holdingPlaceClient.close();
        return model;
    }
    
    public String getSpeciesName(String speciesId){
        speciesClient = new SpeciesClient();
        SpeciesModel model = speciesClient.find_JSON(SpeciesModel.class, speciesId);
        speciesClient.close();
        return model.getName();
    }
    
    public String getCapacityTypeName(String capacityTypeId){
        capacityTypeClient = new CapacityTypeClient();
        CapacityTypeModel model = capacityTypeClient.find_JSON(CapacityTypeModel.class, capacityTypeId);
        capacityTypeClient.close();
        return model.getName();
    }
    
    public String getUserNameByUserId(String userId){
        userClient = new UserClient();
        UserModel model = userClient.find_JSON(UserModel.class, userId);
        userClient.close();
        
        return model.getLastName() + " " + model.getFirstName();
    }
    
    public String getDateFormat(long date){
        if(date == 0){
            return ("-");
        }
        
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy.MM.dd. HH:mm");
        Date correctDate = new Date(date);
        return formatter.format(correctDate);
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
    
    public String getCityName(int id){
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

    public HoldingPlaceModel getSearchedHoldingPlace() {
        return searchedHoldingPlace;
    }

    public String getSearchedId() {
        return searchedId;
    }
    
    public void searchHoldingPlaceWithId(String id){
        if(id != null){
            this.searchedId = id;
            searchHoldingPlace();
        }
    }
    
    public void searchHoldingPlace(){
        if(this.searchedId == null){
            return;
        }
        
        holdingPlaceClient = new HoldingPlaceClient();
        this.searchedHoldingPlace = holdingPlaceClient.find_JSON(HoldingPlaceModel.class, this.searchedId);
        holdingPlaceClient.close();
    }
}
