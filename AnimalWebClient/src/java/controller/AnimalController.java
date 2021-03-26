/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import client.AnimalClient;
import client.AnimalDiseasesClient;
import client.AnimalHasDiseasesClient;
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
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import model.AnimalDiseasesModel;
import model.AnimalHasDiseasesModel;
import model.UserModel;
import org.json.JSONObject;

/**
 *
 * @author Patrik
 */
@ManagedBean(name="animalController", eager = true)
@SessionScoped
public class AnimalController {
    private String openedId = "-1";
    private String id;
    private String userName;
    private List<String> breedingIds;
    private List<BreedingHasAnimalModel> breedingHasAnimals;
    private List<HoldingPlaceHasBreedingModel> holdingPlaceHasBreedings;
    private List<HoldingPlaceModel> holdingPlaces;
    
    private String searchedEarTag;
    private AnimalModel searchedAnimal;
    
    private AnimalModel newAnimal = new AnimalModel();
    private List<BreedingHasAnimalModel> newBreedingHasAnimal = new ArrayList<>();
    private List<AnimalHasDiseasesModel> newAnimalHasDiseases = new ArrayList<>();
    
    private UserHasBreedingClient userHasBreedingClient;
    private BreedingHasAnimalClient breedingHasAnimalClient;
    private AnimalHasDiseasesClient animalHasDiseasesClient;
    private HoldingPlaceHasBreedingClient holdingPlaceHasBreedingClient;
    private HoldingPlaceClient holdingPlaceClient;
    private AnimalClient animalClient;
    private SpeciesClient speciesClient;
    private BreedClient breedClient;
    private ColorClient colorClient;
    private CalvingClient calvingClient;
    private CountryClient countryClient;
    private CountyClient countyClient;
    private CityClient cityClient;
    private UserClient userClient;
    private AnimalDiseasesClient animalDiseasesClient;
    
    public AnimalController(){
    }
    
    private List<String> getBreedingIdsByUserId(){
        id = Session.getUserId();
        userHasBreedingClient = new UserHasBreedingClient();
        List<String> breedings = (List<String>) userHasBreedingClient.findAllBreedingIdByUserId_JSON(List.class, id);
        userHasBreedingClient.close();
        return breedings;
    }
    
    private int getUserIdByBreedingId(String breedingId){
        userHasBreedingClient = new UserHasBreedingClient();
        int model = userHasBreedingClient.findUserIdByBreedingId_JSON(Integer.class, breedingId);
        userHasBreedingClient.close();
        return model;
    }
    
    public List<BreedingHasAnimalModel> getAllBreedingsHasAnimalByBreedingIds(){
        breedingHasAnimalClient = new BreedingHasAnimalClient();
        
        List<BreedingHasAnimalModel> breedings = new ArrayList<>();
        for(int i=0; i<this.breedingIds.size(); i++){
            List<BreedingHasAnimalModel> list = (List<BreedingHasAnimalModel>)breedingHasAnimalClient.findAllEarTagsByBreedingId_JSON(List.class, breedingIds.get(i));
            breedings.addAll(list);
        }
        breedingHasAnimalClient.close();
        
        ObjectMapper mapper = new ObjectMapper();
        List<BreedingHasAnimalModel> breedingHasAnimalList = mapper.convertValue(breedings, new TypeReference<List<BreedingHasAnimalModel>>(){});
        return breedingHasAnimalList;
    }
    
    public List<BreedingHasAnimalModel> getAllBreedingsHasAnimalByEarTag(String earTag){
        breedingHasAnimalClient = new BreedingHasAnimalClient();
        List<BreedingHasAnimalModel> list = (List<BreedingHasAnimalModel>)breedingHasAnimalClient.findAllBreedingIdsByEarTag_JSON(List.class, earTag);
        breedingHasAnimalClient.close();
        
        ObjectMapper mapper = new ObjectMapper();
        List<BreedingHasAnimalModel> breedingHasAnimalList = mapper.convertValue(list, new TypeReference<List<BreedingHasAnimalModel>>(){});
        return breedingHasAnimalList;
    }
    
    public List<AnimalHasDiseasesModel> getAllAnimalHasDiseasesByEarTags(String earTag){
        animalHasDiseasesClient = new AnimalHasDiseasesClient();
        List<AnimalHasDiseasesModel> list = (List<AnimalHasDiseasesModel>)animalHasDiseasesClient.findByAnimalEarTag_JSON(List.class, earTag);
        animalHasDiseasesClient.close();
        
        ObjectMapper mapper = new ObjectMapper();
        List<AnimalHasDiseasesModel> animalHasDiseasesList = mapper.convertValue(list, new TypeReference<List<AnimalHasDiseasesModel>>(){});
        return animalHasDiseasesList;
    }
    
    private List<HoldingPlaceHasBreedingModel> getHoldingPlacesByBreedingId(){
        holdingPlaceHasBreedingClient = new HoldingPlaceHasBreedingClient();
        List<HoldingPlaceHasBreedingModel> breedings = new ArrayList<>();
        for(int i=0; i<this.breedingIds.size(); i++){
            breedings.add(holdingPlaceHasBreedingClient.findHoldingPlaceByBreedingId_JSON(HoldingPlaceHasBreedingModel.class, this.breedingIds.get(i)));
        }
        holdingPlaceHasBreedingClient.close();
        return breedings;
    }
    
    private List<HoldingPlaceModel> getHoldingPlaces(){
        holdingPlaceClient = new HoldingPlaceClient();
        List<HoldingPlaceModel> holdingPlaces = new ArrayList<>();
        for(int i=0; i<this.holdingPlaceHasBreedings.size(); i++){
            holdingPlaces.add(holdingPlaceClient.find_JSON(HoldingPlaceModel.class, String.valueOf(this.holdingPlaceHasBreedings.get(i).getHoldingPlaceId())));
        }
        return holdingPlaces;
    }
    
    public List<AnimalModel> getAllAnimal(){
        //this.breedingIds = getBreedingIdsByUserId();
        //this.breedingHasAnimals = getAllBreedingsHasAnimalByBreedingIds();
        ////this.holdingPlaceHasBreedings = getHoldingPlacesByBreedingId();
        ////this.holdingPlaces = getHoldingPlaces();
        
        animalClient = new AnimalClient();
//        List<AnimalModel> animals = new ArrayList<>();
//        for(int i=0; i<this.breedingHasAnimals.size(); i++){
//            AnimalModel animal = animalClient.find_JSON(AnimalModel.class, this.breedingHasAnimals.get(i).getAnimalEarTag());
//            animals.add(animal);
//        }
//        return animals;
        this.id = Session.getUserId();
        List<AnimalModel> animals = animalClient.findAnimalsByUserId_JSON(List.class, this.id);
        animalClient.close();
        return animals;
        
    }
    
    public String getDateFormat(long date){
        if(date == 0){
            return ("-");
        }
        
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy.MM.dd. HH:mm");
        Date correctDate = new Date(date);
        return formatter.format(correctDate);
    }
    
    public String getSpeciesName(String id){
        speciesClient = new SpeciesClient();
        SpeciesModel model = speciesClient.find_JSON(SpeciesModel.class, id);
        speciesClient.close();
        return model.getName();
    }
    
    public String getBreedName(String id){
        breedClient = new BreedClient();
        BreedModel model = breedClient.find_JSON(BreedModel.class, id);
        breedClient.close();
        return model.getName();
    }
    
    public String getColorName(String id){
        colorClient = new ColorClient();
        ColorModel model = colorClient.find_JSON(ColorModel.class, id);
        colorClient.close();
        return model.getName();
    }
    
    public String getCalvingName(String id){
        calvingClient = new CalvingClient();
        CalvingModel model = calvingClient.find_JSON(CalvingModel.class, id);
        calvingClient.close();
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
            if(this.breedingHasAnimals.get(i).getAnimalEarTag().equals(earTag) && this.breedingHasAnimals.get(i).getEndDate() == 0){
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
        /*int breedingId = -1;
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
        }*/
        /*
        String address = null;
        for(int i=0; i<this.holdingPlaces.size(); i++){
            if(this.holdingPlaces.get(i).getId().equals(holdingplaceId)){
                address = "(" + this.holdingPlaces.get(i).getCountryIso2() + ") " +
                          getCountyName(this.holdingPlaces.get(i).getCountyId()) + " " +
                          getCityPostalCode(this.holdingPlaces.get(i).getCityId()) + " " +
                          getCityName(this.holdingPlaces.get(i).getCityId()) + " " +
                          this.holdingPlaces.get(i).getStreet();
            }
        }*/
        String address = null;
        holdingPlaceClient = new HoldingPlaceClient();
        List<HoldingPlaceModel> holdingPlaces = holdingPlaceClient.findByAnimalEarTag_JSON(List.class, earTag);
        holdingPlaceClient.close();
        address = "(" + this.holdingPlaces.get(0).getCountryIso2() + ") " +
                          getCountyName(this.holdingPlaces.get(0).getCountyId()) + " " +
                          getCityPostalCode(this.holdingPlaces.get(0).getCityId()) + " " +
                          getCityName(this.holdingPlaces.get(0).getCityId()) + " " +
                          this.holdingPlaces.get(0).getStreet();
        
        return address;
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
    
    public String getUserNameByUserId(String userId){
        userClient = new UserClient();
        UserModel model = userClient.find_JSON(UserModel.class, userId);
        userClient.close();
        return model.getLastName() + " " + model.getFirstName();
    }
    
    public String getUserNameByAnimalEarTag(String earTag){
        int breedingId = getBreedingIdByAnimal(earTag);
        int userId = getUserIdByBreedingId(String.valueOf(breedingId));
        return getUserNameByUserId(String.valueOf(userId));
    }
    
    public String getAnimalDiseasesName(String id){
        animalDiseasesClient = new AnimalDiseasesClient();
        AnimalDiseasesModel model = animalDiseasesClient.find_JSON(AnimalDiseasesModel.class, id);
        animalDiseasesClient.close();
        return model.getName();
    }

    public AnimalModel getSearchedAnimal() {
        return searchedAnimal;
    }

    public String getSearchedEarTag() {
        return searchedEarTag;
    }

    public String getOpenedId() {
        return openedId;
    }

    public void setOpenedId(String openedId) {
        this.openedId = openedId;
    }

    public void setSearchedEarTag(String searchedEarTag) {
        if(searchedEarTag.isEmpty() || !searchedEarTag.matches("^[0-9]+$")){
            FacesContext.getCurrentInstance().addMessage("search:ear_tag", new FacesMessage("Csak számot tartalmazhat!"));
        }
        this.searchedEarTag = searchedEarTag;
    }
    
    public void searchAnimalWithEarTag(String earTag){
        if(earTag != null){
            this.searchedEarTag = earTag;
            searchAnimal();
        }
    }
    
    public void searchAnimal(){
        if(this.searchedEarTag == null){
            return;
        }
        
        animalClient = new AnimalClient();
        searchedAnimal = animalClient.find_JSON(AnimalModel.class, this.searchedEarTag);
        animalClient.close();
        
        this.breedingHasAnimals = getAllBreedingsHasAnimalByEarTag(this.searchedEarTag);
        
        this.breedingIds = new ArrayList<>();
        for(int i=0; i<this.breedingHasAnimals.size(); i++){
            if(this.breedingHasAnimals.get(i).getEndDate() == 0){
                this.breedingIds.add(String.valueOf(this.breedingHasAnimals.get(i).getBreedingId()));
            }
        }
        
        this.holdingPlaceHasBreedings = getHoldingPlacesByBreedingId();
        this.holdingPlaces = getHoldingPlaces();
        
    }
    
    public void saveNewAnimal(){
        
        this.newAnimal.setIsAccepted(false);
        
        animalClient = new AnimalClient();
        String jsonAnimal = new JSONObject()
                            .put("earTag", this.newAnimal.getEarTag())
                            .put("motherId", this.newAnimal.getMotherId())
                            .put("name", this.newAnimal.getName())
                            .put("sex", this.newAnimal.isSex())
                            .put("birthdate", this.newAnimal.getBirthdate())
                            .put("deathdate", this.newAnimal.getDeathdate())
                            .put("speciesId", this.newAnimal.getSpeciesId())
                            .put("breedId", this.newAnimal.getBreedId())
                            .put("colorId", this.newAnimal.getColorId())
                            .put("twinning", this.newAnimal.isTwinning())
                            .put("calvingId", this.newAnimal.getCalvingId())
                            .put("calvingWeight", this.newAnimal.getCalvingWeight())
                            .put("isAccepted", this.newAnimal.isIsAccepted())
                            .put("inseminationDate", this.newAnimal.getInseminationDate())
                            .toString();
                
        animalClient.create_JSON(jsonAnimal);
        animalClient.close();
        
        breedingHasAnimalClient = new BreedingHasAnimalClient();
        for(int i=0; i < this.newBreedingHasAnimal.size(); i++){
            if(newBreedingHasAnimal.get(i).getStartDate()!=0){
                String jsonString = new JSONObject()
                                    .put("breedingId", newBreedingHasAnimal.get(i).getBreedingId())
                                    .put("animalEarTag", newAnimal.getEarTag())
                                    .put("startDate", newBreedingHasAnimal.get(i).getStartDate())
                                    .put("endDate", newBreedingHasAnimal.get(i).getEndDate())
                                    .toString();
                breedingHasAnimalClient.create_JSON(jsonString);
            }
        }
        breedingHasAnimalClient.close();
        
        animalHasDiseasesClient = new AnimalHasDiseasesClient();
        for(int i=0; i < this.newAnimalHasDiseases.size(); i++){
            if(newAnimalHasDiseases.get(i).getStartDate() != 0){
                String jsonString = new JSONObject()
                                    .put("animalDiseasesId", newAnimalHasDiseases.get(i).getAnimalDiseasesId())
                                    .put("animalEarTag", newAnimal.getEarTag())
                                    .put("startDate", newAnimalHasDiseases.get(i).getStartDate())
                                    .put("endDate", newAnimalHasDiseases.get(i).getEndDate())
                                    .put("comment", newAnimalHasDiseases.get(i).getComment())
                                    .toString();
                animalHasDiseasesClient.create_JSON(jsonString);
            }
        }
        animalHasDiseasesClient.close();
    }
    
    public AnimalModel animalColor = new AnimalModel();

    public AnimalModel getAnimalColor() {
        return animalColor;
    }

    public void setAnimalColor(AnimalModel animalColor) {
        this.animalColor = animalColor;
    }
    
    public void saveModifiedAnimal(){
        this.newAnimal.setIsAccepted(false);
        
        animalClient = new AnimalClient();
        String jsonAnimal = new JSONObject()
                            .put("earTag", this.newAnimal.getEarTag())
                            .put("motherId", this.newAnimal.getMotherId())
                            .put("name", this.newAnimal.getName())
                            .put("sex", this.newAnimal.isSex())
                            .put("birthdate", this.newAnimal.getBirthdate())
                            .put("deathdate", this.newAnimal.getDeathdate())
                            .put("speciesId", this.newAnimal.getSpeciesId())
                            .put("breedId", this.newAnimal.getBreedId())
                            .put("colorId", this.newAnimal.getColorId())
                            .put("twinning", this.newAnimal.isTwinning())
                            .put("calvingId", this.newAnimal.getCalvingId())
                            .put("calvingWeight", this.newAnimal.getCalvingWeight())
                            .put("isAccepted", this.newAnimal.isIsAccepted())
                            .put("inseminationDate", this.newAnimal.getInseminationDate())
                            .toString();
                
        animalClient.edit_JSON(jsonAnimal, String.valueOf(newAnimal.getEarTag()));
        animalClient.close();
        
        breedingHasAnimalClient = new BreedingHasAnimalClient();
        for(int i=0; i < this.newBreedingHasAnimal.size(); i++){
            if(newBreedingHasAnimal.get(i).getStartDate()!=0){
                String jsonString = new JSONObject()
                                    .put("id", newBreedingHasAnimal.get(i).getId())
                                    .put("breedingId", newBreedingHasAnimal.get(i).getBreedingId())
                                    .put("animalEarTag", newAnimal.getEarTag())
                                    .put("startDate", newBreedingHasAnimal.get(i).getStartDate())
                                    .put("endDate", newBreedingHasAnimal.get(i).getEndDate())
                                    .toString();
                breedingHasAnimalClient.edit_JSON(jsonString, String.valueOf(newBreedingHasAnimal.get(i).getId()));
            }
        }
        breedingHasAnimalClient.close();

        animalHasDiseasesClient = new AnimalHasDiseasesClient();
        for(int i=0; i < this.newAnimalHasDiseases.size(); i++){
            if(newAnimalHasDiseases.get(i).getStartDate() != 0){
                String jsonString = new JSONObject()
                                    .put("id", newAnimalHasDiseases.get(i).getId())
                                    .put("animalDiseasesId", newAnimalHasDiseases.get(i).getAnimalDiseasesId())
                                    .put("animalEarTag", newAnimal.getEarTag())
                                    .put("startDate", newAnimalHasDiseases.get(i).getStartDate())
                                    .put("endDate", newAnimalHasDiseases.get(i).getEndDate())
                                    .put("comment", newAnimalHasDiseases.get(i).getComment())
                                    .toString();
                animalHasDiseasesClient.edit_JSON(jsonString, String.valueOf(newAnimalHasDiseases.get(i).getId()));
            }
        }
        animalHasDiseasesClient.close();
    }
    
    public String loadEditPage(String earTag){
        
        animalClient = new AnimalClient();
        this.newAnimal = animalClient.find_JSON(AnimalModel.class, earTag);
        animalClient.close();
        ResetSearchedAnimal();
        
        this.newBreedingHasAnimal = getAllBreedingsHasAnimalByEarTag(earTag);
        this.newAnimalHasDiseases = getAllAnimalHasDiseasesByEarTags(earTag);
        
        NavigationController c = new NavigationController();
        return c.animalEdit();
    }
    
    private void ResetSearchedAnimal(){
        this.searchedAnimal = null;
        this.searchedEarTag = "";
    }

    public AnimalModel getNewAnimal() {
        return newAnimal;
    }

    public void setNewAnimal(AnimalModel newAnimal) {
        this.newAnimal = newAnimal;
    }

    public List<BreedingHasAnimalModel> getNewBreedingHasAnimal() {
        if(this.newBreedingHasAnimal.isEmpty()){
            addNewBreedingHasAnimal();
        }
        return newBreedingHasAnimal;
    }

    public void addNewBreedingHasAnimal() {
        BreedingHasAnimalModel model = new BreedingHasAnimalModel();
        this.newBreedingHasAnimal.add(model);
    }
    
    public void removeNewBreedingHasAnimal(BreedingHasAnimalModel model){
        this.newBreedingHasAnimal.remove(model);
    }

    public List<AnimalHasDiseasesModel> getNewAnimalHasDiseases() {
        if(this.newAnimalHasDiseases.isEmpty()){
            addNewAnimalHasDiseases();
        }
        return newAnimalHasDiseases;
    }

    public void addNewAnimalHasDiseases() {
        AnimalHasDiseasesModel model = new AnimalHasDiseasesModel();
        this.newAnimalHasDiseases.add(model);
    }
    
    public void removeNewAnimalHasDiseases(AnimalHasDiseasesModel model){
        this.newAnimalHasDiseases.remove(model);
    }
    
    public List<SpeciesModel> getAllSpecies(){
        speciesClient = new SpeciesClient();
        List<SpeciesModel> model = speciesClient.findAll_JSON(List.class);
        speciesClient.close();
        return model;
    }
    
    public List<BreedModel> getAllBreed(){
        breedClient = new BreedClient();
        List<BreedModel> model = breedClient.findAll_JSON(List.class);
        breedClient.close();
        return model;
    }
    
    public List<ColorModel> getAllColor(){
        colorClient = new ColorClient();
        List<ColorModel> model = colorClient.findAll_JSON(List.class);
        colorClient.close();
        return model;
    }
    
    public List<CalvingModel> getAllCalving(){
        calvingClient = new CalvingClient();
        List<CalvingModel> model = calvingClient.findAll_JSON(List.class);
        calvingClient.close();
        return model;
    }
    
    public List<AnimalDiseasesModel> getAllAnimalDiseases(){
        animalDiseasesClient = new AnimalDiseasesClient();
        List<AnimalDiseasesModel> model = animalDiseasesClient.findAll_JSON(List.class);
        animalDiseasesClient.close();
        return model;
    }
}
