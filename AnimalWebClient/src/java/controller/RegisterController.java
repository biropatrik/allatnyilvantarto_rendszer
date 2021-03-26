/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import client.CityClient;
import client.CountryClient;
import client.CountyClient;
import client.UserClient;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.regex.Pattern;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import model.CityModel;
import model.CountryModel;
import model.CountyModel;
import model.Pairs;
import model.UserModel;

/**
 *
 * @author Patrik
 */
@ManagedBean(name="registerController", eager = true)
@SessionScoped
public class RegisterController {
    private String first_name;
    private String last_name;
    private String email;
    private String password;
    private String phoneNumber;
    private String countryIso2;
    private int countyId;
    private String postal_code;
    private int cityId;
    private String street;
    
    List<Pairs<String, Boolean>> validatePairs;

    public RegisterController() {
        this.validatePairs = new ArrayList<>();
        
        validatePairs.add(new Pairs("first_name", false));
        validatePairs.add(new Pairs("last_name", false));
        validatePairs.add(new Pairs("email", false));
        validatePairs.add(new Pairs("password", false));
        validatePairs.add(new Pairs("phonenumber", false));
        validatePairs.add(new Pairs("postal_code", false));
        validatePairs.add(new Pairs("street", false));
        
        cleanData();
    }
    
    private void setValidatePairs(String key, boolean value){
        for (Pairs<String, Boolean> validatePair : validatePairs) {
            if(validatePair.getKey().equals(key)){
                validatePair.setValue(value);
            }
        }
    }
    
    private boolean isValidatePairsOk(){
        for (Pairs<String, Boolean> validatePair : validatePairs) {
            if(validatePair.getValue().equals(false)){
                return false;
            }
        }
        return true;
    }
    
    public List<CountryModel> getCountries(){
        CountryClient countryClient = new CountryClient();
        List<CountryModel> countries = (List<CountryModel>) countryClient.findAll_JSON(List.class);
        countryClient.close();
        return countries;
    }
    
    public List<CountyModel> getCounties(){
        CountyClient countyClient = new CountyClient();
        List<CountyModel> counties = (List<CountyModel>) countyClient.findAll_JSON(List.class);
        countyClient.close();
        return counties;
    }
    
    public List<CityModel> getCities(){
        if(postal_code!=null && !postal_code.isEmpty()){
            CityClient cityClient = new CityClient();
            List<CityModel> cities = (List<CityModel>)cityClient.findAll_withPostalCode_JSON(List.class, postal_code);
            cityClient.close();
            return cities;
        }else{
            return null;
        }
    }
    
    public String register(){
        UserModel userModel = new UserModel();
        
        userModel.setId(null);
        userModel.setFirstName(first_name);
        userModel.setLastName(last_name);
        userModel.setPhoneNumber(phoneNumber);
        userModel.setEmail(email);
        byte[] encodedBytes = Base64.getEncoder().encode(password.getBytes());
        userModel.setPassword(new String(encodedBytes));
        userModel.setCountryIso2(countryIso2);
        userModel.setCountyId(countyId);
        userModel.setCityId(cityId);
        userModel.setStreet(street);
        userModel.setRoleId(3);
        userModel.setIsActive(false);
        userModel.setIsAccepted(false);
        
        if(isValidatePairsOk()){
            UserClient userClient = new UserClient();
            userClient.create_JSON(userModel);
            userClient.close();
            cleanData();
            return "/login.xhtml?faces-redirect=true"; 
        }else{
            return "/private/register.xhtml";
        }
    }
    
    private void cleanData(){
        this.first_name = "";
        this.last_name = "";
        this.phoneNumber = "";
        this.email = "";
        this.password ="";
        this.postal_code= "";
        this.street = "";
        this.countryIso2 = "0";
        this.countyId = 0;
    }

    public String getPostal_code() {
        return postal_code;
    }

    public void setPostal_code(String postal_code) {
        if(postal_code == null || postal_code.isEmpty()){
            FacesContext.getCurrentInstance().addMessage("register:postal_code", new FacesMessage("A mező kitöltése kötelező!"));
            setValidatePairs("postal_code",false);
        }else if(!postal_code.matches("^[0-9]+$")){
            FacesContext.getCurrentInstance().addMessage("register:postal_code", new FacesMessage("Csak számot tartalmazhat!"));
            setValidatePairs("postal_code",false);
        }else{
            setValidatePairs("postal_code",true);
            this.postal_code = postal_code;
        }
    }

    
    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        if(first_name == null || first_name.isEmpty()){
            FacesContext.getCurrentInstance().addMessage("register:first_name", new FacesMessage("A mező kitöltése kötelező!"));
            setValidatePairs("first_name",false);
        }else{
            setValidatePairs("first_name",true);
            this.first_name = first_name;
        }
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        if(last_name == null || last_name.isEmpty()){
            FacesContext.getCurrentInstance().addMessage("register:last_name", new FacesMessage("A mező kitöltése kötelező!"));
            setValidatePairs("last_name",false);
        }else{
            setValidatePairs("last_name",true);
            this.last_name = last_name;
        }
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        if(email == null || email.isEmpty()){
            FacesContext.getCurrentInstance().addMessage("register:email", new FacesMessage("A mező kitöltése kötelező!"));
            setValidatePairs("email",false);
        }else if(!validateEmail(email)){
            setValidatePairs("email",false);
            FacesContext.getCurrentInstance().addMessage("register:email", new FacesMessage("Nem megfelelő e-mail cím!"));
        }else{
            UserClient userClient = new UserClient();
            if(userClient.findByEmail_JSON(UserModel.class, email) != null){
                userClient.close();
                setValidatePairs("email",false);
                FacesContext.getCurrentInstance().addMessage("register:email", new FacesMessage("Ez az e-mail cím már foglalt!"));
            }else{
                setValidatePairs("email",true);
                this.email = email;
            }
        }
    }
    
    private boolean validateEmail(String email){
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\."+ 
                            "[a-zA-Z0-9_+&*-]+)*@" + 
                            "(?:[a-zA-Z0-9-]+\\.)+[a-z" + 
                            "A-Z]{2,7}$"; 
                              
        Pattern pat = Pattern.compile(emailRegex);
        return pat.matcher(email).matches(); 
    }
    
    /*private boolean validateEmail(String email){
        try {
            InternetAddress emailAddr = new InternetAddress(email);
            emailAddr.validate();
        } catch (AddressException ex) {
            return false;
        }
        return false;
    }*/

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        if(password.isEmpty()){
            FacesContext.getCurrentInstance().addMessage("register:password", new FacesMessage("A mező kitöltése kötelező!"));
            setValidatePairs("password",false);
        }else{
            setValidatePairs("password",true);
            this.password = password;
        }
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        if(phoneNumber.isEmpty()){
            FacesContext.getCurrentInstance().addMessage("register:phonenumber", new FacesMessage("A mező kitöltése kötelező!"));
            setValidatePairs("phonenumber",false);
        }else if(!phoneNumber.matches("^[0-9]+$")){
            FacesContext.getCurrentInstance().addMessage("register:phonenumber", new FacesMessage("Csak számot tartalmazhat!"));
            setValidatePairs("phonenumber",false);
        }else{
            setValidatePairs("phonenumber",true);
            this.phoneNumber = phoneNumber;
        }
    }

    public String getCountryIso2() {
        return countryIso2;
    }

    public void setCountryIso2(String countryIso2) {
        setValidatePairs("countryIso2",true);
        this.countryIso2 = countryIso2;
    }

    public int getCountyId() {
        return countyId;
    }

    public void setCountyId(int countyId) {
        setValidatePairs("countyId",true);
        this.countyId = countyId;
    }

    public int getCityId() {
        return cityId;
    }

    public void setCityId(int cityId) {
        setValidatePairs("cityId",true);
        this.cityId = cityId;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        if(street.isEmpty()){
            FacesContext.getCurrentInstance().addMessage("register:street", new FacesMessage("A mező kitöltése kötelező!"));
            setValidatePairs("street",false);
        }else{
            setValidatePairs("street",true);
            this.street = street;
        }
    }
}
