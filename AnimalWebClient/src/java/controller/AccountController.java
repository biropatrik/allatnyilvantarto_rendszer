/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import client.CityClient;
import client.CountryClient;
import client.CountyClient;
import client.RoleClient;
import client.UserClient;
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
import model.RoleModel;
import model.UserModel;

/**
 *
 * @author Patrik
 */

@ManagedBean(name="accountController", eager = true)
@SessionScoped
public class AccountController {
    private UserModel user;
    private String countryName;
    private String countyName;
    private String cityPostalCode;
    private String cityName;
    private final String roleName;
    private String newPassword;
    private String newPasswordAgain;
    private String email;
    
    private String id;
    
    public AccountController(){
        getAllUserData();
        countryName = getCountryName(this.user.getCountryIso2());
        countyName = getCountyName(this.user.getCountyId());
        cityPostalCode = getCityPostalCode(this.user.getCityId());
        cityName = getCityName(this.user.getCityId());
        roleName = getRoleName(this.user.getRoleId());
        if(this.user != null){
            email = this.user.getEmail();
        }
    }
    
    public String saveAccountModification(){
        boolean isOk = true;
        
        if(this.user.getLastName() == null || this.user.getLastName().isEmpty()){
            isOk = false;
            FacesContext.getCurrentInstance().addMessage("modification:last_name", new FacesMessage("A mező kitöltése kötelező!"));
        }
        
        if(this.user.getFirstName() == null || this.user.getFirstName().isEmpty()){
            isOk = false;
            FacesContext.getCurrentInstance().addMessage("modification:first_name", new FacesMessage("A mező kitöltése kötelező!"));
        }
        
        if(this.user.getEmail() == null || this.user.getEmail().isEmpty()){
            isOk = false;
            FacesContext.getCurrentInstance().addMessage("modification:email", new FacesMessage("A mező kitöltése kötelező!"));
        }else if(!validateEmail(this.user.getEmail())){
            isOk = false;
            FacesContext.getCurrentInstance().addMessage("modification:email", new FacesMessage("Nem megfelelő e-mail cím!"));
        }else if(!this.email.equals(this.user.getEmail())){
            UserClient userClient = new UserClient();
            if(userClient.find_withEmail_JSON(UserModel.class, this.user.getEmail()) != null){
                userClient.close();
                isOk = false;
                FacesContext.getCurrentInstance().addMessage("modification:email", new FacesMessage("Ez az e-mail cím már foglalt!"));
            }
        }
        
        if(this.user.getPhoneNumber().isEmpty()){
            isOk = false;
            FacesContext.getCurrentInstance().addMessage("modification:phone_number", new FacesMessage("A mező kitöltése kötelező!"));
        }else if(!this.user.getPhoneNumber().matches("^[0-9]+$")){
            isOk = false;
            FacesContext.getCurrentInstance().addMessage("modification:phone_number", new FacesMessage("Csak számot tartalmazhat!"));
        }
        
        if(this.getCityPostalCode() == null || this.getCityPostalCode().isEmpty()){
            isOk = false;
            FacesContext.getCurrentInstance().addMessage("modification:postal_code", new FacesMessage("A mező kitöltése kötelező!"));
        }
        
        if(this.user.getStreet() == null || this.user.getStreet().isEmpty()){
            isOk = false;
            FacesContext.getCurrentInstance().addMessage("modification:street", new FacesMessage("A mező kitöltése kötelező!"));
        }
        
        if(!this.newPassword.isEmpty() || !this.newPassword.isEmpty()){
            if(!this.newPassword.equals(this.newPasswordAgain)){
                isOk = false;
                FacesContext.getCurrentInstance().addMessage("modification:password_new", new FacesMessage("Nem egyeznek a jelszavak!"));
            }else{
                byte[] encodedBytes = Base64.getEncoder().encode(newPassword.getBytes());
                this.user.setPassword(new String(encodedBytes));
            }
        }
        
        if(isOk == true){
            UserClient client = new UserClient();
            client.edit_JSON(this.user, this.id);
            client.close();
            
            this.email = this.user.getEmail();
            
            getAllUserData();
            return "account.xhtml?faces-redirect=true";
        }
        return "";
    }
    
    private boolean validateEmail(String email){
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\."+ 
                            "[a-zA-Z0-9_+&*-]+)*@" + 
                            "(?:[a-zA-Z0-9-]+\\.)+[a-z" + 
                            "A-Z]{2,7}$"; 
                              
        Pattern pat = Pattern.compile(emailRegex);
        return pat.matcher(email).matches(); 
    }
    
    public void getAllUserData(){
        id = Session.getUserId();
        UserClient client = new UserClient();
        this.user = client.find_JSON(UserModel.class, id);
        client.close();
    }
    
    public UserModel getUser(){
        if(this.user == null){
            getAllUserData();
        }
        
        return this.user;
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
    
    private String getRoleName(int id){
        RoleClient client = new RoleClient();
        RoleModel role = client.find_JSON(RoleModel.class, String.valueOf(id));
        client.close();
        if(role == null){
            return "Hiba történt!";
        }
        return role.getName();
    }
    
    public String getCountryName(){
        return this.countryName;
    }
    
    public String getCountyName(){
        return this.countyName;
    }
    
    public String getCityPostalCode(){
        return this.cityPostalCode;
    }
    
    public String getCityName(){
        return this.cityName;
    }
    
    public String getRoleName(){
        return this.roleName;
    }
    
    public String getNewPassword(){
        return this.newPassword;
    }

    public String getNewPasswordAgain() {
        return newPasswordAgain;
    }

    public void setNewPasswordAgain(String newPasswordAgain) {
        this.newPasswordAgain = newPasswordAgain;
    }
    
    public void setNewPassword(String password){
        this.newPassword = password;
    }

    public void setUser(UserModel user) {
        this.user = user;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }

    public void setCountyName(String countyName) {
        this.countyName = countyName;
    }

    public void setCityPostalCode(String cityPostalCode) {
        if(cityPostalCode == null || cityPostalCode.isEmpty()){
            FacesContext.getCurrentInstance().addMessage("modification:postal_code", new FacesMessage("A mező kitöltése kötelező!"));
        }else if(!cityPostalCode.matches("^[0-9]+$")){
            FacesContext.getCurrentInstance().addMessage("modification:postal_code", new FacesMessage("Csak számot tartalmazhat!"));
        }else{
            this.cityPostalCode = cityPostalCode;
        }
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
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
        if(cityPostalCode!=null && !cityPostalCode.isEmpty()){
            CityClient cityClient = new CityClient();
            List<CityModel> cities = (List<CityModel>)cityClient.findAll_withPostalCode_JSON(List.class, cityPostalCode);
            cityClient.close();
            return cities;
        }else{
            return null;
        }
    }
}
