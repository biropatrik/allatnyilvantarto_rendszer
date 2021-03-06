/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

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
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
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
    
    private HoldingPlaceModel newHoldingPlace = new HoldingPlaceModel();
    private List<HoldingPlaceHasBreedingModel> newHoldingPlaceHasBreeding = new ArrayList<>();
    private List<HoldingPlaceHasCapacityModel> newHoldingPlaceHasCapacity = new ArrayList<>();
    private List<HoldingPlaceHasParcelNumberModel> newHoldingPlaceHasParcelNumber = new ArrayList<>();
    private List<HoldingPlaceHasSpeciesModel> newHoldingPlaceHasSpecies = new ArrayList<>();
    private String postal_code;
    private int speciesListCount = 0;
    
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
    
    public long getLongFormatFromDate(String date){
        if(date == null || date.equals("0")){
            return (0);
        }
        long milliseconds = 0;
        SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date d = f.parse(date);
            milliseconds = d.getTime();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        
        return milliseconds;
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
    
    public List<CountryModel> getCountries(){
        countryClient = new CountryClient();
        List<CountryModel> countries = (List<CountryModel>) countryClient.findAll_JSON(List.class);
        countryClient.close();
        return countries;
    }
    
    public List<CountyModel> getCounties(){
        countyClient = new CountyClient();
        List<CountyModel> counties = (List<CountyModel>) countyClient.findAll_JSON(List.class);
        countyClient.close();
        return counties;
    }
    
    public List<CityModel> getCities(){
        if(postal_code!=null && !postal_code.isEmpty()){
            cityClient = new CityClient();
            List<CityModel> cities = (List<CityModel>)cityClient.findAll_withPostalCode_JSON(List.class, postal_code);
            cityClient.close();
            return cities;
        }else{
            return null;
        }
    }
    
    public List<SpeciesModel> getAllSpecies(){
        speciesClient = new SpeciesClient();
        List<SpeciesModel> species = (List<SpeciesModel>) speciesClient.findAll_JSON(List.class);
        speciesClient.close();
        return species;
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

    public HoldingPlaceModel getNewHoldingPlace() {
        return newHoldingPlace;
    }

    public void setNewHoldingPlace(HoldingPlaceModel newHoldingPlace) {
        this.newHoldingPlace = newHoldingPlace;
    }

    public List<HoldingPlaceHasBreedingModel> getNewHoldingPlaceHasBreeding() {
        return newHoldingPlaceHasBreeding;
    }
    
    public void addNewHoldingPlaceHasBreeding(){
        HoldingPlaceHasBreedingModel model = new HoldingPlaceHasBreedingModel();
        this.newHoldingPlaceHasBreeding.add(model);
    }

    public List<HoldingPlaceHasCapacityModel> getNewHoldingPlaceHasCapacity() {
        return newHoldingPlaceHasCapacity;
    }

    public void setNewHoldingPlaceHasCapacity(List<HoldingPlaceHasCapacityModel> newHoldingPlaceHasCapacity) {
        this.newHoldingPlaceHasCapacity = newHoldingPlaceHasCapacity;
    }

    public List<HoldingPlaceHasParcelNumberModel> getNewHoldingPlaceHasParcelNumber() {
        return newHoldingPlaceHasParcelNumber;
    }

    public void setNewHoldingPlaceHasParcelNumber(List<HoldingPlaceHasParcelNumberModel> newHoldingPlaceHasParcelNumber) {
        this.newHoldingPlaceHasParcelNumber = newHoldingPlaceHasParcelNumber;
    }

    public List<HoldingPlaceHasSpeciesModel> getNewHoldingPlaceHasSpecies() {
        //List<SpeciesModel> species = getAllSpecies();
        
        //newHoldingPlaceHasSpecies.clear();
        /*for(int i=0; i < species.size(); i++){
            HoldingPlaceHasSpeciesModel newModel = new HoldingPlaceHasSpeciesModel();
            newModel.setSpeciesId(species.get(i).getId());
            newHoldingPlaceHasSpecies.add(newModel);
        }
        */
        
        /*for(int i=0; i < speciesListCount; i++){
            HoldingPlaceHasSpeciesModel newModel = new HoldingPlaceHasSpeciesModel();
            newHoldingPlaceHasSpecies.add(newModel);
        }*/
        if(newHoldingPlaceHasSpecies.isEmpty()){
            addNewHoldingPlaceHasSpecies();
        }
        
        return newHoldingPlaceHasSpecies;
    }

    public void addNewHoldingPlaceHasSpecies(){
        
        HoldingPlaceHasSpeciesModel model = new HoldingPlaceHasSpeciesModel();
        this.newHoldingPlaceHasSpecies.add(model);
        
        FacesContext.getCurrentInstance().addMessage("addHoldingPlace:postal_code", 
                new FacesMessage("Teszt: " + newHoldingPlaceHasSpecies.get(0).getStartDate()));
        
    }

    public String getPostal_code() {
        return postal_code;
    }

    public int getSpeciesListCount() {
        return speciesListCount;
    }

    public void setSpeciesListCount(int speciesListCount) {
        this.speciesListCount = speciesListCount;
    }
    
    public void setPostal_code(String postal_code) {
        
        if(postal_code == null || postal_code.isEmpty()){
            FacesContext.getCurrentInstance().addMessage("addHoldingPlace:postal_code", new FacesMessage("A mező kitöltése kötelező!"));
        }else if(!postal_code.matches("^[0-9]+$")){
            FacesContext.getCurrentInstance().addMessage("addHoldingPlace:postal_code", new FacesMessage("Csak számot tartalmazhat!"));
        }else{
            this.postal_code = postal_code;
        }
    }
}
