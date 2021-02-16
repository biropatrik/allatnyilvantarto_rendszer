/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import client.AnimalClient;
import client.BreedClient;
import client.BreedingHasAnimalClient;
import client.CalvingClient;
import client.CityClient;
import client.ColorClient;
import client.CountryClient;
import client.CountyClient;
import client.HoldingPlaceClient;
import client.HoldingPlaceHasBreedingClient;
import client.SpeciesClient;
import client.UserClient;
import client.UserHasBreedingClient;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import model.AnimalModel;
import model.BreedModel;
import model.BreedingHasAnimalModel;
import model.CalvingModel;
import model.CityModel;
import model.ColorModel;
import model.CountryModel;
import model.CountyModel;
import model.HoldingPlaceHasBreedingModel;
import model.HoldingPlaceModel;
import model.SpeciesModel;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import model.UserModel;

/**
 *
 * @author Patrik
 */
@ManagedBean(name="animalController", eager = true)
@SessionScoped
public class AnimalController {
    private String id;
    private String userName;
    private String openedId = "-1";
    private List<String> breedingIds;
    private List<BreedingHasAnimalModel> breedingHasAnimals;
    private List<HoldingPlaceHasBreedingModel> holdingPlaceHasBreedings;
    private List<HoldingPlaceModel> holdingPlaces;
    
    public AnimalController(){
        this.userName = getUserNameFromService();
    }
    
    private List<String> getBreedingIdsByUserId(){
        id = Session.getUserId();
        UserHasBreedingClient client = new UserHasBreedingClient();
        List<String> breedings = (List<String>) client.findAllBreedingIdByUserId_JSON(List.class, id);
        client.close();
        return breedings;
    }
    
    public List<BreedingHasAnimalModel> getAllBreedingsHasAnimalByBreedingIds(){
        BreedingHasAnimalClient client = new BreedingHasAnimalClient();
        
        List<BreedingHasAnimalModel> breedings = new ArrayList<>();
        for(int i=0; i<this.breedingIds.size(); i++){
            List<BreedingHasAnimalModel> list = (List<BreedingHasAnimalModel>)client.findAllEarTagsByBreedingId_JSON(List.class, breedingIds.get(i));
            breedings.addAll(list);
        }
        client.close();
        
        ObjectMapper mapper = new ObjectMapper();
        List<BreedingHasAnimalModel> breedingHasAnimalList = mapper.convertValue(breedings, new TypeReference<List<BreedingHasAnimalModel>>(){});
        return breedingHasAnimalList;
    }
    
    private List<HoldingPlaceHasBreedingModel> getHoldingPlacesByBreedingId(){
        HoldingPlaceHasBreedingClient client = new HoldingPlaceHasBreedingClient();
        List<HoldingPlaceHasBreedingModel> breedings = new ArrayList<>();
        for(int i=0; i<this.breedingIds.size(); i++){
            breedings.add(client.findHoldingPlaceByBreedingId_JSON(HoldingPlaceHasBreedingModel.class, this.breedingIds.get(i)));
        }
        client.close();
        return breedings;
    }
    
    private List<HoldingPlaceModel> getHoldingPlaces(){
        HoldingPlaceClient client = new HoldingPlaceClient();
        List<HoldingPlaceModel> holdingPlaces = new ArrayList<>();
        for(int i=0; i<this.holdingPlaceHasBreedings.size(); i++){
            holdingPlaces.add(client.find_JSON(HoldingPlaceModel.class, String.valueOf(this.holdingPlaceHasBreedings.get(i).getHoldingPlaceId())));
        }
        return holdingPlaces;
    }
    
    public List<AnimalModel> getAllAnimal(){
        this.breedingIds = getBreedingIdsByUserId();
        this.breedingHasAnimals = getAllBreedingsHasAnimalByBreedingIds();
        this.holdingPlaceHasBreedings = getHoldingPlacesByBreedingId();
        this.holdingPlaces = getHoldingPlaces();
        
        AnimalClient client = new AnimalClient();
        List<AnimalModel> animals = new ArrayList<>();
        for(int i=0; i<this.breedingHasAnimals.size(); i++){
            AnimalModel animal = client.find_JSON(AnimalModel.class, this.breedingHasAnimals.get(i).getAnimalEarTag());
            animals.add(animal);
        }
        return animals;
    }
    
    public String getDateFormat(long date){
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy.MM.dd. HH:mm");
        Date correctDate = new Date(date);
        return formatter.format(correctDate);
    }
    
    public String getSpeciesName(String id){
        SpeciesClient client = new SpeciesClient();
        SpeciesModel model = client.find_JSON(SpeciesModel.class, id);
        client.close();
        return model.getName();
    }
    
    public String getBreedName(String id){
        BreedClient client = new BreedClient();
        BreedModel model = client.find_JSON(BreedModel.class, id);
        client.close();
        return model.getName();
    }
    
    public String getColorName(String id){
        ColorClient client = new ColorClient();
        ColorModel model = client.find_JSON(ColorModel.class, id);
        client.close();
        return model.getName();
    }
    
    public String getCalvingName(String id){
        CalvingClient client = new CalvingClient();
        CalvingModel model = client.find_JSON(CalvingModel.class, id);
        client.close();
        return model.getName();
    }
    
    public String getSexName(boolean id){
        return id ? "Hímivarú" : "Nőivarú";
    }
    
    public String getTwinningName(boolean id){
        return id ? "Igen" : "Nem";
    }
    
    public int getBreedingIdByAnimal(String earTag){
        for(int i=0; i<this.breedingHasAnimals.size(); i++){
            if(this.breedingHasAnimals.get(i).getAnimalEarTag().equals(earTag)){
                return this.breedingHasAnimals.get(i).getBreedingId();
            }
        }
        return -1;
    }
    
    public String getBreedingHasAnimalStartDate(String earTag){
        long startDate = 0;
        for(int i=0; i<this.breedingHasAnimals.size(); i++){
            if(this.breedingHasAnimals.get(i).getAnimalEarTag().equals(earTag)){
                startDate = this.breedingHasAnimals.get(i).getStartDate();
            }
        }
        return getDateFormat(startDate);
    }
    
    public String getBreedAddressByAnimal(String earTag){
        int breedingId = -1;
        for(int i=0; i<this.breedingHasAnimals.size(); i++){
            if(this.breedingHasAnimals.get(i).getAnimalEarTag().equals(earTag)){
                breedingId = this.breedingHasAnimals.get(i).getBreedingId();
            }
        }
        
        int holdingplaceId = -1;
        for(int i=0; i<this.holdingPlaceHasBreedings.size(); i++){
            if(this.holdingPlaceHasBreedings.get(i).getBreedingId() == breedingId){
                holdingplaceId = this.holdingPlaceHasBreedings.get(i).getHoldingPlaceId();
            }
        }
        
        String address = null;
        for(int i=0; i<this.holdingPlaces.size(); i++){
            if(this.holdingPlaces.get(i).getId().equals(holdingplaceId)){
                address = "(" + this.holdingPlaces.get(i).getCountryIso2() + ") " +
                          getCountyName(this.holdingPlaces.get(i).getCountyId()) + " " +
                          getCityPostalCode(this.holdingPlaces.get(i).getCityId()) + " " +
                          getCityName(this.holdingPlaces.get(i).getCityId()) + " " +
                          this.holdingPlaces.get(i).getStreet();
            }
        }
        return address;
    }
        
    private String getCountryName(String iso2){
        CountryClient client = new CountryClient();
        CountryModel country = client.find_JSON(CountryModel.class, iso2);
        client.close();
        return country.getName();
    }
    
    private String getCountyName(int id){
        CountyClient client = new CountyClient();
        CountyModel county = client.find_JSON(CountyModel.class, String.valueOf(id));
        client.close();
        if(county == null){
            return "Hiba történt!";
        }
        return county.getName();
    }
    
    private String getCityName(int id){
        CityClient client = new CityClient();
        CityModel city = client.find_JSON(CityModel.class, String.valueOf(id));
        client.close();
        if(city == null){
            return "Hiba történt!";
        }
        return city.getName();
    }
    
    private String getCityPostalCode(int id){
        CityClient client = new CityClient();
        CityModel city = client.find_JSON(CityModel.class, String.valueOf(id));
        client.close();
        if(city == null){
            return "Hiba történt!";
        }
        return city.getPostalCode();
    }
    
    private String getUserNameFromService(){
        id = Session.getUserId();
        UserClient client = new UserClient();
        UserModel model = client.find_JSON(UserModel.class, id);
        client.close();
        return model.getLastName() + " " + model.getFirstName();
    }

    public String getOpenedId() {
        return openedId;
    }

    public void setOpenedId(String openedId) {
        this.openedId = openedId;
    }

    public String getUserName() {
        if(userName == null){
            userName = getUserNameFromService();
        }
        return userName;
    }
}
