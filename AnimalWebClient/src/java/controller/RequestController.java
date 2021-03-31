/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import client.CityClient;
import client.CountryClient;
import client.CountyClient;
import client.HoldingPlaceClient;
import client.RoleClient;
import client.UserClient;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import model.CityModel;
import model.CountryModel;
import model.CountyModel;
import model.HoldingPlaceModel;
import model.RoleModel;
import model.UserModel;

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
    
    List<UserModel> registrations = new ArrayList<>();
    private String searchedId;
    private UserModel searchedUser;

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
        if(registrations.isEmpty()){
            registrations = findUsersByIsActive(false);
        }
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
}
