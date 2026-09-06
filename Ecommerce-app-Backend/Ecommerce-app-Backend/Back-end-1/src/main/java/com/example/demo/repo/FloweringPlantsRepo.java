package com.example.demo.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.FloweringPlant;

@Repository
public interface FloweringPlantsRepo extends JpaRepository<FloweringPlant, Long>{

}
