package com.example.demo.config;

import java.io.InputStream;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.example.demo.entity.IndoarPlant;

import com.example.demo.entity.OutdoarPlants;
import com.example.demo.entity.Fruits_Vegitables;
import com.example.demo.entity.Herbs;
import com.example.demo.entity.Bulbs;
import com.example.demo.entity.FloweringPlant;
import com.example.demo.entity.Seeds;
import com.example.demo.entity.PlantAccessories;
import com.example.demo.entity.SoilPesticides;
import com.example.demo.entity.SucculentsCactus;

import com.example.demo.repo.BulbsRepo;
import com.example.demo.repo.FloweringPlantsRepo;
import com.example.demo.repo.Fruits_VegitablesRepo;
import com.example.demo.repo.HerbsRepo;
import com.example.demo.repo.IndoarPlantsRepo;
import com.example.demo.repo.OutdoarPlantsRepo;
import com.example.demo.repo.PlantAccessoriesRepo;
import com.example.demo.repo.SeedsRepo;
import com.example.demo.repo.SoilPesticideRepo;
import com.example.demo.repo.SucculentsCactusRepo;

import com.fasterxml.jackson.databind.ObjectMapper;

@Component
public class DataLoader implements CommandLineRunner {

    @Autowired
    private IndoarPlantsRepo indoarPlantRepo;

    @Autowired
    private OutdoarPlantsRepo outdoarRepo;

    @Autowired
    private FloweringPlantsRepo floweringPlantsRepo;

    @Autowired
    private Fruits_VegitablesRepo fruitsVegRepo;

    @Autowired
    private HerbsRepo herbsRepo;

    @Autowired
    private BulbsRepo bulbsRepo;

    @Autowired
    private SeedsRepo seedsRepo;

    @Autowired
    private PlantAccessoriesRepo accessoriesRepo;

    @Autowired
    private SoilPesticideRepo soilPesticideRepo;

    @Autowired
    private SucculentsCactusRepo succulentsCactusRepo;

    @Override
    public void run(String... args) throws Exception {

        ObjectMapper mapper = new ObjectMapper();

//        // ---------------- Indoar Plants ----------------
//        InputStream indoorIS = getClass().getResourceAsStream("/JSONData/Indoorplants.json");
//        if (indoorIS != null) {
//            List<IndoarPlant> indoor = Arrays.asList(mapper.readValue(indoorIS, IndoarPlant[].class));
//            indoarPlantRepo.saveAll(indoor);
//        }
//
//        // ---------------- Outdoar Plants ----------------
//        InputStream outdoorIS = getClass().getResourceAsStream("/JSONData/Outdoar_Plants.json");
//        if (outdoorIS != null) {
//            List<OutdoarPlants> outdoor = Arrays.asList(mapper.readValue(outdoorIS, OutdoarPlants[].class));
//            outdoarRepo.saveAll(outdoor);
//        }
//
//        // ---------------- Flowering Plants ----------------
//        InputStream floweringIS = getClass().getResourceAsStream("/JSONData/flowering_plants.json");
//        if (floweringIS != null) {
//            List<FloweringPlant> flowering = Arrays.asList(mapper.readValue(floweringIS, FloweringPlant[].class));
//            floweringPlantsRepo.saveAll(flowering);
//        }
//
//        // ---------------- Fruits & Vegetables ----------------
//        InputStream fvIS = getClass().getResourceAsStream("/JSONData/Fruits_Vegitables.json");
//        if (fvIS != null) {
//            List<Fruits_Vegitables> fv = Arrays.asList(mapper.readValue(fvIS, Fruits_Vegitables[].class));
//            fruitsVegRepo.saveAll(fv);
//        }
////
////        // ---------------- Herbs ----------------
//        InputStream herbsIS = getClass().getResourceAsStream("/JSONData/HerbsPlants.json");
//        if (herbsIS != null) {
//            List<Herbs> herbs = Arrays.asList(mapper.readValue(herbsIS, Herbs[].class));
//            herbsRepo.saveAll(herbs);
//        }
////
////        // ---------------- Bulbs ----------------
//        InputStream bulbsIS = getClass().getResourceAsStream("/JSONData/BulbsPlants.json");
//        if (bulbsIS != null) {
//            List<Bulbs> bulbs = Arrays.asList(mapper.readValue(bulbsIS, Bulbs[].class));
//            bulbsRepo.saveAll(bulbs);
//        }
////
////        // ---------------- Seeds ----------------
//        InputStream seedsIS = getClass().getResourceAsStream("/JSONData/Seeds.json");
//        if (seedsIS != null) {
//            List<Seeds> seeds = Arrays.asList(mapper.readValue(seedsIS, Seeds[].class));
//            seedsRepo.saveAll(seeds);
//        }
//
//        // ---------------- Plant Accessories ----------------
//        InputStream accIS = getClass().getResourceAsStream("/JSONData/PlantAccessories.json");
//        if (accIS != null) {
//            List<PlantAccessories> accessories = Arrays.asList(mapper.readValue(accIS, PlantAccessories[].class));
//            accessoriesRepo.saveAll(accessories);
//        }
////
////        // ---------------- Soil & Pesticide ----------------
//        InputStream soilIS = getClass().getResourceAsStream("/JSONData/Soil_Pesticides.json");
//        if (soilIS != null) {
//            List<SoilPesticides> soil = Arrays.asList(mapper.readValue(soilIS, SoilPesticides[].class));
//            soilPesticideRepo.saveAll(soil);
//        }
////
////        // ---------------- Succulents & Cactus ----------------
//        InputStream succIS = getClass().getResourceAsStream("/JSONData/SucculentsCactus.json");
//        if (succIS != null) {
//            List<SucculentsCactus> succ = Arrays.asList(mapper.readValue(succIS, SucculentsCactus[].class));
//            succulentsCactusRepo.saveAll(succ);
//        }

        System.out.println("🌱 All JSON files inserted successfully!");
    }
}
