/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author Patrik
 */
public class UserModel {
    private Integer id;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String phoneNumber;
    private String countryIso2;
    private int countyId;
    private int cityId;
    private String street;
    private int roleId;
    private boolean isActive;
    
    public UserModel(){}

    public UserModel(Integer id, String firstName, String lastName, String email, 
            String password, String phoneNumber, String countryIso2, int countyId, 
            int cityId, String street, int roleId, boolean isActive) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.phoneNumber = phoneNumber;
        this.countryIso2 = countryIso2;
        this.countyId = countyId;
        this.cityId = cityId;
        this.street = street;
        this.roleId = roleId;
        this.isActive = isActive;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getCountryIso2() {
        return countryIso2;
    }

    public void setCountryIso2(String countryIso2) {
        this.countryIso2 = countryIso2;
    }

    public int getCountyId() {
        return countyId;
    }

    public void setCountyId(int countyId) {
        this.countyId = countyId;
    }

    public int getCityId() {
        return cityId;
    }

    public void setCityId(int cityId) {
        this.cityId = cityId;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public int getRoleId() {
        return roleId;
    }

    public void setRoleId(int roleId) {
        this.roleId = roleId;
    }

    public boolean isIsActive() {
        return isActive;
    }

    public void setIsActive(boolean isActive) {
        this.isActive = isActive;
    }
    
    
}
