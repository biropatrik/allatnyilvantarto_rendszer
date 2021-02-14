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
import client.ColorClient;
import client.SpeciesClient;
import client.UserHasBreedingClient;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import model.AnimalModel;
import model.BreedModel;
import model.CalvingModel;
import model.ColorModel;
import model.SpeciesModel;

/**
 *
 * @author Patrik
 */
@ManagedBean(name="animalController", eager = true)
@SessionScoped
public class AnimalController {
    private String id;
    private String openedId = "-1";
    private List<String> breedingIds;
    private List<String> animalEarTags;
    
    public AnimalController(){
        this.breedingIds = getBreedingIdsByUserId();
        this.animalEarTags = getAnimalEarTagsByBreedingIds();
    }
    
    private List<String> getBreedingIdsByUserId(){
        id = Session.getUserId();
        UserHasBreedingClient client = new UserHasBreedingClient();
        List<String> breedings = (List<String>) client.findAllBreedingIdByUserId_JSON(List.class, id);
        client.close();
        return breedings;
    }
    
    private List<String> getAnimalEarTagsByBreedingIds(){
        BreedingHasAnimalClient client = new BreedingHasAnimalClient();
        List<String> earTags = new ArrayList<>();
        for(int i=0; i<this.breedingIds.size(); i++){
            List<String> list = (List<String>)client.findAllEarTagsByBreedingId_JSON(List.class, String.valueOf(breedingIds.get(i)));
            earTags.addAll(list);
        }
        client.close();
        return earTags;
    }
    
    public List<AnimalModel> getAllAnimal(){
        this.breedingIds = getBreedingIdsByUserId();
        this.animalEarTags = getAnimalEarTagsByBreedingIds();
        
        AnimalClient client = new AnimalClient();
        List<AnimalModel> animals = new ArrayList<>();
        for(int i=0; i<this.animalEarTags.size(); i++){
            AnimalModel animal = client.find_JSON(AnimalModel.class, this.animalEarTags.get(i));
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

    public String getOpenedId() {
        return openedId;
    }

    public void setOpenedId(String openedId) {
        this.openedId = openedId;
    }
}
