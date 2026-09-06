package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Bulbs;
import com.example.demo.entity.FloweringPlant;
import com.example.demo.entity.Fruits_Vegitables;
import com.example.demo.entity.Herbs;
import com.example.demo.entity.IndoarPlant;
import com.example.demo.entity.LoginData;
import com.example.demo.entity.OutdoarPlants;
import com.example.demo.entity.PlantAccessories;
import com.example.demo.entity.Seeds;
import com.example.demo.entity.SoilPesticides;
import com.example.demo.entity.SucculentsCactus;
import com.example.demo.entity.Users;
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
import com.example.demo.repo.UsersRepo;

@Service
public class UserService {

	@Autowired
	UsersRepo usersRepo;

	// add users
	public Users addUser(Users user) {
		return usersRepo.save(user);
	}

	// login
	public Boolean loginUser(LoginData logindata) {
		Optional<Users> user = usersRepo.findByEmail(logindata.getEmail());

		if (user.isEmpty()) {
			return false;
		}

		Users user1 = user.get();
		return user1.getPassword().equals(logindata.getPassword());
	}

	@Autowired
	IndoarPlantsRepo indoarPlantsrepo1;

	public List<IndoarPlant> getAllIndoarPlants() {
		return indoarPlantsrepo1.findAll();
	}

	// ---------------------- QTY UPDATE FOR INDOOR PLANTS -------------------------

	public IndoarPlant updateIndoarQty(int plantId, int qty) {

		IndoarPlant plant = indoarPlantsrepo1.findById(plantId).orElse(null);

		if (plant != null) {
			plant.setQty(qty);
			return indoarPlantsrepo1.save(plant);
		}

		return null;
	}

	public void resetIndoarQty(int plantId) {

		IndoarPlant plant = indoarPlantsrepo1.findById(plantId).orElse(null);

		if (plant != null) {
			plant.setQty(0);
			indoarPlantsrepo1.save(plant);
		}
	}

	public IndoarPlant getIndoarPlantById(int plantId) {
		return indoarPlantsrepo1.findById(plantId).orElse(null);
	}

	@Autowired
	private OutdoarPlantsRepo outdoarPlantsRepo;

	// Get all outdoor plants
	public List<OutdoarPlants> getAllOutdoarPlants() {
		return outdoarPlantsRepo.findAll();
	}

	// Update qty
	public OutdoarPlants updateOutdoarQty(Long plantId, Integer qty) {

		OutdoarPlants plant = outdoarPlantsRepo.findById(plantId).orElse(null);

		if (plant != null) {
			plant.setQty(qty);
			return outdoarPlantsRepo.save(plant);
		}

		return null;
	}

	// Reset qty
	public void resetOutdoarQty(Long plantId) {

		OutdoarPlants plant = outdoarPlantsRepo.findById(plantId).orElse(null);

		if (plant != null) {
			plant.setQty(0);
			outdoarPlantsRepo.save(plant);
		}
	}

	// Get plant by id
	public OutdoarPlants getOutdoarPlantById(Long plantId) {
		return outdoarPlantsRepo.findById(plantId).orElse(null);
	}

	@Autowired
	private Fruits_VegitablesRepo fruitsVegitablesRepo;

	// Get all fruits & vegetables
	public List<Fruits_Vegitables> getAllFruitsVegitables() {
		return fruitsVegitablesRepo.findAll();
	}

	// Update qty
	public Fruits_Vegitables updateFruitsVegitablesQty(Long itemId, Integer qty) {

		Fruits_Vegitables item = fruitsVegitablesRepo.findById(itemId).orElse(null);

		if (item != null) {
			item.setQty(qty);
			return fruitsVegitablesRepo.save(item);
		}

		return null;
	}

	// Reset qty
	public void resetFruitsVegitablesQty(Long itemId) {

		Fruits_Vegitables item = fruitsVegitablesRepo.findById(itemId).orElse(null);

		if (item != null) {
			item.setQty(0);
			fruitsVegitablesRepo.save(item);
		}
	}

	// Get by id
	public Fruits_Vegitables getFruitsVegitablesById(Long itemId) {
		return fruitsVegitablesRepo.findById(itemId).orElse(null);
	}

	@Autowired
	private SeedsRepo seedsRepo;

	// Get all seeds
	public List<Seeds> getAllSeeds() {
		return seedsRepo.findAll();
	}

	// Update qty
	public Seeds updateSeedsQty(Long seedId, Integer qty) {

		Seeds seed = seedsRepo.findById(seedId).orElse(null);

		if (seed != null) {
			seed.setQty(qty);
			return seedsRepo.save(seed);
		}

		return null;
	}

	// Reset qty
	public void resetSeedsQty(Long seedId) {

		Seeds seed = seedsRepo.findById(seedId).orElse(null);

		if (seed != null) {
			seed.setQty(0);
			seedsRepo.save(seed);
		}
	}

	// Get by id
	public Seeds getSeedsById(Long seedId) {
		return seedsRepo.findById(seedId).orElse(null);
	}

	@Autowired
	private SoilPesticideRepo soilPesticideRepo;

	// Get all soil & pesticides
	public List<SoilPesticides> getAllSoilPesticides() {
		return soilPesticideRepo.findAll();
	}

	// Update qty
	public SoilPesticides updateSoilPesticideQty(Long itemId, Integer qty) {

		SoilPesticides item = soilPesticideRepo.findById(itemId).orElse(null);

		if (item != null) {
			item.setQty(qty);
			return soilPesticideRepo.save(item);
		}

		return null;
	}

	// Reset qty to 0
	public void resetSoilPesticideQty(Long itemId) {

		SoilPesticides item = soilPesticideRepo.findById(itemId).orElse(null);

		if (item != null) {
			item.setQty(0);
			soilPesticideRepo.save(item);
		}
	}

	// Get by id
	public SoilPesticides getSoilPesticideById(Long itemId) {
		return soilPesticideRepo.findById(itemId).orElse(null);
	}

	@Autowired
	private FloweringPlantsRepo floweringPlantsRepo;

	// Get all flowering plants
	public List<FloweringPlant> getAllFloweringPlants() {
		return floweringPlantsRepo.findAll();
	}

	// Update qty
	public FloweringPlant updateFloweringPlantQty(Long plantId, Integer qty) {

		FloweringPlant plant = floweringPlantsRepo.findById(plantId).orElse(null);

		if (plant != null) {
			plant.setQty(qty);
			return floweringPlantsRepo.save(plant);
		}

		return null;
	}

	// Reset qty
	public void resetFloweringPlantQty(Long plantId) {

		FloweringPlant plant = floweringPlantsRepo.findById(plantId).orElse(null);

		if (plant != null) {
			plant.setQty(0);
			floweringPlantsRepo.save(plant);
		}
	}

	// Get by id
	public FloweringPlant getFloweringPlantById(Long plantId) {
		return floweringPlantsRepo.findById(plantId).orElse(null);
	}

	@Autowired
	private HerbsRepo herbsRepo;

	// Get all herbs
	public List<Herbs> getAllHerbs() {
		return herbsRepo.findAll();
	}

	// Update qty
	public Herbs updateHerbQty(Long herbId, Integer qty) {

		Herbs herb = herbsRepo.findById(herbId).orElse(null);

		if (herb != null) {
			herb.setQty(qty);
			return herbsRepo.save(herb);
		}

		return null;
	}

	// Reset qty
	public void resetHerbQty(Long herbId) {

		Herbs herb = herbsRepo.findById(herbId).orElse(null);

		if (herb != null) {
			herb.setQty(0);
			herbsRepo.save(herb);
		}
	}

	// Get by id
	public Herbs getHerbById(Long herbId) {
		return herbsRepo.findById(herbId).orElse(null);
	}

	@Autowired
	private BulbsRepo bulbsRepo;

	// Get all bulbs
	public List<Bulbs> getAllBulbs() {
		return bulbsRepo.findAll();
	}

	// Update qty
	public Bulbs updateBulbQty(Long bulbId, Integer qty) {

		Bulbs bulb = bulbsRepo.findById(bulbId).orElse(null);

		if (bulb != null) {
			bulb.setQty(qty);
			return bulbsRepo.save(bulb);
		}

		return null;
	}

	// Reset qty
	public void resetBulbQty(Long bulbId) {

		Bulbs bulb = bulbsRepo.findById(bulbId).orElse(null);

		if (bulb != null) {
			bulb.setQty(0);
			bulbsRepo.save(bulb);
		}
	}

	// Get by ID
	public Bulbs getBulbById(Long bulbId) {
		return bulbsRepo.findById(bulbId).orElse(null);
	}

	@Autowired
	private SucculentsCactusRepo succulentsCactusRepo;

	// Get all succulents & cactus
	public List<SucculentsCactus> getAllSucculentsCactus() {
		return succulentsCactusRepo.findAll();
	}

	// Update qty
	public SucculentsCactus updateSucculentCactusQty(Long plantId, Integer qty) {

		SucculentsCactus sc = succulentsCactusRepo.findById(plantId).orElse(null);

		if (sc != null) {
			sc.setQty(qty);
			return succulentsCactusRepo.save(sc);
		}

		return null;
	}

	// Reset qty
	public void resetSucculentCactusQty(Long plantId) {

		SucculentsCactus sc = succulentsCactusRepo.findById(plantId).orElse(null);

		if (sc != null) {
			sc.setQty(0);
			succulentsCactusRepo.save(sc);
		}
	}

	// Get by ID
	public SucculentsCactus getSucculentCactusById(Long plantId) {
		return succulentsCactusRepo.findById(plantId).orElse(null);
	}

	@Autowired
	private PlantAccessoriesRepo plantAccessoriesRepo;

	// Get all accessories
	public List<PlantAccessories> getAllPlantAccessories() {
		return plantAccessoriesRepo.findAll();
	}

	// Update qty
	public PlantAccessories updatePlantAccessoryQty(Long accessoryId, Integer qty) {

		PlantAccessories pa = plantAccessoriesRepo.findById(accessoryId).orElse(null);

		if (pa != null) {
			pa.setQty(qty);
			return plantAccessoriesRepo.save(pa);
		}

		return null;
	}

	// Reset qty
	public void resetPlantAccessoryQty(Long accessoryId) {

		PlantAccessories pa = plantAccessoriesRepo.findById(accessoryId).orElse(null);

		if (pa != null) {
			pa.setQty(0);
			plantAccessoriesRepo.save(pa);
		}
	}

	// Get by ID
	public PlantAccessories getPlantAccessoryById(Long accessoryId) {
		return plantAccessoriesRepo.findById(accessoryId).orElse(null);
	}

}
