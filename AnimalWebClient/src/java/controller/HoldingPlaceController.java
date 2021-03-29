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
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import model.AnimalHasDiseasesModel;
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
import org.json.JSONObject;

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
    private List<Integer> breedingIds;
    
    private HoldingPlaceModel newHoldingPlace = new HoldingPlaceModel();
    private List<HoldingPlaceHasCapacityModel> newHoldingPlaceHasCapacity = new ArrayList<>();
    private List<HoldingPlaceHasParcelNumberModel> newHoldingPlaceHasParcelNumber = new ArrayList<>();
    private List<HoldingPlaceHasSpeciesModel> newHoldingPlaceHasSpecies = new ArrayList<>();
    private String postal_code;
    
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
        this.breedingIds = new ArrayList<>();
        this.breedingIds = getBreedingIdsByUserId();
        
        List<String> holdingPlaceIds = new ArrayList<>();
        for(int i=0; i<this.breedingIds.size(); i++){
            String id = String.valueOf(getHoldingPlaceHasBreedingByBreedingId(String.valueOf(this.breedingIds.get(i))).getHoldingPlaceId());
            if(!holdingPlaceIds.contains(id)){
                holdingPlaceIds.add(id);
            }
        }
        
        holdingPlaceClient = new HoldingPlaceClient();
        List<HoldingPlaceModel> holdingPlaces = new ArrayList<>();
        for(int i=0; i<holdingPlaceIds.size(); i++){
           HoldingPlaceModel model = holdingPlaceClient.find_JSON(HoldingPlaceModel.class, holdingPlaceIds.get(i));
           holdingPlaces.add(model);
        }
        
        return holdingPlaces;
    }
    
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
        
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy.MM.dd.");
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
    
    public List<CityModel> getCities(String postal_code){
        if(postal_code!=null && !postal_code.isEmpty()){
            cityClient = new CityClient();
            List<CityModel> cities = (List<CityModel>)cityClient.findAll_withPostalCode_JSON(List.class, postal_code);
            cityClient.close();
            return cities;
        }else{
            return null;
        }
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
    
    public List<CapacityTypeModel> getAllCapacityType(){
        capacityTypeClient = new CapacityTypeClient();
        List<CapacityTypeModel> capacities = (List<CapacityTypeModel>) capacityTypeClient.findAll_JSON(List.class);
        capacityTypeClient.close();
        return capacities;
    }
    
    public List<String> getAllUserIdByVetRole(){
        userClient = new UserClient();
        List<String> ids = userClient.getAllUserIdByRole_JSON(List.class, "2");
        ids.addAll(userClient.getAllUserIdByRole_JSON(List.class, "1"));
        userClient.close();
        return ids;
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

    public List<HoldingPlaceHasCapacityModel> getNewHoldingPlaceHasCapacity() {
        if(newHoldingPlaceHasCapacity.isEmpty()){
            addNewHoldingPlaceHasCapacity();
        }
        return newHoldingPlaceHasCapacity;
    }

    public void addNewHoldingPlaceHasCapacity() {
        HoldingPlaceHasCapacityModel model = new HoldingPlaceHasCapacityModel();
        this.newHoldingPlaceHasCapacity.add(model);
    }
    
    public void removeNewHoldingPlaceHasCapacity(HoldingPlaceHasCapacityModel model){
        this.newHoldingPlaceHasCapacity.remove(model);
    }

    public List<HoldingPlaceHasParcelNumberModel> getNewHoldingPlaceHasParcelNumber() {
        if(newHoldingPlaceHasParcelNumber.isEmpty()){
            addNewHoldingPlaceHasParcelNumber();
        }
        return newHoldingPlaceHasParcelNumber;
    }

    public void addNewHoldingPlaceHasParcelNumber() {
        HoldingPlaceHasParcelNumberModel model = new HoldingPlaceHasParcelNumberModel();
        this.newHoldingPlaceHasParcelNumber.add(model);
    }
    
    public void removeNewHoldingPlaceHasParcelNumber(HoldingPlaceHasParcelNumberModel model){
        this.newHoldingPlaceHasParcelNumber.remove(model);
    }

    public List<HoldingPlaceHasSpeciesModel> getNewHoldingPlaceHasSpecies() {
        if(newHoldingPlaceHasSpecies.isEmpty()){
            addNewHoldingPlaceHasSpecies();
        }
        return newHoldingPlaceHasSpecies;
    }

    public void addNewHoldingPlaceHasSpecies(){
        HoldingPlaceHasSpeciesModel model = new HoldingPlaceHasSpeciesModel();
        this.newHoldingPlaceHasSpecies.add(model);
    }
    
    public void removeNewHoldingPlaceHasSpecies(HoldingPlaceHasSpeciesModel model){
        this.newHoldingPlaceHasSpecies.remove(model);
    }

    public String getPostal_code() {
        return postal_code;
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
    
    public void saveNewHoldingPlace(){
        
        //Ellenőrzés hiányzik!
        
        this.newHoldingPlace.setIsActive(false);
        holdingPlaceClient = new HoldingPlaceClient();
        holdingPlaceClient.create_JSON(this.newHoldingPlace);
        holdingPlaceClient.close();
        
        holdingPlaceHasSpeciesClient = new HoldingPlaceHasSpeciesClient();
        for(int i=0; i < newHoldingPlaceHasSpecies.size(); i++){
            newHoldingPlaceHasSpecies.get(i).setHoldingPlaceId(this.newHoldingPlace.getId());

            String jsonString = new JSONObject()
                                .put("holdingPlaceId", newHoldingPlaceHasSpecies.get(i).getHoldingPlaceId())
                                .put("speciesId", newHoldingPlaceHasSpecies.get(i).getSpeciesId())
                                .put("startDate", newHoldingPlaceHasSpecies.get(i).getStartDate())
                                .put("utilization", newHoldingPlaceHasSpecies.get(i).getUtilization())
                                .toString();

            holdingPlaceHasSpeciesClient.create_JSON(jsonString);
        }
        holdingPlaceHasSpeciesClient.close();
        
        holdingPlaceHasParcelNumberClient = new HoldingPlaceHasParcelNumberClient();
        for(int i=0; i < newHoldingPlaceHasParcelNumber.size(); i++){
            newHoldingPlaceHasParcelNumber.get(i).setHoldingPlaceId(this.newHoldingPlace.getId());
            
            String jsonString = new JSONObject()
                                .put("holdingPlaceId", newHoldingPlaceHasParcelNumber.get(i).getHoldingPlaceId())
                                .put("cityId", newHoldingPlaceHasParcelNumber.get(i).getCityId())
                                .put("parcelNumber", newHoldingPlaceHasParcelNumber.get(i).getParcelNumber())
                                .toString();
            holdingPlaceHasParcelNumberClient.create_JSON(jsonString);
        }
        holdingPlaceHasParcelNumberClient.close();
        
        holdingPlaceHasCapacityClient = new HoldingPlaceHasCapacityClient();
        for(int i=0; i < newHoldingPlaceHasCapacity.size(); i++){
            newHoldingPlaceHasCapacity.get(i).setHoldingPlaceId(this.newHoldingPlace.getId());

            String jsonString = new JSONObject()
                                .put("holdingPlaceId", newHoldingPlaceHasCapacity.get(i).getHoldingPlaceId())
                                .put("capacityType", newHoldingPlaceHasCapacity.get(i).getCapacityType())
                                .put("size", newHoldingPlaceHasCapacity.get(i).getSize())
                                .put("startDate", newHoldingPlaceHasCapacity.get(i).getStartDate())
                                .toString();

            holdingPlaceHasCapacityClient.create_JSON(jsonString);
        }
        holdingPlaceHasCapacityClient.close();
    }
    
    public void saveModifiedHoldingPlace(){
        
        //Ellenőrzés hiányzik!
        
        this.newHoldingPlace.setIsActive(false);
        holdingPlaceClient = new HoldingPlaceClient();
        holdingPlaceClient.edit_JSON(this.newHoldingPlace, String.valueOf(this.newHoldingPlace.getId()));
        holdingPlaceClient.close();
        
        holdingPlaceHasSpeciesClient = new HoldingPlaceHasSpeciesClient();
        for(int i=0; i < newHoldingPlaceHasSpecies.size(); i++){
            newHoldingPlaceHasSpecies.get(i).setHoldingPlaceId(this.newHoldingPlace.getId());

            String jsonString = new JSONObject()
                                .put("id", newHoldingPlaceHasSpecies.get(i).getId())
                                .put("holdingPlaceId", newHoldingPlaceHasSpecies.get(i).getHoldingPlaceId())
                                .put("speciesId", newHoldingPlaceHasSpecies.get(i).getSpeciesId())
                                .put("startDate", newHoldingPlaceHasSpecies.get(i).getStartDate())
                                .put("utilization", newHoldingPlaceHasSpecies.get(i).getUtilization())
                                .toString();

            if(newHoldingPlaceHasSpecies.get(i).getId() == null){
                holdingPlaceHasSpeciesClient.create_JSON(jsonString);
            }else{
                holdingPlaceHasSpeciesClient.edit_JSON(jsonString, String.valueOf(newHoldingPlaceHasSpecies.get(i).getId()));
            }
        }
        holdingPlaceHasSpeciesClient.close();
        
        holdingPlaceHasParcelNumberClient = new HoldingPlaceHasParcelNumberClient();
        for(int i=0; i < newHoldingPlaceHasParcelNumber.size(); i++){
            newHoldingPlaceHasParcelNumber.get(i).setHoldingPlaceId(this.newHoldingPlace.getId());
            
            String jsonString = new JSONObject()
                                .put("id", newHoldingPlaceHasParcelNumber.get(i).getId())
                                .put("holdingPlaceId", newHoldingPlaceHasParcelNumber.get(i).getHoldingPlaceId())
                                .put("cityId", newHoldingPlaceHasParcelNumber.get(i).getCityId())
                                .put("parcelNumber", newHoldingPlaceHasParcelNumber.get(i).getParcelNumber())
                                .toString();
            
            if(newHoldingPlaceHasParcelNumber.get(i).getId() == null){
                holdingPlaceHasParcelNumberClient.create_JSON(jsonString);
            }else{
                holdingPlaceHasParcelNumberClient.edit_JSON(jsonString, String.valueOf(newHoldingPlaceHasParcelNumber.get(i).getId()));
            }
        }
        holdingPlaceHasParcelNumberClient.close();
        
        holdingPlaceHasCapacityClient = new HoldingPlaceHasCapacityClient();
        for(int i=0; i < newHoldingPlaceHasCapacity.size(); i++){
            newHoldingPlaceHasCapacity.get(i).setHoldingPlaceId(this.newHoldingPlace.getId());

            String jsonString = new JSONObject()
                                .put("id", newHoldingPlaceHasCapacity.get(i).getId())
                                .put("holdingPlaceId", newHoldingPlaceHasCapacity.get(i).getHoldingPlaceId())
                                .put("capacityType", newHoldingPlaceHasCapacity.get(i).getCapacityType())
                                .put("size", newHoldingPlaceHasCapacity.get(i).getSize())
                                .put("startDate", newHoldingPlaceHasCapacity.get(i).getStartDate())
                                .toString();

            if(newHoldingPlaceHasCapacity.get(i).getId() == null && newHoldingPlaceHasCapacity.get(i).getStartDate() != 0){
                holdingPlaceHasCapacityClient.create_JSON(jsonString);
            }else if(newHoldingPlaceHasCapacity.get(i).getStartDate() != 0){
                holdingPlaceHasCapacityClient.edit_JSON(jsonString, String.valueOf(newHoldingPlaceHasCapacity.get(i).getId()));
            }
        }
        holdingPlaceHasCapacityClient.close();
    }
    
    public String loadEditPage(String holdingPlaceId){
        holdingPlaceClient = new HoldingPlaceClient();
        this.newHoldingPlace = holdingPlaceClient.find_JSON(HoldingPlaceModel.class, holdingPlaceId);
        holdingPlaceClient.close();
        setPostal_code(getPostalCodeByCityId());
        
        ObjectMapper mapper = new ObjectMapper();
        
        holdingPlaceHasSpeciesClient = new HoldingPlaceHasSpeciesClient();
        this.newHoldingPlaceHasSpecies = holdingPlaceHasSpeciesClient.findAllHoldingPlaceHasSpeciesById_JSON(List.class, holdingPlaceId);
        this.newHoldingPlaceHasSpecies = mapper.convertValue(newHoldingPlaceHasSpecies, new TypeReference<List<HoldingPlaceHasSpeciesModel>>(){});
        holdingPlaceHasSpeciesClient.close();
        
        holdingPlaceHasParcelNumberClient = new HoldingPlaceHasParcelNumberClient();
        this.newHoldingPlaceHasParcelNumber = holdingPlaceHasParcelNumberClient.findAllHoldingPlaceHasParcelNumbersById_JSON(List.class, holdingPlaceId);
        this.newHoldingPlaceHasParcelNumber = mapper.convertValue(newHoldingPlaceHasParcelNumber, new TypeReference<List<HoldingPlaceHasParcelNumberModel>>(){});
        holdingPlaceHasParcelNumberClient.close();
        setPostalCodesForParcelNumberModels(newHoldingPlaceHasParcelNumber);
        
        holdingPlaceHasCapacityClient = new HoldingPlaceHasCapacityClient();
        this.newHoldingPlaceHasCapacity = holdingPlaceHasCapacityClient.findAllHoldingPlaceHasCapacityById_JSON(List.class, holdingPlaceId);
        this.newHoldingPlaceHasCapacity = mapper.convertValue(newHoldingPlaceHasCapacity, new TypeReference<List<HoldingPlaceHasCapacityModel>>(){});
        holdingPlaceHasCapacityClient.close();
        
        ResetSearchedHoldingPlace();
        
        NavigationController c = new NavigationController();
        return c.holdingPlaceEdit();
    }
    
    private void ResetSearchedHoldingPlace(){
        this.searchedId =  "";
        this.searchedHoldingPlace = null;
    }
    
    public String getPostalCodeByCityId(){
        if(this.newHoldingPlace == null){
            return "";
        }
        
        cityClient = new CityClient();
        CityModel city = cityClient.find_JSON(CityModel.class, String.valueOf(newHoldingPlace.getCityId()));
        cityClient.close();
        
        return city.getPostalCode();
    }
    
    public void setPostalCodeByCityId(String postalCode){
        setPostal_code(postalCode);
    }
    
    private void setPostalCodesForParcelNumberModels(List<HoldingPlaceHasParcelNumberModel> list){
        cityClient = new CityClient();
        for(int i=0; i<list.size(); i++){
            CityModel city = cityClient.find_JSON(CityModel.class, String.valueOf(list.get(i).getCityId()));
            list.get(i).setPostalCode(city.getPostalCode());
        }
        cityClient.close();
    }
}
