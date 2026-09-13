package com.example.demo.controller;

import static java.util.Map.entry;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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

import jakarta.transaction.Transactional;

@RestController
@RequestMapping("/users")
@CrossOrigin(origins = {
    "http://localhost:5173",
    "https://ecommerce-application-six-pink.vercel.app"
})
public class UserController {

    @Autowired
    UserService userService;

    @PostMapping("/signup1")
    public ResponseEntity<?> addUser(@RequestBody Users user) {
        try {
            Users savedUser = userService.addUser(user);
            return ResponseEntity.ok(savedUser);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/check-email")
    public Map<String, Boolean> checkEmail(@RequestParam String email) {
        boolean exists = userService.emailExists(email);
        return Map.of("exists", exists);
    }

    @GetMapping("/{email}")
    public ResponseEntity<?> getUserDetails(@PathVariable String email) {
        Optional<Users> user = userService.getUserByEmail(email);
        if (user.isPresent()) {
            return ResponseEntity.ok(user.get());
        } else {
            return ResponseEntity.status(404).body("User not found");
        }
    }

    @PostMapping("/login1")
    public Boolean loginUser(@RequestBody LoginData logindata) {
        return userService.loginUser(logindata);
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

    @GetMapping("/cart")
    public List<Map<String, Object>> getCartItems() {
        List<Map<String, Object>> cart = new ArrayList<>();

        userService.getAllIndoarPlants().stream()
            .filter(p -> p.getQty() != null && p.getQty() > 0)
            .forEach(p -> cart.add(Map.of(
                    "id", p.getId(),
                    "name", p.getCommonName(),
                    "price", p.getPrice(),
                    "selling_price", p.getSellingPrice(),
                    "qty", p.getQty(),
                    "image", p.getImgUrl(),
                    "category", "Indoor Plants"
            )));

        userService.getAllOutdoarPlants().stream()
            .filter(p -> p.getQty() != null && p.getQty() > 0)
            .forEach(p -> cart.add(Map.of(
                    "id", p.getId(),
                    "name", p.getCommonName(),
                    "price", p.getPrice(),
                    "selling_price", p.getSellingPrice(),
                    "qty", p.getQty(),
                    "image", p.getImgUrl(),
                    "category", "Outdoor Plants"
            )));

        userService.getAllFruitsVegitables().stream()
            .filter(p -> p.getQty() != null && p.getQty() > 0)
            .forEach(p -> cart.add(Map.of(
                    "id", p.getId(),
                    "name", p.getCommonName(),
                    "price", p.getPrice(),
                    "selling_price", p.getSellingPrice(),
                    "qty", p.getQty(),
                    "image", p.getImgUrl(),
                    "category", "Fruits & Vegetables"
            )));

        userService.getAllSeeds().stream()
            .filter(p -> p.getQty() != null && p.getQty() > 0)
            .forEach(p -> cart.add(Map.of(
                    "id", p.getId(),
                    "name", p.getCommonName(),
                    "price", p.getPrice(),
                    "selling_price", p.getSellingPrice(),
                    "qty", p.getQty(),
                    "image", p.getImgUrl(),
                    "category", "Seeds"
            )));

        userService.getAllSoilPesticides().stream()
            .filter(p -> p.getQty() != null && p.getQty() > 0)
            .forEach(p -> cart.add(Map.of(
                    "id", p.getId(),
                    "name", p.getProductName(),
                    "price", p.getPrice(),
                    "selling_price", p.getSellingPrice(),
                    "qty", p.getQty(),
                    "image", p.getImgUrl(),
                    "category", "Soil & Pesticides"
            )));

        userService.getAllFloweringPlants().stream()
            .filter(p -> p.getQty() != null && p.getQty() > 0)
            .forEach(p -> cart.add(Map.of(
                    "id", p.getId(),
                    "name", p.getCommonName(),
                    "price", p.getPrice(),
                    "selling_price", p.getSellingPrice(),
                    "qty", p.getQty(),
                    "image", p.getImgUrl(),
                    "category", "Flowering Plants"
            )));

        userService.getAllHerbs().stream()
            .filter(p -> p.getQty() != null && p.getQty() > 0)
            .forEach(p -> cart.add(Map.of(
                    "id", p.getId(),
                    "name", p.getCommonName(),
                    "price", p.getPrice(),
                    "selling_price", p.getSellingPrice(),
                    "qty", p.getQty(),
                    "image", p.getImgUrl(),
                    "category", "Herbs"
            )));

        userService.getAllBulbs().stream()
            .filter(p -> p.getQty() != null && p.getQty() > 0)
            .forEach(p -> cart.add(Map.of(
                    "id", p.getId(),
                    "name", p.getCommonName(),
                    "price", p.getPrice(),
                    "selling_price", p.getSellingPrice(),
                    "qty", p.getQty(),
                    "image", p.getImgUrl(),
                    "category", "Bulbs"
            )));

        userService.getAllSucculentsCactus().stream()
            .filter(p -> p.getQty() != null && p.getQty() > 0)
            .forEach(p -> cart.add(Map.of(
                    "id", p.getId(),
                    "name", p.getCommonName(),
                    "price", p.getPrice(),
                    "selling_price", p.getSellingPrice(),
                    "qty", p.getQty(),
                    "image", p.getImgUrl(),
                    "category", "Succulents & Cactus"
            )));

        userService.getAllPlantAccessories().stream()
            .filter(p -> p.getQty() != null && p.getQty() > 0)
            .forEach(p -> cart.add(Map.of(
                    "id", p.getId(),
                    "name", p.getName(),
                    "price", p.getPrice(),
                    "selling_price", p.getSellingPrice(),
                    "qty", p.getQty(),
                    "image", p.getImgUrl(),
                    "category", "Accessories"
            )));

        return cart;
    }

    @PostMapping("/cart/clear")
    @Transactional
    public ResponseEntity<?> clearCart() {
        try {
            userService.clearCart();
            return ResponseEntity.ok("Cart cleared successfully");
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Failed to clear cart: " + e.getMessage());
        }
    }

    @GetMapping("/indoarplants/{id}")
    public IndoarPlant getIndoarPlantById(@PathVariable("id") int plantId) {
        return userService.getIndoarPlantById(plantId);
    }

    @GetMapping("/outdoarplants")
    public List<OutdoarPlants> getOutdoarPlants() {
        return userService.getAllOutdoarPlants();
    }

    @PostMapping("/outdoarplants/updateQty")
    public OutdoarPlants updateOutdoarQty(@RequestBody Map<String, Integer> request) {
        Long plantId = request.get("plant_id").longValue();
        Integer qty = request.get("qty");
        return userService.updateOutdoarQty(plantId, qty);
    }

    @PostMapping("/outdoarplants/resetQty")
    public void resetOutdoarQty(@RequestBody Map<String, Long> request) {
        Long plantId = request.get("plant_id");
        userService.resetOutdoarQty(plantId);
    }

    @GetMapping("/outdoarplants/{id}")
    public OutdoarPlants getOutdoarPlantById(@PathVariable("id") Long plantId) {
        return userService.getOutdoarPlantById(plantId);
    }

    @GetMapping("/fruits_vegitables")
    public List<Fruits_Vegitables> getFruitsVegitables() {
        return userService.getAllFruitsVegitables();
    }

    @PostMapping("/fruits_vegitables/updateQty")
    public Fruits_Vegitables updateFruitsVegitablesQty(@RequestBody Map<String, Integer> request) {
        Long itemId = request.get("plant_id").longValue();
        Integer qty = request.get("qty");
        return userService.updateFruitsVegitablesQty(itemId, qty);
    }

    @PostMapping("/fruits_vegitables/resetQty")
    public void resetFruitsVegitablesQty(@RequestBody Map<String, Long> request) {
        Long itemId = request.get("plant_id");
        userService.resetFruitsVegitablesQty(itemId);
    }

    @GetMapping("/fruits_vegitables/{id}")
    public Fruits_Vegitables getFruitsVegitablesById(@PathVariable("id") Long itemId) {
        return userService.getFruitsVegitablesById(itemId);
    }

    @GetMapping("/seeds")
    public List<Seeds> getAllSeeds() {
        return userService.getAllSeeds();
    }

    @PostMapping("/seeds/updateQty")
    public Seeds updateSeedsQty(@RequestBody Map<String, Integer> request) {
        Long seedId = request.get("plant_id").longValue();
        Integer qty = request.get("qty");
        return userService.updateSeedsQty(seedId, qty);
    }

    @PostMapping("/seeds/resetQty")
    public void resetSeedsQty(@RequestBody Map<String, Long> request) {
        Long seedId = request.get("plant_id");
        userService.resetSeedsQty(seedId);
    }

    @GetMapping("/seeds/{id}")
    public Seeds getSeedsById(@PathVariable("id") Long seedId) {
        return userService.getSeedsById(seedId);
    }

    @GetMapping("/soil_pesticides")
    public List<SoilPesticides> getAllSoilPesticides() {
        return userService.getAllSoilPesticides();
    }

    @PostMapping("/soil_pesticides/updateQty")
    public SoilPesticides updateSoilPesticideQty(@RequestBody Map<String, Integer> request) {
        Long itemId = request.get("plant_id").longValue();
        Integer qty = request.get("qty");
        return userService.updateSoilPesticideQty(itemId, qty);
    }

    @PostMapping("/soil_pesticides/resetQty")
    public void resetSoilPesticideQty(@RequestBody Map<String, Long> request) {
        Long itemId = request.get("plant_id");
        userService.resetSoilPesticideQty(itemId);
    }

    @GetMapping("/soil_pesticides/{id}")
    public SoilPesticides getSoilPesticideById(@PathVariable("id") Long id) {
        return userService.getSoilPesticideById(id);
    }

    @GetMapping("/flowering_plants")
    public List<FloweringPlant> getFloweringPlants() {
        return userService.getAllFloweringPlants();
    }

    @PostMapping("/flowering_plants/updateQty")
    public FloweringPlant updateFloweringPlantQty(@RequestBody Map<String, Integer> request) {
        Long plantId = request.get("plant_id").longValue();
        Integer qty = request.get("qty");
        return userService.updateFloweringPlantQty(plantId, qty);
    }

    @PostMapping("/flowering_plants/resetQty")
    public void resetFloweringPlantQty(@RequestBody Map<String, Long> request) {
        Long plantId = request.get("plant_id");
        userService.resetFloweringPlantQty(plantId);
    }

    @GetMapping("/flowering_plants/{id}")
    public FloweringPlant getFloweringPlantById(@PathVariable("id") Long id) {
        return userService.getFloweringPlantById(id);
    }

    @GetMapping("/herbs")
    public List<Herbs> getHerbs() {
        return userService.getAllHerbs();
    }

    @PostMapping("/herbs/updateQty")
    public Herbs updateHerbQty(@RequestBody Map<String, Integer> request) {
        Long herbId = request.get("plant_id").longValue();
        Integer qty = request.get("qty");
        return userService.updateHerbQty(herbId, qty);
    }

    @PostMapping("/herbs/resetQty")
    public void resetHerbQty(@RequestBody Map<String, Long> request) {
        Long herbId = request.get("plant_id");
        userService.resetHerbQty(herbId);
    }

    @GetMapping("/herbs/{id}")
    public Herbs getHerbById(@PathVariable("id") Long id) {
        return userService.getHerbById(id);
    }

    @GetMapping("/bulbs")
    public List<Bulbs> getBulbs() {
        return userService.getAllBulbs();
    }

    @PostMapping("/bulbs/updateQty")
    public Bulbs updateBulbQty(@RequestBody Map<String, Integer> request) {
        Long bulbId = request.get("plant_id").longValue();
        Integer qty = request.get("qty");
        return userService.updateBulbQty(bulbId, qty);
    }

    @PostMapping("/bulbs/resetQty")
    public void resetBulbQty(@RequestBody Map<String, Long> request) {
        Long bulbId = request.get("plant_id");
        userService.resetBulbQty(bulbId);
    }

    @GetMapping("/bulbs/{id}")
    public Bulbs getBulbById(@PathVariable("id") Long id) {
        return userService.getBulbById(id);
    }

    @GetMapping("/succulents_cactus")
    public List<SucculentsCactus> getSucculentsCactus() {
        return userService.getAllSucculentsCactus();
    }

    @PostMapping("/succulents_cactus/updateQty")
    public SucculentsCactus updateSucculentCactusQty(@RequestBody Map<String, Integer> request) {
        Long plantId = request.get("plant_id").longValue();
        Integer qty = request.get("qty");
        return userService.updateSucculentCactusQty(plantId, qty);
    }

    @PostMapping("/succulents_cactus/resetQty")
    public void resetSucculentCactusQty(@RequestBody Map<String, Long> request) {
        Long plantId = request.get("plant_id");
        userService.resetSucculentCactusQty(plantId);
    }

    @GetMapping("/succulents_cactus/{id}")
    public SucculentsCactus getSucculentCactusById(@PathVariable("id") Long id) {
        return userService.getSucculentCactusById(id);
    }

    @GetMapping("/plant_accessories")
    public List<PlantAccessories> getPlantAccessories() {
        return userService.getAllPlantAccessories();
    }

    @PostMapping("/plant_accessories/updateQty")
    public PlantAccessories updatePlantAccessoryQty(@RequestBody Map<String, Integer> request) {
        Long accessoryId = request.get("plant_id").longValue();
        Integer qty = request.get("qty");
        return userService.updatePlantAccessoryQty(accessoryId, qty);
    }

    @PostMapping("/plant_accessories/resetQty")
    public void resetPlantAccessoryQty(@RequestBody Map<String, Long> request) {
        Long accessoryId = request.get("plant_id");
        userService.resetPlantAccessoryQty(accessoryId);
    }

    @GetMapping("/plant_accessories/{id}")
    public PlantAccessories getPlantAccessoryById(@PathVariable("id") Long id) {
        return userService.getPlantAccessoryById(id);
    }

    private Object safe(Object v) {
        return v == null ? "" : v;
    }

    @GetMapping("/search")
    public List<Map<String, Object>> globalSearch(
            @RequestParam("query") String query) {

        List<Map<String, Object>> results = new ArrayList<>();
        String q = query == null ? "" : query.trim().toLowerCase();

        userService.getAllIndoarPlants().stream()
            .filter(p -> p.getCommonName() != null &&
                p.getCommonName().toLowerCase().trim().contains(q.trim()))
            .forEach(p -> results.add(Map.ofEntries(
                entry("id", safe(p.getId())),
                entry("name", safe(p.getCommonName())),
                entry("price", safe(p.getPrice())),
                entry("image", safe(p.getImgUrl())),
                entry("family", safe(p.getFamily())),
                entry("origin", safe(p.getOrigin())),
                entry("climate", safe(p.getClimate())),
                entry("zone", safe(p.getZone())),
                entry("selling_price", safe(p.getSellingPrice())),
                entry("discount_percent", safe(p.getDiscountPercent())),
                entry("rating", safe(p.getRating())),
                entry("description", safe(p.getDescription())),
                entry("benefits", safe(p.getBenefits())),
                entry("steps_to_grow", safe(p.getStepsToGrow())),
                entry("category", "Indoor Plants")
        )));

        userService.getAllOutdoarPlants().stream()
            .filter(p -> p.getCommonName() != null &&
                p.getCommonName().toLowerCase().trim().contains(q.trim()))
            .forEach(p -> results.add(Map.ofEntries(
                entry("id", safe(p.getId())),
                entry("name", safe(p.getCommonName())),
                entry("price", safe(p.getPrice())),
                entry("image", safe(p.getImgUrl())),
                entry("family", safe(p.getFamily())),
                entry("origin", safe(p.getOrigin())),
                entry("climate", safe(p.getClimate())),
                entry("zone", safe(p.getZone())),
                entry("selling_price", safe(p.getSellingPrice())),
                entry("discount_percent", safe(p.getDiscountPercent())),
                entry("rating", safe(p.getRating())),
                entry("description", safe(p.getDescription())),
                entry("benefits", safe(p.getBenefits())),
                entry("steps_to_grow", safe(p.getStepsToGrow())),
                entry("category", "Outdoor Plants")
        )));

        userService.getAllFruitsVegitables().stream()
            .filter(p -> p.getCommonName() != null &&
                p.getCommonName().toLowerCase().trim().contains(q.trim()))
            .forEach(p -> results.add(Map.ofEntries(
                entry("id", safe(p.getId())),
                entry("name", safe(p.getCommonName())),
                entry("price", safe(p.getPrice())),
                entry("image", safe(p.getImgUrl())),
                entry("family", safe(p.getFamily())),
                entry("origin", safe(p.getOrigin())),
                entry("climate", safe(p.getClimate())),
                entry("zone", safe(p.getZone())),
                entry("selling_price", safe(p.getSellingPrice())),
                entry("discount_percent", safe(p.getDiscountPercent())),
                entry("rating", safe(p.getRating())),
                entry("description", safe(p.getDescription())),
                entry("benefits", safe(p.getBenefits())),
                entry("steps_to_grow", safe(p.getStepsToGrow())),
                entry("category", "Fruits & Vegetables")
        )));

        userService.getAllSeeds().stream()
            .filter(p -> p.getCommonName() != null &&
                p.getCommonName().toLowerCase().trim().contains(q.trim()))
            .forEach(p -> results.add(Map.ofEntries(
                entry("id", safe(p.getId())),
                entry("name", safe(p.getCommonName())),
                entry("price", safe(p.getPrice())),
                entry("image", safe(p.getImgUrl())),
                entry("family", safe(p.getFamily())),
                entry("origin", safe(p.getOrigin())),
                entry("climate", safe(p.getClimate())),
                entry("zone", safe(p.getZone())),
                entry("selling_price", safe(p.getSellingPrice())),
                entry("discount_percent", safe(p.getDiscountPercent())),
                entry("rating", safe(p.getRating())),
                entry("description", safe(p.getDescription())),
                entry("benefits", safe(p.getBenefits())),
                entry("steps_to_grow", safe(p.getStepsToGrow())),
                entry("category", "Seeds")
        )));

        userService.getAllSoilPesticides().stream()
            .filter(p -> p.getProductName() != null &&
                p.getProductName().toLowerCase().trim().contains(q.trim()))
            .forEach(p -> results.add(Map.ofEntries(
                entry("id", safe(p.getId())),
                entry("name", safe(p.getProductName())),
                entry("price", safe(p.getPrice())),
                entry("image", safe(p.getImgUrl())),
                entry("origin", safe(p.getOrigin())),
                entry("zone", safe(p.getZone())),
                entry("selling_price", safe(p.getSellingPrice())),
                entry("discount_percent", safe(p.getDiscountPercent())),
                entry("rating", safe(p.getRating())),
                entry("description", safe(p.getDescription())),
                entry("benefits", safe(p.getBenefits())),
                entry("steps_to_grow", safe(p.getDirectionsToUse())),
                entry("category", "Soil & Pesticides")
        )));

        userService.getAllFloweringPlants().stream()
            .filter(p -> p.getCommonName() != null &&
                p.getCommonName().toLowerCase().trim().contains(q.trim()))
            .forEach(p -> results.add(Map.ofEntries(
                entry("id", safe(p.getId())),
                entry("name", safe(p.getCommonName())),
                entry("price", safe(p.getPrice())),
                entry("image", safe(p.getImgUrl())),
                entry("family", safe(p.getFamily())),
                entry("origin", safe(p.getOrigin())),
                entry("climate", safe(p.getClimate())),
                entry("zone", safe(p.getZone())),
                entry("selling_price", safe(p.getSellingPrice())),
                entry("discount_percent", safe(p.getDiscountPercent())),
                entry("rating", safe(p.getRating())),
                entry("description", safe(p.getDescription())),
                entry("benefits", safe(p.getBenefits())),
                entry("steps_to_grow", safe(p.getStepsToGrow())),
                entry("category", "Flowering Plants")
        )));

        userService.getAllHerbs().stream()
            .filter(p -> p.getCommonName() != null &&
                p.getCommonName().toLowerCase().trim().contains(q.trim()))
            .forEach(p -> results.add(Map.ofEntries(
                entry("id", safe(p.getId())),
                entry("name", safe(p.getCommonName())),
                entry("price", safe(p.getPrice())),
                entry("image", safe(p.getImgUrl())),
                entry("family", safe(p.getFamily())),
                entry("origin", safe(p.getOrigin())),
                entry("climate", safe(p.getClimate())),
                entry("zone", safe(p.getZone())),
                entry("selling_price", safe(p.getSellingPrice())),
                entry("discount_percent", safe(p.getDiscountPercent())),
                entry("rating", safe(p.getRating())),
                entry("description", safe(p.getDescription())),
                entry("benefits", safe(p.getBenefits())),
                entry("steps_to_grow", safe(p.getStepsToGrow())),
                entry("category", "Herbs")
        )));

        userService.getAllBulbs().stream()
            .filter(p -> p.getCommonName() != null &&
                p.getCommonName().toLowerCase().trim().contains(q.trim()))
            .forEach(p -> results.add(Map.ofEntries(
                entry("id", safe(p.getId())),
                entry("name", safe(p.getCommonName())),
                entry("price", safe(p.getPrice())),
                entry("image", safe(p.getImgUrl())),
                entry("family", safe(p.getFamily())),
                entry("origin", safe(p.getOrigin())),
                entry("climate", safe(p.getClimate())),
                entry("zone", safe(p.getZone())),
                entry("selling_price", safe(p.getSellingPrice())),
                entry("discount_percent", safe(p.getDiscountPercent())),
                entry("rating", safe(p.getRating())),
                entry("description", safe(p.getDescription())),
                entry("benefits", safe(p.getBenefits())),
                entry("steps_to_grow", safe(p.getStepsToGrow())),
                entry("category", "Bulbs")
        )));

        userService.getAllSucculentsCactus().stream()
            .filter(p -> p.getCommonName() != null &&
                p.getCommonName().toLowerCase().trim().contains(q.trim()))
            .forEach(p -> results.add(Map.ofEntries(
                entry("id", safe(p.getId())),
                entry("name", safe(p.getCommonName())),
                entry("price", safe(p.getPrice())),
                entry("image", safe(p.getImgUrl())),
                entry("family", safe(p.getFamily())),
                entry("origin", safe(p.getOrigin())),
                entry("climate", safe(p.getClimate())),
                entry("zone", safe(p.getZone())),
                entry("selling_price", safe(p.getSellingPrice())),
                entry("discount_percent", safe(p.getDiscountPercent())),
                entry("rating", safe(p.getRating())),
                entry("description", safe(p.getDescription())),
                entry("benefits", safe(p.getBenefits())),
                entry("steps_to_grow", safe(p.getStepsToGrow())),
                entry("category", "Succulents & Cactus")
        )));

        userService.getAllPlantAccessories().stream()
            .filter(p -> p.getName() != null &&
                p.getName().toLowerCase().trim().contains(q.trim()))
            .forEach(p -> results.add(Map.ofEntries(
                entry("id", safe(p.getId())),
                entry("name", safe(p.getName())),
                entry("price", safe(p.getPrice())),
                entry("image", safe(p.getImgUrl())),
                entry("selling_price", safe(p.getSellingPrice())),
                entry("discount_percent", safe(p.getDiscountPercent())),
                entry("rating", safe(p.getRating())),
                entry("description", safe(p.getDescription())),
                entry("benefits", safe(p.getBenefits())),
                entry("steps_to_grow", safe(p.getUsageSteps())),
                entry("category", "Accessories")
        )));

        return results;
    }
}