/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import client.AnimalClient;
import client.BreedingHasAnimalClient;
import client.UserHasBreedingClient;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import model.AnimalModel;

/**
 *
 * @author Patrik
 */
@ManagedBean(name="animalController", eager = true)
@SessionScoped
public class AnimalController {
    private String id;
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
        AnimalModel animal = client.find_JSON(AnimalModel.class, this.animalEarTags.get(0));
        animals.add(animal);
        return animals;
    }
}
