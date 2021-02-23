/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import java.util.Set;
import javax.ws.rs.core.Application;

/**
 *
 * @author Patrik
 */
@javax.ws.rs.ApplicationPath("webresources")
public class ApplicationConfig extends Application {

    @Override
    public Set<Class<?>> getClasses() {
        Set<Class<?>> resources = new java.util.HashSet<>();
        addRestResourceClasses(resources);
        return resources;
    }

    /**
     * Do not modify addRestResourceClasses() method.
     * It is automatically populated with
     * all resources defined in the project.
     * If required, comment out calling this method in getClasses().
     */
    private void addRestResourceClasses(Set<Class<?>> resources) {
        resources.add(service.AnimalDiseasesFacadeREST.class);
        resources.add(service.AnimalFacadeREST.class);
        resources.add(service.AnimalHasDiseasesFacadeREST.class);
        resources.add(service.BreedFacadeREST.class);
        resources.add(service.BreedingClassificationFacadeREST.class);
        resources.add(service.BreedingFacadeREST.class);
        resources.add(service.BreedingHasAnimalFacadeREST.class);
        resources.add(service.BreedingQualificationFacadeREST.class);
        resources.add(service.BreedingTypeFacadeREST.class);
        resources.add(service.CalvingFacadeREST.class);
        resources.add(service.CapacityTypeFacadeREST.class);
        resources.add(service.CityFacadeREST.class);
        resources.add(service.ColorFacadeREST.class);
        resources.add(service.CountryFacadeREST.class);
        resources.add(service.CountyFacadeREST.class);
        resources.add(service.HoldingPlaceFacadeREST.class);
        resources.add(service.HoldingPlaceHasBreedingFacadeREST.class);
        resources.add(service.RoleFacadeREST.class);
        resources.add(service.SpeciesFacadeREST.class);
        resources.add(service.UserFacadeREST.class);
        resources.add(service.UserHasBreedingFacadeREST.class);
        resources.add(service.UserHasMailFacadeREST.class);
    }
    
}
