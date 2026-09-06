package com.example.demo.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
import com.example.demo.service.UserService;

@RestController
@RequestMapping("/users")
@CrossOrigin(origins = "http://localhost:5173") // allow React frontend
public class UserController {

	@Autowired
	UserService userService;

	@PostMapping("/signup1")

	public Users addUser(@RequestBody Users user) {
		return userService.addUser(user);
	}

	@PostMapping("/login1")

	public Boolean loginUser(@RequestBody LoginData logindata) { // convert json into users
		return userService.loginUser(logindata); // check the email given by user and returns true and false

	}

	@GetMapping("/indoarplants")
	public List<IndoarPlant> getIndoarPlant() {
		return userService.getAllIndoarPlants();
	}

	@PostMapping("/indoarplants/updateQty")
	public IndoarPlant updateIndoarQty(@RequestBody Map<String, Integer> request) {
		int plantId = request.get("plant_id");
		int qty = request.get("qty");
		return userService.updateIndoarQty(plantId, qty);
	}

	@PostMapping("/indoarplants/resetQty")
	public void resetIndoarQty(@RequestBody Map<String, Integer> request) {
		int plantId = request.get("plant_id");
		userService.resetIndoarQty(plantId);
	}

	@GetMapping("/indoarplants/{id}")
	@CrossOrigin(origins = "http://localhost:5173")
	public IndoarPlant getIndoarPlantById(@PathVariable("id") int plantId) {
		return userService.getIndoarPlantById(plantId);
	}

	@Autowired
	private UserService outdoarPlantService;

	// Get all outdoor plants
	@GetMapping("/outdoarplants")
	public List<OutdoarPlants> getOutdoarPlants() {
		return userService.getAllOutdoarPlants();
	}

	// Update qty
	@PostMapping("/outdoarplants/updateQty")
	public OutdoarPlants updateOutdoarQty(@RequestBody Map<String, Integer> request) {
		Long plantId = request.get("plant_id").longValue();
		Integer qty = request.get("qty");
		return userService.updateOutdoarQty(plantId, qty);
	}

	// Reset qty
	@PostMapping("/outdoarplants/resetQty")
	public void resetOutdoarQty(@RequestBody Map<String, Long> request) {
		Long plantId = request.get("plant_id");
		userService.resetOutdoarQty(plantId);
	}

	// Get by id
	@GetMapping("/outdoarplants/{id}")
	public OutdoarPlants getOutdoarPlantById(@PathVariable("id") Long plantId) {
		return userService.getOutdoarPlantById(plantId);
	}

	// Get all fruits & vegetables
	@GetMapping("/fruits_vegitables")
	public List<Fruits_Vegitables> getFruitsVegitables() {
		return userService.getAllFruitsVegitables();
	}

	// Update qty
	@PostMapping("/fruits_vegitables/updateQty")
	public Fruits_Vegitables updateFruitsVegitablesQty(@RequestBody Map<String, Integer> request) {
		Long itemId = request.get("plant_id").longValue(); // keeping same key as outdoor
		Integer qty = request.get("qty");
		return userService.updateFruitsVegitablesQty(itemId, qty);
	}

	// Reset qty
	@PostMapping("/fruits_vegitables/resetQty")
	public void resetFruitsVegitablesQty(@RequestBody Map<String, Long> request) {
		Long itemId = request.get("plant_id");
		userService.resetFruitsVegitablesQty(itemId);
	}

	// Get by id
	@GetMapping("/fruits_vegitables/{id}")
	public Fruits_Vegitables getFruitsVegitablesById(@PathVariable("id") Long itemId) {
		return userService.getFruitsVegitablesById(itemId);
	}

	@GetMapping("/seeds")
	public List<Seeds> getAllSeeds() {
		return userService.getAllSeeds();
	}

	// Update qty
	@PostMapping("/seeds/updateQty")
	public Seeds updateSeedsQty(@RequestBody Map<String, Integer> request) {

		Long seedId = request.get("plant_id").longValue(); // same key as vegetables
		Integer qty = request.get("qty");

		return userService.updateSeedsQty(seedId, qty);
	}

	// Reset qty
	@PostMapping("/seeds/resetQty")
	public void resetSeedsQty(@RequestBody Map<String, Long> request) {

		Long seedId = request.get("plant_id");
		userService.resetSeedsQty(seedId);
	}

	// Get by id
	@GetMapping("/seeds/{id}")
	public Seeds getSeedsById(@PathVariable("id") Long seedId) {
		return userService.getSeedsById(seedId);
	}

	// Get all soil & pesticides
	@GetMapping("/soil_pesticides")
	@CrossOrigin(origins = "http://localhost:5173")
	public List<SoilPesticides> getAllSoilPesticides() {
		return userService.getAllSoilPesticides();
	}

	// Update qty
	@PostMapping("/soil_pesticides/updateQty")
	public SoilPesticides updateSoilPesticideQty(@RequestBody Map<String, Integer> request) {

		Long itemId = request.get("plant_id").longValue(); // same key as seeds
		Integer qty = request.get("qty");

		return userService.updateSoilPesticideQty(itemId, qty);
	}

	// Reset qty
	@PostMapping("/soil_pesticides/resetQty")
	public void resetSoilPesticideQty(@RequestBody Map<String, Long> request) {

		Long itemId = request.get("plant_id");
		userService.resetSoilPesticideQty(itemId);
	}

	// Get by ID
	@GetMapping("/soil_pesticides/{id}")
	public SoilPesticides getSoilPesticideById(@PathVariable("id") Long id) {
		return userService.getSoilPesticideById(id);
	}

	// Get all flowering plants
	@GetMapping("/flowering_plants")
	@CrossOrigin(origins = "http://localhost:5173")
	public List<FloweringPlant> getFloweringPlants() {
		return userService.getAllFloweringPlants();
	}

	// Update qty
	@PostMapping("/flowering_plants/updateQty")
	public FloweringPlant updateFloweringPlantQty(@RequestBody Map<String, Integer> request) {

		Long plantId = request.get("plant_id").longValue(); // same key as seeds/soil
		Integer qty = request.get("qty");

		return userService.updateFloweringPlantQty(plantId, qty);
	}

	// Reset qty
	@PostMapping("/flowering_plants/resetQty")
	public void resetFloweringPlantQty(@RequestBody Map<String, Long> request) {

		Long plantId = request.get("plant_id");
		userService.resetFloweringPlantQty(plantId);
	}

	// Get by ID
	@GetMapping("/flowering_plants/{id}")
	public FloweringPlant getFloweringPlantById(@PathVariable("id") Long id) {
		return userService.getFloweringPlantById(id);
	}

	// Get all herbs
	@GetMapping("/herbs")
	@CrossOrigin(origins = "http://localhost:5173")
	public List<Herbs> getHerbs() {
		return userService.getAllHerbs();
	}

	// Update qty
	@PostMapping("/herbs/updateQty")
	public Herbs updateHerbQty(@RequestBody Map<String, Integer> request) {

		Long herbId = request.get("plant_id").longValue(); // same key format used everywhere
		Integer qty = request.get("qty");

		return userService.updateHerbQty(herbId, qty);
	}

	// Reset qty
	@PostMapping("/herbs/resetQty")
	public void resetHerbQty(@RequestBody Map<String, Long> request) {

		Long herbId = request.get("plant_id");
		userService.resetHerbQty(herbId);
	}

	// Get by ID
	@GetMapping("/herbs/{id}")
	public Herbs getHerbById(@PathVariable("id") Long id) {
		return userService.getHerbById(id);
	}

	// Get all bulbs
	@GetMapping("/bulbs")
	@CrossOrigin(origins = "http://localhost:5173")
	public List<Bulbs> getBulbs() {
		return userService.getAllBulbs();
	}

	// Update qty
	@PostMapping("/bulbs/updateQty")
	public Bulbs updateBulbQty(@RequestBody Map<String, Integer> request) {

		Long bulbId = request.get("plant_id").longValue(); // SAME KEY as herbs/seeds
		Integer qty = request.get("qty");

		return userService.updateBulbQty(bulbId, qty);
	}

	// Reset qty
	@PostMapping("/bulbs/resetQty")
	public void resetBulbQty(@RequestBody Map<String, Long> request) {

		Long bulbId = request.get("plant_id");
		userService.resetBulbQty(bulbId);
	}

	// Get by ID
	@GetMapping("/bulbs/{id}")
	public Bulbs getBulbById(@PathVariable("id") Long id) {
		return userService.getBulbById(id);
	}

	// Get all succulents & cactus
	@GetMapping("/succulents_cactus")
	@CrossOrigin(origins = "http://localhost:5173")
	public List<SucculentsCactus> getSucculentsCactus() {
		return userService.getAllSucculentsCactus();
	}

	// Update qty
	@PostMapping("/succulents_cactus/updateQty")
	public SucculentsCactus updateSucculentCactusQty(@RequestBody Map<String, Integer> request) {

		Long plantId = request.get("plant_id").longValue(); // SAME key used everywhere
		Integer qty = request.get("qty");

		return userService.updateSucculentCactusQty(plantId, qty);
	}

	// Reset qty
	@PostMapping("/succulents_cactus/resetQty")
	public void resetSucculentCactusQty(@RequestBody Map<String, Long> request) {

		Long plantId = request.get("plant_id");
		userService.resetSucculentCactusQty(plantId);
	}

	// Get by ID
	@GetMapping("/succulents_cactus/{id}")
	public SucculentsCactus getSucculentCactusById(@PathVariable("id") Long id) {
		return userService.getSucculentCactusById(id);
	}

	// Get all plant accessories
	@GetMapping("/plant_accessories")
	@CrossOrigin(origins = "http://localhost:5173")
	public List<PlantAccessories> getPlantAccessories() {
		return userService.getAllPlantAccessories();
	}

	// Update qty
	@PostMapping("/plant_accessories/updateQty")
	public PlantAccessories updatePlantAccessoryQty(@RequestBody Map<String, Integer> request) {

		Long accessoryId = request.get("plant_id").longValue(); // SAME key as all modules
		Integer qty = request.get("qty");

		return userService.updatePlantAccessoryQty(accessoryId, qty);
	}

	// Reset qty
	@PostMapping("/plant_accessories/resetQty")
	public void resetPlantAccessoryQty(@RequestBody Map<String, Long> request) {

		Long accessoryId = request.get("plant_id");
		userService.resetPlantAccessoryQty(accessoryId);
	}

	// Get by ID
	@GetMapping("/plant_accessories/{id}")
	public PlantAccessories getPlantAccessoryById(@PathVariable("id") Long id) {
		return userService.getPlantAccessoryById(id);
	}

}
